/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import services.RoleCrud;
import entities.Role;
import utils.Notification;
       

/**
 * FXML Controller class
 *
 * @author user
 */
public class Modify_roleController implements Initializable {

    private Role selectedRole;
     Notification nft = new Notification();
    
    @FXML
    private TextField ao_xid_role;
    @FXML
    private TextField ao_xname_role;
    @FXML
    private Button ao_btn_valid_mod_role;
    @FXML
    private TextArea ao_xdetail_role;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        
    }    

    @FXML
    private void validerModification(ActionEvent event) throws SQLException, IOException {
       
        int id = Integer.parseInt(ao_xid_role.getText());
        String name = ao_xname_role.getText();
        String detail = ao_xdetail_role.getText();
        RoleCrud rc= new RoleCrud();
        
        rc.updateRole(name, detail, id);
        
         FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("test2.fxml"));
        Test2Controller roleController = loader.getController();
      
        
        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        stage.hide();     
        nft.notif("Role d'id " +id+ " est modifié \n nom" + name+" \n detail s:"+detail);
        
       
        
   
    }
    
    // accepts role infos to modify 
    public void initData(Role r) 
    { 
 
    selectedRole =r;
    ao_xid_role.setText(String.valueOf(selectedRole.getRole_id()));
    ao_xname_role.setText(selectedRole.getRole_name());
    ao_xdetail_role.setText(selectedRole.getRole_detail());
    }
    
}
