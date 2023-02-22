/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import entities.Produit;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;
import services.ProduitCRUD;

/**
 * FXML Controller class
 *
 * @author User
 */
public class ProduitStarController implements Initializable {

    private Button Retour_m;
    private Button minus4;
    @FXML
    private Button dashboard4;
    @FXML
    private Button panier4;
    @FXML
    private Button reclam4;
    @FXML
    private ImageView image4;
    @FXML
    private Button supp4;
    @FXML
    private TableView<Produit> table_view4;
    @FXML
    private TableColumn<Produit, String> artiste4;
    @FXML
    private TableColumn<Produit, String> prix4;
    @FXML
    private TableColumn<Produit, String> localistaion4;
    @FXML
    private TableColumn<Produit, String> type_prod4;
    @FXML
    private TableColumn<Produit, String> sat_prod4;
    @FXML
    private Label label_image4;
     private Image imagem1;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    
         ProduitCRUD ppd = new ProduitCRUD();
        ObservableList Ar =  ppd.afficherProduit();
      
        artiste4.setCellValueFactory(new PropertyValueFactory<Produit, String>("NomProd"));
	prix4.setCellValueFactory(new PropertyValueFactory<Produit, String>("PrixProd"));	
	localistaion4.setCellValueFactory(new PropertyValueFactory<Produit, String>("LocalisationProd"));
        type_prod4.setCellValueFactory(new PropertyValueFactory<Produit, String>("TypeProd"));
	sat_prod4.setCellValueFactory(new PropertyValueFactory<Produit, String>("TypeStatue"));

       table_view4.setItems(Interfaces.ListFavorit);
    }
  

     private void close4_btn(ActionEvent event) {
         System.exit(0);
    }

    private void minus4_btn(ActionEvent event) {
         Stage stage =(Stage) minus4.getScene().getWindow();
     stage.setIconified(true);
    }

    @FXML
    private void dashboard4_btn(ActionEvent event) throws IOException {
          reclam4.getScene().getWindow().hide();
        reclam4.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("Visiteur.fxml"));
        Stage mainStage = new Stage();
        Scene scene = new Scene(root);
        mainStage.setScene(scene);
        mainStage.show();
    }

    @FXML
    private void panier4_btn(ActionEvent event) {
    }


    @FXML
    private void reclam4_btn(ActionEvent event) throws IOException {
        ///redirection 
        
      
    }

    @FXML
    private void supp4(ActionEvent event) {
        
        ProduitCRUD ecd = new ProduitCRUD();
        ObservableList EV =  ecd.afficherProduit();
        ecd.supprimerProduit(Interfaces.p1.getIdPROD());
        
    
        
        
        Notifications notificationBuilder;
             notificationBuilder = Notifications.create()
             .title("Effectué")
             .text("Oeuvre Supprime Avec Succés")
             .graphic(null)
             .hideAfter(Duration.seconds(10))
             .position(Pos.BASELINE_RIGHT); 
             notificationBuilder.showConfirm();
    }

    @FXML
    private void selectimage2(MouseEvent event) {
         Produit getData_m = table_view4.getSelectionModel().getSelectedItem();
    int num = table_view4.getSelectionModel().getFocusedIndex();
        if((num-1) <-1)
        return;
        label_image4.setText(getData_m.getNomProd());
        String uri = "file:" + getData_m.getimagem1();
        
        imagem1 = new Image (uri, 215,184, false, true) ;
        image4.setImage(imagem1);
        
    }
    }
    

