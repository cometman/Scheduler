package com.imedical.Scheduler;

import java.io.IOException;

import com.imedical.Scheduler.data.PatientContainer;
import com.imedical.Scheduler.mobilePages.LoginPage;
import com.imedical.Scheduler.mobilePages.MainTabSheet;
import com.imedical.Scheduler.mobilePages.RegisterWindow;
import com.imedical.common.PropertyLoader;
import com.imedical.controller.Controller;
import com.imedical.model.ProviderModel;
import com.vaadin.addon.touchkit.ui.TouchKitApplication;
import com.vaadin.addon.touchkit.ui.TouchKitWindow;

/**
 * The Application's "main" class
 */
@SuppressWarnings("serial")
public class MyVaadinApplication extends TouchKitApplication {
	private TouchKitWindow mainWindow;
	private PatientContainer dataSource = null;
	private MyVaadinApplication instance;
	private ProviderModel providerModel;
	private Controller controller;
	private MainTabSheet mainTabSheet = new MainTabSheet();
	private RegisterWindow registerWindow;

	LoginPage login;

	public MyVaadinApplication() {
		try {
			PropertyLoader
			.loadProperties(PropertyLoader.ENVIRONENT_PROPERTIES_FILE);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if (instance == null) {
			login = new LoginPage();
			instance = this;
		}

	}

	@Override
	public void setUser(Object user) {
		super.setUser(user);
//		providerModel = (ProviderModel) user;
		if (user != null && !user.toString().equals("new")) {
			providerModel = login.getProviderModel();
			controller = new Controller(mainTabSheet, providerModel);
		}

		if (user.equals("new")) {
			if (providerModel == null) {
				providerModel = new ProviderModel();
				registerWindow = new RegisterWindow(providerModel.getProvider());
				controller = new Controller(registerWindow, providerModel);
			} else {
				controller.buildRegisterWindow();
			}

		}
//		
//		
//		super.setUser(user);
//		
//		if (user != null && !user.toString().equals("new")) {
//			providerModel = login.getProviderModel();
//			controller = new Controller(mainTabSheet, providerModel);
//		}
//
//		if (user.equals("new")) {
//			if (providerModel == null) {
//				providerModel = new ProviderModel();
//				registerWindow = new RegisterWindow(providerModel.getProvider());
//				controller = new Controller(registerWindow, providerModel);
//			} else {
//				controller.buildRegisterWindow();
//			}
//
//		}
	};

	public void showLoginWindow() {
		mainWindow.addWindow(login);
	}

	@Override
	public void init() {
		setTheme("imedical");
		configureMainWindow();
	}

	/*
	 * We get the property model for the user here. This gets us the user
	 * information..patients..and appointments. We can access this anywhere in
	 * our program :)
	 */
	@Override
	public Object getUser() {
		super.getUser();
		return providerModel;
	};

	@Override
	public void onBrowserDetailsReady() {

	}

	private void configureMainWindow() {
		mainWindow = new TouchKitWindow();
		mainWindow.setCaption("Patient Scheduler");
		mainWindow.setWebAppCapable(true);
		mainWindow.setPersistentSessionCookie(true);
		setMainWindow(mainWindow);
		mainWindow.addWindow(login);
	}

	public PatientContainer getPatientContainer() {
		if (dataSource == null) {
			dataSource = new PatientContainer();
			ProviderModel model = (ProviderModel) getUser();
			dataSource = dataSource.loadInitialData(model);
		}
		return dataSource;
	}

	public MyVaadinApplication getApplicationInstance() {

		return instance;
	}

}
