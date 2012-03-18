package com.imedical.Scheduler;

import static org.junit.Assert.*;

import org.junit.Test;

import com.imedical.Scheduler.data.ProviderPatientDAO;
import com.imedical.Scheduler.data.ProviderVO;

public class GetProvider {
	static ProviderPatientDAO patientdao = new ProviderPatientDAO();
	static ProviderVO test;

	@Test
	public void canRetrieveProviderInfo() throws Exception {

		ProviderVO p;
		p = patientdao.getProvider("csbuiss@gmail.com", "halflife87");

		assertTrue(p.getFirstName().equals("Clay"));

	}

	@Test
	public void canAddNewProvider() throws Exception {
		test = new ProviderVO();
		test.setAuth_key("123");
		test.setEmail("test");
		test.setFirstName("test");
		test.setPassword("test");
		ProviderVO p = new ProviderVO();
		patientdao.addProvider(test);
		p = patientdao.getProvider("test", "test");
		assertTrue(p.getFirstName().equals("test"));

	}

	@Test
	public void canDeleteProvider() throws Exception {
		patientdao.deleteProvider(patientdao.getProvider("test", "test"));

		assertTrue(patientdao.getProvider("test", "test").equals(null));
	}


}
