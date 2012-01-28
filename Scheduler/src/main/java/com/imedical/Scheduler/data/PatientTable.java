package com.imedical.Scheduler.data;

import com.imedical.Scheduler.mobilePages.PatientTab;
import com.vaadin.addon.touchkit.ui.NavigationManager;
import com.vaadin.data.Property;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.ui.Button;
import com.vaadin.ui.Table;

public class PatientTable extends NavigationManager {
	private Table table;
	private PatientContainer patientContainer = new PatientContainer();
	private NavigationManager navigationManger;
	private PatientTab patientTab;

	public PatientTable(PatientTab appRef) {
		patientTab = appRef;
		//
		// Add these columns as temporary - Replace with container
		getTable().addContainerProperty("First Name", String.class, "");
		table.addContainerProperty("Last Name", String.class, "");
		table.addContainerProperty("Next Appointment", String.class, "");

		table.setSelectable(true);
		table.setSizeFull();
		table.setImmediate(true);
		table.setContainerDataSource(patientContainer.loadInitialData());
		table.setVisibleColumns(PatientContainer.NATUAL_COL_ORDER);
		table.setColumnHeaders(PatientContainer.COL_HEADERS_ENGLISH);

		table.addListener(new Property.ValueChangeListener() {
			private static final long serialVersionUID = 1L;

			public void valueChange(ValueChangeEvent event) {
				if (table.getValue() != null) {
					PatientVO patient = (PatientVO) table.getValue();
					patientTab.showDetailView(patient);
				}
			}
		});

	}

	public Table getTable() {
		if (table == null) {
			table = new Table();
		}
		return table;
	}
}
