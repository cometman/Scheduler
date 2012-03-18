package com.imedical.data;

import com.imedical.Scheduler.data.ProviderVO;

public class TestUser {

	public ProviderVO testUser;

	public TestUser() {
		testUser = new ProviderVO();
		testUser.setAuth_key("ck0t03t44gye5umcyboyxt96ru41ck9y");
		testUser.setEmail("admin@pasadya.com");
		testUser.setFirstName("Danielle");
		testUser.setPassword("halflife87");
	}

	public ProviderVO getTestUser() {

		return testUser;
	}

}
