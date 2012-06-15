package com.imedical.common;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import javax.xml.bind.PropertyException;

import com.imedical.Scheduler.data.ProviderVO;

/**
 * Loader for property files
 * 
 * @author cometman
 * 
 */
public class PropertyLoader {

	// Environment Properties
	public static final String ENVIRONENT_PROPERTIES_FILE = "src/main/resources/Environment.properties";
	private static final String ENVIRONMENT_PROPERTY = "environment";
	public static final String DEV = "dev";
	public static final String TEST = "test";
	public static final String PROD = "prod";

	// Test Provider Properties
	public static final String TEST_PROVIDER_PROPERTIES_FILE = "src/main/resources/TestProvider.properties";
	private static final String FIRST_NAME_PROPERTY = "first_name";
	private static final String ID_PROPERTY = "id";
	private static final String PASSWORD_PROPERTY = "password";
	private static final String EMAIL_PROPERTY = "email";
	private static final String AUTHKEY_PROPERTY = "auth_key";

	private static Properties properties;
	private static FileInputStream fis;
	private static PropertyLoader _instance;

	private PropertyLoader() {

	}

	/**
	 * Load the property file into memory and prepare for accessing
	 * 
	 * @throws IOException
	 */
	public static void loadProperties(String propFile) throws IOException {
		properties = new Properties();
		File propertyFile = new File(propFile);
		try {
			fis = new FileInputStream(propertyFile);
			properties.load(fis);
		} catch (IOException e) {
			throw new FileNotFoundException(
					"Unable to locate property file for [" + propFile + "]");
		}

	}

	/**
	 * Return the environment specification. Either dev/test/prod based on
	 * environment.properties
	 * 
	 * @return
	 * @throws PropertyException
	 * @throws IOException
	 */
	public static String getEnvironment() throws PropertyException, IOException {
		// Check to see if property loader has been initialized. Lazy init...
		if (properties == null) {
			loadProperties(ENVIRONENT_PROPERTIES_FILE);
		}

		// Check to make sure property is not null or empty
		if (properties.getProperty(ENVIRONMENT_PROPERTY) != null
				&& !properties.getProperty(ENVIRONMENT_PROPERTY).isEmpty()) {
			// Check if we are in prod
			if (properties.getProperty(ENVIRONMENT_PROPERTY).equals(PROD)) {
				properties = null;
				return PROD;
			}
			// Check if we are in test
			if (properties.getProperty(ENVIRONMENT_PROPERTY).equals(TEST)) {
				properties = null;
				return TEST;
			}
			// Otherwise, we are in dev
			else {
				properties = null;
				return DEV;
			}
		} else {
			properties = null;
			throw new PropertyException("Unable to load environment settings.");
		}
	}

	/**
	 * Get a test provider in order to develop and test with
	 * 
	 * @return
	 * @throws IOException
	 * @throws PropertyException
	 */
	public static ProviderVO getTestProvider() throws IOException,
			PropertyException {
		ProviderVO testProvider = new ProviderVO();
		if (properties == null) {
			loadProperties(TEST_PROVIDER_PROPERTIES_FILE);
		}

		// Check to see if the Test Provider ID is null or empty. If either one
		// of these is, we
		// know there is a problem loading the property file..
		if (properties.getProperty(ID_PROPERTY) != null
				&& !properties.getProperty(ID_PROPERTY).isEmpty()) {

			// Set the test provider details from the properties file...
			testProvider.setAuth_key(properties.getProperty(AUTHKEY_PROPERTY));
			testProvider.setEmail(properties.getProperty(EMAIL_PROPERTY));
			testProvider.setFirstName(properties
					.getProperty(FIRST_NAME_PROPERTY));
			testProvider.setPassword(properties.getProperty(PASSWORD_PROPERTY));
			testProvider.setId(properties.getProperty(ID_PROPERTY));
			properties = null;
			return testProvider;

		} else {
			properties = null;
			throw new PropertyException(
					"Unable to load test provider settings.");
		}
	}
	
	public static boolean isTest(){
		try {
			if (getEnvironment().equals(PropertyLoader.TEST)){
				return true;
			}
		} catch (PropertyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	
	public static boolean isProd(){
		try {
			if (getEnvironment().equals(PropertyLoader.PROD)){
				return true;
			}
		} catch (PropertyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

}
