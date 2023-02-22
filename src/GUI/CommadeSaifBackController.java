/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import static GUI.CommandeSaifController.idc;
import entities.Commande;
import entities.Session;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import services.CommandeCRUD;

/**
 * FXML Controller class
 *
 * @author User
 */
public class CommadeSaifBackController implements Initializable {

    @FXML
    private AnchorPane anchorPaneCommande;
    @FXML
    private ChoiceBox<Integer> cb;
    @FXML
    private Label lbWarningFacture;
    @FXML
    private TableView<Commande> tableCommandeSaif;
    @FXML
    private TableColumn<Commande, Integer> id;
    @FXML
    private TableColumn<Commande, Integer> idPanier;
    @FXML
    private TableColumn<Commande, Integer> idProd;
    @FXML
    private TableColumn<Commande, String> nomProd;
    @FXML
    private TableColumn<Commande, Integer> quantite;
    @FXML
    private TableColumn<Commande, Float> prixUnitaire;
    @FXML
    private TableColumn<Commande, Float> prixRem;
    @FXML
    private TableColumn<Commande, String> etatCmd;
    @FXML
    private TableColumn<Commande, String> date;
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
        
         
         
            id.setCellValueFactory(new PropertyValueFactory<Commande , Integer>("idCmd"));
            idPanier.setCellValueFactory(new PropertyValueFactory<Commande , Integer>("idPanier"));
            idProd.setCellValueFactory(new PropertyValueFactory<Commande , Integer>("idProd"));
            nomProd.setCellValueFactory(new PropertyValueFactory<Commande , String>("nomProd"));
                   quantite.setCellValueFactory(new PropertyValueFactory<Commande , Integer>("quantite"));
            prixUnitaire.setCellValueFactory(new PropertyValueFactory<Commande , Float>("prixProd"));
            prixRem.setCellValueFactory(new PropertyValueFactory<Commande , Float>("prixRemise"));
            etatCmd.setCellValueFactory(new PropertyValueFactory<Commande , String>("etatCmd"));
                        date.setCellValueFactory(new PropertyValueFactory<Commande , String>("dateCmd"));

                         CommandeCRUD ccrd = new CommandeCRUD();
   
    List<Commande> allProd = ccrd.afficherCommande();
    ObservableList<Commande> obList = FXCollections.observableArrayList(allProd);
            
            //trouver liste id commandes
            List<Integer> l = null ;
            if (allProd != null){
                l = ccrd.getAllCommandeId();
            }
            //initialiser dropdown menu
            
            if (!(l ==  null)){
                cb.getItems().addAll(l);
            }
              //  cb.setValue;
           // }
            cb.setOnAction(this::getCommandeCB);
            
            tableCommandeSaif.setItems(obList);
    }    

    @FXML
    private void redirectDashboardCommandeSaif(ActionEvent event) throws IOException, SQLException {
            int idCurrentUser = Integer.parseInt(Session.id);
        Session session = new Session();
        String roleArray = session.getRole(idCurrentUser);
        String role = roleArray.substring(2,roleArray.length()-2);
        System.err.println(role);
        
        
        if ("ROLE_ARTISTE".equals(role) ){
        Parent root = FXMLLoader.load(getClass().getResource("CCC.fxml"));
        anchorPaneCommande.getScene().getWindow().hide();
        anchorPaneCommande.getScene().getWindow();
        
        Stage mainStage = new Stage();
        Scene scene = new Scene(root);
        mainStage.setScene(scene);
        mainStage.show();

    }
        else if ("ROLE_PRESTATAIRE".equals(role)){
             Parent root = FXMLLoader.load(getClass().getResource("Prestataire.fxml"));
        anchorPaneCommande.getScene().getWindow().hide();
        anchorPaneCommande.getScene().getWindow();
        
        Stage mainStage = new Stage();
        Scene scene = new Scene(root);
        mainStage.setScene(scene);
        mainStage.show();
        }
        else if ("ROLE_SUPER_ADMIN".equals(role)){
        Parent root = FXMLLoader.load(getClass().getResource("AdminDashboard.fxml"));
        anchorPaneCommande.getScene().getWindow().hide();
        anchorPaneCommande.getScene().getWindow();
        
        Stage mainStage = new Stage();
        Scene scene = new Scene(root);
        mainStage.setScene(scene);
        mainStage.show();
        }
        else if ("ROLE_USER".equals(role) ){
              Parent root = FXMLLoader.load(getClass().getResource("UserDashboard.fxml"));
        anchorPaneCommande.getScene().getWindow().hide();
        anchorPaneCommande.getScene().getWindow();
        
        Stage mainStage = new Stage();
        Scene scene = new Scene(root);
        mainStage.setScene(scene);
        mainStage.show();
        }
    }

  

    
    
      private void getCommandeCB(ActionEvent event){
        Integer id = cb.getValue();
        if (id!=null){
            CommandeCRUD ccrd = new CommandeCRUD();
            Commande c1 = new Commande(id);
            List<Commande> allProd = ccrd.afficherParIdCommande(c1);
            ObservableList<Commande> obList = FXCollections.observableArrayList(allProd);
            tableCommandeSaif.setItems(obList);
        }
    }

    @FXML
    private void signout600(ActionEvent event) throws IOException {
              Parent root = FXMLLoader.load(getClass().getResource("test.fxml"));
        anchorPaneCommande.getScene().getWindow().hide();
        anchorPaneCommande.getScene().getWindow();
        
        Stage mainStage = new Stage();
        Scene scene = new Scene(root);
        mainStage.setScene(scene);
        mainStage.show();
    }
}
