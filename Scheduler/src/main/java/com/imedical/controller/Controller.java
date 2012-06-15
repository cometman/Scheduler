package com.imedical.controller;

import javax.swing.ButtonModel;

import com.imedical.Scheduler.MyVaadinApplication;
import com.imedical.Scheduler.data.PatientVO;
import com.imedical.Scheduler.data.ProviderVO;
import com.imedical.Scheduler.data.calendar.AppointmentEvent;
import com.imedical.Scheduler.data.calendar.CalendarPanel;
import com.imedical.Scheduler.mobilePages.AppointmentView;
import com.imedical.Scheduler.mobilePages.CalendarTab;
import com.imedical.Scheduler.mobilePages.LoginPage;
import com.imedical.Scheduler.mobilePages.MainTabSheet;
import com.imedical.Scheduler.mobilePages.NewPatientPage;
import com.imedical.Scheduler.mobilePages.PatientDetailView;
import com.imedical.Scheduler.mobilePages.PatientTab;
import com.imedical.Scheduler.mobilePages.RegisterWindow;
import com.imedical.Scheduler.mobilePages.SettingsTab;
import com.imedical.box.userregistration.RegisterNewUser;
import com.imedical.common.SchedulerException;
import com.imedical.model.ProviderModel;
import com.vaadin.addon.touchkit.ui.NavigationView;
import com.vaadin.addon.touchkit.ui.TabBarView;
import com.vaadin.data.Property;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.ComponentContainer.ComponentAttachEvent;
import com.vaadin.ui.ComponentContainer.ComponentAttachListener;
import com.vaadin.ui.Button;
import com.vaadin.ui.Table;
import com.vaadin.ui.Window;

public class Controller {

	// Model
	private ProviderModel model;

	// Views
	private static final String REGISTER_WINDOW = "RegisterWindow";

	private MainTabSheet mainTabSheet;
	private CalendarTab calendarTab;
	private PatientTab patientTab;
	private SettingsTab settingsTab;
	private Window mainWindow = MyVaadinApplication.get().getMainWindow();
	private NavigationView m_view;
	private Table table;
	private RegisterWindow registerWindow;
	private LoginPage loginPage;
	private RegisterNewUser registerNewUser;
	private PatientDetailView detailView;
	private AppointmentView appointmentView;
	private CalendarPanel calendarPanel;

	/*
	 * Tab Bar View - Add the tabs to the view and setup the views by passing in
	 * the model
	 */
	public Controller(TabBarView t_view, ProviderModel model) {
		setLoginWindow();
		mainWindow.removeWindow(loginPage);
		this.model = model;
		mainTabSheet = (MainTabSheet) t_view;
		mainWindow.setContent(t_view);
		MainTabSheetListener(t_view);

		calendarPanel = new CalendarPanel();
		patientTab = new PatientTab(this.model);
		calendarTab = new CalendarTab(calendarPanel);
		settingsTab = new SettingsTab();

		mainTabSheet.addTab(patientTab, "Patients");
		mainTabSheet.addTab(calendarTab, "Calendar");
		mainTabSheet.addTab(settingsTab, "Settings");

		buildPatientTab();

		// Add listener to the calendar tab
	}

	public Controller(NavigationView view, ProviderModel model) {
		this.model = model;
		this.m_view = view;
		registerWindow = (RegisterWindow) m_view;
		if (view.getClass().getSimpleName().equals(REGISTER_WINDOW)) {
			buildRegisterWindow();
		}

	}

	public void loginWindowToggle() {

	}

