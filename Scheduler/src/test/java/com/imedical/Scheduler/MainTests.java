package com.imedical.Scheduler;

import static org.junit.Assert.*;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MainTests {
	Logger log = LoggerFactory.getLogger(MainTests.class);

	@Test
	public void test() {
		fail("Not yet implemented");
	}

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
}
