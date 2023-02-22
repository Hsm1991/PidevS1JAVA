/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import entities.Evenement;
import entities.Session;
import entities.Ticket;
import java.io.IOException;
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
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;
import services.CRUDTicket;
import services.EvenementCRUD;

/**
 * FXML Controller class
 *
 * @author User
 */
public class TicketsEvController implements Initializable {
    
    Ticket HHH = new Ticket();

    @FXML
    private TextField TikRech;
    @FXML
    private Button TikAdd;
    @FXML
    private Button TikDel;
    @FXML
    private Button TikUpd;
    @FXML
    private TextField EvNo;
    @FXML
    private TextField PrixTik;
    @FXML
    private ComboBox<String> TypeTik;
    @FXML
    private TableColumn<Ticket, String> IdTicket;
    @FXML
    private TableColumn<Ticket, String> PrixTicket;
    @FXML
    private TableColumn<Ticket, String> NomEvent;
    @FXML
    private TableColumn<Ticket, String> TypeTicket;
    @FXML
    private TableView<Ticket> TikTable;
    private Button BackEVEN;
    @FXML
    private Label errevvnom;
    @FXML
    private Label errtikpr;
    @FXML
    private Button TikSel;
    private Button MINIMISER1;
    @FXML
    private Button CLR222;
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
        //////////////////  CONTROLE DE SAISIE//////////////
        errevvnom.setVisible(false);
        errtikpr.setVisible(false);
        //////////////////  REMPLISSAGE COMBOBOX/////////////////
         ObservableList<String> list = FXCollections.observableArrayList("1ére Classe","2ème Classe","3ème Classe");
        TypeTik.setItems(list);
        /////////////////////AFFICHAGE AU TREE VIEW////////////////////////
        CRUDTicket ecd = new CRUDTicket();
        ObservableList TE =  ecd.afficherTickets();
        IdTicket.setCellValueFactory(new PropertyValueFactory<Ticket, String>("IdTicket"));
        NomEvent.setCellValueFactory(new PropertyValueFactory<Ticket, String>("NomEvent"));
	PrixTicket.setCellValueFactory(new PropertyValueFactory<Ticket, String>("PrixTicket"));	
	TypeTicket.setCellValueFactory(new PropertyValueFactory<Ticket, String>("TypeTicket"));
         TikTable.setItems(TE);
         
         /////////////////////RECHERCHE////////////////////////////////
         
         FilteredList<Ticket> filteredData = new FilteredList<>(TE, b -> true);
		
		//2. Set the filter Predicate whenever the filter changes.
		TikRech.textProperty().addListener((observable, oldValue, newValue) -> {
			filteredData.setPredicate(TIK -> {
				// If filter text is empty, display all persons.
								
				if (newValue == null || newValue.isEmpty()) {
					return true;
				}
				
				// Compare first name and last name of every person with filter text.
				String lowerCaseFilter = newValue.toLowerCase();
				
				
				 if (TIK.getNomEvent().toLowerCase().contains(lowerCaseFilter)) {
					return true; // Filter matches last name.
				}
                                 else if (String.valueOf(TIK.getIdTicket()).indexOf(lowerCaseFilter)!=-1){
				     return true;}
                                 else if (String.valueOf(TIK.getPrixTicket()).indexOf(lowerCaseFilter)!=-1){
				     return true;}
                                 else if (TIK.getTypeTicket().toLowerCase().contains(lowerCaseFilter)) {
					return true; // Filter matches last name.
				}
                            return false;
                          
                                
	});
		});
                SortedList<Ticket> sortedData = new SortedList<>(filteredData);
		
		// 4. Bind the SortedList comparator to the TableView comparator.
		// 	  Otherwise, sorting the TableView would have no effect.
		sortedData.comparatorProperty().bind(TikTable.comparatorProperty());
		
		// 5. Add sorted (and filtered) data to the table.
		TikTable.setItems(sortedData);
        
