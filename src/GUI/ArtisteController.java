/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import entities.Produit;
import entities.Session;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.animation.TranslateTransition;
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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Circle;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Duration;
import javax.mail.MessagingException;
import org.controlsfx.control.Notifications;
import services.ProduitCRUD;
import services.getData_m;

/**
 * FXML Controller class
 *
 * @author User
 */
public class ArtisteController implements Initializable {
   private Image imagem1;
   
Produit pv = new Produit();
    @FXML
    private AnchorPane window1;
    @FXML
    private AnchorPane nav_form_artiste_m;
    private Button suivre_vente_art;
    private AnchorPane halfNav_form_artiste_m;
    private Button arrow_btn_artiste_m;
    private Button bars_btn_artiste_m;
    @FXML
    private AnchorPane mainCenter_form_artiste_m;
    @FXML
    private TextField ch_titre;
    @FXML
    private Label erreur_Titre;
    @FXML
    private ComboBox<String> cmbx_art_stat;
    @FXML
    private ComboBox<String> cmb_art_typ;
    @FXML
    private TextField chm_artiste_loc;
    @FXML
    private TextField cha_artiste_prix;
    @FXML
    private ImageView Image_art_m;
    @FXML
    private Label err2;
    @FXML
    private Label erreur_Titre2;
    @FXML
    private Label erreur_Titre3;
    @FXML
    private Label labelimage;
    @FXML
    private Button importer2;
    @FXML
    private Label errex;
    @FXML
    private TableView<Produit> tree_artiste;
    @FXML
    private TableColumn<Produit, Integer> IdPROD;
    @FXML
    private TableColumn<Produit, String> NomProd;
    @FXML
    private TableColumn<Produit, String> PrixProd;
    @FXML
    private TableColumn<Produit, String> LocalisationProd;
    @FXML
    private TableColumn<Produit, String> TypeProd;
    @FXML
    private TableColumn<Produit, String> TypeStatue;
    @FXML
    private TextField champ_recherche_m;
    @FXML
    private Button vider;
    @FXML
    private Button Supprimer_ARTISTE;
    @FXML
    private Button Ajouter_artiste;
    @FXML
    private Button Modifier_artiste;
    @FXML
    private Button select_m1;
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
               ///////////controle de saisie
                erreur_Titre.setVisible(false);
                     erreur_Titre2.setVisible(false);
                        erreur_Titre3.setVisible(false);
                        errex.setVisible(false);
                        err2.setVisible(false);
                        
        ///////////////////cmbx///////////////////                
        ObservableList<String> list = FXCollections.observableArrayList("Thétrale","Musicale","Art");
         cmb_art_typ.setItems(list);
         
         ObservableList<String> list1 = FXCollections.observableArrayList("Disponible","NonDisponible");
         cmbx_art_stat.setItems(list1);
         
        // //affichage table view
        ProduitCRUD ppd = new ProduitCRUD();
        ObservableList TE =  ppd.afficherProduit();
        IdPROD.setCellValueFactory(new PropertyValueFactory<Produit, Integer>("IdPROD"));
        NomProd.setCellValueFactory(new PropertyValueFactory<Produit, String>("NomProd"));
	PrixProd.setCellValueFactory(new PropertyValueFactory<Produit, String>("PrixProd"));	
	LocalisationProd.setCellValueFactory(new PropertyValueFactory<Produit, String>("LocalisationProd"));
        TypeProd.setCellValueFactory(new PropertyValueFactory<Produit, String>("TypeProd"));
	TypeStatue.setCellValueFactory(new PropertyValueFactory<Produit, String>("TypeStatue"));

       tree_artiste.setItems(TE);
       
       
       
          
       ////////////////////////////recherche//////////////////////////////////////////////////////
        FilteredList<Produit> filteredData = new FilteredList<>(TE, b -> true);
         
