/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

/**
 * FXML Controller class
 *
 * @author User
 */
public class Confirm_deleteController implements Initializable {

    @FXML
    private Button ao_btn_yes_del_role;
    @FXML
    private Button ao_btn_no_del_role;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void confirmDelete(ActionEvent event) {
    }

    @FXML
    private void cancelDelete(ActionEvent event) {
    }
    
}
