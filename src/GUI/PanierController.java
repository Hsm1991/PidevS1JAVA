/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import entities.Session;
import entities.Panier;
import entities.Commande;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Popup;
import javafx.stage.Stage;
import javafx.util.Duration;
import services.*;
//import org.controlsfx.control.Notifications;

/**
 * FXML Controller class
 *
 * @author saif
 */
public class PanierController implements Initializable {


    @FXML
    private Button btnDashboardPanierSaif;
    @FXML
    private Button btnReclamationPanierSaif;
    @FXML
    private TableView<Panier> tablePanierSaif;
    @FXML
    private TableColumn<Panier , String> colNomProduitPanier;
    @FXML
    private TableColumn<Panier , Float> colPrixUnitairePanier;
    @FXML
    private TableColumn<Panier , Integer> colQuantitePanier;
    @FXML
    private TableColumn<Panier , Float> colPrixPanier;
    @FXML
    private Button btnSupprimerProduitSaif;
    @FXML
    private Button btnContinuerShoppingSaif;
    @FXML
    private Button btnViderPanierSaif;
    @FXML
    private Button btnPasserCommande;
    @FXML
    private Button btnModifierQuantiteSaif;
    @FXML
    private AnchorPane anchorPanePanier;
    @FXML
    private TextField tfTotalPanier;
    @FXML
    private Button btnCalculerTotalPanier;
    @FXML
    private Button btnCommandesPanier;
    @FXML
    private Label lbWarningVideSaif;
    
    private static Integer q = 1;
    
    //session
    Integer idSession = Integer.parseInt(Session.id);
    //trouver le panier en sa totalité*************************************************
    Panier p = new Panier(Integer.parseInt(Session.id));
    PanierCRUD pcrd = new PanierCRUD();
    List<Panier> allProd = pcrd.afficherParIdPanier(p);
    //*********************************************************************************
    
    
    //conversion en observableList*****************************************************
    ObservableList<Panier> obList = FXCollections.observableArrayList(allProd);
    //*********************************************************************************
    @FXML
    private Label lbWarningSelection;
    @FXML
    private Button ao_btn_signout;
    @FXML
    private Label labelsignout;
    
    
   
    
    
    
    
    /**
     * Initializes the controller class.
     */
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        colNomProduitPanier.setCellValueFactory(new PropertyValueFactory<Panier , String>("nomProd"));
        colPrixUnitairePanier.setCellValueFactory(new PropertyValueFactory<Panier , Float>("prixProd"));
        colQuantitePanier.setCellValueFactory(new PropertyValueFactory<Panier , Integer>("quantite"));
        colPrixPanier.setCellValueFactory(new PropertyValueFactory<Panier , Float>("prixRemise"));
        