	/***********************************************************************
	 * REGISTER WINDOW
	 * *********************************************************************
	 */
	public void buildRegisterWindow() {
		setLoginWindow();

		loginPage.setVisible(false);
		if (registerWindow.isVisible() == false) {
			registerWindow.setVisible(true);
		}

		mainWindow.setContent(registerWindow);
		/*
		 * Cancel listener
		 */
		registerWindow.setCancelListener(new ClickListener() {

			private static final long serialVersionUID = -2817522398023135016L;

			@Override
			public void buttonClick(ClickEvent event) {
				registerWindow.setVisible(false);
				loginPage.setVisible(true);
			}
		});

		/*
		 * Submit listener
		 */
		registerWindow.setRegiserListener(new ClickListener() {
			@Override
			public void buttonClick(ClickEvent event) {
				// Make sure the passwords match!
				if (registerWindow.getPasswordChecker().equals(
						registerWindow.getProviderBeanVO().getBean()
								.getPassword())) {
					registerNewUser = new RegisterNewUser(registerWindow
							.getProviderBeanVO());

					/*
					 * If we made it through the registration process, set the
					 * user of the application to the newly registered one.
					 */
					if (registerNewUser.isRegistrationComplete()) {
						ProviderVO providerForModel = registerWindow
								.getProviderBeanVO().getBean();
						try {

							// ProviderModel providerModel = new ProviderModel(
							// providerForModel.getId(), providerForModel
							// .getPassword());

						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} finally {
							// Redirect the user to the login page. This will be
							// their first login..
							registerWindow.setVisible(false);
							loginPage.setVisible(true);

						}

					}
				} else {
					mainWindow.showNotification("Error",
							"Passwords do not Match",
							Window.Notification.TYPE_WARNING_MESSAGE);
				}
			}
		});

	}

	/***********************************************************************
	 * PATIENT TAB SETUP
	 * *********************************************************************
	 */
	public void buildPatientTab() {
		table = patientTab.getPatientTable().getTable();
		patientTab.getPatientTable().addTableSelectListener(
				new PatientTableDetailListener());
		patientTab.addButtonListener(new PatientTabAddPatientListener());
	}

	/*
	 * Handler for the table. When a patient is clicked go to detail view
	 */
	class PatientTableDetailListener implements Property.ValueChangeListener {

		private static final long serialVersionUID = -2817186818504262483L;

		@Override
		public void valueChange(ValueChangeEvent event) {
			PatientVO patient = (PatientVO) table.getValue();
			detailView = new PatientDetailView(patient, patientTab
					.getPatientTable().getTable());

			// Add the new appointment listener to the patient detail view

			patientTab.showDetailView(detailView);
			try {
				appointmentView = new AppointmentView(null,
						detailView.getPatient());
				appointmentView.setSaveListener(new SaveAppointmentListener());
			} catch (SchedulerException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			detailView.setAppointmentListener(new AppointmentListener());
		}
	}

	class PatientTabAddPatientListener implements ClickListener {

		private static final long serialVersionUID = 8260229853677600472L;

		@Override
		public void buttonClick(ClickEvent event) {
			System.out.println("click");

			patientTab.addNewPatient();

		}

	}

	/***********************************************************************
	 * APPOINTMENT TAB SETUP
	 * *********************************************************************
	 */

	/*
	 * Handler for navigating to the appointment page when new appointment is
	 * clicked.
	 */
	class AppointmentListener implements ClickListener {

		@Override
		public void buttonClick(ClickEvent event) {

			detailView.navigateToAppointmentView(appointmentView);

		}
	}

	/*
	 * Handler for saving appointments
	 */
	class SaveAppointmentListener implements ClickListener {

		@Override
		public void buttonClick(ClickEvent event) {
		
			AppointmentEvent appointment = appointmentView
					.createNewAppointment();

			calendarPanel.addNewEvent(appointment);
			calendarTab.requestRepaintAll();

		}

	}

	/***********************************************************************
	 * CALENDAR SETUP
	 * *********************************************************************
	 */

	/***********************************************************************
	 * SETTINGS SETUP
	 * *********************************************************************
	 */

	public void MainTabSheetListener(TabBarView t) {
		t.addListener(new ComponentAttachListener() {
			private static final long serialVersionUID = 1L;

			@Override
			public void componentAttachedToContainer(ComponentAttachEvent event) {
				System.out.println(event.getAttachedComponent().getClass()
						.getSimpleName());
			}
		});
	}

	public void setLoginWindow() {
		for (Window w : mainWindow.getChildWindows()) {
			loginPage = (LoginPage) w;
		}
	}

}
