/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import entities.Evenement;
import entities.Session;
import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
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
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;
import services.EvenementCRUD;

/**
 * FXML Controller class
 *
 * @author User
 */
public class EVENTFRONTController implements Initializable {

    @FXML
    private TableView<Evenement> EventTable;
    @FXML
    private TableColumn<Evenement, String> NomEvent;
    @FXML
    private TableColumn<Evenement, String> AdresseEvent;
    @FXML
    private TableColumn<Evenement, String> CapaciteEvent;
    @FXML
    private TableColumn<Evenement, String> NbrTicketsAcheté;
    @FXML
    private TableColumn<Evenement, String> DateDebutEvent;
    @FXML
    private TableColumn<Evenement, String> DateFinEvent;
    @FXML
    private TableColumn<Evenement, String> TypeEvent;
    @FXML
    private TableColumn<Evenement, String> CategorieEvent;
    @FXML
    private TableColumn<Evenement, String> PrixEntrée;
    @FXML
    private TextField RecheV;
    @FXML
    private Button AddEvenement1;
    @FXML
    private ImageView EventsDispo;
    @FXML
    private Label disponibl;
    private Image image1;
    @FXML
    private Label message;
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
        
        
           //////////////////////////REMPLISSAGE TABLE VIEW////////////////////////////////
         EvenementCRUD ecd = new EvenementCRUD();
         ObservableList EV =  ecd.afficherEvenemets();
         
         
         NomEvent.setCellValueFactory(new PropertyValueFactory<Evenement, String>("NomEvent"));
         AdresseEvent.setCellValueFactory(new PropertyValueFactory<Evenement, String>("AdresseEvent"));
         CapaciteEvent.setCellValueFactory(new PropertyValueFactory<Evenement, String>("CapaciteEvent"));
         NbrTicketsAcheté.setCellValueFactory(new PropertyValueFactory<Evenement, String>("nbrTicketAchete"));
         DateDebutEvent.setCellValueFactory(new PropertyValueFactory<Evenement,String>("DateDebutEvent"));
         DateFinEvent.setCellValueFactory(new PropertyValueFactory<Evenement,String>("DateFinEvent"));
         TypeEvent.setCellValueFactory(new PropertyValueFactory<Evenement,String>("TypeEvent"));
         CategorieEvent.setCellValueFactory(new PropertyValueFactory<Evenement,String>("CategorieEvent"));
         PrixEntrée.setCellValueFactory(new PropertyValueFactory<Evenement,String>("PrixEntre"));
         EventTable.setItems(EV);
         
         
                    /////////////////////////RECHERCHE////////////////////////
         FilteredList<Evenement> filteredData = new FilteredList<>(EV, b -> true);
         //2. Set the filter Predicate whenever the filter changes.
         RecheV.textProperty().addListener((observable, oldValue, newValue) -> {
         filteredData.setPredicate(Even -> {
         // If filter text is empty, display all persons.
         if (newValue == null || newValue.isEmpty()) {
         return true;
         }
         // Compare first name and last name of every person with filter text.
         String lowerCaseFilter = newValue.toLowerCase();
         if (Even.getNomEvent().toLowerCase().contains(lowerCaseFilter)) {
         return true; // Filter matches last name.
         }
         else if (Even.getAdresseEvent().toLowerCase().contains(lowerCaseFilter)) {
         return true;}
         
         else if (String.valueOf(Even.getIdEvent()).indexOf(lowerCaseFilter)!=-1){
         return true;}
         else if (String.valueOf(Even.getCapaciteEvent()).indexOf(lowerCaseFilter)!=-1){
         return true;}
         else return String.valueOf(Even.getCategorieEvent()).contains(lowerCaseFilter);
         });
         });
         SortedList<Evenement> sortedData = new SortedList<>(filteredData);
         // 4. Bind the SortedList comparator to the TableView comparator.
         // 	  Otherwise, sorting the TableView would have no effect.
         sortedData.comparatorProperty().bind(EventTable.comparatorProperty());
         // 5. Add sorted (and filtered) data to the table.
         EventTable.setItems(sortedData);
    
