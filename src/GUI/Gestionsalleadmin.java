/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import entities.Session;
import services.SalleCrud;
import utils.MyConnection;
import entities.salle;
import java.io.IOException;
import static java.lang.Math.E;
import static java.lang.StrictMath.E;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Duration;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javaMailUtil.javaMail;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javax.activation.DataSource;
import javax.mail.MessagingException;
import org.controlsfx.control.Notifications;
import utils.SendMail;


/**
 * FXML Controller class
 *
 * @author MLLE-BAHRI
 */
public class Gestionsalleadmin implements Initializable {

    @FXML
    private AnchorPane anchorpane2Nb;
    @FXML
    private Button ajouterNb;
    @FXML
    private Button supprimerNb;
    @FXML
    private Button modifierNb;
    @FXML
    private Label TypeNb;
    @FXML
    private Label StatuNb;
    @FXML
    private Label prixNb;
    @FXML
    private ComboBox<String> type1Nb;
    @FXML
    private ComboBox<String> statu1Nb;
    @FXML
    private TextField prix1Nb;
    @FXML
    private AnchorPane anchorpaneNb;
    @FXML
    private TableView<salle> tableNb;
    private TableColumn<salle, Integer> idtableNb;
    @FXML
    private TableColumn<salle, String> numtableNb;
    @FXML
    private TableColumn<salle, String> typetaableNb;
    @FXML
    private TableColumn<salle, String> statutableNb;
    @FXML
    private TableColumn<salle, String> prixtableNb;
    @FXML
    private TextField recherche1Nb;
    SalleCrud sc = new SalleCrud();
    salle s = new salle();
    private String[] type ={"couverte","en plain air"};
    private String[] statu={"Disponible","Non disponible"};
    @FXML
    private Label ctlNb2;
    @FXML
    private Label ctlNb3;
    @FXML
    private Label ctlNb4;
    @FXML
    private Label lbWarningSalle;
    @FXML
    private Label lbWarningCorrect;
    @FXML
    private Label lbWarningSelect;
    private Button ARRNADA;
    @FXML
    private Button ao_btn_dashboard;
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
        type1Nb.getItems().addAll(type);
        statu1Nb.getItems().addAll(statu);
        ObservableList<String> list = FXCollections.observableArrayList("En plain air","Couverte");
         type1Nb.setItems(list);
         ObservableList<String> list1 = FXCollections.observableArrayList("Disponible","Non Disponible");
         statu1Nb.setItems(list1);
         
         salle s = tableNb.getSelectionModel().getSelectedItem();
         
         
         