        // TODO
    }    

    @FXML
    private void BACKEVENT(ActionEvent event) throws IOException {
        TikTable.getScene().getWindow().hide();
         Parent root = FXMLLoader.load(getClass().getResource("main.fxml"));
         Stage mainStage = new Stage();
         Scene scene = new Scene(root);
         mainStage.setScene(scene);
         mainStage.show();} 

    @FXML
    private void AjouTT(ActionEvent event) throws IOException {
          TikTable.getScene().getWindow().hide();
         Parent root = FXMLLoader.load(getClass().getResource("AjoutTicket.fxml"));
         Stage mainStage = new Stage();
         Scene scene = new Scene(root);
         mainStage.setScene(scene);
         mainStage.show();} 

    @FXML
    private void DelTikt(ActionEvent event) throws IOException {
        
     
        Interfaces.ev1 = TikTable.getSelectionModel().getSelectedItem();
       // Décccc1.getScene().getWindow().hide();
        //TikDel.getScene().getWindow();
        /*Parent root = FXMLLoader.load(getClass().getResource("SUP1.fxml"));
        Stage mainStage = new Stage();
        Scene scene = new Scene(root);
        mainStage.setScene(scene);
        mainStage.show();} */
            CRUDTicket ecd = new CRUDTicket();
        ObservableList EV =  ecd.afficherTickets();
        ecd.SupprimerTicket(Interfaces.ev1.getIdTicket());
              
        ObservableList TE =  ecd.afficherTickets();
        IdTicket.setCellValueFactory(new PropertyValueFactory<Ticket, String>("IdTicket"));
        NomEvent.setCellValueFactory(new PropertyValueFactory<Ticket, String>("NomEvent"));
	PrixTicket.setCellValueFactory(new PropertyValueFactory<Ticket, String>("PrixTicket"));	
	TypeTicket.setCellValueFactory(new PropertyValueFactory<Ticket, String>("TypeTicket"));
         TikTable.setItems(TE);
         
        
        
         
        
        
        
          Notifications notificationBuilder = Notifications.create()
                    .title("Effectué")
                    .text("Ticket Supprimée Correctement")
                     .graphic(null)
                     .hideAfter(Duration.seconds(10))
                     .position(Pos.BASELINE_RIGHT);

             notificationBuilder.showConfirm();}
             
             
             
             
             
             
             
             
             
             
    

    @FXML
    private void selectTIIK(ActionEvent event) {
         HHH = TikTable.getSelectionModel().getSelectedItem();
                EvNo.setText(HHH.getNomEvent());
                PrixTik.setText(String.valueOf(HHH.getPrixTicket()));
              
    }

    @FXML
    private void TIKUPD(ActionEvent event) {
        
            if(EvNo.getText().equals("")){
            errtikpr.setVisible(true);
        }
         if(PrixTik.getText().equals("")){
           TikSel.setVisible(true);
        }
         else{
        Ticket T = new Ticket (HHH.getIdTicket(),Float.valueOf(PrixTik.getText()),EvNo.getText(),TypeTik.getValue());
        CRUDTicket crd = new CRUDTicket();
        crd.ModifTique(T, T.getIdTicket());
         ObservableList TV =  crd.afficherTickets();
         crd.afficherTickets();
          IdTicket.setCellValueFactory(new PropertyValueFactory<Ticket, String>("IdTicket"));
        PrixTicket.setCellValueFactory(new PropertyValueFactory<Ticket, String>("PrixTicket"));
        NomEvent.setCellValueFactory(new PropertyValueFactory<Ticket, String>("NomEvent"));
        TypeTicket.setCellValueFactory(new PropertyValueFactory<Ticket, String>("TypeTicket"));  
        TV = crd.afficherTickets();
         TikTable.setItems(TV); 
         
         
          
         Notifications notificationBuilder;
     notificationBuilder = Notifications.create()
             .title("Effectué")
             .text("Ticket Modifiée Correctement")
             .graphic(null)
             .hideAfter(Duration.seconds(10))
             .position(Pos.BASELINE_RIGHT);

             notificationBuilder.showConfirm();
        
    }
    }


    private void FER1(ActionEvent event) {
          System.exit(0);
    }

    private void MIN12121(ActionEvent event) {
             Stage stage = (Stage) MINIMISER1.getScene().getWindow();
        stage.setIconified(true);
    }

    @FXML
    private void GORLMT1(ActionEvent event) throws IOException {
             TikTable.getScene().getWindow().hide();
        TikTable.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("ReclamationFront.fxml"));
        Stage mainStage = new Stage();
        Scene scene = new Scene(root);
        mainStage.setScene(scene);
        mainStage.show();
    }


    @FXML
    private void CLR22(ActionEvent event) {
         EvNo.setText("");
        PrixTik.setText("");
        TypeTik.setValue("Type Ticket");
    }

    private void DDDD(ActionEvent event) throws IOException {
        TikTable.getScene().getWindow().hide();
        TikTable.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("CCC.fxml"));
        Stage mainStage = new Stage();
        Scene scene = new Scene(root);
        mainStage.setScene(scene);
        mainStage.show();
    }

    @FXML
    private void godash121(ActionEvent event) throws SQLException, IOException {
         int idCurrentUser = Integer.parseInt(Session.id);
        Session session = new Session();
        String roleArray = session.getRole(idCurrentUser);
        String role = roleArray.substring(2,roleArray.length()-2);
        System.err.println(role);
        
        
        if ("ROLE_ARTISTE".equals(role) ){
        Parent root = FXMLLoader.load(getClass().getResource("CCC.fxml"));
        TikTable.getScene().getWindow().hide();
        TikTable.getScene().getWindow();
        
        Stage mainStage = new Stage();
        Scene scene = new Scene(root);
        mainStage.setScene(scene);
        mainStage.show();

    }
        else if ("ROLE_PRESTATAIRE".equals(role)){
             Parent root = FXMLLoader.load(getClass().getResource("Prestataire.fxml"));
        TikTable.getScene().getWindow().hide();
        TikTable.getScene().getWindow();
        
        Stage mainStage = new Stage();
        Scene scene = new Scene(root);
        mainStage.setScene(scene);
        mainStage.show();
        }
        else if ("ROLE_SUPER_ADMIN".equals(role)){
        Parent root = FXMLLoader.load(getClass().getResource("AdminDashboard.fxml"));
        TikTable.getScene().getWindow().hide();
        TikTable.getScene().getWindow();
        
        Stage mainStage = new Stage();
        Scene scene = new Scene(root);
        mainStage.setScene(scene);
        mainStage.show();
        }
        else if ("ROLE_USER".equals(role) ){
              Parent root = FXMLLoader.load(getClass().getResource("UserDashboard.fxml"));
        TikTable.getScene().getWindow().hide();
        TikTable.getScene().getWindow();
        
        Stage mainStage = new Stage();
        Scene scene = new Scene(root);
        mainStage.setScene(scene);
        mainStage.show();}
    }

    @FXML
    private void signout2233(ActionEvent event) throws IOException {
         Parent root = FXMLLoader.load(getClass().getResource("test.fxml"));
        TikTable.getScene().getWindow().hide();
        TikTable.getScene().getWindow();
        
        Stage mainStage = new Stage();
        Scene scene = new Scene(root);
        mainStage.setScene(scene);
        mainStage.show();
    }
}
    

