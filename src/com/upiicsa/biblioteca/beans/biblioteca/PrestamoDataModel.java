package com.upiicsa.biblioteca.beans.biblioteca;

import java.util.List;

import javax.faces.model.ListDataModel;

import org.primefaces.model.SelectableDataModel;

import com.upiicsa.biblioteca.model.PrestamoPojo;
import com.upiicsa.biblioteca.service.impl.PrestamoServiceImpl;

public class PrestamoDataModel extends ListDataModel<PrestamoPojo> implements SelectableDataModel <PrestamoPojo>{

	public PrestamoDataModel() {
		// TODO Auto-generated constructor stub
	}
	public PrestamoDataModel(List<PrestamoPojo>data)
	{
		super(data);
		
	}
	@Override
	public PrestamoPojo getRowData(String id) {
		PrestamoServiceImpl prestamoServiceImpl=new PrestamoServiceImpl();
		return (PrestamoPojo)prestamoServiceImpl.obtenerObjeto(id);
	}

	@Override
	public Object getRowKey(PrestamoPojo prestamoPojo) {
		// TODO Auto-generated method stub
		return prestamoPojo.getIdPrestamo();
	}

}
