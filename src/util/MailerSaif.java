/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.io.IOException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

/**
 *
 * @author saif
 */
public class MailerSaif {
    final String username="saifeddine.chobba@esprit.tn";
    final String password="213JMT8592";
public  void sendMail(String recepient, String path) throws MessagingException, IOException{
    
    Properties properties = new Properties();
    properties.put("mail.smtp.auth","true");
    properties.put("mail.smtp.starttls.enable", "true");
    properties.put("mail.smtp.host", "smtp.gmail.com");
    properties.put("mail.smtp.port", "587");
    
    Session session= Session.getInstance(properties, new javax.mail.Authenticator() 
 {

    @Override
    protected PasswordAuthentication getPasswordAuthentication(){
       return new PasswordAuthentication(username, password);
    }
});
    
    
        Message message =  prepareMessage(session, username, recepient,path);
        Transport.send(message);
        System.out.println("mail facture envoyé");
    }
    private static Message prepareMessage(Session session, String myAccountEmail, String recepient,String path) throws IOException{
        try{
            Message message =  new MimeMessage(session);
            message.setFrom(new InternetAddress(myAccountEmail));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(recepient));
            message.setSubject("facture");
            
            Multipart emailContent = new MimeMultipart();
            
            MimeBodyPart textBody = new MimeBodyPart();
            textBody.setText("voici votre facture attaché ci-dessous\nMerci d'avoir intéragir avec nous.");
            
            MimeBodyPart pdf = new MimeBodyPart();
            pdf.attachFile(path);
            
            emailContent.addBodyPart(textBody);
            emailContent.addBodyPart(pdf);
            
            message.setContent(emailContent);
            
        return message;
        }catch(MessagingException ex){
            System.err.println(ex.getMessage());
        }
    return null;
    }
}

