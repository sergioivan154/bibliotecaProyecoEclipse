package com.upiicsa.biblioteca.beans.biblioteca;

import java.util.List;

import javax.faces.model.ListDataModel;

import org.primefaces.model.SelectableDataModel;

import com.upiicsa.biblioteca.model.LibroPojo;
import com.upiicsa.biblioteca.service.impl.LibroServiceImpl;


public class LibroDataModel extends  ListDataModel<LibroPojo> implements SelectableDataModel <LibroPojo> {

	public LibroDataModel(){
	}
	public LibroDataModel(List<LibroPojo>data)
	{
		super(data);
	}
	@Override
	public LibroPojo getRowData(String id) {
		LibroServiceImpl serviceImpl=new LibroServiceImpl();
		return (LibroPojo)serviceImpl.obtenerObjeto(id);
	}

	@Override
	public Object getRowKey(LibroPojo libroPojo) {
		return libroPojo.getIdLibro();
	}

}
