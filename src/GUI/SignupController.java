/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import static GUI.TestController.infoBox;
import java.io.IOException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import static java.util.Collections.list;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import static java.util.concurrent.TimeUnit.SECONDS;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.PasswordField;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import entities.User;
import java.util.List;
import javafx.collections.FXCollections;
import services.BCrypt;
import services.RoleCrud;
import services.UserCrud;

/**
 * FXML Controller class
 *
 * @author user
 */
public class SignupController implements Initializable {

    //ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
    
     String role_name;
     
      boolean s= false;
   
    @FXML
    private Button btnsignup;
    @FXML
    private ProgressBar progress_pwd;
    @FXML
    private Label label_pwd;
    @FXML
    private TextField signup_pwd;
    @FXML
    private TextField confirm_pwd;
    @FXML
    private Label label_confirm;
    @FXML
    private Button btn_generate;
    @FXML
    private TextField textphone;
    @FXML
    private Label validate_tel;
    @FXML
    private Label fill_field;
    @FXML
    private TextField ao_user_nom;
    @FXML
    private TextField ao_user_prenom;
    @FXML
    private ComboBox<String> ao_role_combo;
    @FXML
    private TextField ao_login_id;
    @FXML
    private TextField ao_user_email;
    @FXML
    private TextField ao_user_adresse;
@FXML
    private Label validmail;

    /**
     * Initializes the controller class.
     */
    
    UserCrud uc = new UserCrud();
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
     
        RoleCrud rc = new RoleCrud();
        List<String> list = new ArrayList();
        
            list.add("[\"ROLE_USER\"]");
             list.add("[\"ROLE_ARTISTE\"]");
        ObservableList<String> listofroles =  FXCollections.observableArrayList(list) ;
        
                
        ao_role_combo.setItems(listofroles);
        ao_role_combo.getSelectionModel().selectFirst();
        
