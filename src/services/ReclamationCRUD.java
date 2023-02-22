/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.Commande;
import entities.Reclamation;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import utils.MaConnexion;

/**
 *
 * @author User
 */
public class ReclamationCRUD {
    
    Connection cnx;

    public ReclamationCRUD() {
        cnx= MaConnexion.getInstance().getCnx();
    }
    
    public void supprimerReclamation(Reclamation r){
        try {
            String req = "delete from reclamation where id = ? " ;
            
            PreparedStatement pst = cnx.prepareStatement(req);
            pst.setObject(1,r.getId());
            System.out.println(r.getId());
           
            pst.executeUpdate();
            System.out.println("rec supprimé ");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

   


    public void modifierReclamation(Reclamation rNew , Reclamation rOld){
         try {
            String req = "update reclamation set  iduser = ? , description = ? , recdate = ? ,  "
            + "selon = ? , mail = ? "+
            " where id = " + rOld.getId();
            PreparedStatement pst = cnx.prepareStatement(req);
            pst.setObject(1,rNew.getIduser());
            pst.setObject(2,rNew.getDescription());
            pst.setObject(3,rNew.getRecdate());
            pst.setObject(4,rNew.getSelon());
            pst.setObject(5,rNew.getMail());
         
        
            pst.executeUpdate();
            System.out.println("rec modifié");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    public List<Reclamation> afficherReclamationAll(){
        List<Reclamation> myList = new ArrayList<>();
        String req = "select * from reclamation";
        Statement st;
        try {
            st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while (rs.next()){
                Reclamation c = new Reclamation();
                c.setId(rs.getInt(1));
                c.setIduser(rs.getInt(2));
                c.setDescription(rs.getString(3));
                c.setRecdate(rs.getString(4));
                c.setSelon(rs.getString(5));
                c.setMail(rs.getString(6));
                myList.add(c);
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    return myList;
    }
    
     public List<Reclamation> afficherReclamationById(int iduser){
        List<Reclamation> myList = new ArrayList<>();
        String req = "select * from reclamation where iduser = ? ";
        PreparedStatement st;
        
         
          
           
        try {
            st = cnx.prepareStatement(req);
            st.setObject(1, iduser);
            ResultSet rs = st.executeQuery();
            while (rs.next()){
                Reclamation c = new Reclamation();
                c.setId(rs.getInt(1));
                c.setIduser(rs.getInt(2));
                c.setDescription(rs.getString(3));
                c.setRecdate(rs.getString(4));
                c.setSelon(rs.getString(5));
                c.setMail(rs.getString(6));
                myList.add(c);
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    return myList;
    }


    public void ajouterReclamation(Reclamation c){
        
        try {
            String req = "insert into reclamation(iduser,description,recdate,selon,mail)  values(?,?,?,?,?)";
            PreparedStatement pst = cnx.prepareStatement(req);
            pst.setObject(1,c.getIduser());
            pst.setObject(2,c.getDescription());
            pst.setObject(3,c.getRecdate());
            pst.setObject(4,c.getSelon());
            pst.setObject(5,c.getMail());
        

            pst.executeUpdate();
            System.out.println("rec ajoutée");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }
    
}
