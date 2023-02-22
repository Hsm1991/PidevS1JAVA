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
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.DragEvent;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseDragEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import services.ProduitPMService;

/**
 * FXML Controller class
 *
 * @author elbootic.com
 */

public class AjoutProduitController implements Initializable {

    @FXML
    private TextField PMreference;
    @FXML
    private TextField PMnom;
    @FXML
    private TextField PMquantite;
    @FXML
    private TextField PMtype;
    @FXML
    private TextField PMprix;
    @FXML
    private Button PMajouter;
    @FXML
    private Label text;
    private TextField PMdateAJOUT;
    @FXML
    private Label erreur_nom;
    @FXML
    private Label d;
    @FXML
    private Label erreur_ref;
    @FXML
    private Label erreur_qu;
    @FXML
    private Label erreur_ty;
    @FXML
    private Label eurreur_da;
    @FXML
    private Label erreur_prix;
    @FXML
    private Button retourbtn;
    @FXML
    private Label erreur_nom1;
    @FXML
    private Button retourbtn1;
    @FXML
    private Label errPaseInt;
    @FXML
    private DatePicker datePrrr;
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
    public void initialize (URL url, ResourceBundle rb) {
                errPaseInt.setVisible(false);
        erreur_nom1.setVisible(false);
        erreur_nom.setVisible(false);
        erreur_ref.setVisible(false);
        erreur_qu.setVisible(false);
        erreur_ty.setVisible(false);
        eurreur_da.setVisible(false);
        erreur_prix.setVisible(false);
    //  if (PMnom.getText().equals()){
          
          erreur_nom.setVisible(false);
    //  }


    }    

    @FXML
    private void Ajoutproduit(ActionEvent event) throws SQLException, IOException {
        int x = 0;
                errPaseInt.setVisible(false);
        erreur_nom1.setVisible(false);
          erreur_nom.setVisible(false);
        erreur_ref.setVisible(false);
        erreur_qu.setVisible(false);
        erreur_ty.setVisible(false);
        eurreur_da.setVisible(false);
        erreur_prix.setVisible(false);
         ProduitPMService tess = new ProduitPMService();
        /* if(PMnom.getText().equals("") || PMreference.getText().equals("") || PMquantite.getText().equals("") ||  PMtype.getText().equals("") && PMdateAJOUT.getText().equals("")){
              erreur_nom.setVisible(true);
        erreur_ref.setVisible(true);
        erreur_qu.setVisible(true);
        erreur_ty.setVisible(true);
        eurreur_da.setVisible(true);
        erreur_prix.setVisible(true);
         } */
       // if (PMnom.getText().length()==0)
       // {
         //   PMnom.setStyle("-fx-border-color: red ; -fx-border-width: 2px ;");
           // new animatefx.animation.shake(PMnom).play();
       //}
       /// else{
           // PMnom.setStyle(null);
      //  }
      
        if(PMnom.getText().equals("")){
            erreur_nom.setVisible(true);
            return;
          
        }
         if (PMreference.getText().equals("")){
           erreur_ref.setVisible(true);
           return;
        }
           
      
                    if(PMquantite.getText().equals("")){
           erreur_qu.setVisible(true);
           return;
        }
                    if(PMtype.getText().equals("")){
           erreur_ty.setVisible(true);
           return;
        }  
                    if(PMprix.getText().equals("")){
           erreur_prix.setVisible(true);
           return;
        }
                    if (datePrrr.getValue().toString().equals("")){
                        eurreur_da.setVisible(true);
                        return;
                        
                    }

/*            if(PMnom.getText().equals("") && PMreference.getText().equals("") &&PMquantite.getText().equals("") && PMtype.getText().equals("") && PMdateAJOUT.getText().equals("")){
            if(PMajouter.getLayoutX()==14){
               PMajouter.setLayoutX(30);
           } else{
              PMajouter.getLayoutX();

               PMajouter.setLayoutX(14); */


              /* PMajouter.getScene().getWindow().hide();
        Parent root = FXMLLoader.load(getClass().getResource("ajoutProduit.fxml"));
                Stage mainStage = new Stage();
                Scene scene = new Scene(root);
                mainStage.setScene(scene);
                mainStage.show();
              
             }    

         
     }else{    
         */
                ProduitPM produit = new ProduitPM();
               PMajouter.setLayoutX(14);
                     String nom = PMnom.getText();
                     try {
                    
             
        int reference = Integer.parseInt(PMreference.getText());
        int quantite = Integer.parseInt(PMquantite.getText());
        String type = PMtype.getText();
        float prix = Float.parseFloat(PMprix.getText());
       //String date = ""+datePrrr.getValue().getYear()+"-"+datePrrr.getValue().getMonthValue()+"-"+datePrrr.getValue().getDayOfMonth();
       String date = datePrrr.getValue().toString();
         ProduitPM  produit1 = new ProduitPM(nom,reference, quantite,type,prix,date);
         produit = produit1;
                     } catch (Exception e) {
                         x=1;
            errPaseInt.setVisible(true);
                }
                if (x == 0) {
                    
                
        ProduitPMService pmalek = new ProduitPMService();
         System.out.println(produit.getDateAjout());
                    System.out.println(produit);
        pmalek.ajouterProduit(produit);
        text.setText("tttt");
               PMajouter.getScene().getWindow().hide();
        Parent root = FXMLLoader.load(getClass().getResource("produitPMdetails.fxml"));
                Stage mainStage = new Stage();
                Scene scene = new Scene(root);
                mainStage.setScene(scene);
                mainStage.show();
              
              
  
        
       } 
  
  



      //  }
       
    }

