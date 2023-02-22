/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import entities.ProduitPM;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.Duration;
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
import org.controlsfx.control.Notifications;
import services.ProduitPMService;

/**
 * FXML Controller class
 *
 * @author elbootic.com
 */
public class DeleteController implements Initializable {

    @FXML
    private Button confSupp;
    @FXML
    private Button annulerSUp;
    @FXML
    private Label messsupp;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void confirmerSUP(ActionEvent event) throws SQLException, IOException {
        ProduitPMService ecd = new ProduitPMService();
        ObservableList EV =  ecd.afficherProduit();
        ecd.supprimerProduitPM(Interfaces.pm.getIDProd());
        
        messsupp.getScene().getWindow().hide();
              Parent root = FXMLLoader.load(getClass().getResource("produitPMdetails.fxml"));
         Stage mainStage = new Stage();
         Scene scene = new Scene(root);
         mainStage.setScene(scene);
         mainStage.show();
         
        
    

        
                     Notifications notificationBuilder = Notifications.create()
                    .title("Effectué")
                    .text("le produit est Supprimé Correctement")
                     .graphic(null)
                     .hideAfter(javafx.util.Duration.INDEFINITE.seconds(10))
                     .position(Pos.BASELINE_RIGHT);

             notificationBuilder.showConfirm();
    }

    @FXML
    private void retourSUP(ActionEvent event) throws IOException {
          annulerSUp.getScene().getWindow().hide();
        Parent root = FXMLLoader.load(getClass().getResource("produitPMdetails.fxml"));
                Stage mainStage = new Stage();
                Scene scene = new Scene(root);
                mainStage.setScene(scene);
                mainStage.show();

          
       
    }
    
}
