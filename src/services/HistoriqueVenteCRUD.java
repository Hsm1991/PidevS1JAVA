/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.HistoriqueVente;
import entities.Produit;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import utils.MyConnection;


/**
 *
 * @author LENOVO
 */
public class HistoriqueVenteCRUD {
      Connection cnx2;
      public HistoriqueVenteCRUD() {

        cnx2 = MyConnection.getInstance().getCnx();
    }
public void ajouterHistVen(HistoriqueVente h) {
         try {
            String requete2 = "INSERT INTO HistoriqueVente (DateVent,QteVendue,PrixVente,IdPROD)"
                    + "VALUES(?,?,?)";
            PreparedStatement hst = cnx2.prepareStatement(requete2);
            hst.setString(1,  h.getDateVent());
            hst.setFloat(2, h.getQteVendue());
           hst.setFloat(3,h.getPrixVente());
           hst.setInt(4,h.getIdPROD());
            hst.executeUpdate();
            System.out.println("Votre produit vendue est ajoutée");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
}
        
         public ObservableList<HistoriqueVente> afficherHistVentes() {
 ObservableList<HistoriqueVente> myList = FXCollections.observableArrayList();       
 try {

            String requete3 = "SELECT * FROM HistoriqueVente";
            Statement st = cnx2.createStatement();
            ResultSet rs = st.executeQuery(requete3);
            while (rs.next()) {
               HistoriqueVente h = new HistoriqueVente();
                h.setIdVent(rs.getInt(1));
                h.setDateVent(rs.getString("DateVent"));
                h.setQteVendue(rs.getFloat("QteVendue"));
                h.setPrixVente(rs.getFloat("PrixVente"));
                h.setIdPROD(rs.getInt("IdPROD"));
                myList.add(h);
            }

        } catch (SQLException ex) {
            System.err.println("ex.getMessage()");
        }
        return myList;
    }

    public void supprimerHistVentes(int mr) {

        try {
           // System.out.println("Entrez l'Id de vente à supprimer");
            //Scanner sc = new Scanner(System.in);
            //String a = sc.nextLine();

            String sql = "delete from HistoriqueVente where IdVent=" + mr;
            PreparedStatement ste = cnx2.prepareStatement(sql);
            ste.executeUpdate();

            System.out.println("HistoriqueVente vendue a supprimer aves succeé");
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }

    public List<HistoriqueVente> ChercherHistVentes() throws SQLException {
        System.out.println("entre l Id de produit vendue à Chercher");
        Scanner sc = new Scanner(System.in);
        String f = sc.nextLine();
        List<HistoriqueVente> ProduitsVd = new ArrayList<>();

        try {
            String sql = "SELECT * FROM HistoriqueVente where IdVent=" + f;
            ResultSet rs;
            PreparedStatement ste;
            ste = cnx2.prepareStatement(sql);
            rs = ste.executeQuery();
            while (rs.next()) {
                HistoriqueVente h = new HistoriqueVente();
                h.setIdVent(rs.getInt(1));
                h.setDateVent(rs.getString("DateVent"));
                h.setQteVendue(rs.getFloat("QteVendue"));
                h.setPrixVente(rs.getFloat("PrixVente"));
                 h.setIdPROD(rs.getInt("IdPROD"));
                ProduitsVd.add(h);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());

        }
        System.out.println("------> "+ProduitsVd.size());
        return ProduitsVd;
    }

    public void updateHistVentes(HistoriqueVente h, int id) {
        try {
            String sql = "UPDATE HistoriqueVente SET DateVent=? ,QteVendue=? ,PrixVente=?,IdPROD=? WHERE IdVent="+id;
            PreparedStatement ste = cnx2.prepareStatement(sql);
            ste.setString(1,  h.getDateVent());
            ste.setFloat(2, h.getQteVendue());
               ste.setFloat(3, h.getPrixVente());
                ste.setInt(4,h.getIdPROD());
            ste.executeUpdate();
            System.out.println("Votre produit Vendue est modifie !!");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    public int testhistproduit(Integer nb) throws SQLException {
  

        try {
            String sql = "SELECT * FROM HistoriqueVente " ;
        
            ResultSet rs;
            PreparedStatement ste;
            ste = cnx2.prepareStatement(sql);
            rs = ste.executeQuery();
            while (rs.next()) {
               HistoriqueVente p = new HistoriqueVente();
                p.setIdVent(rs.getInt(1));
                p.setDateVent(rs.getString("DateVen"));
                  p.setQteVendue(rs.getFloat(1));
                   p.setQteVendue(rs.getFloat(1));
                   p.setIdPROD(rs.getInt("IdPROD"));
           
                if(p.getIdVent().equals(nb))
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
    
    
      public void remise() {
 ObservableList<HistoriqueVente> myList = FXCollections.observableArrayList();       
myList = afficherHistVentes();
HistoriqueVente hh = new HistoriqueVente();

ProduitCRUD ph = new ProduitCRUD();
hh.setQteVendue(0);
          System.out.println(hh);
           for (HistoriqueVente historiqueVente : myList) {
               if (historiqueVente.getQteVendue() > hh.getQteVendue())
               { System.out.println(historiqueVente);
                   hh = historiqueVente;
               }
           }
               Produit pp = ph.GetOne(hh.getIdPROD());
               System.out.println(pp);
               float p = (float) (pp.getPrixProd() - pp.getPrixProd()*0.1);
        //       System.out.println(hh);   
//          
              ph.updateProduitPrix(hh.getIdPROD(),p);

           }

 
}
      
      
      
      
      
      
      

