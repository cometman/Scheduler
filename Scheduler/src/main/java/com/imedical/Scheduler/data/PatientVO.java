package com.imedical.Scheduler.data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.imedical.Scheduler.data.calendar.AppointmentEvent;

public class PatientVO {
	private int uniqueId;
	private String firstName;
	private String lastName;
	private String middleName;
	private int age;
	private String phoneNumber;
	private String referral;
	private String paymentType;
	// private List<Note> patientNotes = new ArrayList<Note>();
	private String email;
	private List<AppointmentEvent> appointments = new ArrayList<AppointmentEvent>();

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String name) {
		this.firstName = name;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String last_name) {
		this.lastName = last_name;
	}

	public String getMiddleName() {
		return middleName;
	}

	public void setMiddleName(String middle_name) {
		this.middleName = middle_name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int i) {
		this.age = i;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getReferral() {
		return referral;
	}

	public void setReferral(String referral) {
		this.referral = referral;
	}

	public String getPaymentType() {
		return paymentType;
	}

	public void setPaymentType(String paymentType) {
		this.paymentType = paymentType;
	}

	// public List<Note> getPatientNotes() {
	// return patientNotes;
	// }
	//
	// public void setPatientNotes(List<Note> patientNotes) {
	// this.patientNotes = patientNotes;
	// }

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getUniqueId() {
		return uniqueId;
	}

	public void setUniqueId(int uniqueId) {
		this.uniqueId = uniqueId;
	}

	public List<AppointmentEvent> getAppointments() {
		return appointments;
	}

	public void setAppointments(List<AppointmentEvent> appointments) {
		this.appointments = appointments;
	}

	public void addAppointment(AppointmentEvent appointment) {
		this.appointments.add(appointment);
	}

	public AppointmentEvent getNextAppointmentEvent() {
		AppointmentEvent event = null;
		Date todaysDate = new Date();

		for (AppointmentEvent e : getAppointments()) {
			if (e.getStart().after(todaysDate)) {
				if (event == null) {
					event = e;
				} else if (e.getStart().before(event.getStart())) {
					event = e;
				}
			}
		}
		return event;
	}

}
