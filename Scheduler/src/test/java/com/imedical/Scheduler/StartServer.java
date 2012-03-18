package com.imedical.Scheduler;



import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.webapp.WebAppContext;

public class StartServer {

	private static String contextPath = "/";
	private static String resourceBase = "src/main/webapp";
	private static int httpPort = 8888;

	public static void main(String[] args) throws Exception {
		Server server = new Server(httpPort);
		WebAppContext webapp = new WebAppContext();
		webapp.setContextPath(contextPath);
		webapp.setResourceBase(resourceBase);

		webapp.setClassLoader(Thread.currentThread().getContextClassLoader());
		server.setHandler(webapp);
		server.start();
		server.join();
		System.out.println("Started Jetty " + Server.getVersion()
				+ ", go to http://192.168.1.3:" + httpPort + contextPath);
	}
}
