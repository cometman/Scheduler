package com.imedical.Scheduler.mobilePages;

import com.imedical.Scheduler.data.PatientDetailPanel;
import com.imedical.Scheduler.data.PatientForm;
import com.imedical.Scheduler.data.PatientTable;
import com.imedical.Scheduler.data.PatientVO;
import com.vaadin.addon.touchkit.ui.NavigationManager;
import com.vaadin.addon.touchkit.ui.NavigationView;
import com.vaadin.ui.Button;
import com.vaadin.ui.Form;
import com.vaadin.ui.Panel;

public class PatientDetailView extends NavigationView {
	private static final long serialVersionUID = -6881253381664450543L;

	private PatientDetailPanel patientPanel = null;

	public PatientDetailView(PatientVO patient) {

		patientPanel = new PatientDetailPanel(patient);

		setContent(patientPanel);

	}
}
