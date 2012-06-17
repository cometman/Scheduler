package com.imedical.Scheduler.mobilePages;

import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;

import com.imedical.Scheduler.MyVaadinApplication;
import com.imedical.Scheduler.data.calendar.CalendarPanel;
import com.imedical.model.ProviderModel;
import com.vaadin.addon.touchkit.ui.NavigationManager;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;

public class CalendarTab extends NavigationManager implements ClickListener {
	private SimpleDateFormat dateFormat = new SimpleDateFormat(
			"EEEE MMM dd yyyy");
	private GregorianCalendar gregCal = new GregorianCalendar();
	private String caption;
	// private CalendarPanel panel;
	private ProviderModel model;

	public CalendarTab(CalendarPanel panel) {

		panel = new CalendarPanel();

		model = (ProviderModel) MyVaadinApplication.get().getUser();

		caption = dateFormat.format(gregCal.getTime());
		panel.setTabReference(this);
		navigateTo(panel);

		panel.setCaption(caption);
		setImmediate(true);

	}

	@Override
	public void buttonClick(ClickEvent event) {

	}

	/**
	 * Navigate to the details of this appointment
	 * @param view
	 * The view to navigate to
	 */
	public void navigateToAppointmentView(AppointmentView view) {
		navigateTo(view);
	}

}
