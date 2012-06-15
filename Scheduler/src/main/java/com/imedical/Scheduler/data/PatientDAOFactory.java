package com.imedical.Scheduler.data;

import java.io.IOException;

import javax.xml.bind.PropertyException;

import com.imedical.common.PropertyLoader;

public class PatientDAOFactory {
	private IProviderPatientDAO patientDAO;
	private static PatientDAOFactory _instance;

	private PatientDAOFactory() throws PropertyException, IOException {

		String environment = PropertyLoader.getEnvironment();

		if (environment.equals(PropertyLoader.TEST)) {
			patientDAO = new TestProviderPatientDAO();
		} else if (environment.equals(PropertyLoader.PROD)) {
			patientDAO = new ProviderPatientDAO();
		}
	}

	public static PatientDAOFactory getInstance() {
		if (_instance == null){
			try {
				_instance = new PatientDAOFactory();
			} catch (PropertyException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return _instance;
	}

	public IProviderPatientDAO getImplementation() {
		return patientDAO;
	}

}
