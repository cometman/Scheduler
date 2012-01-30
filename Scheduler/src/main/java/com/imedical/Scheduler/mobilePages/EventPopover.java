package com.imedical.Scheduler.mobilePages;

import com.imedical.Scheduler.data.PatientVO;
import com.imedical.Scheduler.data.calendar.AppointmentEvent;
import com.vaadin.addon.calendar.event.CalendarEvent;
import com.vaadin.addon.touchkit.ui.Popover;
import com.vaadin.ui.Label;
import com.vaadin.ui.Panel;

public class EventPopover extends Popover {
	private static final long serialVersionUID = 8427128744793827874L;
	private Panel eventViewPanel = new Panel();
	private Label eventTitleLabel = new Label();
	private Label patientName = new Label();
	private StringBuilder stringBuilder = new StringBuilder();
	private PatientVO patient;
	private AppointmentEvent calendarEvent;

	public EventPopover(AppointmentEvent event) {
		calendarEvent = event;
		patient = event.getPatientVO();
		this.populateLablels();

		setHeight("50%");
		setWidth("50%");
		center();

		eventViewPanel.setCaption(event.getStart().toString());
		this.addComponent(eventViewPanel);
	}

	public void populateLablels() {

		eventViewPanel.addComponent(eventTitleLabel);
		Label patientInformation = new Label("Patient Information");
		eventViewPanel.addComponent(patientInformation);
		stringBuilder.append(patient.getFirstName() + " "
				+ patient.getLastName());
		Label ageLabel = new Label(patient.getAge() + "");
		Label phoneLabel = new Label(patient.getPhoneNumber());
		eventViewPanel.addComponent(ageLabel);
		eventViewPanel.addComponent(phoneLabel);
		patientName.setCaption(stringBuilder.toString());
		eventViewPanel.addComponent(patientName);

	}
}
