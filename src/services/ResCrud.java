/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;
import utils.MyConnection;
import entities.res;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author MLLE-BAHRI
 */
public class ResCrud {
    /*
     //cnx2;
    public SalleCrud(){
       Connection cnx2=MyConnection.getInstance().getCnx();
    }*/
    
    
    //nadoushaajoute res kamek cc saisie 
    
    public void AjouterRes(res R){
Connection cnx2=MyConnection.getInstance().getCnx();
        
            String requete2 = "INSERT INTO res (num_salle,nom_artiste,date_res,montant) VALUES(?,?,?,?);";
            try {
            PreparedStatement pst =cnx2.prepareStatement(requete2);
            pst.setInt(1, R.getNum_salle());
            pst.setString(2, R.getNom_artiste());
            pst.setString(3, R.getDate_res());
            pst.setFloat(4, R.getMontant());
            pst.executeUpdate();
            System.out.println("reservation ajouté avec succés!!!");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        
    }
    
    
    
    public ObservableList<res> afficherReservation() {
        ObservableList<res> myList = FXCollections.observableArrayList();
        Connection cnx2=MyConnection.getInstance().getCnx();
        try {
            String requete3 = "SELECT * FROM res";
            Statement st = cnx2.createStatement();
            ResultSet rs = st.executeQuery(requete3);
            while (rs.next()) {
                res e = new res();
                e.setId_res(rs.getInt(1));
                e.setNum_salle(rs.getInt("num_salle"));
                e.setNom_artiste(rs.getString("nom_artiste"));
                e.setDate_res(rs.getString("date_res"));
                e.setMontant(rs.getFloat("montant"));
                
                myList.add(e);
            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());

        }
             return myList;
}
    public void UpdateRes(res rnew , res rold){
try
    {
        String sql = "UPDATE res SET id_res = ? , num_salle = ? ,  nom_artiste = ? , date_res = ? , "+
              " montant = ? "  + "WHERE id_res = " + rold.getId_res();
       
 
            PreparedStatement pst = new MyConnection().getCnx().prepareStatement(sql); 
            pst.setObject(1, rnew.getId_res());
            pst.setObject(1, rnew.getNum_salle());
            pst.setObject(1, rnew.getNom_artiste());
            pst.setObject(1, rnew.getDate_res());
            pst.setObject(1, rnew.getMontant());
            pst.executeUpdate();
            System.out.println("res updated");
    }catch (SQLException ex){
        System.out.println(ex.getMessage());
}
}
    
    public ObservableList<res> afficherSalle2() {
        ObservableList<res> myList = FXCollections.observableArrayList();
        Connection cnx2=MyConnection.getInstance().getCnx();
        try {
            String requete3 = "SELECT * FROM res";
            Statement st = cnx2.createStatement();
            ResultSet rs = st.executeQuery(requete3);
            while (rs.next()) {
                res e = new res();
                e.setNum_salle(rs.getInt("num_salle"));
                e.setNom_artiste(rs.getString("nom_artiste"));
                e.setDate_res(rs.getString("date_res"));
               
                myList.add(e);
            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());

        }
             return myList;
}
    
    public void supprimerres(res r)  {

        try {
            Connection cnx2=MyConnection.getInstance().getCnx();
       
            String requete4 = "delete from res where id_res = ? " ;
            PreparedStatement ste = cnx2.prepareStatement(requete4);
            ste.setObject(1, r.getId_res());
            System.out.println(ste);
            ste.executeUpdate();

            System.out.println("res supprimé aves succeés");
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }
    
}
