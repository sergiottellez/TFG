/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import java.util.List;
import model.User;

/**
 *
 * @author sergiottellez
 */
public interface UserService {
    
      User findByEmail(String email);
         User findById(Integer id);
         public void deleteUser(User user);
         public void newUser(String email, String password, String name, String apellido1, String apellido2, Integer role); //meter formulario creación
         public void editUser(User user,String email, String password, String name, String apellido1, String apellido2, Integer role); //meter formulario editar
         public void hacerAdmin(User user); //para darle un botón y ya está, previa confirmación
         public List<User> orderByEmail();
         public List<User>  orderByNombre();
         public List<User>  orderByEmpresa();
         public List<User>  orderByApellido();
         public List<User> obtenerLista();
         public void edit(User user);
         User login(String email,String pass);
    
    
    
}
