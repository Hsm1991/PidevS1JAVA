/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import entities.Evenement;
import entities.Ticket;
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
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.util.Duration;
import javax.mail.MessagingException;
import org.controlsfx.control.Notifications;
import services.CRUDTicket;
import services.EvenementCRUD;

/**
 * FXML Controller class
 *
 * @author User
 */
public class AjoutTicketController implements Initializable {

    @FXML
    private TextField TIKins;
    private TextField EVTIK;
    @FXML
    private Button TIKadd;
    @FXML
    private Button BackAdd;
    @FXML
    private ComboBox<String> EVT;
    @FXML
    private ComboBox<Evenement> GetAllEV;
    @FXML
    private Label errevvnom;
    @FXML
    private Label errnbrrrt;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        errnbrrrt.setVisible(false);
        errevvnom.setVisible(false);
         ObservableList<String> list = FXCollections.observableArrayList("1ère Classe","2ème Classe","3ème Classe");
        EVT.setItems(list);
         EvenementCRUD ecd = new EvenementCRUD();
         ObservableList<Evenement> OBL = ecd.afficherEvenemets();
         GetAllEV.getItems().addAll(OBL);
         
    }    

      @FXML
    private void AjouterTicketsH(ActionEvent event) throws SQLException {
     
        if(TIKins.getText().equals("")){
            errevvnom.setVisible(true);
        }
        else{
            
        CRUDTicket ktk = new CRUDTicket();
      int i=Integer.parseInt(TIKins.getText());
       int av = GetAllEV.getValue().getIdEvent();
       String strID =  EVT.getValue();
            System.out.println(strID);
       if (GetAllEV.getValue().getCapaciteEvent() - GetAllEV.getValue().getNbrTicketAchete() < i)
       {
          errnbrrrt.setVisible(true);
       }
       else {
       ktk.ajouterTiquetsForEvent2(av,i,strID);
       
       
       
                 Notifications notificationBuilder = Notifications.create()
                    .title("Effectué")
                    .text("Ajout Effectué Avec Sucées")
                     .graphic(null)
                     .hideAfter(Duration.seconds(10))
                     .position(Pos.BASELINE_RIGHT);

             notificationBuilder.showConfirm();
    }}
        }

    @FXML
    private void Arrrrr(ActionEvent event) throws IOException {
          BackAdd.getScene().getWindow().hide();
         Parent root = FXMLLoader.load(getClass().getResource("TicketsEv.fxml"));
         Stage mainStage = new Stage();
         Scene scene = new Scene(root);
         mainStage.setScene(scene);
         mainStage.show();} 
    }
    

