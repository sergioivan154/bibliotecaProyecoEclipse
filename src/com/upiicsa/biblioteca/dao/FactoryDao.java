package com.upiicsa.biblioteca.dao;

import java.sql.Connection;
import java.sql.SQLException;

public interface FactoryDao {
	Connection getConex()throws ClassNotFoundException, SQLException;
	void closeConnection()throws SQLException ;

}
