/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package config;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 *
 * @author sergiottellez
 */
public class MyAuthenticationProvider implements AuthenticationProvider {
 
    private UserDetailsService userDetailsService;
 
    public void setUserDetailsService(UserDetailsService userDetailsService) {
 
        this.userDetailsService = userDetailsService;
    }
 
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
 
        UserDetails userDetails = this.userDetailsService.loadUserByUsername(authentication.getName());
        if (userDetails != null && new BCryptPasswordEncoder().matches(authentication.getCredentials().toString(),
                        userDetails.getPassword())) {
            return new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
        }
 
        throw new BadCredentialsException("Bad credentials");
    }



    @Override
    public boolean supports(Class<?> type) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}