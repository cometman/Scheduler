package com.imedical.box.userregistration;

import java.io.File;
import java.io.IOException;
import java.rmi.RemoteException;

import javax.xml.rpc.holders.StringHolder;

import boxnet.holders.SOAPFolderHolder;

import com.imedical.Scheduler.data.ProviderVO;
import com.imedical.box.BoxIOData;
import com.imedical.box.Box_Finals;
import com.imedical.box.CreateBoxSession;
import com.imedical.box.IBoxIOData;
import com.imedical.common.LogUtil;

public class CreateBoxFolderAndFile {
	private ProviderVO provider;
	private SOAPFolderHolder createdFolder = new SOAPFolderHolder();

	public CreateBoxFolderAndFile(ProviderVO provider) {
		this.provider = provider;
	}

	/*
	 * Create the PatientScheduler folder for the user, as well as the XML file
	 * to serve as the patient scheduler data source.
	 */
	public boolean createBaseApplicationFolder() {
		StringHolder status = new StringHolder();

		long id = 0;
		long shared = 0;
		boolean createFolderResult = false;
		try {
			CreateBoxSession.initializeServiceLocator();
			CreateBoxSession.proxy.create_folder(Box_Finals.API_KEY,
					provider.getAuth_key(), id,
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
	public boolean createTemplateFile() {
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
			boxio.uploadDataFile(xmlTemplate, provider, folderID);
			createFileResult = true;
		} catch (Exception e) {
			LogUtil.writeMessage("Unable to create XML file for the new user!"
					+ e.getMessage());
			e.printStackTrace();
		} finally {
			xmlTemplate.delete();
		}
		return createFileResult;
	}
}
