/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import entities.Role;

/**
 *
 * @author user
 */
public class MyConnexion {
    
    public String url="jdbc:mysql://localhost:3306/projetpidev";
    public String login="root";
    public String pwd ="";
    public static MyConnexion instance ;
 
    
    Connection cnx ;

    private MyConnexion() {
        
        try {
          cnx=  DriverManager.getConnection(url,login,pwd);
          System.out.println("connexion etablie ");
                 
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
               

    } 
   public Connection getCnx(){
    
   return cnx;
}
    public static MyConnexion getInstance(){
    if (instance == null) { 
        instance = new MyConnexion();
    }
    return instance;
}

 
    
}
    
    

