package com.imedical.box;

import java.rmi.RemoteException;
import java.util.List;

import javax.xml.rpc.ServiceException;
import javax.xml.rpc.holders.StringHolder;

import boxnet.BoxnetServiceLocator;
import boxnet.holders.SOAPUserHolder;

import com.google.gwt.benchmarks.client.Setup;
import com.imedical.Scheduler.MyVaadinApplication;
import com.imedical.Scheduler.data.IPatientDAO;
import com.imedical.Scheduler.data.PatientDAO;
import com.imedical.Scheduler.data.ProviderVO;
import com.vaadin.data.util.BeanItem;
import com.vaadin.ui.Window;

public class RegisterNewUser {
	private ProviderVO provider;
	private boolean emailStatus = true;
	private boolean usernameStatus = true;
	private boolean nameNotNull = true;
	private boolean boxRegisterResult = true;
	private String usernameErrorReason = "";
	private String errorMessage;
	private IPatientDAO patientDAO = new PatientDAO();
	private BoxnetServiceLocator boxService = new BoxnetServiceLocator();
	private static final Box_Finals APIKEY = Box_Finals.APIKEY;
	private StringHolder boxStatus;
	private StringHolder auth_token;
	private SOAPUserHolder soapUser;

	public RegisterNewUser(BeanItem<ProviderVO> bean) {
		List<ProviderVO> providers = patientDAO.loadProvidersInList();
		/*
		 * Validation for the registration forum
		 */
		provider = bean.getBean();
		for (ProviderVO p : providers) {
			if (p.getEmail().equals(provider.getEmail())) {
				emailStatus = false;
				break;
			}
			if (p.getUsername().equals(provider.getUsername())) {
				usernameStatus = false;
				usernameErrorReason = "username is taken.";
				break;
			}

		}
		/*
		 * Ensure there are no null values
		 */
		if (provider.getEmail() == null) {
			emailStatus = false;
		}
		if (provider.getFirstName() == null) {
			nameNotNull = false;
		}
		if (provider.getUsername() == null) {
			nameNotNull = false;
			usernameErrorReason = "field is empty.";
		}

		/*
		 * Check all of the conditions to see if we can register the user
		 */
		if (emailStatus) {
			if (usernameStatus) {
				if (nameNotNull) {
					if (boxRegisterResult) {
						try {
							System.out.println(APIKEY.toString());
							boxS
							boxService.getboxnetPort().register_new_user(
									APIKEY.toString(), provider.getUsername(),
									provider.getPassword(), boxStatus,
									auth_token, soapUser);
							System.out.println("Registering user!");
							MyVaadinApplication.get().setUser(provider);
						} catch (RemoteException e) {
							e.printStackTrace();
						} catch (ServiceException e) {
							e.printStackTrace();
						}

					} else {
						errorMessage = "Problem registering with Box.net.";
						displayErrorMessage(errorMessage);
					}
				} else {
					errorMessage = "You must enter your name.";
					displayErrorMessage(errorMessage);
				}
			} else {
				errorMessage = "Invalid Username: " + usernameErrorReason;
				displayErrorMessage(errorMessage);
			}
		} else {
			errorMessage = "Email is invalid!";
			displayErrorMessage(errorMessage);
		}

	}

	public boolean checkExistingEmail(ProviderVO p) {
		return emailStatus;
	}

	public boolean checkUserNameStatus(ProviderVO p) {
		return usernameStatus;
	}

	public boolean checkNameNotNull(ProviderVO p) {
		return nameNotNull;
	}

	public boolean boxRegisterResult(ProviderVO p) {
		return boxRegisterResult;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void displayErrorMessage(String message) {
		MyVaadinApplication
				.get()
				.getMainWindow()
				.showNotification("Error", message,
						Window.Notification.TYPE_WARNING_MESSAGE);
	}
}
