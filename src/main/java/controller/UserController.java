/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import dao.UserDao;
import dao.UserDaoImpl;
import excel.Fila;
import excel.Lector;
import static excel.Lector.cogerCad;
import static excel.Lector.cogerDob;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.lang.annotation.Annotation;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Random;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import model.Conector;
import model.Grupo;
import model.Tuple;
import model.User;




import org.springframework.jdbc.core.JdbcTemplate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.core.io.ClassPathResource;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.messaging.MessagingException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.ModelAttribute;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import sun.rmi.transport.Transport;


/**
 *
 * @author sergiottellez
 */

@Controller
@Scope("session")
@RequestMapping(value="/")
@SessionAttributes({"tipoListado","textoTipoBusqueda"})
public class UserController {
    

    @Autowired
    private UserDaoImpl userService; //inject xml
    
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    
    
    @Autowired
    private  User user;
    
    @Autowired
    private UsernamePasswordAuthenticationToken princi;
    
    private Lector l ;
    
    private String fichero;

    private Fila fila;

    public Lector getL() {
        return l;
    }

    public void setL(Lector l) {
        this.l = l;
    }

    public Fila getFila() {
        return fila;
    }

    public void setFila(Fila fila) {
        this.fila = fila;
    }
    
    @Autowired
    private List listado;
    
    @Autowired
    private List bajas;
    
 
    private String fileLocation;
    
  
    
    @Autowired
    private JdbcTemplate jdbcTemplate;

    
    
    public UserController(){
        Conector con = new Conector();
        this.jdbcTemplate = new JdbcTemplate(con.conectar());
        this.passwordEncoder = new BCryptPasswordEncoder();
        this.fichero = new String();
    }

    public JdbcTemplate getJdbcTemplate() {
        return jdbcTemplate;
    }

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    
    
    
    @RequestMapping(value={"/verLista.htm", "/verLista"},method={RequestMethod.POST, RequestMethod.GET})
    public ModelAndView verLista(HttpServletRequest request)
    {
        Integer tipoListado = 6;
        HttpSession session = request.getSession();
        ModelAndView mav=new ModelAndView();
         Object prueba = session.getAttribute("user");
  if(prueba == null){
        mav.setViewName("login");

      
  }else{
     
      
     String tipolistadoString = request.getParameter("tipoListadoNumero");
     if(tipolistadoString != null){
         tipoListado =  Integer.parseInt(request.getParameter("tipoListadoNumero"));
        
     }
     
     
        String parametro =  request.getParameter("textoTipoBusqueda");
        if(session.getAttribute("listado") == null){
            
            this.listado = new ArrayList();
            session.setAttribute("listado", listado);
            
        }else{
            this.listado = (ArrayList) session.getAttribute("listado");
        } 
        
       String prueba12 = (request.getParameter("anio"));
       String prueba22 = request.getParameter("tipo");
       Integer anio = null;
       
       if(prueba12 != null){
          anio =  Integer.parseInt(prueba12);
       }
       
       if(anio != null && prueba22 != null){
           
           
           String q = "SELECT * FROM user WHERE antiguedad=? and actual=1;";
           listado = this.jdbcTemplate.query(q,new UserRowMapper(),anio);
           mav.addObject("datos",listado);
           mav.setViewName("verLista");
           
       }else if(prueba22 == null && anio != null){
              String q = "SELECT * FROM user WHERE reciente=? and actual=1;";
           listado = this.jdbcTemplate.query(q,new UserRowMapper(),anio);
           mav.addObject("datos",listado);
           mav.setViewName("verLista");
           
       }else{
       
           switch (tipoListado) {
            case 0: //Email
                if(parametro!=null || !parametro.equals("")){
                    
                    parametro = "%" + parametro +"%";
                    
           String q = "SELECT * from user WHERE email LIKE ? AND actual=1";
           
         
             listado=this.jdbcTemplate.query(q,new UserRowMapper(),parametro);
       // List datos = this.userService.obtenerLista(); //Prueba de UserDao
            mav.addObject("datos",listado);
            mav.setViewName("verLista");
           
                }else{
                    parametro = "Introduce dato";
                    request.setAttribute("textoTipoBusqueda", parametro);
                }
                break;
            case 1: //Universidad
                
             
                if(parametro!=null || !parametro.equals("")){
                    
                     String sql="select * from user WHERE universidad LIKE '%" + parametro + "%' AND actual=1";
                 listado=this.jdbcTemplate.queryForList(sql);
       // List datos = this.userService.obtenerLista(); //Prueba de UserDao
                    mav.addObject("datos",listado);
            
                    mav.setViewName("verLista");
                     
        }
              // listado = findPorNombre(parametro);
                
                
           
               else{
                        parametro = "Introduce dato";
                    request.setAttribute("textoTipoBusqueda", parametro);
                }
                break;
        
            case 3: //Activos
                    String q = "SELECT * from user WHERE activo=1 AND actual=1";
           
         
             listado=this.jdbcTemplate.queryForList(q);
       // List datos = this.userService.obtenerLista(); //Prueba de UserDao
            mav.addObject("datos",listado);
            mav.setViewName("verLista");

         
                
                break;
            case 4: //Region 
           
                if(parametro!=null || !parametro.equals("")){
                    
                     String sql="select * from user WHERE region LIKE '%" + parametro + "%' AND actual=1";
                 listado=this.jdbcTemplate.queryForList(sql);
       // List datos = this.userService.obtenerLista(); //Prueba de UserDao
                    mav.addObject("datos",listado);
            
                    mav.setViewName("verLista");
                     
        }
              // listado = findPorNombre(parametro);
                
                
           
               else{
                        parametro = "Introduce dato";
                    request.setAttribute("textoTipoBusqueda", parametro);
                }
                break;
            case 5: // NOMBRE
                if(parametro!=null || !parametro.equals("")){
                    
                     String sql="select * from user WHERE nombre LIKE '%" + parametro + "%' AND actual=1";
                 listado=this.jdbcTemplate.queryForList(sql);
       // List datos = this.userService.obtenerLista(); //Prueba de UserDao
                    mav.addObject("datos",listado);
            
                    mav.setViewName("verLista");
                     
        }
              // listado = findPorNombre(parametro);
                
                
           
               else{
                        parametro = "Introduce dato";
                    request.setAttribute("textoTipoBusqueda", parametro);
                }
                break;
            case 6: //Todos -> por defecto
                  String sql="SELECT * from user where actual=1";
                 

                 listado=this.jdbcTemplate.queryForList(sql);
       // List datos = this.userService.obtenerLista(); //Prueba de UserDao
                    mav.addObject("datos",listado);
            
                    mav.setViewName("verLista");
                   
                break;
                
                
            case 7: //Exterior
                String ql="SELECT * from user where user_id LIKE '%EXT%' ";
                 listado=this.jdbcTemplate.queryForList(ql);
       // List datos = this.userService.obtenerLista(); //Prueba de UserDao
                    mav.addObject("datos",listado);
            
                    mav.setViewName("verLista");
                    break;
        
       
       // List datos = this.userService.obtenerLista(); //Prueba de UserDao
      
    }

           }
  }
           mav.addObject("fichero", fichero);
           fichero = "";
          return mav;
      
 
    }
    
