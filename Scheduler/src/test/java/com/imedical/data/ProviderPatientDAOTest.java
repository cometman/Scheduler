package com.imedical.data;

import static org.junit.Assert.*;

import java.io.File;
import java.util.List;

import org.junit.Test;

import com.imedical.Scheduler.data.IProviderPatientDAO;
import com.imedical.Scheduler.data.PatientVO;
import com.imedical.Scheduler.data.PatientVOList;
import com.imedical.Scheduler.data.ProviderPatientDAO;
import com.imedical.Scheduler.data.ProviderVO;
import com.imedical.box.BoxIOData;
import com.imedical.box.IBoxIOData;
import com.imedical.model.ProviderModel;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

public class ProviderPatientDAOTest {

	IProviderPatientDAO daotest = new ProviderPatientDAO();
	IBoxIOData ibox = new BoxIOData();
	IProviderPatientDAO pdao = new ProviderPatientDAO();

	static ProviderVO testProvider;

	@Test
	public void canRetrieveFileForLoadingData() throws Exception {
		ProviderModel model = new ProviderModel("admin@pasadya.com",
				"halflife87");

		PatientVOList patients = daotest
				.createPatientsAndAppointmentFromXML(model);
		assertNotNull(patients);
		System.out.println(patients.getPatients().size());
	}

	@Test
	public void canGeneratePatientObjectsFromData() throws Exception {
		ProviderModel testProvider = new ProviderModel("admin@pasadya.com",
				"halflife87");

		List<PatientVO> allPatients = pdao.getAllPatients(testProvider);
		assertTrue(allPatients.size() > 2);

	}

	@Test
	public void canAddPatientToTheBoxFile() throws Exception {
		PatientVO xena = new PatientVO();
		xena.setAge(22);
		xena.setEmail("Xena@xena.com");
		xena.setFirstName("Xena");
		xena.setLastName("Selby");
		xena.setMiddleName("Bobina");
		xena.setPaymentType("cash");
		xena.setPhoneNumber("32566879007");
		xena.setReferral("Yes");
		xena.setUniqueId(123);
		ProviderModel model = new ProviderModel("admin@pasadya.com",
				"halflife87");
		pdao.addNewPatient(model, xena);

	}

	@Test
	public void canEditPatientInBoxFile() {

		fail("Not yet implemented");

	}

	@Test
	public void canRemovePatientInBoxFile() {
		fail("Not yet implemented");

	}
}
