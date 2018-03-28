/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;



/**
 *
 * @author sergiottellez
 */

public class User implements Serializable {
    
     private static final long serialVersionUID = 1L;
    
    
	private int user_id;
	
        @NotNull
	private String email;
        @NotNull
	private String password;

	private String name;

        private String apellido1;
  
        private String apellido2;

        private String universidad;

        private String region;

        private int empresa;
 
        private int antiguedad;

        private int reciente;

        private int activo;

        private Integer role;

    public User() {
    }

    public int getAntiguedad() {
        return antiguedad;
    }

    public void setAntiguedad(int antiguedad) {
        this.antiguedad = antiguedad;
    }

    public int getActivo() {
        return activo;
    }

    public void setActivo(int activo) {
        this.activo = activo;
    }

    public String getApellido1() {
        return apellido1;
    }

    public void setApellido1(String apellido1) {
        this.apellido1 = apellido1;
    }

    public String getApellido2() {
        return apellido2;
    }

    public void setApellido2(String apellido2) {
        this.apellido2 = apellido2;
    }

    public int getEmpresa() {
        return empresa;
    }

    public void setEmpresa(int empresa) {
        this.empresa = empresa;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public int getReciente() {
        return reciente;
    }

    public void setReciente(int reciente) {
        this.reciente = reciente;
    }

    public String getUniversidad() {
        return universidad;
    }

    public void setUniversidad(String universidad) {
        this.universidad = universidad;
    }


    
    
  
   

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

  
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getRole() {
        return role;
    }

    public void setRole(Integer role) {
        this.role = role;
    }
    
    
    public User(String email, String apellido1,String apellido2, String name, Integer role, String password){
        
        
        this.email = email;
        this.apellido1 = apellido1;
                this.apellido2 = apellido2;

        this.name = name;
        this.role = role;
        this.password = password;
        
    }
    
      public User(String email, String apellido1,String apellido2, String name, Integer role, String password,String universidad,String region,Integer empresa,Integer antiguedad,Integer reciente,Integer activo){
        
        
        this.email = email;
        this.apellido1 = apellido1;
                this.apellido2 = apellido2;
                this.universidad = universidad;
                this.activo = activo;
                this.antiguedad = antiguedad;
                this.empresa = empresa;
                this.region = region;
        this.name = name;
        this.role = role;
        this.password = password;
        
    }
    
}


