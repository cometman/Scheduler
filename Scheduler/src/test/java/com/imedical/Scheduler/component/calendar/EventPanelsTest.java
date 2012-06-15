package com.imedical.Scheduler.component.calendar;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.imedical.Scheduler.data.PatientVO;
import com.imedical.Scheduler.data.calendar.AppointmentEvent;
import com.imedical.common.SchedulerException;

public class EventPanelsTest {

	EventPanelFactory eventPanelFactory;
	List<AppointmentEvent> events;
	AppointmentEvent testEvent;
	PatientVO testPatient;

	@Before
	public void setupEventObjects() throws SchedulerException {
		testPatient = new PatientVO();
		testPatient.setAge(22);
		testPatient.setFirstName("Clay");
		testPatient.setLastName("Selby");
		testPatient.setPhoneNumber("3256697707");

		testEvent = new AppointmentEvent();
		testEvent.setDescription("A simple checkup");
		testEvent.setStart(new Date());
		testEvent.setEnd(new Date());
		testEvent.setCaption("Clay's checkup");
		testEvent.setPatientVO(testPatient);

		events = new ArrayList<AppointmentEvent>();
		events.add(testEvent);
//		eventPanelFactory = new EventPanelFactory(events);

	}

	@Test
	public void canConstructEmptyEventPanelObject() {

		assertNotNull(eventPanelFactory);
	}

	@Test
	public void canRetrieveMultipleEmptykEventPanelObjects() {
		assertTrue(eventPanelFactory.getPanels().size() > 0);
	}

	@Test
	public void canPopulateEventPanelObject() {

	}

}
