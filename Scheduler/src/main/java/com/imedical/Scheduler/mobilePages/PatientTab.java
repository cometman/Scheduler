package com.imedical.Scheduler.mobilePages;

import java.lang.reflect.InvocationTargetException;
import java.text.DateFormat;

import com.imedical.Scheduler.data.PatientContainer;
import com.imedical.Scheduler.data.PatientForm;
import com.imedical.Scheduler.data.PatientTable;
import com.imedical.Scheduler.data.PatientVO;
import com.vaadin.addon.touchkit.ui.NavigationManager;
import com.vaadin.addon.touchkit.ui.NavigationView;
import com.vaadin.addon.touchkit.ui.TabBarView;
import com.vaadin.data.Property;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.event.FieldEvents.TextChangeEvent;
import com.vaadin.event.FieldEvents.TextChangeListener;
import com.vaadin.ui.AbstractTextField.TextChangeEventMode;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Panel;
import com.vaadin.ui.Table;
import com.vaadin.ui.TextField;

public class PatientTab extends NavigationManager {

	private static final long serialVersionUID = -3244374618085945750L;

	private Panel contentPanel = new Panel();
	private PatientTable patientTable;
	private PatientTab instance;
	private PatientDetailView patientDetailView;
	private PatientContainer patientContainer = new PatientContainer();
	private TextField searchBar = new TextField();

	public PatientTab() {
		if (instance == null) {
			instance = this;
			patientTable = new PatientTable(instance);
			buildView();
		}

		searchBar.addListener(new TextChangeListener() {

			public void textChange(TextChangeEvent event) {
				try {
					patientTable.getTable().setContainerDataSource(
							patientContainer.loadFilteredData(event.getText()));
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
				System.out.println("test" + event.getText());

			}
		});
		searchBar.setTextChangeEventMode(TextChangeEventMode.LAZY);
		this.setCaption("Patient Viewer");

	}

	public void buildView() {

		contentPanel.addComponent(searchBar);
		contentPanel.addComponent(patientTable.getTable());
		navigateTo(contentPanel);

	}

	public void showDetailView(PatientVO patient) {
		patientDetailView = new PatientDetailView(patient);
		navigateTo(patientDetailView);
	}

	public PatientTab getInstance() {
		return instance;
	}

}
