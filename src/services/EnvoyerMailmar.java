/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author LENOVO
 */
public class EnvoyerMailmar {
    
 
public static void EnvoyerMailmar(String recepient ,String detail) throws MessagingException {
Properties properties = new Properties();
properties.put("mail.smtp.auth", "true");
properties.put("mail.smtp.starttls.enable", "true");
properties.put("mail.smtp.host", "smtp.gmail.com");
properties.put("mail.smtp.port", "587");

String myAccountEmail ="arfaoui.maram@esprit.tn"; 
String password = "213JFT7631";
Session session= Session.getInstance(properties, new javax.mail.Authenticator() 
 {

    @Override
    protected PasswordAuthentication getPasswordAuthentication(){
       return new PasswordAuthentication(myAccountEmail, password);
    }
});
    
    
        Message message =  prepareMessage(session, myAccountEmail, recepient , detail);
        Transport.send(message);
        System.out.println("Un message d'ajout d'un produiut été envoyé à l'artiste");
    }
private static Message prepareMessage(Session session, String myAccountEmail, String recepient , String detail){
try{
Message message =  new MimeMessage(session);
message.setFrom(new InternetAddress(myAccountEmail));
message.setRecipient(Message.RecipientType.TO, new InternetAddress(recepient));
message.setSubject("BONJOUR");
message.setText("Vous avez ajouter un nouveau oeuvre avec ces détails :"+detail);
return message;
}catch(MessagingException ex){
Logger.getLogger(EnvoyerMailmar.class.getName()).log(Level.SEVERE , null,ex);
        }
return null;
}

    
    
}
