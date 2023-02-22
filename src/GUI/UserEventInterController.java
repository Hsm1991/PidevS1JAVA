/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import entities.Evenement;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
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
import javafx.stage.Stage;
import javax.mail.MessagingException;
import services.CRUDTicket;
import services.EvenementCRUD;
import services.GetData212;

/**
 * FXML Controller class
 *
 * @author User
 */
public class UserEventInterController implements Initializable {
      Evenement EventData = new Evenement();
    @FXML
    private TableView<Evenement> userevent;
    @FXML
    private TableColumn<Evenement, String> EventName;
    @FXML
    private TableColumn<Evenement, String> EventAdddress;
    @FXML
    private TableColumn<Evenement, String> EventCapacity;
    @FXML
    private TableColumn<Evenement, String> NbrTicketEV;
    @FXML
    private TableColumn<Evenement, String> StartE;
    @FXML
    private TableColumn<Evenement, String> EndE;
    @FXML
    private TableColumn<Evenement, String> eventtype;
    @FXML
    private TableColumn<Evenement, String> eventcategory;
    @FXML
    private TableColumn<Evenement, String> prixE;
    @FXML
    private TextField UserRech;
    @FXML
    private Button GOREC2;
    @FXML
    private Button Décccc55;
    @FXML
    private ImageView EventsDispo5;
    @FXML
    private Label disponibl5;
    @FXML
    private Button ADDTIK;
    @FXML
    private Button BACKUSR;
    @FXML
    private Button panADD;
    private Button MINIMISER5;
    private Image image2;
    @FXML
    private TextField nbrTIK11;
    @FXML
    private Label errnbrrrt;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        SelectionEvent1();
                  errnbrrrt.setVisible(false);

        ///////////////////////////Remplissage table view////////////////////////
        EvenementCRUD ecd = new EvenementCRUD();
        ObservableList EV =  ecd.afficherEvenemets();
	EventName.setCellValueFactory(new PropertyValueFactory<Evenement, String>("NomEvent"));
	EventAdddress.setCellValueFactory(new PropertyValueFactory<Evenement, String>("AdresseEvent"));
	EventCapacity.setCellValueFactory(new PropertyValueFactory<Evenement, String>("CapaciteEvent"));
        NbrTicketEV.setCellValueFactory(new PropertyValueFactory<Evenement, String>("nbrTicketAchete"));
        StartE.setCellValueFactory(new PropertyValueFactory<Evenement,String>("DateDebutEvent"));
        EndE.setCellValueFactory(new PropertyValueFactory<Evenement,String>("DateFinEvent"));
        eventtype.setCellValueFactory(new PropertyValueFactory<Evenement,String>("TypeEvent"));
        eventcategory.setCellValueFactory(new PropertyValueFactory<Evenement,String>("CategorieEvent"));
        prixE.setCellValueFactory(new PropertyValueFactory<Evenement,String>("PrixEntre"));
        EventsDispo5.setImage(image2);
          
                
                
        userevent.setItems(EV);
        
        
        
        ////////////////FONCTION RECHERCHER///////////
        
          FilteredList<Evenement> filteredData = new FilteredList<>(EV, b -> true);
		
