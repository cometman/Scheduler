package com.imedical.Scheduler.data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.vaadin.data.util.BeanItemContainer;

public class PatientContainer extends BeanItemContainer<PatientVO> implements
		Serializable {

	private static final long serialVersionUID = 7669757062603387633L;
	private static IPatientDAO patientDAO = null;
	private static List<PatientVO> patients = new ArrayList<PatientVO>();
	private PatientContainer patientContainer;
	public static final Object[] NATUAL_COL_ORDER = new Object[] { "firstName",
			"lastName", "phoneNumber", "nextAppointment" };
	public static final String[] COL_HEADERS_ENGLISH = new String[] {
			"First name", "Last name", "Phone", "Next appt." };

	public PatientContainer() throws IllegalArgumentException {
		super(PatientVO.class);
	}

	public PatientContainer loadData() {
		if (patientDAO == null) {
			patientDAO = new PatientDAO();
			patients = patientDAO.getAllPatients();
		}

		if (patientContainer == null) {
			patientContainer = new PatientContainer();
			for (PatientVO p : patients) {
				patientContainer.addItem(p);
			}
		}

		return patientContainer;
	}

}
