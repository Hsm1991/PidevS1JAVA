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
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.AreaChart;
import javafx.scene.control.Button;
import javafx.scene.control.SortEvent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javax.mail.MessagingException;
import services.CommandPMService;
import services.ProduitPMService;

/**
 * FXML Controller class
 *
 * @author elbootic.com
 */
public class CommandeController implements Initializable {
CommandPMService cs = new CommandPMService();
    ProduitPMService ps = new ProduitPMService();
    @FXML
    private TableView<commandPM> CMtable;
    @FXML
    private TableColumn<commandPM, String> CMnomtab;
    @FXML
    private TableColumn<commandPM, Integer> CMreferncetab;
    @FXML
    private TableColumn<commandPM, Integer> CMquantitetab;
    @FXML
    private TableColumn<commandPM, Date> CMdateAjouttab;
    @FXML
    private TextField CMchercher;
    @FXML
    private Button rtn5;
    @FXML
    private TableColumn<commandPM, String> CMtype;
    @FXML
    private Button zhuhcz;
    @FXML
    private Button aaa;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    try {
        ps.alertstock();
    } catch (SQLException ex) {
        Logger.getLogger(CommandeController.class.getName()).log(Level.SEVERE, null, ex);
    } catch (MessagingException ex) {
        Logger.getLogger(CommandeController.class.getName()).log(Level.SEVERE, null, ex);
    }
            ObservableList PM = cs.affichercommande();
        CMnomtab.setCellValueFactory(new PropertyValueFactory<commandPM, String>("NomProd"));
	CMreferncetab.setCellValueFactory(new PropertyValueFactory<commandPM, Integer>("referenceCM"));
	CMquantitetab.setCellValueFactory(new PropertyValueFactory<commandPM, Integer>("quantiteCpm"));
	CMdateAjouttab.setCellValueFactory(new PropertyValueFactory<commandPM, Date>("date"));
               CMtype.setCellValueFactory(new PropertyValueFactory<commandPM, String>("type"));

	CMtable.setItems(PM);
        try {
            
        
        FilteredList<commandPM> filteredData = new FilteredList<>(PM, b -> true);
		
		// 2. Set the filter Predicate whenever the filter changes.
		CMchercher.textProperty().addListener((observable, oldValue, newValue) -> {
			filteredData.setPredicate(c -> {
				// If filter text is empty, display all persons.
								
				if (newValue == null || newValue.isEmpty()) {
					return true;
				}
				
				// Compare first name and last name of every person with filter text.
				String lowerCaseFilter = newValue.toLowerCase();
				
				if (c.getNomProd().toLowerCase().contains(lowerCaseFilter) ) {
					return true; // Filter matches first name.
			
				}
                               
                               
                              
				    
				     
				else return String.valueOf(c.getNomProd()).contains(lowerCaseFilter); // Does not match.
                                
	});
		});
                SortedList<commandPM> sortedData = new SortedList<>(filteredData);
		
		// 4. Bind the SortedList comparator to the TableView comparator.
		// 	  Otherwise, sorting the TableView would have no effect.
		sortedData.comparatorProperty().bind(CMtable.comparatorProperty());
		
		// 5. Add sorted (and filtered) data to the table.
		CMtable.setItems(filteredData);
        }
         catch (Exception e) {
                 System.out.println(e.getMessage());
                 }
       
        
     
        
        
    }    

   
    

    @FXML
    private void retourbtn5(ActionEvent event) throws IOException {
         CMchercher.getScene().getWindow().hide();
        Parent root = FXMLLoader.load(getClass().getResource("produitPMdetails.fxml"));
                Stage mainStage = new Stage();
                Scene scene = new Scene(root);
                mainStage.setScene(scene);
                mainStage.show();
        
    }

    @FXML
    private void rtn5(MouseEvent event) {
    }

    @FXML
    private void dash00(ActionEvent event) throws IOException, SQLException {
        
            int idCurrentUser = Integer.parseInt(Session.id);
        Session session = new Session();
        String roleArray = session.getRole(idCurrentUser);
        String role = roleArray.substring(2,roleArray.length()-2);
        System.err.println(role);
        
        
        if ("ROLE_ARTISTE".equals(role) ){
        Parent root = FXMLLoader.load(getClass().getResource("CCC.fxml"));
        CMtable.getScene().getWindow().hide();
        CMtable.getScene().getWindow();
        
        Stage mainStage = new Stage();
        Scene scene = new Scene(root);
        mainStage.setScene(scene);
        mainStage.show();

    }
        else if ("ROLE_PRESTATAIRE".equals(role)){
             Parent root = FXMLLoader.load(getClass().getResource("Prestataire.fxml"));
        CMtable.getScene().getWindow().hide();
        CMtable.getScene().getWindow();
        
        Stage mainStage = new Stage();
        Scene scene = new Scene(root);
        mainStage.setScene(scene);
        mainStage.show();
        }
        else if ("ROLE_SUPER_ADMIN".equals(role)){
        Parent root = FXMLLoader.load(getClass().getResource("AdminDashboard.fxml"));
        CMtable.getScene().getWindow().hide();
        CMtable.getScene().getWindow();
        
        Stage mainStage = new Stage();
        Scene scene = new Scene(root);
        mainStage.setScene(scene);
        mainStage.show();
        }
        else if ("ROLE_USER".equals(role) ){
              Parent root = FXMLLoader.load(getClass().getResource("UserDashboard.fxml"));
        CMtable.getScene().getWindow().hide();
        CMtable.getScene().getWindow();
        
        Stage mainStage = new Stage();
        Scene scene = new Scene(root);
        mainStage.setScene(scene);
        mainStage.show();
        }
    
    
    }

    @FXML
    private void reclamer(ActionEvent event) throws IOException {
              CMtable.getScene().getWindow().hide();
        CMtable.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("ReclamationFront.fxml"));
        Stage mainStage = new Stage();
        Scene scene = new Scene(root);
        mainStage.setScene(scene);
        mainStage.show();
    }



 
}
