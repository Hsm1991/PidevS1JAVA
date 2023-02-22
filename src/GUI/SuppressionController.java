/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

/**
 * FXML Controller class
 *
 * @author User
 */
public class SuppressionController implements Initializable {

    @FXML
    private Label label_supp;
    @FXML
    private Button Confirmation_supp;
    @FXML
    private Button annulation_sup;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void conf_sup_btn(ActionEvent event) {
    }

    @FXML
    private void annulation_sup_btn(ActionEvent event) {
    }
    
}
