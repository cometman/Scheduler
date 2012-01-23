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

	public DBConnectionPool() {

		if (jdbcPool == null) {
			try {
				jdbcPool = new SimpleJDBCConnectionPool(
						"com.mysql.jdbc.Driver",
						"jdbc:mysql://184.106.130.103:3306/Pasadya",
						"cometman", "halflife87", 2, 5);

				TableQuery tq = new TableQuery("Inventory", jdbcPool);
				tq.setVersionColumn("OPTLOCK");
				SQLContainer container = new SQLContainer(tq);
			
				System.out.println(container.getItemUnfiltered(container.firstItemId()));
	
				
				
				System.out.println(tq.getPrimaryKeyColumns());
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
	}
}
