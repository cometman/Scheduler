package com.imedical.Scheduler.data;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Map;

import com.imedical.Scheduler.data.calendar.AppointmentEvent;
import com.vaadin.addon.sqlcontainer.SQLContainer;
import com.vaadin.addon.sqlcontainer.filters.Like;
import com.vaadin.data.Item;
import com.vaadin.data.Property;
import com.vaadin.data.util.BeanContainer;
import com.vaadin.data.util.BeanItem;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.data.util.filter.And;
import com.vaadin.data.util.filter.Compare;

public class ProviderPatientDAO implements IProviderPatientDAO {

	private List<PatientVO> patientList = new ArrayList<PatientVO>();
	private DBConnectionPool connectionPool = new DBConnectionPool();
	private SQLContainer container;
	private ProviderVO provider;

	public List<PatientVO> getPatientByString(String searchQuery) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<PatientVO> getAllPatients() {
		// Test Patients
		AppointmentEvent testAppointment = new AppointmentEvent();

		GregorianCalendar gCal = new GregorianCalendar();
		PatientVO patient1 = new PatientVO();
		patient1.setFirstName("Clay");
		patient1.setLastName("Selby");
		patient1.setAge(22);
		patient1.setPhoneNumber("3256687007");
		patient1.setAge(22);

		testAppointment.setPatientVO(patient1);
		gCal.set(Calendar.HOUR_OF_DAY, 12);
		gCal.add(Calendar.DAY_OF_WEEK, 1);
		testAppointment.setStart(gCal.getTime());
		gCal.add(Calendar.HOUR, 1);
		testAppointment.setEnd(gCal.getTime());
		testAppointment.setCaption("New Test apointment");
		testAppointment.setDescription("THe new description for the test");
		patient1.addAppointment(testAppointment);

		patientList.add(patient1);

		return patientList;
	}

	public void addNewPatient(PatientVO patient) {
		// TODO Auto-generated method stub
	}

	@Override
	public PatientVO getPatientByID(int ID) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ProviderVO getProvider(String email, String password)
			throws Exception {

		container = connectionPool.getProviderContainer();
		// Object positionInDB = container.firstItemId();
		//
		// while (positionInDB != null) {
		// if (container.getItem(positionInDB).getItemProperty("username")
		// .toString().equals(userid)) {
		// if (container.getItem(positionInDB).getItemProperty("password")
		// .toString().equals(password)) {
		// provider = new ProviderVO();
		// provider.setPassword(password);
		// break;
		// } else {
		// System.out.println("Bad password");
		// break;
		// }
		// } else {
		// positionInDB = container.nextItemId(positionInDB);
		// }
		// }

		container.addContainerFilter(new Compare.Equal("email", email));
		container.addContainerFilter(new Compare.Equal("password", password));
		try {
			container.commit();
		} catch (UnsupportedOperationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		if (container.size() > 0) {
			Object providerID = container.firstItemId();
			provider = loadProvider(providerID);
			container.removeAllContainerFilters();
			return provider;
		} else {
			container.removeAllContainerFilters();
			// TODO HANDLE THIS EXCEPTION!
			return null;
			// throw new Exception(
			// "No provider's for that email/password were found!");
		}

	}

	/*
	 * Add properties to the provider java bean
	 */
	public ProviderVO loadProvider(Object providerID) {
		ProviderVO provider = new ProviderVO();
		provider.setAuth_key(container.getItem(providerID)
				.getItemProperty("auth_key").toString());
		provider.setEmail(container.getItem(providerID)
				.getItemProperty("email").toString());
		provider.setFirstName(container.getItem(providerID)
				.getItemProperty("first_name").toString());
		provider.setId(container.getItem(providerID).getItemProperty("id")
				.toString());
		provider.setPassword(container.getItem(providerID)
				.getItemProperty("password").toString());
		System.out.println(provider.getAuth_key());

		return provider;

	}

	@Override
	public List<AppointmentEvent> getAppointments(String providerID) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<PatientVO> getPatients(String providerID) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isProviderEmailValid(String email) {

		return false;
	}

	@Override
	public boolean isProviderUserNameValid(String userName) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<ProviderVO> loadProvidersInList() {
		List<ProviderVO> providerList = new ArrayList<ProviderVO>();
		container = connectionPool.getProviderContainer();
		Object positionInDB = container.firstItemId();
		while (positionInDB != null) {
			ProviderVO providerVO = new ProviderVO();
			providerVO.setEmail(container.getItem(positionInDB)
					.getItemProperty("email").toString());
			providerVO.setPassword(container.getItem(positionInDB)
					.getItemProperty("password").toString());
			providerVO.setId(container.getItem(positionInDB)
					.getItemProperty("id").toString());
			providerVO.setFirstName(container.getItem(positionInDB)
					.getItemProperty("first_name").toString());
			providerVO.setAuth_key(container.getItem(positionInDB)
					.getItemProperty("auth_key").toString());
//			providerVO.setAuth_key(container.getItem(positionInDB)
//					.getItemProperty("ticket").toString());

			providerList.add(providerVO);
			positionInDB = container.nextItemId(positionInDB);

		}
		container = null;
		return providerList;
	}

	@Override
	public void addProvider(ProviderVO provider)
			throws UnsupportedOperationException, SQLException {
		container = connectionPool.getProviderContainer();
		container.addItem();
		Object lastId = container.lastItemId();
		container.getItem(lastId).getItemProperty("email")
				.setValue(provider.getEmail());
		container.getItem(lastId).getItemProperty("first_name")
				.setValue(provider.getFirstName());
		container.getItem(lastId).getItemProperty("password")
				.setValue(provider.getPassword());
		container.getItem(lastId).getItemProperty("auth_key")
				.setValue(provider.getAuth_key());
		container.commit();

	}

	@Override
	public void deleteProvider(ProviderVO provider) {
		container = connectionPool.getProviderContainer();

		for (int i = 0; i <= container.size(); i++) {
			String id = null;
			if (container.getIdByIndex(i).toString() != null) {
				id = container.getIdByIndex(i).toString();

				if (id.equals(provider.getId())) {
					container.removeItem(container.getIdByIndex(i));
				}

			}

		}

		try {

			container.commit();
		} catch (UnsupportedOperationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
