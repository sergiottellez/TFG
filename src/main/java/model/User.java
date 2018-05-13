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

import java.util.Date;
import javax.validation.constraints.NotNull;



/**
 *
 * @author sergiottellez
 */

public class User  {
    
     
    
    
	private String user_id;
        
        private Date fecha;
        
        private String orcid;
	
        @NotNull
	private String email;
        @NotNull
	private String password;

	private String nombre;
        
       

        private String apellido1;
  
        private String apellido2;

        private String universidad;

        private String region;

        private int empresa;
 
        private int antiguedad;

        private int reciente;

        private int activo;
        
        private int cuota;

  
        private Integer role;
        
        private String sexo;
        
        private String dni;
        
        private String grupoInvestigacion;
  public int getCuota() {
        return cuota;
    }

    public void setCuota(int cuota) {
        this.cuota = cuota;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getOrcid() {
        return orcid;
    }

    public void setOrcid(String orcid) {
        this.orcid = orcid;
    }

    public String getGrupoInvestigacion() {
        return grupoInvestigacion;
    }

    public void setGrupoInvestigacion(String grupoInvestigacion) {
        this.grupoInvestigacion = grupoInvestigacion;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }
        
        private int fundador;
        
        private String dblppersonname;
        
        private String authorkey;
        
        private String pais;

    public String getDblppersonname() {
        return dblppersonname;
    }

    public void setDblppersonname(String dblppersonname) {
        this.dblppersonname = dblppersonname;
    }

    public String getAuthorkey() {
        return authorkey;
    }

    public void setAuthorkey(String authorkey) {
        this.authorkey = authorkey;
    }


    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public int getFundador() {
        return fundador;
    }

    public void setFundador(int fundador) {
        this.fundador = fundador;
    }
        

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

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

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
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

        this.nombre = name;
        this.role = role;
        this.password = password;
        
    }
    
      public User(String email, String apellido1,String apellido2, String name, Integer role, String password,String universidad,String region,Integer empresa,Integer antiguedad,Integer reciente,Integer activo,String sexo){
        
        
        this.email = email;
        this.apellido1 = apellido1;
                this.apellido2 = apellido2;
                this.universidad = universidad;
                this.activo = activo;
                this.antiguedad = antiguedad;
                this.empresa = empresa;
                this.region = region;
        this.nombre = name;
        this.role = role;
        this.password = password;
        this.sexo = sexo;
        
    }
    
}


