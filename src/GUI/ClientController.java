/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;

import entities.Panier;
import entities.Produit;
import entities.Session;
import java.awt.Color;
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
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;
import services.MetierSaif;
import services.PanierCRUD;
import services.ProduitCRUD;

/**
 * FXML Controller class
 *
 * @author User
 */
public class ClientController implements Initializable {

    private Button minus3;
    @FXML
    private AnchorPane main_center_form3;
    @FXML
    private Button Ajouter3;
    @FXML
    private ImageView pd_imageview3;
    @FXML
    private Label label_img2;
    @FXML
    private AnchorPane prd_dispo3_form;
    @FXML
    private TableView<Produit> table_view_prd3;
    @FXML
    private TableColumn<Produit, String> artiste3;
    @FXML
    private TableColumn<Produit, String> prix3;
    @FXML
    private TableColumn<Produit, String> localistaion_prod3;
    @FXML
    private TableColumn<Produit, String> type_prod3;
    @FXML
    private TableColumn<Produit, String> sat_prod3;
    @FXML
    private TextField recherche3;
    @FXML
    private AnchorPane halfnavform3;
    @FXML
    private Circle small_circle3;
 private Image imagem1;
    @FXML
    private Label message_error;
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
        // TODO
    ProduitCRUD ppd = new ProduitCRUD();
        ObservableList Ar =  ppd.afficherProduit();
      
        artiste3.setCellValueFactory(new PropertyValueFactory<Produit, String>("NomProd"));
	prix3.setCellValueFactory(new PropertyValueFactory<Produit, String>("PrixProd"));	
	localistaion_prod3.setCellValueFactory(new PropertyValueFactory<Produit, String>("LocalisationProd"));
        type_prod3.setCellValueFactory(new PropertyValueFactory<Produit, String>("TypeProd"));
	sat_prod3.setCellValueFactory(new PropertyValueFactory<Produit, String>("TypeStatue"));

       table_view_prd3.setItems(Ar);
       
       message_error.setVisible(false);
                // TODO
    
       FilteredList<Produit> filteredData = new FilteredList<>(Ar, b -> true);
         
         //2. Set the filter Predicate whenever the filter changes.
         recherche3.textProperty().addListener((observable, oldValue, newValue) -> {
         filteredData.setPredicate(prod -> {
         // If filter text is empty, display all persons.
         
         if (newValue == null || newValue.isEmpty()) {
         return true;
         }
         
         // Compare first name and last name of every person with filter text.
         String lowerCaseFilter = newValue.toLowerCase();
         
         
         if (prod.getNomProd().toLowerCase().contains(lowerCaseFilter)) {
         return true; // Filter matches last name.
         }
         else if (String.valueOf(prod.getPrixProd()).indexOf(lowerCaseFilter)!=-1) {
         return true;}
         else if (prod.getLocalisationProd().toLowerCase().contains(lowerCaseFilter)) {
         return true;}
      
         else if (String.valueOf(prod.getTypeProd()).indexOf(lowerCaseFilter)!=-1){
         return true;}
         else return String.valueOf(prod.getTypeStatue()).contains(lowerCaseFilter);
         
         
         });
         });
         SortedList<Produit> sortedData = new SortedList<>(filteredData);
         
         // 4. Bind the SortedList comparator to the TableView comparator.
         // 	  Otherwise, sorting the TableView would have no effect.
         sortedData.comparatorProperty().bind(table_view_prd3.comparatorProperty());
         
