package com.imedical.Scheduler.component.calendar;

import com.imedical.Scheduler.data.calendar.AppointmentEvent;
import com.vaadin.ui.Label;
import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;

public class EventPanel extends Panel {
	// Event to populate panel with
	private AppointmentEvent event = new AppointmentEvent();
	// The panel we are creating
	private EventPanel panelObject;

	public EventPanel(AppointmentEvent event) {
		// Make sure we are working with a new panel object
		panelObject = null;
		// If the event is not null, create the panel object
		if (event != null) {
			this.event = event;
			panelObject = this;
			addPatientData();
		}
	}

	/**
	 * Add the patient information to the panel
	 */
	private void addPatientData() {

		VerticalLayout horzLayout = new VerticalLayout();
		Label titleLabel = new Label(event.getCaption());
		Label timeLabel = new Label(event.getStart().toString());
		horzLayout.addComponent(timeLabel);
		horzLayout.addComponent(titleLabel);
		panelObject.addComponent(horzLayout);
//		horzLayout.setStyleName("calendar-horiz-layout");

	}

	public EventPanel getPanel() {
		return panelObject;
	}

	public AppointmentEvent getEvent() {
		return event;
	}

}
