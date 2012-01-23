package com.imedical.Scheduler.mobilePages;

import java.util.ResourceBundle;

import com.imedical.Scheduler.data.DBConnectionPool;
import com.vaadin.addon.touchkit.ui.TabBarView;
import com.vaadin.ui.TabSheet.Tab;

public class MainTabSheet extends TabBarView {

	private PatientTab patientView;
	private CalendarTab calendarView;
	private SettingsTab settingsView;
	private AppointmentTab appointmentView;
	private DBConnectionPool poolConnection;

	public MainTabSheet() {
		poolConnection = new DBConnectionPool();
	}

	@Override
	public void attach() {
		// TODO Auto-generated method stub
		super.attach();

		patientView = new PatientTab();
		Tab addTab = addTab(patientView);
		addTab.setCaption("Patients");

		calendarView = new CalendarTab();
		addTab = addTab(calendarView);
		addTab.setCaption("Calendar");

		appointmentView = new AppointmentTab();
		addTab = addTab(appointmentView);
		addTab.setCaption("New Appointment");

		settingsView = new SettingsTab();
		addTab = addTab(settingsView);
		addTab.setCaption("Settings");

	}

}
