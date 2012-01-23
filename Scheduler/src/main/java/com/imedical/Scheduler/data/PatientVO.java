package com.imedical.Scheduler.data;

import java.util.Date;

public class PatientVO {
	private int uniqueId;
	private String firstName;
	private String lastName;
	private String middleName;
	private int age;
	private String phoneNumber;
	private String reasonForVisit;
	private String referral;
	private String paymentType;
	// private List<Note> patientNotes = new ArrayList<Note>();
	private Date nextAppointment;
	private String email;

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

	public String getReasonForVisit() {
		return reasonForVisit;
	}

	public void setReasonForVisit(String reasonForVisit) {
		this.reasonForVisit = reasonForVisit;
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

	public Date getNextAppointment() {
		return nextAppointment;
	}

	public void setNextAppointment(Date nextAppointment) {
		this.nextAppointment = nextAppointment;
	}

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

}
