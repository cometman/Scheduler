package com.imedical.Scheduler.data;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.imedical.Scheduler.data.calendar.AppointmentEvent;
import com.imedical.Scheduler.mobilePages.AppointmentView;
import com.imedical.common.TestData;
import com.imedical.model.ProviderModel;

public class TestProviderPatientDAO implements IProviderPatientDAO {

	private ProviderVO provider;
	private PatientVOList patientList = new PatientVOList();
	private TestData testData = TestData.getInstance();
	private List<AppointmentEvent> appointments;

	@Override
	public PatientVOList createPatientsAndAppointmentFromXML(
			ProviderModel providerModel) {

		return testData.getPatients();
	}

	@Override
	public List<PatientVO> getPatientByString(String searchQuery) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ProviderVO getProvider(String userid, String password)
			throws Exception {
		return testData.getProvider();
	}

	@Override
	public List<AppointmentEvent> getAppointments() {
		appointments = new ArrayList<AppointmentEvent>();

		List<PatientVO> patients = getAllPatients(null);

		for (PatientVO patient : patients) {
			appointments.addAll(patient.getAppointments());
		}
		return appointments;
	}

	@Override
	public List<AppointmentEvent> getAppointmentCached() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<PatientVO> getPatients(String providerID) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<PatientVO> getAllPatients(ProviderModel provider) {

		return testData.getPatients().getPatients();
	}

	@Override
	public void addNewPatient(ProviderModel providerModel, PatientVO patient) {
		testData.getPatients().addPatient(patient);

	}

	@Override
	public void removePatient(ProviderModel providerModel, PatientVO patient) {
		testData.getPatients().removePatient(patient);

	}

	@Override
	public void updateRecord(ProviderModel providerModel, PatientVO patient) {
		PatientVOList patientList = this
				.createPatientsAndAppointmentFromXML(providerModel);

		List<PatientVO> patients = patientList.getPatients();

		// Locate the patient by the patient ID
		for (PatientVO p : patients) {
			if (p.getUniqueId() == patient.getUniqueId()) {
				// Set the old patient object equal to the new patient changes
				patientList.removePatient(p);
				patientList.addPatient(patient);
				// Update the Provider Model list

				break;

			}
		}

	}

	@Override
	public PatientVO getPatientByID(int ID) {
		for (PatientVO patient : patientList.getPatients()) {
			if (patient.getUniqueId() == ID) {
				return patient;
			}
		}
		return null;
	}

	@Override
	public boolean isProviderEmailValid(String email) {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isProviderUserNameValid(String userName) {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public List<ProviderVO> loadProvidersInList() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addProvider(ProviderVO provider)
			throws UnsupportedOperationException, SQLException {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteProvider(ProviderVO provider) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<AppointmentEvent> getAppointmentsOnDate(Date date) {
		List<AppointmentEvent> appts = this.getAppointments();
		List<AppointmentEvent> validAppts = new ArrayList<AppointmentEvent>();

		// Create calendar objects so we can easily test the days
		Calendar appointmentsCal = Calendar.getInstance();

		Calendar requestedDateCal = Calendar.getInstance();
		requestedDateCal.setTime(date);

		// Look through list of appointments and find appointments that start on
		// the specified day
		for (AppointmentEvent appointment : appts) {
			appointmentsCal.setTime(appointment.getStart());

			// Does the year match?
			if (requestedDateCal.get(Calendar.YEAR) == appointmentsCal
					.get(Calendar.YEAR)) {
				// Does the day in the year match?
				if (requestedDateCal.get(Calendar.DAY_OF_YEAR) == appointmentsCal
						.get(Calendar.DAY_OF_YEAR)) {
					validAppts.add(appointment);
				}
			}
		}

		return validAppts;
	}
}
