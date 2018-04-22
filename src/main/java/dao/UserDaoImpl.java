/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import javax.persistence.Query;
import javax.sql.DataSource;
import model.Conector;
import model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author sergiottellez
 */

@Repository
public class UserDaoImpl implements UserDao {
    
    
    
    @Autowired
   JdbcTemplate template;

    public UserDaoImpl(DataSource dataSource) {
        this.template = new JdbcTemplate(dataSource);
    }

    public JdbcTemplate getTemplate() {
        return template;
    }

    @Autowired
    public void setTemplate(JdbcTemplate template) {
        this.template = template;
    }
    
    
    
     public UserDaoImpl(){
        Conector con = new Conector();
        this.template = new JdbcTemplate(con.conectar());
    }
    





    
  

    @Transactional(readOnly =false)
    @Override
   public User findByEmail(String email) throws DataAccessException {
        
       String sql = "SELECT * from user WHERE email = '" + email + "';";
       //Query query = getEm().createQuery(sql);
       return template.queryForObject(sql, new RowMapper<User>(){
            
           
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
    });
           
           
           
       

             // return (User) query.getSingleResult();
        
          

   } 
    @Transactional(readOnly =false)
    @Override
    public void deleteUser(User user)throws DataAccessException {//¿puede un mismo email tener varias cuentas?
        String sql = "DELETE  from user where email= '" + user.getEmail() + "';" ; 
        template.update(sql);
        // getEm().getTransaction().begin();
        //Query query = getEm().createQuery(sql);
       // query.executeUpdate();
        //getEm().getTransaction().commit();

    }
    
    @Transactional(readOnly =false)
    @Override
    public void newUser(String email, String password, String name, String apellido1, String apellido2, Integer role) throws DataAccessException{
        User user = new User(email,apellido1,apellido2,name,role, password); //tengo que encriptarlo
        String sql = "INSERT IGNORE INTO user VALUES (" + user.getUser_id() + ", '" + user.getEmail() + "', '" +user.getApellido1()+ "', '" + user.getNombre()+ "', '" +user.getPassword() +  "', " + user.getRole() + ";";
        // template.update(sql);
        //getEm().getTransaction().begin();
        //Query query = getEm().createQuery(sql);
        //query.executeUpdate();
       // em.getTransaction().commit();
    }

    @Transactional(readOnly =false)
    @Override
    public void editUser(User user, String email, String password, String name, String apellido1, String apellido2, Integer role) throws DataAccessException{
        user.setEmail(email);
        user.setApellido1(apellido1);
          user.setApellido2(apellido2);

        user.setNombre(name);
        user.setPassword(password); //OJO CON LAS PASSWORD QUE ESTÉN ENCRIPTADAS
       
        user.setRole(role);
        String sql = "UPDATE user SET email='" + email + "', name='" + name + "', apellido1 '" + apellido1 +  "', apellido2 '" + apellido2 + "', role=" + role + ", password='" + password + "' WHERE user_id='" + user.getUser_id() + "';";
        //template.update(sql);
      //  em.getTransaction().begin();
        //Query query = getEm().createQuery(sql);
        //query.executeUpdate();
        //em.getTransaction().commit();
    }

    @Transactional(readOnly =false)
    @Override
    public void hacerAdmin(User user)throws DataAccessException {
        user.setRole(1);
        String sql = "UPDATE user SET role=" + 1 + " WHERE user_id='" + user.getUser_id() + "';";
         //template.update(sql);
       // em.getTransaction().begin();
       // Query query = getEm().createQuery(sql);
       // query.executeUpdate();
      //  em.getTransaction().commit();
    }
    
    @Transactional(readOnly = true)
    @SuppressWarnings("unchecked")
    @Override
    public List<User> orderByEmail() throws DataAccessException {
        
        String sql = "SELECT * from user ORDER BY email ASC";
        //Query query = getEm().createQuery(sql);

       // return query.getResultList();
      return template.query(sql, new RowMapper<User>() {
   @Override
   public User mapRow(ResultSet rs, int row) throws SQLException {
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

        });
      

    }
    
    
    @Transactional(readOnly = true)
    @Override
    public List<User> orderByNombre() throws DataAccessException {
         String sql = "SELECT * from user ORDER BY name ASC";
       // Query query = getEm().createQuery(sql);

        //return query.getResultList();
         return template.query(sql, new RowMapper<User>() {
   @Override
   public User mapRow(ResultSet rs, int row) throws SQLException {
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

        });
      

    }

    @Transactional(readOnly = true)
    @Override
    public List<User>  orderByEmpresa() throws DataAccessException {
   String sql = "SELECT * from user ORDER BY apellido1 ASC";
        //Query query = getEm().createQuery(sql);

        return null; }

    @Transactional(readOnly = true)
    @Override
    public List<User> orderByApellido() throws DataAccessException {
        String sql = "SELECT * from user ORDER BY apellido1 ASC";
        //Query query = getEm().createQuery(sql);

     

        return template.query(sql, new RowMapper<User>() {
   @Override
   public User mapRow(ResultSet rs, int row) throws SQLException {
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

        });
      


    }

    @Transactional(readOnly = true)
    @SuppressWarnings("unchecked")
    @Override
    public List<User> obtenerLista() throws DataAccessException {
        String sql = "SELECT * from user ORDER BY name ASC"; //por defecto
  //      Query query = getEm().createQuery(sql);

        //return session.getCurrentSession().createQuery(sql).list();

        return template.query(sql, new RowMapper<User>() {
   @Override
   public User mapRow(ResultSet rs, int row) throws SQLException {
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

        });
      
    }

    @Transactional(readOnly =false)
    @Override
    public User findById(Integer user_id) throws DataAccessException {
     String sql = "SELECT u from user where user_id = '" + user_id + "';";
        //Query query = getEm().createQuery(sql);


     //return     (User)    query.getSingleResult();

       return template.queryForObject(sql, new Object[] {user_id}, new BeanPropertyRowMapper<User>(User.class));
    }
    
    @Transactional(readOnly =false)
    @Override
    public void edit(User user) throws DataAccessException {
        String sql = "UPDATE user SET email='" + user.getEmail() + "', name='" + user.getNombre() + "', apellido1 '" + user.getApellido1() + "', role=" + user.getRole() + ", password='" + user.getPassword() + "' WHERE user_id='" + user.getUser_id() + "';";
     template.update(sql);
       // em.getTransaction().begin();
       // Query query = getEm().createQuery(sql);
    //    query.executeUpdate();
        //em.getTransaction().commit();
    }

    @Override
    public User login(String email, String pass) {
 String sql = "SELECT * from user WHERE email = '" + email + "' AND password='"+ pass + "';";
       //Query query = getEm().createQuery(sql);
       return template.queryForObject(sql,  new BeanPropertyRowMapper<User>(User.class));
    }

    @Override
    public void saveOrUpdate(User contact) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
   
 



}