        validate_tel.setOpacity(0);
        fill_field.setOpacity(0);
        label_confirm.setOpacity(0);
        label_pwd.setOpacity(0);
        validmail.setOpacity(0);
          
     
    
        
       
    }    

    @FXML
    private void signup(ActionEvent event) throws SQLException, IOException {
        
        //reset errors//
        validate_tel.setOpacity(0);
        fill_field.setOpacity(0);
        label_confirm.setOpacity(0);
        label_pwd.setOpacity(0);
        validmail.setOpacity(0);
          
                     ///////////////   
      
      
        
        boolean r = false ;
        boolean notempty = false;
        boolean v= false;
        
       String role = ao_role_combo.getValue();
       String cpwd = confirm_pwd.getText();
       String pwd = signup_pwd.getText();
       String phone = textphone.getText();  
       String adresse = ao_user_adresse.getText();
       String mail = ao_user_email.getText();
       String prenom = ao_user_prenom.getText();
       String nom =ao_user_nom.getText();
       String id=ao_login_id.getText();
       
    
              
              
             if (phone.length() == 0  || pwd.length()==0 || cpwd.length() == 0|| adresse.length()==0 || mail.length()==0 || prenom.length()==0 || nom.length()==0 || id.length()==0  )
              {fill_field.setText("Veuillez remplir tous les champs");
              fill_field.setOpacity(1);
              //scheduler.scheduleAtFixedRate(runnable, 0, 1, SECONDS);
                  System.out.println("empty");
              return;
              }
           
              else { notempty = true;}
             
      
       
     // s= validateStrength(pwd); 
        
       boolean flag = uc.checkMailExists(mail);
       boolean flag2 = uc.isValidEmailAddress(mail);
           
             
              if (!(pwd.equals(cpwd) && validateStrength(pwd))  ) { 
                  label_confirm.setText("Veuillez confirmer Mot de passe"); 
                   label_confirm.setOpacity(1);
                return;
              }
              else {
                  label_confirm.setText("Mot de passe confirmé");
                label_confirm.setOpacity(1);
               
        //        scheduler.scheduleAtFixedRate(runnable, 0, 1, SECONDS);
                    
                  System.out.println("pass strong + identical");
                  System.out.println(r);
                  r = true;
              }
              
              
              
              if (validatephone(phone)== true) {
              
              validate_tel.setText("Numéro de téléphone validé");
              validate_tel.setOpacity(1);
               //scheduler.scheduleAtFixedRate(runnable, 0, 1, SECONDS);
                  System.out.println("tel valide");
                v=true;
              }
              
               if (validatephone(phone)== false) {
              
              validate_tel.setText("Numéro de téléphone non validé");
              //scheduler.scheduleAtFixedRate(runnable, 0, 1, SECONDS);
              validate_tel.setOpacity(1);
                   System.out.println("tel non valide");
              }
               
               
        
        if (flag == true   )
                   {
                  validmail.setText("Adresse Email utilisé dèja ");
                  validmail.setOpacity(1);
                   //scheduler.scheduleAtFixedRate(runnable, 0, 1, SECONDS);
                       System.out.println("email existing");
          
                    }  
        if (flag2 == false   )
                   {
                       
               validmail.setText("Adresse Email non valide ");
               validmail.setOpacity(1);
                //scheduler.scheduleAtFixedRate(runnable, 0, 1, SECONDS);
                       System.out.println("email non valid");
      
                    }  
        
       // flag2 = true;flag=false;v =true;r=true;s=true;notempty=true;
                  System.out.println(r);
     if (flag2 == true && flag == false && v == true && r == true && s == true && notempty == true)
                   {
                      System.out.println("all true");
                    RoleCrud rolecrud = new RoleCrud();
                   // String hashPwd = BCrypt.hashpw(pwd, BCrypt.gensalt(12));
                   String pass = "$2y$13$RDO.XPoJBoTXc6gCeT4GwOy8RVmvR7SpNpl.rlydwUNUragUBNvei";
                     User p = new User(id,pass,nom,prenom,mail,phone,adresse,role);
                     p.setCategory_id(rolecrud.getCategoryId(role));
                     uc.createUser(p);
                     //reset errors//
                     validmail.setOpacity(0);
                     validate_tel.setOpacity(0);
                     label_confirm.setOpacity(0);
                     fill_field.setOpacity(0);
                     ///////////////
                       
                      infoBox("Utilisateur ajouté avec succès", null, "succes");
                      
                    Node node = (Node) event.getSource();
                    Stage stage = (Stage) node.getScene().getWindow();
                    stage.hide();     
                   
              /*     Stage stage = (Stage) btn_generate.getScene().getWindow();
                   stage.hide();*/
                     
                    
                     
                    }  
               
      
    
}
    
    
    public boolean validateStrength(String newPass)
{    
    boolean strength =true;
    
    if (newPass.length() == 0 )
    {
        label_pwd.setText("");
        strength= false;
    }

    boolean lengthRule = newPass.length() >= 8 && newPass.length() <=15;

    boolean upperRule = !newPass.equals(newPass.toLowerCase());   // at least one upper case
    boolean lowerRule = !newPass.equals(newPass.toUpperCase());   // at least one lower case
    boolean numeralRule = newPass.matches("(.*)[0-9](.*)");   //at least one numeral
    boolean nonAlphaRule = newPass.matches("(.*)[^A-Za-z0-9](.*)");  // at least one non-alphanumeric.

    int ruleCount = (upperRule ? 1 : 0) + ( lowerRule ? 1 : 0) + ( numeralRule ? 1 : 0) + (nonAlphaRule ? 1 : 0);

    if (ruleCount >= 3 && lengthRule)
    {
        label_pwd.setText("Password is strong");
        label_pwd.setTextFill(Color.GREEN);
        progress_pwd.setProgress(0.9);
        progress_pwd.setStyle("-fx-accent: green;");
        return strength;
    }
    else
    {
        if ( ruleCount >= 2 && lengthRule)
        {
        label_pwd.setText("Password is mild");
        label_pwd.setTextFill(Color.ORANGE);
        progress_pwd.setStyle("-fx-accent: orange;");
        progress_pwd.setProgress(0.6);
        strength = false;
        
      
        }
        else if (ruleCount < 2 && lengthRule )
        {
        label_pwd.setText("Password is weak");
        label_pwd.setTextFill(Color.RED);
        progress_pwd.setStyle("-fx-accent: red;");
        progress_pwd.setProgress(0.3);
        
        
        strength = false;
        }
        else 
        {label_pwd.setText("Password is too short");
        label_pwd.setTextFill(Color.RED);
        progress_pwd.setProgress(0.1);
        progress_pwd.setStyle("-fx-accent: red;");
        strength = false; 
        }
    }
 return strength;
}

    @FXML
    private void generatepwd(ActionEvent event) {
        
        
      String pwd="" ;
      
      String capitalCaseLetters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
      String lowerCaseLetters = "abcdefghijklmnopqrstuvwxyz";
      String specialCharacters = "!@#$+*_%=@/|";
      String numbers = "1234567890";
      String combinedChars = capitalCaseLetters + lowerCaseLetters + specialCharacters + numbers;
      int min = 8;
      int max = 15;
      int len = (int) (Math.random() * (max-min+1) + min); 
      char[] new_pwd = new char[len];
      Random r = new Random();
        for(int i = 0; i< len ; i++) {
         new_pwd[i] = combinedChars.charAt(r.nextInt(combinedChars.length()));
      }
        pwd=String.valueOf(new_pwd);
       signup_pwd.setText(pwd);
    }
    
    
    
    public boolean validatephone(String phone){
    if (textphone.getText().matches("\\d{8}")) 
            {
    return true;
}else {
   return false;
}
    }



    @FXML
    private void Select_role(ActionEvent event) {
        
          role_name = ao_role_combo.getSelectionModel().getSelectedItem().toString();
          
    }
    
    
    
    
    /*   final Runnable runnable = new Runnable() {
            int countdownStarter = 8;

            public void run( ) {

                System.out.println(countdownStarter);
                countdownStarter--;

                if (countdownStarter < 0) {
                    System.out.println("Timer Over!");
                     scheduler.shutdown();
                     validate_tel.setOpacity(0);
                     fill_field.setOpacity(0);
                     label_confirm.setOpacity(0);
                      label_pwd.setOpacity(0);
                      validmail.setOpacity(0);
                   
                    
                    
                }
            }
        };
       
       
       
         final Runnable runnable2 = new Runnable() {
            int countdownStarter = 8;

            public void run( ) {

                System.out.println(countdownStarter);
                countdownStarter--;

                if (countdownStarter < 0) {
                    System.out.println("Timer Over!");
                     
                     label_pwd.setText("");
                     label_pwd.setTextFill(Color.BLACK);
                     progress_pwd.setProgress(0.0);
                     progress_pwd.setStyle("-fx-accent: grey;");
                      label_pwd.setOpacity(0);
                      scheduler.shutdown();
                      
                    
                    
                }
            }
        };

*/

    @FXML
    private void checkpwd(KeyEvent event) {
        
         s= validateStrength(signup_pwd.getText()); 
    }
    
   
  
    
  
}
    
    
    



