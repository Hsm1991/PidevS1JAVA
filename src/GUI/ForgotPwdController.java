/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import static GUI.TestController.infoBox;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.Window;
import javax.mail.MessagingException;
import services.UserCrud;
import utils.SendMail;
import utils.SendPwd;

/**
 * FXML Controller class
 *
 * @author user
 */
public class ForgotPwdController implements Initializable {

    @FXML
    private TextField ao_pwd_email;
    @FXML
    private Button ao_btn_send_pwd;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void sendEmailPwd(ActionEvent event) throws SQLException, MessagingException {
        
        SendPwd mailTo = new SendPwd();
        UserCrud uc = new UserCrud();
     
        String mail = ao_pwd_email.getText();
        
         if (mail.isEmpty()) {
            infoBox("Veuillez saisir une adresse", null, "Failed"); }
         
        boolean flag = uc.checkMailExists(mail);
        boolean flag2 = uc.isValidEmailAddress(mail);
        
        if (flag == false   )
                   {
       
      infoBox("Cet adresse n'existe pas", null, "Failed");
                    }  
        if (flag2 == false   )
                   {
       
      infoBox("Adresse non valide ", null, "Failed");
                    }  
        
         if (flag == true & flag2 == true  )
                   {
                      
                       mailTo.sendpwdTo(mail,uc.getPassword(mail));
                       infoBox("Mot de passe Envoyé", null, "Succes");
                          } 
         
            
        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        stage.hide();     
         
    }
}
        
        
    
    

