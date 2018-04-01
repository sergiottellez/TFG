/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.UserDao;
import dao.UserDaoImpl;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import model.Conector;
import model.Tuple;
import model.User;




import org.springframework.jdbc.core.JdbcTemplate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;

import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import service.UserService;
import service.UserServiceImpl;

/**
 *
 * @author sergiottellez
 */

@Controller
@RequestMapping(value="/")
public class UserController {
    

    @Autowired
    private UserDaoImpl userService; //inject xml
    
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;    
    
    
    private JdbcTemplate jdbcTemplate;

    public UserController(){
        Conector con = new Conector();
        this.jdbcTemplate = new JdbcTemplate(con.conectar());
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    public JdbcTemplate getJdbcTemplate() {
        return jdbcTemplate;
    }

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    
    
    
    @RequestMapping(value={"/verLista.htm", "/verLista"},method=RequestMethod.GET)
    public ModelAndView verLista()
    {
        ModelAndView mav=new ModelAndView();
        String sql="select * from user";
        List datos=this.jdbcTemplate.queryForList(sql);
       // List datos = this.userService.obtenerLista(); //Prueba de UserDao
        mav.addObject("datos",datos);
        mav.setViewName("verLista");
        return mav;
    }


 
 
   
 @RequestMapping(value={"/login","/"}, method=RequestMethod.GET)
 public ModelAndView login(@RequestParam(value="error", required = false) String error){
  ModelAndView model = new ModelAndView();
  
  if(error != null){
   model.addObject("msg", "The username or password is incorrect!");
  }
  
  model.setViewName("login");
  
  return model;
 }
 
 @RequestMapping(value="/login", method=RequestMethod.POST)
 public ModelAndView verifyLogin(@RequestParam String email,@RequestParam String password,HttpSession session){
     
       String sql = "SELECT * from user WHERE email = '" + email + "';";
       //Query query = getEm().createQuery(sql);
       User user =  this.jdbcTemplate.queryForObject(sql, new RowMapper<User>(){
            
           
         @Override
        public User mapRow(ResultSet rs, int i) throws SQLException {
            User e = new User();
             e.setUser_id(rs.getInt(1));
             e.setEmail(rs.getString(2));
    e.setName(rs.getString(3));
    e.setApellido2(rs.getString(4));
    e.setApellido1(rs.getString(5));
    e.setPassword(rs.getString(6));
    e.setRole(rs.getInt(7));
    e.setUniversidad(rs.getString(8));
    e.setRegion(rs.getString(9));
    e.setEmpresa(rs.getInt(10));
    e.setAntiguedad(rs.getInt(11));
    e.setReciente(rs.getInt(12));
    e.setActivo(rs.getInt(13));

    return e;
        }
    });
           
       
       ModelAndView model = new ModelAndView();
           
    //User user = this.userService.findByEmail(email);
    if(user == null){
        model.setViewName("login");
        return model;
    }else{
        
      
        if(this.passwordEncoder.matches(password,user.getPassword())){
        session.setAttribute("userPrincipal", user);
          model.setViewName("home");
        return model;
    }else{
              model.setViewName("home");
        return model;
        }
    }
    
 }
 
 @RequestMapping(value={"/", "/home"}, method=RequestMethod.GET)
 public ModelAndView home(){
  ModelAndView model = new ModelAndView();
  model.setViewName("verLista");
  
  return model;
 }
 
 @RequestMapping(value="/logout", method=RequestMethod.GET)
 public String logoutPage(HttpServletRequest request, HttpServletResponse response){
  Authentication auth = SecurityContextHolder.getContext().getAuthentication();
  
  if(auth != null){
   new SecurityContextLogoutHandler().logout(request, response, auth);
  }
  
  return "redirect:/login";
 }
 
 @RequestMapping(value="/accessDenied", method=RequestMethod.GET)
 public ModelAndView accessDenied(){
  ModelAndView model = new ModelAndView();
  model.setViewName("errors/access_denied");
  return model;
 }
 
 @RequestMapping(value = "/saveContact", method = RequestMethod.POST)
public ModelAndView editContact(@ModelAttribute User contact) {
    this.saveOrUpdate(contact);
    return new ModelAndView("home");
}

@RequestMapping(value = "/editUser", method = RequestMethod.GET)
public ModelAndView editUser(HttpServletRequest request) {
    String userId = (request.getParameter("id"));
    //User user = this.userService.findById(userId);
    
    User user = this.findById(userId);
    ModelAndView model = new ModelAndView("userEditForm");
    model.addObject("contact", user);
 
    return model;
}




 public void saveOrUpdate(User contact) {
    if (contact.getUser_id() > 0) {
        // update
        String sql = "UPDATE user SET name=?, email=?, apellido1=?,apellido2=?,password=?,role=?,universidad=?,region=?,empresa=?,antiguedad=?,reciente=?,activo=? WHERE email=?";
        jdbcTemplate.update(sql, contact.getName(), contact.getEmail(),
                contact.getApellido1(), contact.getApellido2(),this.passwordEncoder.encode(contact.getPassword()),contact.getRole(),contact.getUniversidad(),contact.getRegion(),contact.getEmpresa(),contact.getAntiguedad(),contact.getReciente(),contact.getActivo(),contact.getEmail());
    } else {
        // insert
        String sql = "INSERT INTO user (name, email, apellido1, apellido2,password,role,universidad,region,empresa,antiguedad,reciente,activo)"
                    + " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql, contact.getName(), contact.getEmail(),
                contact.getApellido1(), contact.getApellido2(), this.passwordEncoder.encode(contact.getPassword()),contact.getRole(),contact.getUniversidad(),contact.getRegion(),contact.getEmpresa(),contact.getAntiguedad(),contact.getReciente(),contact.getActivo());
   
    }
        
      
     
 } 

