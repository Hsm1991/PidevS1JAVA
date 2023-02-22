/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.res;
import utils.MyConnection;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import static javax.swing.text.html.HTML.Tag.SELECT;

/**
 *
 * @author MLLE-BAHRI
 */
public class Métier {
    Connection cnx2;
    
    public Métier() {
        cnx2 = MyConnection.getInstance().getCnx();
    }
    
    public  float remiseReservation (res r , float prix){
        
        float p=prix;
        int n = 0 ;
        String requete3 = "SELECT * FROM res where Nom_artiste = " + r.getNom_artiste();
        System.out.println(r.getNom_artiste());
        try {
             Statement st = cnx2.createStatement();
            ResultSet rs = st.executeQuery(requete3);
            System.out.println("rs");
            while (rs.next()){
                n++;
            }
        } catch (SQLException ex) {
            Logger.getLogger(Métier.class.getName()).log(Level.SEVERE, null, ex);
        }
       if (n>3){
           p = 0.9f * prix;
       }
       if (n>6){
           p = 0.8f * prix ;
       }
       if (n>9){
           p = 0.7f * prix;
       }
       
    return p;
    }
      
      
     
   
}
    
    
    
    
 
