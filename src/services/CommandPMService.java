package services;
import utils.MyConnection;
import entities.ProduitPM;
import entities.commandPM;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javax.mail.MessagingException;
import services.mail;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/*package service;

import connectionpck.MyConnection;
import java.sql.Connection;
import connectionpck.MyConnection;
import entities.ProduitPM;
import iservice.IserviceProduit;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javax.swing.JOptionPane;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javax.swing.JOptionPane;


/**
 *
 * @author elbootic.com
 */

public class CommandPMService {
   
    public Connection cnx;
     public CommandPMService() {
          cnx = MyConnection.getInstance().getCnx();
         
}
     
     
     
     public void ajouterCommande(commandPM  commande , String type) throws SQLException, MessagingException{
         System.out.println("aaaaaaaa");
         ProduitPMService ps = new ProduitPMService();
        // ProduitPM pr = ps.getProduits();
         System.out.println(ps.GetOne(commande.getIDProd()));
         if(type == "artiste"){
   if(ps.GetOne(commande.getIDProd()) != null){
         if (ps.GetOne(commande.getIDProd()).getQuantiteP() >= commande.getQuantiteCpm()){
             
             ps.modifierQuantite(commande.getQuantiteCpm(), commande.getIDProd());

         try {
            String req = "INSERT INTO commandpm( IDProd,NomProd,referenceCM ,date,quantiteCpm,iduser,type) values (?,?,?,?,?,?,?)";
            
               System.out.println("ee");
            PreparedStatement pst =  cnx.prepareStatement(req);
           
            pst.setInt(1, commande.getIDProd());
            pst.setString(2, commande.getNomProd());
            pst.setInt(3, commande.getReferenceCM());
            pst.setDate(4, commande.getDate());
            pst.setInt(5, commande.getQuantiteCpm());
            pst.setInt(6,commande.getIduser());
            pst.setString(7, type);
            pst.executeUpdate();
            System.out.println("COMMANDE AJOUTEE !");
         
             try {
                    mail.sendMail("abdelmalek.baccar@esprit.tn","Commande passe avec succes Quantite :" + commande.getQuantiteCpm());
             } catch (Exception e) {
             }
         
            //JOptionPane.showMessageDialog(null, "Exercice Add ");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
            
            
        }
         }
     }
         
        else
                 System.out.println("quantite insifisant");
                 }
         else
         System.out.println("Produit introvable");
            
            if(type == "fournisseur")
            {
                  try {
            String req = "INSERT INTO commandpm( IDProd,NomProd,referenceCM ,date,quantiteCpm,iduser,type) values (?,?,?,?,?,?,?)";
            
               System.out.println("ee");
            PreparedStatement pst =  cnx.prepareStatement(req);
           
            pst.setInt(1, commande.getIDProd());
            pst.setString(2, commande.getNomProd());
            pst.setInt(3, commande.getReferenceCM());
            pst.setDate(4, commande.getDate());
            pst.setInt(5, commande.getQuantiteCpm());
            pst.setInt(6,commande.getIduser());
             pst.setString(7, type);
           
            pst.executeUpdate();
            System.out.println("COMMANDE AJOUTEE !");
         
             try {
                    mail.sendMail("abdelmalek.baccar@esprit.tn","Commande passe avec succes Quantite :" + commande.getQuantiteCpm());
             } catch (Exception e) {
             }
         
            //JOptionPane.showMessageDialog(null, "Exercice Add ");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
            
            
        }
                ps.modifierQuantiteplus(commande.getQuantiteCpm(),commande.getIDProd());
            }
     
     }
      public void supprimercommande(int idCPM) throws SQLException {

        String req = "DELETE FROM commandPM WHERE idCPM=?";
        try {
            PreparedStatement pst = (PreparedStatement) cnx.prepareStatement(req);
            pst.setInt(1, idCPM);
            pst.executeUpdate();
        } catch (SQLException ex) {
                        System.out.println(ex);
        }

    }
  public  boolean chercher(int id) {
        String req = "select * from commandPM";
        List<Integer> list = new ArrayList<>();
        try {
            java.sql.Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {

                list.add(rs.getInt(1));

            }
        } catch (SQLException ex) {
            Logger.getLogger(commandPM.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list.contains(id);
    }
  public boolean modifiercommande(commandPM C,int id) throws SQLException {
         if (chercher(id)) {
            try {
                String req = "UPDATE commandPM SET IDProd=?,date=?,NomProd=?,referenceCM=?,quantiteCpm=?,iduser=?,type=? WHERE idCPM = "+id;
            PreparedStatement pst = cnx.prepareStatement(req);
            pst.setInt(1, C.getIDProd());
            pst.setString(2, C.getNomProd());
             pst.setInt(3, C.getReferenceCM());
            pst.setDate(4, C.getDate());
            pst.setInt(5, C.getQuantiteCpm());
            pst.setInt(6, C.getIduser());
            pst.setString(7, C.getType());
           
           
            //pst.setInt(5, id);
            pst.executeUpdate (); 

            } catch (SQLException ex) {
                Logger.getLogger(commandPM.class.getName()).log(Level.SEVERE, null, ex);
            }
            System.out.println("update valide");
            return true;
        }
        System.out.println("update invalid: commande n'existe pas");
        return false;
    }
  public ObservableList<commandPM> affichercommande() {
        ObservableList<commandPM> myList = FXCollections.observableArrayList();
        try {

            String requete3 = "SELECT * FROM commandPM";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(requete3);
            while (rs.next()) {
                commandPM C = new commandPM();
                C.setIdCPM(rs.getInt(1));
                C.setIDProd(rs.getInt("IDProd"));
                C.setNomProd(rs.getString("NomProd"));
                C.setReferenceCM(rs.getInt("referenceCM"));
                C.setDate(rs.getDate("date"));
                C.setQuantiteCpm(rs.getInt("quantiteCpm"));
                C.setIduser(rs.getInt("iduser"));
                C.setType(rs.getString("type"));
               
                myList.add(C);
            }

        } catch (SQLException ex) {
            System.err.println("ex.getMessage()");
        }
        return myList;
    }
  
}
