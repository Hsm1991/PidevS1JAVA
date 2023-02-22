/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import entities.Reclamation;
import entities.Session;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;
import services.ReclamationCRUD;

/**
 * FXML Controller class
 *
 * @author User
 */
public class AjoutReclamationController implements Initializable {

    @FXML
    private AnchorPane anchorPaneCommande;
    @FXML
    private Button retour;
    @FXML
    private ChoiceBox<String> selon;
    @FXML
    private Button ajouterBtn;
    @FXML
    private Label lbWarningFacture;
    private Button logout;
    @FXML
    private TextArea decription;
    @FXML
    private Button ao_btn_dashboard;
    @FXML
    private Button ao_btn_manage_users;
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
        
      
        ObservableList<String> list = FXCollections.observableArrayList("Problème Technique","Problème De Livraison","Problème De Paiement");
         selon.setItems(list);
         selon.setValue("Problème Technique");
         
         
    }    

    @FXML
    private void redirectDashboard(ActionEvent event) throws IOException, SQLException {
           int idCurrentUser = Integer.parseInt(Session.id);
        Session session = new Session();
        String roleArray = session.getRole(idCurrentUser);
        String role = roleArray.substring(2,roleArray.length()-2);
        System.err.println(role);
        
        
        if ("ROLE_ARTISTE".equals(role) ){
        Parent root = FXMLLoader.load(getClass().getResource("CCC.fxml"));
        ao_btn_dashboard.getScene().getWindow().hide();
        ao_btn_dashboard.getScene().getWindow();
        
        Stage mainStage = new Stage();
        Scene scene = new Scene(root);
        mainStage.setScene(scene);
        mainStage.show();

    }
        else if ("ROLE_PRESTATAIRE".equals(role)){
             Parent root = FXMLLoader.load(getClass().getResource("Prestataire.fxml"));
        ao_btn_dashboard.getScene().getWindow().hide();
        ao_btn_dashboard.getScene().getWindow();
        
        Stage mainStage = new Stage();
        Scene scene = new Scene(root);
        mainStage.setScene(scene);
        mainStage.show();
        }
        else if ("ROLE_SUPER_ADMIN".equals(role)){
        Parent root = FXMLLoader.load(getClass().getResource("AdminDashboard.fxml"));
        ao_btn_dashboard.getScene().getWindow().hide();
        ao_btn_dashboard.getScene().getWindow();
        
        Stage mainStage = new Stage();
        Scene scene = new Scene(root);
        mainStage.setScene(scene);
        mainStage.show();
        }
        else if ("ROLE_USER".equals(role) ){
              Parent root = FXMLLoader.load(getClass().getResource("UserDashboard.fxml"));
        ao_btn_dashboard.getScene().getWindow().hide();
        ao_btn_dashboard.getScene().getWindow();
        
        Stage mainStage = new Stage();
        Scene scene = new Scene(root);
        mainStage.setScene(scene);
        mainStage.show();
        }
    }

    @FXML
    private void redirectReclamationCommandeSaif(ActionEvent event) throws IOException {
           ao_btn_dashboard.getScene().getWindow().hide();
         Parent root = FXMLLoader.load(getClass().getResource("ReclamationFront.fxml"));
         Stage mainStage = new Stage();
         Scene scene = new Scene(root);
         mainStage.setScene(scene);
         mainStage.show();
    }

    @FXML
    private void redirectCommandes(ActionEvent event) throws IOException {
           ao_btn_dashboard.getScene().getWindow().hide();
         Parent root = FXMLLoader.load(getClass().getResource("CommandeSaif.fxml"));
         Stage mainStage = new Stage();
         Scene scene = new Scene(root);
         mainStage.setScene(scene);
         mainStage.show();
    }

    @FXML
    private void retourArriere(ActionEvent event) throws IOException {
         ao_btn_dashboard.getScene().getWindow().hide();
         Parent root = FXMLLoader.load(getClass().getResource("ReclamationFront.fxml"));
         Stage mainStage = new Stage();
         Scene scene = new Scene(root);
         mainStage.setScene(scene);
         mainStage.show();
    }

    @FXML
    private void ajouter(ActionEvent event) {
        String selonAjout = selon.getValue();
        String descriptionAjout = decription.getText();
        ReclamationCRUD rcrd = new ReclamationCRUD();
        CommandeSaifController ctrl = new CommandeSaifController();
        String date = new SimpleDateFormat("yyyy/MM/dd  HH:mm:ss")
                    .format(Calendar.getInstance().getTime());
        
        Reclamation r = new Reclamation();
        
        r.setDescription(descriptionAjout);
        r.setIduser(Integer.parseInt(Session.id) );
        r.setMail(ctrl.afficherParIdMail(Integer.parseInt(Session.id)));
        r.setSelon(selonAjout);
        r.setRecdate(date);
        
        rcrd.ajouterReclamation(r);
        System.out.println("added");
        
        
                     Notifications notificationBuilder = Notifications.create()
                    .title("Effectué")
                    .text("Réclamation Ajoutée Avec Succès")
                     .graphic(null)
                     .hideAfter(Duration.seconds(10))
                     .position(Pos.BASELINE_RIGHT);

             notificationBuilder.showConfirm();
        
    }

    @FXML
    private void signout10(ActionEvent event) throws IOException {
        ao_btn_dashboard.getScene().getWindow().hide();
         Parent root = FXMLLoader.load(getClass().getResource("test.fxml"));
         Stage mainStage = new Stage();
         Scene scene = new Scene(root);
         mainStage.setScene(scene);
         mainStage.show();
    }
    
}
