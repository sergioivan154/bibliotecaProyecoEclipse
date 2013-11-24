package com.upiicsa.biblioteca.service.impl;

import java.util.List;

import com.upiicsa.biblioteca.dao.impl.LibroDaoImpl;
import com.upiicsa.biblioteca.service.AbstractService;


public class LibroServiceImpl implements AbstractService {

	private LibroDaoImpl libroDaoImpl;

	public LibroServiceImpl() {
		libroDaoImpl = new LibroDaoImpl();
	}

	@Override
	public List<?> obtenerListado() {

		return libroDaoImpl.getListado();
	}

	@Override
	public Object obtenerObjeto(Object id) {
	
		return libroDaoImpl.getObjeto(id);
	}

	@Override
	public void agregarObjeto(Object o) {
		libroDaoImpl.addObjeto(o);

	}

	@Override
	public void editarObjeto(Object o) {
		libroDaoImpl.editObjeto(o);

	}

	@Override
	public void borrarObjeto(Object id) {
		libroDaoImpl.deleteObjeto(id);

	}

	@Override
	public List<?> listadoPersonalizado(String cond) {
		return libroDaoImpl.customListado(cond);
	
	}

	@Override
	public void eliminaPersonalizado(String qry) {
		libroDaoImpl.customElimina(qry);
	}
	public List<?> obtenerListaLibrosPorAutor(Integer id)
	{
		return libroDaoImpl.getListaLibrosPorAutor(id);
	}
	public List<?> obtenerListaLibrosPorTitulo(Integer id)
	{
		return libroDaoImpl.getListaLibrosPorTitulo(id);
	}

}
