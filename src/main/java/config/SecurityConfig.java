/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package config;

import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import service.LoginServiceImpl;

/**
 *
 * @author sergiottellez
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
 
 @Autowired
 DataSource dataSource;
 
 @Autowired
 LoginServiceImpl loginServiceImpl;
 
 @Autowired
 public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
  auth.userDetailsService(loginServiceImpl);
  auth.authenticationProvider(authenticationProvider());
 }
 
 @Bean
 public DaoAuthenticationProvider authenticationProvider(){
  DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
  authenticationProvider.setUserDetailsService(loginServiceImpl);
  authenticationProvider.setPasswordEncoder(passwordEncoder());
  
  return authenticationProvider;
 }
 
 @Bean
 public PasswordEncoder passwordEncoder(){
  return new BCryptPasswordEncoder();
 }
 
 @Override
 protected void configure(HttpSecurity http) throws Exception{
  http.csrf().disable();
  
  http.authorizeRequests().antMatchers("/login", "/user/signup", "/user/register").permitAll();
  http.authorizeRequests().antMatchers("/verLista.htm", "/verLista")
.access("hasRole(1)");
  http.authorizeRequests().antMatchers("/verLista")
.access("hasRole(1)");
  
  http.authorizeRequests().and().formLogin()
   .loginProcessingUrl("/j_spring_security_check")
   .loginPage("/login")
   .failureUrl("/login?error=true")
   .usernameParameter("email")
   .passwordParameter("password")
   .and().logout().logoutUrl("/j_spring_security_logout").logoutSuccessUrl("/login")
   .and().rememberMe()
   .tokenRepository(persistentTokenRepository())
   .tokenValiditySeconds(60*60)
   .and().exceptionHandling().accessDeniedPage("/accessDenied");
 }
 
 @Bean
 public PersistentTokenRepository persistentTokenRepository(){
  JdbcTokenRepositoryImpl db = new JdbcTokenRepositoryImpl();
  db.setDataSource(dataSource);
  
  return db;
 }
 
}