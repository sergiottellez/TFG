/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import model.Conector;
import model.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

/**
 *
 * @author sergiottellez
 */

@Repository
public class LoginDaoImpl implements LoginDao{
    
     private JdbcTemplate jdbcTemplate;

    public LoginDaoImpl(){
        Conector con = new Conector();
        this.jdbcTemplate = new JdbcTemplate(con.conectar());
    }

    public JdbcTemplate getJdbcTemplate() {
        return jdbcTemplate;
    }

    @Autowired
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    
    @Override
    public UserInfo findUserInfo(String email){
        String sql =  "SELECT email,password FROM user WHERE email = :email";
        UserInfo userInfo = this.jdbcTemplate.queryForObject(sql,new UserInfoMapper());
                
                return userInfo;
        
    }


    
    private static final class UserInfoMapper implements RowMapper<UserInfo>{
        
        
        public UserInfo mapRow(ResultSet rs, int rownum)throws SQLException{
            
            String email = rs.getString("email");
            String password = rs.getString("password");
            
            
            return new UserInfo(email,password);
            
        }
    }
        
        public SqlParameterSource getSqlParameterSource(String email,String password){
            MapSqlParameterSource parameterSource = new MapSqlParameterSource();
            parameterSource.addValue("email", email);
            parameterSource.addValue("password", password);
                        
                        
            return parameterSource;

            
            
            
            
        }
        
    
    
    
    
}
