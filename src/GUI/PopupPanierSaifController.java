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
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author saif
 */
public class PopupPanierSaifController implements Initializable {

    @FXML
    private AnchorPane anchorPanePopupPanier;
    @FXML
    private Button btnPopupPanier;
    @FXML
    private TextField tfPopupPanier;
    @FXML
    private Label warningPanier;

    public PopupPanierSaifController() {
    }
    
    
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }   
    

    @FXML
    private void confirmPopupPanier(ActionEvent event) {
        Integer q ;
        try{
            q = Integer.parseInt(tfPopupPanier.getText());
            PanierController pc = new PanierController();
            pc.setQ(q);
            Stage stage = (Stage) anchorPanePopupPanier.getScene().getWindow();
            stage.close();
        }catch(NumberFormatException ex){
            warningPanier.setVisible(true);
        }
        
    }
    
}
