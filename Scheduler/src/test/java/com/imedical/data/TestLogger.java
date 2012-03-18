package com.imedical.data;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.logging.Level;

import org.junit.Test;

import com.imedical.common.LogUtil;

public class TestLogger {

	@Test
	public void LogTester()  {
		LogUtil.writeMessage("Here is a test");
		LogUtil.writeMessage("Here is a testsdfsdfsdf");
		LogUtil.closeWriter();
	}

}