         // 5. Add sorted (and filtered) data to the table.
         table_view_prd3.setItems(sortedData);
    }
   

    private void ProduitStar_m(ActionEvent event) throws IOException {
         
        table_view_prd3.getScene().getWindow().hide();
         Parent root = FXMLLoader.load(getClass().getResource("ProduitStar.fxml"));
         Stage mainStage = new Stage();
         Scene scene = new Scene(root);
         mainStage.setScene(scene);
         mainStage.show();}



    private void close3(ActionEvent event) {
        System.exit(0);
    }

    private void minus3(ActionEvent event) {
     Stage stage =(Stage) minus3.getScene().getWindow();
     stage.setIconified(true);
        
    }


    @FXML
    private void Dashboard3(ActionEvent event) throws IOException, SQLException {
          int idCurrentUser = Integer.parseInt(Session.id);
        Session session = new Session();
        String roleArray = session.getRole(idCurrentUser);
        String role = roleArray.substring(2,roleArray.length()-2);
        System.err.println(role);
        
        
        if ("ROLE_ARTISTE".equals(role) ){
        Parent root = FXMLLoader.load(getClass().getResource("CCC.fxml"));
        table_view_prd3.getScene().getWindow().hide();
        table_view_prd3.getScene().getWindow();
        
        Stage mainStage = new Stage();
        Scene scene = new Scene(root);
        mainStage.setScene(scene);
        mainStage.show();

    }
        else if ("ROLE_PRESTATAIRE".equals(role)){
             Parent root = FXMLLoader.load(getClass().getResource("Prestataire.fxml"));
        table_view_prd3.getScene().getWindow().hide();
        table_view_prd3.getScene().getWindow();
        
        Stage mainStage = new Stage();
        Scene scene = new Scene(root);
        mainStage.setScene(scene);
        mainStage.show();
        }
        else if ("ROLE_SUPER_ADMIN".equals(role)){
        Parent root = FXMLLoader.load(getClass().getResource("AdminDashboard.fxml"));
        table_view_prd3.getScene().getWindow().hide();
        table_view_prd3.getScene().getWindow();
        
        Stage mainStage = new Stage();
        Scene scene = new Scene(root);
        mainStage.setScene(scene);
        mainStage.show();
        }
        else if ("ROLE_USER".equals(role) ){
              Parent root = FXMLLoader.load(getClass().getResource("UserDashboard.fxml"));
        table_view_prd3.getScene().getWindow().hide();
        table_view_prd3.getScene().getWindow();
        
        Stage mainStage = new Stage();
        Scene scene = new Scene(root);
        mainStage.setScene(scene);
        mainStage.show();
        }
    }


    @FXML
    private void Reclamation3(ActionEvent event) throws IOException {
              table_view_prd3.getScene().getWindow().hide();
        table_view_prd3.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("ReclamationFront.fxml"));
        Stage mainStage = new Stage();
        Scene scene = new Scene(root);
        mainStage.setScene(scene);
        mainStage.show();
    }






    @FXML
    private void recherche3(ActionEvent event) {
    }
   /* Connection cnx2;
    private void rechaclient3(ActionEvent event) {
        String sql ="SELECT * FROM PRODUIT WHERE NomProd='" +rechartiste3.getText() + "'";
        
        try{
             ResultSet rs;
            PreparedStatement ste;
            ste = cnx2.prepareStatement(sql);
            rs = ste.executeQuery();
            boolean check=false;
            
            Alert alert ;
            if(titreprod3.getText().isEmpty()){
              alert=new Alert (AlertType.ERROR);
              alert.setTitle("Admin Message");
              alert.setHeaderText(null);
              alert.setContentText("S'il vous plait selectionnez l'artiste");
              alert.showAndWait();
            }else{
                while(rs.next()){
                    titreprod3.setText(rs.getString("NomProd"));
                    prixprod3.setText(rs.getString("PrixProd"));
                    localprod3.setText(rs.getString("LocalisationProd"));
                    typeprod3.setText(rs.getString("TypeProd"));
                    statprod3.setText(rs.getString("TypeStatue"));
                    
                    
                    getData_m.path111=rs.getString("imagem1");
                    String uri = "file"+getData_m.path111;
                   imagem1 = new Image (uri, 229,186, false, true) ;
                     pd_imageview3.setImage(imagem1);
                      
                    check = true ;
                }
                if(!check){
                    titreprod3.setText("L'oeuvre n'est pas disponible!");
              
                
                
                }
            }
        }catch(Exception e){e.printStackTrace();}
    }*/
    
@FXML
public void selectimage2(){
     
      Produit getData_m = table_view_prd3.getSelectionModel().getSelectedItem();
    int num = table_view_prd3.getSelectionModel().getFocusedIndex();
        if((num-1) <-1)
        return;
        label_img2.setText(getData_m.getNomProd());
        String uri = "file:" + getData_m.getimagem1();
        
        imagem1 = new Image (uri, 229,186, false, true) ;
        pd_imageview3.setImage(imagem1);
}

    private void addFavo(ActionEvent event) {
        Interfaces.ListFavorit.add(table_view_prd3.getSelectionModel().getSelectedItem())
        ;
    }

    private void gotoFav(ActionEvent event) throws IOException {
        table_view_prd3.getScene().getWindow().hide();
         Parent root = FXMLLoader.load(getClass().getResource("ProduitStar.fxml"));
         Stage mainStage = new Stage();
         Scene scene = new Scene(root);
         mainStage.setScene(scene);
         mainStage.show();
    }

    @FXML
    private void panierredirection(ActionEvent event) throws IOException {
                table_view_prd3.getScene().getWindow().hide();
        table_view_prd3.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("panier.fxml"));
        Stage mainStage = new Stage();
        Scene scene = new Scene(root);
        mainStage.setScene(scene);
        mainStage.show();
    }

    @FXML
    private void Ajouterpanier(ActionEvent event) {
        Produit selected = table_view_prd3.getSelectionModel().getSelectedItem();
        
        if(selected == null){
            message_error.setVisible(true);
        }
        else{
            
            message_error.setVisible(false);
            ///quantitee
            int q =0 ;
            PanierCRUD pcrd = new PanierCRUD();
            Panier panier = new Panier();
            panier.setIdPanier(Integer.parseInt(Session.id));
            q = pcrd.checkExists(panier, selected.getIdPROD());
          
            
            panier.setIdProd(selected.getIdPROD());
            panier.setNomProd(selected.getNomProd());
            panier.setPrixProd(selected.getPrixProd());
            panier.setQuantite(q+1);
            panier.setPrixRemise(MetierSaif.remisePanier(q, selected.getPrixProd()));
            
            if (q == 0){
                pcrd.ajouterPanier(panier);
            }
            else{
                pcrd.modifierPanier(panier, panier);
            }
            
             Notifications notificationBuilder = Notifications.create()
                    .title("Effectué")
                    .text("Produit Ajouté Au Panier")
                     .graphic(null)
                     .hideAfter(Duration.seconds(10))
                     .position(Pos.BASELINE_RIGHT);

             notificationBuilder.showConfirm();
             
            
        }
        
    }

    @FXML
    private void signout400(ActionEvent event) throws IOException {
             table_view_prd3.getScene().getWindow().hide();
        table_view_prd3.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("test.fxml"));
        Stage mainStage = new Stage();
        Scene scene = new Scene(root);
        mainStage.setScene(scene);
        mainStage.show();
    
    }
}
