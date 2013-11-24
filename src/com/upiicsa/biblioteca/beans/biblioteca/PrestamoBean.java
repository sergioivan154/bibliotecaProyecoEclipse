package com.upiicsa.biblioteca.beans.biblioteca;

import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.primefaces.context.RequestContext;

import com.upiicsa.biblioteca.model.AutorPojo;
import com.upiicsa.biblioteca.model.EstudiantePojo;
import com.upiicsa.biblioteca.model.LibroPojo;
import com.upiicsa.biblioteca.model.PrestamoPojo;
import com.upiicsa.biblioteca.service.impl.AutorServiceImpl;
import com.upiicsa.biblioteca.service.impl.EstudianteServiceImpl;
import com.upiicsa.biblioteca.service.impl.LibroServiceImpl;
import com.upiicsa.biblioteca.service.impl.PrestamoServiceImpl;

@ManagedBean(name = "prestamoBean")
@SuppressWarnings("unchecked")
@SessionScoped
public class PrestamoBean {

	private PrestamoPojo prestamoPojo;
	private LibroPojo libroPojo;
	private AutorPojo autorPojo;
	private PrestamoServiceImpl prestamoServiceImpl;
	private LibroServiceImpl libroServiceImpl;
	private PrestamoDataModel dataModel;

	private LibroDataModel libroDataModel;
	private List<PrestamoPojo> prestamoSeleccion;
	private List<LibroPojo> libroSeleccion;

	private List<LibroPojo> listaLibros;
	private List<AutorPojo> listaAutores;

	private EstudiantePojo estudiantePojo;

	private EstudianteServiceImpl estudianteServiceImpl;
	private List<EstudiantePojo> estudianteSeleccion;

	public PrestamoBean() {

		libroPojo = new LibroPojo();
		autorPojo = new AutorPojo();
		estudiantePojo = new EstudiantePojo();
		estudianteServiceImpl = new EstudianteServiceImpl();
		prestamoPojo = new PrestamoPojo();
		prestamoServiceImpl = new PrestamoServiceImpl();

		libroPojo = new LibroPojo();
		libroServiceImpl = new LibroServiceImpl();

		listaLibros = (List<LibroPojo>) new LibroServiceImpl().obtenerListado();
		listaAutores = (List<AutorPojo>) new AutorServiceImpl()
				.obtenerListado();
		libroDataModel = new LibroDataModel(
				(List<LibroPojo>) libroServiceImpl.obtenerListado());

	}

	public String buscarEstudiante() {
		if (estudiantePojo.getBoleta() != null)

			estudiantePojo = (EstudiantePojo) estudianteServiceImpl
					.obtenerObjeto((estudiantePojo.getBoleta()));

		return null;
	}

	// > Altas <
	public String insertar() {
		FacesContext fContext = FacesContext.getCurrentInstance();
		System.out.println(estudiantePojo.getBoleta() );
		
		System.out.println(libroSeleccion.size() );
		System.out.println( prestamoPojo.getFechaEntrega() );
		System.out.println(prestamoPojo.getFechaPrestamo() );
		if (estudiantePojo.getBoleta() != null && libroSeleccion.size() > 0 && prestamoPojo.getFechaEntrega()!=null && prestamoPojo.getFechaPrestamo()!=null) {
			prestamoPojo.setBoleta(estudiantePojo.getBoleta());
			for (LibroPojo pojo : libroSeleccion) {
				if (prestamoServiceImpl
						.contarLibrosPrestados(pojo.getIdLibro()) < pojo
						.getExistencia()) {
					prestamoPojo.setIdLibro(pojo.getIdLibro());
					prestamoServiceImpl.agregarObjeto(prestamoPojo);
					fContext.addMessage("growl", new FacesMessage(
							"Libro registrado correctamente: "));

				}
				else
				{
					fContext.addMessage("growl", new FacesMessage(
							"Libro agotado: "));
				}
			}
			prestamoPojo = new PrestamoPojo();
			dataModel = new PrestamoDataModel(
					(List<PrestamoPojo>) prestamoServiceImpl.obtenerListado());
		} else {
			fContext.addMessage(
					"growl",
					new FacesMessage(
							"No se pueden apartar libros hasta llenar todos los campos"));
		}
		return null;
	}

	// ---> Bajas <---
	public String borrar() {
		FacesContext fContext = FacesContext.getCurrentInstance();
		for (PrestamoPojo p : prestamoSeleccion) {
			prestamoServiceImpl.borrarObjeto(p.getIdLibro());
		}
		prestamoSeleccion = new ArrayList<>();
		dataModel = new PrestamoDataModel(
				(List<PrestamoPojo>) prestamoServiceImpl.obtenerListado());
		fContext.addMessage("growl", new FacesMessage(
				"Libro(s)  eliminado(s) correctamente."));
		return null;
	}