    /*@RequestMapping(value={"/verListaInicial.htm", "/verListaInicial"},method={RequestMethod.POST, RequestMethod.GET})
    public ModelAndView verListaInicial(HttpServletRequest request)
    {
        ModelAndView mav=new ModelAndView();
        HttpSession session = request.getSession();
  
          
                  String sql="select * from user";
                this.listado=this.jdbcTemplate.queryForList(sql);
       // List datos = this.userService.obtenerLista(); //Prueba de UserDao
                    mav.addObject("datos",listado);
            
                    mav.setViewName("verLista");
                   
            
        
       
       // List datos = this.userService.obtenerLista(); //Prueba de UserDao
      
    

          
          return mav;
      
       
    }
    
    */
    
 
 
   
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
 public ModelAndView verifyLogin(@RequestParam String dni,@RequestParam String password,HttpSession session){
     
       String sql = "SELECT * from user WHERE dni = '" + dni + "' AND actual=1";
        ModelAndView model = new ModelAndView();
     
       //Query query = getEm().createQuery(sql);
       try{
        user =  this.jdbcTemplate.queryForObject(sql, new RowMapper<User>(){
            
           
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
    e.setPrimerLogin(rs.getInt(27));
    return e;
        }
    });
       }catch(EmptyResultDataAccessException e){
             model.setViewName("errorLogin");
        return model;
           
           
       }
     
           
       
            
    //User user = this.userService.findByEmail(email);
    if(user == null){
        model.setViewName("errorLogin");
        return model;
    }else{
        
      
        if(this.passwordEncoder.matches(password,user.getPassword())){
       // session.setAttribute("user", user); //usuario en session
       this.princi = new UsernamePasswordAuthenticationToken(user.getEmail(),password);
       session.setAttribute("user", user);
       model.addObject("user", user);
        session.setAttribute("tipoListado", 6);
        model.setViewName("home");
        return model;
    }else{
              model.setViewName("login");
        return model;
        }
    }
    
 }
 
 @RequestMapping(value= "/home", method=RequestMethod.GET)
 public ModelAndView home(HttpSession session){
     boolean popup = true;
  ModelAndView model = new ModelAndView();
  Object prueba = session.getAttribute("user");
  if(prueba == null){
        model.setViewName("login");

      
  }else{
  model.setViewName("home");
  model.addObject("fichero", fichero);
  if(fichero.equals("")){
    popup = false;
    
  }else{
      popup=true;
  }
  fichero = "";
  model.addObject("valor", popup);
  session.setAttribute("tipoListado", 6);
  session.setAttribute("textoTipoBusqueda", "Datos");

  }
  
  return model;
 }
 

 @RequestMapping(value="/accessDenied", method=RequestMethod.GET)
 public ModelAndView accessDenied(){
  ModelAndView model = new ModelAndView();
  model.setViewName("access_denied");
  return model;
 }
 
  @RequestMapping(value="/loginFallo", method=RequestMethod.GET)
 public ModelAndView loginFallo(){
  ModelAndView model = new ModelAndView();
  model.setViewName("errorLogin");
  return model;
 }
 
  @RequestMapping(value="/errorInsercionExcel", method=RequestMethod.GET)
 public ModelAndView excel(){
  ModelAndView model = new ModelAndView();
  model.setViewName("errorInsercion");
  return model;
 }
   @RequestMapping(value="/error", method=RequestMethod.GET)
 public ModelAndView error(){
  ModelAndView model = new ModelAndView();
  model.setViewName("error");
  return model;
 }
 
