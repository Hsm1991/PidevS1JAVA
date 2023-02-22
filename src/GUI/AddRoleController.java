/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;

import static GUI.TestController.infoBox;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import services.RoleCrud;

/**
 * FXML Controller class
 *
 * @author User
 */
public class AddRoleController implements Initializable {

    @FXML
    private TextField ao_new_role_id;
    @FXML
    private TextField ao_new_role_name;
    @FXML
    private TextArea ao_new_role_detail;
    @FXML
    private Button ao_ntn_add_role;
    @FXML
    private Button ao_btn_cancel_role;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void AddRole(ActionEvent event) throws SQLException {
        
      
        if (ao_new_role_id.getText().isEmpty() || ao_new_role_name.getText().isEmpty() || ao_new_role_detail.getText().isEmpty() ) {
            
        infoBox("Please fill all fields ", null, "Failed");
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
       
      infoBox("User Already exists", null, "Failed");
                    } 
         
         else if (flag2 == true)
         {
           infoBox("Id already used", null, "Failed");
         }
         else if (flag3==true) { infoBox("role exists under different id", null, "Failed"); }
         else { 
          
             
        rc.createRole(id, name, detail);
           
        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        stage.hide();     
         }
     
      
            }
    
    @FXML
    private void cancel(ActionEvent event) {
        
             
        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        stage.hide();     
        
        
    }
    
}
