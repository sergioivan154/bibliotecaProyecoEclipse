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
import com.upiicsa.biblioteca.model.EstudiantePojo;

public class EstudianteDaoImpl extends FactoryDaoImpl implements AbstractFacade {

	Log logger = LogFactory.getLog(EstudianteDaoImpl.class);
	private Connection connection;
	private Statement statement;
	private ResultSet result;

	@Override
	public List<?> getListado() {
		logger.info("[Entrando al método EstudianteDaoImpl.getListado]");

		List<EstudiantePojo> listaEstudiantesPojo = null;

		EstudiantePojo estudiantePojo = null;
		String query;

		query = " select * from estudiante;";
		logger.info(query + "::..");
		try {
			connection = getConex();
			statement = connection.createStatement();
			result = statement.executeQuery(query);

			listaEstudiantesPojo = new ArrayList<EstudiantePojo>();

			while (result.next()) {

				estudiantePojo = new EstudiantePojo();
				estudiantePojo.setBoleta(result.getString("boleta"));
				estudiantePojo.setNombre(result.getString("nombre"));
				estudiantePojo.setDireccion(result.getString("direccion"));
				estudiantePojo.setCarrera(result.getString("carrera"));
				estudiantePojo.setEdad(result.getString("edad"));
				estudiantePojo.setSemestre(result.getString("semestre"));
				estudiantePojo.setInscrito(result.getBoolean("inscrito"));

				listaEstudiantesPojo.add(estudiantePojo);
			}

			closeConnection();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {

			logger.info(">> Error code =  " + e.getErrorCode() + " <<");
		} catch (Exception e) {
			logger.info("Error tipo general : " + e.getMessage());
		}

		return listaEstudiantesPojo;
	}

	@Override
	public Object getObjeto(Object id) {
		logger.info("[Entrada al metodo EstudianteDaoImpl.getObjeto()]");
		EstudiantePojo estudiantePojo = new EstudiantePojo();

		String query = " select * from estudiante where boleta='" + id.toString()
				+ "';";
		logger.info("->> " + query);
		try {
			connection = getConex();
			statement = connection.createStatement();
			result = statement.executeQuery(query);

			result.next();

			estudiantePojo.setBoleta(result.getString("boleta"));
			estudiantePojo.setNombre(result.getString("nombre"));
			estudiantePojo.setDireccion(result.getString("direccion"));
			estudiantePojo.setCarrera(result.getString("carrera"));
			estudiantePojo.setEdad(result.getString("edad"));
			estudiantePojo.setSemestre(result.getString("semestre"));
			estudiantePojo.setInscrito(result.getBoolean("inscrito"));
			closeConnection();

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			logger.info(">> Error code =  " + e.getErrorCode() + " <<");
			e.printStackTrace();
		} catch (Exception e) {
			logger.info(e.getMessage());
		}
		return estudiantePojo;
	}

	@Override
	public void addObjeto(Object o) {
		logger.info("[Entrada al metodo EstudianteDaoImpl.addObjeto()]");

		EstudiantePojo estudiantePojo = (EstudiantePojo) o;
		String query =
				"insert into estudiante values (" + 
		  "'"	+ estudiantePojo.getNombre() + "'," + "'"
				+ estudiantePojo.getDireccion()+ "'," + "'" 
				+ estudiantePojo.getCarrera() + "'," + ""
				+ estudiantePojo.getEdad() + "," + "'"
				+ estudiantePojo.getInscrito() + "'"
				+ estudiantePojo.getSemestre()+
						"');";

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
		logger.info("[Entrada al metodo EstudianteDaoImpl.editObjeto()]");

		EstudiantePojo estudiantePojo = (EstudiantePojo) o;

		String query = "";
		
		logger.info("->> " + query);
		try {
			connection = getConex();
			statement = connection.createStatement();
			result = statement.executeQuery(query);
			result.next();

			query="update estudiante set "+ 
					"nombre='"+estudiantePojo.getNombre()+"', "+
					"direccion='"+estudiantePojo.getDireccion()+"', "+
					"carrera='"+estudiantePojo.getCarrera()+"', "+
					"edad="+estudiantePojo.getEdad()+", "+
					"inscrito='"+estudiantePojo.getInscrito()+"', " +
					"semestre="+estudiantePojo.getSemestre()+
					"where boleta='"+estudiantePojo.getBoleta()+"';";
					 
					
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
		logger.info("[Entrada al metodo EstudianteDaoImpl.deleteObjeto()]");
		String query = "DELETE FROM estudiante WHERE boleta="
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
		logger.info("[Entrando al método EstudianteDaoImpl.getListado]");

		List<EstudiantePojo> listaEstudiantesPojo = null;

		EstudiantePojo estudiantePojo = null;
		String query;

		query = " select * from estudiante where boleta='"+cond+"';";
		logger.info(query + "::..");
		try {
			connection = getConex();
			statement = connection.createStatement();
			result = statement.executeQuery(query);

			listaEstudiantesPojo = new ArrayList<EstudiantePojo>();

			while (result.next()) {

				estudiantePojo = new EstudiantePojo();
				estudiantePojo.setBoleta(result.getString("boleta"));
				estudiantePojo.setNombre(result.getString("nombre"));
				estudiantePojo.setDireccion(result.getString("direccion"));
				estudiantePojo.setCarrera(result.getString("carrera"));
				estudiantePojo.setEdad(result.getString("edad"));
				estudiantePojo.setSemestre(result.getString("semestre"));
				estudiantePojo.setInscrito(result.getBoolean("inscrito"));

				listaEstudiantesPojo.add(estudiantePojo);
			}

			closeConnection();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {

			logger.info(">> Error code =  " + e.getErrorCode() + " <<");
		} catch (Exception e) {
			logger.info("Error tipo general : " + e.getMessage());
		}

		return listaEstudiantesPojo;
	}

	@Override
	public void customElimina(String qry) {
		// TODO Auto-generated method stub

	}

}
