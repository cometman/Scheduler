package com.imedical.Scheduler.data.calendar;

import java.util.List;

import com.imedical.Scheduler.MyVaadinApplication;
import com.imedical.Scheduler.component.calendar.EventPanel;
import com.imedical.Scheduler.component.calendar.EventPanelFactory;
import com.imedical.Scheduler.data.IProviderPatientDAO;
import com.imedical.Scheduler.data.PatientDAOFactory;
import com.imedical.Scheduler.mobilePages.AppointmentView;
import com.imedical.Scheduler.mobilePages.CalendarTab;
import com.imedical.common.SchedulerException;
import com.imedical.model.ProviderModel;
import com.vaadin.addon.touchkit.ui.NavigationView;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.event.LayoutEvents.LayoutClickEvent;
import com.vaadin.event.LayoutEvents.LayoutClickListener;
import com.vaadin.event.MouseEvents.ClickEvent;
import com.vaadin.event.MouseEvents.ClickListener;
import com.vaadin.ui.VerticalLayout;

public class CalendarPanel extends NavigationView {

	private static final long serialVersionUID = 1L;
	private IProviderPatientDAO pdao = PatientDAOFactory.getInstance()
			.getImplementation();
	private ProviderModel providerModel;
	private EventPanelFactory panelFactory;
	private VerticalLayout verticalLayout = new VerticalLayout();
	private List<AppointmentEvent> appointmentEvents;
	private CalendarTab calTab;

	// Container for the appointment objects to be added to the screen
	private BeanItemContainer<AppointmentEvent> appointments = new BeanItemContainer<AppointmentEvent>(
			AppointmentEvent.class);

	public CalendarPanel() {
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
		verticalLayout.setStyleName("calendar-vertical-layout");
		setContent(verticalLayout);

	}

	/**
	 * Create the appointment objects and add them to the panel
	 * 
	 * @throws SchedulerException
	 */
	public void addEvents() throws SchedulerException {

		// Add the appointments we want to show to the container
		for (AppointmentEvent appointment : appointmentEvents) {
			appointments.addBean(appointment);
		}

		// Instantiate the panel factory
		panelFactory = new EventPanelFactory(appointments);

		// Add the objects to the screen
		for (final EventPanel panel : panelFactory.getPanels()) {

			// Create the click listener for the appointments
			ClickListener cl = new ClickListener() {
				private static final long serialVersionUID = 1L;

				@Override
				public void click(ClickEvent event) {
					try {
						AppointmentView view = new AppointmentView(
								panel.getEvent(), panel.getEvent()
										.getPatientVO());
						calTab.navigateToAppointmentView(view);
					} catch (SchedulerException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

				}
			};
			panel.addListener(cl);
			panel.setStyleName("cal-appointment-button");
			verticalLayout.addComponent(panel);
		}
	}

	public void addNewEvent(AppointmentEvent event) {
		appointmentEvents.add(event);
		try {
			addEvents();
		} catch (SchedulerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void setTabReference(CalendarTab calTab) {
		this.calTab = calTab;
	}

}
// Fetch the provider model

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