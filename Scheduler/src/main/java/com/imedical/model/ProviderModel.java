package com.imedical.model;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import com.imedical.Scheduler.MyVaadinApplication;
import com.imedical.Scheduler.data.IProviderPatientDAO;
import com.imedical.Scheduler.data.ProviderPatientDAO;
import com.imedical.Scheduler.data.PatientVO;
import com.imedical.Scheduler.data.ProviderVO;
import com.imedical.Scheduler.data.calendar.AppointmentEvent;
import com.imedical.box.BoxIOData;
import com.imedical.box.Box_Finals;
import com.imedical.box.IBoxIOData;
import com.imedical.box.accountTree.FileVO;
import com.imedical.box.accountTree.FolderVO;
import com.imedical.common.LogUtil;
import com.vaadin.data.Item.PropertySetChangeEvent;
import com.vaadin.data.Item.PropertySetChangeListener;
import com.vaadin.data.Property;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.Property.ValueChangeListener;
import com.vaadin.data.util.BeanItem;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.ui.AbstractSelect.NewItemHandler;

public class ProviderModel {
	private ProviderVO provider;
	private List<PatientVO> patients = new ArrayList<PatientVO>();
	private List<AppointmentEvent> appointments = new ArrayList<AppointmentEvent>();
	private IProviderPatientDAO ipatientDAO = new ProviderPatientDAO();
	private boolean authStatus = false;
	private String schedulerFileID;
	private String schedulerFodlerID;
	private ProviderModel providerModel = this;
	private IBoxIOData boxIOData = new BoxIOData();

	// Constructor for existing members
	public ProviderModel(String userID, String password) throws Exception {

		System.out.println("Trying provider..");
		provider = ipatientDAO.getProvider(userID, password);

		if (provider != null) {
			authStatus = true;
			setProviderDataFileID();
			// Set the local data source equal to the file from Box.net
			// LogUtil.writeMessage(Pr)
			// this.patients = setPatientsForModel(provider.getId());
			// this.appointments = setAppointmentsForModel(provider.getId());

		}
	}

	// Constructor for new members
	public ProviderModel() {
		provider = new ProviderVO();
	}

	public ProviderVO getProvider() {
		return provider;
	}

	public List<PatientVO> getPatients() {
		return patients;
	}

	public List<AppointmentEvent> getAppointments() {
		return appointments;
	}

	public List<PatientVO> setPatientsForModel(String providerID) {
		List<PatientVO> patients = ipatientDAO
				.createPatientsAndAppointmentFromXML(this).getPatients();
		return patients;
	}

	// TODO - Look at takign this out..we may need only to get patient objects..
	public List<AppointmentEvent> setAppointmentsForModel(String providerID) {
		List<AppointmentEvent> appointments = new ArrayList<AppointmentEvent>();
		appointments.addAll(ipatientDAO.getAppointments(providerID));
		return appointments;
	}

	public boolean getAuthStatus() {
		return authStatus;
	}

	private void setProviderDataFileID() {
		FolderVO providerAccount = boxIOData.createAccoutTreePOJO(boxIOData
				.getAccountTreeXML(0, provider));

		for (FolderVO f : providerAccount.getFolders()) {
			/*
			 * First find the patient scheduler folder
			 */
			if (f.getName().equals(Box_Finals.BOX_FOLDER_PATIENT_SCHEDULER)) {
				/*
				 * Next, locate the patient scheduler file
				 */
				schedulerFodlerID = f.getId();
				for (FileVO file : f.getFiles()) {
					/*
					 * Once we have the file, download it and pass to the
					 * getPatientsFromXML method
					 */
					if (file.getFileName().equals(
							Box_Finals.BOX_FILE_PATIENT_SCHEDULER)) {
						schedulerFileID = file.getId();
					}
				}
			}
		}
	}

	public String getProviderDataFileID() {
		return schedulerFileID;
	}

	public String getProviderDataFolderID() {
		return schedulerFodlerID;
	}

}