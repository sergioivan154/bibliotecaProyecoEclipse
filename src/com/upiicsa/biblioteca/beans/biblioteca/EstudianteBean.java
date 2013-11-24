package com.upiicsa.biblioteca.beans.biblioteca;

import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.primefaces.context.RequestContext;

import com.upiicsa.biblioteca.model.EstudiantePojo;
import com.upiicsa.biblioteca.service.impl.EstudianteServiceImpl;

@ManagedBean(name = "estudianteBean")
@SuppressWarnings("unchecked")
@SessionScoped
public class EstudianteBean{
	
	
	private EstudiantePojo estudiantePojo ;
	
	private EstudianteServiceImpl estudianteServiceImpl;
	private EstudianteDataModel dataModel;
	private List<EstudiantePojo> estudianteSeleccion;
	private List<EstudiantePojo> listaEstudiantes;
	public EstudianteBean() {
		
		estudiantePojo = new EstudiantePojo();
		estudianteServiceImpl = new EstudianteServiceImpl();
//		dataModel = new EstudianteDataModel(
//				(List<EstudiantePojo>) estudianteServiceImpl
//						.obtenerListado());
		listaEstudiantes = (List<EstudiantePojo>) new EstudianteServiceImpl()
		.obtenerListado();
		System.out.print(listaEstudiantes.size());
	
	}
	// > Altas <
	public String insertar() {
		FacesContext fContext = FacesContext.getCurrentInstance();
		estudianteServiceImpl.agregarObjeto(estudiantePojo);
		fContext.addMessage("growl", new FacesMessage(
				"Libro registrado correctamente."));
		estudiantePojo = new EstudiantePojo();
		dataModel = new EstudianteDataModel(
				(List<EstudiantePojo>) estudianteServiceImpl.obtenerListado());
		return null;
	}
// ---> Bajas <---
public String borrar() {
	FacesContext fContext = FacesContext.getCurrentInstance();
	for (EstudiantePojo p : estudianteSeleccion) {
		estudianteServiceImpl.borrarObjeto(p.getBoleta());
	}
	estudianteSeleccion = new ArrayList<>();
	dataModel = new EstudianteDataModel(
			(List<EstudiantePojo>) estudianteServiceImpl
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
	for (EstudiantePojo p : estudianteSeleccion) {
		estudianteServiceImpl.editarObjeto(p);
	}
	estudianteSeleccion = new ArrayList<>();
	context.execute("editDialog.hide()");
	fContext.addMessage(
			"growl",
			new FacesMessage(
					"Libro(s)  eliminado(s) actualizado(s) correctamente."));
	dataModel = new EstudianteDataModel(
			(List<EstudiantePojo>) estudianteServiceImpl
					.obtenerListado());
	return null;
}
//---> funcion para llenado dinamico de campos desactivados <---
	public void recarga() {
		
	/*	ReglamentoServiceImpl impl = new ReglamentoServiceImpl();
		reglamento = (ReglamentoPojo) impl.obtenerObjeto(estudiantePojo
				.getIdReglamento());*/
		
	}
	
	public String buscar()
	{
		if (estudiantePojo.getBoleta() != null)

			 estudiantePojo=
					(EstudiantePojo)estudianteServiceImpl
							.obtenerObjeto((estudiantePojo.getBoleta()));
		
		return null;
	}
	public EstudiantePojo getEstudiantePojo() {
		return estudiantePojo;
	}
	public void setEstudiantePojo(EstudiantePojo estudiantePojo) {
		this.estudiantePojo = estudiantePojo;
	}
	public EstudianteServiceImpl getEstudianteServiceImpl() {
		return estudianteServiceImpl;
	}
	public void setEstudianteServiceImpl(EstudianteServiceImpl estudianteServiceImpl) {
		this.estudianteServiceImpl = estudianteServiceImpl;
	}
	public EstudianteDataModel getDataModel() {
		return dataModel;
	}
	public void setDataModel(EstudianteDataModel dataModel) {
		this.dataModel = dataModel;
	}
	public List<EstudiantePojo> getEstudianteSeleccion() {
		return estudianteSeleccion;
	}
	public void setEstudianteSeleccion(List<EstudiantePojo> estudianteSeleccion) {
		this.estudianteSeleccion = estudianteSeleccion;
	}
	public List<EstudiantePojo> getListaEstudiantes() {
		return listaEstudiantes;
	}
	public void setListaEstudiantes(List<EstudiantePojo> listaEstudiantes) {
		this.listaEstudiantes = listaEstudiantes;
	}

}
