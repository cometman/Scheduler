package com.imedical.Scheduler.data.calendar;

import java.text.DateFormat;
import java.text.DateFormat.Field;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Panel;

public class CalendarPanel extends Panel implements ClickListener {
	private MainCalendar mainCalendar;
	private Button weekView = new Button("Week", this);
	private Button dayView = new Button("Day", this);
	private GregorianCalendar gregCalendar = new GregorianCalendar();

	public CalendarPanel() {
		addComponent(weekView);
		addComponent(dayView);
		if (mainCalendar == null) {
			buildMainCalendar();

		}
		addComponent(mainCalendar);

	}

	private MainCalendar buildMainCalendar() {
		mainCalendar = new MainCalendar();
		mainCalendar.setSizeFull();
		return mainCalendar;
	}

	private void updateCalendar() {

	}

	@Override
	public void buttonClick(ClickEvent event) {
		if (event.getButton().equals(weekView)) {
			gregCalendar.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
			mainCalendar.setStartDate(gregCalendar.getTime());
			gregCalendar.set(Calendar.DAY_OF_WEEK, Calendar.SATURDAY);
			mainCalendar.setEndDate(gregCalendar.getTime());

			mainCalendar.setEndDate(gregCalendar.getTime());

		} else if (event.getButton().equals(dayView)) {
			mainCalendar.setStartDate(gregCalendar.getTime());
			mainCalendar.setEndDate(gregCalendar.getTime());
		}
		// updateCalendar();

	}
}