		//2. Set the filter Predicate whenever the filter changes.
		UserRech.textProperty().addListener((observable, oldValue, newValue) -> {
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
                               // else if (Even.getDateDebutEvent().toLowerCase().contains(lowerCaseFilter)) {
					//return true;}
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
		sortedData.comparatorProperty().bind(userevent.comparatorProperty());
		
		// 5. Add sorted (and filtered) data to the table.
		userevent.setItems(sortedData);
    }

    @FXML
    private void GORLMT(ActionEvent event) {
    }

    @FXML
    private void out1236(ActionEvent event) {
    }

  

    private void MIN5(ActionEvent event) {
             Stage stage = (Stage) MINIMISER5.getScene().getWindow();
        stage.setIconified(true);
    }
    
        @FXML
    public void SelectionEvent1(){
       
    Evenement EventData = userevent.getSelectionModel().getSelectedItem();
    int num = userevent.getSelectionModel().getFocusedIndex();
        if((num-1) <-1)
        return;
        disponibl5.setText(EventData.getNomEvent());
        String uri = "file:" + EventData.getImage1();
        
        image2 = new Image (uri, 432,294, false, true) ;
        EventsDispo5.setImage(image2);
        

}

    @FXML
    private void reservertiq(ActionEvent event) throws MessagingException, SQLException {
             errnbrrrt.setVisible(false);
         EventData = userevent.getSelectionModel().getSelectedItem();
        CRUDTicket ccr = new CRUDTicket();
       
        if (nbrTIK11.getText().isEmpty())
        {
             if (EventData.getCapaciteEvent() - EventData.getNbrTicketAchete() < 1)
       {
          errnbrrrt.setVisible(true);
       } else
         ccr.ajouterTiquetsForEvent(EventData.getIdEvent(),1, "1 Ere choix");
        }
        else
        {
            
             if (EventData.getCapaciteEvent() - EventData.getNbrTicketAchete() < 1)
       {
          errnbrrrt.setVisible(true);
       } else
        ccr.ajouterTiquetsForEvent(EventData.getIdEvent(),Integer.parseInt( nbrTIK11.getText()), "1 Ere choix");
        }
        
        
          EvenementCRUD ecd = new EvenementCRUD();
        ObservableList EV =  ecd.afficherEvenemets();
	EventName.setCellValueFactory(new PropertyValueFactory<Evenement, String>("NomEvent"));
	EventAdddress.setCellValueFactory(new PropertyValueFactory<Evenement, String>("AdresseEvent"));
	EventCapacity.setCellValueFactory(new PropertyValueFactory<Evenement, String>("CapaciteEvent"));
        NbrTicketEV.setCellValueFactory(new PropertyValueFactory<Evenement, String>("nbrTicketAchete"));
        StartE.setCellValueFactory(new PropertyValueFactory<Evenement,String>("DateDebutEvent"));
        EndE.setCellValueFactory(new PropertyValueFactory<Evenement,String>("DateFinEvent"));
        eventtype.setCellValueFactory(new PropertyValueFactory<Evenement,String>("TypeEvent"));
        eventcategory.setCellValueFactory(new PropertyValueFactory<Evenement,String>("CategorieEvent"));
        prixE.setCellValueFactory(new PropertyValueFactory<Evenement,String>("PrixEntre"));
        EventsDispo5.setImage(image2);
          
                
                
        userevent.setItems(EV);
        
        
        
        ////////////////MISE A JOUR DE TABLE VIEW///////////
        
          FilteredList<Evenement> filteredData = new FilteredList<>(EV, b -> true);
		
		//2. Set the filter Predicate whenever the filter changes.
		UserRech.textProperty().addListener((observable, oldValue, newValue) -> {
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
//                                else if (Even.getDateDebutEvent().toLowerCase().contains(lowerCaseFilter)) {
					//return true;}
                                else if (String.valueOf(Even.getIdEvent()).indexOf(lowerCaseFilter)!=-1){
				     return true;}
                                else if (String.valueOf(Even.getCapaciteEvent()).indexOf(lowerCaseFilter)!=-1){
				     return true;}
				else return String.valueOf(Even.getCategorieEvent()).contains(lowerCaseFilter);

                                
	});
		});
                SortedList<Evenement> sortedData = new SortedList<>(filteredData);
		sortedData.comparatorProperty().bind(userevent.comparatorProperty());
		userevent.setItems(sortedData);
    }

    @FXML
    private void BACKUSR_btn(ActionEvent event) throws IOException {
          BACKUSR.getScene().getWindow().hide();
        BACKUSR.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("CCC.fxml"));
        Stage mainStage = new Stage();
        Scene scene = new Scene(root);
        mainStage.setScene(scene);
        mainStage.show();
    }
}
      
