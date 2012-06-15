package com.imedical.Scheduler.component.calendar;

import java.util.ArrayList;
import java.util.List;

import com.imedical.Scheduler.data.IProviderPatientDAO;
import com.imedical.Scheduler.data.ProviderPatientDAO;
import com.imedical.Scheduler.data.calendar.AppointmentEvent;
import com.imedical.common.SchedulerErrorCodes;
import com.imedical.common.SchedulerException;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.ui.Panel;

/**
 * 
 * @author Clay
 * 
 *         Factory to create the EventPanels. EventPanels will be objects that
 *         represent an event that is being displayed to the provider. Every
 *         appointment/event will have an event panel created for it.
 *         <hr/>
 *         <b>Panel information</b>
 *         <ol>
 *         <li>Event panel constructor will be passed in an AppointmentEvent
 *         object.</li>
 *         <li>The event panel object (EPO) will create an event panel with the
 *         following information:</li>
 *         <ul>
 *         <li>Event description</li>
 *         <li>Event start and end time</li>
 *         <li>Provider associated with the event</li>
 *         <li>Event location</li>
 *         </ul>
 * 
 *         </ol>
 *         <hr/>
 *         <b>Special functions</b>
 *         <ol>
 *         <li>EPO will be given a color based on priority/functio</li>
 *         <ul>
 *         <li>Red - Very important</li>
 *         <li>Yellow - needs attention</li>
 *         <li>Grey - normal event</li>
 *         </ul>
 *         </ol>
 * 
 * 
 */
public class EventPanelFactory {

	private static final long serialVersionUID = 3748940880483857297L;
	private BeanItemContainer<AppointmentEvent> appointments;
	private List<EventPanel> populatedPanels = new ArrayList<EventPanel>();

	/**
	 * Default constructor. Must be created with appointments to populate
	 * component with;
	 * 
	 * @param appointments
	 * @throws SchedulerException
	 */
	public EventPanelFactory(BeanItemContainer<AppointmentEvent> appointments)
			throws SchedulerException {
		if (appointments != null) {
			this.appointments = appointments;
			createEventPanels();
		} else {
			throw new SchedulerException(SchedulerErrorCodes.APPOINTMENTS_ERROR);
		}
	}

	/**
	 * Create an event panel object for every appointment
	 */
	private void createEventPanels() {
		for (AppointmentEvent appointment : appointments.getItemIds()) {
			EventPanel eventPanel = new EventPanel(appointment);
			populatedPanels.add(eventPanel.getPanel());
		}
	}
	
	public EventPanel createNewEventPanel(AppointmentEvent event){
		EventPanel eventPanel = new EventPanel(event);
		
		return eventPanel;
	}

	/**
	 * Return the created event panel objects
	 * 
	 * @return
	 */
	public List<EventPanel> getPanels() {
		return populatedPanels;

	}

}
