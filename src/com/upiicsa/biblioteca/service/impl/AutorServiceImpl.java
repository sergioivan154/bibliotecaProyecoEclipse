package com.upiicsa.biblioteca.service.impl;

import java.util.List;

import com.upiicsa.biblioteca.dao.impl.AutorDaoImpl;
import com.upiicsa.biblioteca.service.AbstractService;

public class AutorServiceImpl implements AbstractService {
	private AutorDaoImpl autorDaoImpl;

	public AutorServiceImpl() {
		autorDaoImpl = new AutorDaoImpl();
	}

	@Override
	public List<?> obtenerListado() {

		return autorDaoImpl.getListado();
	}

	@Override
	public Object obtenerObjeto(Object id) {
	
		return autorDaoImpl.getObjeto(id);
	}

	@Override
	public void agregarObjeto(Object o) {
		autorDaoImpl.addObjeto(o);

	}

	@Override
	public void editarObjeto(Object o) {
		autorDaoImpl.editObjeto(o);

	}

	@Override
	public void borrarObjeto(Object id) {
		autorDaoImpl.deleteObjeto(id);

	}

	@Override
	public List<?> listadoPersonalizado(String cond) {
		return autorDaoImpl.customListado(cond);
	
	}

	@Override
	public void eliminaPersonalizado(String qry) {
		autorDaoImpl.customElimina(qry);
	}

	
}
