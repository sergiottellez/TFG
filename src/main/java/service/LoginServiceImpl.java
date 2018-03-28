/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import dao.LoginDao;
import java.util.ArrayList;
import java.util.List;
import model.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

/**
 *
 * @author sergiottellez
 */
public class LoginServiceImpl implements UserDetailsService {

    LoginDao loginDao;

    public LoginDao getLoginDao() {
        return loginDao;
    }

    @Autowired
    public void setLoginDao(LoginDao loginDao) {
        this.loginDao = loginDao;
    }
    
    
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UserInfo userInfo = loginDao.findUserInfo(email);
        
        if(userInfo == null){
            throw new UsernameNotFoundException("Email not found");
            
        }
        
        List<GrantedAuthority> grantList = new ArrayList<GrantedAuthority>();
        
        //MODIFICAR SI PUEDEN ACCEDER SOCIOS
        GrantedAuthority authority = new SimpleGrantedAuthority("1");
        grantList.add(authority);
        
        UserDetails userDetails = new User(userInfo.getEmail(),userInfo.getPassword(),grantList);
        
        return userDetails;

    }
    
    
    
}