   public User findById(String email) throws DataAccessException {
      String sql = "SELECT * from user WHERE email = '" + email + "';";
       //Query query = getEm().createQuery(sql);
       User user =  this.jdbcTemplate.queryForObject(sql, new RowMapper<User>(){
            
           
         @Override
        public User mapRow(ResultSet rs, int i) throws SQLException {
            User e = new User();
             e.setUser_id(rs.getInt(1));
             e.setEmail(rs.getString(2));
    e.setName(rs.getString(3));
    e.setApellido2(rs.getString(4));
    e.setApellido1(rs.getString(5));
    e.setPassword(rs.getString(6));
    e.setRole(rs.getInt(7));
    e.setUniversidad(rs.getString(8));
    e.setRegion(rs.getString(9));
    e.setEmpresa(rs.getInt(10));
    e.setAntiguedad(rs.getInt(11));
    e.setReciente(rs.getInt(12));
    e.setActivo(rs.getInt(13));

    return e;
        }
    });
       
       return user;
             } 

 
 
   
     public void deleteUser(User user)throws DataAccessException {//¿puede un mismo email tener varias cuentas?
        String sql = "DELETE from user where email= '" + user.getEmail() + "';" ; 
        jdbcTemplate.update(sql);
        // getEm().getTransaction().begin();
        //Query query = getEm().createQuery(sql);
       // query.executeUpdate();
        //getEm().getTransaction().commit();

    }
     
     @RequestMapping(value = "/deleteUser", method = RequestMethod.GET)
public ModelAndView deleteContact(HttpServletRequest request) {
   
    
    String userId = (request.getParameter("id"));
    //User user = this.userService.findById(userId);
    
    User user = this.findById(userId);
   deleteUser(user);
 
   return new ModelAndView("home");
}


@RequestMapping(value = "/newUser", method = RequestMethod.GET)
public ModelAndView newUser(HttpServletRequest request) {
    //String userId = (request.getParameter("id"));
    //User user = this.userService.findById(userId);
    
   // User user = this.findById(userId);
   
   User newContact = new User();
   
    ModelAndView model = new ModelAndView("userNewForm");
 model.addObject("contact", newContact);
 
    return model;
}


