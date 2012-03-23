package com.imedical.Scheduler;

import static org.junit.Assert.*;

import java.rmi.RemoteException;

import org.junit.Test;

import com.imedical.Scheduler.data.ProviderVO;
import com.imedical.box.userregistration.RegisterUserBox;

public class RegisterUser {

	@Test
	public void canRegisterUser() throws RemoteException {
		ProviderVO provider = new ProviderVO();
		provider.setFirstName("Clay");
		provider.setEmail("test@xyz12.com");
		provider.setPassword("halflife");
		
		RegisterUserBox registerUser = new RegisterUserBox(provider);

		assertTrue(registerUser.getAttempt());

		if (!registerUser.getAttempt()) {
			System.out.println(registerUser.getErrorMessage());
		}

	}

}
