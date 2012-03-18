package com.imedical.Scheduler.data;

import java.util.ArrayList;
import java.util.List;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamImplicit;

@XStreamAlias("patients")
public class PatientVOList {
	@XStreamImplicit
	private List<PatientVO> patients = new ArrayList<PatientVO>();

	public void addPatient(PatientVO f) {
		patients.add(f);
	}

	public List<PatientVO> getPatients() {
		return patients;
	}
}