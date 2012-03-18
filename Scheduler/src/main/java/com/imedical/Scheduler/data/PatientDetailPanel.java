package com.imedical.Scheduler.data;

import com.imedical.Scheduler.MyVaadinApplication;
import com.imedical.model.ProviderModel;
import com.vaadin.addon.touchkit.ui.NavigationButton;
import com.vaadin.addon.touchkit.ui.VerticalComponentGroup;
import com.vaadin.data.Item;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Form;
import com.vaadin.ui.Panel;
import com.vaadin.ui.Table;

public class PatientDetailPanel extends Panel implements ClickListener {
	private static final long serialVersionUID = 6339603637916622029L;
	private PatientForm patientForm;
	private Button editInfo = new Button("Edit", this);
	private Button deleteButton = new Button("Delete", this);
	private ProviderModel providerModel = (ProviderModel) MyVaadinApplication
			.get().getUser();
	private PatientVO patient;

	// Constructor for existing patients
	public PatientDetailPanel(PatientVO patient) {
		if (patient != null) {
			this.patient = patient;
		}

		patientForm = new PatientForm(patient);
		patientForm.setImmediate(true);
		addComponent(patientForm);

		if (patient.getUniqueId() > 0) {
			addEditButton();
			addComponent(deleteButton);
		}

	}

	@Override
	public void buttonClick(ClickEvent event) {
		if (event.getButton().equals(editInfo)) {
			if (editInfo.getCaption().equals("Edit")) {
				for (Object o : patientForm.getItemDataSource()
						.getItemPropertyIds()) {
					editInfo.setCaption("Finished");
					patientForm.getField(o).setReadOnly(false);
				}
			} else {
				for (Object o : patientForm.getItemDataSource()
						.getItemPropertyIds()) {
					patientForm.getField(o).setReadOnly(true);
					editInfo.setCaption("Edit");
				}
			}
		} else if (event.getButton().equals(deleteButton)) {
			providerModel.getPatients().remove(patient);
			for (PatientVO patient : providerModel.getPatients()) {
				System.out.println(patient.getFirstName());
			}

		}
	}

	public void addEditButton() {
		addComponent(editInfo);
	}

}