         //2. Set the filter Predicate whenever the filter changes.
         champ_recherche_m.textProperty().addListener((observable, oldValue, newValue) -> {
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
         else if (String.valueOf(prod.getIdPROD()).indexOf(lowerCaseFilter)!=-1){
         return true;}
         else if (String.valueOf(prod.getTypeProd()).indexOf(lowerCaseFilter)!=-1){
         return true;}
         else return String.valueOf(prod.getTypeStatue()).contains(lowerCaseFilter);
         
         
         });
         });
         SortedList<Produit> sortedData = new SortedList<>(filteredData);
         
         // 4. Bind the SortedList comparator to the TableView comparator.
         // 	  Otherwise, sorting the TableView would have no effect.
         sortedData.comparatorProperty().bind(tree_artiste.comparatorProperty());
         
         // 5. Add sorted (and filtered) data to the table.
         tree_artiste.setItems(sortedData);
                
         
         
     
         
        }
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
    
        
        
        
        
    
   @FXML
    private void DeleteProduit(ActionEvent event) throws IOException {
        Interfaces.p1 = tree_artiste.getSelectionModel().getSelectedItem();
        //importer2.getScene().getWindow().hide();
       // Supprimer_ARTISTE.getScene().getWindow();
       // Parent root = FXMLLoader.load(getClass().getResource("SuppressionMaram.fxml"));
      /*  Stage mainStage = new Stage();
        Scene scene = new Scene(root);
        mainStage.setScene(scene);
        mainStage.show();*/
            ProduitCRUD ecd = new ProduitCRUD();
          ecd.supprimerProduit(Interfaces.p1.getIdPROD());
    
           ProduitCRUD ppd = new ProduitCRUD();
        ObservableList TE =  ppd.afficherProduit();
        IdPROD.setCellValueFactory(new PropertyValueFactory<Produit, Integer>("IdPROD"));
        NomProd.setCellValueFactory(new PropertyValueFactory<Produit, String>("NomProd"));
	PrixProd.setCellValueFactory(new PropertyValueFactory<Produit, String>("PrixProd"));	
	LocalisationProd.setCellValueFactory(new PropertyValueFactory<Produit, String>("LocalisationProd"));
        TypeProd.setCellValueFactory(new PropertyValueFactory<Produit, String>("TypeProd"));
	TypeStatue.setCellValueFactory(new PropertyValueFactory<Produit, String>("TypeStatue"));

       tree_artiste.setItems(TE);
       
      
        
        
        
        
        
    }
    
    
      ///////////////////ajouter//////////
    @FXML
    private void AjouterArtiste(ActionEvent event) throws MessagingException, SQLException {
        erreur_Titre.setVisible(false);
        erreur_Titre2.setVisible(false);
        erreur_Titre3.setVisible(false);
            err2.setVisible(false);  
      errex.setVisible(false);  
        Produit e1 = new Produit();
        
        
        ////////////////////CONTROLE DE SAISIE//////////////////
        int x = 1;
              ProduitCRUD ecd = new ProduitCRUD();
        if (ecd.testproduit(ch_titre.getText()) == 1)
        {
            x = 0;
        errex.setVisible(true);
        }
         if(ch_titre.getText().equals("")){
            erreur_Titre.setVisible(true);
             x = 0;
        }
         if(chm_artiste_loc.getText().equals("")){
           erreur_Titre2.setVisible(true);
            x = 0;
        }
           if(cha_artiste_prix.getText().equals("")){
           erreur_Titre3.setVisible(true);
            x = 0;
        }
           

         if(x == 1){
        
          erreur_Titre.setVisible(false);
        if(ch_titre.getText().equals("")){
           erreur_Titre.setVisible(true);
        }
               erreur_Titre2.setVisible(false);
        if(chm_artiste_loc.getText().equals("")){
           erreur_Titre2.setVisible(true);
        }
               erreur_Titre3.setVisible(false);
        if(cha_artiste_prix.getText().equals("")){
           erreur_Titre3.setVisible(true);
        }
        
        
        try{
         Produit p = new Produit(ch_titre.getText(), Float.valueOf(cha_artiste_prix.getText()),chm_artiste_loc.getText(),cmb_art_typ.getValue(),cmbx_art_stat.getValue());

                  e1 = p;
                    }
                     catch(Exception ed)
                         {
                           errex.setVisible(true);
                            x= 0;
                          }
                         if(x == 1){
                             try{
               ecd.ajouterProduit2(e1);
                erreur_Titre.setVisible(false);
        erreur_Titre2.setVisible(false);
        erreur_Titre3.setVisible(false);
            err2.setVisible(false);  
      errex.setVisible(false);  
        
        }catch(Exception e)
    {
        err2.setVisible(true);
    }}
                         
        ObservableList TE =  ecd.afficherProduit();
        ecd.afficherProduit();
         tree_artiste.setItems(TE);
        IdPROD.setCellValueFactory(new PropertyValueFactory<Produit, Integer>("IdPROD"));
        NomProd.setCellValueFactory(new PropertyValueFactory<Produit, String>("NomProd"));
	PrixProd.setCellValueFactory(new PropertyValueFactory<Produit, String>("PrixProd"));	
	LocalisationProd.setCellValueFactory(new PropertyValueFactory<Produit, String>("LocalisationProd"));
        TypeProd.setCellValueFactory(new PropertyValueFactory<Produit, String>("TypeProd"));
	TypeStatue.setCellValueFactory(new PropertyValueFactory<Produit, String>("TypeStatue"));
         TE =  ecd.afficherProduit();
       tree_artiste.setItems(TE);
       }
         
         
         
         
         Notifications notificationBuilder;
             notificationBuilder = Notifications.create()
             .title("Effectué")
             .text("Oeuvre Ajouté Avec Succés")
             .graphic(null)
             .hideAfter(Duration.seconds(10))
             .position(Pos.BASELINE_RIGHT); 
             notificationBuilder.showConfirm();
    }
     
    
    
    
    
    
    
      /////////////////////////////////redirection//////////////////////////////////
    @FXML
    private void SuivreVenteArtiste(ActionEvent event) throws IOException {
        Ajouter_artiste.getScene().getWindow().hide();
         Parent root = FXMLLoader.load(getClass().getResource("OeuvVenMaram.fxml"));
         Stage mainStage = new Stage();
         Scene scene = new Scene(root);
         mainStage.setScene(scene);
         mainStage.show();}

    
    
    
    
    /////////////////////modification //////////////////////////////
    @FXML
    private void Modif(ActionEvent event) {
        
      Produit p= new Produit(pv.getIdPROD(),ch_titre.getText(), Float.valueOf(cha_artiste_prix.getText()),chm_artiste_loc.getText(),cmb_art_typ.getValue(),cmbx_art_stat.getValue());
        ProduitCRUD ppd = new ProduitCRUD();
        ppd.updateProduit(p, p.getIdPROD());
         ObservableList TE =  ppd.afficherProduit();
         ppd.afficherProduit();
       IdPROD.setCellValueFactory(new PropertyValueFactory<Produit, Integer>("IdPROD"));
        NomProd.setCellValueFactory(new PropertyValueFactory<Produit, String>("NomProd"));
	PrixProd.setCellValueFactory(new PropertyValueFactory<Produit, String>("PrixProd"));	
	LocalisationProd.setCellValueFactory(new PropertyValueFactory<Produit, String>("LocalisationProd"));
        TypeProd.setCellValueFactory(new PropertyValueFactory<Produit, String>("TypeProd"));
	TypeStatue.setCellValueFactory(new PropertyValueFactory<Produit, String>("TypeStatue"));
         TE =  ppd.afficherProduit();
          tree_artiste.setItems(TE);
      
    
    
    
     Notifications notificationBuilder;
             notificationBuilder = Notifications.create()
             .title("Effectué")
             .text("Oeuvre Modifiéé Avec Succés")
             .graphic(null)
             .hideAfter(Duration.seconds(10))
             .position(Pos.BASELINE_RIGHT); 
             notificationBuilder.showConfirm();
    
    
    }
    
    
    /////////////////////selectionner/////////////
        
    @FXML
    private void Selection(ActionEvent event) {
        pv = tree_artiste.getSelectionModel().getSelectedItem();
                ch_titre.setText(pv.getNomProd());
                cmb_art_typ.setValue(pv.getTypeProd());
                  cmbx_art_stat.setValue(pv.getTypeStatue());
                chm_artiste_loc.setText(pv.getLocalisationProd());
                cha_artiste_prix.setText(String.valueOf(pv.getPrixProd()));
               
    }



   
    
    ///////////////////////////logout//////////////////////
    private void LO_artiste_mar(ActionEvent event)  throws IOException {
   ao_btn_manage_roles.getScene().getWindow().hide();
         Parent root = FXMLLoader.load(getClass().getResource("test.fxml"));
         Stage mainStage = new Stage();
         Scene scene = new Scene(root);
         mainStage.setScene(scene);
         mainStage.show();}

  /////////navigation///////////////////// 
    public void sliderArrow() {

        TranslateTransition slide = new TranslateTransition();

        slide.setDuration(Duration.seconds(.5));
        slide.setNode(nav_form_artiste_m);
        slide.setToX(-224);

       TranslateTransition slide1 = new TranslateTransition();

        slide1.setDuration(Duration.seconds(.5));
        slide1.setNode( mainCenter_form_artiste_m);
        slide1.setToX(-224 + 90);

        TranslateTransition slide2 = new TranslateTransition();
        slide2.setDuration(Duration.seconds(.5));
        slide2.setNode( halfNav_form_artiste_m);
        slide2.setToX(0);


        slide.setOnFinished((ActionEvent event) -> {

            arrow_btn_artiste_m.setVisible(false);
             bars_btn_artiste_m.setVisible(true);

        });
           slide2.play();
        slide1.play();
        slide.play();
 }
    public void sliderderBars(){
          TranslateTransition slide = new TranslateTransition();

        slide.setDuration(Duration.seconds(.5));
        slide.setNode(nav_form_artiste_m);
        slide.setToX(0);

        TranslateTransition slide1 = new TranslateTransition();

        slide1.setDuration(Duration.seconds(.5));
        slide1.setNode( mainCenter_form_artiste_m);
        slide1.setToX(0);

        TranslateTransition slide2 = new TranslateTransition();
        slide2.setDuration(Duration.seconds(.5));
        slide2.setNode(halfNav_form_artiste_m);
        slide2.setToX(-77);

        slide.setOnFinished((ActionEvent event) -> {

           arrow_btn_artiste_m.setVisible(true);
            bars_btn_artiste_m.setVisible(false);

        });

        slide2.play();
        slide1.play();
        slide.play();
    }

    
    /////////////////image oeuvre/////////////////
    public void selectOeuvreDispo(){
      
  
}
    @FXML
    public void importerimage(){
   
FileChooser open = new FileChooser();
open.setTitle("Open Image File");
open.getExtensionFilters().add(new FileChooser.ExtensionFilter("Image File", "*jpg", "*png"));
File file= open.showOpenDialog(window1.getScene().getWindow());

if(file != null){
getData_m.path111 = file.getAbsolutePath();
imagem1 = new Image(file.toURI() .toString() , 229, 186, false, true);
Image_art_m.setImage(imagem1);

        
}
}



