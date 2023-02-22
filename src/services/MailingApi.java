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
 * @author User
 */
public class MailingApi {
    public static void sendMail(String recepient , String msg) throws MessagingException {
Properties properties = new Properties();
properties.put("mail.smtp.auth", "true");
properties.put("mail.smtp.starttls.enable", "true");
properties.put("mail.smtp.host", "smtp.gmail.com");
properties.put("mail.smtp.port", "587");

String myAccountEmail ="houssemeddine.mhedhbi@esprit.tn"; 
String password = "SarahDanah2021";
Session session= Session.getInstance(properties, new Authenticator() {

    @Override
    protected PasswordAuthentication getPasswordAuthentication(){
        return new PasswordAuthentication(myAccountEmail, password);
    }
});
    
    
        Message message =  prepareMessage(session, myAccountEmail, recepient,msg);
        Transport.send(message);
        System.out.println("Un message d'ajout d'évènement été envoyé à l'administrateur");
    }
private static Message prepareMessage(Session session, String myAccountEmail, String recepient , String msg){
try{
Message message =  new MimeMessage(session);
message.setFrom(new InternetAddress(myAccountEmail));
message.setRecipient(Message.RecipientType.TO, new InternetAddress(recepient));
message.setSubject("BONJOUR");
message.setText("Un nouveau évènement à été ajouté à votre liste dont les information sont: " +msg);
return message;
}catch(MessagingException ex){
Logger.getLogger(MailingApi.class.getName()).log(Level.SEVERE , null,ex);
        }
return null;
}
}

    

