/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import static GUI.TestController.infoBox;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import entities.Role;
import entities.Session;
import entities.User;
import java.sql.SQLException;
import services.UserCrud;
import utils.Notification;

/**
 * FXML Controller class
 *
 * @author user
 */
public class Manage_usersController implements Initializable {

    
    Notification ntf = new Notification();
    @FXML
    private TableColumn<User, String> ao_user_id;
    @FXML
    private TableColumn<User, String> ao_user_nom;
    @FXML
    private TableColumn<User, String> ao_user_prenom;
    @FXML
    private TableColumn<User, String> ao_user_role;
    @FXML
    private TableColumn<User, String> ao_user_mail;
    @FXML
    private TableColumn<User, String> ao_user_adresse;
    @FXML
    private TableColumn<User, String> ao_user_tel;
    @FXML
    private TableColumn<User, String> ao_user_mdp;
    @FXML
    private TextField ao_search_user;
    @FXML
    private Button ao_btn_mod_user;
    @FXML
    private Button ao_btn_del_user;
    @FXML
    private Button ao_btn_refresh;
    @FXML
    private Button ao_btn_add_user;
    @FXML
    private TableView<User> ao_table_users;
    @FXML
    private Label ao_label;
    @FXML
    private Button ao_btn_dashboard;
    @FXML
    private Button ao_btn_manage_users;
    @FXML
    private Button ao_btn_manage_roles;
    @FXML
    private Button ao_btn_signout;
    @FXML
    private Label labelsignout;
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
          
    
      UserCrud rc= new UserCrud();
     ObservableList list_users =  rc.showAllUsers();
       ao_user_id.setCellValueFactory(new PropertyValueFactory<User, String>("user_id"));
        ao_user_nom.setCellValueFactory(new PropertyValueFactory<User, String>("user_nom"));
        ao_user_prenom.setCellValueFactory(new PropertyValueFactory<User, String>("user_prenom"));
        ao_user_role.setCellValueFactory(new PropertyValueFactory<User, String>("user_role"));
        ao_user_mail.setCellValueFactory(new PropertyValueFactory<User, String>("user_mail"));
        ao_user_mdp.setCellValueFactory(new PropertyValueFactory<User, String>("user_mdp"));
        ao_user_adresse.setCellValueFactory(new PropertyValueFactory<User, String>("user_adresse"));
        ao_user_tel.setCellValueFactory(new PropertyValueFactory<User, String>("user_tel"));
    

       list_users = rc.showAllUsers();
        ao_table_users.setItems(list_users);
        
        UpdateTable();
        /// search bar _ table view filter result as you search  ///
        
        FilteredList<User> filteredData = new FilteredList<>(list_users,b -> true);
        