        tablePanierSaif.setItems(obList);
    }    

   @FXML
    private void redirectDashboardPanierSaif(ActionEvent event) throws IOException, SQLException {
       int idCurrentUser = Integer.parseInt(Session.id);
        Session session = new Session();
        String roleArray = session.getRole(idCurrentUser);
        String role = roleArray.substring(2,roleArray.length()-2);
        System.err.println(role);
        
        
        if ("ROLE_ARTISTE".equals(role) ){
        Parent root = FXMLLoader.load(getClass().getResource("CCC.fxml"));
        tablePanierSaif.getScene().getWindow().hide();
        tablePanierSaif.getScene().getWindow();
        
        Stage mainStage = new Stage();
        Scene scene = new Scene(root);
        mainStage.setScene(scene);
        mainStage.show();

    }
        else if ("ROLE_PRESTATAIRE".equals(role)){
             Parent root = FXMLLoader.load(getClass().getResource("Prestataire.fxml"));
        tablePanierSaif.getScene().getWindow().hide();
        tablePanierSaif.getScene().getWindow();
        
        Stage mainStage = new Stage();
        Scene scene = new Scene(root);
        mainStage.setScene(scene);
        mainStage.show();
        }
        else if ("ROLE_SUPER_ADMIN".equals(role)){
        Parent root = FXMLLoader.load(getClass().getResource("AdminDashboard.fxml"));
        tablePanierSaif.getScene().getWindow().hide();
        tablePanierSaif.getScene().getWindow();
        
        Stage mainStage = new Stage();
        Scene scene = new Scene(root);
        mainStage.setScene(scene);
        mainStage.show();
        }
        else if ("ROLE_USER".equals(role) ){
              Parent root = FXMLLoader.load(getClass().getResource("UserDashboard.fxml"));
        tablePanierSaif.getScene().getWindow().hide();
        tablePanierSaif.getScene().getWindow();
        
        Stage mainStage = new Stage();
        Scene scene = new Scene(root);
        mainStage.setScene(scene);
        mainStage.show();
        }
    }

    @FXML
    private void redirectReclamationPanierSaif(ActionEvent event) throws IOException {
         tablePanierSaif.getScene().getWindow().hide();
        tablePanierSaif.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("ReclamationFront.fxml"));
        Stage mainStage = new Stage();
        Scene scene = new Scene(root);
        mainStage.setScene(scene);
        mainStage.show(); 
    }

    private void logoutPanierSaif(MouseEvent event) {
        //redirection vers Login interface
        try {
            AnchorPane pane = FXMLLoader.load(getClass().getResource("panier.fxml"));
            anchorPanePanier.getChildren().setAll(pane);
        } catch (IOException ex) {
            Logger.getLogger(CommandeSaifController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void supprimerProduitPanierSaif(ActionEvent event) {
        //1:suppression produit panier de la BD
        //2:mise a jour de tableview
        try{
        Panier p1 = tablePanierSaif.getSelectionModel().getSelectedItem();
        System.out.println(p.getIdPanier());
        PanierCRUD pcrd = new PanierCRUD();
        pcrd.supprimerPanier(p1);
        allProd = pcrd.afficherParIdPanier(p1);
        obList = FXCollections.observableArrayList(allProd);
        tablePanierSaif.setItems(obList);
        
          /*  Notifications notificationBuilder = Notifications.create()
                    .title("Effectué")
                    .text("produit supprimée de panier")
                     .graphic(null)
                     .hideAfter(Duration.seconds(10))
                     .position(Pos.BASELINE_RIGHT)
                     .onAction(new EventHandler<ActionEvent>(){
                         @Override
                         public void handle(ActionEvent event){
                             System.out.println("produit supprimée");
                         }
                     }
                     );   

             notificationBuilder.showConfirm();     */
        }catch(Exception ex){
            System.out.println("selectionner un element");
        }
    }

    @FXML
    private void continuerShopping(ActionEvent event) {
        //redirection vers shop
        try {
            AnchorPane pane = FXMLLoader.load(getClass().getResource("panier.fxml"));
            anchorPanePanier.getChildren().setAll(pane);
        } catch (IOException ex) {
            Logger.getLogger(CommandeSaifController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void viderPanier(ActionEvent event) {
        //1:suppression panier de la BD
        //2:mise a jour du tableview ???
        lbWarningSelection.setVisible(false);
        lbWarningVideSaif.setVisible(false);
        Panier p1 = new Panier(idSession);
        PanierCRUD pcrd1 = new PanierCRUD();
        System.out.println("en train de vider panier");
        pcrd1.viderPanier(p1);
        allProd = pcrd1.afficherParIdPanier(p1);
        obList = FXCollections.observableArrayList(allProd);
        tablePanierSaif.setItems(obList);
        
    }

    @FXML
    private void passerCommandeSaif(ActionEvent event) {
        //controle
        lbWarningSelection.setVisible(false);
        //1:capturer informations panier
        Panier p1 = new Panier(idSession);
        List<Panier> allProd1 = pcrd.afficherParIdPanier(p1);
        if (allProd1.isEmpty()){
            lbWarningVideSaif.setVisible(true);
        }
        else{
            //compilation du l'idCmd
            Integer id = 1; 
            boolean found = false ;
            CommandeCRUD ccrd = new CommandeCRUD();
            List<Commande> allCmd = ccrd.afficherCommande();
            for (Commande cTest : allCmd){
                if (id <= cTest.getIdCmd()){
                    id = cTest.getIdCmd();
                    found = true;
                }
            }
            if (found){
                id = id +1 ;
            }
            //compilation du date systeme
            String date = new SimpleDateFormat("yyyy/MM/dd  HH:mm:ss")
                    .format(Calendar.getInstance().getTime());

            //enregistre la commande
            for (Panier pTest : allProd1){
                //creation de la commande
                Commande c = new Commande();
                //construction de la commande
                c.setIdCmd(id);
                c.setIdPanier(pTest.getIdPanier());
                c.setIdProd(pTest.getIdProd());
                c.setNomProd(pTest.getNomProd());
                c.setQuantite(pTest.getQuantite());
                c.setPrixProd(pTest.getPrixProd());
                c.setPrixRemise(pTest.getPrixRemise());
                c.setEtatCmd("En attente de confirmation");
                c.setDateCmd(date);
                //ajout au BD
                ccrd.ajouterCommande(c);  
                //passation du l'idCmd 
                CommandeSaifController.idc = id;
            } 
            //3:redirection vers commande.fxml 
            FXMLLoader loader = new FXMLLoader(getClass().getResource("commandeSaif.fxml"));
            try {
                Parent root = loader.load();
                anchorPanePanier.getScene().setRoot(root);
            } catch (IOException ex) {
                System.err.println(ex.getMessage());
            }
        }
    }
    @FXML
    private void modifierQuantiteSaif(ActionEvent event) {
        Panier pOld = new Panier();
        if (tablePanierSaif.getSelectionModel().getSelectedItem() == null){
            lbWarningSelection.setVisible(true);
            
        }
        else{
            lbWarningSelection.setVisible(false);
            try {
                //fetching de selection
                try{
                    pOld = tablePanierSaif.getSelectionModel().getSelectedItem();

                }catch(Exception ex){
                    System.out.println("selectionner une ligne");
                }
                //creation stage
                Stage stage = new Stage();
                Parent root = FXMLLoader.load(getClass().getResource("popupPanierSaif.fxml"));
                stage.setScene(new Scene(root));
                stage.initModality(Modality.APPLICATION_MODAL);
                stage.initOwner(btnModifierQuantiteSaif.getScene().getWindow());
                stage.showAndWait();

                if (pOld!=null){
                    //creation nouveau panier
                    Panier pNew = new Panier();
                    pNew.setIdPanier(pOld.getIdPanier());
                    pNew.setIdProd(pOld.getIdProd());
                    pNew.setNomProd(pOld.getNomProd());
                    pNew.setQuantite(q);
                    pNew.setPrixProd(pOld.getPrixProd());
                    pNew.setPrixRemise(MetierSaif.remisePanier(q, pNew.getPrixProd()));

                    //modification BD
                    pcrd.modifierPanier(pNew, pOld);

                    //repopulation tableau
                    allProd = pcrd.afficherParIdPanier(pNew);
                    obList = FXCollections.observableArrayList(allProd);
                    tablePanierSaif.setItems(obList);

                }
                else{
                    System.err.println("selectionner un element");
                }

            } catch (IOException ex) {
                System.err.println(ex.getMessage());
            }
        
        } 
    }

    public Integer getQ() {
        return q;
    }

    public void setQ(Integer q) {
        this.q = q;
    }

    @FXML
    private void calculerTotalPanier(ActionEvent event) {
        Float t = MetierSaif.totalPanier(p);
        tfTotalPanier.setText("" + t);
        
    }

    @FXML
    private void redirectCommandePanierSaif(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("commandeSaif.fxml"));
        CommandeCRUD ccrd = new CommandeCRUD();
        CommandeSaifController.idc = ccrd.afficherParIdPanierCommande(idSession).getIdCmd();
        System.out.println(CommandeSaifController.idc);
        try {
            Parent root = loader.load();
            
            System.out.println("dernier commande = " + CommandeSaifController.idc);
            anchorPanePanier.getScene().setRoot(root);
        } catch (IOException ex) {
            System.err.println("error"+ex.getMessage());
            ex.printStackTrace();
        }
    }

    @FXML
    private void signout1307(ActionEvent event) throws IOException {
                Parent root = FXMLLoader.load(getClass().getResource("test.fxml"));
        tablePanierSaif.getScene().getWindow().hide();
        tablePanierSaif.getScene().getWindow();
        
        Stage mainStage = new Stage();
        Scene scene = new Scene(root);
        mainStage.setScene(scene);
        mainStage.show();
    }
 
    
}
