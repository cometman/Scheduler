package com.imedical.box;

import static org.junit.Assert.*;

import java.io.File;

import org.junit.Test;

import com.imedical.Scheduler.data.IProviderPatientDAO;
import com.imedical.Scheduler.data.ProviderPatientDAO;
import com.imedical.Scheduler.data.ProviderVO;
import com.imedical.box.accountTree.FolderVO;

public class UploadFileTest {

	@Test
	public void canUploadFile() throws Exception {
		IBoxIOData ibox = new BoxIOData();
		IProviderPatientDAO pdao = new ProviderPatientDAO();

		ProviderVO testProvider = pdao.getProvider("admin@pasadya.com",
				"halflife87");
		String boxAcctXML = ibox.getAccountTreeXML(0, testProvider);
		FolderVO acct = ibox.createAccoutTreePOJO(boxAcctXML);
		File testFile = new File("C:/Testing/Test.xml");

//		UploadFile uploadFile = new UploadFile(testFile, testProvider, "0");
//
//		uploadFile.startUpload();
//		BoxUpload boxupload = new BoxUpload(testFile, testProvider, "0");
//		System.out.println(boxupload.createFileEntity().getContentLength());
//		System.out.println(boxupload.createFileEntity().);
//		boxupload.uploadFile();
		ibox.uploadDataFile(testFile, testProvider, "0");
	}

}
