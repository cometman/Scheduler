package com.imedical.Scheduler.data;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Map;

import com.imedical.Scheduler.data.calendar.AppointmentEvent;
import com.vaadin.addon.sqlcontainer.SQLContainer;

public class PatientDAO implements IPatientDAO {

	private List<PatientVO> patientList = new ArrayList<PatientVO>();
	private DBConnectionPool connectionPool = new DBConnectionPool();
	private SQLContainer container;
	private ProviderVO provider;

	public List<PatientVO> getPatientByString(String searchQuery) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<PatientVO> getAllPatients() {
		// Test Patients
		AppointmentEvent testAppointment = new AppointmentEvent();

		GregorianCalendar gCal = new GregorianCalendar();
		PatientVO patient1 = new PatientVO();
		patient1.setFirstName("Clay");
		patient1.setLastName("Selby");
		patient1.setAge(22);
		patient1.setPhoneNumber("3256687007");
		patient1.setAge(22);

		testAppointment.setPatientVO(patient1);
		gCal.set(Calendar.HOUR_OF_DAY, 12);
		gCal.add(Calendar.DAY_OF_WEEK, 1);
		testAppointment.setStart(gCal.getTime());
		gCal.add(Calendar.HOUR, 1);
		testAppointment.setEnd(gCal.getTime());
		testAppointment.setCaption("New Test apointment");
		testAppointment.setDescription("THe new description for the test");
		patient1.addAppointment(testAppointment);

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

	@Override
	public ProviderVO getProvider(String userid, String password) {

		container = connectionPool.getProviderContainer();
		Object positionInDB = container.firstItemId();

		while (positionInDB != null) {
			if (container.getItem(positionInDB).getItemProperty("username")
					.toString().equals(userid)) {
				if (container.getItem(positionInDB).getItemProperty("password")
						.toString().equals(password)) {
					provider = new ProviderVO();
					provider.setUsername(userid);
					provider.setPassword(password);
					break;
				} else {
					System.out.println("Bad password");
					break;
				}
			} else {
				positionInDB = container.nextItemId(positionInDB);
			}
		}

		return provider;
	}

	@Override
	public List<AppointmentEvent> getAppointments(String providerID) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<PatientVO> getPatients(String providerID) {
		// TODO Auto-generated method stub
		return null;
	}

}
