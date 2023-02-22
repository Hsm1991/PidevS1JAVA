/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;
import services.UserCrud ;
import entities.User;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import static java.util.concurrent.TimeUnit.SECONDS;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.binding.Bindings;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.Window;
import entities.Role;
import entities.Session;
import java.sql.Statement;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import utils.MyConnexion;





/**
 * FXML Controller class
 *
 * @author user
 */
public class TestController implements Initializable {

     int interval =2;
     Timer timer;
     

    @FXML
    private Button ao_btnsignup;
    @FXML
    private TextField ao_xlogin;
   
    @FXML
    private Button ao_btnlogin;
    @FXML
    private Label ao_forgot_pwd;
    @FXML
    private ToggleButton pwd_show;
    @FXML
    private Label show_pwd;
    @FXML
    private PasswordField ao_xpassword;
    @FXML
    private Rectangle rect;
    @FXML
    private ImageView showimg;
    @FXML
    private ImageView hideimg;
        Session s = new Session();
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        

        
        show_pwd.setVisible(false);
        showimg.setVisible(true);
        hideimg.setVisible(false);
        rect.setVisible(false);
       
    }    
    
    
       int totalAttempts = 3;
ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

    @FXML
    private void login(ActionEvent event) throws SQLException, IOException, InterruptedException {
       
        
        Window owner = ao_btnlogin.getScene().getWindow();
        
        System.out.println(ao_xlogin.getText());
        System.out.println(ao_xpassword.getText());
        
        
        if (ao_xlogin.getText().isEmpty()) {
            showAlert(Alert.AlertType.ERROR, owner, "Form Error!",
                "Veuillez saisir votre pseudo");
            return;
        }
        if (ao_xpassword.getText().isEmpty()) {
            showAlert(Alert.AlertType.ERROR, owner, "Form Error!",
                "Veuillez saisir votre mot de passe");
            return;
        }
        
     
        
        String loginInfo = ao_xlogin.getText();
        String pwdInfo = ao_xpassword.getText();
        
             
          s.id=getSessIdByMail(loginInfo);
           System.out.println(s.toString());
        
      //  MyConnexion mc = MyConnexion.getInstance();
                
        UserCrud pcd =new UserCrud();
        
        boolean flag = pcd.checkUserExists(loginInfo, pwdInfo);
        
     
         if (flag == false & totalAttempts != 0 )
                   {
            totalAttempts--;
           infoBox("Veuillez saisir un identifiant de connexion et un mot de passe corrects", null, "Failed");
            System.out.println(totalAttempts);
            
             ao_xlogin.setText("");
        ao_xpassword.setText("");
        
                    } 
         
         else  if (totalAttempts == 0)
                { infoBox("Nombre maximum de tentatives dépassé, réessayez plus tard", null, "Failed");
               
                  ao_btnlogin.setDisable(true); 
                  
         
               
             scheduler.scheduleAtFixedRate(runnable, 0, 1, SECONDS);
            
           
    
             }
    
           
                
         
          else {
                 
        //todo redirect
                       
                String role=pcd.getRolefromMail(loginInfo);

                    if (role.equalsIgnoreCase("[\"ROLE_SUPER_ADMIN\"]")){



              /* Node node = (Node) event.getSource();
              Stage stage = (Stage) node.getScene().getWindow();
               stage.hide();   
              FXMLLoader loader = new FXMLLoader();
              loader.setLocation(getClass().getResource("AdminDashboard.fxml"));

              Parent root =loader.load();

               Scene scene = new Scene(root); 


               Stage stage2 = new Stage();
               stage2.setScene(scene);
               stage2.initStyle(StageStyle.UNDECORATED);
               stage2.setWidth(1300);
               stage2.setHeight(700);
               stage2.show();

               //1207 700 */
              
                Parent root = FXMLLoader.load(getClass().getResource("AdminDashboard.fxml"));
        ao_btnlogin.getScene().getWindow().hide();
        ao_btnlogin.getScene().getWindow();
        
        Stage mainStage = new Stage();
        Scene scene = new Scene(root);
        mainStage.setScene(scene);
        mainStage.show();
              

                }
                    else  if (role.equalsIgnoreCase("[\"ROLE_USER\"]")){
                          Parent root = FXMLLoader.load(getClass().getResource("UserDashboard.fxml"));
        ao_btnlogin.getScene().getWindow().hide();
        ao_btnlogin.getScene().getWindow();
        
        Stage mainStage = new Stage();
        Scene scene = new Scene(root);
        mainStage.setScene(scene);
        mainStage.show();
                    }
                    else  if (role.equalsIgnoreCase("[\"ROLE_ARTISTE\"]")){
                          Parent root = FXMLLoader.load(getClass().getResource("CCC.fxml"));
        ao_btnlogin.getScene().getWindow().hide();
        ao_btnlogin.getScene().getWindow();
        
        Stage mainStage = new Stage();
        Scene scene = new Scene(root);
        mainStage.setScene(scene);
        mainStage.show();
                    }
                    else  if (role.equalsIgnoreCase("[\"ROLE_PRESTATAIRE\"]")){
                          Parent root = FXMLLoader.load(getClass().getResource("Prestataire.fxml"));
        ao_btnlogin.getScene().getWindow().hide();
        ao_btnlogin.getScene().getWindow();
        
        Stage mainStage = new Stage();
        Scene scene = new Scene(root);
        mainStage.setScene(scene);
        mainStage.show();
                    }
                    
                    
                    
                    
        }
                
    }

   public static void infoBox(String infoMessage, String headerText, String title) {
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setContentText(infoMessage);
        alert.setTitle(title);
        alert.setHeaderText(headerText);
        alert.showAndWait();
    }

    private static void showAlert(Alert.AlertType alertType, Window owner, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.initOwner(owner);
        alert.show();
    }

    @FXML
    private void gotosignup(ActionEvent event) throws IOException {
        
        
          FXMLLoader loader = new FXMLLoader();
           loader.setLocation(getClass().getResource("signup.fxml"));
           
           
           Parent root =loader.load();
           
       
            
            Scene scene = new Scene(root); 
            
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.initStyle(StageStyle.UTILITY);
            stage.setWidth(1011);
            stage.setHeight(800);
            stage.show(); 
            
            
            /*ao_btnlogin.getScene().getWindow().hide();
                Parent root = FXMLLoader.load(getClass().getResource("signup.fxml"));
                Stage mainStage = new Stage();
                mainStage.initStyle(StageStyle.UTILITY);
                mainStage.setWidth(1011);
                mainStage.setHeight(800);
                Scene scene = new Scene(root);
                mainStage.setScene(scene);
                mainStage.show();*/
            
      
        
    }

    @FXML
    private void sendpwd(MouseEvent event) throws IOException {
        
           FXMLLoader loader = new FXMLLoader();
           loader.setLocation(getClass().getResource("forgotPwd.fxml"));
           Parent root =loader.load();
           Scene scene = new Scene(root); 
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.initStyle(StageStyle.UTILITY);
            stage.show();
        
        
}


        
        /*
        try {
            String loginInfo = ao_xlogin.getText();
            String pwdInfo = ao_xpassword.getText();
            
            FXMLLoader loader = new FXMLLoader (getClass().getResource("test2.fxml"));
            
            Parent root =loader.load();
            Test2Controller t2c = loader.getController();
            ao_xlogin.getScene().setRoot(root);
        } catch (IOException ex) {
            Logger.getLogger(TestController.class.getName()).log(Level.SEVERE, null, ex);
        }
       h 7 
    } 


*/

   final Runnable runnable = new Runnable() {
            int countdownStarter = 10;

            public void run( ) {

                System.out.println(countdownStarter);
                countdownStarter--;

                if (countdownStarter < 0) {
                    System.out.println("Timer Over!");
                     scheduler.shutdown();
                    ao_btnlogin.setDisable(false); 
                   
                    
                    
                }
            }
        };

    @FXML
    private void showpassword(ActionEvent event) {
        
          
         showimg.setVisible(true);
          hideimg.setVisible(false);
          rect.setVisible(false);
          rect.setOpacity(1);
         
        if (pwd_show.isSelected())
            
        {   showimg.setVisible(false);
            hideimg.setVisible(true);
            show_pwd.setVisible(true);
            rect.setVisible(true);
            show_pwd.textProperty().bind(Bindings.concat(ao_xpassword.getText()));
            show_pwd.setText("hide");
            
             
            
        }
        else 
            
        {   rect.setVisible(false);
            rect.setOpacity(0);
            ao_xpassword.setText(show_pwd.getText());
            show_pwd.setVisible(false);
            show_pwd.setText("show");
            
           
            
            
        }
        
        
    }

    @FXML
    private void typepassword(KeyEvent event) {
        
       show_pwd.textProperty().bind(Bindings.concat(ao_xpassword.getText()));
       // ao_xpassword.setText(show_pwd.getText());
    }
    
    
private String getSessIdByMail(String email){
         Connection cnx2 ; 
         cnx2=MyConnexion.getInstance().getCnx();
         String mySessId = "0" ;
        try {
             String showOneUser= "SELECT * FROM user where email=?";
           PreparedStatement st = cnx2.prepareStatement(showOneUser);
            st.setString(1, email);
            System.out.println(st);
            System.out.println(email);
            ResultSet rs = st.executeQuery();
            while (rs.next()){
               mySessId = rs.getString("id");
           
               
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserCrud.class.getName()).log(Level.SEVERE, null, ex);
        }
        return mySessId;
    }


    

}