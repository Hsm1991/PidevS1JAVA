/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

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
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author user
 */
public class DashboardAdminController implements Initializable {

    @FXML
    private Button ao_btn_manage_roles;
    @FXML
    private BorderPane mainPane;
    @FXML
    private Button ao_btn_dashboard;
    @FXML
    private Button ao_btn_manage_users;
    @FXML
    private Button ao_btn_signout;
    @FXML
    private Label labelsignout;

    @FXML
    private void GoToManageRoles(ActionEvent event) throws IOException {
        
        
          FXMLLoader loader = new FXMLLoader();
        
           FxmlLoader object = new FxmlLoader();
           Pane view = object.getPage("test2");
           mainPane.setCenter(view);
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
    }

    @FXML
    private void GoToDashboard(ActionEvent event) {
    }

    @FXML
    private void GoToManagUsers(ActionEvent event) throws IOException {
        
         
          FXMLLoader loader = new FXMLLoader();
        
           FxmlLoader object = new FxmlLoader();
           Pane view = object.getPage("manage_users");
           mainPane.setCenter(view);
        
        
    }

    @FXML
    private void signout(ActionEvent event) throws IOException{
        
        
        
         /* FXMLLoader loader = new FXMLLoader (getClass().getResource("test.fxml"));
          
            Parent root =loader.load();
            TestController sc = loader.getController();
            labelsignout.getScene().setRoot(root);  */
            
            labelsignout.getScene().getWindow().hide();
         Parent root = FXMLLoader.load(getClass().getResource("test.fxml"));
         Stage mainStage = new Stage();
         Scene scene = new Scene(root);
         mainStage.setScene(scene);
         mainStage.show();
    }
    
         
    }
    

    
