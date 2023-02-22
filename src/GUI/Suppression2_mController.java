/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import services.HistoriqueVenteCRUD;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author LENOVO
 */
public class Suppression2_mController implements Initializable {

    @FXML
    private Label labelsup2;
    @FXML
    private Button confirmer2;
    @FXML
    private Button annuler2;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void confirmer2(ActionEvent event) throws IOException {
         HistoriqueVenteCRUD ecd = new HistoriqueVenteCRUD();
        ObservableList EV =  ecd.afficherHistVentes();
        ecd.supprimerHistVentes(Interfaces.h1.getIdVent());
        
        labelsup2.getScene().getWindow().hide();
              Parent root = FXMLLoader.load(getClass().getResource("OeuvVenMaram.fxml"));
         Stage mainStage = new Stage();
         Scene scene = new Scene(root);
         mainStage.setScene(scene);
         mainStage.show();
    }

    @FXML
    private void annuler_supp2(ActionEvent event) throws IOException {
         annuler2.getScene().getWindow().hide();
         Parent root = FXMLLoader.load(getClass().getResource("OeuvVenMaram.fxml"));
         Stage mainStage = new Stage();
         Scene scene = new Scene(root);
         mainStage.setScene(scene);
         mainStage.show();}
    }
    

