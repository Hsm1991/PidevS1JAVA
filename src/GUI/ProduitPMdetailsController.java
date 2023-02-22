/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import com.mysql.jdbc.MySQLConnection;
import utils.MyConnection;
import entities.ProduitPM;
import entities.Session;
import java.io.IOException;
import static java.lang.Math.E;
import java.net.URL;
import java.sql.SQLException;
import static java.util.Collections.list;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
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
import javafx.scene.control.SortEvent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import static javafx.scene.input.KeyCode.C;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;
import services.ProduitPMService;


/**
 * FXML Controller class
 *
 * @author elbootic.com
 */
public class ProduitPMdetailsController implements Initializable {

    @FXML
    private  TableColumn<ProduitPM, String> PMnomtab;
    @FXML
    private TableColumn<ProduitPM, String> PMreferncetab;
    @FXML
    private TableColumn<ProduitPM, String> PMquantitetab;
    @FXML
    private TableColumn<ProduitPM, String> PMtypetab;
    @FXML
    private TableColumn<ProduitPM, String> PMdateAjouttab;
    @FXML
    private TableColumn<ProduitPM, String> PMprixtab;
    //@FXML
  //  private TextField PMcherchertab;
    @FXML
    private TableView<ProduitPM> PMtable;
   // @FXML
   // private Button PMajoutertab;

    /**
     * Initializes the controller class.
     */
      
     
      ProduitPMService es = new ProduitPMService();
//    @FXML
//    private Button PMmodifier;
//    @FXML
//    private Button suppPM;
//    @FXML
//    private Button PMcommander;
//    @FXML
//    private Label errSelectlabel;
    @FXML
    private Button PMajoutertab;
    @FXML
    private Button PMmodifier;
    @FXML
    private Button suppPM;
    private Label errSelectlabel;
    @FXML
    private Button PMcommander;
    @FXML
    private TextField PMcherchertab;
    @FXML
    private Button dashhhhh;
    @FXML
    private Button ao_btn_signout;
    @FXML
    private Label labelsignout;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
//        errSelectlabel.setVisible(false);
        PMtable.setEditable(true);
        ObservableList PM = es.afficherProduit();
        System.err.println(PM);
        PMnomtab.setCellValueFactory(new PropertyValueFactory<ProduitPM, String>("NomProd"));
	PMreferncetab.setCellValueFactory(new PropertyValueFactory<ProduitPM, String>("referenceP"));
	PMquantitetab.setCellValueFactory(new PropertyValueFactory<ProduitPM, String>("quantiteP"));
	PMtypetab.setCellValueFactory(new PropertyValueFactory<ProduitPM, String>("typep"));
        PMdateAjouttab.setCellValueFactory(new PropertyValueFactory<ProduitPM, String>("dateAjoutPM"));
        PMprixtab.setCellValueFactory(new PropertyValueFactory<ProduitPM, String>("prixPM"));
	PMtable.setItems(PM);
        try {
            
        
        FilteredList<ProduitPM> filteredData = new FilteredList<>(PM, b -> true);
		
		// 2. Set the filter Predicate whenever the filter changes.
		PMcherchertab.textProperty().addListener((observable, oldValue, newValue) -> {
			filteredData.setPredicate(produit -> {
				// If filter text is empty, display all persons.
								
				if (newValue == null || newValue.isEmpty()) {
					return true;
				}
				
				// Compare first name and last name of every person with filter text.
				String lowerCaseFilter = newValue.toLowerCase();
				
				if (produit.getNomProd().toLowerCase().contains(lowerCaseFilter) ) {
					return true; // Filter matches first name.
				} else if (produit.getType().toLowerCase().contains(lowerCaseFilter)) {
					return true; // Filter matches last name.
				}
                               
                               
                              
				    
				     
				else return String.valueOf(produit.getNomProd()).contains(lowerCaseFilter); // Does not match.
                                
	});
		});
                SortedList<ProduitPM> sortedData = new SortedList<>(filteredData);
		
		// 4. Bind the SortedList comparator to the TableView comparator.
		// 	  Otherwise, sorting the TableView would have no effect.
		sortedData.comparatorProperty().bind(PMtable.comparatorProperty());
		
		// 5. Add sorted (and filtered) data to the table.
		PMtable.setItems(sortedData);
        }
         catch (Exception e) {
                 System.out.println(e.getMessage());
                 }
       
        
     
       
       
