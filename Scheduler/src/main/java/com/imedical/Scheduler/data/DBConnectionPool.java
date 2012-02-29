package com.imedical.Scheduler.data;

import java.sql.SQLException;

import com.vaadin.addon.sqlcontainer.SQLContainer;
import com.vaadin.addon.sqlcontainer.connection.JDBCConnectionPool;
import com.vaadin.addon.sqlcontainer.connection.SimpleJDBCConnectionPool;
import com.vaadin.addon.sqlcontainer.query.QueryDelegate;
import com.vaadin.addon.sqlcontainer.query.TableQuery;
import com.vaadin.data.Container.Filter;

public class DBConnectionPool {
	private JDBCConnectionPool jdbcPool;
	private SQLContainer container;

	public DBConnectionPool() {

		// Provider Connection Pool
		if (jdbcPool == null) {
			try {
				jdbcPool = new SimpleJDBCConnectionPool(
						"com.mysql.jdbc.Driver",
						"jdbc:mysql://184.106.130.103:3306/iMedical_Scheduler",
						"cometman", "halflife87", 2, 5);

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
	}

	public SQLContainer getProviderContainer() {

		TableQuery tq = new TableQuery("Providers", jdbcPool);

		tq.setVersionColumn("id");
		try {
			container = new SQLContainer(tq);
		} catch (SQLException e) {
			System.out.println("problem gettign the provider container..");
			e.printStackTrace();
		}
		return container;
	}

	public SQLContainer getPatientContainer() {

		TableQuery tq = new TableQuery("Patients", jdbcPool);

		tq.setVersionColumn("OPTLOCK");
		try {
			container = new SQLContainer(tq);
		} catch (SQLException e) {
			System.out.println("problem gettign the patient container..");
			e.printStackTrace();
		}
		return container;
	}

	public SQLContainer getAppointmentContainer() {

		TableQuery tq = new TableQuery("Providers", jdbcPool);

		tq.setVersionColumn("OPTLOCK");
		try {
			container = new SQLContainer(tq);
		} catch (SQLException e) {
			System.out.println("problem gettign the appointment container..");
			e.printStackTrace();
		}
		return container;
	}

}