         //////////////////////////REMPLISSAGE TABLE VIEW////////////////////////////////
         SalleCrud ecd = new SalleCrud();
         ObservableList EV =  ecd.afficherSalle();
         System.out.println(EV);
         numtableNb.setCellValueFactory(new PropertyValueFactory<salle, String>("num_salle"));
         typetaableNb.setCellValueFactory(new PropertyValueFactory<salle, String>("type"));
         statutableNb.setCellValueFactory(new PropertyValueFactory<salle, String>("statu"));
         prixtableNb.setCellValueFactory(new PropertyValueFactory<salle, String>("prix"));
         tableNb.setItems(EV);

        
        FilteredList<salle> filteredData = new FilteredList<>(EV, b -> true);
        recherche1Nb.textProperty().addListener((observable, oldValue, newValue) -> {
          filteredData.setPredicate(cp-> {
              if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
              String lowerCaseFilter = newValue.toLowerCase();
              if (cp.getStatu().toLowerCase().contains(lowerCaseFilter) ) {
                    return true;                                                                             // Filter matches first name.
                    
                } else if (cp.getType().toLowerCase().contains(lowerCaseFilter) ){
                    return true;                                                                                    // Filter matches date deb.
                } 
                return false;
                
                
       });
    });
        SortedList<salle> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(tableNb.comparatorProperty());
        tableNb.setItems(sortedData);
    
        
    }   
   /* /////////////////////////RECHERCHE////////////////////////
 
         FilteredList<salle> filteredData = new FilteredList<>(EV, b -> true);
         //2. Set the filter Predicate whenever the filter changes.
         recherche1Nb.textProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
         filteredData.setPredicate((E Even) -> {
         // If filter text is empty, display all persons.
         if (newValue == null || newValue.isEmpty()) {
         return true;
         }
         // Compare first name and last name of every person with filter text.
         String lowerCaseFilter = newValue.toLowerCase();
         if (Even.getID_salle.toLowerCase().contains(lowerCaseFilter)) {
         return true; // Filter matches last name.
         }
         else if (Even.getNum_salle.toLowerCase().contains(lowerCaseFilter)) {
         return true;}
         else if (Even.getDateDebutEvent().toLowerCase().contains(lowerCaseFilter)) {
         return true;}
         else if (String.valueOf(Even.getIdEvent()).indexOf(lowerCaseFilter)!=-1){
         return true;}
         else if (String.valueOf(Even.getCapaciteEvent()).indexOf(lowerCaseFilter)!=-1){
         return true;}
         else return String.valueOf(Even.getCategorieEvent()).contains(lowerCaseFilter);
         });
         });
         SortedList<salle> sortedData = new SortedList<>(filteredData);
         // 4. Bind the SortedList comparator to the TableView comparator.
         // 	  Otherwise, sorting the TableView would have no effect.
         sortedData.comparatorProperty().bind(tableNb.comparatorProperty());
         // 5. Add sorted (and filtered) data to the table.
         tableNb.setItems(sortedData);
    }*/
    


    
    ////////////////////ajouterrr

   @FXML
    private void add_Salle(ActionEvent event) throws MessagingException {
        
       if ( type1Nb.getValue() != null && statu1Nb.getValue() != null && 
               !prix1Nb.getText().isEmpty()){
        
        
        try{
              
            String type = type1Nb.getValue(); 
            String service = statu1Nb.getValue(); 
            String prix = prix1Nb.getText();
            salle s = new salle(type,service,Float.parseFloat(prix));
            SalleCrud sc = new SalleCrud();
            System.out.println("adding");
            sc.AjouterSalle(s);
            System.out.println("added");
            //repopuler tableau
            SalleCrud ecd = new SalleCrud();
            ObservableList EV =  ecd.afficherSalle();
            tableNb.setItems(EV);
            
            
            
            
            
            
            
            
            
            
            
              
                       Notifications notificationBuilder;
                       notificationBuilder = Notifications.create()
                               .title("Effectué")
                               .text("Salle Ajoutée Avec Succés")
                               .graphic(null)
                               .hideAfter(javafx.util.Duration.seconds(10))
                               .position(Pos.BASELINE_RIGHT);
                       notificationBuilder.showConfirm();
            
            lbWarningSalle.setVisible(false);
            lbWarningCorrect.setVisible(false);
            lbWarningSelect.setVisible(false);
        }catch(Exception ex){
            lbWarningCorrect.setVisible(true);
        }
            
            
       }
       else{
           
           
           lbWarningSalle.setVisible(true);
       }
       
      /* String mail = "nada.bahri@esprit.tn";
       String message = "salle ajouté";
       SendMail m = new javaMail();
       m.sendMail(mail, "notification",message);
       */
       
    }
    
    
    
    
    
/////SUPRIMMMMMMMMMMMMMMER
    @FXML
    private void deletesalle(ActionEvent event) {
        try{
        salle s = tableNb.getSelectionModel().getSelectedItem();
        //String num = num1Nb.getText();  
        SalleCrud sc = new SalleCrud();
        sc.supprimerSalle(s);
        SalleCrud ecd = new SalleCrud();
            ObservableList EV =  ecd.afficherSalle();
            tableNb.setItems(EV);
            lbWarningSelect.setVisible(false);
                    }catch(Exception ex){
                        lbWarningSelect.setVisible(true);
                    }
        
        
        
        
        
        
          
                       Notifications notificationBuilder;
                       notificationBuilder = Notifications.create()
                               .title("Effectué")
                               .text("Salle Supprimée Correctement")
                               .graphic(null)
                               .hideAfter(javafx.util.Duration.seconds(10))
                               .position(Pos.BASELINE_RIGHT);
                       notificationBuilder.showConfirm();
        
        
       ////////////////////////notiication 
       /* TNotifications notificationBuilder = Notifications.create()
                    .title("Effectué")
                    .text("Ticket Supprimée Correctement")
                     .graphic(null)
                     .hideAfter(Duration.seconds(10))
                     .position(Pos.BASELINE_RIGHT);

             notificationBuilder.showConfirm();
    */
    }

       
        
        
    

    @FXML
    private void updateSalle(ActionEvent event) {
        if (tableNb.getSelectionModel().getSelectedItem() == null){
            lbWarningSelect.setVisible(true);
        }
        else{
            int id = tableNb.getSelectionModel().getSelectedItem().getNum_salle();
            System.out.println(id);
            lbWarningSelect.setVisible(false);
            if (  type1Nb.getValue() != null && statu1Nb.getValue() != null && 
               !prix1Nb.getText().isEmpty()){
                
                try{
                    lbWarningCorrect.setVisible(false);
                     
                    String type = type1Nb.getValue(); 
                    String service = statu1Nb.getValue(); 
                    String prix = prix1Nb.getText();
                    salle s = new salle(type,service,Integer.parseInt(prix));
                    SalleCrud sc = new SalleCrud();
                    sc.ModifierSalle(s,id);
                    SalleCrud ecd = new SalleCrud();
                    
                    ObservableList EV =  FXCollections.observableArrayList(ecd.afficherSalle());
                    System.out.println(EV);
                    tableNb.setItems(EV);
                    ObservableList<salle>  list = FXCollections.observableArrayList();
                    lbWarningSalle.setVisible(false);
                    lbWarningCorrect.setVisible(false);
                    lbWarningSelect.setVisible(false);
                    
                    
                    
                    
                    
                    
                    
                    
                    
                      
                       Notifications notificationBuilder;
                       notificationBuilder = Notifications.create()
                               .title("Effectué")
                               .text("Salle Modifiée Avec Succés")
                               .graphic(null)
                               .hideAfter(javafx.util.Duration.seconds(10))
                               .position(Pos.BASELINE_RIGHT);
                       notificationBuilder.showConfirm();
                }catch(Exception ex){
                    lbWarningCorrect.setVisible(true);
                }
        /*
            try {
            //
            Connection cnx = MyConnection.getInstance().getCnx();
            ResultSet rs = cnx.createStatement().executeQuery("SELECT num_salle , type , statu , prix FROM salle");
            while(rs.next())
            {
                 list.add(new salle(rs.getInt(1), rs.getString(2), rs.getString(3),rs.getFloat(4)));
            }
        } catch (SQLException ex) {
            Logger.getLogger(Window1.class.getName()).log(Level.SEVERE, null, ex);
        }
       
        numtableNb.setCellValueFactory(new PropertyValueFactory<>("num_salle"));
        typetaableNb.setCellValueFactory(new PropertyValueFactory<>("type"));
        statutableNb.setCellValueFactory(new PropertyValueFactory<>("statu"));
        prixtableNb.setCellValueFactory(new PropertyValueFactory<>("prix"));
        



        
        tableNb.setItems(list);
           */         
            }
            else {
                lbWarningSalle.setVisible(true);
            }
            
            
            
        
        }
        
    }   