        // TODO
    }    

    @FXML
    private void ajouterfenetre(ActionEvent event) throws IOException {
        PMajoutertab.getScene().getWindow().hide();
        Parent root = FXMLLoader.load(getClass().getResource("ajoutProduit.fxml"));
                Stage mainStage = new Stage();
                Scene scene = new Scene(root);
                mainStage.setScene(scene);
                mainStage.show();
    }

   
  
        
       
        
    

    @FXML
    private void supprimerPROD(ActionEvent event) throws IOException, SQLException  {
       /* try {
             Interfaces.pm = PMtable.getSelectionModel().getSelectedItem();
             if(           Interfaces.pm != null) {
             suppPM.getScene().getWindow().hide();
      //  suppPM.getScene().getWindow();
         Parent root = FXMLLoader.load(getClass().getResource("delete.fxml"));
         Stage mainStage = new Stage();
         Scene scene = new Scene(root);
         mainStage.setScene(scene);
         mainStage.show();
        } else
                 errSelectlabel.setVisible(true);  
                 }
             
             
             catch (Exception e) {
                    errSelectlabel.setVisible(false);
        }*/
       
        Interfaces.pm = PMtable.getSelectionModel().getSelectedItem();
       
           ProduitPMService crd = new ProduitPMService();
           ObservableList EV =  crd.afficherProduit();
        crd.supprimerProduitPM(Interfaces.pm.getIDProd());
         
         
        ///idEvent.setCellValueFactory(new PropertyValueFactory<Evenement, Integer>("idEvent"));
	 ObservableList PM = es.afficherProduit();
        PMnomtab.setCellValueFactory(new PropertyValueFactory<ProduitPM, String>("NomProd"));
	PMreferncetab.setCellValueFactory(new PropertyValueFactory<ProduitPM, String>("referenceP"));
	PMquantitetab.setCellValueFactory(new PropertyValueFactory<ProduitPM, String>("quantiteP"));
	PMtypetab.setCellValueFactory(new PropertyValueFactory<ProduitPM, String>("type"));
        PMdateAjouttab.setCellValueFactory(new PropertyValueFactory<ProduitPM, String>("dateAjoutPM"));
        PMprixtab.setCellValueFactory(new PropertyValueFactory<ProduitPM, String>("prixPM"));
	PMtable.setItems(PM); 

    
        
        
        
        
        
                     Notifications notificationBuilder = Notifications.create()
                    .title("Effectué")
                    .text("Produit Supprimé Correctement")
                     .graphic(null)
                     .hideAfter(Duration.seconds(10))
                     .position(Pos.BASELINE_RIGHT);

             notificationBuilder.showConfirm();

        
        
        
        
        
        
        
        
        
//       try {
//            int xx = PMtable.getSelectionModel().getSelectedItem().getIDProd();
//           
//            
//        es.supprimerProduitPM(xx);
//        PMtable.setEditable(true);
//        ObservableList PM = es.afficherProduit();
//        PMnomtab.setCellValueFactory(new PropertyValueFactory<ProduitPM, String>("NomProd"));
//	PMreferncetab.setCellValueFactory(new PropertyValueFactory<ProduitPM, String>("referenceP"));
//	PMquantitetab.setCellValueFactory(new PropertyValueFactory<ProduitPM, String>("quantiteP"));
//	PMtypetab.setCellValueFactory(new PropertyValueFactory<ProduitPM, String>("type"));
//        PMdateAjouttab.setCellValueFactory(new PropertyValueFactory<ProduitPM, String>("dateAjoutPM"));
//        PMprixtab.setCellValueFactory(new PropertyValueFactory<ProduitPM, String>("prixPM"));
//	PMtable.setItems(PM);
//       } catch (SQLException ex) {
//            System.out.println(ex.getMessage());
//        }
       /* private void delete21(ActionEvent event) {
           int abc = TikTable.getSelectionModel().getSelectedItem().getIdTicket();
        CRUDTicket ecd = new CRUDTicket();
        ObservableList EV =  ecd.afficherTickets();
        ecd.SupprimerTicket(abc);
        TikTable.setEditable(true);
        ecd.afficherTickets();
        IdTicket.setCellValueFactory(new PropertyValueFactory<Ticket, String>("IdTicket"));
        PrixTicket.setCellValueFactory(new PropertyValueFactory<Ticket, String>("PrixTicket"));
        NomEvent.setCellValueFactory(new PropertyValueFactory<Ticket, String>("NomEvent"));
        TypeTicket.setCellValueFactory(new PropertyValueFactory<Ticket, String>("TypeTicket"));  
        EV =  ecd.afficherTickets();
        TikTable.setItems(EV);
        
        
          Notifications notificationBuilder = Notifications.create()
                    .title("Effectué")
                    .text("Ticket Supprimée Correctement")
                     .graphic(null)
                     .hideAfter(Duration.seconds(10))
                     .position(Pos.BASELINE_RIGHT);

             notificationBuilder.showConfirm();
    }
      */  


   // }

  /*  private void modifierfenetreTAB(ProduitPM produit) {
         
       
       
    }

   

    @FXML
    private void modifierfenetre(ActionEvent event) throws IOException  {
        
       
           ProduitPMService ps = new ProduitPMService();
        ProduitPM c = new ProduitPM(PMtable.getSelectionModel().getSelectedItem().getIDProd(),
                tableview.getSelectionModel().getSelectedItem().getUser_id(),
                 tableview.getSelectionModel().getSelectedItem().getDate(),
                tableview.getSelectionModel().getSelectedItem().getType(),
               tableview.getSelectionModel().getSelectedItem().getContenu(),
                tableview.getSelectionModel().getSelectedItem().getImg(),
                tableview.getSelectionModel().getSelectedItem().getEtat()
                );
        ListReclamationController.connectedRec = c;
        */
    
    
//    
//         suppPM.getScene().getWindow().hide();
//        Parent root = FXMLLoader.load(getClass().getResource("delete.fxml"));
//                Stage mainStage = new Stage();
//                Scene scene = new Scene(root);
//                mainStage.setScene(scene);
//                mainStage.show();
    }



    @FXML
    private void modifierfenetre(ActionEvent event) throws IOException {
        try {
                Interfaces p = new Interfaces();
        p.p= PMtable.getSelectionModel().getSelectedItem();
             if (p.p != null){   
          PMcherchertab.getScene().getWindow().hide();
        Parent root = FXMLLoader.load(getClass().getResource("modifierprod.fxml"));
                Stage mainStage = new Stage();
                Scene scene = new Scene(root);
                mainStage.setScene(scene);
                mainStage.show();
             }
             else
                  errSelectlabel.setVisible(true); 
        } catch (Exception e) {
//                   errSelectlabel.setVisible(true); 
        }
    
        

    }

  
    @FXML
    private void commanderPM(ActionEvent event) throws IOException {
        
                PMcherchertab.getScene().getWindow().hide();
        Parent root = FXMLLoader.load(getClass().getResource("ajoutCommand.fxml"));
                Stage mainStage = new Stage();
                Scene scene = new Scene(root);
                mainStage.setScene(scene);
                mainStage.show();
        
    }

    @FXML
    private void dashhhh(ActionEvent event) throws IOException, SQLException {
           int idCurrentUser = Integer.parseInt(Session.id);
        Session session = new Session();
        String roleArray = session.getRole(idCurrentUser);
        String role = roleArray.substring(2,roleArray.length()-2);
        System.err.println(role);
        
        
        if ("ROLE_ARTISTE".equals(role) ){
        Parent root = FXMLLoader.load(getClass().getResource("CCC.fxml"));
        PMcherchertab.getScene().getWindow().hide();
        PMcherchertab.getScene().getWindow();
        
        Stage mainStage = new Stage();
        Scene scene = new Scene(root);
        mainStage.setScene(scene);
        mainStage.show();

    }
        else if ("ROLE_PRESTATAIRE".equals(role)){
             Parent root = FXMLLoader.load(getClass().getResource("Prestataire.fxml"));
        PMcherchertab.getScene().getWindow().hide();
        PMcherchertab.getScene().getWindow();
        
        Stage mainStage = new Stage();
        Scene scene = new Scene(root);
        mainStage.setScene(scene);
        mainStage.show();
        }
        else if ("ROLE_SUPER_ADMIN".equals(role)){
        Parent root = FXMLLoader.load(getClass().getResource("AdminDashboard.fxml"));
        PMcherchertab.getScene().getWindow().hide();
        PMcherchertab.getScene().getWindow();
        
        Stage mainStage = new Stage();
        Scene scene = new Scene(root);
        mainStage.setScene(scene);
        mainStage.show();
        }
        else if ("ROLE_USER".equals(role) ){
              Parent root = FXMLLoader.load(getClass().getResource("UserDashboard.fxml"));
        PMcherchertab.getScene().getWindow().hide();
        PMcherchertab.getScene().getWindow();
        
        Stage mainStage = new Stage();
        Scene scene = new Scene(root);
        mainStage.setScene(scene);
        mainStage.show();
        }
     
     
    }

  

    @FXML
    private void signout(ActionEvent event) throws IOException {
           Parent root = FXMLLoader.load(getClass().getResource("test.fxml"));
        PMcherchertab.getScene().getWindow().hide();
        PMcherchertab.getScene().getWindow();
        
        Stage mainStage = new Stage();
        Scene scene = new Scene(root);
        mainStage.setScene(scene);
        mainStage.show();
    }


  

   
  


  
}
    


        
        
    
   

    


