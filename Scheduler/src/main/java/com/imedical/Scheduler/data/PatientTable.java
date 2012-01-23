package com.imedical.Scheduler.data;

import com.imedical.Scheduler.mobilePages.PatientDetailView;
import com.imedical.Scheduler.mobilePages.PatientTab;
import com.vaadin.addon.touchkit.ui.NavigationButton;
import com.vaadin.addon.touchkit.ui.NavigationManager;
import com.vaadin.addon.touchkit.ui.NavigationView;
import com.vaadin.data.Property;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.ui.Button;
import com.vaadin.ui.Table;

public class PatientTable extends NavigationManager {
	private Table table;
	private PatientContainer patientContainer = new PatientContainer();
	private NavigationManager navigationManger;
	private PatientDetailView patientView = null;
	private PatientTab patientTab = null;

	public PatientTable() {
		Button testButton = new Button("test");

		// Add these columns as temporary - Replace with container
		getTable().addContainerProperty("First Name", String.class, "");
		table.addContainerProperty("Last Name", String.class, "");
		table.addContainerProperty("Next Appointment", String.class, "");

		table.setSelectable(true);
		table.setSizeFull();
		table.setImmediate(true);
		table.setContainerDataSource(patientContainer.loadData());
		table.setVisibleColumns(PatientContainer.NATUAL_COL_ORDER);
		table.setColumnHeaders(PatientContainer.COL_HEADERS_ENGLISH);

		table.addListener(new Property.ValueChangeListener() {
			private static final long serialVersionUID = 1L;

			public void valueChange(ValueChangeEvent event) {

				if (table.getValue() != null) {
					PatientVO patient = (PatientVO) table.getValue();
					patientTab.navigateTo(patientView = new PatientDetailView(
							patient));
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

	public PatientTab setInstance(PatientTab tab) {
		patientTab = tab;
		return patientTab;
	}

}
