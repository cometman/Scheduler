package com.imedical.Scheduler.data;

import java.io.Serializable;
import java.util.Observer;

import com.imedical.Scheduler.data.calendar.AppointmentEvent;
import com.vaadin.data.Item;
import com.vaadin.data.Property;
import com.vaadin.data.Property.ConversionException;
import com.vaadin.data.Property.ReadOnlyException;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.util.BeanItem;
import com.vaadin.ui.Button;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Field;
import com.vaadin.ui.Form;
import com.vaadin.ui.InlineDateField;
import com.vaadin.ui.PopupDateField;
import com.vaadin.ui.Select;
import com.vaadin.ui.Component.Event;

public class PatientForm extends Form implements Serializable {

	private static final long serialVersionUID = -8995688104424142712L;
	private PatientVO patient;
	private BeanItem<PatientVO> referenceBean;
	private static final String APPOINTMENTS = "appointments";
	private static final String NEXT_APPOINTMENT = "nextAppointmentEvent";
	private boolean isNewPatient = false;

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
		if (patientBean.getItemProperty(NEXT_APPOINTMENT) == null) {
			nextAppointmentField.setInputPrompt("No appointment scheduled");
		}
		nextAppointmentField.setInputPrompt(patientBean.getItemProperty(
				NEXT_APPOINTMENT).toString());
		nextAppointmentField.setCaption("Next Appointment");
		this.addField(NEXT_APPOINTMENT, nextAppointmentField);
		nextAppointmentField.setReadOnly(false);
		referenceBean = patientBean;

	}

	public void existingPatientContent(BeanItem<PatientVO> patientBean) {
//		setEditStatus(false);
		for (Object o : patientBean.getItemPropertyIds()) {
			this.getField(NEXT_APPOINTMENT).setVisible(false);
			this.getField(APPOINTMENTS).setVisible(false);
			this.getItemProperty(o).setReadOnly(true);
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

	public PatientVO getPatient() {
		return this.patient;
	}

	public void setIsNewPatient() {
		isNewPatient = true;
	}

}
