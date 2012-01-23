package com.imedical.Scheduler.mobilePages;

import java.text.DateFormat;

import com.imedical.Scheduler.data.PatientForm;
import com.imedical.Scheduler.data.PatientTable;
import com.vaadin.addon.touchkit.ui.NavigationManager;
import com.vaadin.addon.touchkit.ui.NavigationView;
import com.vaadin.addon.touchkit.ui.TabBarView;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Table;

public class PatientTab extends NavigationManager implements ClickListener {

	private static final long serialVersionUID = -3244374618085945750L;

	private Button addPatient = new Button("New Patient", this);

	private PatientTable patientTable = new PatientTable();

	// private NavigationManager navigationManager = new NavigationManager(
	// this.getParent());

	public PatientTab() {
		buildView();
	}

	private void buildView() {
		patientTable.setInstance(this);
		navigateTo(patientTable.getTable());

	}

	public void navigateToDetailView() {
		// navigationManager.navigateTo(new PatientDetailView());

	}

	public PatientTab getInstance() {
		return this;
	}

	@Override
	public void buttonClick(ClickEvent event) {
		// TODO Auto-generated method stub

	}

}
