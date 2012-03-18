package com.imedical.Scheduler.data.calendar;

import java.util.Calendar;
import java.util.GregorianCalendar;

import com.imedical.Scheduler.MyVaadinApplication;
import com.imedical.Scheduler.mobilePages.EventPopover;
import com.imedical.model.ProviderModel;
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
	private EventPopover eventPopover;

	public CalendarPanel() {
		VerticalLayout verticalLayout = new VerticalLayout();
		verticalLayout.setSizeFull();
		HorizontalLayout horizontalLayout = new HorizontalLayout();

		if (mainCalendar == null) {
			buildMainCalendar();
		}
		verticalLayout.addComponent(mainCalendar);
		addComponent(verticalLayout);
		addComponent(horizontalLayout);

		horizontalLayout.addComponent(weekView);
		horizontalLayout.addComponent(dayView);
		horizontalLayout.setComponentAlignment(weekView, Alignment.BOTTOM_LEFT);
		horizontalLayout.setComponentAlignment(dayView, Alignment.BOTTOM_RIGHT);
		verticalLayout.setComponentAlignment(mainCalendar,
				Alignment.MIDDLE_CENTER);

		mainCalendar.setHandler(new EventClickHandler() {

			@Override
			public void eventClick(EventClick event) {
				ProviderModel prop = (ProviderModel) MyVaadinApplication.get()
						.getUser();
				CalEventProvider calEventProvider = mainCalendar
						.returnEventProvider();
				AppointmentEvent appointmentEvent = calEventProvider
						.getAppointmentByStartAndCaption(event
								.getCalendarEvent());
				eventPopover = new EventPopover(appointmentEvent);
				getWindow().addWindow(eventPopover);

			}
		});

	}

	private MainCalendar buildMainCalendar() {
		mainCalendar = new MainCalendar();

		mainCalendar.setVisibleHoursOfDay(8, 17);

		mainCalendar.setSizeFull();
		mainCalendar.setStartDate(gregCalendar.getTime());
		mainCalendar.setEndDate(gregCalendar.getTime());
		// Add the event listeners to the calendar here!
		// mainCalendar.setHandler(new EventClickHandler() {
		//
		// public void eventClick(EventClick event) {
		// getWindow().addWindow(createPopover(event.getCalendarEvent()));
		//
		// }
		// });

		return mainCalendar;
	}

	public MainCalendar getMainCalendar() {
		return mainCalendar;
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
		return eventPopover;
	}

}
