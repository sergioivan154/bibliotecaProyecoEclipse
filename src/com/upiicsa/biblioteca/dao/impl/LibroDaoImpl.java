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
import com.upiicsa.biblioteca.model.LibroPojo;

public class LibroDaoImpl extends FactoryDaoImpl implements AbstractFacade {
	Log logger = LogFactory.getLog(LibroDaoImpl.class);
	private Connection connection;
	private Statement statement;
	private ResultSet result;

	@Override
	public List<?> getListado() {
		logger.info("[Entrando al método LibroDaoImpl.getListado]");

		List<LibroPojo> listaLibrosPojo = null;
		
		LibroPojo libroPojo = null;
		String query;

		query = "  select a.*, c.nombre as 'autor' from libro a join libAut b on a.idLibro=b.idLibro join autor c on c.idAutor=b.idAutor;";
		logger.info(query + "::..");
		try {
			connection = getConex();
			statement = connection.createStatement();
			result = statement.executeQuery(query);

			listaLibrosPojo = new ArrayList<LibroPojo>();

			while (result.next()) {

				libroPojo = new LibroPojo();
				libroPojo.setIdLibro(result.getInt("idLibro"));
				libroPojo.setTitulo(result.getString("titulo"));
				libroPojo.setEditorial(result.getString("editorial"));
				libroPojo.setArea(result.getString("area"));
				libroPojo.setUbicacion(result.getString("ubicacion"));
				libroPojo.setExistencia(result.getInt("existencia"));
				libroPojo.setAutor(result.getString("autor"));
				listaLibrosPojo.add(libroPojo);
			}
			System.out.println(listaLibrosPojo);
			closeConnection();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {

			logger.info(">> Error code =  " + e.getErrorCode() + " <<");
		} catch (Exception e) {
			logger.info("Error tipo general : " + e.getMessage());
		}

		return listaLibrosPojo;
	}

	@Override
	public Object getObjeto(Object id) {
		logger.info("[Entrada al metodo LibroDaoImpl.getObjeto()]");
		LibroPojo libroPojo = new LibroPojo();

		String query = " select  a.*,c.nombre as 'autor' from libro a join libAut b on a.idLibro=b.idLibro join autor c on c.idAutor=b.idAutor where a.idLibro=" + id.toString()
				+ ";";
		logger.info("->> " + query);
		try {
			connection = getConex();
			statement = connection.createStatement();
			result = statement.executeQuery(query);

			result.next();

			libroPojo.setIdLibro(result.getInt("idLibro"));
			libroPojo.setTitulo(result.getString("titulo"));
			libroPojo.setEditorial(result.getString("editorial"));
			libroPojo.setArea(result.getString("area"));
			libroPojo.setUbicacion(result.getString("ubicacion"));
			libroPojo.setExistencia(result.getInt("existencia"));
			libroPojo.setAutor(result.getString("autor"));
			closeConnection();

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			logger.info(">> Error code =  " + e.getErrorCode() + " <<");
			e.printStackTrace();
		} catch (Exception e) {
			logger.info(e.getMessage());
		}
		return libroPojo;
	}

	@Override
	public void addObjeto(Object o) {
		logger.info("[Entrada al metodo LibroDaoImpl.addObjeto()]");

		LibroPojo libroPojo = (LibroPojo) o;
		String query =
				"insert into libro values (" + 
		  "'"	+ libroPojo.getTitulo() + "'," + "'"
				+ libroPojo.getEditorial()+ "'," + "'" 
				+ libroPojo.getArea() + "'," + "'"
				+ libroPojo.getUbicacion() + "'," + ""
				+ libroPojo.getExistencia() + ");";

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
		logger.info("[Entrada al metodo LibroDaoImpl.editObjeto()]");

		LibroPojo libroPojo = (LibroPojo) o;

		String query = "";
		
		logger.info("->> " + query);
		try {
			connection = getConex();
			statement = connection.createStatement();
			result = statement.executeQuery(query);
			result.next();

			query="update libro set "+ 
					"titulo='"+libroPojo.getTitulo()+"', "+
					"editorial='"+libroPojo.getEditorial()+"', "+
					"area='"+libroPojo.getArea()+"', "+
					"ubicacion='"+libroPojo.getUbicacion()+"', "+
					"existencia="+libroPojo.getExistencia()+" "+
					"where idLibro="+libroPojo.getIdLibro()+";";
					 
					
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
		logger.info("[Entrada al metodo LibroDaoImpl.deleteObjeto()]");
		String query = "DELETE FROM libro WHERE idLibro="
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
		logger.info("[Entrando al método LibroDaoImpl.getListado]");

		List<LibroPojo> listaLibrosPojo = null;
		
		LibroPojo libroPojo = null;
		String query;

		query = "  select a.*, c.nombre as 'autor' from libro a join libAut b on a.idLibro=b.idLibro join autor c on c.idAutor=b.idAutor where a.titulo like '%"+cond+"%' order by c.nombre; ";
		logger.info(query + "::..");
		try {
			connection = getConex();
			statement = connection.createStatement();
			result = statement.executeQuery(query);

			listaLibrosPojo = new ArrayList<LibroPojo>();

			while (result.next()) {

				libroPojo = new LibroPojo();
				libroPojo.setIdLibro(result.getInt("idLibro"));
				libroPojo.setTitulo(result.getString("titulo"));
				libroPojo.setEditorial(result.getString("editorial"));
				libroPojo.setArea(result.getString("area"));
				libroPojo.setUbicacion(result.getString("ubicacion"));
				libroPojo.setExistencia(result.getInt("existencia"));
				libroPojo.setAutor(result.getString("autor"));
				listaLibrosPojo.add(libroPojo);
			}
			System.out.println(listaLibrosPojo);
			closeConnection();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {

			logger.info(">> Error code =  " + e.getErrorCode() + " <<");
		} catch (Exception e) {
			logger.info("Error tipo general : " + e.getMessage());
		}

		return listaLibrosPojo;

	}
	
	public List<?> getListaLibrosPorAutor(Integer cond)
	{
		logger.info("[Entrando al método LibroDaoImpl.getListado]");

		List<LibroPojo> listaLibrosPojo = null;
		
		LibroPojo libroPojo = null;
		String query;

		query = "  select a.*, c.nombre as 'autor' from libro a join libAut b on a.idLibro=b.idLibro join autor c on c.idAutor=b.idAutor where c.idAutor="+cond+" order by c.nombre; ";
		logger.info(query + "::..");
		try {
			connection = getConex();
			statement = connection.createStatement();
			result = statement.executeQuery(query);

			listaLibrosPojo = new ArrayList<LibroPojo>();

			while (result.next()) {

				libroPojo = new LibroPojo();
				libroPojo.setIdLibro(result.getInt("idLibro"));
				libroPojo.setTitulo(result.getString("titulo"));
				libroPojo.setEditorial(result.getString("editorial"));
				libroPojo.setArea(result.getString("area"));
				libroPojo.setUbicacion(result.getString("ubicacion"));
				libroPojo.setExistencia(result.getInt("existencia"));
				libroPojo.setAutor(result.getString("autor"));
				listaLibrosPojo.add(libroPojo);
			}
			System.out.println(listaLibrosPojo);
			closeConnection();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {

			logger.info(">> Error code =  " + e.getErrorCode() + " <<");
		} catch (Exception e) {
			logger.info("Error tipo general : " + e.getMessage());
		}

		return listaLibrosPojo;
	}
	public List<?> getListaLibrosPorTitulo(Integer cond) {
		logger.info("[Entrando al método LibroDaoImpl.getListado]");

		List<LibroPojo> listaLibrosPojo = null;
		
		LibroPojo libroPojo = null;
		String query;

		query = "  select a.*, c.nombre as 'autor' from libro a join libAut b on a.idLibro=b.idLibro join autor c on c.idAutor=b.idAutor where a.idLibro="+cond+" order by a.titulo; ";
		logger.info(query + "::..");
		try {
			connection = getConex();
			statement = connection.createStatement();
			result = statement.executeQuery(query);

			listaLibrosPojo = new ArrayList<LibroPojo>();

			while (result.next()) {

				libroPojo = new LibroPojo();
				libroPojo.setIdLibro(result.getInt("idLibro"));
				libroPojo.setTitulo(result.getString("titulo"));
				libroPojo.setEditorial(result.getString("editorial"));
				libroPojo.setArea(result.getString("area"));
				libroPojo.setUbicacion(result.getString("ubicacion"));
				libroPojo.setExistencia(result.getInt("existencia"));
				libroPojo.setAutor(result.getString("autor"));
				listaLibrosPojo.add(libroPojo);
			}
			System.out.println(listaLibrosPojo);
			closeConnection();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {

			logger.info(">> Error code =  " + e.getErrorCode() + " <<");
		} catch (Exception e) {
			logger.info("Error tipo general : " + e.getMessage());
		}

		return listaLibrosPojo;
	}
	@Override
	public void customElimina(String qry) {
		// TODO Auto-generated method stub

	}

}
