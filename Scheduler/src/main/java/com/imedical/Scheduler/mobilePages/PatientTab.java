package com.imedical.Scheduler.mobilePages;

import java.lang.reflect.InvocationTargetException;

import com.imedical.Scheduler.data.PatientContainer;
import com.imedical.Scheduler.data.PatientTable;
import com.imedical.model.ProviderModel;
import com.vaadin.addon.touchkit.ui.NavigationManager;
import com.vaadin.event.FieldEvents.TextChangeEvent;
import com.vaadin.event.FieldEvents.TextChangeListener;
import com.vaadin.ui.AbstractTextField.TextChangeEventMode;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Panel;
import com.vaadin.ui.TextField;

public class PatientTab extends NavigationManager {

	private static final long serialVersionUID = -3244374618085945750L;

	private Panel contentPanel = new Panel();
	private PatientTab instance;
	private PatientContainer patientContainer = new PatientContainer();
	private TextField searchBar = new TextField();
	private PatientTable patientTable;
	private Button addPatientButton = new Button("Add Patient");
	private ProviderModel model;

	public PatientTab(ProviderModel model) {
		this.model = model;
		if (instance == null) {
			instance = this;
			patientTable = new PatientTable(model);
		}
		buildView();

		searchBar.addListener(new TextChangeListener() {

			private static final long serialVersionUID = -7982739247858478256L;

			public void textChange(TextChangeEvent event) {
				try {
					patientTable.getTable().setContainerDataSource(
							patientContainer.loadFilteredData(event.getText()));
					patientTable.getTable().setVisibleColumns(
							PatientContainer.NATUAL_COL_ORDER);
					patientTable.getTable().setColumnHeaders(
							PatientContainer.COL_HEADERS_ENGLISH);
				} catch (IllegalAccessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IllegalArgumentException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (InvocationTargetException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		});
		searchBar.setTextChangeEventMode(TextChangeEventMode.LAZY);
		searchBar.setInputPrompt("Search patients");
	

	}
	
	private void addCssStyles(){
		patientTable.getTable().setStyleName("patient-table");
		addPatientButton.setStyleName("add-patient");
		searchBar.setStyleName("search-bar");
	}

	public void buildView() {
		contentPanel.addComponent(addPatientButton);
		contentPanel.addComponent(searchBar);
		contentPanel.addComponent(patientTable.getTable());
	
		addCssStyles();
		navigateTo(contentPanel);

	}

	public void showDetailView(PatientDetailView view) {
		navigateTo(view);
	}

	public void addNewPatient() {
		NewPatientPage newPatientPage = new NewPatientPage(
				patientTable.getTable(), model);
		navigateTo(newPatientPage);
	}

	public PatientTab getInstance() {
		return instance;
	}

	public PatientTable getPatientTable() {
		return patientTable;
	}

	public void addButtonListener(ClickListener click) {
		addPatientButton.addListener(click);
	}

}
