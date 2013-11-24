package com.upiicsa.biblioteca.dao.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.upiicsa.biblioteca.dao.AbstractFacade;
import com.upiicsa.biblioteca.model.AutorPojo;

public class AutorDaoImpl extends FactoryDaoImpl implements AbstractFacade {
	Log logger = LogFactory.getLog(AutorDaoImpl.class);
	private Connection connection;
	private Statement statement;
	private ResultSet result;

	@Override
	public List<?> getListado() {
		logger.info("[Entrando al método AutorDaoImpl.getListado]");

		List<AutorPojo> listaAutoresPojo = null;

		AutorPojo autorPojo = null;
		String query;

		query = "  select c.* from libro a join libAut b on a.idLibro=b.idLibro join autor c on c.idAutor=b.idAutor;";
		logger.info(query + "::..");
		try {
			connection = getConex();
			statement = connection.createStatement();
			result = statement.executeQuery(query);

			listaAutoresPojo = new ArrayList<AutorPojo>();

			while (result.next()) {

				autorPojo = new AutorPojo();
				autorPojo.setIdAutor(result.getInt("idAutor"));
				autorPojo.setNombre(result.getString("nombre"));
				autorPojo.setNacionalidad(result.getString("nacionalidad"));
				listaAutoresPojo.add(autorPojo);
			}

			closeConnection();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {

			logger.info(">> Error code =  " + e.getErrorCode() + " <<");
		} catch (Exception e) {
			logger.info("Error tipo general : " + e.getMessage());
		}

		return listaAutoresPojo;
	}

	@Override
	public Object getObjeto(Object id) {
		logger.info("[Entrada al metodo AutorDaoImpl.getObjeto()]");
		AutorPojo autorPojo = new AutorPojo();

		String query = " select  c.* from libro a join libAut b on a.idLibro=b.idLibro join autor c on c.idAutor=b.idAutor where idAutor=" + id.toString()
				+ ";";
		logger.info("->> " + query);
		try {
			connection = getConex();
			statement = connection.createStatement();
			result = statement.executeQuery(query);

			result.next();

			autorPojo.setIdAutor(result.getInt("idAutor"));
			autorPojo.setNombre(result.getString("nombre"));
			autorPojo.setNacionalidad(result.getString("nacionalidad"));

			closeConnection();

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			logger.info(">> Error code =  " + e.getErrorCode() + " <<");
			e.printStackTrace();
		} catch (Exception e) {
			logger.info(e.getMessage());
		}
		return autorPojo;
	}

	@Override
	public void addObjeto(Object o) {
		logger.info("[Entrada al metodo AutorDaoImpl.addObjeto()]");

		AutorPojo autorPojo = (AutorPojo) o;
		String query = "insert into autor values (" + "'"
				+ autorPojo.getNombre() + "'," + "'" + autorPojo.getNacionalidad()
				+ "');";

		logger.info("->> " + query);
		try {

			connection = getConex();
			statement = connection.createStatement();

			statement.executeUpdate(query);

			closeConnection();

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			logger.info(">> Error code =  " + e.getErrorCode() + " <<");
			e.printStackTrace();
		} catch (Exception e) {
			logger.info(e.getMessage());
		}

	}

	@Override
	public void editObjeto(Object o) {
		logger.info("[Entrada al metodo AutorDaoImpl.editObjeto()]");

		AutorPojo autorPojo = (AutorPojo) o;

		String query = "";

		logger.info("->> " + query);
		try {
			connection = getConex();
			statement = connection.createStatement();
			result = statement.executeQuery(query);
			result.next();

			query = "update autor set " + "nombre='" + autorPojo.getNombre()
					+ "', " + "nacionalidad='" + autorPojo.getNacionalidad() + "';";

			statement.executeUpdate(query);

			closeConnection();

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			logger.info(">> Error code =  " + e.getErrorCode() + " <<");
			e.printStackTrace();
		} catch (Exception e) {
			logger.info(e.getMessage());
		}
	}

	@Override
	public void deleteObjeto(Object id) {
		// TODO Auto-generated method stub
		logger.info("[Entrada al metodo AutorDaoImpl.deleteObjeto()]");
		String query = "DELETE FROM autor WHERE idAutor=" + (Integer) id;
		logger.info("->> " + query);
		try {
			connection = getConex();
			statement = connection.createStatement();
			statement.executeUpdate(query);
			closeConnection();

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			logger.info(">> Error code =  " + e.getErrorCode() + " <<");
			e.printStackTrace();
		} catch (Exception e) {
			logger.info(e.getMessage());
		}
	}

	@Override
	public List<?> customListado(String cond) {
		return null;
	}

	@Override
	public void customElimina(String qry) {
		// TODO Auto-generated method stub

	}

}
