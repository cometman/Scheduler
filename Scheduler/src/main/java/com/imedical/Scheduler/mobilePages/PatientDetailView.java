package com.imedical.Scheduler.mobilePages;

import com.imedical.Scheduler.MyVaadinApplication;
import com.imedical.Scheduler.data.IProviderPatientDAO;
import com.imedical.Scheduler.data.PatientDetailPanel;
import com.imedical.Scheduler.data.PatientForm;
import com.imedical.Scheduler.data.PatientTable;
import com.imedical.Scheduler.data.PatientVO;
import com.imedical.Scheduler.data.ProviderPatientDAO;
import com.imedical.common.SchedulerException;
import com.imedical.model.ProviderModel;
import com.vaadin.addon.touchkit.ui.NavigationButton;
import com.vaadin.addon.touchkit.ui.NavigationManager;
import com.vaadin.addon.touchkit.ui.NavigationView;
import com.vaadin.data.Item;
import com.vaadin.data.Property;
import com.vaadin.ui.Button;
import com.vaadin.ui.Form;
import com.vaadin.ui.Label;
import com.vaadin.ui.Panel;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Table;

public class PatientDetailView extends NavigationView implements ClickListener {
	private static final long serialVersionUID = -6881253381664450543L;

	private PatientDetailPanel patientPanel = null;
	private Table table;
	private PatientVO patient;
	private ProviderModel model;

	// Constructor for EXISITING patients
	public PatientDetailView(PatientVO patient, Table table) {
		model = (ProviderModel) MyVaadinApplication.get().getUser();
		this.patient = patient;
		this.table = table;
		patientPanel = new PatientDetailPanel(patient);
		this.setCaption(patient.getFirstName() + " " + patient.getLastName());

		this.getLeftComponent().setCaption("Back");
		this.setRightComponent(new Button("New Appointment", this));
		// this.getRightComponent().setCaption("Stuff");
		setContent(patientPanel);

	}

	/**
	 * Schedule appointment button
	 */
	@Override
	public void buttonClick(ClickEvent event) {

		try {
			AppointmentView appointmentView = new AppointmentView(null, patient);
			this.getNavigationManager().navigateTo(appointmentView);
		} catch (SchedulerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// this.getNavigationManager().navigateBack();
		// PatientVO comparePatient;
		// System.out.println(patient.getFirstName());
		// for (Object item : table.getItemIds()) {
		// table.removeItem(item);
		// comparePatient = (PatientVO) item;
		// if (comparePatient.getUniqueId() == patient.getUniqueId()) {
		// System.out.println(comparePatient.getFirstName());
		// System.out.println("ID: "+ item.toString());
		// table.removeItem(item);
		// MyVaadinApplication.get().getMainWindow().showNotification("Removing patient");
		// // IProviderPatientDAO pdao = new ProviderPatientDAO();
		// // pdao.removePatient(model, patient);
		// }

	}
}