@RequestMapping(value = "/editUser", method = RequestMethod.GET)
public ModelAndView editUser(HttpServletRequest request,HttpSession session) {
    String userId = request.getParameter("id");
    //User user = this.userService.findById(userId);
      Object prueba = session.getAttribute("user");
          ModelAndView model = new ModelAndView();


    if(prueba == null){
        model.setViewName("login");

      
  }else{
    User userN = this.findById(userId);
     model = new ModelAndView("userEditForm");
    model.addObject("contacto", userN);
    
    
    
    String sql = "SELECT nombreReg from regionEsp ORDER BY nombreReg;";
    List<String> region = this.jdbcTemplate.queryForList(sql, String.class);
    model.addObject("region",region);
    
     String sql1 = "SELECT nombreUniv from universidad ORDER BY nombreUniv;";
    List<String> universidades = this.jdbcTemplate.queryForList(sql1, String.class);
    model.addObject("universidades",universidades);
    
    String[] locales = Locale.getISOCountries();

    List<String> paises = new ArrayList<>();
	for (String countryCode : locales) {

		Locale obj = new Locale("", countryCode);

		paises.add("" + obj.getCountry() 
			+ "  " + obj.getDisplayCountry());

	}
        
            model.addObject("paises",paises);

    
    
    }
    return model;
}
  public User findById(String email) throws DataAccessException {
      String sql = "SELECT * from user WHERE email = '" + email + "' AND actual=1;";
       //Query query = getEm().createQuery(sql);
       User userN =  this.jdbcTemplate.queryForObject(sql, new RowMapper<User>(){
            
           
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
    e.setCuota(rs.getInt(25));
    return e;
        }
    });
       
       return userN;
             } 

 public User findByIdFecha(String user_id) throws DataAccessException {
      String sql = "SELECT * from user WHERE user_id = '" + user_id + "' ORDER BY fecha DESC;";
       //Query query = getEm().createQuery(sql);
       List userN =  this.jdbcTemplate.queryForList(sql, new RowMapper<User>(){
            
           
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
       
       return (User) userN.get(0);
             } 

 
   
     public void deleteUser(final User userN)throws DataAccessException {//¿puede un mismo email tener varias cuentas?
        String sql = "DELETE from user where user_id= '" + userN.getUser_id()+ "' and actual=1;" ; 
        jdbcTemplate.update(sql);
        // getEm().getTransaction().begin();
        //Query query = getEm().createQuery(sql);
       // query.executeUpdate();
        //getEm().getTransaction().commit();

    }
     
     @RequestMapping(value = "/deleteUser", method = RequestMethod.GET)
public String deleteContact(HttpServletRequest request) {
   
    
    String userId = (request.getParameter("id"));
   
    //User user = this.userService.findById(userId);
    
    User userN = this.findByIdDeVerdad(userId);
   deleteUser(userN);
 
   //return new ModelAndView("home");
       return "redirect:/verLista";

}


@RequestMapping(value = "/newUser", method = RequestMethod.GET)
public ModelAndView newUser(HttpServletRequest request,HttpSession session) {
    //String userId = (request.getParameter("id"));
    //User user = this.userService.findById(userId);
    
   // User user = this.findById(userId);
   Object prueba = session.getAttribute("user");
          ModelAndView model = new ModelAndView();


    if(prueba == null){
        model.setViewName("login");

      
  }else{
   User newContact = new User();
   
    model = new ModelAndView("userNewForm");
    model.addObject("contacto", newContact);
    String sql = "SELECT nombreReg from regionEsp;";
    List<String> region = this.jdbcTemplate.queryForList(sql, String.class);
    model.addObject("region",region);

    
     String sql1 = "SELECT nombreUniv from universidad ORDER BY nombreUniv;";
    List<String> universidades = this.jdbcTemplate.queryForList(sql1, String.class);
    model.addObject("universidades",universidades);
    
    String[] locales = Locale.getISOCountries();

    List<String> paises = new ArrayList<>();
	for (String countryCode : locales) {

		Locale obj = new Locale("", countryCode);

		paises.add("" + obj.getCountry() 
			+ "  " + obj.getDisplayCountry());

	}
        
            model.addObject("paises",paises);

    
    }
    return model;
}


 @RequestMapping(value = "/summary", method = RequestMethod.GET)
    public ModelAndView verSummary(HttpSession session)
    {
        
        Object prueba = session.getAttribute("user");
          ModelAndView model = new ModelAndView();


    if(prueba == null){
        model.setViewName("login");

      
  }else{
        
        String sql="SELECT universidad FROM user WHERE actual= 1";
        List<String> universidad = new ArrayList<>();
        Map<String, Tuple<Integer,Integer>> datosFinales = new HashMap<>();
        
      List<String> datosUniversidadLista= this.jdbcTemplate.queryForList(sql, String.class);
       
       for(String s : datosUniversidadLista){ //Metemos todas las universidades de la BD
           
           if(!universidad.contains(s) && !s.equals(" ")){
               universidad.add(s);
               
           }
           
       }
       
       
       
       
       for(String univ : universidad){
           //Buscamos por cada universidad el numero total que hay
              String consulta="select count(*) from user where universidad LIKE '" + univ + "' AND actual=1;"; //Esta consulta es la que falla
              Integer datosNumero = this.jdbcTemplate.queryForObject(consulta,Integer.class);
              String consulta2="select count(activo) from user where universidad LIKE '" + univ + "' AND activo=" + 1 + " AND actual=1;"; //Esta consulta es la que falla
              Integer datosNumero2 = this.jdbcTemplate.queryForObject(consulta2,Integer.class);
              
              if(datosNumero > 0){
              Tuple<Integer,Integer> tupla = new Tuple(datosNumero,datosNumero2);
              
              datosFinales.put(univ, tupla);
              }
              
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
               String consulta="select count(*) from user where region= '" + univ + "' AND actual=1;"; //Esta consulta es la que falla
              Integer datosNumero = this.jdbcTemplate.queryForObject(consulta,Integer.class);
              String consulta2="select count(*) from user where region= '" + univ + "' AND activo= "+ 1 +  " AND actual=1;"; //Esta consulta es la que falla
              Integer datosNumero2 = this.jdbcTemplate.queryForObject(consulta2,Integer.class);
              
              if(datosNumero > 0){
              Tuple<Integer,Integer> tupla = new Tuple(datosNumero,datosNumero2);
              
              datosRegion.put(univ, tupla);
              }
       }
       
       
       
        String sql2="SELECT count(*) FROM user WHERE sexo='" + 'M' + "' AND actual=1;";
   
        
       
  Integer datosMujeres = this.jdbcTemplate.queryForObject(sql2,Integer.class); 
  
    String sql3="SELECT count(*) FROM user WHERE sexo='" + 'M' + "' AND activo=" + 1 + " AND actual=1;";
   
        
       
  Integer datosMujeresActivas = this.jdbcTemplate.queryForObject(sql3,Integer.class);   
  
     String sql4="SELECT count(*) FROM user WHERE actual=1";
   
        
       
  Integer datosTotales = this.jdbcTemplate.queryForObject(sql4,Integer.class);   
  if(datosMujeres > 0){
  datosMujeresActivas = (datosMujeresActivas*100)/datosMujeres;
  datosMujeres = (datosMujeres*100)/datosTotales;
  }
       
       
       
  
  
   Tuple<Integer,Integer> datosMujeresTupla = new Tuple<>(datosMujeres,datosMujeresActivas);
   
   
   
   
   Integer aniocont = 2010;
           Calendar cal= Calendar.getInstance();
int year= cal.get(Calendar.YEAR);
           
        List<Integer> aniosjiji = new ArrayList<>();
        while(aniocont < year){
            
            aniosjiji.add(aniocont);
                   
           aniocont++; 
        }
        Map<Integer, Tuple<Integer,Integer>> datosFinalesjiji = new HashMap<>();
        
       
       
       for(Integer univ : aniosjiji){
           //Buscamos por cada universidad el numero total que hay
              String consulta="select count(*) from user where antiguedad = " + univ + " AND actual=1;"; //Esta consulta es la que falla
              Integer datosNumero = this.jdbcTemplate.queryForObject(consulta,Integer.class);
              String consulta2="select count(activo) from user where reciente = " + univ + " AND activo=" + 1 + " AND actual=1;"; //Esta consulta es la que falla
              Integer datosNumero2 = this.jdbcTemplate.queryForObject(consulta2,Integer.class);
              
              if(datosNumero > 0){
              Tuple<Integer,Integer> tuplanueva = new Tuple(datosNumero,datosNumero2);
                
              datosFinalesjiji.put(univ, tuplanueva);
              }
              
       }
       
       // List datos = this.userService.obtenerLista(); //Prueba de UserDao
        model.addObject("datosFinales",datosFinales);
        model.addObject("datosRegion",datosRegion);
        model.addObject("datosMujeres",datosMujeresTupla);
        model.addObject("datosanios",datosFinalesjiji);
        model.setViewName("summary");
        
    }
    return model;
    }
    
  @RequestMapping(value = "/logout", method = RequestMethod.GET)
  public final String logoutPage( HttpServletRequest request,
       HttpServletResponse response) {
    final Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    HttpSession session = request.getSession(false);

    session.invalidate();
    if (auth != null) {
      new SecurityContextLogoutHandler().logout(request, response, auth);
    }
    return "redirect:/login";
	  }

  
 /* @RequestMapping(value = "/saveContactEdit", method = RequestMethod.POST)
public ModelAndView saveContact(@Valid @ModelAttribute("contacto") User contacto) {
    
    Grupo grupo = this.findByNombreGrupo(contacto.getGrupoInvestigacion());
    Date fecha = new Date();
    
    
     String sql = "UPDATE user SET nombre=?, email=?, apellido1=?,apellido2=?,password=?,role=?,universidad=?,region=?,empresa=?,antiguedad=?,reciente=?,activo=?,sexo=?,grupoInvestigacion=?,fundador=?,dblppersonname=?,authorkey=?,fecha=?,pais=? WHERE email=?";
        jdbcTemplate.update(sql, contacto.getNombre(), contacto.getEmail(),
                contacto.getApellido1(), contacto.getApellido2(),this.passwordEncoder.encode(contacto.getPassword()),contacto.getRole(),
                contacto.getUniversidad(),contacto.getRegion(),contacto.getEmpresa(),
                contacto.getAntiguedad(),contacto.getReciente(),contacto.getActivo(),contacto.getSexo(),
               grupo.getNombre(),contacto.getFundador(),contacto.getDblppersonname(),
                contacto.getAuthorkey(),fecha,contacto.getPais(),contacto.getEmail());
     
    ModelAndView mv = new ModelAndView("home");
    
    return mv;


    }
*/
  @RequestMapping(value = "/saveContactEdit", method = RequestMethod.POST)
public String saveContact(@Valid @ModelAttribute("contacto") User contacto) {
    
    Grupo grupo = this.findByNombreGrupo(contacto.getGrupoInvestigacion());
    Date fecha = new Date();
    
    if(grupo == null){
        contacto.setGrupoInvestigacion(null);
    }
  try{
        String sql1 = "SELECT * from user WHERE user_id LIKE '" + contacto.getUser_id() + "';";
       //Query query = getEm().createQuery(sql);
       List userLista =  this.jdbcTemplate.query(sql1, new RowMapper<User>(){
            
           
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
    e.setCuota(rs.getInt(25));
    e.setNombreEmpresa(rs.getString(26));
    return e;
        }
    });
       
       User e;
        String sql5 = "SELECT * from user WHERE user_id LIKE '" + contacto.getUser_id() + "' AND actual=1;";
        e = jdbcTemplate.queryForObject(sql5, new UserRowMapper());
        boolean pass;
        pass = e.getPassword().equals(contacto.getPassword());
       if(!userLista.isEmpty()){ //Todos los que tengan esa id los actualizamos
           
           
               String up = "UPDATE user SET actual = 0  WHERE user_id=?";
               jdbcTemplate.update(up,contacto.getUser_id());
           
       }
       
       if(pass){
    
    
     String sql = "INSERT INTO user (user_id,fecha,orcid,dni,password,apellido2,apellido1,nombre,sexo,dblppersonname,authorkey,role,universidad,region,empresa,pais,email,grupoInvestigacion,antiguedad,reciente,activo,fundador,actual,cuota,nombreEmpresa,primerLogin)" +
             "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?,?, ?,?,?)";
        jdbcTemplate.update(sql, contacto.getUser_id(), fecha,
                contacto.getOrcid(), contacto.getDni(),contacto.getPassword(),contacto.getApellido2(),
                contacto.getApellido1(),contacto.getNombre(),contacto.getSexo(),
                contacto.getDblppersonname(),contacto.getAuthorkey(),contacto.getRole(),contacto.getUniversidad(),
               contacto.getRegion(),contacto.getEmpresa(),contacto.getPais(),
                contacto.getEmail(),
                contacto.getGrupoInvestigacion(),
                contacto.getAntiguedad(),
                contacto.getReciente(),
                contacto.getActivo(),
                contacto.getFundador(),
                1,
                contacto.getCuota(),
                contacto.getNombreEmpresa(),
                0);
        
        
       }else{
            String sql = "INSERT INTO user (user_id,fecha,orcid,dni,password,apellido2,apellido1,nombre,sexo,dblppersonname,authorkey,role,universidad,region,empresa,pais,email,grupoInvestigacion,antiguedad,reciente,activo,fundador,actual,cuota,nombreEmpresa)" +
             "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?,?, ?,?)";
        jdbcTemplate.update(sql, contacto.getUser_id(), fecha,
                contacto.getOrcid(), contacto.getDni(),this.passwordEncoder.encode(contacto.getPassword()),contacto.getApellido2(),
                contacto.getApellido1(),contacto.getNombre(),contacto.getSexo(),
                contacto.getDblppersonname(),contacto.getAuthorkey(),contacto.getRole(),contacto.getUniversidad(),
               contacto.getRegion(),contacto.getEmpresa(),contacto.getPais(),
                contacto.getEmail(),
                contacto.getGrupoInvestigacion(),
                contacto.getAntiguedad(),
                contacto.getReciente(),
                contacto.getActivo(),
                contacto.getFundador(),
                1,contacto.getCuota(),
                contacto.getNombreEmpresa());
       }
  }catch(Exception e){
      return "error";
  }
    ModelAndView mv = new ModelAndView("home");
    if(contacto.getRole() == 1){
    return "redirect:/verLista";
    }else{
        return "home";
    }

    }

  
  @RequestMapping(value = "/saveContactNew", method = RequestMethod.POST)
    public ModelAndView saveContactUser(@Valid @ModelAttribute("contacto") User contacto) {
        
        Grupo grupo = this.findByNombreGrupo(contacto.getGrupoInvestigacion());
        Date fecha = new Date();
           ModelAndView mv = new ModelAndView();

        
        
        if(grupo == null){
            contacto.setGrupoInvestigacion(null);
        }
        
        try{
        
        String prueba = "SELECT count(*) from user where user_id=?";
      int count=  this.jdbcTemplate.queryForInt(prueba, contacto.getUser_id());
      
      if(count == 0){
        
     
       try{
        
    String sql = "INSERT INTO user (user_id,orcid,fecha,dni,password,apellido2,apellido1,nombre,sexo,dblppersonname,authorkey,role,universidad,region,empresa,pais,email,grupoInvestigacion,antiguedad,reciente,activo,fundador,actual,cuota,nombreEmpresa)"
                    + " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?,?,?,?)";
        jdbcTemplate.update(sql,contacto.getUser_id(),contacto.getOrcid(),fecha,contacto.getDni(),  this.passwordEncoder.encode(contacto.getPassword()),
                contacto.getApellido2(), contacto.getApellido1(),contacto.getNombre(),
                contacto.getSexo(),contacto.getDblppersonname(),contacto.getAuthorkey(),contacto.getRole(),
                contacto.getUniversidad(),contacto.getRegion(),contacto.getEmpresa(),contacto.getPais(),contacto.getEmail(),contacto.getGrupoInvestigacion(),contacto.getAntiguedad(),
                contacto.getReciente(),contacto.getActivo(),contacto.getFundador(),1, contacto.getCuota(),contacto.getNombreEmpresa());
   
        mv = new ModelAndView("home");
       }catch(Exception e){
           mv = new ModelAndView("error");
           
       }
      }else{
          
          mv = new ModelAndView("error");
          
      }
      
        }catch(Exception e){
                       mv = new ModelAndView("error");

        } 
    
    
    return mv;
}
    
    
    
@RequestMapping(value = "/grupoUserP", method = RequestMethod.GET)
public ModelAndView grupoUser(HttpServletRequest request,HttpSession session) {
    String userId = request.getParameter("id");
    //User user = this.userService.findById(userId);
    
    Grupo grupo = this.findByNombreGrupo(userId);
    Object prueba = session.getAttribute("user");
          ModelAndView model = new ModelAndView();


    if(prueba == null){
        model.setViewName("login");

      
  }else{
     model = new ModelAndView("grupoUser");
    
    
  
    String nombreLider =""+ grupo.getResponsable().getNombre() + " " + grupo.getResponsable().getApellido1() + " " + grupo.getResponsable().getApellido2(); 
    String nombreCorresponsal =""+ grupo.getCorresponsal().getNombre() + " " + grupo.getCorresponsal().getApellido1() + " " + grupo.getCorresponsal().getApellido2(); 

    model.addObject("grupoN", grupo);
    model.addObject("lider",nombreLider);
    model.addObject("corresponsal",nombreCorresponsal);
    }
    return model;
}


public Grupo findByNombreGrupo(String nombre)  {
      String sql = "SELECT * FROM grupos WHERE nombre LIKE '" + nombre + "';";
      
       //Query query = getEm().createQuery(sql);
       List<Grupo> grupos =  this.jdbcTemplate.query(sql, new RowMapper<Grupo>(){
            
           
         @Override
        public Grupo mapRow(ResultSet rs, int i) throws SQLException {
            Grupo e = new Grupo();
            e.setNombre(rs.getString(1));
            
            e.setUrl(rs.getString(2));
            String idLider = rs.getString(3);
            User lider = findByIdDeVerdad(idLider);
            e.setResponsable(lider);
            String idCorresponsal = rs.getString(4);
            User corresponsal = findByIdDeVerdad(idCorresponsal);
            e.setCorresponsal(corresponsal);
            
            
        
    return e;
        }
    });
       if (grupos.isEmpty()){
           return null;
       }
       return grupos.get(0);
             } 



public User findByIdDeVerdad(String id) throws DataAccessException {
      String sql = "SELECT * from user WHERE user_id = '" + id + "' AND actual=1;";
       //Query query = getEm().createQuery(sql);
       User userN =  this.jdbcTemplate.queryForObject(sql, new RowMapper<User>(){
            
           
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
    e.setCuota(rs.getInt(25));
    e.setNombreEmpresa(rs.getString(26));
    return e;
        }
    });
       
       return userN;
             } 




