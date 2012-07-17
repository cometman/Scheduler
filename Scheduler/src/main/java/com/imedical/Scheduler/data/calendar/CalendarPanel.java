package com.imedical.Scheduler.data.calendar;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
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
import com.vaadin.event.MouseEvents.ClickEvent;
import com.vaadin.event.MouseEvents.ClickListener;
import com.vaadin.ui.Button;
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
	private CalendarPanel previousView;
	private CalendarPanel nextView;
	private Calendar timeKeeper;
//	private SimpleDateFormat dateFormat = new SimpleDateFormat("EEEE MMM dd yyyy");
	private SimpleDateFormat dateFormat = new SimpleDateFormat("EEEE d");
	
	// Container for the appointment objects to be added to the screen
	private BeanItemContainer<AppointmentEvent> appointments = new BeanItemContainer<AppointmentEvent>(
			AppointmentEvent.class);
	
	// Date suffixes
	 static String[] suffixes =
			  //    0     1     2     3     4     5     6     7     8     9
			     { "th", "st", "nd", "rd", "th", "th", "th", "th", "th", "th",
			  //    10    11    12    13    14    15    16    17    18    19
			       "th", "th", "th", "th", "th", "th", "th", "th", "th", "th",
			  //    20    21    22    23    24    25    26    27    28    29
			       "th", "st", "nd", "rd", "th", "th", "th", "th", "th", "th",
			  //    30    31
			       "th", "st" };


	public CalendarPanel(Date dayToView) {
		timeKeeper = Calendar.getInstance();
		timeKeeper.setTime(dayToView);

		setImmediate(true);
		appointmentEvents = pdao.getAppointmentsOnDate(timeKeeper.getTime());
		providerModel = (ProviderModel) MyVaadinApplication.get().getUser();
		try {
			addEvents();
		} catch (SchedulerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		verticalLayout.setSizeFull();
		verticalLayout.setStyleName("calendar-vertical-layout");
		verticalLayout.setSizeUndefined();
		verticalLayout.setWidth("100%");
		setContent(verticalLayout);
	
//		this.setScrollPosition(scrollPosition)
//		buildNavigationBar();
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

	public void buildNavigationBar() {
		
		Button previousDay = new Button("Back");
		Button nextDay = new Button("Next");
		this.setLeftComponent(previousDay);
		this.setRightComponent(nextDay);
		
		int day = timeKeeper.get(Calendar.DAY_OF_MONTH);
		this.setCaption(dateFormat.format(timeKeeper.getTime())+suffixes[day]);
		// this.getLeftComponent().setCaption("Previous");
		// this.getRightComponent().setCaption("Next");

		previousDay.addListener(new Button.ClickListener() {

			private static final long serialVersionUID = -8806631534302680882L;

			@Override
			public void buttonClick(Button.ClickEvent event) {

				timeKeeper.set(Calendar.DAY_OF_MONTH,
						timeKeeper.get(Calendar.DAY_OF_MONTH) - 1);
				calTab.navigateBackwardCalendarView(createPreviousView());
			}
		});

		nextDay.addListener(new Button.ClickListener() {
			private static final long serialVersionUID = -1925434088605606965L;

			@Override
			public void buttonClick(Button.ClickEvent event) {
				// Subtract one day from the current time
				timeKeeper.set(Calendar.DAY_OF_MONTH,
						timeKeeper.get(Calendar.DAY_OF_MONTH) + 1);
				calTab.navigateForwardCalendarView(createNextView());

			}
		});
	}

	public CalendarPanel createPreviousView() {

		// Set the calendar instance equal to the currentDate object
		// calInstance.setTime(currentDate);

		// Subtract one day from the current time

		// Update the current date reference
		// currentDate = calInstance.getTime();

		CalendarPanel calPanel = new CalendarPanel(timeKeeper.getTime());
		String dayCaption = dateFormat.format(timeKeeper.getTime());
		
		int day = timeKeeper.get(Calendar.DAY_OF_MONTH);
		
		calPanel.buildNavigationBar();

		calPanel.setCaption(dateFormat.format(timeKeeper.getTime())+suffixes[day]);

		// Reference for the CalendarTab so that we can access the nav manager
		calPanel.setTabReference(calTab);

		// Create the new CalendarPanel object

		return calPanel;

	}

	public CalendarPanel getPreviousView() {

		previousView = createPreviousView();

		return previousView;
	}

	public CalendarPanel getNextView() {

		nextView = createNextView();

		return nextView;
	}

	public CalendarPanel createNextView() {
		// Calendar calInstance = Calendar.getInstance();

		// Set the calendar instance equal to the currentDate object
		// calInstance.setTime(currentDate);

		// Update the current date reference
		// currentDate = calInstance.getTime();

		CalendarPanel calPanel = new CalendarPanel(timeKeeper.getTime());
		
		int day = timeKeeper.get(Calendar.DAY_OF_MONTH);
		calPanel.setCaption(dateFormat.format(timeKeeper.getTime())+suffixes[day]);
		
		calPanel.buildNavigationBar();
		// Reference for the CalendarTab so that we can access the nav manager
		calPanel.setTabReference(calTab);

		// Create the new CalendarPanel object
	
		return calPanel;
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