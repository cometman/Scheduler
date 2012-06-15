package com.imedical.Scheduler.component.calendar;

import com.imedical.Scheduler.MyVaadinApplication;
import com.imedical.Scheduler.data.calendar.AppointmentEvent;
import com.vaadin.terminal.ThemeResource;
import com.vaadin.ui.Label;
import com.vaadin.ui.Panel;

public class EventPanel extends Panel {
	// Event to populate panel with
	private AppointmentEvent event = new AppointmentEvent();
	// The panel we are creating
	private EventPanel panelObject;
	private Label titleLabel;

	public EventPanel(AppointmentEvent event) {
		// Make sure we are working with a new panel object
		panelObject = null;
		// If the event is not null, create the panel object
		if (event != null) {
			this.event = event;
			panelObject = this;
			createPanelBaseTemplate();
			addPatientData();
		}
	}

	/**
	 * The base characteristics of the panel (size, shape, etc)
	 */
	private void createPanelBaseTemplate() {
		panelObject.setWidth("100%");
		panelObject.setHeight("20%");
		panelObject.setStyleName("eventpanel");

	}

	/**
	 * Add the patient information to the panel
	 */
	private void addPatientData() {

		titleLabel = new Label(event.getCaption());
		panelObject.addComponent(titleLabel);

	}

	public EventPanel getPanel() {
		return panelObject;
	}

}
