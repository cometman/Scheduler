package com.imedical.box;

import java.rmi.RemoteException;
import java.util.HashMap;
import java.util.Map;

import javax.xml.rpc.ServiceException;
import javax.xml.rpc.holders.StringHolder;

import boxnet.BoxnetPort;
import boxnet.BoxnetServiceLocator;

public class CreateBoxSession {
	public static BoxnetServiceLocator service;
	public static BoxnetPort proxy;
	public static final String APIKEY = "dgnc6ghkqc26lnnbr9lhjzx460qhjsxp";
	public static CreateBoxSession session = new CreateBoxSession();

	private CreateBoxSession() {
		/*
		 * Should never be called from outside method.
		 */
		initializeServiceLocator();
	}

	public static Map<String, String> getBoxTicket() throws RemoteException {
		Map<String, String> ticketMap = new HashMap<String, String>();
		StringHolder status = new StringHolder();
		StringHolder ticket = new StringHolder();

		proxy.get_ticket(APIKEY, status, ticket);

		ticketMap.put(status.value, ticket.value);
		return ticketMap;
	}

	public static void initializeServiceLocator() {
		if (proxy == null) {
			try {
				service = new BoxnetServiceLocator();
				proxy = service.getboxnetPort();
			} catch (ServiceException e) {

				e.printStackTrace();
			}
		}
	}

}
