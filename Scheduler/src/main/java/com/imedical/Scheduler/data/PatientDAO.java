package com.imedical.Scheduler.data;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Map;

public class PatientDAO implements IPatientDAO {

	private List<PatientVO> patientList = new ArrayList<PatientVO>();

	public List<PatientVO> getPatientByString(String searchQuery) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<PatientVO> getAllPatients() {
		// Test Patients
		GregorianCalendar gCal = new GregorianCalendar();
		PatientVO patient1 = new PatientVO();
		patient1.setFirstName("Clay");
		patient1.setLastName("Selby");
		patient1.setAge(22);
		gCal.add(Calendar.DAY_OF_WEEK, 1);
		patient1.setNextAppointment(gCal.getTime());

		PatientVO patient2 = new PatientVO();
		patient2.setFirstName("Todd");
		patient2.setLastName("Selby");
		patient2.setAge(25);
		gCal.add(Calendar.DAY_OF_WEEK, 1);
		patient2.setNextAppointment(gCal.getTime());

		patientList.add(patient2);
		patientList.add(patient1);

		return patientList;
	}

	public void addNewPatient(PatientVO patient) {
		// TODO Auto-generated method stub
	}

	@Override
	public PatientVO getPatientByID(int ID) {
		// TODO Auto-generated method stub
		return null;
	}

}
