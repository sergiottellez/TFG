/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import model.UserInfo;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;

/**
 *
 * @author sergiottellez
 */
public final class UserInfoMapper implements RowMapper<UserInfo>{
        
        
        public UserInfo mapRow(ResultSet rs, int rownum)throws SQLException{
            
            String email = rs.getString("email");
            String password = rs.getString("password");
            
            
            return new UserInfo(email,password);
            
        }
        
        public SqlParameterSource getSqlParameterSource(String email,String password){
            MapSqlParameterSource parameterSource = new MapSqlParameterSource();
            parameterSource.addValue("email", email);
            parameterSource.addValue("password", password);
                        
                        
            return parameterSource;

            
            
            
            
        }
        

}