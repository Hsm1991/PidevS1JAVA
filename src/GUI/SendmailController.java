/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import static GUI.TestController.infoBox;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.Window;
import javax.mail.MessagingException;
import entities.User;
import utils.SendMail;
import utils.Notification;

/**
 * FXML Controller class
 *
 * @author user
 */
public class SendmailController implements Initializable {
    
    File file =null ;
  //  File im =null;
    List<File> im =null;
    Notification ntf = new Notification();
private User selectedUser;
    @FXML
    private TextField ao_mail;
    @FXML
    private TextArea ao_text;
    @FXML
    private Button send;
    @FXML
    private TextField ao_subject;
    @FXML
    private Button upload;
    
    /**
     * Initializes the controller class.
     */
    
      SendMail sm = new SendMail();
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
         
      

    }    
    
    
      public void initData2(User r) 
    { 
 
    selectedUser =r;
    
    ao_mail.setText(selectedUser.getUser_mail());
   // ao_xmdp.setText(selectedUser.getUser_mdp());
    
    
    }

    @FXML
    private void sendmail(ActionEvent event) throws MessagingException, IOException {
        
        String mes = ao_text.getText();
         String subject = ao_subject.getText();
         String mail = ao_mail.getText();
        
        sm.send(subject, mail,im, mes);
        ntf.notif("Email envoyé avec succès");
        
        
    }

    @FXML
    private void uploadfile(ActionEvent event) {
        
       FileChooser fc = new FileChooser();
         
       
        Window ownerWindow = null;
   
        im= fc.showOpenMultipleDialog(ownerWindow);
        ntf.notif("Fichiers importés");
        System.out.println(file);
        
       
    }

    @FXML
    private void uploadim(ActionEvent event) {
        
         
    }
}
