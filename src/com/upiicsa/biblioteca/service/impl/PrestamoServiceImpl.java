package com.upiicsa.biblioteca.service.impl;

import java.util.List;

import com.upiicsa.biblioteca.dao.impl.PrestamoDaoImpl;
import com.upiicsa.biblioteca.service.AbstractService;


public class PrestamoServiceImpl implements AbstractService {

	private PrestamoDaoImpl prestamoDaoImpl;

	public PrestamoServiceImpl() {
		prestamoDaoImpl = new PrestamoDaoImpl();
	}

	@Override
	public List<?> obtenerListado() {

		return prestamoDaoImpl.getListado();
	}

	@Override
	public Object obtenerObjeto(Object id) {
	
		return prestamoDaoImpl.getObjeto(id);
	}

	@Override
	public void agregarObjeto(Object o) {
		prestamoDaoImpl.addObjeto(o);

	}

	@Override
	public void editarObjeto(Object o) {
		prestamoDaoImpl.editObjeto(o);

	}

	@Override
	public void borrarObjeto(Object id) {
		prestamoDaoImpl.deleteObjeto(id);

	}

	@Override
	public List<?> listadoPersonalizado(String cond) {
		return prestamoDaoImpl.customListado(cond);
	
	}

	@Override
	public void eliminaPersonalizado(String qry) {
		prestamoDaoImpl.customElimina(qry);
	}
	
	public Integer contarLibrosPrestados(Integer idLibro)
	{
		return prestamoDaoImpl.countLibrosPrestados(idLibro);
	}


}