/*
    @FXML
    private void getSele(MouseEvent event) {
        
        int index = tableNb.getSelectionModel().getSelectedIndex();
        if(index<= -1)
        {
            return;
        }
        //num1Nb.setText(numtableNb.getCellData(index).toString());
       // prix1Nb.setText(prixtableNb.getCellData(index).toString());
    }
   */  

    @FXML
    private void redirectresnada(ActionEvent event) throws IOException {
         ajouterNb.getScene().getWindow().hide();
         Parent root = FXMLLoader.load(getClass().getResource("gestionreservationadmin.fxml"));
         Stage mainStage = new Stage();
         Scene scene = new Scene(root);
         mainStage.setScene(scene);
         mainStage.show();
}        


    @FXML
    private void dashboards(ActionEvent event) throws IOException, SQLException {
         int idCurrentUser = Integer.parseInt(Session.id);
        Session session = new Session();
        String roleArray = session.getRole(idCurrentUser);
        String role = roleArray.substring(2,roleArray.length()-2);
        System.err.println(role);
        
        
        if ("ROLE_ARTISTE".equals(role) ){
        Parent root = FXMLLoader.load(getClass().getResource("CCC.fxml"));
        ajouterNb.getScene().getWindow().hide();
        ajouterNb.getScene().getWindow();
        
        Stage mainStage = new Stage();
        Scene scene = new Scene(root);
        mainStage.setScene(scene);
        mainStage.show();

    }
        else if ("ROLE_PRESTATAIRE".equals(role)){
             Parent root = FXMLLoader.load(getClass().getResource("Prestataire.fxml"));
        ajouterNb.getScene().getWindow().hide();
        ajouterNb.getScene().getWindow();
        
        Stage mainStage = new Stage();
        Scene scene = new Scene(root);
        mainStage.setScene(scene);
        mainStage.show();
        }
        else if ("ROLE_SUPER_ADMIN".equals(role)){
        Parent root = FXMLLoader.load(getClass().getResource("AdminDashboard.fxml"));
        ajouterNb.getScene().getWindow().hide();
        ajouterNb.getScene().getWindow();
        
        Stage mainStage = new Stage();
        Scene scene = new Scene(root);
        mainStage.setScene(scene);
        mainStage.show();
        }
        else if ("ROLE_USER".equals(role) ){
              Parent root = FXMLLoader.load(getClass().getResource("UserDashboard.fxml"));
        ajouterNb.getScene().getWindow().hide();
        ajouterNb.getScene().getWindow();
        
        Stage mainStage = new Stage();
        Scene scene = new Scene(root);
        mainStage.setScene(scene);
        mainStage.show();
        }
    }

    @FXML
    private void signout20221(ActionEvent event) throws IOException {
           Parent root = FXMLLoader.load(getClass().getResource("test.fxml"));
        ajouterNb.getScene().getWindow().hide();
        ajouterNb.getScene().getWindow();
        
        Stage mainStage = new Stage();
        Scene scene = new Scene(root);
        mainStage.setScene(scene);
        mainStage.show();
    }
    }

            
            
            
            
            
            
        
    
    
    
    
    
    







