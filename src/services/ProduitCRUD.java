/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.Produit;
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
import javax.mail.MessagingException;
import utils.MyConnection;

/**
 *
 * @author LENOVO
 */
public class ProduitCRUD {

    Connection cnx2;

    public ProduitCRUD() {

        cnx2 = MyConnection.getInstance().getCnx();
    }

    public void ajouterProduit() {
        String requete = "INSERT INTO produit (NomProd,PrixProd,LocalisationProd,TypeProd,TypeStatue)"
                + "VALUES ('Tableau',22,'Tunis','theatre','disponible')";
        try {
            Statement st = cnx2.createStatement();
            st.executeUpdate(requete);
            System.out.println("Produit ajouteé avec succès");
        } catch (SQLException ex) {

            System.err.println(ex.getMessage());
        }
    }

    public void ajouterProduit2(Produit p) throws MessagingException {
        try {
            String requete2 = "INSERT INTO produit (NomProd,PrixProd,LocalisationProd,TypeProd,TypeStatue,imagem1,rating) values (?,?,?,?,?,?,?) ";
            PreparedStatement pst = cnx2.prepareStatement(requete2);

            pst.setString(1, p.getNomProd());
            pst.setFloat(2, p.getPrixProd());
            pst.setString(3, p.getLocalisationProd());
            pst.setString(4, p.getTypeProd());
            pst.setString(5, p.getTypeStatue());
           String uri_p = getData_m.path111;
           uri_p =uri_p.replace("\\", "\\\\");
           pst.setString(6, uri_p);
           pst.setString(7, "0");
            pst.executeUpdate();
            System.out.println("Votre oeuvre est ajoutée");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        EnvoyerMailmar.EnvoyerMailmar("arfaoui.maram@esprit.tn",""+"Produit{"  + " Nom oeuvre=" + p.getNomProd()+ ", Prix oeuvre=" + p.getPrixProd()+ ", Localisation=" + p.getLocalisationProd()+ ", Type=" + p.getTypeProd()+ ", Statut=" + p.getTypeStatue()+'}');
    }

    public ObservableList<Produit> afficherProduit() {
       ObservableList<Produit> myList = FXCollections.observableArrayList();
        
        try {

            String requete3 = "SELECT * FROM produit";
            Statement st = cnx2.createStatement();
            ResultSet rs = st.executeQuery(requete3);
            while (rs.next()) {
                Produit p = new Produit();
                p.setIdPROD(rs.getInt(1));
                p.setNomProd(rs.getString("NomProd"));
                p.setPrixProd(rs.getFloat("PrixProd"));
                p.setLocalisationProd(rs.getString("LocalisationProd"));
                p.setTypeProd(rs.getString("TypeProd"));
                p.setTypeStatue(rs.getString("TypeStatue"));
                
                p.setimagem1(rs.getString("imagem1"));
                myList.add(p);
            }

        } catch (SQLException ex) {
            System.err.println("ex.getMessage()");
        }
        return myList;
    }
    
    
       public Produit GetOne(int id) {
  //     ObservableList<Produit> myList = FXCollections.observableArrayList();
        
        try {

            String requete3 = "SELECT * FROM `produit` WHERE `IdPROD`="+ id;
            Statement st = cnx2.createStatement();
            ResultSet rs = st.executeQuery(requete3);
            while (rs.next()) {
                Produit p = new Produit();
                p.setIdPROD(rs.getInt(1));
                p.setNomProd(rs.getString("NomProd"));
                p.setPrixProd(rs.getFloat("PrixProd"));
                p.setLocalisationProd(rs.getString("LocalisationProd"));
                p.setTypeProd(rs.getString("TypeProd"));
                p.setTypeStatue(rs.getString("TypeStatue"));
                
                p.setimagem1(rs.getString("imagem1"));
             return p;
            }

        } catch (SQLException ex) {
            System.err.println("ex.getMessage()");
        }
        return null;
    }

    public void supprimerProduit(int mr) {

        try {
            //System.out.println("Entrez l'Id de produit à supprimer");
            //Scanner sc = new Scanner(System.in);
            //String a = sc.nextLine();

            String sql = "delete from produit where IdPROD=" + mr;
            PreparedStatement ste = cnx2.prepareStatement(sql);
            ste.executeUpdate();

            System.out.println("produit a supprimer aves succeé");
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }

    public List<Produit> ChercherProduit() throws SQLException {
        System.out.println("entre l Id de produit à Chercher");
        Scanner sc = new Scanner(System.in);
        String f = sc.nextLine();
        List<Produit> Produits = new ArrayList<>();

        try {
            String sql = "SELECT * FROM produit where IdPROD=" + f;
            ResultSet rs;
            PreparedStatement ste;
            ste = cnx2.prepareStatement(sql);
            rs = ste.executeQuery();
            while (rs.next()) {
                Produit p = new Produit();
                p.setIdPROD(rs.getInt(1));
                p.setNomProd(rs.getString("NomProd"));
                p.setPrixProd(rs.getFloat("PrixProd"));
                p.setLocalisationProd(rs.getString("LocalisationProd"));
                p.setTypeProd(rs.getString("TypeProd"));
                p.setTypeStatue("TypeStatue");
                Produits.add(p);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());

        }
        System.out.println("------> "+Produits.size());
        return Produits;
    }

    public void updateProduit(Produit p, int id) {
        try {
            String sql = "UPDATE produit SET NomProd=?, PrixProd=?, LocalisationProd=? ,TypeProd=?,TypeStatue=? WHERE IDPROD="+id;
            PreparedStatement ste = cnx2.prepareStatement(sql);
         
            ste.setString(1, p.getNomProd());
            ste.setFloat(2, p.getPrixProd());
            ste.setString(3, p.getLocalisationProd());
          
            ste.setString(4, p.getTypeProd());
            ste.setString(5, p.getTypeStatue());
            
            ste.executeUpdate();
            System.out.println("Votre produit est modifie !!");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
        public void updateProduitPrix( int id ,Float Prix) {
        try {
            String sql = "UPDATE `produit` SET `PrixProd`=? WHERE `IdPROD` = "+id;
            PreparedStatement ste = cnx2.prepareStatement(sql);
         
            ste.setFloat(1, Prix);
           
            
            ste.executeUpdate();
            System.out.println("Votre prix produit est modifie !!");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    

public int testproduit(String nom) throws SQLException {
  

        try {
            String sql = "SELECT * FROM Produit " ;
        
            ResultSet rs;
            PreparedStatement ste;
            ste = cnx2.prepareStatement(sql);
            rs = ste.executeQuery();
            while (rs.next()) {
                Produit p = new Produit();
                p.setIdPROD(rs.getInt(1));
                p.setNomProd(rs.getString("NomProd"));
           
                if(p.getNomProd().equals(nom))
                {
                        return 1;
                }
                    
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());

        }
               System.out.println("Aucun oeuvre");
        return 0;
    }
}
