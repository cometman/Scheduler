package com.imedical.box.userregistration;

import java.io.File;
import java.io.IOException;
import java.rmi.RemoteException;

import javax.xml.rpc.holders.StringHolder;

import com.imedical.Scheduler.data.IProviderPatientDAO;
import com.imedical.Scheduler.data.ProviderPatientDAO;
import com.imedical.Scheduler.data.ProviderVO;
import com.imedical.box.BoxIOData;
import com.imedical.box.Box_Finals;
import com.imedical.box.CreateBoxSession;
import com.imedical.box.IBoxIOData;
import com.imedical.common.LogUtil;
import com.imedical.model.ProviderModel;

import boxnet.holders.SOAPFolderHolder;
import boxnet.holders.SOAPUserHolder;

public class RegisterUserBox {
	private StringHolder status = new StringHolder();
	private StringHolder auth_token = new StringHolder();
	private SOAPUserHolder user = new SOAPUserHolder();
	public static String SUCCESS_RESPONSE = "successful_register";
	public static String BAD_EMAIL_INVALID_RESPONSE = "email_invalid";
	public static String BAD_EMAIL_ALREADY_REGISTERED_RESPONSE = "email_already_registered";
	public static String BAD_API_KEY_RESTRICTED = "application_restricted";
	public static String BAD_GENERIC_ERRORS = "e_register";
	private boolean registerAttempt = false;
	private String errorMessage = "";
	private SOAPFolderHolder createdFolder;
	private IProviderPatientDAO dao = new ProviderPatientDAO();
	private ProviderVO provider;

	public RegisterUserBox() {

	}

	public RegisterUserBox(ProviderVO provider) throws RemoteException {
		this.provider = provider;
		CreateBoxSession.initializeServiceLocator();
		CreateBoxSession.proxy.register_new_user(CreateBoxSession.APIKEY,
				provider.getEmail(), provider.getPassword(), status,
				auth_token, user);
		if (status.value.equals(SUCCESS_RESPONSE)) {
			registerAttempt = true;
			// ProviderVO newProvider = constructProvider(login, password);
			setupFolderStructure(provider);
			createTemplateFile(provider);

		} else if (status.value.equals(BAD_EMAIL_INVALID_RESPONSE)) {
			errorMessage = "The login provided is not a valid email address.";
			registerAttempt = false;

		} else if (status.value.equals(BAD_EMAIL_ALREADY_REGISTERED_RESPONSE)) {
			errorMessage = "The login provided is already registered by another user.";
			registerAttempt = false;

		} else if (status.value.equals(BAD_API_KEY_RESTRICTED)) {
			errorMessage = "You provided an invalid api_key, or the api_key is restricted from calling this function.";
			registerAttempt = false;

		} else if (status.value.equals(BAD_GENERIC_ERRORS)) {
			errorMessage = "Generic error warning for other errors.";
			registerAttempt = false;
		}
	}

	public String getAuthToken() {
		return auth_token.value;
	}

	public boolean getAttempt() {
		return registerAttempt;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	/*
	 * Construct the newly registered user as a Provider object
	 */
	public ProviderVO constructProvider(String userid, String password) {
		IProviderPatientDAO dao = new ProviderPatientDAO();
		ProviderVO newProvider = new ProviderVO();

		try {
			newProvider = dao.getProvider(userid, password);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return newProvider;
	}

	/*
	 * Create the PatientScheduler folder for the user, as well as the XML file
	 * to serve as the patient scheduler data source.
	 */
	public boolean setupFolderStructure(ProviderVO provider) {
		StringHolder status = new StringHolder();
		createdFolder = new SOAPFolderHolder();
		long id = 0;
		long shared = 0;
		boolean createFolderResult = false;
		try {
			CreateBoxSession.proxy.create_folder(Box_Finals.API_KEY,
					auth_token.value, id,
					Box_Finals.BOX_FOLDER_PATIENT_SCHEDULER, shared, status,
					createdFolder);

			createFolderResult = true;
		} catch (RemoteException e) {
			LogUtil.writeMessage("Remote exception when registering user. "
					+ e.getMessage());
			e.printStackTrace();
		}
		return createFolderResult;
	}

	/*
	 * Create the XML template file that will be the data source fot the
	 * application
	 */
	public boolean createTemplateFile(ProviderVO newProvider) {
		boolean createFileResult = false;
		IBoxIOData boxio = new BoxIOData();
		File xmlTemplate = new File("patients.xml");
		try {
			xmlTemplate.createNewFile();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		try {
			String folderID = String
					.valueOf(createdFolder.value.getFolder_id());
			boxio.uploadDataFile(xmlTemplate, newProvider, folderID);
			createFileResult = true;
		} catch (Exception e) {
			LogUtil.writeMessage("Unable to create XML file for the new user!"
					+ e.getMessage());
			e.printStackTrace();
		}
		return createFileResult;
	}
}
