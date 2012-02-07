package com.imedical.model;

import java.util.ArrayList;
import java.util.List;

import com.imedical.Scheduler.MyVaadinApplication;
import com.imedical.Scheduler.data.IPatientDAO;
import com.imedical.Scheduler.data.PatientDAO;
import com.imedical.Scheduler.data.PatientVO;
import com.imedical.Scheduler.data.ProviderVO;
import com.imedical.Scheduler.data.calendar.AppointmentEvent;

public class ProviderModel {
	private ProviderVO provider;
	private List<PatientVO> patients = new ArrayList<PatientVO>();
	private List<AppointmentEvent> appointments = new ArrayList<AppointmentEvent>();
	private IPatientDAO ipatientDAO = new PatientDAO();
	private boolean authStatus = false;

	private String test = "";

	// Constructor for existing members
	public ProviderModel(String userID, String password) {

		System.out.println("Trying provider..");
		provider = ipatientDAO.getProvider(userID, password);

		System.out.println("Invalid provider..");

		if (provider != null) {
			authStatus = true;
			System.out.println("Great!");
			// this.patients = setPatientsForModel(provider.getId());
			// this.appointments = setAppointmentsForModel(provider.getId());

		}
	}

	// Constructor for new members
	public ProviderModel() {
		provider = new ProviderVO();
	}

	public String getTest() {
		return test;
	}

	public String setTest(String test) {
		return this.test = test;
	}

	public ProviderVO getProvider() {
		return provider;
	}

	public List<PatientVO> getPatients() {
		return patients;
	}

	public List<AppointmentEvent> getAppointments() {
		return appointments;
	}

	public List<PatientVO> setPatientsForModel(String providerID) {
		List<PatientVO> patients = new ArrayList<PatientVO>();
		patients.addAll(ipatientDAO.getPatients(providerID));
		return patients;
	}

	public List<AppointmentEvent> setAppointmentsForModel(String providerID) {
		List<AppointmentEvent> appointments = new ArrayList<AppointmentEvent>();
		appointments.addAll(ipatientDAO.getAppointments(providerID));
		return appointments;
	}

	public boolean getAuthStatus() {
		return authStatus;
	}
}