@RequestMapping(value = "/verHistorial", method = RequestMethod.GET)
public ModelAndView verHistorial(HttpServletRequest request,HttpSession session) {
    String userId = request.getParameter("id");
    //User user = this.userService.findById(userId);
    Object prueba = session.getAttribute("user");
          ModelAndView model = new ModelAndView();


    if(prueba == null){
        model.setViewName("login");

      
  }else{
        try{
    List userN = this.listFindById(userId);
     model = new ModelAndView("verHistorial");
    model.addObject("historial", userN);
    
        }catch(Exception e){
            model = new ModelAndView("error");
        }
    
    }
 
    return model;
}




public List listFindById(String id) throws DataAccessException {
      String sql = "SELECT * from user WHERE user_id = '" + id + "' ORDER BY fecha DESC;";
       //Query query = getEm().createQuery(sql);
       List userN = new ArrayList<>();
        userN =  this.jdbcTemplate.queryForList(sql);
       
       return userN;
             } 



@RequestMapping(value = "/verBajas", method = RequestMethod.GET)
public ModelAndView verBajas(HttpServletRequest request,HttpSession session) {
   
    //User user = this.userService.findById(userId);
    Object prueba = session.getAttribute("user");
          ModelAndView model = new ModelAndView();


    if(prueba == null){
        model.setViewName("login");

      
  }else{
    bajas = this.findPosiblesBajas();
    List<Map<String,Object>> emails = this.findPosiblesBajasEmail();
     model = new ModelAndView("verBajas");
    model.addObject("bajas", bajas);
    List email = new ArrayList<>(); 
    Collection<Object> values = new ArrayList<>();
   for(int i = 0; i<emails.size(); i++){
        values.add(emails.get(i).values().toString());
      
   }
   
   
     for(Object a : values){
       email.add(a);
               }
     
     Object[] a = email.toArray();
   System.out.println(email.get(0));
    model.addObject("emails",email);
    }
    return model;
}

    private List findPosiblesBajas() {
        String sql = "SELECT * from user WHERE actual=1 AND ((YEAR(NOW())+1)-reciente) > 3 AND fundador=0;";
       //Query query = getEm().createQuery(sql);
       List userN = new ArrayList<>();
        userN =  this.jdbcTemplate.queryForList(sql);
       
       return userN;
             } 
    
     private List<Map<String,Object>> findPosiblesBajasEmail() {
        String sql = "SELECT email from user WHERE actual=1 AND ((YEAR(NOW())+1)-reciente) > 3 AND fundador=0;";
       //Query query = getEm().createQuery(sql);
      
       List<Map<String,Object>> userN;
        userN =  this.jdbcTemplate.queryForList(sql);
       
       return userN;
             } 
     @RequestMapping(value = "/recuperarPass", method = RequestMethod.GET)
