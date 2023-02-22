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
import entities.Facture;
import entities.Panier;
import utils.MaConnexion;
/**
 *
 * @author saif
 */
public class FactureCRUD {
    
    Connection cnx;
    
    public  FactureCRUD(){
        cnx= MaConnexion.getInstance().getCnx();

    }
    public List<Facture> afficherFacture(){
        List<Facture> myList = new ArrayList<>();
        String req = "select * from facture";
        Statement st;
        try {
            st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while (rs.next()){
                Facture f = new Facture();
                f.setIdFacture(rs.getInt(1));
                f.setIdCmd(rs.getInt(2));
                f.setIdUser(rs.getInt(3));
                f.setMontant(rs.getFloat(4));
                f.setDateCmd(rs.getString(5));
                myList.add(f);
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }  
        
    return myList;
    }
    public void ajouterFacture(Facture f){
        try {
            String req = "insert into facture values(?,?,?,?,?)";
            PreparedStatement pst = cnx.prepareStatement(req);
            pst.setObject(1,f.getIdFacture());
            pst.setObject(2,f.getIdCmd());
            pst.setObject(3,f.getIdUser());
            pst.setObject(4,f.getMontant());
            pst.setObject(5, f.getDateCmd());
            pst.executeUpdate();
            System.out.println("Facture ajoutée");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }
    public void supprimerFacture(Facture f){
        try {
            String req = "delete from facture where " + "idFacture = ? " ;
            PreparedStatement pst = cnx.prepareStatement(req);
            pst.setObject(1,f.getIdFacture());
            pst.executeUpdate();
            System.out.println("facture supprimée ");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }
    public void modifierFacture(Facture fNew,Facture fOld){
        try {
            String req = " update facture set idFacture = ? , idCmd = ? , idUser = ? , montant = ? , "
            + "dateCmd = ? " + "where idFacture = " + fOld.getIdFacture();
            PreparedStatement pst = cnx.prepareStatement(req);
            pst.setObject(1,fNew.getIdFacture());
            pst.setObject(2,fNew.getIdCmd());
            pst.setObject(3,fNew.getIdUser());
            pst.setObject(4,fNew.getMontant());
            pst.setObject(5,fNew.getDateCmd());
            pst.executeUpdate();
            System.out.println("facture modifiée avec succes");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }
}
