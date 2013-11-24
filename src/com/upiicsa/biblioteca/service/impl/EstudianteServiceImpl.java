package com.upiicsa.biblioteca.service.impl;

import java.util.List;

import com.upiicsa.biblioteca.dao.impl.EstudianteDaoImpl;
import com.upiicsa.biblioteca.service.AbstractService;

public class EstudianteServiceImpl implements AbstractService {
	private EstudianteDaoImpl estudianteDaoImpl;

	public EstudianteServiceImpl() {
		estudianteDaoImpl = new EstudianteDaoImpl();
	}

	@Override
	public List<?> obtenerListado() {

		return estudianteDaoImpl.getListado();
	}

	@Override
	public Object obtenerObjeto(Object id) {
	
		return estudianteDaoImpl.getObjeto(id);
	}

	@Override
	public void agregarObjeto(Object o) {
		estudianteDaoImpl.addObjeto(o);

	}

	@Override
	public void editarObjeto(Object o) {
		estudianteDaoImpl.editObjeto(o);

	}

	@Override
	public void borrarObjeto(Object id) {
		estudianteDaoImpl.deleteObjeto(id);

	}

	@Override
	public List<?> listadoPersonalizado(String cond) {
		return estudianteDaoImpl.customListado(cond);
	
	}

	@Override
	public void eliminaPersonalizado(String qry) {
		estudianteDaoImpl.customElimina(qry);
	}

	
}
