/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

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
import javafx.stage.Stage;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;
import services.ProduitCRUD;

/**
 * FXML Controller class
 *
 * @author User
 */
public class SuppressionMaramController implements Initializable {

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
         // TODO
    }    

    @FXML
    private void conf_sup_btn(ActionEvent event) throws IOException {
      ProduitCRUD ecd = new ProduitCRUD();
        ObservableList EV =  ecd.afficherProduit();
        ecd.supprimerProduit(Interfaces.p1.getIdPROD());
        
        label_supp.getScene().getWindow().hide();
              Parent root = FXMLLoader.load(getClass().getResource("Artiste.fxml"));
         Stage mainStage = new Stage();
         Scene scene = new Scene(root);
         mainStage.setScene(scene);
         mainStage.show();
         
         
         
         
         
       
         
         
         
         
         
         
         
         
         
         
         
         
         
         
         
         
         
         
         
         
         
         
         
         
         
         
         
         
         
         Notifications notificationBuilder;
             notificationBuilder = Notifications.create()
             .title("Effectué")
             .text("Oeuvre Supprime Avec Succés")
             .graphic(null)
             .hideAfter(Duration.seconds(10))
             .position(Pos.BASELINE_RIGHT); 
             notificationBuilder.showConfirm();
         
    }

    @FXML
    private void annulation_sup_btn(ActionEvent event)  throws IOException {
        annulation_sup.getScene().getWindow().hide();
         Parent root = FXMLLoader.load(getClass().getResource("Artiste.fxml"));
         Stage mainStage = new Stage();
         Scene scene = new Scene(root);
         mainStage.setScene(scene);
         mainStage.show();}

}
    
