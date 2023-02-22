/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.net.URL;
import java.util.Observable;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import entities.User;
import services.RoleCrud;
import services.UserCrud;
import utils.Notification;

/**
 * FXML Controller class
 *
 * @author user
 */
public class Modify_userController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
    Notification ntf = new Notification();
    
    String role_name;
    
    
    private User selectedUser;
    @FXML
    private TextField ao_xid;
    @FXML
    private TextField ao_xnom;
    @FXML
    private TextField ao_xprenom;
    @FXML
    private TextField ao_xmail;
    @FXML
    private TextField ao_xmdp;
    @FXML
    private TextField ao_xadresse;
    @FXML
    private TextField ao_xcontact;
    @FXML
    private Button ao_btn_modify_ok;
    
    @FXML
    private ComboBox<String> ao_role_combo;
    @FXML
    private Label ao_selected_role;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        RoleCrud rc = new RoleCrud();
        ObservableList<String> listofroles= rc.ListRoleNames();
        
                
        ao_role_combo.setItems(listofroles);
        ao_role_combo.getSelectionModel().selectFirst();
        
                
    }    
    
    
    
      public void initData(User r) 
    { 
 
    selectedUser =r;
    ao_xid.setText(selectedUser.getUser_id());
    ao_xnom.setText(selectedUser.getUser_nom());
    ao_xprenom.setText(selectedUser.getUser_prenom());
    ao_selected_role.setText(selectedUser.getUser_role());
   // ao_role_combo.setValue(selectedUser.getUser_role());
    ao_xmail.setText(selectedUser.getUser_mail());
    ao_xmdp.setText(selectedUser.getUser_mdp());
    ao_xadresse.setText(selectedUser.getUser_adresse());
    ao_xcontact.setText(selectedUser.getUser_tel());
    role_name = selectedUser.getUser_role(); 
    
    }
@FXML
    private void Select_role(ActionEvent event) {
        
        role_name = ao_role_combo.getValue().toString();
       // ao_selected_role.setText(role_name);
        
                
    }
    @FXML
    private void Modify_user(ActionEvent event) {
        RoleCrud rolecrud = new RoleCrud();
        
        String id = ao_xid.getText();
        String nom = ao_xnom.getText();
        String prenom = ao_xprenom.getText();
        String roles = role_name;
        System.out.println(roles);
        String mail = ao_xmail.getText();
        String mdp = ao_xmdp.getText();
        String adresse = ao_xadresse.getText();
        String tel = ao_xcontact.getText();
        int    categ_id = rolecrud.getCategoryId(roles);
        UserCrud uc= new UserCrud();
        
        
       
        uc.updateUser(mdp,nom,prenom,mail,adresse,tel,roles,id,categ_id);
        
        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        stage.hide();   
        ntf.notif("Utilisateur d'id "+id+" est modifié avec succès");
        
    }

    
     
    
    
}