    @FXML
    private void retour(ActionEvent event) throws IOException {
          retourbtn.getScene().getWindow().hide();
        Parent root = FXMLLoader.load(getClass().getResource("produitPMdetails.fxml"));
                Stage mainStage = new Stage();
                Scene scene = new Scene(root);
                mainStage.setScene(scene);
                mainStage.show();


    }

    @FXML
    private void actualiser(ActionEvent event) {
        PMnom.setText("");
        PMdateAJOUT.setText("");
        PMprix.setText("");
        PMreference.setText("");
        PMquantite.setText("");
        PMtype.setText("");
        erreur_nom1.setVisible(false);
        erreur_nom.setVisible(false);
        erreur_ref.setVisible(false);
        erreur_qu.setVisible(false);
        erreur_ty.setVisible(false);
        eurreur_da.setVisible(false);
        erreur_prix.setVisible(false);
      
        
   }
   /* public void keyTyped(KeyEvent k)  
 {  
  if (! Character.isDigit(k.get()))  
  {  
   k.consume();
  }    
 }  
*/

    @FXML
    private void godash100(ActionEvent event) throws IOException, SQLException {
            int idCurrentUser = Integer.parseInt(Session.id);
        Session session = new Session();
        String roleArray = session.getRole(idCurrentUser);
        String role = roleArray.substring(2,roleArray.length()-2);
        System.err.println(role);
        
        
        if ("ROLE_ARTISTE".equals(role) ){
        Parent root = FXMLLoader.load(getClass().getResource("CCC.fxml"));
        erreur_prix.getScene().getWindow().hide();
        erreur_prix.getScene().getWindow();
        
        Stage mainStage = new Stage();
        Scene scene = new Scene(root);
        mainStage.setScene(scene);
        mainStage.show();

    }
        else if ("ROLE_PRESTATAIRE".equals(role)){
             Parent root = FXMLLoader.load(getClass().getResource("Prestataire.fxml"));
        erreur_prix.getScene().getWindow().hide();
        erreur_prix.getScene().getWindow();
        
        Stage mainStage = new Stage();
        Scene scene = new Scene(root);
        mainStage.setScene(scene);
        mainStage.show();
        }
        else if ("ROLE_SUPER_ADMIN".equals(role)){
        Parent root = FXMLLoader.load(getClass().getResource("AdminDashboard.fxml"));
        erreur_prix.getScene().getWindow().hide();
        erreur_prix.getScene().getWindow();
        
        Stage mainStage = new Stage();
        Scene scene = new Scene(root);
        mainStage.setScene(scene);
        mainStage.show();
        }
        else if ("ROLE_USER".equals(role) ){
              Parent root = FXMLLoader.load(getClass().getResource("UserDashboard.fxml"));
        erreur_prix.getScene().getWindow().hide();
        erreur_prix.getScene().getWindow();
        
        Stage mainStage = new Stage();
        Scene scene = new Scene(root);
        mainStage.setScene(scene);
        mainStage.show();
        }
    
    }

    @FXML
    private void signout(ActionEvent event) throws IOException {
                 Parent root = FXMLLoader.load(getClass().getResource("test.fxml"));
        erreur_prix.getScene().getWindow().hide();
        erreur_prix.getScene().getWindow();
        
        Stage mainStage = new Stage();
        Scene scene = new Scene(root);
        mainStage.setScene(scene);
        mainStage.show();
        }
    }

    
    

