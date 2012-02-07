package com.imedical.Scheduler.data.calendar;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.imedical.Scheduler.data.PatientVO;
import com.vaadin.addon.calendar.event.CalendarEventEditor;
import com.vaadin.addon.calendar.event.CalendarEvent.EventChangeNotifier;

public class AppointmentEvent implements CalendarEventEditor,
		EventChangeNotifier {

	private static final long serialVersionUID = -7057468551667781922L;

	private Date start;
	private Date end;
	private String caption;
	private String appointmentReason;
	private String styleName;
	private boolean isAllDay;
	private List<EventChangeListener> listeners = new ArrayList<EventChangeListener>();
	private PatientVO patientVO;
	private String provider_id;

	public Date getStart() {
		return start;
	}

	public Date getEnd() {
		return end;
	}

	public String getCaption() {
		return caption;
	}

	public String getDescription() {
		return appointmentReason;
	}

	public String getStyleName() {
		return styleName;
	}

	public boolean isAllDay() {
		return isAllDay;
	}

	public void setCaption(String caption) {
		this.caption = caption;
		fireEventChange();

	}

	public void setDescription(String description) {
		this.appointmentReason = description;
		fireEventChange();
	}

	public void setEnd(Date end) {
		this.end = end;
		fireEventChange();
	}

	public void setStart(Date start) {
		this.start = start;
		fireEventChange();
	}

	public void setStyleName(String styleName) {
		this.styleName = styleName;
		fireEventChange();
	}

	public void setAllDay(boolean isAllDay) {
		this.isAllDay = isAllDay;
		fireEventChange();
	}

	public void addListener(EventChangeListener listener) {
		listeners.add(listener);
	}

	public void removeListener(EventChangeListener listener) {
		listeners.remove(listener);
	}

	protected void fireEventChange() {
		EventChange event = new EventChange(this);

		for (EventChangeListener listener : listeners) {
			listener.eventChange(event);
		}
	}

	public PatientVO getPatientVO() {
		return patientVO;
	}

	public void setPatientVO(PatientVO patientVO) {
		this.patientVO = patientVO;
	}

}
