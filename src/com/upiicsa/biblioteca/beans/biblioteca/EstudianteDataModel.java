package com.upiicsa.biblioteca.beans.biblioteca;

import java.util.List;

import javax.faces.model.ListDataModel;

import org.primefaces.model.SelectableDataModel;

import com.upiicsa.biblioteca.model.EstudiantePojo;
import com.upiicsa.biblioteca.service.impl.EstudianteServiceImpl;

public class EstudianteDataModel extends ListDataModel<EstudiantePojo> implements SelectableDataModel <EstudiantePojo>{

	public EstudianteDataModel() {
		// TODO Auto-generated constructor stub
	}
	public EstudianteDataModel(List<EstudiantePojo>data)
	{
		super(data);
		
	}
	@Override
	public EstudiantePojo getRowData(String id) {
		EstudianteServiceImpl estudianteServiceImpl=new EstudianteServiceImpl();
		return (EstudiantePojo)estudianteServiceImpl.obtenerObjeto(id);
	}

	@Override
	public Object getRowKey(EstudiantePojo estudiantePojo) {
		// TODO Auto-generated method stub
		return estudiantePojo.getBoleta();
	}

}
