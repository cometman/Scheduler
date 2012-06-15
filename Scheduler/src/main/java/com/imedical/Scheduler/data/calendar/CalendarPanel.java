package com.imedical.Scheduler.data.calendar;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import com.imedical.Scheduler.MyVaadinApplication;
import com.imedical.Scheduler.component.calendar.EventPanel;
import com.imedical.Scheduler.component.calendar.EventPanelFactory;
import com.imedical.Scheduler.data.IProviderPatientDAO;
import com.imedical.Scheduler.data.PatientDAOFactory;
import com.imedical.Scheduler.data.ProviderPatientDAO;
import com.imedical.Scheduler.mobilePages.EventPopover;
import com.imedical.common.SchedulerException;
import com.imedical.model.ProviderModel;
import com.sun.org.apache.bcel.internal.generic.NEW;
import com.vaadin.addon.calendar.event.CalendarEvent;
import com.vaadin.addon.calendar.ui.CalendarComponentEvents.EventClick;
import com.vaadin.addon.calendar.ui.CalendarComponentEvents.EventClickHandler;
import com.vaadin.addon.touchkit.ui.Popover;
import com.vaadin.data.util.BeanItem;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;

public class CalendarPanel extends Panel {// implements ClickListener {
	private MainCalendar mainCalendar;
	// private Button weekView = new Button("Week", this);
	// private Button dayView = new Button("Day", this);
	private GregorianCalendar gregCalendar = new GregorianCalendar();
	private EventPopover eventPopover;
	private IProviderPatientDAO pdao = PatientDAOFactory.getInstance().getImplementation();
	private ProviderModel providerModel;
	private EventPanelFactory panelFactory;
	private VerticalLayout verticalLayout = new VerticalLayout();
	private List<AppointmentEvent> appointmentEvents;

	// Container for the appointment objects to be added to the screen
	private BeanItemContainer<AppointmentEvent> appointments = new BeanItemContainer<AppointmentEvent>(
			AppointmentEvent.class);

	public CalendarPanel(){
		setImmediate(true);
		appointmentEvents = pdao.getAppointments();
		providerModel = (ProviderModel) MyVaadinApplication.get().getUser();
		try {
			addEvents();
		} catch (SchedulerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		verticalLayout.setSizeFull();
		addComponent(verticalLayout);
		
	}
	
	public void addEvents() throws SchedulerException{

		
		// Add the appointments we want to show to the container
		for (AppointmentEvent appointment : appointmentEvents) {
			appointments.addBean(appointment);
		}

		// Instantiate the panel factory
		panelFactory = new EventPanelFactory(appointments);

		// Add the objects to the screen

		

		

		for (EventPanel panel : panelFactory.getPanels()) {
			verticalLayout.addComponent(panel);
		}
	}
	
	public void addNewEvent(AppointmentEvent event){
		appointmentEvents.add(event);
		try {
			addEvents();
		} catch (SchedulerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
//Fetch the provider model


		// VerticalLayout verticalLayout = new VerticalLayout();
		// verticalLayout.setSizeFull();
		// HorizontalLayout horizontalLayout = new HorizontalLayout();
		//
		// if (mainCalendar == null) {
		// buildMainCalendar();
		// }
		// verticalLayout.addComponent(mainCalendar);
		// addComponent(verticalLayout);
		// addComponent(horizontalLayout);
		//
		// horizontalLayout.addComponent(weekView);
		// horizontalLayout.addComponent(dayView);
		// horizontalLayout.setComponentAlignment(weekView,
		// Alignment.BOTTOM_LEFT);
		// horizontalLayout.setComponentAlignment(dayView,
		// Alignment.BOTTOM_RIGHT);
		// verticalLayout.setComponentAlignment(mainCalendar,
		// Alignment.MIDDLE_CENTER);
		//
		// mainCalendar.setHandler(new EventClickHandler() {
		//
		// @Override
		// public void eventClick(EventClick event) {
		// ProviderModel prop = (ProviderModel) MyVaadinApplication.get()
		// .getUser();
		// CalEventProvider calEventProvider = mainCalendar
		// .returnEventProvider();
		// AppointmentEvent appointmentEvent = calEventProvider
		// .getAppointmentByStartAndCaption(event
		// .getCalendarEvent());
		// eventPopover = new EventPopover(appointmentEvent);
		// getWindow().addWindow(eventPopover);
		//
		// }
		// });
		//
		// }
		//
		// private MainCalendar buildMainCalendar() {
		// mainCalendar = new MainCalendar();
		//
		// mainCalendar.setVisibleHoursOfDay(8, 17);
		//
		// mainCalendar.setSizeFull();
		// mainCalendar.setStartDate(gregCalendar.getTime());
		// mainCalendar.setEndDate(gregCalendar.getTime());
		// // Add the event listeners to the calendar here!
		// // mainCalendar.setHandler(new EventClickHandler() {
		// //
		// // public void eventClick(EventClick event) {
		// // getWindow().addWindow(createPopover(event.getCalendarEvent()));
		// //
		// // }
		// // });
		//
		// return mainCalendar;
		// }
		//
		// public MainCalendar getMainCalendar() {
		// return mainCalendar;
		// }
		//
		// @Override
		// public void buttonClick(ClickEvent event) {
		// if (event.getButton().equals(weekView)) {
		// gregCalendar.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
		// mainCalendar.setStartDate(gregCalendar.getTime());
		// gregCalendar.set(Calendar.DAY_OF_WEEK, Calendar.SATURDAY);
		// mainCalendar.setEndDate(gregCalendar.getTime());
		//
		// mainCalendar.setEndDate(gregCalendar.getTime());
		//
		// } else if (event.getButton().equals(dayView)) {
		// mainCalendar.setStartDate(gregCalendar.getTime());
		// mainCalendar.setEndDate(gregCalendar.getTime());
		// }
		// // updateCalendar();
		//
		// }
		//
		// public Popover createPopover(CalendarEvent event) {
		// Popover eventPopover = new Popover();
		// Panel popoverPanel = new Panel();
		// popoverPanel.setCaption(event.getCaption());
		// eventPopover.setContent(popoverPanel);
		// return eventPopover;
		// }