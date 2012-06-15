package com.imedical.Scheduler.mobilePages;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import com.imedical.Scheduler.MyVaadinApplication;
import com.imedical.Scheduler.data.IProviderPatientDAO;
import com.imedical.Scheduler.data.PatientDAOFactory;
import com.imedical.Scheduler.data.PatientVO;
import com.imedical.Scheduler.data.ProviderPatientDAO;
import com.imedical.Scheduler.data.calendar.AppointmentEvent;
import com.imedical.common.SchedulerErrorCodes;
import com.imedical.common.SchedulerException;
import com.imedical.model.ProviderModel;
import com.vaadin.addon.touchkit.ui.NavigationView;
import com.vaadin.data.util.BeanItem;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Form;
import com.vaadin.ui.ListSelect;
import com.vaadin.ui.PopupDateField;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalSplitPanel;

/**
 * Appointment view
 * 
 * @author Clay
 * 
 */
public class AppointmentView extends NavigationView{ //implements ClickListener {

	private static final long serialVersionUID = 4927846137499446876L;
	private AppointmentEvent appointment;
	private PatientVO patient;
	private Form appointmentForm;
	private VerticalSplitPanel panel = new VerticalSplitPanel();
	private Button saveButton = new Button("Save");
	private IProviderPatientDAO pdao = PatientDAOFactory.getInstance().getImplementation();
	private ProviderModel providerModel;

	/**
	 * Default constructor. If the appointment is null, this is a new
	 * appointment we are scheduling. Otherwise, populate information on screen
	 * with existing details.
	 * 
	 * @param appointment
	 * @throws SchedulerException
	 */
	public AppointmentView(AppointmentEvent appointment, PatientVO patient)
			throws SchedulerException {

		// If the patient data is null, error out.
		if (patient == null) {
			throw new SchedulerException(SchedulerErrorCodes.GENERAL_ERROR);
		} else {
			this.patient = patient;
		}

		// Check if a new appointment, or existing appointment
		if (appointment == null) {
			this.appointment = new AppointmentEvent();
		} else {
			this.appointment = appointment;
			buildExistingAppointment();
		}

		// Build base components
		buildBaseComponents();

	}

	/**
	 * Build base components: caption, nav buttons, form layout,etc.
	 */
	public void buildBaseComponents() {
		this.setCaption(patient.getFirstName() + " " + patient.getLastName());
		this.getLeftComponent().setCaption("Cancel");
		appointmentForm = new Form();
		appointmentForm.setImmediate(true);
		BeanItem<AppointmentEvent> appointmentBean = new BeanItem<AppointmentEvent>(
				appointment);
		appointmentForm.setItemDataSource(appointmentBean);
		appointmentForm.getField("patientVO").setVisible(false);
		appointmentForm.getField("styleName").setVisible(false);
		appointmentForm.getField("end").setVisible(false);
		appointmentForm.getField("start").setVisible(false);
		PopupDateField dateField = new PopupDateField();
		dateField.setDateFormat("MM-dd-yyyy hh:mm");
	
		// Add a custom field for duration in 15 minute increments
		BeanItemContainer<String> durationChoices = new BeanItemContainer<String>(
				String.class);
		durationChoices.addBean(new String("15"));
		durationChoices.addBean(new String("30"));
		durationChoices.addBean(new String("45"));
		durationChoices.addBean(new String("60"));
		durationChoices.addBean(new String("75"));
		durationChoices.addBean(new String("90"));

		ListSelect durationSelect = new ListSelect("Duration", durationChoices);
		appointmentForm.addField("start", dateField);
		appointmentForm.addField("duration", durationSelect);
		panel.setFirstComponent(appointmentForm);
		panel.setSecondComponent(saveButton);

		this.setContent(panel);

	}

	/**
	 * Populate screen with existing appointment details
	 */
	public void buildExistingAppointment() {

	}

	/**
	 * Create a new appointment object.
	 */
	public void buildNewAppointment() {

	}
	
	public void setSaveListener(ClickListener listener){
		saveButton.addListener(listener);
	}
	/**
	 * Save button click handler. Add the appointment to patient object, update
	 * the database, then navigate back to patient record.
	 */
	
	public AppointmentEvent createNewAppointment() {
		// Create the new appointment based on form results
		AppointmentEvent newAppointment = new AppointmentEvent();
		newAppointment.setPatientVO(patient);
		newAppointment.setAllDay(false);
		newAppointment.setCaption((String) appointmentForm.getField("caption")
				.getValue());
		newAppointment.setDescription((String) appointmentForm.getField(
				"description").getValue());
		Date startTime = (Date) appointmentForm.getField("start").getValue();
		newAppointment.setStart(startTime);
		Calendar endTime = Calendar.getInstance();
		endTime.setTime(startTime);

		int duration = Integer.valueOf(((String) appointmentForm.getField(
				"duration").getValue()));

		endTime.set(Calendar.MINUTE, endTime.get(Calendar.MINUTE) + duration);
		newAppointment.setEnd(endTime.getTime());
		
		// Add the appointment to the patient's appointments
		patient.addAppointment(newAppointment);
		
		// Fetch the provider model
		providerModel = (ProviderModel) MyVaadinApplication.get().getUser();
		
		// Update the database
		pdao.updateRecord(providerModel, patient);
		
		// Navigate back to the patient's detail view.
		this.getNavigationManager().navigateBack();
		
		return newAppointment;
	}
}
