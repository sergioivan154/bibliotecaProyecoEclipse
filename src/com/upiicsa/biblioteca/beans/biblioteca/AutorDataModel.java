package com.upiicsa.biblioteca.beans.biblioteca;

import java.util.List;

import javax.faces.model.ListDataModel;

import org.primefaces.model.SelectableDataModel;

import com.upiicsa.biblioteca.model.AutorPojo;
import com.upiicsa.biblioteca.service.impl.AutorServiceImpl;

public class AutorDataModel extends ListDataModel<AutorPojo> implements SelectableDataModel <AutorPojo>{

	public AutorDataModel() {
		// TODO Auto-generated constructor stub
	}
	public AutorDataModel(List<AutorPojo>data)
	{
		super(data);
		
	}
	@Override
	public AutorPojo getRowData(String id) {
		AutorServiceImpl autorServiceImpl=new AutorServiceImpl();
		return (AutorPojo)autorServiceImpl.obtenerObjeto(id);
	}

	@Override
	public Object getRowKey(AutorPojo autorPojo) {
		// TODO Auto-generated method stub
		return autorPojo.getIdAutor();
	}

}
