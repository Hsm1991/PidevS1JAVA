/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
/**
 *
 * @author saif
 */
public class MaConnexion {
    final String URL="jdbc:mysql://localhost:3306/projetpidev";
    final String USR="root";
    final String PWD="";
    
    
    Connection cnx ;
    public static MaConnexion  instance ;
    
    
    private MaConnexion(){
        try{
            cnx=DriverManager.getConnection(URL,USR,PWD);
            System.out.println("connexion etablie avec success!");
        }
        catch (SQLException ex){
            System.err.println(ex.getMessage());
            
        }
    }
        
        public static MaConnexion getInstance(){
            if (instance == null) {
                instance = new MaConnexion();
            }
            return instance;
        }
        public Connection getCnx(){
            return cnx;
        }
        
}
