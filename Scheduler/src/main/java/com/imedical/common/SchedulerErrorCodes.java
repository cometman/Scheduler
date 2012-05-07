package com.imedical.common;

public class SchedulerErrorCodes {

	public static final int BOX_IOERROR = 1;
	public static final int PATIENT_RETRIEVAL_ERROR = 2;
	public static final int PROVIDER_RETRIEVAL_ERROR = 3;
	public static final int IMEDICAL_SERVER_ERROR = 4;
	public static final int GENERAL_ERROR = 5;
	public static final int APPOINTMENTS_ERROR = 5;

	public static String getErrorMessage(int errorCode) {
		switch (errorCode) {
		case BOX_IOERROR:
			return "Problem connecting to Box.net";
		case PATIENT_RETRIEVAL_ERROR:
			return "Problem retrieving patient information";
		case APPOINTMENTS_ERROR:
			return "Problem retrieving appointment information";
		default:
			return "General error!";
		}

	}

}
