package com.imedical.Scheduler.data;

import java.io.Serializable;

import com.imedical.Scheduler.data.calendar.AppointmentEvent;
import com.vaadin.data.Item;
import com.vaadin.data.util.BeanItem;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Form;
import com.vaadin.ui.PopupDateField;

public class PatientForm extends Form implements Serializable {

	private static final long serialVersionUID = -8995688104424142712L;
	private PatientVO patient;
	private BeanItem<PatientVO> referenceBean;
	private static final String APPOINTMENTS = "appointments";
	private static final String NEXT_APPOINTMENT = "nextAppointmentEvent";
	private boolean isNewPatient = false;
	private Form appointmentForm;

	public PatientForm(PatientVO patient) {
		BeanItem<PatientVO> patientBean = new BeanItem<PatientVO>(patient);
		this.setItemDataSource(patientBean);
		// If this is an existing patient show all
		if (patient.getUniqueId() > 0) {
			existingPatientContent(patientBean);
		}
		// If not a new patient, hide unneeded content
		else {
			setupNewPatientContent(patientBean);
		}

		/*
		 * Add the appointment combo box to the form. This will allow the user
		 * to select an appointment to review it
		 */
		ComboBox appointmentCombos = new ComboBox();
		appointmentCombos.setCaption("Appointments");
		this.addField(APPOINTMENTS, appointmentCombos);
		appointmentCombos.setInputPrompt("Select to view");
		for (AppointmentEvent e : patient.getAppointments()) {
			appointmentCombos.addItem(e.getStart());
		}
		appointmentCombos.setImmediate(true);
		appointmentCombos.addListener(new ValueChangeListener() {
			private static final long serialVersionUID = -1543419028358085731L;

			@Override
			public void valueChange(
					com.vaadin.data.Property.ValueChangeEvent event) {
				System.out.println(event.getProperty().getValue());

			}
		});

		/*
		 * Add the Next appointment select to the view
		 */
		PopupDateField nextAppointmentField = new PopupDateField();
		// nextAppointmentField.setPropertyDataSource(patientBean
		// .getItemProperty(NEXT_APPOINTMENT));
		if (patientBean.getItemProperty(NEXT_APPOINTMENT).getValue() == null) {
			nextAppointmentField.setInputPrompt("No appointment scheduled");
		}
		nextAppointmentField.setInputPrompt(patientBean.getItemProperty(
				NEXT_APPOINTMENT).toString());
		nextAppointmentField.setCaption("Next Appointment");
		nextAppointmentField.setWidth("50%");
		nextAppointmentField.setStyleName("next-appointment-field");
		nextAppointmentField.setImmediate(true);

		nextAppointmentField.addListener(new ValueChangeListener() {

			@Override
			public void valueChange(
					com.vaadin.data.Property.ValueChangeEvent event) {
				System.out.println("Dates chaging");
				appointmentForm.setVisible(true);

			}
		});

		this.addField(NEXT_APPOINTMENT, nextAppointmentField);
		this.getField("uniqueId").setVisible(false);
		nextAppointmentField.setReadOnly(true);
		referenceBean = patientBean;

	}

	public void existingPatientContent(BeanItem<PatientVO> patientBean) {
		// setEditStatus(false);
	
		for (Object o : patientBean.getItemPropertyIds()) {
			this.getField(NEXT_APPOINTMENT).setVisible(false);
			this.getField(APPOINTMENTS).setVisible(false);

		}
	}

	public void setupNewPatientContent(BeanItem<PatientVO> patientBean) {
		setEditStatus(true);
		for (Object o : patientBean.getItemPropertyIds()) {
			this.getField(NEXT_APPOINTMENT).setVisible(false);
			this.getField(APPOINTMENTS).setVisible(false);
			this.getItemProperty(o).setReadOnly(false);
		}
	}

	@Override
	public void setItemDataSource(Item newDataSource) {
		super.setItemDataSource(newDataSource);
	}

	public void setEditStatus(boolean value) {
		// for (Object o : referenceBean.getItemPropertyIds()) {
		// this.getField(o).setReadOnly(value);
		// }
	}

	public void setPatient(PatientVO patients) {
		this.patient = patients;

	}

	public void setAppointmentForm(Form appointmentForm) {
		if (appointmentForm != null) {
			this.appointmentForm = appointmentForm;
		
		}
	}

	public PatientVO getPatient() {
		return this.patient;
	}

	public void setIsNewPatient() {
		isNewPatient = true;
	}

}
