/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package config;



import org.flywaydb.core.Flyway;

public class Migrations {
    public static void main(String[] args) throws Exception {
        Flyway flyway = new Flyway();
        flyway.setDataSource(System.getenv("jdbc:mysql://localhost:3306/TFG?zeroDateTimeBehavior=convertToNull"),
                             System.getenv("root"),
                             System.getenv("jonimoni"));
        flyway.migrate();
    }
}
