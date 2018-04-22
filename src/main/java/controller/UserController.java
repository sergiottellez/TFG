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
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;

import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.ModelAttribute;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;


/**
 *
 * @author sergiottellez
 */

@Controller
@Scope("session")
@RequestMapping(value="/")
@SessionAttributes({"user","tipoListado","textoTipoBusqueda"})
public class UserController {
    

    @Autowired
    private UserDaoImpl userService; //inject xml
    
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    
    
    @Autowired
    private  User user;
    
    @Autowired
    private UsernamePasswordAuthenticationToken princi;
    
    
    @Autowired
    private List listado;
    
 
    
    
  
    
    @Autowired
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
    
    
    
    @RequestMapping(value={"/verLista.htm", "/verLista"},method={RequestMethod.POST, RequestMethod.GET})
    public ModelAndView verLista(HttpSession session,HttpServletRequest request)
    {
        Integer tipoListado = 6;
        ModelAndView mav=new ModelAndView();
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
        
       
       // List datos = this.userService.obtenerLista(); //Prueba de UserDao
      
    }

          
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
 public ModelAndView verifyLogin(@RequestParam String email,@RequestParam String password,HttpSession session){
     
       String sql = "SELECT * from user WHERE email = '" + email + "' AND actual=1";
       //Query query = getEm().createQuery(sql);
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
  ModelAndView model = new ModelAndView();
  model.setViewName("home");
  session.setAttribute("tipoListado", 6);
  session.setAttribute("textoTipoBusqueda", "Datos");

  
  
  return model;
 }
 

 @RequestMapping(value="/accessDenied", method=RequestMethod.GET)
 public ModelAndView accessDenied(){
  ModelAndView model = new ModelAndView();
  model.setViewName("errors/access_denied");
  return model;
 }
 
@RequestMapping(value = "/editUser", method = RequestMethod.GET)
public ModelAndView editUser(HttpServletRequest request,HttpSession session) {
    String userId = request.getParameter("id");
    //User user = this.userService.findById(userId);
    
    User userN = this.findById(userId);
    ModelAndView model = new ModelAndView("userEditForm");
    model.addObject("contacto", userN);
    
    
    
    String sql = "SELECT nombreReg from regionEsp;";
    List<String> region = this.jdbcTemplate.queryForList(sql, String.class);
    model.addObject("region",region);
 
    return model;
}
  public User findById(String email) throws DataAccessException {
      String sql = "SELECT * from user WHERE email = '" + email + "';";
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
        String sql = "DELETE from user where email= '" + userN.getEmail() + "';" ; 
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
    
    User userN = this.findById(userId);
   deleteUser(userN);
 
   return new ModelAndView("home");
}


@RequestMapping(value = "/newUser", method = RequestMethod.GET)
public ModelAndView newUser(HttpServletRequest request) {
    //String userId = (request.getParameter("id"));
    //User user = this.userService.findById(userId);
    
   // User user = this.findById(userId);
   
   User newContact = new User();
   
    ModelAndView model = new ModelAndView("userNewForm");
    model.addObject("contacto", newContact);
 
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
              String consulta="select count(*) from user where universidad= '" + univ + "' AND actual=1;"; //Esta consulta es la que falla
              Integer datosNumero = this.jdbcTemplate.queryForObject(consulta,Integer.class);
              String consulta2="select count(activo) from user where universidad= '" + univ + "' AND activo=" + 1 + " AND actual=1;"; //Esta consulta es la que falla
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
               String consulta="select count(*) from user where region= '" + univ + "' AND actual=1;"; //Esta consulta es la que falla
              Integer datosNumero = this.jdbcTemplate.queryForObject(consulta,Integer.class);
              String consulta2="select count(*) from user where region= '" + univ + "' AND activo= "+ 1 +  " AND actual=1;"; //Esta consulta es la que falla
              Integer datosNumero2 = this.jdbcTemplate.queryForObject(consulta2,Integer.class);
              Tuple<Integer,Integer> tupla = new Tuple(datosNumero,datosNumero2);
              
              datosRegion.put(univ, tupla);
           
       }
       
       
       
        String sql2="SELECT count(*) FROM user WHERE sexo='" + 'M' + "' AND actual=1;";
   
        
       
  Integer datosMujeres = this.jdbcTemplate.queryForObject(sql2,Integer.class); 
  
    String sql3="SELECT count(*) FROM user WHERE sexo='" + 'M' + "' AND activo=" + 1 + " AND actual=1;";
   
        
       
  Integer datosMujeresActivas = this.jdbcTemplate.queryForObject(sql3,Integer.class);   
  
