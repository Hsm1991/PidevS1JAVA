/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import entities.Ticket;
import java.io.IOException;
import java.net.URL;
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
import javafx.scene.control.Label;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;
import services.CRUDTicket;
import services.EvenementCRUD;

/**
 * FXML Controller class
 *
 * @author User
 */
public class SUP1Controller implements Initializable {

    @FXML
    private Button dddl;
    @FXML
    private Button bbck;
    @FXML
    private Label asd1;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void delete21(ActionEvent event) throws IOException {
            
          CRUDTicket ecd = new CRUDTicket();
        ObservableList EV =  ecd.afficherTickets();
        ecd.SupprimerTicket(Interfaces.ev1.getIdTicket());
        
        asd1.getScene().getWindow().hide();
              Parent root = FXMLLoader.load(getClass().getResource("TicketsEv.fxml"));
         Stage mainStage = new Stage();
         Scene scene = new Scene(root);
         mainStage.setScene(scene);
         mainStage.show();
         
        
        
        
          Notifications notificationBuilder = Notifications.create()
                    .title("Effectué")
                    .text("Ticket Supprimée Correctement")
                     .graphic(null)
                     .hideAfter(Duration.seconds(10))
                     .position(Pos.BASELINE_RIGHT);

             notificationBuilder.showConfirm();
    }

    @FXML
    private void back21(ActionEvent event) throws IOException {
         bbck.getScene().getWindow().hide();
         Parent root = FXMLLoader.load(getClass().getResource("TicketsEv.fxml"));
         Stage mainStage = new Stage();
         Scene scene = new Scene(root);
          mainStage.setScene(scene);
         mainStage.show();
    }
    
}
