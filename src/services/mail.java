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
 * @author elbootic.com
 */
public class mail {
    /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */




/**
 *
 * @author User
 */
 
    public static void sendMail(String recepient ,String mess) throws MessagingException {
Properties properties = new Properties();
properties.put("mail.smtp.auth", "true");
properties.put("mail.smtp.starttls.enable", "true");
properties.put("mail.smtp.host", "smtp.gmail.com");
properties.put("mail.smtp.port", "587");

String myAccountEmail ="abdelmalek.baccar@esprit.tn"; 
String password = "213JMT7971";
Session session= Session.getInstance(properties, new Authenticator() {

    @Override
    protected PasswordAuthentication getPasswordAuthentication(){
        return new PasswordAuthentication(myAccountEmail, password);
    }
});
    
    
        Message message =  prepareMessage(session, myAccountEmail, recepient);
        message.setText(mess);
        Transport.send(message);
        System.out.println("Security alert!!!!");
    }
private static Message prepareMessage(Session session, String myAccountEmail, String recepient){
try{
Message message =  new MimeMessage(session);
message.setFrom(new InternetAddress(myAccountEmail));
message.setRecipient(Message.RecipientType.TO, new InternetAddress(recepient));
message.setSubject("Alerte");
//message.setText("Vous avez un produit de stock qui sera en repture bientot veuillez consulter votre stock");
return message;
}catch(MessagingException ex){
Logger.getLogger(mail.class.getName()).log(Level.SEVERE , null,ex);
        }
return null;
}
}
    
