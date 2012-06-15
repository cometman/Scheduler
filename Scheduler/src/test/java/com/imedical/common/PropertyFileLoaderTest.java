package com.imedical.common;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;
import java.io.IOException;

import javax.xml.bind.PropertyException;

import org.junit.Test;

import com.imedical.Scheduler.data.ProviderVO;

public class PropertyFileLoaderTest {

	@Test
	public void canInitializePropertyLoader() throws IOException {
		PropertyLoader.loadProperties(PropertyLoader.ENVIRONENT_PROPERTIES_FILE);
	}

	@Test
	public void canGetProdEnvironmentFromPropertyFiles() throws IOException, PropertyException {
		PropertyLoader.loadProperties(PropertyLoader.ENVIRONENT_PROPERTIES_FILE);
		assertTrue(PropertyLoader.getEnvironment().equals(PropertyLoader.TEST));
	}

	@Test
	public void canCreateTestProviderForTesting() throws IOException, PropertyException{
		ProviderVO testProvider = PropertyLoader.getTestProvider();
		assertTrue(testProvider.getFirstName().equals("clay"));
	}
	
	@Test
	public void canLoadEnvironmentAndSetProvider() throws IOException, PropertyException{
		
//		PropertyLoader.loadProperties(PropertyLoader.ENVIRONENT_PROPERTIES_FILE);
		ProviderVO testProvider = PropertyLoader.getTestProvider();
		assertTrue(testProvider.getFirstName().equals("clay") && PropertyLoader.getEnvironment().equals(PropertyLoader.TEST));
		
	}
}
