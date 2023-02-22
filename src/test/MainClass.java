/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;


import entities.Evenement;
import entities.Ticket;
import static java.lang.ProcessBuilder.Redirect.to;
import java.sql.SQLException;
import java.util.Date;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import static jdk.nashorn.internal.objects.NativeJava.to;
import services.CRUDTicket;
import services.EvenementCRUD;
import services.HistoriqueVenteCRUD;
import utils.MyConnection;
import services.MailingApi;
import services.ProduitCRUD;
import services.SmsApi;


/**
 *
 * @author User
 */
public class MainClass  {
    
    public static void main(String[] a1rgs) throws SQLException, AddressException,  MessagingException {
        MyConnection mc = MyConnection.getInstance();
       EvenementCRUD ecd = new EvenementCRUD();
       Date df = new Date();
       java.util.Date utilDate = new java.util.Date(); //date that needs conversion
       java.sql.Date sqlDate; //converts to SQL date format
        sqlDate = new java.sql.Date(df.getTime());
//      Evenement e2 = new Evenement("Event45","Tunis",50,500,"22/02/2022 à 10.00h","22/02/2022 à 12h","Distantiel","Musical", 50);
     // System.out.println(ecd.GetEventById("1")); 
     //ecd.ajouterEvenement(e2);
    // ecd.ModifierEvenement(e2, 1);
     //System.out.println(ecd.afficherEvenemets2());
     //ecd.ChercherEvenement(1);
      // System.out.println(ecd.ModifierEvenement(e2, 1));
     // System.out.println(ecd.afficherEvenemets());
 //    ecd.supprimerEvent(26);
        ProduitCRUD t = new ProduitCRUD();
   t.updateProduitPrix(1, (float)1);
     //  CRUDTicket tcd = new CRUDTicket();
        
    //  Ticket t1 = new Ticket ();
      // tcd.ajouterTicket("2eme Classe",6);
        //tcd.ajouterTiquetsForEvent(5, 30, "1ere classe");
        //tcd.ChercherTicket();
       //System.out.println(tcd.afficherTickets()); 
        //tcd.SupprimerTicket();
      // tcd.augmentPrixticket(3);
      
       // MailingApi.sendMail("houssemeddine.mhedhbi@esprit.tn");
      // SmsApi ap = new SmsApi();
     // ap.sms("houcem1307", "LalaDoudy2021","+21625292257","HELLO");
       
       
    }       
  
}
