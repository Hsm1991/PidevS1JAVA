/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;

import services.HistoriqueVenteCRUD;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import entities.HistoriqueVente;
import entities.Session;
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
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import services.ProduitCRUD;

/**
 * FXML Controller class
 *
 * @author User
 */
public class OeuvVenController implements Initializable {
HistoriqueVente hv= new HistoriqueVente();
    private Button gestion_prod;
    @FXML
    private AnchorPane mainCenter_form_artiste_m2;
    private DatePicker date2;
        private TextField quantit_vendu_2;
    private TextField quantit2;
    private TextField pprixVente2;
    private Label erreue_quantite;
    private Label erreur_prixvente;
    private Label erreur77;
    private TextField nom2;
    @FXML
    private TableView<HistoriqueVente> table_vent2;
    @FXML
    private TableColumn<HistoriqueVente, Integer> idVent2;
    @FXML
    private TableColumn<HistoriqueVente, String> DateVent2;
    @FXML
    private TableColumn<HistoriqueVente, String> QteVendue2;
    @FXML
    private TableColumn<HistoriqueVente, String> prixVente2;
    @FXML
    private TextField recherche2;
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

    
        
         HistoriqueVenteCRUD pvd = new HistoriqueVenteCRUD();
        ObservableList HV =  pvd.afficherHistVentes();
        idVent2.setCellValueFactory(new PropertyValueFactory<HistoriqueVente, Integer>("IdVent"));
        DateVent2.setCellValueFactory(new PropertyValueFactory<HistoriqueVente, String>("DateVent"));
	QteVendue2.setCellValueFactory(new PropertyValueFactory<HistoriqueVente, String>("QteVendue"));	
	prixVente2.setCellValueFactory(new PropertyValueFactory<HistoriqueVente, String>("PrixVente"));
      

       table_vent2.setItems(HV);
      
       //////////////////////Recherche//////////////////////
       
        FilteredList<HistoriqueVente> filteredData = new FilteredList<>(HV, b -> true);
         
   
         recherche2.textProperty().addListener((observable, oldValue, newValue) -> {
         filteredData.setPredicate(hvod -> {
       
         
         if (newValue == null || newValue.isEmpty()) {
         return true;
         }
         
         // Compare first name and last name of every person with filter text.
         String lowerCaseFilter = newValue.toLowerCase();
         
         
        
         if (String.valueOf(hvod.getQteVendue()).indexOf(lowerCaseFilter)!=-1) {
         return true;}
    
        else if (String.valueOf(hvod.getIdVent()).indexOf(lowerCaseFilter)!=-1){
         return true;}
       
         else return String.valueOf(hvod.getPrixVente()).contains(lowerCaseFilter);
         
         
         });
         });
         SortedList<HistoriqueVente> sortedData = new SortedList<>(filteredData);
    
