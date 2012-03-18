package com.imedical.box;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;

import org.apache.axis.utils.ByteArray;
import org.junit.Test;

import com.imedical.Scheduler.data.IProviderPatientDAO;
import com.imedical.Scheduler.data.ProviderPatientDAO;
import com.imedical.Scheduler.data.ProviderVO;
import com.imedical.box.accountTree.BoxAccountTree;
import com.imedical.box.accountTree.FolderVO;
import com.sun.xml.messaging.saaj.util.ByteOutputStream;

public class BoxIODataTest {

	@Test
	public void canFindProvider() throws Exception {
		IBoxIOData ibox = new BoxIOData();
		IProviderPatientDAO pdao = new ProviderPatientDAO();

		ProviderVO testProvider = pdao.getProvider("admin@pasadya.com",
				"halflife87");
		// ibox.retrieveFile(fileID, provider)

	}

	@Test
	public void canGetAccountTree() throws Exception {
		IBoxIOData ibox = new BoxIOData();
		IProviderPatientDAO pdao = new ProviderPatientDAO();

		ProviderVO testProvider = pdao.getProvider("admin@pasadya.com",
				"halflife87");

		String boxAcctXML = ibox.getAccountTreeXML(0, testProvider);
		FolderVO acct = ibox.createAccoutTreePOJO(boxAcctXML);
		System.out.println(acct.getFolders().get(0).getName());
	}

	@Test
	public void canGetFileIDByFolderName() throws Exception {
		IBoxIOData ibox = new BoxIOData();
		IProviderPatientDAO pdao = new ProviderPatientDAO();

		ProviderVO testProvider = pdao.getProvider("admin@pasadya.com",
				"halflife87");

		String boxAcctXML = ibox.getAccountTreeXML(0, testProvider);
		FolderVO acct = ibox.createAccoutTreePOJO(boxAcctXML);

		String fileid = ibox.getFileIDByFolderName(acct, "PatientScheduler");
		System.out.println(fileid);
	}

	@Test
	public void canUploadFileTOBox() throws Exception {
		IBoxIOData ibox = new BoxIOData();
		IProviderPatientDAO pdao = new ProviderPatientDAO();

		ProviderVO testProvider = pdao.getProvider("admin@pasadya.com",
				"halflife87");
		String boxAcctXML = ibox.getAccountTreeXML(0, testProvider);
		FolderVO acct = ibox.createAccoutTreePOJO(boxAcctXML);
		File testFile = new File("C:/Testing/Testdoc.txt");
		ibox.uploadDataFile(testFile,testProvider, "0");
	}

}
