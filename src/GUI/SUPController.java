/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import entities.Evenement;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;
import services.EvenementCRUD;
import GUI.Interfaces;
import GUI.MainController;
import javafx.scene.control.Label;

/**
 * FXML Controller class
 *
 * @author User
 */
public class SUPController implements Initializable {

    @FXML
    private Button supp;
    @FXML
    private Button arr;
    @FXML
    private Label aa;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void Supprimer(ActionEvent event) throws IOException, SQLException {
        

            
    
        
        
          EvenementCRUD ecd = new EvenementCRUD();
        ObservableList EV =  ecd.afficherEvenemets();
        ecd.supprimerEvent(Interfaces.ev.getIdEvent());
        
        aa.getScene().getWindow().hide();
              Parent root = FXMLLoader.load(getClass().getResource("main.fxml"));
         Stage mainStage = new Stage();
         Scene scene = new Scene(root);
         mainStage.setScene(scene);
         mainStage.show();
         
        
    

        
                     Notifications notificationBuilder = Notifications.create()
                    .title("Effectué")
                    .text("Evènement Supprimé Correctement")
                     .graphic(null)
                     .hideAfter(Duration.seconds(10))
                     .position(Pos.BASELINE_RIGHT);

             notificationBuilder.showConfirm();
        
        
    }

    @FXML
    private void RETAR(ActionEvent event) throws IOException {
          arr.getScene().getWindow().hide();
         Parent root = FXMLLoader.load(getClass().getResource("main.fxml"));
         Stage mainStage = new Stage();
         Scene scene = new Scene(root);
         mainStage.setScene(scene);
         mainStage.show();
    }

    }
    