     String sql4="SELECT count(*) FROM user WHERE actual=1";
   
        
       
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
    
  @RequestMapping(value = "/logout", method = RequestMethod.GET)
  public final String logoutPage( HttpServletRequest request,
       HttpServletResponse response) {
    final Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    if (auth != null) {
      new SecurityContextLogoutHandler().logout(request, response, auth);
    }
    return "redirect:/login?logout";
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
public ModelAndView saveContact(@Valid @ModelAttribute("contacto") User contacto) {
    
    Grupo grupo = this.findByNombreGrupo(contacto.getGrupoInvestigacion());
    Date fecha = new Date();
  
        String sql1 = "SELECT * from user WHERE user_id = " + contacto.getUser_id() + ";";
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
    return e;
        }
    });
       
       
       if(!userLista.isEmpty()){ //Todos los que tengan esa id los actualizamos
           
           
               String up = "UPDATE user SET actual = 0  WHERE user_id=?";
               jdbcTemplate.update(up,contacto.getUser_id());
           
       }
    
    
     String sql = "INSERT INTO user (user_id,fecha,orcid,dni,password,apellido2,apellido1,nombre,sexo,dblppersonname,authorkey,role,universidad,region,empresa,pais,email,grupoInvestigacion,antiguedad,reciente,activo,fundador,actual)" +
             "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?,?)";
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
                1);
        
        
        
     
    ModelAndView mv = new ModelAndView("home");
    
    return mv;


    }

  
  @RequestMapping(value = "/saveContactNew", method = RequestMethod.POST)
    public ModelAndView saveContactUser(@Valid @ModelAttribute("contacto") User contacto) {
        
        Grupo grupo = this.findByNombreGrupo(contacto.getGrupoInvestigacion());
        Date fecha = new Date();
        
        
     
       
        
    String sql = "INSERT INTO user (fecha,dni,password,apellido2,apellido1,nombre,sexo,dblppersonname,authorkey,role,universidad,region,empresa,pais,email,grupoInvestigacion,antiguedad,reciente,activo,fundador,actual)"
                    + " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?,?,?)";
        jdbcTemplate.update(sql,fecha,contacto.getDni(),  this.passwordEncoder.encode(contacto.getPassword()),
                contacto.getApellido2(), contacto.getApellido1(),contacto.getNombre(),
                contacto.getSexo(),contacto.getDblppersonname(),contacto.getAuthorkey(),contacto.getRole(),
                contacto.getUniversidad(),contacto.getRegion(),contacto.getEmpresa(),contacto.getPais(),contacto.getEmail(),grupo.getNombre(),contacto.getAntiguedad(),
                contacto.getReciente(),contacto.getActivo(),contacto.getFundador(),1);
   
        
        
       
      
             
   ModelAndView mv = new ModelAndView("home");
    
    return mv;
}
    
    
    
@RequestMapping(value = "/grupoUserP", method = RequestMethod.GET)
public ModelAndView grupoUser(HttpServletRequest request,HttpSession session) {
    String userId = request.getParameter("id");
    //User user = this.userService.findById(userId);
    
    Grupo grupo = this.findByNombreGrupo(userId);
    ModelAndView model = new ModelAndView("grupoUser");
    
    
  
    String nombreLider =""+ grupo.getResponsable().getNombre() + " " + grupo.getResponsable().getApellido1() + " " + grupo.getResponsable().getApellido2(); 
    String nombreCorresponsal =""+ grupo.getCorresponsal().getNombre() + " " + grupo.getCorresponsal().getApellido1() + " " + grupo.getCorresponsal().getApellido2(); 

    model.addObject("grupoN", grupo);
    model.addObject("lider",nombreLider);
    model.addObject("corresponsal",nombreCorresponsal);
 
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
    return e;
        }
    });
       
       return userN;
             } 




@RequestMapping(value = "/verHistorial", method = RequestMethod.GET)
public ModelAndView verHistorial(HttpServletRequest request,HttpSession session) {
    String userId = request.getParameter("id");
    //User user = this.userService.findById(userId);
    
    List userN = this.listFindById(userId);
    ModelAndView model = new ModelAndView("verHistorial");
    model.addObject("historial", userN);
    
    
    
  
 
    return model;
}




public List listFindById(String id) throws DataAccessException {
      String sql = "SELECT * from user WHERE user_id = '" + id + "' ORDER BY fecha DESC;";
       //Query query = getEm().createQuery(sql);
       List userN = new ArrayList<>();
        userN =  this.jdbcTemplate.queryForList(sql);
       
       return userN;
             } 





        
    
    
            
           
  
       
    

    
}
