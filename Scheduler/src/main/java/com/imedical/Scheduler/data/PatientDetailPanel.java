package com.imedical.Scheduler.data;

import com.vaadin.addon.touchkit.ui.NavigationButton;
import com.vaadin.addon.touchkit.ui.VerticalComponentGroup;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Form;
import com.vaadin.ui.Panel;

public class PatientDetailPanel extends Panel implements ClickListener {
	private static final long serialVersionUID = 6339603637916622029L;
	private NavigationButton navigationButton = new NavigationButton();
	private PatientForm patientForm;
	private Button editInfo = new Button("Edit", this);

	public PatientDetailPanel(PatientVO patient) {
		patientForm = new PatientForm(patient);

		addComponent(patientForm);
		addComponent(editInfo);

	}

	@Override
	public void buttonClick(ClickEvent event) {
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
	}
}
