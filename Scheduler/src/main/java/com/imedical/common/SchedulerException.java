package com.imedical.common;

import com.imedical.Scheduler.MyVaadinApplication;
import com.vaadin.ui.Window.Notification;

public class SchedulerException extends Exception {

	private static final long serialVersionUID = -3638034770296767149L;

	public SchedulerException(int errorCode) {
		displayErrorMessage(SchedulerErrorCodes.getErrorMessage(errorCode));
	}

	public static void displayErrorMessage(String errorMessage) {
		MyVaadinApplication
				.get()
				.getMainWindow()
				.showNotification("Error", errorMessage,
						Notification.TYPE_ERROR_MESSAGE);
	}
}