 @RequestMapping(value = "/summary", method = RequestMethod.GET)
    public ModelAndView verSummary()
    {
        ModelAndView mav=new ModelAndView();
        String sql="SELECT universidad FROM user";
        List<String> universidad = new ArrayList<>();
        Map<String, Tuple<Integer,Integer>> datosFinales = new HashMap<>();
        
      List<String> datosUniversidadLista= this.jdbcTemplate.queryForList(sql, String.class);
       
       for(String s : datosUniversidadLista){ //Metemos todas las universidades de la BD
           
           if(!universidad.contains(s)){
               universidad.add( s);
               
           }
           
       }
       
       
       
       
       for(String univ : universidad){
           //Buscamos por cada universidad el numero total que hay
              String consulta="select count(*) from user where universidad= '" + univ + "';"; //Esta consulta es la que falla
              Integer datosNumero = this.jdbcTemplate.queryForObject(consulta,Integer.class);
              String consulta2="select count(activo) from user where universidad= '" + univ + "' AND activo=" + 1 + ";"; //Esta consulta es la que falla
              Integer datosNumero2 = this.jdbcTemplate.queryForObject(consulta2,Integer.class);
              Tuple<Integer,Integer> tupla = new Tuple(datosNumero,datosNumero2);
              
              datosFinales.put(univ, tupla);
           
       }
       
       
       
        String sql1="SELECT region FROM user";
        List<String> region = new ArrayList<>();
        Map<String, Tuple<Integer,Integer>> datosRegion = new HashMap<>();
        
      List<String> datosRegionLista= this.jdbcTemplate.queryForList(sql1, String.class);
       
       for(String s : datosRegionLista){ //Metemos todas las universidades de la BD
           
           if(!region.contains(s)){
               region.add( s);
               
           }
           
       }
       
       
       
       
       for(String univ : region){
           //Buscamos por cada universidad el numero total que hay
               String consulta="select count(*) from user where region= '" + univ + "';"; //Esta consulta es la que falla
              Integer datosNumero = this.jdbcTemplate.queryForObject(consulta,Integer.class);
              String consulta2="select count(*) from user where region= '" + univ + "' AND activo= "+ 1 +  ";"; //Esta consulta es la que falla
              Integer datosNumero2 = this.jdbcTemplate.queryForObject(consulta2,Integer.class);
              Tuple<Integer,Integer> tupla = new Tuple(datosNumero,datosNumero2);
              
              datosRegion.put(univ, tupla);
           
       }
       
       
       
        String sql2="SELECT count(*) FROM user WHERE sexo='" + 'M' + "';";
   
        
       
  Integer datosMujeres = this.jdbcTemplate.queryForObject(sql2,Integer.class); 
  
    String sql3="SELECT count(*) FROM user WHERE sexo='" + 'M' + "' AND activo=" + 1 + ";";
   
        
       
  Integer datosMujeresActivas = this.jdbcTemplate.queryForObject(sql3,Integer.class);   
  
     String sql4="SELECT count(*) FROM user";
   
        
       
  Integer datosTotales = this.jdbcTemplate.queryForObject(sql4,Integer.class);   
  
  datosMujeresActivas = (datosMujeresActivas*100)/datosMujeres;
  datosMujeres = (datosMujeres*100)/datosTotales;
  
       
       
       
  
  
   Tuple<Integer,Integer> datosMujeresTupla = new Tuple<>(datosMujeres,datosMujeresActivas);
       
       // List datos = this.userService.obtenerLista(); //Prueba de UserDao
        mav.addObject("datosFinales",datosFinales);
        mav.addObject("datosRegion",datosRegion);
        mav.addObject("datosMujeres",datosMujeresTupla);
        mav.setViewName("summary");
        return mav;
    }
         

    
}
