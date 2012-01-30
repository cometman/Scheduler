package com.imedical.Scheduler.mobilePages;

import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;

import com.imedical.Scheduler.data.calendar.CalendarPanel;
import com.vaadin.addon.calendar.ui.CalendarComponentEvents.EventClick;
import com.vaadin.addon.calendar.ui.CalendarComponentEvents.EventClickHandler;
import com.vaadin.addon.touchkit.ui.NavigationView;
import com.vaadin.addon.touchkit.ui.Popover;

public class CalendarTab extends NavigationView {
	private SimpleDateFormat dateFormat = new SimpleDateFormat(
			"EEEE MMM dd yyyy");
	private GregorianCalendar gregCal = new GregorianCalendar();
	private String caption;
	private CalendarPanel panel;

	public CalendarTab() {
		if (panel == null) {
			panel = new CalendarPanel();
		}
		caption = dateFormat.format(gregCal.getTime());
		setContent(panel);
		this.setCaption(caption);



	}

}
