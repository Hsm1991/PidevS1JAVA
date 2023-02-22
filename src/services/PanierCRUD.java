/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;
import utils.MaConnexion;
import entities.Panier;
/**
 *
 * @author saif
 */
public class PanierCRUD {
    Connection cnx;

    public PanierCRUD() {
        cnx= MaConnexion.getInstance().getCnx();
    }
    
    public void supprimerPanier(Panier p){
        try {
            String req = "delete from panier where " + "idPanier=? " + "and " +
            "idProd = ? "  ;
            PreparedStatement pst = cnx.prepareStatement(req);
            pst.setObject(1,p.getIdPanier());
            pst.setObject(2,p.getIdProd());
            pst.executeUpdate();
            System.out.println("panier supprimé");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }
    
    /* chaque utilisateur a un seul panier associé a lui;on vide le panier càd on 
    suprime toutes les entrées avc l'id du panier spécifié */
    public void viderPanier(Panier p){
       try {
            String req = "delete from panier where " + "idPanier=? ";
            PreparedStatement pst = cnx.prepareStatement(req);
            pst.setObject(1,p.getIdPanier());
            pst.executeUpdate();
            System.out.println("panier vidé");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }
    
    
    public void ajouterPanier(Panier p){
        
        //recherche panier p dans tab panier
        List<Panier> panierList = afficherParIdPanier(p);
        Boolean found = false;
        for (Panier pTest:panierList){
            Integer idp = pTest.getIdProd();
            if ( idp == p.getIdProd() ){
                found = true;
                Panier pNew = new Panier();
                pNew.setIdPanier(pTest.getIdPanier());
                pNew.setIdProd(idp);
                pNew.setNomProd(pTest.getNomProd());
                pNew.setQuantite(pTest.getQuantite()+1);
                pNew.setPrixProd(pTest.getPrixProd());
                pNew.setPrixRemise(MetierSaif.remisePanier(pNew.getQuantite(), pNew.getPrixProd()));
                modifierPanier(pNew, pTest);
            }
        }    
            if (!found){
                try {
                    String req = "insert into panier values(?,?,?,?,?,?)";
                    PreparedStatement pst = cnx.prepareStatement(req);
                    pst.setObject(1,p.getIdPanier());
                    pst.setObject(2,p.getIdProd());
                    pst.setObject(3,p.getNomProd());
                    pst.setObject(4,p.getQuantite());
                    pst.setObject(5,p.getPrixProd());
                    pst.setObject(6,MetierSaif.remisePanier(p.getQuantite(),p.getPrixProd()));
                    pst.executeUpdate();
                    System.out.println("produit avec id= " + p.getIdProd() +  " ajouté au panier avec id= " + p.getIdPanier());
                }catch (SQLException ex) {
                    System.err.println(ex.getMessage());
                }
            }
        
         
    }
    
    public void modifierPanier(Panier pNew , Panier pOld){
         try {
            String req = " update panier set idPanier = ? , idProd = ? , nomProd = ? , quantite = ? , prixProd = ? , prixRemise = ? "
            + " where idPanier = " + pOld.getIdPanier() + " and idProd = " + pOld.getIdProd();
            PreparedStatement pst = cnx.prepareStatement(req);
            pst.setObject(1,pNew.getIdPanier());
            pst.setObject(2,pNew.getIdProd());
            pst.setObject(3,pNew.getNomProd());
            pst.setObject(4,pNew.getQuantite());
            pst.setObject(5,pNew.getPrixProd());
            pst.setObject(6,MetierSaif.remisePanier(pNew.getQuantite(),pNew.getPrixProd()));
            pst.executeUpdate();
            System.out.println("panier modifié avec succes");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
            ex.printStackTrace();
        }
    }
    
    public List<Panier> afficherPanier(){
        List<Panier> myList = new ArrayList<>();
        String req = "select * from panier";
        Statement st;
        try {
            st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while (rs.next()){
                Panier p = new Panier();
                p.setIdPanier(rs.getInt(1));
                p.setIdProd(rs.getInt(2));
                p.setNomProd(rs.getString(3));
                p.setQuantite(rs.getInt(4));
                p.setPrixProd(rs.getFloat(5));
                p.setPrixRemise(rs.getFloat(6));
                myList.add(p);
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }        
    return myList;
    }
    
    public  List<Panier> afficherParIdPanier(Panier p){
        List<Panier> myList = new ArrayList<>();
        List<Panier> all ;
        PanierCRUD pCRUD = new PanierCRUD();
        all = pCRUD.afficherPanier();
        //grouper les elements d'un panier dans un seul variable
        all.forEach((pTest) -> {
            if (Objects.equals(pTest.getIdPanier(), p.getIdPanier()) ){
                myList.add(pTest);
            }
        });
    return myList;
    }
    
    
    public int checkExists(Panier panier ,int idprod){
       List<Panier> myList = new ArrayList<>();
       myList = afficherParIdPanier(panier);
       for (Panier prod : myList){
        if (prod.getIdProd() == idprod){
            return prod.getQuantite();
        }
    }
       
       return 0;
    }
   
    
}
