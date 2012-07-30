package com.imedical.Scheduler.mobilePages;

import com.imedical.Scheduler.MyVaadinApplication;
import com.imedical.Scheduler.data.IProviderPatientDAO;
import com.imedical.Scheduler.data.PatientForm;
import com.imedical.Scheduler.data.PatientVO;
import com.imedical.Scheduler.data.ProviderPatientDAO;
import com.imedical.model.ProviderModel;
import com.vaadin.addon.touchkit.ui.NavigationView;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Panel;
import com.vaadin.ui.Table;

public class NewPatientPage extends NavigationView implements ClickListener {

	private static final long serialVersionUID = 4282214513994424438L;
	private Panel contentPanel = new Panel();
	private PatientVO patient = new PatientVO();
	private Button addPatientButton = new Button("Create", this);
	private Table table;
	private IProviderPatientDAO daoModel = new ProviderPatientDAO();
	private ProviderModel model;

	// Constructor for NEW patients
	public NewPatientPage(Table table, ProviderModel model) {
		this.model = model;
		this.table = table;
		buildContentPanel();
		setContent(contentPanel);

	}

	public void buildContentPanel() {
		this.setCaption("New Patient");
		this.getLeftComponent().setCaption("Back");
		contentPanel.addComponent(addPatientButton);
		PatientForm form = new PatientForm(patient);
		form.setImmediate(true);
		contentPanel.addComponent(form);
	}

	@Override
	public void buttonClick(ClickEvent event) {
		table.addItem(patient);
		daoModel.addNewPatient(model, patient);
//		this.getNavigationManager().navigateBack();
		System.out.println("added");
		MyVaadinApplication.get().getMainWindow().showNotification("Succcess!", "New patient record created.");
	}
}