public ModelAndView recuperarPass(HttpServletRequest request,HttpSession session) {
   
    //User user = this.userService.findById(userId);
    
   
    ModelAndView model = new ModelAndView("recuperarPass");
    
    return model;
}


           @RequestMapping(value = "/recuperarPassAction", method = RequestMethod.POST)
public ModelAndView recuperarPassAction(HttpServletRequest request,HttpSession session,@RequestParam String dni ) {
   
    //User user = this.userService.findById(userId);
    User usuario;
   
    String sql = "SELECT * from user where dni LIKE ? AND actual = 1";
    Object[] param = {dni};
        List<User> us = this.jdbcTemplate.query(sql, param, new UserRowMapper());
        if(!us.isEmpty()){
            usuario = us.get(0);
            String sql2 = "UPDATE user SET newPass = 1  WHERE user_id=?";
            jdbcTemplate.update(sql2,usuario.getUser_id());
            
            
             String caracteres = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRST";
       char[] array = caracteres.toCharArray();
       Integer nCaracteres = caracteres.toCharArray().length;
         char[] aleatorio = new char[nCaracteres];
           Random r = new Random();
       for(int i = 0; i<nCaracteres;i++){
         aleatorio[i] = array[r.nextInt(nCaracteres-1)];
       }
       
            
            String url = Arrays.toString(aleatorio) + usuario.getNombre() + usuario.getApellido1();
       
       String urlSecreta = this.passwordEncoder.encode(url);
       
       String sql3 = "INSERT into recPass (user_id,url) VALUES (?,?)";
       jdbcTemplate.update(sql3,usuario.getUser_id(),urlSecreta);
       
       String urlFinal= "http://localhost:8080/TFGPruebaFinal/recuperarPassActionlRec?url=" + urlSecreta;
       
       String cuerpo = "Recupere su contraseña de SISTEDES a través de la siguiente URL: "+ urlFinal;
       
        try {
            enviarConGMail(usuario.getEmail(),"Recuperar password",cuerpo);
        } catch (javax.mail.MessagingException ex) {
            Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, null, ex);
        }
       
       
        }
        
           ModelAndView model = new ModelAndView("login");
    
             return model;
        
        
}

  private static void enviarConGMail(String destinatario, String asunto, String cuerpo) throws AddressException, javax.mail.MessagingException {
        
            // Esto es lo que va delante de @gmail.com en tu cuenta de correo. Es el remitente también.
            String remitente = "sistedestfg";  //Para la dirección nomcuenta@gmail.com
            String pass = "sistedesprueba";
            Properties props = System.getProperties();
         
    props.put("mail.smtp.host", "smtp.gmail.com");  //El servidor SMTP de Google
    props.put("mail.smtp.user", remitente);
    props.put("mail.smtp.clave", "sistedesprueba");    //La clave de la cuenta
    props.put("mail.smtp.auth", "true");    //Usar autenticación mediante usuario y clave
    props.put("mail.smtp.starttls.enable", "true"); //Para conectar de manera segura al servidor SMTP
    props.put("mail.smtp.port", "587"); //El puerto SMTP seguro de Google
            
            Session session = Session.getDefaultInstance(props);
    MimeMessage message = new MimeMessage(session);

    try {
                try {
                    message.setFrom(new InternetAddress(remitente));
                } catch (javax.mail.MessagingException ex) {
                    Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, null, ex);
                }
        message.addRecipients(Message.RecipientType.TO, destinatario);  //Se podrían añadir varios de la misma manera
        message.setSubject(asunto);
        message.setText(cuerpo);
                javax.mail.Transport transport = session.getTransport("smtp");
        transport.connect("smtp.gmail.com", remitente, pass);
        transport.sendMessage(message, message.getAllRecipients());
        transport.close();
    }
    catch (MessagingException me) {
        me.printStackTrace();   //Si se produce un error
    }
 
}
  
   @RequestMapping(value = "/recuperarPassActionlRec", method = RequestMethod.GET)
