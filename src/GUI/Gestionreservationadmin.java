/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import services.ResCrud;
import services.SalleCrud;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;
import entities.*;
import java.io.IOException;
import java.sql.SQLException;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.util.Duration;


/**
 * FXML Controller class
 *
 * @author MLLE-BAHRI
 */
public class Gestionreservationadmin implements Initializable {

    @FXML
    private AnchorPane nadares;
    @FXML
    private AnchorPane anchorpane222Nb;
    @FXML
    private TableView<res> table2Nb;
    @FXML
    private TableColumn<res, Integer> colNum;
    @FXML
    private TableColumn<res, String> colNomartiste;
    @FXML
    private TableColumn<res, String> colDate;
    @FXML
    private TableColumn<res, Float> colMontant;
    @FXML
    private TextField tfRecherche;
    @FXML
    private Button btnSupprimer;
    @FXML
    private Label lbWarningSelect;
    @FXML
    private AnchorPane kbira;
    @FXML
    private Button ao_btn_dashboard;
    @FXML
    private Button ao_btn_manage_roles;
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
        ResCrud ecd = new ResCrud();
         ObservableList EV =  ecd.afficherReservation();
         
         
         colNum.setCellValueFactory(new PropertyValueFactory<res, Integer>("num_salle"));
         colNomartiste.setCellValueFactory(new PropertyValueFactory<res, String>("nom_artiste"));
         colDate.setCellValueFactory(new PropertyValueFactory<res, String>("date_res"));
         colMontant.setCellValueFactory(new PropertyValueFactory<res, Float>("montant"));
         
         table2Nb.setItems(EV);
    }    

    @FXML
    private void redirectSalle(ActionEvent event) throws IOException {
        
                 kbira.getScene().getWindow().hide();
         Parent root = FXMLLoader.load(getClass().getResource("gestionsalleadmin.fxml"));
         Stage mainStage = new Stage();
         Scene scene = new Scene(root);
         mainStage.setScene(scene);
         mainStage.show();
}     
    

    @FXML
    private void deleteRes(ActionEvent event) {
        try{
        res r = table2Nb.getSelectionModel().getSelectedItem();
            System.out.println(r);
        //String num = num1Nb.getText();  
        ResCrud sc = new ResCrud();
        sc.supprimerres(r);
        
            ObservableList EV =  sc.afficherReservation();
            table2Nb.setItems(EV);
            lbWarningSelect.setVisible(false);
                    }catch(Exception ex){
                        lbWarningSelect.setVisible(true);
                    }
         
      /*  Notifications notificationBuilder = Notifications.create()
                    .title("Effectué")
                    .text("reservation supprimée")
                     .graphic(null)
                     .hideAfter(Duration.seconds(10))
                     .position(Pos.BASELINE_RIGHT)
                     .onAction(new EventHandler<ActionEvent>(){
                         @Override
                         public void handle(ActionEvent event){
                             System.out.println("reservation supprimée");
                         }
                     }
                     );

             notificationBuilder.showConfirm();*/
             


    }


    @FXML
    private void go_dashboard(ActionEvent event) throws IOException, SQLException {
          int idCurrentUser = Integer.parseInt(Session.id);
        Session session = new Session();
        String roleArray = session.getRole(idCurrentUser);
        String role = roleArray.substring(2,roleArray.length()-2);
        System.err.println(role);
        
        
        if ("ROLE_ARTISTE".equals(role) ){
        Parent root = FXMLLoader.load(getClass().getResource("CCC.fxml"));
        btnSupprimer.getScene().getWindow().hide();
        btnSupprimer.getScene().getWindow();
        
        Stage mainStage = new Stage();
        Scene scene = new Scene(root);
        mainStage.setScene(scene);
        mainStage.show();

    }
        else if ("ROLE_PRESTATAIRE".equals(role)){
             Parent root = FXMLLoader.load(getClass().getResource("Prestataire.fxml"));
        btnSupprimer.getScene().getWindow().hide();
        btnSupprimer.getScene().getWindow();
        
        Stage mainStage = new Stage();
        Scene scene = new Scene(root);
        mainStage.setScene(scene);
        mainStage.show();
        }
        else if ("ROLE_SUPER_ADMIN".equals(role)){
        Parent root = FXMLLoader.load(getClass().getResource("AdminDashboard.fxml"));
        btnSupprimer.getScene().getWindow().hide();
        btnSupprimer.getScene().getWindow();
        
        Stage mainStage = new Stage();
        Scene scene = new Scene(root);
        mainStage.setScene(scene);
        mainStage.show();
        }
        else if ("ROLE_USER".equals(role) ){
              Parent root = FXMLLoader.load(getClass().getResource("UserDashboard.fxml"));
        btnSupprimer.getScene().getWindow().hide();
        btnSupprimer.getScene().getWindow();
        
        Stage mainStage = new Stage();
        Scene scene = new Scene(root);
        mainStage.setScene(scene);
        mainStage.show();
        }
    }

    @FXML
    private void signout2022(ActionEvent event) throws IOException {
         Parent root = FXMLLoader.load(getClass().getResource("test.fxml"));
        btnSupprimer.getScene().getWindow().hide();
        btnSupprimer.getScene().getWindow();
        
        Stage mainStage = new Stage();
        Scene scene = new Scene(root);
        mainStage.setScene(scene);
        mainStage.show();
    }
}
