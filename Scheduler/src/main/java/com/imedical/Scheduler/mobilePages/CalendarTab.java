package com.imedical.Scheduler.mobilePages;

import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;

import com.imedical.Scheduler.MyVaadinApplication;
import com.imedical.Scheduler.data.calendar.CalendarPanel;
import com.imedical.model.ProviderModel;
import com.vaadin.addon.calendar.ui.CalendarComponentEvents.EventClick;
import com.vaadin.addon.calendar.ui.CalendarComponentEvents.EventClickHandler;
import com.vaadin.addon.touchkit.ui.NavigationView;
import com.vaadin.addon.touchkit.ui.Popover;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Component.Listener;
import com.vaadin.ui.ComponentContainer.ComponentAttachEvent;

public class CalendarTab extends NavigationView implements ClickListener {
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

	@Override
	public void buttonClick(ClickEvent event) {
		

	}

}
