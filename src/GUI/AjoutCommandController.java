/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import entities.ProduitPM;
import entities.Session;
import entities.commandPM;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableListBase;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javax.mail.MessagingException;
import org.controlsfx.control.Notifications;
import services.CommandPMService;
import services.ProduitPMService;

/**
 * FXML Controller class
 *
 * @author elbootic.com
 */
public class AjoutCommandController implements Initializable {

    @FXML
    private DatePicker CMdateA;
    @FXML
    private ComboBox<String> CMtype;
    ObservableList<String> type = FXCollections.observableArrayList("artiste" , "fournisseur");
    @FXML
    private TextField CMquantite;
    @FXML
    private TextField CMreference;
    private TextField CMnom;
    @FXML
    private Button CMajouter;
    @FXML
    private ComboBox<ProduitPM> Prood;
    @FXML
    private Button retourtabpm;
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
        try {
            ProduitPMService t= new ProduitPMService();
            Prood.setItems(t.getProduits());
            CMtype.setItems(type);
            // TODO
        } catch (SQLException ex) {
            Logger.getLogger(AjoutCommandController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    

    @FXML
    private void ajoutercommand(ActionEvent event) throws SQLException, MessagingException, IOException {
     
        int reference = Integer.parseInt(CMreference.getText());
        int quantite = Integer.parseInt(CMquantite.getText());
        String type = CMtype.getValue();
        Date d = new Date(CMdateA.getValue().getYear()-1901, CMdateA.getValue().getMonthValue()+2, CMdateA.getValue().getDayOfYear());
      
       commandPM  command = new commandPM(Prood.getValue().getIDProd(),Prood.getValue().getNomProd(),reference, d,quantite,1,CMtype.getValue());
        CommandPMService pmalek = new CommandPMService();
     pmalek.ajouterCommande(command,CMtype.getValue());
     
     
             
            CMajouter.getScene().getWindow().hide();
        Parent root = FXMLLoader.load(getClass().getResource("commande.fxml"));
                Stage mainStage = new Stage();
                Scene scene = new Scene(root);
                mainStage.setScene(scene);
                mainStage.show();
                
                
                     Notifications notificationBuilder = Notifications.create()
                    .title("ajouter")
                    .text("commande ajouter")
                     .graphic(null)
                     .hideAfter(javafx.util.Duration.INDEFINITE.seconds(10))
                     .position(Pos.BASELINE_RIGHT);
             notificationBuilder.showConfirm();
     
     
    }

    
            
    @FXML
    private void retourPMaffich(ActionEvent event) throws IOException {
         
            retourtabpm.getScene().getWindow().hide();
        Parent root = FXMLLoader.load(getClass().getResource("produitPMdetails.fxml"));
                Stage mainStage = new Stage();
                Scene scene = new Scene(root);
                mainStage.setScene(scene);
                mainStage.show();
    }


    @FXML
    private void dash_commande(ActionEvent event) throws IOException, SQLException {
        
         int idCurrentUser = Integer.parseInt(Session.id);
        Session session = new Session();
        String roleArray = session.getRole(idCurrentUser);
        String role = roleArray.substring(2,roleArray.length()-2);
        System.err.println(role);
        
        
        if ("ROLE_ARTISTE".equals(role) ){
        Parent root = FXMLLoader.load(getClass().getResource("CCC.fxml"));
        retourtabpm.getScene().getWindow().hide();
        retourtabpm.getScene().getWindow();
        
        Stage mainStage = new Stage();
        Scene scene = new Scene(root);
        mainStage.setScene(scene);
        mainStage.show();

    }
        else if ("ROLE_PRESTATAIRE".equals(role)){
             Parent root = FXMLLoader.load(getClass().getResource("Prestataire.fxml"));
        retourtabpm.getScene().getWindow().hide();
        retourtabpm.getScene().getWindow();
        
        Stage mainStage = new Stage();
        Scene scene = new Scene(root);
        mainStage.setScene(scene);
        mainStage.show();
        }
        else if ("ROLE_SUPER_ADMIN".equals(role)){
        Parent root = FXMLLoader.load(getClass().getResource("AdminDashboard.fxml"));
        retourtabpm.getScene().getWindow().hide();
        retourtabpm.getScene().getWindow();
        
        Stage mainStage = new Stage();
        Scene scene = new Scene(root);
        mainStage.setScene(scene);
        mainStage.show();
        }
        else if ("ROLE_USER".equals(role) ){
              Parent root = FXMLLoader.load(getClass().getResource("UserDashboard.fxml"));
        retourtabpm.getScene().getWindow().hide();
        retourtabpm.getScene().getWindow();
        
        Stage mainStage = new Stage();
        Scene scene = new Scene(root);
        mainStage.setScene(scene);
        mainStage.show();
        }
    }

    @FXML
    private void signouttt(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("test.fxml"));
        retourtabpm.getScene().getWindow().hide();
        retourtabpm.getScene().getWindow();
        
        Stage mainStage = new Stage();
        Scene scene = new Scene(root);
        mainStage.setScene(scene);
        mainStage.show();
    }
    
        
}
