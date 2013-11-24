package com.upiicsa.biblioteca.dao.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class UserDaoImpl extends FactoryDaoImpl {

	Log logger = LogFactory.getLog(UserDaoImpl.class);
	
	private Connection connection;
	private Statement statement;
	private ResultSet result;

	public boolean login(String user, String password) {

		boolean ret = false;
		String query = "select count(*) as n from dbo.Usuario where nombre_usuario= '"
				+ user + "' and clave= '" + password + "' ;";
		System.out.println(query);
		try {
			connection = getConex();
			statement = connection.createStatement();
			result = statement.executeQuery(query);

			result.next();
			if (result.getInt("n") == 1)
				ret = true;

			closeConnection();

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			logger.info(">> Error code =  " + e.getErrorCode() + " <<");
			e.printStackTrace();
		}catch (Exception e) {
			logger.info(e.getMessage());
		}

		return ret;

	}
	 

	public String rol(String user) {
		String rol = "";
		String query = "select (select COUNT(*) from administrador a where a.rfc = u.rfc) admin, "
				+ "(select COUNT(*) from Evaluador e where e.rfc = u.rfc) eval, "
				+ "(select COUNT(*) from Becario b where b.rfc = u.rfc) beca "
				+ "from Usuario u Where rfc ='" + user+"'";
		System.out.println(query);
		try {
			connection = getConex();
			statement = connection.createStatement();
			result = statement.executeQuery(query);

			result.next();
			if (result.getInt("admin") == 1) {
				rol += "a";
			}
			if (result.getInt("eval") == 1) {
				rol += "e";
			}
			if (result.getInt("beca") == 1) {
				rol += "b";
			}

			closeConnection();

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			logger.info(">> Error code =  " + e.getErrorCode() + " <<");
			e.printStackTrace();
		}catch (Exception e) {
			logger.info(e.getMessage());
		}
		return rol;
	}
	
}
