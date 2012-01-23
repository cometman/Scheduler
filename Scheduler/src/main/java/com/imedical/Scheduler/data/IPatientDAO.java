package com.imedical.Scheduler.data;

import java.util.List;
import java.util.Map;

public interface IPatientDAO {
	public List<PatientVO> getPatientByString(String searchQuery);

	public List<PatientVO> getAllPatients();

	public void addNewPatient(PatientVO patient);

	public PatientVO getPatientByID(int ID);

}
