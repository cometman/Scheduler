package com.imedical.Scheduler.data;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.jetty.continuation.ContinuationFilter.FilteredContinuation;

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

	public PatientContainer loadInitialData() {
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

	/*
	 * Filter the search results based on the string
	 */
	public PatientContainer loadFilteredData(String s)
			throws IllegalAccessException, IllegalArgumentException,
			InvocationTargetException {
		PatientContainer filteredContainer = new PatientContainer();
		Method[] methods = PatientVO.class.getMethods();
		Method[] methodsToUse = new Method[methods.length];

		int counter = 0;
		/*
		 * Grab the methods from the PatientVO class that are of type String and
		 * are getters
		 */
		for (Method m : methods) {
			if (m.getReturnType().getName().contains("java.lang.String")) {
				if (m.getName().contains("get")) {
					methodsToUse[counter] = m;
					counter++;
				}
			}
		}
		String result = null;
		System.out.println("Size" + methodsToUse.length);
		for (PatientVO p : patients) {
			for (Method m : methodsToUse) {
				if (m != null) {
					if (m.invoke(p) == null) {
						System.out.println("null");
					} else {
						result = (String) m.invoke(p).toString().toLowerCase();

						if (result.contains(s)) {
							if (!filteredContainer.containsId(p)) {
								filteredContainer.addBean(p);
								System.out.println(p.getFirstName());
							}
						} else {

						}
					}
				}
			}
		}

		return filteredContainer;
	}
}
