package com.imedical.Scheduler.data;

import com.imedical.Scheduler.mobilePages.PatientTab;
import com.vaadin.addon.touchkit.ui.NavigationManager;
import com.vaadin.data.Property;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.Property.ValueChangeListener;
import com.vaadin.ui.Button;
import com.vaadin.ui.Table;

public class PatientTable extends NavigationManager {
	private static final long serialVersionUID = -9167659024150479681L;
	private Table table = new Table();
	private PatientContainer patientContainer = new PatientContainer();
	private NavigationManager navigationManger;
	private PatientTable patientTable;
	private PatientTab tabInstance;

	public PatientTable(PatientTab instance) {
		if (patientTable == null) {
			patientTable = this;
			tabInstance = instance;
			// Add these columns as temporary - Replace with container

			getTable().addContainerProperty("First Name", String.class, "");
			table.addContainerProperty("Last Name", String.class, "");

			table.setSelectable(true);
			table.setSizeFull();
			table.setImmediate(true);
			table.setContainerDataSource(patientContainer.loadInitialData());
			table.setVisibleColumns(PatientContainer.NATUAL_COL_ORDER);
			table.setColumnHeaders(PatientContainer.COL_HEADERS_ENGLISH);
		}
	}

	public void addTableSelectListener(Property.ValueChangeListener listener) {
		table.addListener(listener);
	}

	public PatientTable getPatientTableInstance() {
		return patientTable;
	}

	public Table getTable() {
		return table;
	}
}
