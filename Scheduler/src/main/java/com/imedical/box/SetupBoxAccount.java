package com.imedical.box;

import java.rmi.RemoteException;

import javax.xml.rpc.holders.StringHolder;

import boxnet.SOAPFolder;
import boxnet.holders.SOAPFolderHolder;

import com.imedical.Scheduler.data.ProviderVO;
import com.imedical.common.LogUtil;

/*
 *  This class will connect to the users box.net account.  Next, the appropriate folders and files will be created.
 *  
 */
public class SetupBoxAccount {
	private String auth_token;
	private long parent_id = 0;
	private final String name = "PatientScheduler";
	private final long share = 0;
	private ProviderVO provider;
	private StringHolder operationStatus = new StringHolder();
	private SOAPFolderHolder folderInformation = new SOAPFolderHolder();

	public SetupBoxAccount(ProviderVO provider) {
		this.provider = provider;
		CreateBoxSession.initializeServiceLocator();
	}

	private SetupBoxAccount() {
		/*
		 * Should not call this...
		 */
	}

	public boolean createRootSchedulerFolder() {
		try {
			CreateBoxSession.proxy.create_folder(Box_Finals.API_KEY,
					provider.getAuth_key(), parent_id, name, share,
					operationStatus, folderInformation);
			return true;
		} catch (RemoteException e) {
			LogUtil.writeMessage("Problem creating root scheduler folder"
					+ e.getMessage());
			e.printStackTrace();
		}
		return false;
	}

	public boolean createPatientWorkSheet() {

		return false;
	}

}
