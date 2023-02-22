/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.io.File;
import java.util.Properties;
import javax.mail.Address;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

/**
 *
 * @author user
 */
public class SendPwd {
     
    
    public void sendpwdTo (String address , String mdp) throws AddressException, MessagingException {
    
        Properties properties = new Properties();
        properties.put("mail.smtp.auth", true);
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", 587);
        properties.put("mail.smtp.starttls.enable", true);
         properties.put("mail.transport.protocol", "smtp");
        // 1 create mail session to enable sending 
        
        Session session = Session.getInstance(properties,new Authenticator() {  // auth : object gets auth info for a network cnx
         
        
       @Override
         protected PasswordAuthentication getPasswordAuthentication(){
        return new PasswordAuthentication("app.bazart1@gmail.com","wvfjorhaiglvmarr");
         }
    });
        
        
        
    
    Message message;
    message = new MimeMessage(session);
    
    // if email with no attachment
    message.setSubject("Forgotten Password");
   // message.setContent("<h1> mail </h1>","text/html"); // content, type
    
    Address addressTo = new InternetAddress(address);
   
    message.setRecipient(Message.RecipientType.TO,addressTo);
    
    MimeMultipart multipart = new MimeMultipart();
    
    
    //if email with attachment
   
     MimeBodyPart messageBodyPart = new MimeBodyPart();
   //  messageBodyPart.setContent("<h1> mail </h1>","text/html");
     messageBodyPart.setText("your Password is " +mdp);
    
    multipart.addBodyPart(messageBodyPart);

    
    message.setContent(multipart);
    
    Transport.send(message);
   
    }
}
