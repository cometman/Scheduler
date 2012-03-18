package com.imedical.Scheduler.data;

import java.util.List;

import com.vaadin.addon.calendar.event.CalendarEvent;

public interface ICalendarDAO {
	public List<CalendarEvent> getAllCalendarEvents(String userid);

}
