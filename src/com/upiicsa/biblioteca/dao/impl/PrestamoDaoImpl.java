package com.upiicsa.biblioteca.dao.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.upiicsa.biblioteca.dao.AbstractFacade;
import com.upiicsa.biblioteca.model.PrestamoPojo;

public class PrestamoDaoImpl extends FactoryDaoImpl implements AbstractFacade {
	Log logger = LogFactory.getLog(PrestamoDaoImpl.class);
	private Connection connection;
	private Statement statement;
	private ResultSet result;

	@Override
	public List<?> getListado() {
		logger.info("[Entrando al método PrestamoDaoImpl.getListado]");

		List<PrestamoPojo> listaPrestamosPojo = null;

		PrestamoPojo prestamoPojo = null;
		String query;

		query = " select * from prestamo;";
		logger.info(query + "::..");
		try {
			connection = getConex();
			statement = connection.createStatement();
			result = statement.executeQuery(query);

			listaPrestamosPojo = new ArrayList<PrestamoPojo>();

			while (result.next()) {

				prestamoPojo = new PrestamoPojo();
				prestamoPojo.setIdPrestamo(result.getInt("idPrestamo"));
				prestamoPojo.setFechaPrestamo(result.getDate("fechaPrestamo"));
				prestamoPojo.setFechaEntrega(result.getDate("fechaEntrega"));
				prestamoPojo.setBoleta(result.getString("boleta"));
				prestamoPojo.setIdLibro(result.getInt("idLibro"));
				
				prestamoPojo.setDevuelto(result.getBoolean("devuelto"));

				listaPrestamosPojo.add(prestamoPojo);
			}

			closeConnection();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {

			logger.info(">> Error code =  " + e.getErrorCode() + " <<");
		} catch (Exception e) {
			logger.info("Error tipo general : " + e.getMessage());
		}

		return listaPrestamosPojo;
	}

	@Override
	public Object getObjeto(Object id) {
		logger.info("[Entrada al metodo PrestamoDaoImpl.getObjeto()]");
		PrestamoPojo prestamoPojo = new PrestamoPojo();

		String query = " select * from prestamo where idPrestamo=" + id.toString()
				+ ";";
		logger.info("->> " + query);
		try {
			connection = getConex();
			statement = connection.createStatement();
			result = statement.executeQuery(query);

			result.next();

			prestamoPojo.setIdPrestamo(result.getInt("idPrestamo"));
			prestamoPojo.setFechaPrestamo(result.getDate("fechaPrestamo"));
			prestamoPojo.setFechaEntrega(result.getDate("fechaEntrega"));
			prestamoPojo.setBoleta(result.getString("boleta"));
			prestamoPojo.setIdLibro(result.getInt("idLibro"));
			
			prestamoPojo.setDevuelto(result.getBoolean("devuelto"));
			closeConnection();

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			logger.info(">> Error code =  " + e.getErrorCode() + " <<");
			e.printStackTrace();
		} catch (Exception e) {
			logger.info(e.getMessage());
		}
		return prestamoPojo;
	}

	@Override
	public void addObjeto(Object o) {
		logger.info("[Entrada al metodo PrestamoDaoImpl.addObjeto()]");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		PrestamoPojo prestamoPojo = (PrestamoPojo) o;
		String query =
				"insert into prestamo values (" + 
				"'"+ sdf.format(prestamoPojo.getFechaPrestamo()) +"'"	 + "," + "'"
				+ sdf.format(prestamoPojo.getFechaEntrega())+ "'," + "'" 
				+ prestamoPojo.getBoleta() + "'," + "'"
				+ prestamoPojo.getIdLibro() + "', 'False');";

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
		logger.info("[Entrada al metodo PrestamoDaoImpl.editObjeto()]");

		PrestamoPojo prestamoPojo = (PrestamoPojo) o;

		String query = "";
		
		logger.info("->> " + query);
		try {
			connection = getConex();
			statement = connection.createStatement();
			result = statement.executeQuery(query);
			result.next();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");

			query="update prestamo set "+ 
					"idLibro="+prestamoPojo.getIdLibro()+", "+
					"fechaEntrega='"+sdf.format(prestamoPojo.getFechaEntrega())+"', "+
					"devuelto='"+prestamoPojo.getDevuelto()+"';";
					 
					
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
		logger.info("[Entrada al metodo PrestamoDaoImpl.deleteObjeto()]");
		String query = "DELETE FROM prestamo WHERE idPrestamo="
				+ (Integer) id;
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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void customElimina(String qry) {
		// TODO Auto-generated method stub

	}
	public Integer countLibrosPrestados(Integer idLibro)
	{
		logger.info("[Entrada al metodo PrestamoDaoImpl.countPrestamos()]");
		String query = "select COUNT(*) as 'librosPrestados' from prestamo where idLibro="+  idLibro+ " and devuelto='False';";
		Integer librosPrestados=0;
		logger.info("->> " + query);
		try {
			connection = getConex();
			statement = connection.createStatement();
			result = statement.executeQuery(query);
			result.next();
			librosPrestados=result.getInt("librosPrestados");
			closeConnection();

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			logger.info(">> Error code =  " + e.getErrorCode() + " <<");
			e.printStackTrace();
		} catch (Exception e) {
			logger.info(e.getMessage());
		}
		return librosPrestados;
	}

}
