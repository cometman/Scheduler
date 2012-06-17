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
import com.vaadin.data.Property.ValueChangeListener;
import com.vaadin.ui.Button;
import com.vaadin.ui.Form;
import com.vaadin.ui.Label;
import com.vaadin.ui.Panel;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Table;

public class PatientDetailView extends NavigationView {
	private static final long serialVersionUID = -6881253381664450543L;

	private PatientDetailPanel patientPanel = null;
	private Table table;
	private PatientVO patient;
	private ProviderModel model;
	private Button appointmentButton;

	// Constructor for EXISITING patients
	public PatientDetailView(PatientVO patient, Table table) {
		model = (ProviderModel) MyVaadinApplication.get().getUser();
		this.patient = patient;
		this.table = table;
		patientPanel = new PatientDetailPanel(patient);
		this.setCaption(patient.getFirstName() + " " + patient.getLastName());

		this.getLeftComponent().setCaption("Back");
		appointmentButton = new Button();
		appointmentButton.setCaption("New Appointment");
		this.setRightComponent(appointmentButton);

		setContent(patientPanel);

		// View reference for the panel we are displaying (allows us to access
		// the navigation manager)
		patientPanel.setViewReference(this);

	}

	public void setAppointmentListener(ClickListener listener) {
		System.out.println("adding listener");
		appointmentButton.addListener(listener);
	}

	public void navigateToAppointmentView(AppointmentView view) {
		System.out.println("here detail view");
		this.getNavigationManager().navigateTo(view);
	}

	public PatientVO getPatient() {
		return patient;
	}
}
