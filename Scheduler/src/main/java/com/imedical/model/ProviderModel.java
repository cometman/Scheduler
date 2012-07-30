package com.imedical.model;

import java.util.ArrayList;
import java.util.List;

import com.imedical.Scheduler.data.IProviderPatientDAO;
import com.imedical.Scheduler.data.PatientDAOFactory;
import com.imedical.Scheduler.data.PatientVO;
import com.imedical.Scheduler.data.ProviderVO;
import com.imedical.Scheduler.data.calendar.AppointmentEvent;
import com.imedical.box.BoxIOData;
import com.imedical.box.Box_Finals;
import com.imedical.box.IBoxIOData;
import com.imedical.box.accountTree.FileVO;
import com.imedical.box.accountTree.FolderVO;
import com.imedical.common.PropertyLoader;

public class ProviderModel {
	private ProviderVO provider;
	private List<PatientVO> patients = new ArrayList<PatientVO>();
	private List<AppointmentEvent> appointments = new ArrayList<AppointmentEvent>();
	private IProviderPatientDAO ipatientDAO = PatientDAOFactory.getInstance().getImplementation();
	private boolean authStatus = false;
	private String schedulerFileID;
	private String schedulerFodlerID;
	private String uploadedFileID;
	private ProviderModel providerModel = this;
	private IBoxIOData boxIOData = new BoxIOData();

	public ProviderModel(String userID, String password) throws Exception {

		provider = ipatientDAO.getProvider(userID, password);

		if (this.getProvider() != null && PropertyLoader.isProd()) {
			authStatus = true;
			setProviderDataFileID(provider);
		} else if (this.getProvider() != null && PropertyLoader.isTest()) {
			authStatus = true;
		}
		// setProviderDataFileID(provider);
		// // Set the local data source equal to the file from Box.net
		// // LogUtil.writeMessage(Pr)
		// // this.patients = setPatientsForModel(provider.getId());
		// // this.appointments = setAppointmentsForModel(provider.getId());
		//
		// }

	}

	public ProviderModel(ProviderVO provider) {
		if (provider != null) {
			authStatus = true;
			setProviderDataFileID(provider);
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
		appointments.addAll(ipatientDAO.getAppointments());
		return appointments;
	}

	public boolean getAuthStatus() {
		return authStatus;
	}

	public void setProviderDataFileID(ProviderVO provider) {

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

				/*
				 * If we cannot find any files, create a file in the box account
				 * for patient scheduler
				 */

				// if (f.getFiles() == null) {
				//
				// RegisterUserBox rgb = new RegisterUserBox();
				// rgb.createTemplateFile(this.getProvider());
				//
				// }
				//
				// else {
				for (FileVO file : f.getFiles()) {
					/*
					 * Once we have the file, download it and pass to the
					 * getPatientsFromXML method
					 */
					if (file.getFileName().equals(
							Box_Finals.BOX_FILE_PATIENT_SCHEDULER)) {
						schedulerFileID = file.getId();
					}
					/*
					 * This file is for editing patients.xml. It is a uplaoded
					 * file.
					 */
					if (file.getFileName().equals(provider.getId() + ".xml")) {
						uploadedFileID = file.getId();
					}
				}
			}
		}
	}

	public String getUploadedFileID() {
		return uploadedFileID;
	}

	public String getProviderDataFileID() {
		return schedulerFileID;
	}

	public String getProviderDataFolderID() {
		return schedulerFodlerID;
	}

}