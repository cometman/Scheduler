package com.imedical.Scheduler.data.calendar;

import java.util.Date;
import java.util.GregorianCalendar;

import com.imedical.Scheduler.MyVaadinApplication;
import com.vaadin.addon.calendar.ui.Calendar;
import com.vaadin.addon.calendar.ui.CalendarComponentEvents.EventClickHandler;
import com.vaadin.addon.calendar.ui.CalendarTargetDetails;
import com.vaadin.addon.calendar.ui.CalendarComponentEvents.EventClick;

import com.vaadin.event.dd.DragAndDropEvent;
import com.vaadin.event.dd.DropHandler;
import com.vaadin.event.dd.acceptcriteria.AcceptAll;
import com.vaadin.event.dd.acceptcriteria.AcceptCriterion;
import com.vaadin.terminal.gwt.client.ui.ClickEventHandler;
import com.vaadin.ui.Table.TableTransferable;

public class MainCalendar extends Calendar {

	private static final long serialVersionUID = 7876830444596538956L;
	private GregorianCalendar gregCalendar = new GregorianCalendar();
	private CalEventProvider eventProvider;

	public MainCalendar() {

		eventProvider = new CalEventProvider();

//		this.setEventProvider(eventProvider);
	}

	public Calendar createCalendar() {
		Calendar calendar = new Calendar();
		calendar.setDropHandler(new DropHandler() {

			/**
			 * 
			 */
			private static final long serialVersionUID = 9088838916704639613L;

			@Override
			public AcceptCriterion getAcceptCriterion() {
				return AcceptAll.get();
			}

			@Override
			public void drop(DragAndDropEvent event) {
				CalendarTargetDetails details = (CalendarTargetDetails) event
						.getTargetDetails();
				TableTransferable transferable = (TableTransferable) event
						.getTransferable();
			}

		});

		return calendar;
	}

	public CalEventProvider returnEventProvider() {
		return eventProvider;
	}

}
