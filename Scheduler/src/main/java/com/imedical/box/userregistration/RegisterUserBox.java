package com.imedical.box.userregistration;

import java.rmi.RemoteException;

import javax.xml.rpc.holders.StringHolder;

import boxnet.holders.SOAPUserHolder;

import com.imedical.Scheduler.data.ProviderVO;
import com.imedical.box.CreateBoxSession;

public class RegisterUserBox {
	private StringHolder status = new StringHolder();

	private SOAPUserHolder user = new SOAPUserHolder();
	public static String SUCCESS_RESPONSE = "successful_register";
	public static String BAD_EMAIL_INVALID_RESPONSE = "email_invalid";
	public static String BAD_EMAIL_ALREADY_REGISTERED_RESPONSE = "email_already_registered";
	public static String BAD_API_KEY_RESTRICTED = "application_restricted";
	public static String BAD_GENERIC_ERRORS = "e_register";
	private boolean registerAttempt = false;
	private String errorMessage = "";
	private StringHolder auth_token = new StringHolder();

	public RegisterUserBox() {

	}

	public RegisterUserBox(ProviderVO provider) throws RemoteException {

		CreateBoxSession.initializeServiceLocator();
		CreateBoxSession.proxy.register_new_user(CreateBoxSession.APIKEY,
				provider.getEmail(), provider.getPassword(), status,
				auth_token, user);
		if (status.value.equals(SUCCESS_RESPONSE)) {
			registerAttempt = true;

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

}
