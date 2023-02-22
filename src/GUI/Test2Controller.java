/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import static GUI.TestController.infoBox;
import static com.sun.org.apache.xalan.internal.xsltc.compiler.util.Type.Int;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.SortEvent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import entities.Role;
import entities.Session;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import services.RoleCrud;
import utils.Notification;

/**
 * FXML Controller class
 *
 * @author user
 */
public class Test2Controller implements Initializable {

  Notification ntf = new Notification();
    @FXML
    private TableView<Role> ao_table_role;
    @FXML
    private Button ao_btn_mod_role;
    @FXML
    private Button ao_btn_del_role;
    @FXML
    private TableColumn<Role, Integer> ao_role_id;
    @FXML
    private TableColumn<Role, String> ao_role_name;
    @FXML
    private TableColumn<Role, String> ao_role_detail;
    @FXML
    private TextField ao_search_role;
    @FXML
    private Button ao_btn_refresh;
    @FXML
    private TextField ao_new_role_id;
    @FXML
    private TextField ao_new_role_name;
    @FXML
    private TextArea ao_new_role_detail;
    @FXML
    private Button ao_ntn_add_role;
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
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
       
        
    
      RoleCrud rc= new RoleCrud();
     ObservableList list_roles =  rc.showAllRoles();
      ao_role_id.setCellValueFactory(new PropertyValueFactory<Role, Integer>("role_id"));
        ao_role_name.setCellValueFactory(new PropertyValueFactory<Role, String>("role_name"));
        ao_role_detail.setCellValueFactory(new PropertyValueFactory<Role, String>("role_detail"));
    

       list_roles = rc.showAllRoles();
        ao_table_role.setItems(list_roles);
        
        
        UpdateTable();
        
        /// search bar _ table view filter result as you search  ///
        
        FilteredList<Role> filteredData = new FilteredList<>(list_roles,b -> true);
        
        ao_search_role.textProperty().addListener((observable, oldValue , newValue) -> {
        filteredData.setPredicate(Role ->   {
            
            // if no search , display all 
            if (newValue.isEmpty() || newValue ==null)
             { return true;}
        
            String searchkeyword = newValue.toLowerCase();
            if (String.valueOf(Role.getRole_id()).indexOf(searchkeyword)> -1) {
                return true;  // means we found match in id
                  } 
            else if (Role.getRole_name().toLowerCase().indexOf(searchkeyword)> -1) {
                return true;  // means we found match in name
                  } 
         else if (Role.getRole_detail().toLowerCase().indexOf(searchkeyword)> -1) {
                return true;  // means we found match in detail
                  } 
            
            
         else return false;  // no match found

        });
         });
        SortedList<Role> sortedData = new SortedList<>(filteredData);
        // bind sorted result with table view 
        sortedData.comparatorProperty().bind(ao_table_role.comparatorProperty());
        // apply filtered and sorted data to able view 
        ao_table_role.setItems(sortedData);
 
     
    }    


    
    // pass selected person to modify scene
    @FXML
    private void modify_role(ActionEvent event) throws IOException {
        
           FXMLLoader loader = new FXMLLoader();
           loader.setLocation(getClass().getResource("modify_role.fxml"));
           Parent root =loader.load();
            
            Role r = ao_table_role.getSelectionModel().getSelectedItem();
            // access controller and call method 
            Modify_roleController mc = loader.getController();
            mc.initData(r);
            
            Scene scene = new Scene(root); 
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.initStyle(StageStyle.UTILITY);
            stage.show();
            
            UpdateTable();
          //  Modify_roleController mc = loader.getController();
       //     ao_btn_mod_role.getScene().setRoot(root);
            
          
          

      
        
    }

    @FXML
    private void delete_role(ActionEvent event) throws IOException {
       
        RoleCrud rc= new RoleCrud();
        ObservableList list =  rc.showAllRoles();
        int index = ao_table_role.getSelectionModel().getSelectedItem().getRole_id();
     
       
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setContentText("voulez-vous vraiment supprimer ce role ? ");
        alert.setTitle("Confirm delete");
        alert.setHeaderText(null);
        Optional<ButtonType> action = alert.showAndWait();
        if (action.get() == ButtonType.OK)
            
        {  ntf.notif("Role supprimé avec succès");
            rc.deleteRole(index);
        UpdateTable();
        
        
        
        }
        
     
    }
    

 void UpdateTable() {
    
     RoleCrud rc= new RoleCrud();
     ObservableList list_roles =  rc.showAllRoles();
      ao_role_id.setCellValueFactory(new PropertyValueFactory<Role, Integer>("role_id"));
        ao_role_name.setCellValueFactory(new PropertyValueFactory<Role, String>("role_name"));
        ao_role_detail.setCellValueFactory(new PropertyValueFactory<Role, String>("role_detail"));
    
       list_roles = rc.showAllRoles();
        ao_table_role.setItems(list_roles);
     
 }

    @FXML
    private void refreshTable(ActionEvent event) {
        
         UpdateTable();
    }

    private void addNewRole(ActionEvent event) throws IOException {
        
           FXMLLoader loader = new FXMLLoader();
           loader.setLocation(getClass().getResource("AddRole.fxml"));
           Parent root =loader.load();
          Scene scene = new Scene(root); 
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.initStyle(StageStyle.UTILITY);
            stage.show();
        
    }

    @FXML
    private void AddRole(ActionEvent event) throws SQLException {
        
        
        
        
        
        if (ao_new_role_id.getText().isEmpty() || ao_new_role_name.getText().isEmpty() || ao_new_role_detail.getText().isEmpty() ) {
            
        infoBox("veuillez remplir tous les champs ", null, "Failed");
        }
        
       int id =Integer.parseInt( ao_new_role_id.getText());
       String name = ao_new_role_name.getText();
       String detail = ao_new_role_detail.getText();
       
               
        RoleCrud rc = new RoleCrud();
        
        boolean flag = rc.checkRoleExists(id,name,detail);
        boolean flag2 = rc.checkRoleid(id);
        boolean flag3=rc.checkRole(name, detail);
        
     
         if (flag == true  )
                   {
       
      infoBox("Ce role existe déjà", null, "Failed");
                    } 
         
         else if (flag2 == true)
         {
           infoBox("Id utilisé", null, "Failed");
         }
         else if (flag3==true) { infoBox("Ce role existe sous different id ", null, "Failed"); }
         
         else { 
          
            
                 rc.createRole(id, name, detail);
                 UpdateTable();
                  ntf.notif("Role ajouté avec succès");

        
         }
     
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