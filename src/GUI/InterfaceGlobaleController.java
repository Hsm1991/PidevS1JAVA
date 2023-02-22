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
public class InterfaceGlobaleController implements Initializable {
    private Button cltev;
    private Button gsalle;
    @FXML
    private Button gev;
    @FXML
    private Button gou;
    @FXML
    private Button gosalle;
    @FXML
    private Button ao_btn_manage_users;
    @FXML
    private Label labelsignout;
    @FXML
    private Button ao_btn_signout;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    private void conev(ActionEvent event) throws IOException {
           labelsignout.getScene().getWindow().hide();
      labelsignout.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("UserEventInter.fxml"));
        Stage mainStage = new Stage();
        Scene scene = new Scene(root);
        mainStage.setScene(scene);
        mainStage.show();
    }

    private void gouv_btn1(ActionEvent event) throws IOException {
         labelsignout.getScene().getWindow().hide();
      labelsignout.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("ClientMaram.fxml"));
        Stage mainStage = new Stage();
        Scene scene = new Scene(root);
        mainStage.setScene(scene);
        mainStage.show();
        

    }

    @FXML
    private void intcl_btnE(ActionEvent event) throws IOException {
              labelsignout.getScene().getWindow().hide();
        labelsignout.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("main.fxml"));
        Stage mainStage = new Stage();
        Scene scene = new Scene(root);
        mainStage.setScene(scene);
        mainStage.show();
    }

    @FXML
    private void gouv_btn(ActionEvent event) throws IOException {
        labelsignout.getScene().getWindow().hide();
        labelsignout.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("Artiste.fxml"));
        Stage mainStage = new Stage();
        Scene scene = new Scene(root);
        mainStage.setScene(scene);
        mainStage.show();
    }


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
    private void gorec(ActionEvent event) throws IOException {
             gev.getScene().getWindow().hide();
        gev.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("ReclamationFront.fxml"));
        Stage mainStage = new Stage();
        Scene scene = new Scene(root);
        mainStage.setScene(scene);
        mainStage.show();
    }

    @FXML
    private void gosalle(ActionEvent event) throws IOException {
             gev.getScene().getWindow().hide();
        gev.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("reservationartiste.fxml"));
        Stage mainStage = new Stage();
        Scene scene = new Scene(root);
        mainStage.setScene(scene);
        mainStage.show();
    }

    @FXML
    private void signout(ActionEvent event) throws IOException {
                    gev.getScene().getWindow().hide();
        gev.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("test.fxml"));
        Stage mainStage = new Stage();
        Scene scene = new Scene(root);
        mainStage.setScene(scene);
        mainStage.show();
    }



    
}
