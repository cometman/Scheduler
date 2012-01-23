package com.imedical.Scheduler.data;

import java.io.Serializable;

import com.vaadin.data.Item;
import com.vaadin.data.util.BeanItem;
import com.vaadin.ui.Button;
import com.vaadin.ui.Form;

public class PatientForm extends Form implements Serializable {

	private static final long serialVersionUID = -8995688104424142712L;
	private PatientVO patient;
	private BeanItem<PatientVO> referenceBean;

	public PatientForm(PatientVO patient) {

		this.setCaption("Patient Form");
		this.setDescription("Description");
		BeanItem<PatientVO> patientBean = new BeanItem<PatientVO>(patient);
		this.setItemDataSource(patientBean);
		setEditStatus(false);

		for (Object o : patientBean.getItemPropertyIds()) {
			this.getField(o).setReadOnly(true);
		}

		referenceBean = patientBean;
		System.out.println(referenceBean.getBean().getFirstName());

	}

	@Override
	public void setItemDataSource(Item newDataSource) {
		super.setItemDataSource(newDataSource);
	}

	public void setEditStatus(boolean value) {
//		for (Object o : referenceBean.getItemPropertyIds()) {
//			this.getField(o).setReadOnly(value);
//		}
	}

	public void setPatient(PatientVO patients) {
		System.out.println("test");
		System.out.println("testing" + patients.getFirstName());
		this.patient = patients;

	}

	public PatientVO getPatient() {
		return this.patient;
	}

}
