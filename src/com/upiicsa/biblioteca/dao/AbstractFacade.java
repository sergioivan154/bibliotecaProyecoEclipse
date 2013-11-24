package com.upiicsa.biblioteca.dao;

import java.util.List;

public interface AbstractFacade{
    
    public List<?> getListado();
    
    public Object getObjeto(Object id);
    
    public void addObjeto(Object o);
    
    public void editObjeto(Object o);
    
    public void deleteObjeto(Object id);
    
    public List<?> customListado(String cond);
    
    public void customElimina(String qry);

}
