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
public class PrestataireController implements Initializable {

    private Button gsalle;
    @FXML
    private Button gpan;
    @FXML
    private Button gpan1;
    @FXML
    private Button gpan2;
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


    private void gsalle_btn(ActionEvent event) throws IOException {
           labelsignout.getScene().getWindow().hide();
        labelsignout.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("gestionreservationadmin.fxml"));
        Stage mainStage = new Stage();
        Scene scene = new Scene(root);
        mainStage.setScene(scene);
        mainStage.show();
    }

      @FXML
    private void gpan_btn(ActionEvent event) throws IOException {
          labelsignout.getScene().getWindow().hide();
        labelsignout.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("ProduitPMdetails.fxml"));
        Stage mainStage = new Stage();
        Scene scene = new Scene(root);
        mainStage.setScene(scene);
        mainStage.show();
    }


    @FXML
    private void aff_rec(ActionEvent event) throws IOException {
           labelsignout.getScene().getWindow().hide();
        labelsignout.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("ReclamationBack.fxml"));
        Stage mainStage = new Stage();
        Scene scene = new Scene(root);
        mainStage.setScene(scene);
        mainStage.show();
    }

    @FXML
    private void aff_cmd(ActionEvent event) throws IOException {
           labelsignout.getScene().getWindow().hide();
        labelsignout.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("CommadeSaifBack.fxml"));
        Stage mainStage = new Stage();
        Scene scene = new Scene(root);
        mainStage.setScene(scene);
        mainStage.show();
    }

    @FXML
    private void signout(ActionEvent event) throws IOException {
          labelsignout.getScene().getWindow().hide();
        labelsignout.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("test.fxml"));
        Stage mainStage = new Stage();
        Scene scene = new Scene(root);
        mainStage.setScene(scene);
        mainStage.show();
    }
    
}
