/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import javax.validation.constraints.NotNull;

/**
 *
 * @author sergiottellez
 */
public class Grupo {
    
    @NotNull
    private String nombre;
    
    private String url;
    
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    private User responsable;
    
    private User corresponsal;
    
    public Grupo(String nombre,String url, User lider, User corresponsal){
        this.corresponsal= corresponsal;
        this.responsable = lider;
        this.url = url;
        this.nombre = nombre;
             
        
        
    }
    
    public Grupo(){
        
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public User getResponsable() {
        return responsable;
    }

    public void setResponsable(User responsable) {
        this.responsable = responsable;
    }

  

    public User getCorresponsal() {
        return corresponsal;
    }

    public void setCorresponsal(User corresponsal) {
        this.corresponsal = corresponsal;
    }
    
    
}
