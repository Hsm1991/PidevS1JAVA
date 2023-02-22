/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.Evenement;
import entities.Ticket;
import java.awt.Event;
import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.util.Duration;
import javax.mail.MessagingException;
import org.controlsfx.control.Notifications;
import utils.MyConnection;



/**
 *
 * @author User
 */
public class CRUDTicket {
    EvenementCRUD ecd = new EvenementCRUD();
    Connection cnx2;

    
    
    // mail
       // creation d'une session unique
 
    
    //end
    public CRUDTicket() {
        cnx2 = MyConnection.getInstance().getCnx();
    }

    public CRUDTicket(int idTicket, String text, String text0) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public CRUDTicket(int idTicket, String text, String text0, String value) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
     
        public void ajouterTicket(String type ,int id) throws SQLException {
            String s=String.valueOf(id);
            Evenement e = ecd.GetEventById(s);
            if (e.getCapaciteEvent() > e.getNbrTicketAchete())
            {
        try {
            String requete6 = "INSERT INTO TICKET (PrixTicket,NomEvent,TypeTicket)"
                    + "VALUES(?,?,?)";
            PreparedStatement pst = cnx2.prepareStatement(requete6);
            pst.setFloat(1, e.getPrixEntre());
            pst.setString(2, e.getNomEvent());
            pst.setString(3, type);
           // System.out.println(T);
          // e.setNbrTicketAchete(e.getNbrTicketAchete()+1);  // apres chaque achat d un tique nbr tiqueachete fi table event sera incremnte par 1
           // applee lil methode upfdate enevt 
           boolean x = ecd.ModifierEvenementNbrTique(e,id);
            pst.executeUpdate();
            // augmentPrixticket(id);
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
            }
            else 
            {
                System.out.println("Pas des places disponibles");}
    }
        
        public void ChercherTicket() throws SQLException {
        System.out.println("Entrer l'Id de la ticket à Chercher");
        Scanner sc = new Scanner(System.in);
        String h = sc.nextLine();
        List<Ticket> Ticket = new ArrayList<>();

        try {
            String sql = "SELECT * FROM TICKET where IdTicket=" + h;
            ResultSet rs;
            PreparedStatement ste;
            ste = cnx2.prepareStatement(sql);
            rs = ste.executeQuery();
            while (rs.next()) {
                Ticket t = new Ticket();
                t.setIdTicket(rs.getInt(1));
                t.setPrixTicket(rs.getFloat("PrixTicket"));
                t.setNomEvent(rs.getString("NomEvent"));
                t.setTypeTicket(rs.getString("TypeTicket"));
                System.out.println(t);
                Ticket.add(t);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());

        }
    }
        
        
         public ObservableList<Ticket> afficherTickets() {
        ObservableList<Ticket> myList1 = FXCollections.observableArrayList();
        try {
            String requete3 = "SELECT * FROM Ticket";
            Statement st = cnx2.createStatement();
            ResultSet rs = st.executeQuery(requete3);
            while (rs.next()) {
                Ticket t = new Ticket();
                t.setIdTicket(rs.getInt(1));
                t.setPrixTicket(rs.getFloat("PrixTicket"));
                t.setNomEvent(rs.getString("NomEvent"));
                t.setTypeTicket(rs.getString("TypeTicket"));
                myList1.add(t);
            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());

        }
             return myList1;
    }
         
         public void SupprimerTicket(int a) {

          try {

            String requete4 = "delete from TICKET where IdTicket=" + a;
            PreparedStatement ste = cnx2.prepareStatement(requete4);
            ste.executeUpdate();

            System.out.println("Ticket supprimée aves succeés");
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }
         
         
         
        
         
         
         public void ajouterTiquetsForEvent(int idEvent , int nbr , String type) throws MessagingException, SQLException{
                EvenementCRUD ea = new  EvenementCRUD();
                Evenement aa = ea.GetEventById(""+idEvent);
        try {
            for (int i = 0; i < nbr; i++) {
            ajouterTicket(type,idEvent);    
           
               
            }
            augmentPrixticket(idEvent);
        } catch (SQLException ex) {
            Logger.getLogger(CRUDTicket.class.getName()).log(Level.SEVERE, null, ex);
        }
        MailingApi.sendMail("houssemeddine.mhedhbi@esprit.tn","Vous avez une nouvelle réservation de tickets Nom Evènement: " + aa.getNomEvent()+" Capacité : "+aa.getCapaciteEvent());
         }
         
         
         
            public void ajouterTiquetsForEvent2(int idEvent , int nbr , String type) throws SQLException {
        try {
            for (int i = 0; i < nbr; i++) {
            ajouterTicket(type,idEvent);    
               
            }
           
            
        } catch (SQLException ex) {
            Logger.getLogger(CRUDTicket.class.getName()).log(Level.SEVERE, null, ex);
        }
     augmentPrixticket(idEvent);
         }
         
         public void augmentPrixticket(int idEvent ) throws SQLException{
                  String s=String.valueOf(idEvent);
            Evenement e = ecd.GetEventById(s);
            
            if (e.getCapaciteEvent() - e.getNbrTicketAchete() <= 10){
                float p = e.getPrixEntre() + (e.getPrixEntre()*20/100);
                        
                ecd.ModifierEvenementPrix(p, idEvent);
                System.out.println("prix augmenté de 20%");
                
                
                       
         Notifications notificationBuilder;
         notificationBuilder = Notifications.create()
             .title("NOTEZ BIEN")
             .text("Il ya une augmentation de prix de ticket de 20% vu que la capacité maximale est presque atteinte")
             .graphic(null)
             .hideAfter(Duration.seconds(25))
             .position(Pos.BASELINE_RIGHT);

             notificationBuilder.showConfirm();
                
            }
            
            
            
        
        
         }
         
         
         
         
           public void ModifTique (Ticket e, int id) {
        try {
            String sql = "UPDATE Ticket SET PrixTicket=? ,NomEvent=? ,TypeTicket=? WHERE Ticket.IdTicket="+id;
            PreparedStatement ste = cnx2.prepareStatement(sql);
            ste.setFloat(1, e.getPrixTicket());
            ste.setString(2, e.getNomEvent());
            ste.setString(3, e.getTypeTicket());
        
            ste.executeUpdate();
            System.out.println("Modification effectuée avec succés !!");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
    }
}
