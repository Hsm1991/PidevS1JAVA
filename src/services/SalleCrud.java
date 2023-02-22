/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import utils.MyConnection;
import entities.salle;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author MLLE-BAHRI
 */
public class SalleCrud {
    /*
     //cnx2;
    public SalleCrud(){
       Connection cnx2=MyConnection.getInstance().getCnx();
    }*/
    public void AjouterSalle(salle S){
            Connection cnx2=MyConnection.getInstance().getCnx();
        
            String requete2 = "INSERT INTO salle (type,statu,prix,nom,capacite) VALUES(?,?,?,?,?);";
            try {
            PreparedStatement pst =cnx2.prepareStatement(requete2);
            
            pst.setString(1, S.getType());
            pst.setString(2, S.getStatu());
            pst.setFloat(3, S.getPrix());
            pst.setObject(4,"Salle" + (getLastIdsalle()+1));
            pst.setObject(5, 400);
            pst.executeUpdate();
            System.out.println("Salle ajouté avec succés!!!");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        
    }
    
    
    
    public ObservableList<salle> afficherSalle() {
        ObservableList<salle> myList = FXCollections.observableArrayList();
        Connection cnx2=MyConnection.getInstance().getCnx();
        try {
            String requete3 = "SELECT * FROM salle";
            Statement st = cnx2.createStatement();
            ResultSet rs = st.executeQuery(requete3);
            while (rs.next()) {
                salle e = new salle();
                e.setNum_salle(rs.getInt("num_salle"));
                e.setPrix(rs.getFloat("prix"));
                e.setStatu(rs.getString("statu"));
                e.setType(rs.getString("type"));
               
                myList.add(e);
            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());

        }
             return myList;
}
    
    public ObservableList<salle> afficherSalle2() {
        ObservableList<salle> myList = FXCollections.observableArrayList();
        Connection cnx2=MyConnection.getInstance().getCnx();
        try {
            String requete3 = "SELECT * FROM salle";
            Statement st = cnx2.createStatement();
            ResultSet rs = st.executeQuery(requete3);
            while (rs.next()) {
                salle e = new salle();
                //e.setID_salle(rs.getInt("IDsalle"));
                e.setNum_salle(rs.getInt("num_salle"));
                e.setPrix(rs.getFloat("prix"));
                e.setStatu(rs.getString("statu"));
                e.setType(rs.getString("type"));
               
                myList.add(e);
            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());

        }
             return myList;
}
    
    public void supprimerSalle(salle s)  {

        try {
            Connection cnx2=MyConnection.getInstance().getCnx();
       
            String requete4 = "delete from salle where num_salle = " + s.getNum_salle();
            PreparedStatement ste = cnx2.prepareStatement(requete4);
            ste.executeUpdate();

            System.out.println("salle supprimé aves succeés");
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }
    
    
    public void ChercherSalle(int id) throws SQLException {
        System.out.println("entre l'Id de la salle à Chercher");
        Scanner sc = new Scanner(System.in);
        String h = sc.nextLine();
        List<salle> salle = new ArrayList<>();

        try {
            Connection cnx2=MyConnection.getInstance().getCnx();

            String sql = "SELECT * FROM salle where num_salle = " + h;
        
            ResultSet rs;
            PreparedStatement ste;
            ste = cnx2.prepareStatement(sql);
            rs = ste.executeQuery();
            while (rs.next()) {
                salle e = new salle();
                
                e.setNum_salle(rs.getInt("num_salle"));
                e.setType(rs.getString("type"));
                e.setStatu(rs.getString("statu"));
                e.setPrix(rs.getFloat("prix"));
                System.out.println(e);
                salle.add(e);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());

        }
    }
    
   
    public void ModifierSalle (salle e, int id) {
        try {
             Connection cnx2=MyConnection.getInstance().getCnx();

            String sql = "UPDATE salle SET  type = ? ,statu = ? , prix = ? WHERE num_salle = "+id;
            PreparedStatement ste = cnx2.prepareStatement(sql);
          
            
            ste.setString(1, e.getType());
            ste.setString(2, e.getStatu());
            ste.setFloat(3, e.getPrix());
            System.out.println("salle ajoutée est : " + e);
            ste.executeUpdate();
            System.out.println("Modification effectuée avec succés !!");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        
    }
    
    public int getLastIdsalle(){
         Connection cnx2=MyConnection.getInstance().getCnx();
         int latest =  0 ;
        try {
            String requete3 = "SELECT * FROM salle";
            Statement st = cnx2.createStatement();
            ResultSet rs = st.executeQuery(requete3);
            
            while (rs.next()) {
               latest = rs.getInt("num_salle");
            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());

        }
        return latest;
    }

}