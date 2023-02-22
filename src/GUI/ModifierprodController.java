/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import entities.ProduitPM;
import entities.Session;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
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
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import services.ProduitPMService;

/**
 * FXML Controller class
 *
 * @author elbootic.com
 */
public class ModifierprodController implements Initializable {
      ProduitPMService pmalek = new ProduitPMService();
    Interfaces p = new Interfaces();
     ProduitPM prodM = p.p;
    @FXML
    private TextField nom;
    @FXML
    private TextField ref;
    @FXML
    private TextField qu;
    @FXML
    private TextField ty;
    @FXML
    private TextField pri;
    @FXML
    private TextField da;
    @FXML
    private Button modifb;
    @FXML
    private Button retour1;
    @FXML
    private Label d;
    @FXML
    private Label exitNomerr;
    @FXML
    private Label error;
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
            exitNomerr.setVisible(false);
        nom.setText(prodM.getNomProd());
        ref.setText(Integer.toString(prodM.getReferenceP()));
        qu.setText(Integer.toString(prodM.getQuantiteP()));
        ty.setText(prodM.getType());
        pri.setText(Float.toString(prodM.getPrixPM()));
        da.setText(prodM.getDateAjout());
    if(nom.getText()==null){
           System.out.println("1");
       }
       if(nom.getText()==""){
           System.out.println("2");
       }
        if(nom.getText().equals("")){
           System.out.println("3");
       }
        
        error.setVisible(false);
   
    
}

    @FXML
    private void modif(ActionEvent event) throws IOException, SQLException {
         
        if(nom.getText().equals("") || ref.getText().equals("") || qu.getText().equals("") || ty.getText().equals("") || da.getText().equals("")){
           error.setText("Veillez remplir les champs");
           error.setVisible(true);
            return;
         
        }
        
        if ( !(isNumeric((ref.getText()))) || !(isNumeric(qu.getText())) || !(isNumeric(pri.getText())) ){
                error.setText("Veillez saisir des valeurs numeruiques");
                error.setVisible(true);
                return;
         }
        error.setVisible(false);
               modifb.setLayoutX(700);
                     String nom = this.nom.getText();
        int reference = Integer.parseInt(ref.getText());
        int quantite = Integer.parseInt(qu.getText());
        String type = ty.getText();
        float prix = Float.parseFloat(pri.getText());
        String date_ajout = da.getText();
                 System.out.println("modif");
         ProduitPM  produit = new ProduitPM(prodM.getIDProd(),nom,reference, quantite,type,prix,date_ajout);
      
         System.out.println(produit.getDateAjout());
             System.out.println(produit);
        pmalek.modifierProduit(produit,produit.getIDProd());
             modifb.getScene().getWindow().hide();
        Parent root = FXMLLoader.load(getClass().getResource("produitPMdetails.fxml"));
                Stage mainStage = new Stage();
                Scene scene = new Scene(root);
                mainStage.setScene(scene);
                mainStage.show();

        
  
    




       
    }

    @FXML
    private void retourr(ActionEvent event) throws IOException {
        retour1.getScene().getWindow().hide();
        Parent root = FXMLLoader.load(getClass().getResource("produitPMdetails.fxml"));
                Stage mainStage = new Stage();
                Scene scene = new Scene(root);
                mainStage.setScene(scene);
                mainStage.show();

    }


    @FXML
    private void modifdash(ActionEvent event) throws IOException, SQLException {
          int idCurrentUser = Integer.parseInt(Session.id);
        Session session = new Session();
        String roleArray = session.getRole(idCurrentUser);
        String role = roleArray.substring(2,roleArray.length()-2);
        System.err.println(role);
        
        
        if ("ROLE_ARTISTE".equals(role) ){
        Parent root = FXMLLoader.load(getClass().getResource("CCC.fxml"));
        retour1.getScene().getWindow().hide();
        retour1.getScene().getWindow();
        
        Stage mainStage = new Stage();
        Scene scene = new Scene(root);
        mainStage.setScene(scene);
        mainStage.show();

    }
        else if ("ROLE_PRESTATAIRE".equals(role)){
             Parent root = FXMLLoader.load(getClass().getResource("Prestataire.fxml"));
        retour1.getScene().getWindow().hide();
        retour1.getScene().getWindow();
        
        Stage mainStage = new Stage();
        Scene scene = new Scene(root);
        mainStage.setScene(scene);
        mainStage.show();
        }
        else if ("ROLE_SUPER_ADMIN".equals(role)){
        Parent root = FXMLLoader.load(getClass().getResource("AdminDashboard.fxml"));
        retour1.getScene().getWindow().hide();
        retour1.getScene().getWindow();
        
        Stage mainStage = new Stage();
        Scene scene = new Scene(root);
        mainStage.setScene(scene);
        mainStage.show();
        }
        else if ("ROLE_USER".equals(role) ){
              Parent root = FXMLLoader.load(getClass().getResource("UserDashboard.fxml"));
        retour1.getScene().getWindow().hide();
        retour1.getScene().getWindow();
        
        Stage mainStage = new Stage();
        Scene scene = new Scene(root);
        mainStage.setScene(scene);
        mainStage.show();
        }
    
    }
    
    
    public static boolean isNumeric(String str) { 
  try {  
    Double.parseDouble(str);  
    return true;
  } catch(NumberFormatException e){  
    return false;  
  }  
}

    @FXML
    private void signout1023214(ActionEvent event) throws IOException {
                 Parent root = FXMLLoader.load(getClass().getResource("test.fxml"));
        retour1.getScene().getWindow().hide();
        retour1.getScene().getWindow();
        
        Stage mainStage = new Stage();
        Scene scene = new Scene(root);
        mainStage.setScene(scene);
        mainStage.show();
    }

    }
