package com.upiicsa.biblioteca.dao.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.upiicsa.biblioteca.dao.FactoryDao;

public class FactoryDaoImpl implements FactoryDao {

	Log logger = LogFactory.getLog(FactoryDaoImpl.class);
			
	private Connection connection = null;
	final String driverDB = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
	final String urlDB = "jdbc:sqlserver://localhost:1433;databaseName=biblioteca;";
	final String userDB = "biblioteca_user";
	final String passDB = "biblioteca123";
	
	@Override
	public Connection getConex() throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		Class.forName(driverDB);
		connection = DriverManager.getConnection(urlDB, userDB, passDB);
		return connection;
	}

	@Override
	public void closeConnection() throws SQLException {
		// TODO Auto-generated method stub
		connection.close();
	}
	
}
