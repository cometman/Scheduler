package com.imedical.box;

import static org.junit.Assert.*;

import org.junit.Test;

import com.imedical.Scheduler.data.ProviderVO;
import com.imedical.box.userregistration.RegisterUserBox;
import com.imedical.model.ProviderModel;

public class RegisterUserCreateOperations {

	@Test
	public void canCreateRootFolder(){
		RegisterUserBox registerUser = new RegisterUserBox();
		
		ProviderVO provider = registerUser.constructProvider("admin@pasadya.com",
				"halflife87");
		
		System.out.println(provider.getFirstName());
		registerUser.setupFolderStructure(provider);
		registerUser.createTemplateFile(provider);
		
	}

}
