package com.upiicsa.biblioteca.model;

public class EstudiantePojo {
	private String boleta;
	private String nombre;
	private String direccion;
	private String carrera;
	private String edad;
	private String semestre;
	private Boolean inscrito;
	
	public String getSemestre() {
		return semestre;
	}
	public void setSemestre(String semestre) {
		this.semestre = semestre;
	}
	public String getBoleta() {
		return boleta;
	}
	public void setBoleta(String boleta) {
		this.boleta = boleta;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getDireccion() {
		return direccion;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	public String getCarrera() {
		return carrera;
	}
	public void setCarrera(String carrera) {
		this.carrera = carrera;
	}
	public String getEdad() {
		return edad;
	}
	public void setEdad(String edad) {
		this.edad = edad;
	}
	public Boolean getInscrito() {
		return inscrito;
	}
	public void setInscrito(Boolean inscrito) {
		this.inscrito = inscrito;
	}
	
}
