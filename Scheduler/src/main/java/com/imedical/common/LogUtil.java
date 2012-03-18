package com.imedical.common;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

public class LogUtil {

	public static LogUtil instance = new LogUtil();

	private File file = new File("admin_log.txt");
	private static FileWriter outFile;
	private static PrintWriter out;
	private static final SimpleDateFormat sdf = new SimpleDateFormat(
			"MM/dd/yyyy h:mm a");

	private LogUtil() {
		/*
		 * Only needs this for the internal operations. Never call externally..
		 */

		if (!file.exists()) {

			createLogFile();
		}
		prepareWriter();

	}

	private void createLogFile() {

		try {
			file.createNewFile();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void prepareWriter() {
		try {

			outFile = new FileWriter(file, true);
			out = new PrintWriter(outFile);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void writeMessage(String s) {

		Date currentTime = new Date();

		out.append(sdf.format(currentTime.getTime()) + ": " + s + "\n");

	}

	public static void closeWriter() {

		out.close();

	}
}
