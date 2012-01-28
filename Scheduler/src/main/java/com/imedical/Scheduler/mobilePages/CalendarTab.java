package com.imedical.Scheduler.mobilePages;

import com.imedical.Scheduler.data.calendar.CalendarPanel;
import com.vaadin.addon.touchkit.ui.NavigationView;

public class CalendarTab extends NavigationView {
	private CalendarPanel panel;
	public CalendarTab() {
		if (panel == null){
			panel = new CalendarPanel();
		}
		setContent(panel);
		this.setCaption("Calendar");
		

	}

}
