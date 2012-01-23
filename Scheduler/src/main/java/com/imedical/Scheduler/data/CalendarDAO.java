package com.imedical.Scheduler.data;

import java.util.ArrayList;
import java.util.List;

import com.vaadin.addon.calendar.event.CalendarEvent;

public class CalendarDAO implements ICalendarDAO {
	List<CalendarEvent> eventList = new ArrayList<CalendarEvent>();

	public List<CalendarEvent> getAllCalendarEvents(String userid) {

		return eventList;
	}

}