         sortedData.comparatorProperty().bind(table_vent2.comparatorProperty());
         table_vent2.setItems(sortedData);
                
         
         
     
     //Statistique();
         
         
         
         
         
         
         
         
         
         
         
         
         
         
 
         
        }

       
   

    @FXML
    private void dash2_btn(ActionEvent event) throws IOException, SQLException {
          int idCurrentUser = Integer.parseInt(Session.id);
        Session session = new Session();
        String roleArray = session.getRole(idCurrentUser);
        String role = roleArray.substring(2,roleArray.length()-2);
        System.err.println(role);
        
        
        if ("ROLE_ARTISTE".equals(role) ){
        Parent root = FXMLLoader.load(getClass().getResource("CCC.fxml"));
        table_vent2.getScene().getWindow().hide();
        table_vent2.getScene().getWindow();
        
        Stage mainStage = new Stage();
        Scene scene = new Scene(root);
        mainStage.setScene(scene);
        mainStage.show();
    }
        else if ("ROLE_PRESTATAIRE".equals(role)){
             Parent root = FXMLLoader.load(getClass().getResource("Prestataire.fxml"));
        table_vent2.getScene().getWindow().hide();
        table_vent2.getScene().getWindow();
        
        Stage mainStage = new Stage();
        Scene scene = new Scene(root);
        mainStage.setScene(scene);
        mainStage.show();
        }
        else if ("ROLE_SUPER_ADMIN".equals(role)){
        Parent root = FXMLLoader.load(getClass().getResource("AdminDashboard.fxml"));
        table_vent2.getScene().getWindow().hide();
        table_vent2.getScene().getWindow();
        
        Stage mainStage = new Stage();
        Scene scene = new Scene(root);
        mainStage.setScene(scene);
        mainStage.show();
        }
        else if ("ROLE_USER".equals(role) ){
              Parent root = FXMLLoader.load(getClass().getResource("UserDashboard.fxml"));
        table_vent2.getScene().getWindow().hide();
        table_vent2.getScene().getWindow();
        
        Stage mainStage = new Stage();
        Scene scene = new Scene(root);
        mainStage.setScene(scene);
        mainStage.show();
        }
    

        
    }

    @FXML
    private void gestion_prod_btn(ActionEvent event) throws IOException {
        table_vent2.getScene().getWindow().hide();
         Parent root = FXMLLoader.load(getClass().getResource("Artiste.fxml"));
         Stage mainStage = new Stage();
         Scene scene = new Scene(root);
         mainStage.setScene(scene);
         mainStage.show();}


    @FXML
    private void reclam_btn(ActionEvent event) throws IOException {
            table_vent2.getScene().getWindow().hide();
        table_vent2.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("ReclamationFront.fxml"));
        Stage mainStage = new Stage();
        Scene scene = new Scene(root);
        mainStage.setScene(scene);
        mainStage.show();
    }


    
    
    ////////////////////////ajouter
    private void ajouter_btn2(ActionEvent event) {
      erreur_prixvente.setVisible(false);
        erreue_quantite.setVisible(false); 
        erreur77.setVisible(false); 
        HistoriqueVente hv = new HistoriqueVente();
        
                
                
            erreur_prixvente.setVisible(false);
        if(pprixVente2.getText().equals("")){
           erreur_prixvente.setVisible(true);
        }
               erreue_quantite.setVisible(false);
        if(quantit2.getText().equals("")){
           erreue_quantite.setVisible(true);
        }
            
        else{
  HistoriqueVente h = new HistoriqueVente(date2.getValue().toString(),Float.valueOf(quantit2.getText()),Float.valueOf(pprixVente2.getText()),Integer.valueOf(nom2.getText()));

 
     HistoriqueVenteCRUD pcd = new HistoriqueVenteCRUD();
      pcd.ajouterHistVen(h);
  
    }}
    ////////////////////////modifier/////////////////

    private void modifier2_btn(ActionEvent event) {
           
HistoriqueVente h= new HistoriqueVente(hv.getIdVent(),date2.getValue().toString(),Float.valueOf(quantit2.getText()),Float.valueOf(pprixVente2.getText()),Integer.valueOf(nom2.getText()));
       HistoriqueVenteCRUD ppd = new HistoriqueVenteCRUD();
        ppd.updateHistVentes(h, h.getIdVent());
         ObservableList TE =  ppd.afficherHistVentes();
         ppd.afficherHistVentes();
     idVent2.setCellValueFactory(new PropertyValueFactory<HistoriqueVente, Integer>("IdVent"));
        DateVent2.setCellValueFactory(new PropertyValueFactory<HistoriqueVente, String>("DateVent"));
	QteVendue2.setCellValueFactory(new PropertyValueFactory<HistoriqueVente, String>("QteVendue"));	
	prixVente2.setCellValueFactory(new PropertyValueFactory<HistoriqueVente, String>("PrixVente"));
         TE =  ppd.afficherHistVentes();
          table_vent2.setItems(TE);
      
    }
    private void selectionne2_btn(ActionEvent event) {
         hv = table_vent2.getSelectionModel().getSelectedItem();
                quantit2.setText(String.valueOf(hv.getQteVendue()));
                pprixVente2.setText(String.valueOf(hv.getPrixVente()));
               // date2.setChronology(String.valueOf(hv.getDateVent()));
              
    }

    private void supprimer2_btn(ActionEvent event) throws IOException {
         Interfaces.h1 = table_vent2.getSelectionModel().getSelectedItem();
        //supprimer2.getScene().getWindow().hide();
      //ajouter2.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("Suppression2.fxml"));
        Stage mainStage = new Stage();
        Scene scene = new Scene(root);
        mainStage.setScene(scene);
        mainStage.show();
        
        
        
  
    }

  

    private void vider_btn2(ActionEvent event) {
        quantit2.setText("");
                pprixVente2.setText( "");
                date2.setChronology(null);
    }


    private void miseajour_btn(ActionEvent event) {
        
        HistoriqueVenteCRUD pp = new HistoriqueVenteCRUD();
        pp.remise();
    }

    @FXML
    private void signout550(ActionEvent event) throws IOException {
            table_vent2.getScene().getWindow().hide();
        table_vent2.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("test.fxml"));
        Stage mainStage = new Stage();
        Scene scene = new Scene(root);
        mainStage.setScene(scene);
        mainStage.show();
    }

    
}
    