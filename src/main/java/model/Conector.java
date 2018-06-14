/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;



import org.springframework.jdbc.datasource.DriverManagerDataSource;

public class Conector {


    public DriverManagerDataSource conectar(){

        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://eu-cdbr-west-02.cleardb.net:3306/heroku_e4e414a8b0455e6?reconnect=true?useUnicode=true&characterEncoding=UTF-8");
        dataSource.setUsername("bd43ae8bef6706");
        dataSource.setPassword("0d7c6c1b");
        return dataSource;


    }
}
