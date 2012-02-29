package com.imedical.Scheduler.data;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.imedical.Scheduler.data.calendar.AppointmentEvent;

public interface IProviderPatientDAO {
	public List<PatientVO> getPatientByString(String searchQuery);

	public ProviderVO getProvider(String userid, String password) throws Exception;

	public List<AppointmentEvent> getAppointments(String providerID);

	public List<PatientVO> getPatients(String providerID);

	public List<PatientVO> getAllPatients();

	public void addNewPatient(PatientVO patient);

	public PatientVO getPatientByID(int ID);

	public boolean isProviderEmailValid(String email);

	public boolean isProviderUserNameValid(String userName);

	public List<ProviderVO> loadProvidersInList();
	
	public void addProvider(ProviderVO provider)
			throws UnsupportedOperationException, SQLException;
	
	public void deleteProvider(ProviderVO provider);

}
