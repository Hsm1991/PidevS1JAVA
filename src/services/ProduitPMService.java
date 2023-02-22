/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import utils.MyConnection;
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
import javafx.geometry.Pos;
import javax.mail.MessagingException;
import javax.swing.JOptionPane;
import org.controlsfx.control.Notifications;


/**
 *
 * @author elbootic.com
 */
public class ProduitPMService  {
    
    
    private Connection cnx;
   

    public ProduitPMService() {
        cnx =   MyConnection.getInstance().getCnx();
      }

    
    public void ajouterProduit(ProduitPM produit) throws SQLException {
        if(produit.getNomProd() == null){
            System.out.println("Entre un valide nom");}
        else if (produit.getPrixPM() == 0.0) {
             System.out.println("Entre un valide Prix");}
        
        else
        {
       try {
            String req = "INSERT INTO produitpm( NomProd,referenceP,quantiteP,typep,prixPM,dateAjoutPM)values(?,?,?,?,?,?)";
            PreparedStatement pst =  cnx.prepareStatement(req);
         
            pst.setString(1, produit.getNomProd());
            pst.setInt(2, produit.getReferenceP());
            pst.setInt(3, produit.getQuantiteP());
            pst.setString(4,produit.getType());
            pst.setFloat(5,produit.getPrixPM());
            pst.setString(6,produit.getDateAjoutPM());
                   

            pst.executeUpdate();
            System.out.println("Produit added !");
            //JOptionPane.showMessageDialog(null, "Exercice Add ");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
            
            
        }}
    }
    
   
    public ObservableList<ProduitPM> getProduits()throws SQLException {
        ObservableList ProduitsList = FXCollections.observableArrayList(); 
            
        
            String req = "Select * from produitpm";
            Statement st = (Statement) cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while(rs.next()){
                ProduitsList.add(new ProduitPM(
                          rs.getInt("IDProd")  ,
                    rs.getString("NomProd") ,
                    rs.getInt("referenceP"),
                    rs.getInt("quantiteP"),
                    rs.getString("typep"),
                    rs.getFloat("prixPM"),
                    rs.getString("dateAjoutPM")));
                

            
        }
        return ProduitsList;
    }
  public void supprimerProduitPM(int IDProd) throws SQLException {
      

        String req = "DELETE FROM produitpm WHERE IDProd=?";
        try {
            PreparedStatement pst = (PreparedStatement) cnx.prepareStatement(req);
            pst.setInt(1, IDProd);
            pst.executeUpdate();
        } catch (SQLException ex) {
                        System.out.println(ex);
        }

    }
  public  boolean chercher(int id) {
        String req = "select * from produitpm";
        List<Integer> list = new ArrayList<>();
        try {
            java.sql.Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {

                list.add(rs.getInt(1));

            }
        } catch (SQLException ex) {
            Logger.getLogger(ProduitPM.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list.contains(id);
    }
  public  ProduitPM GetOne(int id) {
        String req = "select * from produitpm WHERE IDProd="+id;
        ProduitPM p = new ProduitPM();
        try {
            java.sql.Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {
                p.setIDProd(rs.getInt(1));
                p.setNomProd(rs.getString("NomProd"));
                p.setReferenceP(rs.getInt("referenceP"));
                p.setQuantiteP(rs.getInt("quantiteP"));
                p.setType(rs.getString("typep"));
                p.setPrixPM(rs.getFloat("prixPM"));
                p.setDateAjoutPM(rs.getString("dateAjoutPM"));
              
 return p;
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProduitPM.class.getName()).log(Level.SEVERE, null, ex);
        }
       return null;
    }
     
    public boolean modifierProduit(ProduitPM E,int id) throws SQLException {
         if (chercher(id)) {
            try {
                String req = "UPDATE produitpm SET NomProd=?,referenceP=?,quantiteP=?,typep=? ,	prixPM=? , dateAjoutPM=? WHERE IDProd = "+id;
            PreparedStatement pst = cnx.prepareStatement(req);
            pst.setString(1, E.getNomProd());
            pst.setInt(2, E.getReferenceP());
            pst.setInt(3, E.getQuantiteP());
            pst.setString(4, E.getType());
            pst.setFloat(5, E.getPrixPM());
            pst.setString(6, E.getDateAjoutPM());
            //pst.setInt(5, id);
            pst.executeUpdate (); 

            } catch (SQLException ex) {
                Logger.getLogger(ProduitPM.class.getName()).log(Level.SEVERE, null, ex);
            }
            System.out.println("update valide");
            return true;
        }
        System.out.println("update invalid: produit nexiste pas");
        return false;
    }
      public boolean modifierQuantite(int q,int id) throws SQLException {
          ProduitPM p = GetOne(id);
         if (chercher(id)) {
            try {
                String req = "UPDATE produitpm SET quantiteP=? WHERE IDProd = "+id;
            PreparedStatement pst = cnx.prepareStatement(req);
            pst.setInt(1,p.getQuantiteP()- q);
       
         
            //pst.setInt(5, id);
            pst.executeUpdate (); 

            } catch (SQLException ex) {
                Logger.getLogger(ProduitPM.class.getName()).log(Level.SEVERE, null, ex);
            }
            System.out.println("update quantite valide");
            return true;
        }
        System.out.println("update invalid: produit nexiste pas");
        return false;
    }
    
         public boolean modifierQuantiteplus(int q,int id) throws SQLException {
          ProduitPM p = GetOne(id);
         if (chercher(id)) {
            try {
                String req = "UPDATE produitpm SET quantiteP=? WHERE IDProd = "+id;
            PreparedStatement pst = cnx.prepareStatement(req);
            pst.setInt(1,p.getQuantiteP()+ q);
       
         
            //pst.setInt(5, id);
            pst.executeUpdate (); 

            } catch (SQLException ex) {
                Logger.getLogger(ProduitPM.class.getName()).log(Level.SEVERE, null, ex);
            }
            System.out.println("update quantite valide");
            return true;
        }
        System.out.println("update invalid: produit nexiste pas");
        return false;
    }

    
    public ObservableList<ProduitPM> afficherProduit() {
        ObservableList<ProduitPM> myList = FXCollections.observableArrayList();
        try {

            String requete3 = "SELECT * FROM produitpm";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(requete3);
            while (rs.next()) {
                ProduitPM p = new ProduitPM();
                p.setIDProd(rs.getInt(1));
                p.setNomProd(rs.getString("NomProd"));
                p.setReferenceP(rs.getInt("referenceP"));
                p.setQuantiteP(rs.getInt("quantiteP"));
                p.setType(rs.getString("typep"));
                p.setPrixPM(rs.getFloat("prixPM"));
                p.setDateAjoutPM(rs.getString("dateAjoutPM"));
                myList.add(p);
            }

        } catch (SQLException ex) {
            System.err.println("ex.getMessage()");
        }
        return myList;
    }
   public void alertstock() throws SQLException, MessagingException{
       for (ProduitPM p : getProduits()) {
           if(p.getQuantiteP()<3){
               System.out.println("produit en alerte de stock  :" + p.getNomProd());
           mail.sendMail("abdelmalek.baccar@esprit.tn","le produit :" + p.getNomProd()  + " est en alerte de stock");
           

           }
       }
       
   }
   public int testnom(String s ) throws SQLException{
       int tn =0;
       ObservableList<ProduitPM> testnom = getProduits(); 
       for (ProduitPM p : testnom){
           if (p.getNomProd().equals(s) )
               return 1;
       }
       return 0;
   }
}
