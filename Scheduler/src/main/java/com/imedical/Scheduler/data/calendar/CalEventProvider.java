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

public class CalEventProvider implements CalendarEventProvider{

	private static final long serialVersionUID = 1550248810556339270L;

	private List<CalendarEvent> eventList = new ArrayList<CalendarEvent>();
	private PatientContainer patientContainer;
	private MyVaadinApplication appReference = new MyVaadinApplication();

	public CalEventProvider() {

		patientContainer = appReference.getApplicationInstance()
				.getPatientContainer();

		for (int i = 0; i < patientContainer.size(); i++) {
			System.out.println("HEre is size" + patientContainer.size());
			PatientVO patientItem = patientContainer.getIdByIndex(i);

			CustomEvent event = new CustomEvent();
			event.setCaption(patientItem.getFirstName());
			event.setDescription(patientItem.getLastName());

			GregorianCalendar gregCal = new GregorianCalendar();
			gregCal.setTime(patientItem.getNextAppointment());

			Date startTime = gregCal.getTime();
			event.setStart(startTime);

			gregCal.add(Calendar.HOUR, 1);

			Date endTime = gregCal.getTime();
			event.setEnd(endTime);

			eventList.add(event);
		}
	}

	@Override
	public List<CalendarEvent> getEvents(Date startDate, Date endDate) {
		// TODO Auto-generated method stub
		return eventList;
	}

}
