/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import dao.UserDao;
import dao.UserDaoImpl;
import java.util.List;
import model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 *
 * @author sergiottellez
 */

@Service
public class UserServiceImpl implements UserService{
    
   
    UserDaoImpl userDao;
    
    @Autowired
    private PasswordEncoder passwordEncoder;

    public UserDaoImpl getUserDao() {
        return userDao;
    }

    
     @Autowired
    public void setUserDao(UserDaoImpl userDao) {
        this.userDao = userDao;
    }

    @Override
    public User findByEmail(String email) {
        return userDao.findByEmail(email);
    }

    @Override
    public User findById(Integer id) {
        
        return userDao.findById(id);
    }

    @Override
    public void deleteUser(User user) {
         userDao.deleteUser(user);
    }

    @Override //Hay que cambiar el userDao para ponerle todos los campos
    public void newUser(String email, String password, String name, String apellido1, String apellido2, Integer role) {
            userDao.newUser(email, passwordEncoder.encode(password), name, apellido1, apellido2, role);

    }

    @Override
    public void editUser(User user, String email, String password, String name, String apellido1, String apellido2, Integer role) {
        userDao.editUser(user, email, passwordEncoder.encode(password), name, apellido1, apellido2, role);
    }

    @Override
    public void hacerAdmin(User user) {
        userDao.hacerAdmin(user);
    }

    @Override
    public List<User> orderByEmail() {
        return userDao.orderByEmail();
    }

    @Override
    public List<User> orderByNombre() {
        return userDao.orderByNombre();
    }

    @Override
    public List<User> orderByEmpresa() {
        return userDao.orderByEmpresa();
    }

    @Override
    public List<User> orderByApellido() {
        return userDao.orderByApellido();
    }

    @Override
    public List<User> obtenerLista() {
        return userDao.obtenerLista();
    }

    @Override
    public void edit(User user) {
        userDao.edit(user);
    }

    @Override
    public User login(String email, String pass) {
        return userDao.login(email, pass);
    }
    
}
