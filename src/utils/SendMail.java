/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.Address;
import javax.mail.MessagingException;
import javax.mail.Transport;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

/**
 *
 * @author user
 */



public class SendMail {
    
    public  void send (String subject ,String addressRc , List<File> list, String mes ) throws MessagingException, IOException {
        
        // smtp config properties : host , auth , port , tls, transport protocol
         
         // host : serber to connect to 
         // port smtp defaukt port is 587
         // auth = true , needed when using gail 
         // start tls enable if true switches to a tls protected connection 
         // transport. protocol specifies the default t p 
        
        
        
        
        //smtp : simple mail transport protocol 
        
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
        return new PasswordAuthentication("saifeddine.chobba@esprit.tn","213JMT8592");
         }
    });
    
    Message message;
    message = new MimeMessage(session);
    
    // if email with no attachment
    message.setSubject(subject);
   // message.setContent("<h1> mail </h1>","text/html"); // content, type
    
    Address addressTo = new InternetAddress(addressRc);
   
    message.setRecipient(Message.RecipientType.TO,addressTo);
    
    MimeMultipart multipart = new MimeMultipart();
    
    
    //if email with attachment
   
    
    
    if ( list !=null)
        
        for (Iterator<File> it = list.iterator(); it.hasNext();) {
            File file = it.next();
            MimeBodyPart attachment = new MimeBodyPart();
            attachment.attachFile(file);
            multipart.addBodyPart(attachment);
        }
    
     
  //  MimeBodyPart attachment2 = new MimeBodyPart();
  //  attachment2.attachFile(new File("src/files/logo1.png"));
    
    
     MimeBodyPart messageBodyPart = new MimeBodyPart();
     messageBodyPart.setText(mes);
     
    
    multipart.addBodyPart(messageBodyPart);
    
   // multipart.addBodyPart(attachment2);
    
    message.setContent(multipart);
    
    Transport.send(message);
   
    }
    }
    
