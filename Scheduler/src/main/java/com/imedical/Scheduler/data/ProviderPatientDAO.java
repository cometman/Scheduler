package com.imedical.Scheduler.data;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import com.imedical.Scheduler.data.calendar.AppointmentEvent;
import com.imedical.box.BoxIOData;
import com.imedical.box.Box_Finals;
import com.imedical.box.CreateBoxSession;
import com.imedical.box.IBoxIOData;
import com.imedical.box.accountTree.FileVO;
import com.imedical.box.accountTree.FolderVO;
import com.imedical.box.accountTree.XStreamHelper;
import com.imedical.common.LogUtil;
import com.imedical.model.ProviderModel;
import com.thoughtworks.xstream.XStream;
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

	private List<PatientVO> patientList;
	private DBConnectionPool connectionPool = new DBConnectionPool();
	private SQLContainer container;
	private ProviderVO provider;

	private IBoxIOData boxio = new BoxIOData();

	public List<PatientVO> getPatientByString(String searchQuery) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<PatientVO> getAllPatients(ProviderModel provider) {
		if (patientList == null) {
			patientList = this.createPatientsAndAppointmentFromXML(provider)
					.getPatients();
		}

		return patientList;
	}

	public void addNewPatient(ProviderModel providerModel, PatientVO patient) {
		// Add the patient to patient list
		PatientVOList patientList = this
				.createPatientsAndAppointmentFromXML(providerModel);
		patientList.addPatient(patient);

		// Convert to XML

		XStream xstream = XStreamHelper.getXStream();
		String xml = xstream.toXML(patientList);
		File uploadFile = new File(Box_Finals.BOX_FILE_PATIENT_SCHEDULER);
		try {
			FileUtils.writeStringToFile(uploadFile, xml);
		} catch (IOException e) {
			LogUtil.writeMessage("Unable to write string to file!");
			e.printStackTrace();
		}

		// Commit the changes to BOX.net
		boxio.uploadDataFile(uploadFile, providerModel.getProvider(),
				providerModel.getProviderDataFolderID());

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
			// providerVO.setAuth_key(container.getItem(positionInDB)
			// .getItemProperty("ticket").toString());

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

	@Override
	public PatientVOList createPatientsAndAppointmentFromXML(
			ProviderModel providerModel) {
		if (providerModel != null) {
			PatientVOList patients;

			FolderVO providerAccount = boxio.createAccoutTreePOJO(boxio
					.getAccountTreeXML(0, providerModel.getProvider()));
			for (FolderVO f : providerAccount.getFolders()) {
				/*
				 * First find the patient scheduler folder
				 */
				if (f.getName().equals(Box_Finals.BOX_FOLDER_PATIENT_SCHEDULER)) {
					/*
					 * Next, locate the patient scheduler file
					 */
					for (FileVO file : f.getFiles()) {
						/*
						 * Once we have the file, download it and pass to the
						 * getPatientsFromXML method
						 */
						if (file.getFileName().equals(
								Box_Finals.BOX_FILE_PATIENT_SCHEDULER)) {
							File downloadedXML = boxio.retrieveFile(
									file.getId(), providerModel.getProvider());
							String stringFromFile = this
									.getStringContentsFromFile(downloadedXML);
							if (stringFromFile.length() > 0) {
								patients = getPatientsFromXML(stringFromFile);
							} else {
								patients = new PatientVOList();
							}
							return patients;
						}
					}
				}
			}
		}
		return null;
	}

	private String getStringContentsFromFile(File f) {
		String parsedString = null;
		FileInputStream fis = null;
		try {
			fis = new FileInputStream(f);
			int length = (int) f.length();
			if (length > 0) {
				byte[] buffer = new byte[length];
				fis.read(buffer, 0, length);
				parsedString = new String(buffer);
			} else {
				parsedString = new String();
			}
		} catch (FileNotFoundException e) {
			LogUtil.writeMessage("Problem creating input stream for file ["
					+ f.getName() + "]");
			e.printStackTrace();
		} catch (IOException e) {
			LogUtil.writeMessage("Problem writing to buffer from file ["
					+ f.getName() + "]");
			e.printStackTrace();
		}

		return parsedString;
	}

	/*
	 * Extract the patients from xml via XStream.
	 */
	private PatientVOList getPatientsFromXML(String xmlFile) {
		XStream xstream = new XStream();
		xstream.autodetectAnnotations(true);
		xstream.alias("patient", PatientVO.class);
		xstream.alias("patients", PatientVOList.class);

		PatientVOList patients = new PatientVOList();

		patients = (PatientVOList) xstream.fromXML(xmlFile);

		return patients;

	}
}
