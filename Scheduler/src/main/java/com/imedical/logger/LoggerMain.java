package com.imedical.logger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LoggerMain {

	public static Logger logger;

	public LoggerMain(Class clazz) {
		logger = LoggerFactory.getLogger(clazz);
	}

	public static void errorLog(String s) {
		logger.error(s);
	}

	public static void warnLog(String s) {
		logger.warn(s);
	}

	public static void infoLog(String s) {
		logger.info(s);
	}

}