@FXML
public void selectimage1(){
     
      Produit getData_m = tree_artiste.getSelectionModel().getSelectedItem();
    int num = tree_artiste.getSelectionModel().getFocusedIndex();
        if((num-1) <-1)
        return;
        labelimage.setText(getData_m.getNomProd());
        String uri = "file:" + getData_m.getimagem1();
        
        imagem1 = new Image (uri, 229,186, false, true) ;
        Image_art_m.setImage(imagem1);
}

//@FXML
   // private void selectApp(MouseEvent event) {
     //    int index = tree_artiste.getSelectionModel().getSelectedIndex();
        //Produit app = tree_artiste.getSelectionModel().getSelectedItem();
        //idlaba.setText(col_ida.getCellData(index).toString());
        //candlab.setText(col_candidat.getCellData(index).toString());
        //offlab.setText(col_offre.getCellData(index).toString());
        //file_path.setText(col_cv.getCellData(index).toString());
        //etat.get44444.setText(col_etat.getCellData(index).toString());

    @FXML
    private void vider_btn(ActionEvent event) {
                ch_titre.setText("");
                cmbx_art_stat.setValue("");
                cmb_art_typ.setValue("");
                chm_artiste_loc.setText("");
                cha_artiste_prix.setText("");
      
    }



    @FXML
    private void reclam(ActionEvent event) throws IOException {
              ao_btn_manage_roles.getScene().getWindow().hide();
        ao_btn_manage_roles.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("ReclamationFront.fxml"));
        Stage mainStage = new Stage();
        Scene scene = new Scene(root);
        mainStage.setScene(scene);
        mainStage.show();
    }

    @FXML
    private void godash(ActionEvent event) throws IOException, SQLException {
         int idCurrentUser = Integer.parseInt(Session.id);
        Session session = new Session();
        String roleArray = session.getRole(idCurrentUser);
        String role = roleArray.substring(2,roleArray.length()-2);
        System.err.println(role);
        
        
        if ("ROLE_ARTISTE".equals(role) ){
        Parent root = FXMLLoader.load(getClass().getResource("CCC.fxml"));
        ao_btn_manage_roles.getScene().getWindow().hide();
        ao_btn_manage_roles.getScene().getWindow();
        
        Stage mainStage = new Stage();
        Scene scene = new Scene(root);
        mainStage.setScene(scene);
        mainStage.show();
    }
        else if ("ROLE_PRESTATAIRE".equals(role)){
             Parent root = FXMLLoader.load(getClass().getResource("Prestataire.fxml"));
        ao_btn_manage_roles.getScene().getWindow().hide();
        ao_btn_manage_roles.getScene().getWindow();
        
        Stage mainStage = new Stage();
        Scene scene = new Scene(root);
        mainStage.setScene(scene);
        mainStage.show();
        }
        else if ("ROLE_SUPER_ADMIN".equals(role)){
        Parent root = FXMLLoader.load(getClass().getResource("AdminDashboard.fxml"));
        ao_btn_manage_roles.getScene().getWindow().hide();
        ao_btn_manage_roles.getScene().getWindow();
        
        Stage mainStage = new Stage();
        Scene scene = new Scene(root);
        mainStage.setScene(scene);
        mainStage.show();
        }
        else if ("ROLE_USER".equals(role) ){
              Parent root = FXMLLoader.load(getClass().getResource("UserDashboard.fxml"));
        ao_btn_manage_roles.getScene().getWindow().hide();
        ao_btn_manage_roles.getScene().getWindow();
        
        Stage mainStage = new Stage();
        Scene scene = new Scene(root);
        mainStage.setScene(scene);
        mainStage.show();
        }
    }

    @FXML
    private void signout200(ActionEvent event) throws IOException {
               ao_btn_manage_roles.getScene().getWindow().hide();
        ao_btn_manage_roles.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("test.fxml"));
        Stage mainStage = new Stage();
        Scene scene = new Scene(root);
        mainStage.setScene(scene);
        mainStage.show();
    }

        

}

      
    