	// ---> Cambios <---
	public String actualiza() {
		RequestContext context = RequestContext.getCurrentInstance();
		FacesContext fContext = FacesContext.getCurrentInstance();
		for (PrestamoPojo p : prestamoSeleccion) {
			prestamoServiceImpl.editarObjeto(p);
		}
		prestamoSeleccion = new ArrayList<>();
		context.execute("editDialog.hide()");
		fContext.addMessage("growl", new FacesMessage(
				"Libro(s)  eliminado(s) actualizado(s) correctamente."));
		dataModel = new PrestamoDataModel(
				(List<PrestamoPojo>) prestamoServiceImpl.obtenerListado());
		return null;
	}

	// ---> funcion para llenado dinamico de campos desactivados <---
	public String buscarTitulo() {

		if (libroPojo.getIdLibro() != null)
			libroDataModel = new LibroDataModel(
					(List<LibroPojo>) libroServiceImpl
							.obtenerListaLibrosPorTitulo(libroPojo.getIdLibro()));
		System.out.print(libroDataModel);
		return null;

	}

	public String buscarAjax() {

		if (libroPojo.getTitulo() != null)
			libroDataModel = new LibroDataModel(
					(List<LibroPojo>) libroServiceImpl
							.listadoPersonalizado(libroPojo.getTitulo()));
		System.out.print(libroDataModel);
		return null;

	}

	public String buscarAutor() {

		if (autorPojo.getIdAutor() != null)
			libroDataModel = new LibroDataModel(
					(List<LibroPojo>) libroServiceImpl
							.obtenerListaLibrosPorAutor(autorPojo.getIdAutor()));
		System.out.print(libroDataModel);
		return null;

	}

	public String pedirLibro() {
		System.out.println(libroSeleccion);
		FacesContext fContext = FacesContext.getCurrentInstance();

		String prestamo = "";
		if (this.getLibroSeleccion().size() <= 0) {
			fContext.addMessage("growl", new FacesMessage(
					"Seleccione un libro para pedirlo prestado"));
		} else {

			prestamo = "pedirLibro";
		}

		return prestamo;
	}

	public PrestamoPojo getPrestamoPojo() {
		return prestamoPojo;
	}

	public void setPrestamoPojo(PrestamoPojo prestamoPojo) {
		this.prestamoPojo = prestamoPojo;
	}

	public LibroPojo getLibroPojo() {
		return libroPojo;
	}

	public void setLibroPojo(LibroPojo libroPojo) {
		this.libroPojo = libroPojo;
	}

	public AutorPojo getAutorPojo() {
		return autorPojo;
	}

	public void setAutorPojo(AutorPojo autorPojo) {
		this.autorPojo = autorPojo;
	}

	public PrestamoServiceImpl getPrestamoServiceImpl() {
		return prestamoServiceImpl;
	}

	public void setPrestamoServiceImpl(PrestamoServiceImpl prestamoServiceImpl) {
		this.prestamoServiceImpl = prestamoServiceImpl;
	}

	public LibroServiceImpl getLibroServiceImpl() {
		return libroServiceImpl;
	}

	public void setLibroServiceImpl(LibroServiceImpl libroServiceImpl) {
		this.libroServiceImpl = libroServiceImpl;
	}

	public PrestamoDataModel getDataModel() {
		return dataModel;
	}

	public void setDataModel(PrestamoDataModel dataModel) {
		this.dataModel = dataModel;
	}

	public LibroDataModel getLibroDataModel() {
		return libroDataModel;
	}

	public void setLibroDataModel(LibroDataModel libroDataModel) {
		this.libroDataModel = libroDataModel;
	}

	public List<PrestamoPojo> getPrestamoSeleccion() {
		return prestamoSeleccion;
	}

	public void setPrestamoSeleccion(List<PrestamoPojo> prestamoSeleccion) {
		this.prestamoSeleccion = prestamoSeleccion;
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

	public List<AutorPojo> getListaAutores() {
		return listaAutores;
	}

	public void setListaAutores(List<AutorPojo> listaAutores) {
		this.listaAutores = listaAutores;
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

	public void setEstudianteServiceImpl(
			EstudianteServiceImpl estudianteServiceImpl) {
		this.estudianteServiceImpl = estudianteServiceImpl;
	}

	public List<EstudiantePojo> getEstudianteSeleccion() {
		return estudianteSeleccion;
	}

	public void setEstudianteSeleccion(List<EstudiantePojo> estudianteSeleccion) {
		this.estudianteSeleccion = estudianteSeleccion;
	}

}
