package com.imedical.Scheduler.data.calendar;

import java.text.DateFormat;
import java.text.DateFormat.Field;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import com.vaadin.addon.calendar.event.CalendarEvent;
import com.vaadin.addon.calendar.ui.CalendarComponentEvents.EventClick;
import com.vaadin.addon.calendar.ui.CalendarComponentEvents.EventClickHandler;
import com.vaadin.addon.touchkit.ui.Popover;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;

public class CalendarPanel extends Panel implements ClickListener {
	private MainCalendar mainCalendar;
	private Button weekView = new Button("Week", this);
	private Button dayView = new Button("Day", this);
	private GregorianCalendar gregCalendar = new GregorianCalendar();

	public CalendarPanel() {
		VerticalLayout verticalLayout = new VerticalLayout();
		verticalLayout.setSizeFull();
		HorizontalLayout horizontalLayout = new HorizontalLayout();
		horizontalLayout.addComponent(weekView);
		horizontalLayout.addComponent(dayView);
		if (mainCalendar == null) {
			buildMainCalendar();
		}
		verticalLayout.addComponent(mainCalendar);
		horizontalLayout.setComponentAlignment(weekView, Alignment.TOP_LEFT);
		horizontalLayout.setComponentAlignment(dayView, Alignment.TOP_RIGHT);
		verticalLayout.setComponentAlignment(mainCalendar,
				Alignment.MIDDLE_CENTER);
		addComponent(horizontalLayout);
		addComponent(verticalLayout);

	}

	private MainCalendar buildMainCalendar() {
		mainCalendar = new MainCalendar();

		mainCalendar.setVisibleHoursOfDay(8, 17);

		mainCalendar.setWidth("90%");
		mainCalendar.setHeight("100%");

		// Add the event listeners to the calendar here!
		mainCalendar.setHandler(new EventClickHandler() {

			public void eventClick(EventClick event) {
				getWindow().addWindow(createPopover(event.getCalendarEvent()));

			}
		});
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

	public Popover createPopover(CalendarEvent event) {
		Popover eventPopover = new Popover();
		Panel popoverPanel = new Panel();
		popoverPanel.setCaption(event.getCaption());
		eventPopover.setContent(popoverPanel);
		popoverPanel.setSt
		return eventPopover;
	}
}
