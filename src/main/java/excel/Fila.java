/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package excel;

import java.util.Date;
import javax.validation.constraints.NotNull;

/**
 *
 * @author sergiottellez
 */public class Fila {

	 String user_id;
        
         Date fecha;
        
      String apellido1;
  
         String apellido2;

         String universidad;

    String region;

      String empresa;
      
      String centro;
 
        int antiguedad;

         int reciente;

         String activo;
         
         String foreigner;

    public String getCentro() {
        return centro;
    }

    public void setCentro(String centro) {
        this.centro = centro;
    }

    public String getForeigner() {
        return foreigner;
    }

    public void setForeigner(String foreigner) {
        this.foreigner = foreigner;
    }

         Integer role;
        
         String sexo;
        

	 String email;
 

	 String nombre;
        
     String dblppersoname;
        
       String dblpauthorkey;

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }



    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

  

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDblppersoname() {
        return dblppersoname;
    }

    public void setDblppersoname(String dblppersoname) {
        this.dblppersoname = dblppersoname;
    }

    public String getDblpauthorkey() {
        return dblpauthorkey;
    }

    public void setDblpauthorkey(String dblpauthorkey) {
        this.dblpauthorkey = dblpauthorkey;
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

    public String getUniversidad() {
        return universidad;
    }

    public void setUniversidad(String universidad) {
        this.universidad = universidad;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getEmpresa() {
        return empresa;
    }

    public void setEmpresa(String empresa) {
        this.empresa = empresa;
    }

    public int getAntiguedad() {
        return antiguedad;
    }

    public void setAntiguedad(int antiguedad) {
        this.antiguedad = antiguedad;
    }

    public int getReciente() {
        return reciente;
    }

    public void setReciente(int reciente) {
        this.reciente = reciente;
    }

    public String getActivo() {
        return activo;
    }

    public void setActivo(String activo) {
        this.activo = activo;
    }

    public Integer getRole() {
        return role;
    }

    public void setRole(Integer role) {
        this.role = role;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }


        
        
        

      


    
    
}

