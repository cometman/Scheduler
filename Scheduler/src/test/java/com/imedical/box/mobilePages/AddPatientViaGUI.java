package com.imedical.box.mobilePages;

import static org.junit.Assert.*;

import org.junit.Test;

import com.imedical.Scheduler.MyVaadinApplication;
import com.imedical.Scheduler.data.PatientTable;
import com.imedical.Scheduler.data.PatientVO;
import com.imedical.model.ProviderModel;
import com.vaadin.data.Item;

public class AddPatientViaGUI {

	@Test
	public void canCreatePatientVIAGUI() throws Exception {
		ProviderModel model = new ProviderModel("admin@pasadya.com",
				"halflife87");
		PatientTable table = new PatientTable(model);
//
		PatientVO test = new PatientVO();
		test.setAge(222222);
		test.setEmail("none");
		test.setFirstName("Clay");
		test.setLastName("Last");
		test.setMiddleName("me");
		test.setReferral("this");
		
		Item newItem = table.getTable().addItem(test);
//		System.out.println(newItem);
		
		for (Object item : table.getTable().getItemIds()){
			System.out.println(table.getTable().getItem(item));
		}
		
//		MyVaadinApplication.get().
		// System.out.println(table.getCaption());
		// MyVaadinApplication.get().setUser(model);
	}

}
