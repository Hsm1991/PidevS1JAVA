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
import utils.MaConnexion;
import entities.Commande;
import entities.Panier;
import java.util.Collections;
/**
 *
 * @author saif
 */
public class CommandeCRUD {
    Connection cnx;

    public CommandeCRUD() {
        cnx= MaConnexion.getInstance().getCnx();
    }

    public void supprimerCommande(Commande c){
        try {
            String req = "delete from commande where " + "idCmd = ? " +
            " and idProd = ? and dateCmd = ? " ;
            PreparedStatement pst = cnx.prepareStatement(req);
            pst.setObject(1,c.getIdCmd());
            pst.setObject(2,c.getIdProd());
            pst.setObject(3,c.getDateCmd());
            pst.executeUpdate();
            System.out.println("commande supprimé : produit = " + c.getIdProd());
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    public void annulerCommande(Commande c){
        try {
            String req = "update commande set etatCmd = 'annulée' where " + "idCmd = ? ";
            PreparedStatement pst = cnx.prepareStatement(req);
            pst.setObject(1,c.getIdCmd());

            pst.executeUpdate();
            System.out.println("commande annulé");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }


    public void modifierCommande(Commande cNew , Commande cOld){
         try {
            String req = "update commande set idCmd = ? , idPanier = ? , idProd = ? , nomProd = ? ,  "
            + "quantite = ? , prixProd = ? , prixRemise = ? , etatCmd = ? , dateCmd = ? " +
            " where idCmd = " + cOld.getIdCmd() + " and idProd = "+cOld.getIdProd() ;
            PreparedStatement pst = cnx.prepareStatement(req);
            pst.setObject(1,cNew.getIdCmd());
            pst.setObject(2,cNew.getIdPanier());
            pst.setObject(3,cNew.getIdProd());
            pst.setObject(4,cNew.getNomProd());
            pst.setObject(5,cNew.getQuantite());
            pst.setObject(6,cNew.getPrixProd());
            pst.setObject(7,cNew.getPrixRemise());
            pst.setObject(8,cNew.getEtatCmd());
            pst.setObject(9,cNew.getDateCmd());
            pst.executeUpdate();
            System.out.println("commande modifié");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    public List<Commande> afficherCommande(){
        List<Commande> myList = new ArrayList<>();
        String req = "select * from commande";
        Statement st;
        try {
            st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while (rs.next()){
                Commande c = new Commande();
                c.setIdCmd(rs.getInt(1));
                c.setIdPanier(rs.getInt(2));
                c.setIdProd(rs.getInt(3));
                c.setNomProd(rs.getString(4));
                c.setQuantite(rs.getInt(5));
                c.setPrixProd(rs.getFloat(6));
                c.setPrixRemise(rs.getFloat(7));
                c.setEtatCmd(rs.getString(8));
                c.setDateCmd(rs.getString(9));
                myList.add(c);
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    return myList;
    }

    public void ajouterCommande(Commande c){
        if (c.getEtatCmd() == null){
            System.out.println("saisir un etat correcte");
        }
        if (c.getDateCmd() == null ){
            System.out.println("saisir une date correcte");
        }
        try {
            String req = "insert into commande  values(?,?,?,?,?,?,?,?,?)";
            PreparedStatement pst = cnx.prepareStatement(req);
            pst.setObject(1,c.getIdCmd());
            pst.setObject(2,c.getIdPanier());
            pst.setObject(3,c.getIdProd());
            pst.setObject(4,c.getNomProd());
            pst.setObject(5,c.getQuantite());
            pst.setObject(6,c.getPrixProd());
            pst.setObject(7,c.getPrixRemise());
            pst.setObject(8,c.getEtatCmd());
            pst.setObject(9,c.getDateCmd());

            pst.executeUpdate();
            System.out.println("commande ajoutée");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    public List<Commande> afficherParIdCommande(Commande c){
        List<Commande> myList = new ArrayList();
        List<Commande> all = afficherCommande() ;
        for (Commande cTest : all){
            if (Objects.equals(cTest.getIdCmd(), c.getIdCmd())){
                myList.add(cTest);
            }
        };
        
    return myList;
    }
    
    public List<Integer> afficherParUserCommande(Commande c){
        List<Integer> myList = new ArrayList();
        List<Commande> all = afficherCommande();
        for (Commande cTest : all){
            if (Objects.equals(cTest.getIdPanier(), c.getIdPanier())){
                if (!myList.contains(cTest.getIdCmd())){
                    myList.add(cTest.getIdCmd());
                }  
            }
        }
    return myList;
    }
    
    public Commande afficherParIdPanierCommande(Integer idPanier){
        List<Commande> all = afficherCommande();
        Commande c = new Commande();
        boolean found=false;
        Integer n=0;
        for (Commande cTest : all){
            n++;
            System.err.println(n);
            System.out.println("cTEst is :" + cTest);
            if (Objects.equals(cTest.getIdPanier(), idPanier)){
                found = true;
                System.out.println("checked");
                c.setIdCmd(cTest.getIdCmd());
            }
            if (!found){
                c.setIdCmd(0);
            }
        }
    return c;
    }
    
    public void updateEtatCommande(Commande c,String etat){
        List<Commande> l = afficherParIdCommande(c);
        for (Commande cTest : l){
            Commande cOld = cTest;
            System.out.println(cOld);
            cTest.setEtatCmd(etat);
            System.out.println(cTest);
            modifierCommande(cTest, cOld);
        }
        
    }
    
    
    public List<Integer> getAllCommandeId(){
        List<Integer> myList = new ArrayList() ;
        List<Commande> allCommande = afficherCommande();
        if (allCommande.isEmpty()){
            return null ;
        }
        
        int last = allCommande.get(0).getIdCmd();
        System.out.println(last);
    
        System.err.println(allCommande);
        myList.add(last);
     
        for (Commande cmd : allCommande){
            if (cmd.getIdCmd() == last){
                continue;
            }
            else{
                myList.add(cmd.getIdCmd());
                last = cmd.getIdCmd();
            }
        }
        
           System.out.println(myList);
        
        return myList;
    }
    
     public List<Commande> getAllCommandeByUser(int iduser){
          List<Commande> allCommandeUser = new ArrayList() ;
          
            String req = "select * from commande where idPanier = "+iduser;
        Statement st;
        try {
            st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while (rs.next()){
                Commande c = new Commande();
                c.setIdCmd(rs.getInt(1));
                c.setIdPanier(rs.getInt(2));
                c.setIdProd(rs.getInt(3));
                c.setNomProd(rs.getString(4));
                c.setQuantite(rs.getInt(5));
                c.setPrixProd(rs.getFloat(6));
                c.setPrixRemise(rs.getFloat(7));
                c.setEtatCmd(rs.getString(8));
                c.setDateCmd(rs.getString(9));
                allCommandeUser.add(c);
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
          
         return allCommandeUser;
     }
    public List<Integer> getAllCommandeIdByUser(int iduser){
        List<Integer> myList = new ArrayList() ;
        List<Commande> allCommande = getAllCommandeByUser(iduser);
        if (allCommande.isEmpty()){
            return null ;
        }
        
        int last = allCommande.get(0).getIdCmd();
        
    
     
        myList.add(last);
     
        for (Commande cmd : allCommande){
            if (cmd.getIdCmd() == last){
                continue;
            }
            else{
                myList.add(cmd.getIdCmd());
                last = cmd.getIdCmd();
            }
        }
        
           System.out.println(myList);
        
        return myList;
    }
}
