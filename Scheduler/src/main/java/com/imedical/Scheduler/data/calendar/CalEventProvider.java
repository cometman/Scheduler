package com.imedical.Scheduler.data.calendar;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import com.imedical.Scheduler.MyVaadinApplication;
import com.imedical.Scheduler.data.PatientContainer;
import com.imedical.Scheduler.data.PatientVO;
import com.vaadin.addon.calendar.event.CalendarEvent;
import com.vaadin.addon.calendar.event.CalendarEventProvider;
import com.vaadin.addon.calendar.ui.CalendarComponentEvents.EventClick;
import com.vaadin.addon.calendar.ui.CalendarComponentEvents.EventClickHandler;
import com.vaadin.ui.Component.Event;

public class CalEventProvider implements CalendarEventProvider {

	private static final long serialVersionUID = 1550248810556339270L;

	private List<CalendarEvent> eventList = new ArrayList<CalendarEvent>();
	private List<AppointmentEvent> appointmentEvent = new ArrayList<AppointmentEvent>();
	private PatientContainer patientContainer;
	private MyVaadinApplication appReference = new MyVaadinApplication();

	public CalEventProvider() {

		patientContainer = appReference.getApplicationInstance()
				.getPatientContainer();

		for (int i = 0; i < patientContainer.size(); i++) {
			PatientVO patientItem = patientContainer.getIdByIndex(i);

			if (patientItem.getAppointments() != null) {
				for (AppointmentEvent e : patientItem.getAppointments()) {
					eventList.add(e);
					appointmentEvent.add(e);
				}
			}
		}
	}

	@Override
	public List<CalendarEvent> getEvents(Date startDate, Date endDate) {
		return eventList;
	}

	public AppointmentEvent getAppointmentByStartAndCaption(CalendarEvent event) {
		AppointmentEvent eventReturn = null;

		for (AppointmentEvent e : appointmentEvent) {
			if (e.getStart() == event.getStart()) {
				if (e.getEnd() == event.getEnd()) {
					eventReturn = e;
				}
			}

		}
		return eventReturn;
	}

}
