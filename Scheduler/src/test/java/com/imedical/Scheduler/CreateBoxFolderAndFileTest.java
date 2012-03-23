package com.imedical.Scheduler;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.imedical.Scheduler.data.ProviderVO;
import com.imedical.box.userregistration.CreateBoxFolderAndFile;
import com.imedical.model.ProviderModel;

public class CreateBoxFolderAndFileTest {
	ProviderModel providerModel;
	CreateBoxFolderAndFile cbf;

	@Before
	public void setupVars() throws Exception {
		providerModel = new ProviderModel("admin@pasadya.com", "halflife87");
		cbf = new CreateBoxFolderAndFile(providerModel.getProvider());
	}

	@Test
	public void canCreateBoxFolderAndFile() {
		assertTrue(cbf.createBaseApplicationFolder());
		assertTrue(cbf.createTemplateFile());
	}

}
