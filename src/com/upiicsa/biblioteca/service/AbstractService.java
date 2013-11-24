package com.upiicsa.biblioteca.service;

import java.util.List;

public interface AbstractService {
	
    public List<?> obtenerListado();
    
    public Object obtenerObjeto(Object id);
    
    public void agregarObjeto(Object o);
    
    public void editarObjeto(Object o);
    
    public void borrarObjeto(Object id);
    
    public List<?> listadoPersonalizado(String cond); //listado personalizado
    
    public void eliminaPersonalizado(String qry); //eliminado personalizado

}
