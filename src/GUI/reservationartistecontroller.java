/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import entities.Evenement;
import services.ResCrud;
import services.SalleCrud;
import utils.MyConnection;
import entities.Session;
import entities.res;
import entities.salle;
import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;
import services.Métier;

/**
 * FXML Controller class
 *
 * @author MLLE-BAHRI
 */
public class reservationartistecontroller implements Initializable {

    private Button btnRedirectDashboardReserver;
    @FXML
    private Button btnReserverArtiste;
    @FXML
    private TableView<salle> tableNb;
    private TableColumn<salle, Integer > colNum;
    private TableColumn<salle,String > colType;
    private TableColumn<salle, String> colStatu;
    private TextField tfReserverArtiste;
    private TableColumn<salle, Float> colPrix;
    private Label lbWarning;
    @FXML
    private TableColumn<salle, String> numtableNb;
    @FXML
    private TableColumn<salle, String> typetaableNb;
    @FXML
    private TableColumn<salle, String> statutableNb;
    @FXML
    private TableColumn<salle, String> prixtableNb;
    @FXML
    private Button ao_btn_dashboard;
    @FXML
    private Button ao_btn_manage_users;
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
         SalleCrud ecd = new SalleCrud();
         ObservableList EV =  ecd.afficherSalle();
         System.out.println(EV);
         numtableNb.setCellValueFactory(new PropertyValueFactory<salle, String>("num_salle"));
         typetaableNb.setCellValueFactory(new PropertyValueFactory<salle, String>("type"));
         statutableNb.setCellValueFactory(new PropertyValueFactory<salle, String>("statu"));
         prixtableNb.setCellValueFactory(new PropertyValueFactory<salle, String>("prix"));
         tableNb.setItems(EV);
       
    }    


    @FXML
    private void reserverArtiste(ActionEvent event) throws URISyntaxException, IOException {
      
       
          
             Notifications notificationBuilder = Notifications.create()
                    .title("Effectué")
                    .text("Pour Procéder Au Paiement Vous Serez Redirigé Vers Le Site Web")
                     .graphic(null)
                     .hideAfter(Duration.seconds(10))
                     .position(Pos.BASELINE_RIGHT);

             notificationBuilder.showConfirm();
             
             
               Desktop desk = Desktop.getDesktop();
        
        // now we enter our URL that we want to open in our
        // default browser
        desk.browse(new URI("http://127.0.0.1:8000/login"));
       
        
            
        
    }
    
    private String afficherParIdNom(String id){
       String nom = "fdf";
       Connection cnx = MyConnection.getInstance().getCnx();
       String req = "select * from users";
       Statement st;
        try {
            st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while (rs.next()){
                System.out.println(rs.getString(1));
                if (id.equals(rs.getString("id")) ){
                    System.out.println("if");
                    nom = rs.getString(3);
                    
                }
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
       
    return nom;
    }

    @FXML
    private void Godash(ActionEvent event) throws SQLException, IOException {
          int idCurrentUser = Integer.parseInt(Session.id);
        Session session = new Session();
        String roleArray = session.getRole(idCurrentUser);
        String role = roleArray.substring(2,roleArray.length()-2);
        System.err.println(role);
        
        
        if ("ROLE_ARTISTE".equals(role) ){
        Parent root = FXMLLoader.load(getClass().getResource("CCC.fxml"));
        ao_btn_dashboard.getScene().getWindow().hide();
        ao_btn_dashboard.getScene().getWindow();
        
        Stage mainStage = new Stage();
        Scene scene = new Scene(root);
        mainStage.setScene(scene);
        mainStage.show();
    }
        else if ("ROLE_PRESTATAIRE".equals(role)){
             Parent root = FXMLLoader.load(getClass().getResource("Prestataire.fxml"));
        ao_btn_dashboard.getScene().getWindow().hide();
        ao_btn_dashboard.getScene().getWindow();
        
        Stage mainStage = new Stage();
        Scene scene = new Scene(root);
        mainStage.setScene(scene);
        mainStage.show();
        }
        else if ("ROLE_SUPER_ADMIN".equals(role)){
        Parent root = FXMLLoader.load(getClass().getResource("AdminDashboard.fxml"));
        ao_btn_dashboard.getScene().getWindow().hide();
        ao_btn_dashboard.getScene().getWindow();
        
        Stage mainStage = new Stage();
        Scene scene = new Scene(root);
        mainStage.setScene(scene);
        mainStage.show();
        }
        else if ("ROLE_USER".equals(role) ){
              Parent root = FXMLLoader.load(getClass().getResource("UserDashboard.fxml"));
        ao_btn_dashboard.getScene().getWindow().hide();
        ao_btn_dashboard.getScene().getWindow();
        
        Stage mainStage = new Stage();
        Scene scene = new Scene(root);
        mainStage.setScene(scene);
        mainStage.show();
        }
    }


    @FXML
    private void reclamer(ActionEvent event) throws IOException {
               ao_btn_dashboard.getScene().getWindow().hide();
        ao_btn_dashboard.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("ReclamationFront.fxml"));
        Stage mainStage = new Stage();
        Scene scene = new Scene(root);
        mainStage.setScene(scene);
        mainStage.show();
    }

    @FXML
    private void signout114477(ActionEvent event) throws IOException {
               ao_btn_dashboard.getScene().getWindow().hide();
        ao_btn_dashboard.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("test.fxml"));
        Stage mainStage = new Stage();
        Scene scene = new Scene(root);
        mainStage.setScene(scene);
        mainStage.show();
    }
    
}
