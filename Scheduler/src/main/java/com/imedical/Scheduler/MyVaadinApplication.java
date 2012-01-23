package com.imedical.Scheduler;

import com.imedical.Scheduler.data.PatientContainer;
import com.imedical.Scheduler.mobilePages.MainTabSheet;
import com.imedical.Scheduler.mobilePages.PatientTab;
import com.vaadin.addon.touchkit.ui.NavigationBar;
import com.vaadin.addon.touchkit.ui.TouchKitApplication;
import com.vaadin.addon.touchkit.ui.TouchKitWindow;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Label;
import com.vaadin.ui.TabSheet.Tab;
import com.vaadin.ui.Window;

/**
 * The Application's "main" class
 */
@SuppressWarnings("serial")
public class MyVaadinApplication extends TouchKitApplication {
	private TouchKitWindow mainWindow;
	private PatientContainer dataSource = null;
	private MyVaadinApplication instance;

	public MyVaadinApplication() {
		if (instance == null) {
			instance = this;
		}
	}

	@Override
	public void init() {
		configureMainWindow();
	}

	@Override
	public void onBrowserDetailsReady() {
		mainWindow.setContent(new MainTabSheet());
	}

	private void configureMainWindow() {
		mainWindow = new TouchKitWindow();
		mainWindow.setCaption("Patient Scheduler");
		mainWindow.setWebAppCapable(true);
		mainWindow.setPersistentSessionCookie(true);
		setMainWindow(mainWindow);
	}

	public PatientContainer getPatientContainer() {
		if (dataSource == null) {
			dataSource = new PatientContainer();
			dataSource = dataSource.loadData();
		}

		return dataSource;
	}

	public MyVaadinApplication getApplicationInstance() {

		return instance;
	}
}
