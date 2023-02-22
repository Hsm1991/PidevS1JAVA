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
public class UserDashboardController implements Initializable {

    @FXML
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

    @FXML
    private void gocommandes(ActionEvent event) throws IOException {
         gpan2.getScene().getWindow().hide();
        gpan2.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("CommandeSaif.fxml"));
        Stage mainStage = new Stage();
        Scene scene = new Scene(root);
        mainStage.setScene(scene);
        mainStage.show();
    }

    @FXML
    private void gopanier(ActionEvent event) throws IOException {
          gpan2.getScene().getWindow().hide();
        gpan2.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("panier.fxml"));
        Stage mainStage = new Stage();
        Scene scene = new Scene(root);
        mainStage.setScene(scene);
        mainStage.show();
        
    }



    @FXML
    private void oueuvres_front(ActionEvent event) throws IOException {
           gpan2.getScene().getWindow().hide();
        gpan2.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("ClientMaram.fxml"));
        Stage mainStage = new Stage();
        Scene scene = new Scene(root);
        mainStage.setScene(scene);
        mainStage.show();
        
    }

    @FXML
    private void goevents(ActionEvent event) throws IOException {
               gpan2.getScene().getWindow().hide();
        gpan2.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("EVENTFRONT.fxml"));
        Stage mainStage = new Stage();
        Scene scene = new Scene(root);
        mainStage.setScene(scene);
        mainStage.show();
    }

    @FXML
    private void signout321123(ActionEvent event) throws IOException {
                  gpan2.getScene().getWindow().hide();
        gpan2.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("test.fxml"));
        Stage mainStage = new Stage();
        Scene scene = new Scene(root);
        mainStage.setScene(scene);
        mainStage.show();
    }
    
}
