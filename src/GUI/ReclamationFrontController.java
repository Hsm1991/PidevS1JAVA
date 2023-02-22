/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

//import com.fasterxml.jackson.annotation.JsonTypeInfo.Id;
import entities.Panier;
import entities.Reclamation;
import entities.Session;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
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
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
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
public class ReclamationFrontController implements Initializable {

    @FXML
    private AnchorPane anchorPanePanier;
    private Button logout;
    @FXML
    private TableView<Reclamation> tablePanierSaif;
    @FXML
    private TableColumn<Reclamation, Integer> colId;
    @FXML
    private TableColumn<Reclamation, String> colSelon;
    @FXML
    private TableColumn<Reclamation, String> colDate;
    @FXML
    private Button btnSupprimerProduitSaif;
    @FXML
    private Label lbWarningVideSaif;
    @FXML
    private Label lbWarningSelection;
    @FXML
    private Button btnAjouter;
    @FXML
    private Button btnModifier;
    @FXML
    private Label erreur;
    @FXML
    private Button ao_btn_dashboard;
    @FXML
    private Button ao_btn_signout;
    @FXML
    private Label labelsignout;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        ReclamationCRUD crud = new ReclamationCRUD();
        
        ObservableList<Reclamation> obList = FXCollections.observableArrayList(crud.afficherReclamationById(Integer.parseInt(Session.id)));
        erreur.setVisible(false);
        colId.setCellValueFactory(new PropertyValueFactory<Reclamation , Integer>("id"));
        colSelon.setCellValueFactory(new PropertyValueFactory<Reclamation , String>("selon"));
        colDate.setCellValueFactory(new PropertyValueFactory<Reclamation , String>("recdate"));
        
        tablePanierSaif.setItems(obList);
    }    



    @FXML
    private void supprimerRec(ActionEvent event) {
        // hide erreur
        if (tablePanierSaif.getSelectionModel().getSelectedItem() == null){
            erreur.setVisible(true);
        } 
        else{
            ReclamationCRUD crud = new ReclamationCRUD();
             erreur.setVisible(false);
           int id = tablePanierSaif.getSelectionModel().getSelectedItem().getId();
            Reclamation r = new Reclamation();
            r.setId(id );
            crud.supprimerReclamation(r);
            
            
        erreur.setVisible(false);
        colId.setCellValueFactory(new PropertyValueFactory<Reclamation , Integer>("id"));
        colSelon.setCellValueFactory(new PropertyValueFactory<Reclamation , String>("selon"));
        colDate.setCellValueFactory(new PropertyValueFactory<Reclamation , String>("recdate"));
        
             ObservableList<Reclamation> obList = FXCollections.observableArrayList(crud.afficherReclamationById(Integer.parseInt(Session.id)));
        tablePanierSaif.setItems(obList);
        
                  Notifications notificationBuilder = Notifications.create()
                    .title("Effectué")
                    .text("Réclamation Supprimée Avec Succès")
                     .graphic(null)
                     .hideAfter(Duration.seconds(10))
                     .position(Pos.BASELINE_RIGHT);

             notificationBuilder.showConfirm();
    }
    }

    @FXML
    private void ajouterRec(ActionEvent event) throws IOException {
         tablePanierSaif.getScene().getWindow().hide();
         Parent root = FXMLLoader.load(getClass().getResource("AjoutReclamation.fxml"));
         Stage mainStage = new Stage();
         Scene scene = new Scene(root);
         mainStage.setScene(scene);
         mainStage.show();
    }

    @FXML
    private void ModifierRec(ActionEvent event) throws IOException {
        if (tablePanierSaif.getSelectionModel().getSelectedItem() == null){
            erreur.setVisible(true);
        } 
        else{
             erreur.setVisible(false);
            ModifierReclamationController.idRec = tablePanierSaif.getSelectionModel().getSelectedItem().getId();
            tablePanierSaif.getScene().getWindow().hide();
         Parent root = FXMLLoader.load(getClass().getResource("ModifierReclamation.fxml"));
         Stage mainStage = new Stage();
         Scene scene = new Scene(root);
         mainStage.setScene(scene);
         mainStage.show();
    }
    
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
    private void signout1231(ActionEvent event) throws IOException {
          Parent root = FXMLLoader.load(getClass().getResource("test.fxml"));
        ao_btn_dashboard.getScene().getWindow().hide();
        ao_btn_dashboard.getScene().getWindow();
        
        Stage mainStage = new Stage();
        Scene scene = new Scene(root);
        mainStage.setScene(scene);
        mainStage.show();
    }
}