        ao_search_user.textProperty().addListener((observable, oldValue , newValue) -> {
        filteredData.setPredicate((User) ->   {
            
            // if no search , display all 
            if (newValue.isEmpty() || newValue ==null)
             { return true;}
        
            String searchkeyword = newValue.toLowerCase();
            if (User.getUser_id().indexOf(searchkeyword)> -1) {
                return true;  // means we found match in id
                  } 
            else if (User.getUser_nom().toLowerCase().indexOf(searchkeyword)> -1) {
                return true;  // means we found match in name
                  } 
            else if (User.getUser_prenom().toLowerCase().indexOf(searchkeyword)> -1) {
                return true;  // means we found match in name
                  } 
            else if (User.getUser_role().toLowerCase().indexOf(searchkeyword)> -1) {
                return true;  // means we found match in name
                  } 
            else if (User.getUser_mail().toLowerCase().indexOf(searchkeyword)> -1) {
                return true;  // means we found match in name
                  } 
            else if (User.getUser_tel().toLowerCase().indexOf(searchkeyword)> -1) {
                return true;  // means we found match in name
                  } 
            else if (User.getUser_adresse().toLowerCase().indexOf(searchkeyword)> -1) {
                return true;  // means we found match in name
                  } 
            else if (User.getUser_mdp().toLowerCase().indexOf(searchkeyword)> -1) {
                return true;  // means we found match in name
                  } 
       
            
         else return false;  // no match found

        });
         });
        SortedList<User> sortedData = new SortedList<>(filteredData);
        // bind sorted result with table view 
        sortedData.comparatorProperty().bind(ao_table_users.comparatorProperty());
        // apply filtered and sorted data to able view 
        ao_table_users.setItems(sortedData);
                
                
        }

    @FXML
    private void modify_user(ActionEvent event) throws IOException {
        
           FXMLLoader loader = new FXMLLoader();
           loader.setLocation(getClass().getResource("modify_user.fxml"));
           Parent root =loader.load();
            
            User r = ao_table_users.getSelectionModel().getSelectedItem();
            // access controller and call method 
            Modify_userController mc = loader.getController();
            mc.initData(r);
            
            Scene scene = new Scene(root); 
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.initStyle(StageStyle.UTILITY);
            stage.show();
            
            UpdateTable();
    }

    @FXML
    private void delete_user(ActionEvent event) {
        
          UserCrud rc= new UserCrud();
        ObservableList list =  rc.showAllUsers();
        String index = ao_table_users.getSelectionModel().getSelectedItem().getUser_id();
     
       
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setContentText("voulez-vous vraiment supprimer ceT utilisateur?  ");
        alert.setTitle("Confirm delete");
        alert.setHeaderText(null);
        Optional<ButtonType> action = alert.showAndWait();
        if (action.get() == ButtonType.OK)
        {  rc.deleteUser(index);
           ntf.notif("Utilisateur d'id"+index+"est supprimé avec succès");
        
        UpdateTable();
        
        }
        
        
    }

    
    
     void UpdateTable() {
    
     UserCrud rc= new UserCrud();
     ObservableList list_users =  rc.showAllUsers();
      ao_user_id.setCellValueFactory(new PropertyValueFactory<User, String>("user_id"));
        ao_user_nom.setCellValueFactory(new PropertyValueFactory<User, String>("user_nom"));
        ao_user_prenom.setCellValueFactory(new PropertyValueFactory<User, String>("user_prenom"));
        ao_user_role.setCellValueFactory(new PropertyValueFactory<User, String>("user_role"));
        ao_user_mail.setCellValueFactory(new PropertyValueFactory<User, String>("user_mail"));
        ao_user_mdp.setCellValueFactory(new PropertyValueFactory<User, String>("user_mdp"));
        ao_user_adresse.setCellValueFactory(new PropertyValueFactory<User, String>("user_adresse"));
        ao_user_tel.setCellValueFactory(new PropertyValueFactory<User, String>("user_tel"));
    
       list_users = rc.showAllUsers();
        ao_table_users.setItems(list_users);
     
 }
    
    
    @FXML
    private void refreshTable(ActionEvent event) {
        UpdateTable();
       
    }

    @FXML
    private void addNewUser(ActionEvent event) throws IOException {
        
        
          FXMLLoader loader = new FXMLLoader();
           loader.setLocation(getClass().getResource("signup.fxml"));
           
           
           Parent root =loader.load();
           
       
            
             Scene scene = new Scene(root); 
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.initStyle(StageStyle.UTILITY);
            stage.show();
            
      
          
            
        
        
    }

    @FXML
    private void sendmail(ActionEvent event) throws IOException {
        
         FXMLLoader loader = new FXMLLoader();
           loader.setLocation(getClass().getResource("sendmail.fxml"));
           Parent root =loader.load();
            
            User r = ao_table_users.getSelectionModel().getSelectedItem();
            // access controller and call method 
            SendmailController mc = loader.getController();
            mc.initData2(r);
            
            Scene scene = new Scene(root); 
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.initStyle(StageStyle.UTILITY);
            stage.show();
            
          
    }

    @FXML
    private void GoToDashboard(ActionEvent event) throws IOException, SQLException {
        
        int idCurrentUser = Integer.parseInt(Session.id);
        Session session = new Session();
        String roleArray = session.getRole(idCurrentUser);
        String role = roleArray.substring(2,roleArray.length()-2);
        System.err.println(role);
        
        
        if ("ROLE_ARTISTE".equals(role) ){
        Parent root = FXMLLoader.load(getClass().getResource("CCC.fxml"));
        ao_btn_dashboard.getScene().getWindow().hide();
        ao_btn_dashboard.getScene().getWindow();
        
        Stage mainStage = new Stage();
        Scene scene = new Scene(root);
        mainStage.setScene(scene);
        mainStage.show();

    }
        else if ("ROLE_PRESTATAIRE".equals(role)){
             Parent root = FXMLLoader.load(getClass().getResource("Prestataire.fxml"));
        ao_btn_dashboard.getScene().getWindow().hide();
        ao_btn_dashboard.getScene().getWindow();
        
        Stage mainStage = new Stage();
        Scene scene = new Scene(root);
        mainStage.setScene(scene);
        mainStage.show();
        }
        else if ("ROLE_SUPER_ADMIN".equals(role)){
        Parent root = FXMLLoader.load(getClass().getResource("AdminDashboard.fxml"));
        ao_btn_dashboard.getScene().getWindow().hide();
        ao_btn_dashboard.getScene().getWindow();
        
        Stage mainStage = new Stage();
        Scene scene = new Scene(root);
        mainStage.setScene(scene);
        mainStage.show();
        }
        else if ("ROLE_USER".equals(role) ){
              Parent root = FXMLLoader.load(getClass().getResource("UserDashboard.fxml"));
        ao_btn_dashboard.getScene().getWindow().hide();
        ao_btn_dashboard.getScene().getWindow();
        
        Stage mainStage = new Stage();
        Scene scene = new Scene(root);
        mainStage.setScene(scene);
        mainStage.show();
        }
     
    }

    @FXML
    private void GoToManagUsers(ActionEvent event) throws IOException {
        
         Parent root = FXMLLoader.load(getClass().getResource("manage_users.fxml"));
        ao_btn_dashboard.getScene().getWindow().hide();
        ao_btn_dashboard.getScene().getWindow();
        
        Stage mainStage = new Stage();
        Scene scene = new Scene(root);
        mainStage.setScene(scene);
        mainStage.show();
        
    }
    
    @FXML
     private void GoToManageRoles(ActionEvent event) throws IOException {
      Parent root = FXMLLoader.load(getClass().getResource("test2.fxml"));
        ao_btn_dashboard.getScene().getWindow().hide();
        ao_btn_dashboard.getScene().getWindow();
        
        Stage mainStage = new Stage();
        Scene scene = new Scene(root);
        mainStage.setScene(scene);
        mainStage.show();
    }
    @FXML
    private void signout(ActionEvent event) throws IOException {
          Parent root = FXMLLoader.load(getClass().getResource("test.fxml"));
        ao_btn_dashboard.getScene().getWindow().hide();
        ao_btn_dashboard.getScene().getWindow();
        
        Stage mainStage = new Stage();
        Scene scene = new Scene(root);
        mainStage.setScene(scene);
        mainStage.show();
    }
    
    
    
   
    
    
}