        // TODO
        
        message.setVisible(false);
    }    



    @FXML
    private void SelectionEvent(MouseEvent event) {
          Evenement EventData = EventTable.getSelectionModel().getSelectedItem();
    int num = EventTable.getSelectionModel().getFocusedIndex();
        if((num-1) <-1)
        return;
       disponibl.setText(EventData.getNomEvent());
        String uri = "file:" + EventData.getImage1();
        
        image1 = new Image (uri, 285,135, false, true) ;
        EventsDispo.setImage(image1);
    }

    @FXML
    private void AddEvent(ActionEvent event) throws URISyntaxException, IOException {
       Evenement selectedEv =  EventTable.getSelectionModel().getSelectedItem();
       if(selectedEv == null ){
           message.setVisible(true);
       }
       else {
           message.setVisible(false);
             Notifications notificationBuilder = Notifications.create()
                    .title("Effectué")
                    .text("Pour Procéder Au Paiement Vous Serez Redirigé Vers Le Site Web")
                     .graphic(null)
                     .hideAfter(Duration.seconds(10))
                     .position(Pos.BASELINE_RIGHT);

             notificationBuilder.showConfirm();
             
             
               Desktop desk = Desktop.getDesktop();
        
        // now we enter our URL that we want to open in our
        // default browser
        desk.browse(new URI("http://127.0.0.1:8000/login"));
       }
        
    }

    @FXML
    private void godash1001(ActionEvent event) throws IOException, SQLException {
         int idCurrentUser = Integer.parseInt(Session.id);
        Session session = new Session();
        String roleArray = session.getRole(idCurrentUser);
        String role = roleArray.substring(2,roleArray.length()-2);
        System.err.println(role);
        
        
        if ("ROLE_ARTISTE".equals(role) ){
        Parent root = FXMLLoader.load(getClass().getResource("CCC.fxml"));
        EventTable.getScene().getWindow().hide();
        EventTable.getScene().getWindow();
        
        Stage mainStage = new Stage();
        Scene scene = new Scene(root);
        mainStage.setScene(scene);
        mainStage.show();

    }
        else if ("ROLE_PRESTATAIRE".equals(role)){
             Parent root = FXMLLoader.load(getClass().getResource("Prestataire.fxml"));
        EventTable.getScene().getWindow().hide();
        EventTable.getScene().getWindow();
        
        Stage mainStage = new Stage();
        Scene scene = new Scene(root);
        mainStage.setScene(scene);
        mainStage.show();
        }
        else if ("ROLE_SUPER_ADMIN".equals(role)){
        Parent root = FXMLLoader.load(getClass().getResource("AdminDashboard.fxml"));
        EventTable.getScene().getWindow().hide();
        EventTable.getScene().getWindow();
        
        Stage mainStage = new Stage();
        Scene scene = new Scene(root);
        mainStage.setScene(scene);
        mainStage.show();
        }
        else if ("ROLE_USER".equals(role) ){
              Parent root = FXMLLoader.load(getClass().getResource("UserDashboard.fxml"));
        EventTable.getScene().getWindow().hide();
        EventTable.getScene().getWindow();
        
        Stage mainStage = new Stage();
        Scene scene = new Scene(root);
        mainStage.setScene(scene);
        mainStage.show();
        }
    }

    @FXML
    private void out1236(MouseEvent event) {
    }

    @FXML
    private void signout(ActionEvent event) throws IOException {
           Parent root = FXMLLoader.load(getClass().getResource("test.fxml"));
        EventTable.getScene().getWindow().hide();
        EventTable.getScene().getWindow();
        
        Stage mainStage = new Stage();
        Scene scene = new Scene(root);
        mainStage.setScene(scene);
        mainStage.show();
    }
    
}
