package com.imedical.Scheduler.data;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import com.imedical.Scheduler.data.calendar.AppointmentEvent;
import com.imedical.model.ProviderModel;

public interface IProviderPatientDAO {
	/**
	 * Create the value objects (POJOs) for all of the providers patients from
	 * XML.
	 * 
	 * @param providerModel
	 *            The provider to create the patients for
	 * @return List of patients (patients have apointments also)
	 */
	public PatientVOList createPatientsAndAppointmentFromXML(
			ProviderModel providerModel);

	public List<PatientVO> getPatientByString(String searchQuery);

	public ProviderVO getProvider(String userid, String password)
			throws Exception;

	/**
	 * Retrieve all appointments for patients. <b>Note</b> Current
	 * implementation only access database the first time when fetching
	 * patients.
	 * 
	 * @return
	 */
	public List<AppointmentEvent> getAppointments();

	/**
	 * Retrieve appointments between the specified dates
	 * 
	 * @param Date
	 *            The day to return appointments for
	 * @return
	 */
	public List<AppointmentEvent> getAppointmentsOnDate(Date date);

	public List<AppointmentEvent> getAppointmentCached();

	public List<PatientVO> getPatients(String providerID);

	public List<PatientVO> getAllPatients(ProviderModel provider);

	/**
	 * Add a new patient to the provider.
	 * 
	 * @param providerModel
	 *            Contains the provider, patients, appointments, etc of the
	 *            provider.
	 * @param patient
	 *            The patient to add to the provider.
	 */
	public void addNewPatient(ProviderModel providerModel, PatientVO patient);

	/**
	 * Remove a patient from the provider.
	 * 
	 * @param providerModel
	 *            Contains the provider, patients, appointments, etc of the
	 *            provider.
	 * @param patient
	 *            The patient to remove from the provider.
	 */
	public void removePatient(ProviderModel providerModel, PatientVO patient);

	/**
	 * Update a patient record
	 * 
	 * @param providerModel
	 * @param patient
	 */
	public void updateRecord(ProviderModel providerModel, PatientVO patient);

	public PatientVO getPatientByID(int ID);

	public boolean isProviderEmailValid(String email);

	public boolean isProviderUserNameValid(String userName);

	public List<ProviderVO> loadProvidersInList();

	public void addProvider(ProviderVO provider)
			throws UnsupportedOperationException, SQLException;

	public void deleteProvider(ProviderVO provider);

}