public ModelAndView recuperarPassActionlRec(HttpServletRequest request,HttpSession session) {
     ModelAndView model = new ModelAndView();
    String url = request.getParameter("url");
    
    String sql = "SELECT user_id from recPass where url LIKE ? " ;
    
    Object[] param = {url};
   String lista =  this.jdbcTemplate.queryForObject(sql, param, String.class);
   
   String sql1 = "SELECT * FROM user WHERE user_id LIKE ? AND actual=1 AND newPass=1";

    Object[] parame = {lista};
    List<User> us = this.jdbcTemplate.query(sql1, parame, new UserRowMapper());
    
    if(!us.isEmpty()){
        

            model.setViewName("cambiarPass");
        
        
        
        
    }else{
        //Aqui iria la de error
          model.setViewName("login");
         
    }
    
    return model;
 
    
    
    
}

@RequestMapping(value = "/cambiarPassFinal", method = RequestMethod.POST)
public ModelAndView cambiarPassFinal(@RequestParam String dni,@RequestParam String password,HttpServletRequest request,HttpSession session) {
     ModelAndView model = new ModelAndView();
    
    
     String up = "UPDATE user SET newPass = 0, password=?  WHERE dni=? AND actual=1";
      jdbcTemplate.update(up,this.passwordEncoder.encode(password),dni);
      String persona = "SELECT user_id FROM user WHERE dni LIKE ? and actual=1";
      Object[] param = {dni};
      String us = jdbcTemplate.queryForObject(persona, param, String.class);
    String delete = "DELETE FROM recPass where user_id LIKE ?";
    jdbcTemplate.update(delete, us);
   
            model.setViewName("login");
        
        
        
  
    
    return model;
 
    
    
    
}

