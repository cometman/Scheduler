package com.imedical.Scheduler.data;

import java.util.List;
import java.util.Map;

import com.imedical.Scheduler.data.calendar.AppointmentEvent;

public interface IPatientDAO {
	public List<PatientVO> getPatientByString(String searchQuery);

	public ProviderVO getProvider(String userid, String password);

	public List<AppointmentEvent> getAppointments(String providerID);

	public List<PatientVO> getPatients(String providerID);

	public List<PatientVO> getAllPatients();

	public void addNewPatient(PatientVO patient);

	public PatientVO getPatientByID(int ID);

}
