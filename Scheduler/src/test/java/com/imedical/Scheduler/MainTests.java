package com.imedical.Scheduler;

import static org.junit.Assert.*;

import java.awt.Event;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.imedical.Scheduler.mobilePages.LoginPage;
import com.vaadin.ui.Button.ClickEvent;

public class MainTests {
	Logger log = LoggerFactory.getLogger(MainTests.class);

	@Test
	public void appDidstart() {
		StartServer start = new StartServer();
		try {
			start.main(null);
		} catch (Exception e) {
			log.error("Can't Start server!");
			e.printStackTrace();
		}
	}

	@Test
	public void another() {

	}

}