@RequestMapping(value="/loginSecurity", method=RequestMethod.GET)
public String loginJ() {
	return "loginSecurity";
}


@RequestMapping(method = RequestMethod.POST, value = "/uploadExcelFile")
public ModelAndView uploadFile(Model model, @RequestParam(value = "file") MultipartFile file) throws IOException {
    InputStream in = file.getInputStream();
     ModelAndView mw = new ModelAndView();

    File currDir = new File(".");
    String path = currDir.getAbsolutePath();
    fileLocation = path.substring(0, path.length() - 1) + file.getOriginalFilename();
    
    FileOutputStream f = new FileOutputStream(fileLocation);
  
    int ch = 0;
    while ((ch = in.read()) != -1) {
        f.write(ch);
    }
    f.flush();
    f.close();
    model.addAttribute("message", "File: " + file.getOriginalFilename() 
      + " has been uploaded successfully!");
    mw.setViewName("home");
    
    
    
    this.readPOI();
    
    return mw;
  
 
   // return "readPOI";
}

@RequestMapping(method = RequestMethod.GET, value = "/readPOI")
public ModelAndView readPOI() throws IOException {
     
  if(fichero.isEmpty() || fichero.equals("")){
      fichero = "";
  }
    
         ModelAndView model = new ModelAndView();
         List<Fila> prove = new ArrayList<>();

  if (fileLocation != null) {
      if (fileLocation.endsWith(".xlsx") || fileLocation.endsWith(".xls")) {
          try{
        this.l = new Lector(fileLocation);
               prove =   l.getFile();
               if(prove.isEmpty()){
                   System.out.println("HE ENTRADO");
               }else{
                   int i = 2;
                   for(Fila f : prove){
                       this.fila = f;
                       
                       if(!"".equals(this.fila.getUser_id())){
                          int prueba = this.comprobarId(this.fila.getUser_id());  
                          if(prueba ==0){
                              try{
               this.insertarExcel(fila);
                              }catch(Exception e){
                                this.fichero  = fichero + "ERROR LÍNEA " + i+  ": Error en la inserción, utilice la plantilla facilitada.\n";

                              }
                          }else{
                         this.fichero  = fichero + "ERROR LÍNEA " +i+": ID ya existente en el portal\n";

                          }
                       }
                       i++;
                   }
               }
          }catch(Exception e){
                                       this.fichero  = fichero + "Error en el archivo, utilice la plantilla por favor";

          }
      } else {
         //importacion CSV
         
         this.loadObjectList(fileLocation);
        
      }
  } else {
      model.addObject("message", "File missing! Please upload an excel file.");
  }
  
  if(fichero.isEmpty() || fichero.equals("")){
      fichero = "Inserción realizada con exito";
  }
 
    model.setViewName("verLista");
  return model;
}


