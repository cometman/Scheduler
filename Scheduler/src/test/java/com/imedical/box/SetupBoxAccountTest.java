package com.imedical.box;

import static org.junit.Assert.*;

import org.junit.Test;

import com.imedical.Scheduler.data.ProviderVO;
import com.imedical.data.TestUser;

public class SetupBoxAccountTest {

	TestUser testUser = new TestUser();
	static SetupBoxAccount setupAccount;

	@Test
	public void canLoadBoxAccount() {
		System.out.println(testUser.getTestUser().getAuth_key());
		setupAccount = new SetupBoxAccount(testUser.getTestUser());
		assertTrue(setupAccount.createRootSchedulerFolder());
	}

}
