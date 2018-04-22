/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.sql.ResultSet;
import java.sql.SQLException;
import model.User;
import org.springframework.jdbc.core.RowMapper;

/**
 *
 * @author sergiottellez
 */
public class UserRowMapper implements RowMapper<User>{
    
    
            
           
         @Override
        public User mapRow(ResultSet rs, int i) throws SQLException {
         User e = new User();
             e.setUser_id(rs.getString(1));
             e.setFecha(rs.getDate(2));
             e.setOrcid(rs.getString(3));
             e.setDni(rs.getString(4));
             e.setPassword(rs.getString(5));
        e.setApellido2(rs.getString(6));
         e.setApellido1(rs.getString(7));
        e.setNombre(rs.getString(8));
        e.setSexo(rs.getString(9));
        e.setDblppersonname(rs.getString(10));
        e.setAuthorkey(rs.getString(11));
         e.setRole(rs.getInt(12));
           e.setUniversidad(rs.getString(13));
            e.setRegion(rs.getString(14));
            e.setEmpresa(rs.getInt(15));
            e.setPais(rs.getString(16));
        e.setEmail(rs.getString(17));
     e.setGrupoInvestigacion(rs.getString(18));
    e.setAntiguedad(rs.getInt(19));
    e.setReciente(rs.getInt(20));
    e.setActivo(rs.getInt(21));
    e.setFundador(rs.getInt(22));
    return e;

    
        }
    
    
}