private void insertarExcel(Fila f){
    
     Date fecha = new Date();
   
     int empresa=0;
     int activo = 0;
     int fundador = 0;
     String pais;
     if(f.getEmpresa().equalsIgnoreCase("Si") || f.getEmpresa().equalsIgnoreCase("Sí")){
         empresa=1;
     }else{
         empresa=0;
     }
        if(f.getActivo().equalsIgnoreCase("Sí") ||f.getActivo().equalsIgnoreCase("Si")){
         activo=1;
     }else{
         activo=0;
     }
     
     if(f.getForeigner() == null){
         pais = "ES España";
     }else{
         pais = f.getForeigner();
     }
     if(f.getUser_id().charAt(2) == 'F'){
         fundador = 1;
     }
     
     if(f.getUniversidad().length() <=5){
            String consulta="select nombreUniv from universidad where siglasUni LIKE '%" + f.getUniversidad() + "';"; //Esta consulta es la que falla
              String universidad = this.jdbcTemplate.queryForObject(consulta,String.class);
              if(universidad != null){
              f.setUniversidad(universidad);
              }
     }
     
     
     
   

    String sql = "INSERT INTO user (user_id,fecha,orcid,dni,password,apellido2,apellido1,nombre,sexo,dblppersonname,authorkey,role,universidad,region,empresa,pais,email,grupoInvestigacion,antiguedad,reciente,activo,fundador,actual,primerLogin)" +
             "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?,?,?)";
        jdbcTemplate.update(sql, f.getUser_id(), fecha,
                null, f.getEmail(),this.passwordEncoder.encode(f.getEmail()),f.getApellido2(),
                f.getApellido1(),f.getNombre(),f.getSexo(),
                f.getDblppersoname(),f.getDblpauthorkey(),0,f.getUniversidad(),
               f.getRegion(),empresa,pais,
                f.getEmail(),
                null,
                f.getAntiguedad(),
                f.getReciente(),
                activo,
                fundador,
                1,
                1);
        
   
  
}


public int comprobarId(String id){
    Integer n;
     String sql = "SELECT count(*) from user WHERE user_id LIKE '" + id + "' AND actual=1;";
     n = this.jdbcTemplate.queryForObject(sql, Integer.class);
    
    return n;
}


public void loadObjectList(String fileLocation) {
    
        BufferedReader br = null;
        String line = "";
        String cvsSplitBy = ";";
   try {
            br = new BufferedReader(new FileReader(new File(fileLocation)));
            br.readLine();
            int i = 2;
            while ((line = br.readLine()) != null) {

                
                // use comma as separator
                String[] country = line.split(cvsSplitBy);
                
               Fila f = this.lectura(country);
               int prueba = this.comprobarId(f.getUser_id());  
                          if(prueba ==0){
                              try{
               this.insertarExcel(f);
                              }catch(Exception e){
                                this.fichero  = fichero + "ERROR LÍNEA " + i+": Error en la inserción, utilice la plantilla facilitada.\n";

                         
                              }
                          }else{
                         this.fichero  = fichero + "ERROR LÍNEA " +i+ ": ID ya existente en el portal\n";

                          }
               i++;
            }

        } catch (FileNotFoundException e) {
            this.fichero = this.fichero + "Fichero no encontrado\n";
        } catch (IOException e) {
            this.fichero = this.fichero + "Error de formato\n";
        }finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
}



    private Fila lectura(String[] prueba){
         Fila f = new Fila();
         f.setUser_id(prueba[0]);
        
         f.setApellido1(prueba[1]);
         f.setApellido2(prueba[2]);
         f.setNombre(prueba[3]);
         f.setSexo(prueba[4]);
         f.setDblppersoname(prueba[5]);
         f.setDblpauthorkey(prueba[6]);
         f.setUniversidad(prueba[8]);
         f.setRegion(prueba[9]);
         f.setEmpresa(prueba[10]);
         f.setCentro(prueba[11]);
         f.setForeigner(prueba[12]);
         f.setEmail(prueba[13]);
         f.setAntiguedad(Integer.parseInt(prueba[17]));
         f.setActivo(prueba[18]);
         f.setReciente(Integer.parseInt(prueba[19]));
         
         
         return f;
         
         

        
       
       
    }
    
    public void generarArchivoCSV(String file){
        final String delim = ";";
        final String NEXT_LINE = "\n";
        final String elementos[] = {"ID", "Apellido 1","Apellido 2","Nombre","Sexo","dblpperson name","dblp author key","Grupo","Universidad/Centro","Region","Empresa", "Centro", "Foreigner", 
            "Email(s)", "responsable", "Corresponsal", "Junta Dir." ,"Antiguedad" , "ACTIVO?", "reciente"};
        final String ejemplo[] = {"SN00162", "App1_90","App2_90","Nombre_90","M","Nombre_90 App1_90","homepages/Nombre_90App1_90","","UCLM","Castilla-La Mancha","", "", "ES España", 
            "email_90", "", "", "" ,"2010" , "Sí", "2017"};
																																		
        try {
			FileWriter fw = new FileWriter(file);

                        
                        for(int i = 0; i< elementos.length ; i++){
			
			

			fw.append(elementos[i]);
			fw.append(delim);
		
                        }
			fw.append(NEXT_LINE);
                        for(int j=0; j< ejemplo.length; j++){
			fw.append(ejemplo[j]);
                        fw.append(delim);

                        }
			fw.flush();
			fw.close();
		} catch (IOException e) {
			// Error al crear el archivo, por ejemplo, el archivo 
			// está actualmente abierto.
			e.printStackTrace();
		}
    }
    
    


    
    
    
    
   
   


		
	
     
     
        
        
    



}
