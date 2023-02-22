/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;


import entities.Evenement;
import static java.lang.Math.E;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;

import java.text.ParseException;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javax.mail.MessagingException;
import utils.MyConnection;



/**
 *
 * @author User
 */
public class EvenementCRUD {

    Connection cnx2;

    public EvenementCRUD() {
        cnx2 = MyConnection.getInstance().getCnx();
    }

    public void ajouterEvenement(Evenement E) throws MessagingException, ParseException {
        try {
            System.out.println("aaaaa");
            String requete2 = "INSERT INTO Evenement (NomEvent,AdresseEvent,CapaciteEvent,nbrTicketAchete,DateDebutEvent,DateFinEvent,TypeEvent,CategorieEvent,PrixEntre,image1)"
                    + "VALUES(?,?,?,?,?,?,?,?,?,?)";
           PreparedStatement pst =cnx2.prepareStatement(requete2);
            pst.setString(1, E.getNomEvent());
            pst.setString(2, E.getAdresseEvent());
            pst.setInt(3, E.getCapaciteEvent());
            pst.setInt(4, 0);
            System.out.println(E.getDateDebutEvent());
          
          
          
            //Date dd = new Date(2011, 1, 11);
            
            
           pst.setObject(5, E.getDateDebutEvent());
            pst.setObject(6, E.getDateFinEvent());
            pst.setString(7, E.getTypeEvent());
            pst.setString(8, E.getCategorieEvent());
            pst.setFloat(9, E.getPrixEntre());
            String uri = GetData212.path1;
            uri = uri.replace("\\", "\\\\");
            pst.setString(10, uri);
            pst.executeUpdate();
            System.out.println("Evènement ajouté avec succés!!!");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
     MailingApi.sendMail("houssemeddine.mhedhbi@esprit.tn",""+"Evenement{"  + " NomEvent=" + E.getNomEvent() + ", AdresseEvent=" + E.getAdresseEvent() + ", CapaciteEvent=" + E.getCapaciteEvent() + ", nbrTicketAchete=" + E.getNbrTicketAchete() + ", DateDebutEvent=" + E.getDateDebutEvent() + ", DateFinEvent=" + E.getDateFinEvent() + ", TypeEvent=" + E.getTypeEvent() + ", CategorieEvent=" + E.getTypeEvent() + ", PrixEntre=" + E.getPrixEntre()    + '}');
    }

    public ObservableList<Evenement> afficherEvenemets() {
        ObservableList<Evenement> myList = FXCollections.observableArrayList();
        try {
            String requete3 = "SELECT * FROM Evenement";
            Statement st = cnx2.createStatement();
            ResultSet rs = st.executeQuery(requete3);
            while (rs.next()) {
                Evenement e = new Evenement();
                e.setidEvent(rs.getInt("idEvent"));
                e.setNomEvent(rs.getString("NomEvent"));
                e.setAdresseEvent(rs.getString("AdresseEvent"));
                e.setCapaciteEvent(rs.getInt("CapaciteEvent"));
                e.setNbrTicketAchete(rs.getInt("nbrTicketAchete"));
                

               e.setDateDebutEvent( rs.getTimestamp("DateDebutEvent"));
               e.setDateFinEvent( rs.getTimestamp("DateFinEvent"));
                e.setTypeEvent(rs.getString("typeEvent"));
                e.setCategorieEvent(rs.getString("CategorieEvent"));
                e.setPrixEntre(rs.getFloat("PrixEntre"));
                e.setImage1(rs.getString("image1"));
                
                myList.add(e);
            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());

        }
             return myList;
    }

    
     public ObservableList<Evenement> afficherEvenemets2() {
        ObservableList<Evenement> myList = FXCollections.observableArrayList();
        try {
            String requete3 = "SELECT * FROM Evenement";
            Statement st = cnx2.createStatement();
            ResultSet rs = st.executeQuery(requete3);
            while (rs.next()) {
                Evenement e = new Evenement();
                e.setNomEvent(rs.getString("NomEvent"));
                e.setAdresseEvent(rs.getString("AdresseEvent"));
                e.setCapaciteEvent(rs.getInt("CapaciteEvent"));
                e.setNbrTicketAchete(rs.getInt("nbrTicketAchete"));
               
                  e.setDateDebutEvent( rs.getTimestamp("DateDebutEvent"));
                e.setDateFinEvent(rs.getTimestamp("DateFinEvent"));
                e.setTypeEvent(rs.getString("typeEvent"));
                e.setCategorieEvent(rs.getString("CategorieEvent"));
                e.setPrixEntre(rs.getFloat("PrixEntre"));
                e.setImage1(rs.getString("Image1"));
                myList.add(e);
            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());

        }
             return myList;
    }
    public void supprimerEvent(int a ) throws SQLException {

        try {
            //System.out.println("Entrez l'Id de l'évènement supprimer");
            //Scanner sc5 = new Scanner(System.in);
           // String a = sc5.nextLine();

            String requete4 = "delete from Evenement where idEvent=" + a;
            PreparedStatement ste = cnx2.prepareStatement(requete4);
            ste.executeUpdate();

            System.out.println("Evènement supprimé aves succeés");
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }

    public void ChercherEvenement(int id) throws SQLException {
        System.out.println("entre l'Id de l'évènement à Chercher");
        Scanner sc = new Scanner(System.in);
        String h = sc.nextLine();
        List<Evenement> Evenement = new ArrayList<>();

        try {
            String sql = "SELECT * FROM Evenement where id=" + h;
           // System.out.println("==<>>>>> " + sql);
            ResultSet rs;
            PreparedStatement ste;
            ste = cnx2.prepareStatement(sql);
            rs = ste.executeQuery();
            while (rs.next()) {
                Evenement e = new Evenement();
                e.setidEvent(rs.getInt(1));
                e.setNomEvent(rs.getString("NomEvent"));
                e.setAdresseEvent(rs.getString("AdresseEvent"));
                e.setCapaciteEvent(rs.getInt("CapaciteEvent"));
                e.setDateDebutEvent( rs.getTimestamp("DateDebutEvent"));
                e.setDateFinEvent( rs.getTimestamp("DateFinEvent"));
                e.setTypeEvent(rs.getString("typeevent"));
                e.setCategorieEvent(rs.getString("Categorieevent"));
                e.setPrixEntre(rs.getFloat("PrixEntre"));
                System.out.println(e);
                Evenement.add(e);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());

        }
    }



   public void ModifierEvenement (Evenement e, int id) {
        try {
            String sql = "UPDATE Evenement SET NomEvent=? ,AdresseEvent=? ,CapaciteEvent=?,nbrTicketAchete=?,DateDebutEvent=?,DateFinEvent=?,TypeEvent=?,CategorieEvent=?,PrixEntre=?,image1=? WHERE Evenement.idEvent="+id;
            PreparedStatement ste = cnx2.prepareStatement(sql);
            String uri = GetData212.path1;
            uri= uri.replace("\\", "\\\\");
            ste.setString(1, e.getNomEvent());
            ste.setString(2, e.getAdresseEvent());
            ste.setInt(3, e.getCapaciteEvent());
            ste.setInt(4, e.getNbrTicketAchete());
            ste.setTimestamp(5, (Timestamp) e.getDateDebutEvent());
            ste.setTimestamp(6, (Timestamp) e.getDateFinEvent());
            ste.setString(7, e.getTypeEvent());
            ste.setString(8, e.getCategorieEvent());
            ste.setFloat(9, e.getPrixEntre());
            ste.setString(10, uri);
            ste.executeUpdate();
            System.out.println("Modification effectuée avec succés !!");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
    }
    
    
    
       public boolean ModifierEvenementNbrTique(Evenement E , int id) throws SQLException {
       
            try {
                String req = "UPDATE Evenement SET `nbrTicketAchete` = ? WHERE `idEvent` = ? ";
                java.sql.PreparedStatement pst =  cnx2.prepareStatement(req);
                int NewQuantite =  E.getNbrTicketAchete() + 1;
                pst.setInt(1,NewQuantite);
                pst.setInt(2, id);
            
                pst.executeUpdate();

            } catch (SQLException ex) {
                 System.err.println(ex.getMessage());
            }
            System.out.println("Modification effectuée avec sucées");
            return true;
       
             }
       
 
          public boolean ModifierEvenementPrix(float  x, int id) throws SQLException {
       
            try {
                String req = "UPDATE Evenement SET `PrixEntre` = ? WHERE `idEvent` = ? ";
                java.sql.PreparedStatement pst = (java.sql.PreparedStatement) cnx2.prepareStatement(req);
                pst.setFloat(1, x);
                pst.setInt(2, id);
            
                pst.executeUpdate();

            } catch (SQLException ex) {
                 System.err.println(ex.getMessage());
            }
            System.out.println("Modification effectuée avec sucées");
            return true;
       
             }

       
           public Evenement GetEventById(String id) throws SQLException {
  

        try {
            String sql = "SELECT * FROM Evenement where idEvent = ? " ;
        
            ResultSet rs;
            PreparedStatement ste;
            ste = cnx2.prepareStatement(sql);
            ste.setObject(1, id);
            rs = ste.executeQuery();
            while (rs.next()) {
                Evenement e = new Evenement();
                e.setidEvent(rs.getInt(1));
                e.setNomEvent(rs.getString("NomEvent"));
                e.setAdresseEvent(rs.getString("AdresseEvent"));
                e.setCapaciteEvent(rs.getInt("CapaciteEvent"));
                e.setNbrTicketAchete(rs.getInt("nbrTicketAchete"));
                
                e.setDateDebutEvent(rs.getTimestamp("DateDebutEvent"));
                e.setDateFinEvent(rs.getTimestamp("DateFinEvent"));
                e.setTypeEvent(rs.getString("typeevent"));
                e.setCategorieEvent(rs.getString("Categorieevent"));
                e.setPrixEntre(rs.getFloat("PrixEntre"));
                
                return e;
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());

        }
              // System.err.println("aucun event");
        return null;
    }
           
           
    public int testNom(String nom) throws SQLException {
  

        try {
            String sql = "SELECT * FROM Evenement " ;
        
            ResultSet rs;
            PreparedStatement ste;
            ste = cnx2.prepareStatement(sql);
            rs = ste.executeQuery();
            while (rs.next()) {
                Evenement e = new Evenement();
                e.setidEvent(rs.getInt(1));
                e.setNomEvent(rs.getString("NomEvent"));
           
                if(e.getNomEvent().equals(nom))
                {
                        return 1;
                }
                    
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());

        }
              // System.out.println("aucun event");
        return 0;
    }
    

}
