/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author User
 */
public class AdminDashboardController implements Initializable {

    @FXML
    private Button gsalle1;
    @FXML
    private Button gpan212;
    @FXML
    private Button gpan11;
    @FXML
    private Button gpan21;
    @FXML
    private Button gpan22222;
    @FXML
    private Button gpan231;
    @FXML
    private Button gosalle3232;
    @FXML
    private Button ao_btn_signout;
    @FXML
    private Label labelsignout;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void gosalle_btn(ActionEvent event) throws IOException {
               labelsignout.getScene().getWindow().hide();
        labelsignout.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("gestionreservationadmin.fxml"));
        Stage mainStage = new Stage();
        Scene scene = new Scene(root);
        mainStage.setScene(scene);
        mainStage.show();
    }

    @FXML
    private void afficher_stock(ActionEvent event) throws IOException {
            labelsignout.getScene().getWindow().hide();
        labelsignout.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("ProduitPMdetails.fxml"));
        Stage mainStage = new Stage();
        Scene scene = new Scene(root);
        mainStage.setScene(scene);
        mainStage.show();
    
    }

    private void logout30(ActionEvent event) throws IOException {
            labelsignout.getScene().getWindow().hide();
        labelsignout.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("test.fxml"));
        Stage mainStage = new Stage();
        Scene scene = new Scene(root);
        mainStage.setScene(scene);
        mainStage.show();
    }


    @FXML
    private void afficher_rec(ActionEvent event) throws IOException {
           labelsignout.getScene().getWindow().hide();
        labelsignout.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("ReclamationBack.fxml"));
        Stage mainStage = new Stage();
        Scene scene = new Scene(root);
        mainStage.setScene(scene);
        mainStage.show();
    }

    @FXML
    private void afficher_cmd(ActionEvent event) throws IOException {
         labelsignout.getScene().getWindow().hide();
        labelsignout.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("CommadeSaifBack.fxml"));
        Stage mainStage = new Stage();
        Scene scene = new Scene(root);
        mainStage.setScene(scene);
        mainStage.show();
    }

    @FXML
    private void affdi_events(ActionEvent event) throws IOException {
         labelsignout.getScene().getWindow().hide();
        labelsignout.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("main.fxml"));
        Stage mainStage = new Stage();
        Scene scene = new Scene(root);
        mainStage.setScene(scene);
        mainStage.show();
    }

    @FXML
    private void afficher_oeuvres(ActionEvent event) throws IOException {
             labelsignout.getScene().getWindow().hide();
      labelsignout.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("Artiste.fxml"));
        Stage mainStage = new Stage();
        Scene scene = new Scene(root);
        mainStage.setScene(scene);
        mainStage.show();
    }

    @FXML
    private void go_users(ActionEvent event) throws IOException {
            labelsignout.getScene().getWindow().hide();
        labelsignout.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("manage_users.fxml"));
        Stage mainStage = new Stage();
        Scene scene = new Scene(root);
        mainStage.setScene(scene);
        mainStage.show();
    }

    @FXML
    private void signout50(ActionEvent event) throws IOException {
          labelsignout.getScene().getWindow().hide();
        labelsignout.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("test.fxml"));
        Stage mainStage = new Stage();
        Scene scene = new Scene(root);
        mainStage.setScene(scene);
        mainStage.show();
    }
    
}
