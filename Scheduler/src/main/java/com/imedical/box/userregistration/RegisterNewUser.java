package com.imedical.box.userregistration;

import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.List;

import com.imedical.Scheduler.MyVaadinApplication;
import com.imedical.Scheduler.data.IProviderPatientDAO;
import com.imedical.Scheduler.data.ProviderPatientDAO;
import com.imedical.Scheduler.data.ProviderVO;
import com.vaadin.data.util.BeanItem;
import com.vaadin.ui.Window;

public class RegisterNewUser {
	private ProviderVO provider;
	private boolean registrationComplete = false;
	private IProviderPatientDAO patientDAO = new ProviderPatientDAO();
	private List<ProviderVO> providers = patientDAO.loadProvidersInList();
	private RegisterUserBox registerUserWithBox;

	/*
	 * Registration and validation for the registration forum
	 */
	public RegisterNewUser(BeanItem<ProviderVO> bean) {
		/*
		 * Check to see if there were any problems registering new user. If
		 * there weren't, set the user of the application and bring to home
		 * screen. Otherwise, notify the user of the errors.
		 */
		if (bean != null) {
			provider = bean.getBean();
			runValidationProcedure();
		}

	}

	/*
	 * Run the validation procedure
	 */
	public void runValidationProcedure() {
		if (!doNullsExistInForm()) {
			if (!areUsernameOrPasswordTaken()) {
				registerUserWithBox();
			}
		}
	}

	/*
	 * Register user with box.net
	 */

	public void registerUserWithBox() {
		try {
			registerUserWithBox = new RegisterUserBox(provider);
			if (registerUserWithBox.getAttempt()) {
				try {
					provider.setAuth_key(registerUserWithBox.getAuthToken());
					patientDAO.addProvider(provider);

				} catch (UnsupportedOperationException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} finally {
					/*
					 * Create the users folders and files
					 */
					CreateBoxFolderAndFile createBoxAndFiles = new CreateBoxFolderAndFile(
							provider);
					if (createBoxAndFiles.createBaseApplicationFolder()) {
						if (createBoxAndFiles.createTemplateFile()) {
							registrationComplete = true;
						}
					}
				}

			} else {
				displayErrorMessage(registerUserWithBox.getErrorMessage());
			}

		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/*
	 * Check the status of the registration
	 */
	public boolean isRegistrationComplete() {
		if (!registrationComplete) {
			return false;
		}
		return true;

	}

	/*
	 * Validate the email, first name, and user name
	 */
	public boolean doNullsExistInForm() {
		if (areStringsInvalid(provider.getEmail())) {
			displayErrorMessage(RegistrationErrorMessages.NULL_EMAIL);
			return true;
		}
		if (areStringsInvalid(provider.getFirstName())) {
			displayErrorMessage(RegistrationErrorMessages.NULL_FIRST_NAME);
			return true;
		}
		return false;
	}

	/*
	 * Check the user information against existing values in database
	 */
	public boolean areUsernameOrPasswordTaken() {

		for (ProviderVO p : providers) {
			if (p.getEmail().equals(provider.getEmail())) {
				displayErrorMessage(RegistrationErrorMessages.TAKEN_EMAIL);
				return true;
			}
		}

		return false;
	}

	/*
	 * Notify the user of the error message. Set errors exist in form to true;
	 */
	public void displayErrorMessage(String message) {
		MyVaadinApplication
				.get()
				.getMainWindow()
				.showNotification("Error", message,
						Window.Notification.TYPE_WARNING_MESSAGE);
	}

	/*
	 * Make sure strings arent null or ""
	 */
	public boolean areStringsInvalid(String s) {
		if (s == null || s.equals("")) {
			return true;
		}

		return false;
	}

	public ProviderVO getRegisteredUser() {
		return provider;
	}

	/*
	 * Write registration information to database
	 */

}
