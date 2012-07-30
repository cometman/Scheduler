package com.imedical.Scheduler.mobilePages;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import com.imedical.Scheduler.MyVaadinApplication;
import com.imedical.Scheduler.data.calendar.CalendarPanel;
import com.imedical.model.ProviderModel;
import com.vaadin.addon.touchkit.ui.NavigationManager;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;

public class CalendarTab extends NavigationManager implements ClickListener {
	private static final long serialVersionUID = -3283231409443456050L;
	private SimpleDateFormat dateFormat = new SimpleDateFormat(
			"EEEE MMM dd yyyy");
	private String dayCaption;
	private ProviderModel model;
	private Calendar currentView;

	public CalendarTab(CalendarPanel panel) {

		currentView = Calendar.getInstance();
		model = (ProviderModel) MyVaadinApplication.get().getUser();
		panel.buildNavigationBar();
		navigateTo(panel);
		panel.setTabReference(this);
		setImmediate(true);
	}

	@Override
	public void buttonClick(ClickEvent event) {

	}

	
	/**
	 * Navigate to the details of this appointment
	 * 
	 * @param view
	 *            The view to navigate to
	 */
	public void navigateToAppointmentView(AppointmentView view) {
		navigateTo(view);
	}

	/**
	 * Used for navigating to the previous days under the Calendar tab.
	 * 
	 * @param view
	 */
	public void navigateBackwardCalendarView(CalendarPanel view) {
		this.setPreviousComponent(view);
		navigateBack();
	}

	/**
	 * Navigate to the next days under the calendar tab
	 * 
	 * @param view
	 */
	public void navigateForwardCalendarView(CalendarPanel view) {
		navigateTo(view);
	}
}
