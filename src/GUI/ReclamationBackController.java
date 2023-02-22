/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import entities.Reclamation;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import services.ReclamationCRUD;

/**
 * FXML Controller class
 *
 * @author User
 */
public class ReclamationBackController implements Initializable {

    private Circle circle_image_m;
    @FXML
    private TableView<Reclamation> tree_artiste;
    @FXML
    private TableColumn<Reclamation, Integer> id;
    @FXML
    private TableColumn<Reclamation, Integer> iduser;
    @FXML
    private TableColumn<Reclamation, String> desc;
    @FXML
    private TableColumn<Reclamation, String> date;
    @FXML
    private TableColumn<Reclamation, String> selon;
    @FXML
    private TableColumn<Reclamation, String> mail;
    @FXML
    private TextField champ_recherche_m;
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
       ReclamationCRUD crud = new ReclamationCRUD();
        
        ObservableList<Reclamation> obList = FXCollections.observableArrayList(crud.afficherReclamationAll());
        
        id.setCellValueFactory(new PropertyValueFactory<Reclamation , Integer>("id"));
        iduser.setCellValueFactory(new PropertyValueFactory<Reclamation , Integer>("iduser"));
        desc.setCellValueFactory(new PropertyValueFactory<Reclamation , String>("description"));
        date.setCellValueFactory(new PropertyValueFactory<Reclamation , String>("recdate"));
        selon.setCellValueFactory(new PropertyValueFactory<Reclamation , String>("selon"));
        mail.setCellValueFactory(new PropertyValueFactory<Reclamation , String>("mail"));
        
        tree_artiste.setItems(obList);
    }    


    @FXML
    private void dashboard(ActionEvent event) throws IOException, SQLException {
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
    private void selectimage1(MouseEvent event) {
    }

    @FXML
    private void signout110(ActionEvent event) throws IOException {
            Parent root = FXMLLoader.load(getClass().getResource("test.fxml"));
        ao_btn_dashboard.getScene().getWindow().hide();
        ao_btn_dashboard.getScene().getWindow();
        
        Stage mainStage = new Stage();
        Scene scene = new Scene(root);
        mainStage.setScene(scene);
        mainStage.show();
        }
    }

  
    

