package com.imedical.common;

import java.io.IOException;
import java.util.Calendar;
import java.util.Date;

import javax.xml.bind.PropertyException;

import com.imedical.Scheduler.data.PatientVO;
import com.imedical.Scheduler.data.PatientVOList;
import com.imedical.Scheduler.data.ProviderVO;
import com.imedical.Scheduler.data.calendar.AppointmentEvent;
import com.imedical.model.ProviderModel;

/**
 * Test data for use during development
 * 
 * @author cometman
 * 
 */
public class TestData {
	private static PatientVO testPatient1 = new PatientVO();
	private static PatientVO testPatient2 = new PatientVO();
	private static PatientVO testPatient3 = new PatientVO();
	private static PatientVOList pvoList = new PatientVOList();

	private static ProviderModel testModel = new ProviderModel();
	private static ProviderVO testProvider;
	
	private static AppointmentEvent appointment1 = new AppointmentEvent();
	private static AppointmentEvent appointment2 = new AppointmentEvent();

	private static TestData _instance = new TestData();

	private TestData() {
		setTestMetaData();

		pvoList.addPatient(testPatient1);
		pvoList.addPatient(testPatient2);
		pvoList.addPatient(testPatient3);

		try {
			testProvider = PropertyLoader.getTestProvider();
		} catch (PropertyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static TestData getInstance() {

		return _instance;
	}

	private static void setTestMetaData() {
		testPatient1.setAge(22);
		testPatient1.setEmail("test@none.com");
		testPatient1.setFirstName("Clay");
		testPatient1.setLastName("selby");
		testPatient1.setMiddleName("Robert");
		testPatient1.setPaymentType("cash");
		testPatient1.setPhoneNumber("3256687007");
		testPatient1.setUniqueId(1);

		testPatient2.setAge(30);
		testPatient2.setEmail("john@none.com");
		testPatient2.setFirstName("Joe");
		testPatient2.setLastName("Bob");
		testPatient2.setMiddleName("Junior");
		testPatient2.setPaymentType("Check");
		testPatient2.setPhoneNumber("9167891452");
		testPatient2.setUniqueId(2);

		testPatient3.setAge(40);
		testPatient3.setEmail("happy@gmail.com");
		testPatient3.setFirstName("Xena");
		testPatient3.setLastName("Princess");
		testPatient3.setMiddleName("Tester");
		testPatient3.setPaymentType("cash");
		testPatient3.setPhoneNumber("2103831234");
		testPatient3.setUniqueId(3);
		
//		testModel.setProviderDataFileID(testProvider);
		appointment1.setAllDay(false);
		appointment1.setCaption("Genereal checkup");
		appointment1.setDescription("Here is some of the description that will take place");
		appointment1.setStart(new Date());
		Calendar endCal = Calendar.getInstance();
		endCal.set(Calendar.HOUR,endCal.get(Calendar.HOUR)+1);
		appointment1.setEnd(Calendar.getInstance().getTime());
		appointment1.setPatientVO(testPatient1);
		
		appointment2.setAllDay(false);
		appointment2.setCaption("Genereal checkup");
		appointment2.setDescription("Here is some of the description that will take place");
		Calendar startCal = Calendar.getInstance();
		startCal.set(Calendar.HOUR, startCal.get(Calendar.HOUR)+3);
		
		appointment2.setStart(startCal.getTime());
		Calendar endCa1l = Calendar.getInstance();
		endCal.set(Calendar.HOUR, Calendar.getInstance().get(Calendar.HOUR)+1);
		appointment2.setEnd(endCa1l.getTime());
		appointment2.setPatientVO(testPatient1);
		
		testPatient1.addAppointment(appointment1);
		testPatient1.addAppointment(appointment2);
		
	}

	public PatientVOList getPatients() {

		return pvoList;
	}

	public ProviderVO getProvider() {
		return testProvider;

	}
}
