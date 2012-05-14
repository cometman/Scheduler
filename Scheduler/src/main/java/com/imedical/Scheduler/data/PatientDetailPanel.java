package com.imedical.Scheduler.data;

import java.util.List;

import com.imedical.Scheduler.MyVaadinApplication;
import com.imedical.Scheduler.data.calendar.AppointmentEvent;
import com.imedical.model.ProviderModel;
import com.vaadin.addon.touchkit.ui.NavigationButton;
import com.vaadin.addon.touchkit.ui.VerticalComponentGroup;
import com.vaadin.data.Item;
import com.vaadin.data.util.BeanItem;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Form;
import com.vaadin.ui.Panel;
import com.vaadin.ui.Table;

public class PatientDetailPanel extends Panel implements ClickListener {
	private static final long serialVersionUID = 6339603637916622029L;
	private PatientForm patientForm;
	private Button editInfo = new Button("Edit", this);
	private Button deleteButton = new Button("Delete", this);
	private ProviderModel providerModel = (ProviderModel) MyVaadinApplication
			.get().getUser();
	private PatientVO patient;
	private IProviderPatientDAO pdao = new ProviderPatientDAO();

	// Constructor for existing patients
	public PatientDetailPanel(PatientVO patient) {
		if (patient != null) {
			this.patient = patient;
		}

		// Attach the patient form to the screen
		patientForm = new PatientForm(patient);
		patientForm.setImmediate(true);
		addComponent(patientForm);

		if (patient.getUniqueId() != 0) {
			addEditButton();
			addComponent(deleteButton);
		}
		// Initially, set the form to read only
		for (Object o : patientForm.getItemDataSource().getItemPropertyIds()) {
			patientForm.getField(o).setReadOnly(true);
		}

		// Attach the appointment form to the screen, initially hide this form
		buildAppointmentForm();
	}

	/**
	 * Edit button and Delete button logic
	 * 
	 */
	@Override
	public void buttonClick(ClickEvent event) {
		// Edit Button - Toggle caption to "finished" and read only
		if (event.getButton().equals(editInfo)) {
			if (editInfo.getCaption().equals("Edit")) {
				editInfo.setCaption("Finished");
				for (Object o : patientForm.getItemDataSource()
						.getItemPropertyIds()) {
					System.out.println("Here " + o.toString());

					patientForm.getField(o).setReadOnly(false);
				}
			}
			// Toggle caption to EDIT and save results
			else {
				for (Object o : patientForm.getItemDataSource()
						.getItemPropertyIds()) {
					patientForm.getField(o).setReadOnly(true);

					editInfo.setCaption("Edit");
				}

				// Fetch the data from the form, in order to update the patient
				// object
				List<AppointmentEvent> appointments = patient.getAppointments();
				int patientID = (Integer) patientForm.getItemDataSource()
						.getItemProperty("uniqueId").getValue();
				int age = patient.getUniqueId();
				String email = (String) patientForm.getItemDataSource()
						.getItemProperty("email").getValue();
				String fname = (String) patientForm.getItemDataSource()
						.getItemProperty("firstName").getValue();
				String lname = (String) patientForm.getItemDataSource()
						.getItemProperty("lastName").getValue();
				String mname = (String) patientForm.getItemDataSource()
						.getItemProperty("middleName").getValue();
				String paytype = (String) patientForm.getItemDataSource()
						.getItemProperty("paymentType").getValue();
				String phone = (String) patientForm.getItemDataSource()
						.getItemProperty("phoneNumber").getValue();
				String referral = (String) patientForm.getItemDataSource()
						.getItemProperty("referral").getValue();

				// Check to see if a new appointment was set (Not null or empty)
				if (patientForm.getField("nextAppointmentEvent").getValue() != null
						&& !patientForm.getField("nextAppointmentEvent")
								.getValue().equals("")) {
					AppointmentEvent newAppointmentEvent = (AppointmentEvent) patientForm
							.getField("nextAppointmentEvent").getValue();

					// If a new appointment was scheduled, add it to the list of
					// current appointments.
					appointments.add(newAppointmentEvent);
				}

				// Create a new patient based on the new field data
				PatientVO editedPatient = new PatientVO();
				editedPatient.setUniqueId(patientID);
				editedPatient.setAge(age);
				editedPatient.setAppointments(appointments);
				editedPatient.setEmail(email);
				editedPatient.setFirstName(fname);
				editedPatient.setLastName(lname);
				editedPatient.setMiddleName(mname);
				editedPatient.setPaymentType(paytype);
				editedPatient.setPhoneNumber(phone);
				editedPatient.setReferral(referral);

				// Update the data source with the new patient object
				pdao.updateRecord(providerModel, editedPatient);
			}
			// Delete button
		} else if (event.getButton().equals(deleteButton)) {
			providerModel.getPatients().remove(patient);
			for (PatientVO patient : providerModel.getPatients()) {
				System.out.println(patient.getFirstName());
			}

		}
	}

	/**
	 * Display a link to show appointment details. This will toggel on and off
	 * depending on if next appoitnment is set.
	 */
	public void buildAppointmentForm() {
		// Create the bean for the appointment event

		Form appointmentForm = new Form();
		AppointmentEvent event = new AppointmentEvent();
		event.setPatientVO(patient);
		BeanItem<AppointmentEvent> appointmentBean = new BeanItem<AppointmentEvent>(
				event);

		// Set the data source for the form object
		appointmentForm.setItemDataSource(appointmentBean);

		// Configure which fields to show and styling
		appointmentForm.getField("patientVO").setVisible(false);
		appointmentForm.getField("styleName").setVisible(false);
		appointmentForm.getField("start").setVisible(false);
		appointmentForm.getField("end").setWidth("50%");
		patientForm.setAppointmentForm(appointmentForm);
		
		this.addComponent(appointmentForm);
		appointmentForm.setVisible(false);
	}

	public void addEditButton() {
		addComponent(editInfo);
	}

}
