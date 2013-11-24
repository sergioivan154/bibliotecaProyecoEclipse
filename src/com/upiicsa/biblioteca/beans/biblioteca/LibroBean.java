package com.upiicsa.biblioteca.beans.biblioteca;

import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.primefaces.context.RequestContext;

import com.upiicsa.biblioteca.model.LibroPojo;
import com.upiicsa.biblioteca.service.impl.LibroServiceImpl;

@ManagedBean(name = "libroBean")
@SuppressWarnings("unchecked")
@SessionScoped
public class LibroBean {
	
	
	private LibroPojo libroPojo ;
	
	private LibroServiceImpl libroServiceImpl;
	private LibroDataModel dataModel;
	private List<LibroPojo> libroSeleccion;
	private List<LibroPojo> listaLibros;
	public LibroBean() {
		
		libroPojo = new LibroPojo();
		libroServiceImpl = new LibroServiceImpl();
		dataModel = new LibroDataModel(
				(List<LibroPojo>) libroServiceImpl
						.obtenerListado());
		listaLibros = (List<LibroPojo>) new LibroServiceImpl()
		.obtenerListado();
		System.out.print(listaLibros.size());
	
	}
	// > Altas <
	public String insertar() {
		FacesContext fContext = FacesContext.getCurrentInstance();
		libroServiceImpl.agregarObjeto(libroPojo);
		fContext.addMessage("growl", new FacesMessage(
				"Libro registrado correctamente."));
		libroPojo = new LibroPojo();
		dataModel = new LibroDataModel(
				(List<LibroPojo>) libroServiceImpl.obtenerListado());
		return null;
	}
// ---> Bajas <---
public String borrar() {
	FacesContext fContext = FacesContext.getCurrentInstance();
	for (LibroPojo p : libroSeleccion) {
		libroServiceImpl.borrarObjeto(p.getIdLibro());
	}
	libroSeleccion = new ArrayList<>();
	dataModel = new LibroDataModel(
			(List<LibroPojo>) libroServiceImpl
					.obtenerListado());
	fContext.addMessage(
			"growl",
			new FacesMessage(
					"Libro(s)  eliminado(s) correctamente."));
	return null;
}

// ---> Cambios <---
public String actualiza() {
	RequestContext context = RequestContext.getCurrentInstance();
	FacesContext fContext = FacesContext.getCurrentInstance();
	for (LibroPojo p : libroSeleccion) {
		libroServiceImpl.editarObjeto(p);
	}
	libroSeleccion = new ArrayList<>();
	context.execute("editDialog.hide()");
	fContext.addMessage(
			"growl",
			new FacesMessage(
					"Libro(s)  eliminado(s) actualizado(s) correctamente."));
	dataModel = new LibroDataModel(
			(List<LibroPojo>) libroServiceImpl
					.obtenerListado());
	return null;
}
//---> funcion para llenado dinamico de campos desactivados <---
	public void recarga() {
		
	/*	ReglamentoServiceImpl impl = new ReglamentoServiceImpl();
		reglamento = (ReglamentoPojo) impl.obtenerObjeto(libroPojo
				.getIdReglamento());*/
		
	}
	public LibroPojo getLibroPojo() {
		return libroPojo;
	}
	public void setLibroPojo(LibroPojo libroPojo) {
		this.libroPojo = libroPojo;
	}
	public LibroServiceImpl getLibroServiceImpl() {
		return libroServiceImpl;
	}
	public void setLibroServiceImpl(LibroServiceImpl libroServiceImpl) {
		this.libroServiceImpl = libroServiceImpl;
	}
	public LibroDataModel getDataModel() {
		return dataModel;
	}
	public void setDataModel(LibroDataModel dataModel) {
		this.dataModel = dataModel;
	}
	public List<LibroPojo> getLibroSeleccion() {
		return libroSeleccion;
	}
	public void setLibroSeleccion(List<LibroPojo> libroSeleccion) {
		this.libroSeleccion = libroSeleccion;
	}
	public List<LibroPojo> getListaLibros() {
		return listaLibros;
	}
	public void setListaLibros(List<LibroPojo> listaLibros) {
		this.listaLibros = listaLibros;
	}
	
	
}
