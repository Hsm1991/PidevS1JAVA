/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import entities.Evenement;
import entities.Session;
import java.awt.Event;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import static java.lang.Math.E;
import java.net.URL;
import java.util.Date;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import java.time.LocalDate;

import java.time.LocalDateTime;

import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

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
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import javax.mail.MessagingException;
import services.EvenementCRUD;
import javafx.scene.image.Image;

import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import org.controlsfx.control.Notifications;
import services.GetData212;


/**
 * FXML Controller class
 *
 * @author User
 */
public class MainController implements Initializable {
     Evenement sss = new Evenement();

    @FXML
    private TextField EventNa;
    @FXML
    private TextField EventAdrr;
    @FXML
    private TextField EventCap;
    @FXML
    private TextField EventTick;
    @FXML
    private ComboBox<String> EventTyp;
    @FXML
    private ComboBox<String> EventCat;
    @FXML
    private TextField PrixEntEV;
    @FXML
    private TableColumn<Evenement, Integer> idEvent;
    @FXML
    private TableColumn<Evenement, String> NomEvent;
    @FXML
    private TextField RecheV;
    @FXML
    private Button Sev1;
    @FXML
    private Button AddEvenement1;
    @FXML
    private Button ModEvent1;
    @FXML
    private Button SuppEvent1;
    private Button TIKAJOUT1;
    @FXML
    private TableColumn<Evenement, String> AdresseEvent;
    @FXML
    private TableColumn<Evenement, String> CapaciteEvent;
    @FXML
    private TableColumn<Evenement, String> NbrTicketsAcheté;
    @FXML
    private TableColumn<Evenement, String> DateDebutEvent;
    @FXML
    private TableColumn<Evenement, String> DateFinEvent;
    @FXML
    private TableColumn<Evenement, String> TypeEvent;
    @FXML
    private TableColumn<Evenement, String> CategorieEvent;
    @FXML
    private TableColumn<Evenement, String> PrixEntrée;
    @FXML
    private TableView<Evenement> EventTable;
    @FXML
    private Label errn;
    @FXML
    private Label erradrs;
    @FXML
    private Label errcp;
    @FXML
    private Label errtik;
    @FXML
    private Label errpric;
    @FXML
    private DatePicker DD;
    @FXML
    private DatePicker DF;
    @FXML
    private Spinner<Integer> SP3;
    @FXML
    private Spinner<Integer> SP4;
    @FXML
    private Spinner<Integer> SP1;
    @FXML
    private Spinner<Integer> SP2;
    @FXML
    private Label hh1;
    private Button MINIMISER;
    private Button Décccc;
    private FontAwesomeIcon barsbtn12;
    private AnchorPane NavForm321;
    @FXML
    private ImageView EventsDispo;
    private Button GOdas;
    
    private Image image1;
    @FXML
    private Label disponibl;
    private Button AJIMG;
    @FXML
    private AnchorPane ownerWindow;
    @FXML
    private Button importimg1;
    @FXML
    private Label errn1;
    @FXML
    private Label errn11;
    @FXML
    private Label errn12;
    @FXML
    private Button clear2022;
    Timestamp abc;
    Timestamp abc2;
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
        
    
        ////////////////APPEL VALEURS SPIN BUTTONS////////////////
        
        purchaseshowValue();
        purchaseshowValue1();
        purchaseshowValue2();
        purchaseshowValue3();


        
         ///////////////////CONTROLE DE SAISIE///////////////////////////////////////////
        errn.setVisible(false);
        erradrs.setVisible(false);
        errcp.setVisible(false);
        errtik.setVisible(false);
        errpric.setVisible(false);
        errn1.setVisible(false);
        errn12.setVisible(false);
        errn11.setVisible(false);
//////////////////////////REMPLISSAGE COMBOBOX///////////////////////////////
         ObservableList<String> list = FXCollections.observableArrayList("Présentiel","Distanciel");
         EventTyp.setItems(list);
         ObservableList<String> list1 = FXCollections.observableArrayList("Théatral","Musical","Peinture");
         EventCat.setItems(list1);
           //////////////////////////REMPLISSAGE TABLE VIEW////////////////////////////////
         EvenementCRUD ecd = new EvenementCRUD();
         ObservableList EV =  ecd.afficherEvenemets();
         
         idEvent.setCellValueFactory(new PropertyValueFactory<Evenement, Integer>("idEvent"));
         NomEvent.setCellValueFactory(new PropertyValueFactory<Evenement, String>("NomEvent"));
         AdresseEvent.setCellValueFactory(new PropertyValueFactory<Evenement, String>("AdresseEvent"));
         CapaciteEvent.setCellValueFactory(new PropertyValueFactory<Evenement, String>("CapaciteEvent"));
         NbrTicketsAcheté.setCellValueFactory(new PropertyValueFactory<Evenement, String>("nbrTicketAchete"));
         DateDebutEvent.setCellValueFactory(new PropertyValueFactory<Evenement,String>("DateDebutEvent"));
         DateFinEvent.setCellValueFactory(new PropertyValueFactory<Evenement,String>("DateFinEvent"));
         TypeEvent.setCellValueFactory(new PropertyValueFactory<Evenement,String>("TypeEvent"));
         CategorieEvent.setCellValueFactory(new PropertyValueFactory<Evenement,String>("CategorieEvent"));
         PrixEntrée.setCellValueFactory(new PropertyValueFactory<Evenement,String>("PrixEntre"));
         EventTable.setItems(EV);
           /////////////////////////RECHERCHE////////////////////////
         FilteredList<Evenement> filteredData = new FilteredList<>(EV, b -> true);
         //2. Set the filter Predicate whenever the filter changes.
         RecheV.textProperty().addListener((observable, oldValue, newValue) -> {
         filteredData.setPredicate(Even -> {
         // If filter text is empty, display all persons.
         if (newValue == null || newValue.isEmpty()) {
         return true;
         }
         // Compare first name and last name of every person with filter text.
         String lowerCaseFilter = newValue.toLowerCase();
         if (Even.getNomEvent().toLowerCase().contains(lowerCaseFilter)) {
         return true; // Filter matches last name.
         }
         else if (Even.getAdresseEvent().toLowerCase().contains(lowerCaseFilter)) {
         return true;}
         
         else if (String.valueOf(Even.getIdEvent()).indexOf(lowerCaseFilter)!=-1){
         return true;}
         else if (String.valueOf(Even.getCapaciteEvent()).indexOf(lowerCaseFilter)!=-1){
         return true;}
         else return String.valueOf(Even.getCategorieEvent()).contains(lowerCaseFilter);
         });
         });
         SortedList<Evenement> sortedData = new SortedList<>(filteredData);
         // 4. Bind the SortedList comparator to the TableView comparator.
         // 	  Otherwise, sorting the TableView would have no effect.
         sortedData.comparatorProperty().bind(EventTable.comparatorProperty());
         // 5. Add sorted (and filtered) data to the table.
         EventTable.setItems(sortedData);
    }   
    
    /////////////////////////////SPIN BUTTON SETTINGS/////////////////////
            private SpinnerValueFactory<Integer> spinner;

                public void purchaseshowValue(){
                spinner = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 23, 0);
                SP1.setValueFactory(spinner);
                }
                private int hr;
    @FXML
                public void SP1(){
                    hr = SP1.getValue();
                }

                
                private SpinnerValueFactory<Integer> spinner1;

                public void purchaseshowValue1(){
                spinner1 = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 59, 0);
                SP2.setValueFactory(spinner1);
                }
                private int hr1;
    @FXML
                public void SP2(){
                    hr1 = SP2.getValue();
                }
                
                   private SpinnerValueFactory<Integer> spinner4;

                public void purchaseshowValue2(){
                spinner4 = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 59, 0);
                SP4.setValueFactory(spinner4);
                }
                private int hr2;
    @FXML
                public void SP4(){
                    hr1 = SP4.getValue();
                }
                
                
                
                
                  private SpinnerValueFactory<Integer> spinner3;
                public void purchaseshowValue3(){
                spinner3 = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 23, 0);
                SP3.setValueFactory(spinner3);
                }
                private int hr3;
    @FXML
                public void SP3(){
                    hr3 = SP3.getValue();
                }
/////////////////////////////////////////////////////////////////////////////////////////////////

    @FXML
    private void AddEvent(ActionEvent event) throws MessagingException, SQLException {
        errn.setVisible(false);
        erradrs.setVisible(false);
        errcp.setVisible(false);
        errtik.setVisible(false);
        errpric.setVisible(false);
        errn1.setVisible(false);
        errn12.setVisible(false);
        Evenement e1 = new Evenement();
        
        
        ////////////////////CONTROLE DE SAISIE//////////////////
        int x = 1;
              EvenementCRUD ecd = new EvenementCRUD();
        if (ecd.testNom(EventNa.getText()) == 1)
        {
            x = 0;
        errn1.setVisible(true);
        }
         if(EventNa.getText().equals("")){
            errn.setVisible(true);
             x = 0;
        }
         if(EventAdrr.getText().equals("")){
           erradrs.setVisible(true);
            x = 0;
        }
           if(EventCap.getText().equals("")){
           errcp.setVisible(true);
            x = 0;
        }
             if(EventTick.getText().equals("")){
           errtik.setVisible(true);
            x = 0;
        }  

         if(x == 1){
          
               //////////////////GET DATE VALUES///////////////////
               int est1 = SP1.getValue();
               int est2 = SP2.getValue();
 String hour1 = String.valueOf('0');
               String min1 =String.valueOf(0);
               if (est1<10){
                       hour1= '0' + String.valueOf(est1) ;
               }
               else{
                   hour1 = String.valueOf(est1);
               }
                if (est2<10){
                       min1 = '0' + String.valueOf(est2) ;
               }
                else{
                    min1 = String.valueOf(est2);
                }
              
              String hh= String.valueOf(est1);
             String mm= String.valueOf(est2);
              String dd =(DD.getValue()).toString();
              String est = dd.concat(" ").concat(hour1).concat(":").concat(min1).concat(":").concat("00");
              // SimpleDateFormat sf = new SimpleDateFormat("dd-MM-yyyy HH:mm");
              System.out.println(est);
                             abc = Timestamp.valueOf(est);
                             

              //abc= (Timestamp) sf.parse(est); }
                    
                       
                   
               
                   
               //////////////////GET DATE VALUES///////////////////
               int est3 = SP3.getValue();
               int est4 = SP4.getValue();
               String hour = String.valueOf('0');
               String min =String.valueOf(0);
               if (est3<10){
                       hour= '0' + String.valueOf(est3) ;
               }
               else{
                   hour = String.valueOf(est3);
               }
                if (est4<10){
                       min = '0' + String.valueOf(est4) ;
               }
                else{
                    min = String.valueOf(est4);
                }
              
              
              String dd1 =(DF.getValue()).toString();
              
               String est8 = dd1.concat(" ").concat(hour).concat(":").concat(min).concat(":").concat("00");
               // String est8 =(DF.getValue().atTime(est3, est4, 00)).toString();
              //abc2= (Timestamp) sf.parse(est); 
                     abc2 = Timestamp.valueOf(est8);
                     
                       
                   
             
               /////////////////////////////////////////////////////////////////////////////////////////////
               try{
                   Evenement E = new Evenement(EventNa.getText(),EventAdrr.getText(),Integer.parseInt(EventCap.getText()),Integer.parseInt(EventTick.getText()),abc,abc2,EventTyp.getValue(),EventCat.getValue(),
                           Float.valueOf(PrixEntEV.getText()));
                   e1 = E;
               }
               catch(Exception ed)
               {
                   errn11.setVisible(true);
                   x= 0;
               }
               if(x == 1){
                   try{
                       ecd.ajouterEvenement(e1);
                       
                       
                       
                       
                       
                       
                       
                       
                       
                       
                       Notifications notificationBuilder;
                       notificationBuilder = Notifications.create()
                               .title("Effectué")
                               .text("Evènement Ajouté Avec Succés")
                               .graphic(null)
                               .hideAfter(Duration.seconds(10))
                               .position(Pos.BASELINE_RIGHT);
                       notificationBuilder.showConfirm();
                       errn.setVisible(false);
                       erradrs.setVisible(false);
                       errcp.setVisible(false);
                       errtik.setVisible(false);
                       errpric.setVisible(false);
                       errn1.setVisible(false);
                       errn12.setVisible(false);
                       
                   }catch(Exception e)
                   {
                       errn12.setVisible(true);
                   }
                   
               }
               ObservableList EV =  ecd.afficherEvenemets();
               ecd.afficherEvenemets();
               EventTable.setItems(EV);
               idEvent.setCellValueFactory(new PropertyValueFactory<Evenement, Integer>("idEvent"));
               NomEvent.setCellValueFactory(new PropertyValueFactory<Evenement, String>("NomEvent"));
               AdresseEvent.setCellValueFactory(new PropertyValueFactory<Evenement, String>("AdresseEvent"));
               CapaciteEvent.setCellValueFactory(new PropertyValueFactory<Evenement, String>("CapaciteEvent"));
               NbrTicketsAcheté.setCellValueFactory(new PropertyValueFactory<Evenement, String>("nbrTicketAchete"));
               DateDebutEvent.setCellValueFactory(new PropertyValueFactory<Evenement,String>("DateDebutEvent"));
               DateFinEvent.setCellValueFactory(new PropertyValueFactory<Evenement,String>("DateFinEvent"));
               TypeEvent.setCellValueFactory(new PropertyValueFactory<Evenement,String>("TypeEvent"));
               CategorieEvent.setCellValueFactory(new PropertyValueFactory<Evenement,String>("CategorieEvent"));
               PrixEntrée.setCellValueFactory(new PropertyValueFactory<Evenement,String>("PrixEntre"));
               EV =  ecd.afficherEvenemets();
               EventTable.setItems(EV);
               
               
          
             
        
         }
    }

    @FXML
    private void UpdateEvent(ActionEvent event) {
        //////////////////////CONTROLE DE SAISIE//////////////////
        
           if(EventNa.getText().equals("")){
            errn.setVisible(true);
        }
         if(EventAdrr.getText().equals("")){
           erradrs.setVisible(true);
        }
           if(EventCap.getText().equals("")){
           errcp.setVisible(true);
        }
             if(EventTick.getText().equals("")){
           errtik.setVisible(true);
        }  

         else{
                         //////////////////GET DATE VALUES///////////////////
               int ess1 = SP1.getValue();
               int ess2 = SP2.getValue();
 String hour1 = String.valueOf('0');
               String min1 =String.valueOf(0);
               if (ess1<10){
                       hour1= '0' + String.valueOf(ess1) ;
               }
               else{
                   hour1 = String.valueOf(ess1);
               }
                if (ess2<10){
                       min1 = '0' + String.valueOf(ess2) ;
               }
                else{
                    min1 = String.valueOf(ess2);
                }
              
              String hh= String.valueOf(ess1);
             String mm= String.valueOf(ess2);
              String dd =(DD.getValue()).toString();
              String ess = dd.concat(" ").concat(hour1).concat(":").concat(min1).concat(":").concat("00");
              // SimpleDateFormat sf = new SimpleDateFormat("dd-MM-yyyy HH:mm");
              System.out.println(ess);
                   Timestamp abc3 = Timestamp.valueOf(ess);
                             

              //abc= (Timestamp) sf.parse(est); }
                    
                       
                   
               
                   
               //////////////////GET DATE VALUES///////////////////
               int ess3 = SP3.getValue();
               int ess4 = SP4.getValue();
               String hour = String.valueOf('0');
               String min =String.valueOf(0);
               if (ess3<10){
                       hour= '0' + String.valueOf(ess3) ;
               }
               else{
                   hour = String.valueOf(ess3);
               }
                if (ess4<10){
                       min = '0' + String.valueOf(ess4) ;
               }
                else{
                    min = String.valueOf(ess4);
                }
              
              
              String dd11 =(DF.getValue()).toString();
              
               String ess18 = dd11.concat(" ").concat(hour).concat(":").concat(min).concat(":").concat("00");
               // String est8 =(DF.getValue().atTime(est3, est4, 00)).toString();
              //abc2= (Timestamp) sf.parse(est); 
                   Timestamp  abc4 = Timestamp.valueOf(ess18);
                     
           /////////////////////////////////////////////////////////////////////////////////////////////
                   //Evenement E = new Evenement(sss.getIdEvent(),EventNa.getText(),EventAdrr.getText(),Integer.parseInt(EventCap.getText()),Integer.parseInt(EventTick.getText()),abc3,abc4,EventTyp.getValue(),EventCat.getValue(),  Float.valueOf(PrixEntEV.getText()));
              Evenement E = new Evenement();
              E.setidEvent(sss.getIdEvent());
              E.setNomEvent(EventNa.getText());
              E.setPrixEntre(Float.valueOf(PrixEntEV.getText()));
              E.setTypeEvent(EventTyp.getValue());
              E.setNbrTicketAchete(Integer.parseInt(EventTick.getText()));
              E.setDateFinEvent(abc4);
              E.setDateDebutEvent(abc3);
              E.setCategorieEvent(EventCat.getValue());
              E.setCapaciteEvent(Integer.parseInt(EventCap.getText()));
              E.setAdresseEvent(EventAdrr.getText());
              
              
        EvenementCRUD crd = new EvenementCRUD();
        crd.ModifierEvenement(E, E.getIdEvent());
         ObservableList EV =  crd.afficherEvenemets();
         crd.afficherEvenemets();
        idEvent.setCellValueFactory(new PropertyValueFactory<Evenement, Integer>("idEvent"));
	NomEvent.setCellValueFactory(new PropertyValueFactory<Evenement, String>("NomEvent"));
	AdresseEvent.setCellValueFactory(new PropertyValueFactory<Evenement, String>("AdresseEvent"));
	CapaciteEvent.setCellValueFactory(new PropertyValueFactory<Evenement, String>("CapaciteEvent"));
        NbrTicketsAcheté.setCellValueFactory(new PropertyValueFactory<Evenement, String>("nbrTicketAchete"));
        DateDebutEvent.setCellValueFactory(new PropertyValueFactory<Evenement,String>("DateDebutEvent"));
        DateFinEvent.setCellValueFactory(new PropertyValueFactory<Evenement,String>("DateFinEvent"));
        TypeEvent.setCellValueFactory(new PropertyValueFactory<Evenement,String>("TypeEvent"));
        CategorieEvent.setCellValueFactory(new PropertyValueFactory<Evenement,String>("CategorieEvent"));
        PrixEntrée.setCellValueFactory(new PropertyValueFactory<Evenement,String>("PrixEntre"));
        EV =  crd.afficherEvenemets();
        EventTable.setItems(EV); 
                
        
          Notifications notificationBuilder;
             notificationBuilder = Notifications.create()
             .title("Effectué")
             .text("Evènement Modifié Correctement")
             .graphic(null)
             .hideAfter(Duration.seconds(10))
             .position(Pos.BASELINE_RIGHT);

             notificationBuilder.showConfirm();
    }
    }

    @FXML
    private void GORLMT(ActionEvent event) throws IOException {
              EventTable.getScene().getWindow().hide();
        EventTable.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("ReclamationFront.fxml"));
        Stage mainStage = new Stage();
        Scene scene = new Scene(root);
        mainStage.setScene(scene);
        mainStage.show();
    }

    @FXML
    private void GoTIK(ActionEvent event) throws IOException {
        EventTable.getScene().getWindow().hide();
         Parent root = FXMLLoader.load(getClass().getResource("TicketsEv.fxml"));
         Stage mainStage = new Stage();
         Scene scene = new Scene(root);
         mainStage.setScene(scene);
         mainStage.show();

    }

    @FXML
    private void Selection(ActionEvent event) {
           sss = EventTable.getSelectionModel().getSelectedItem();
                EventNa.setText(sss.getNomEvent());
                EventAdrr.setText(sss.getAdresseEvent());
                EventCap.setText(String.valueOf(sss.getCapaciteEvent()));
                PrixEntEV.setText(String.valueOf(sss.getPrixEntre()));
                EventTick.setText(String.valueOf(sss.getNbrTicketAchete()));
    }

    private void FER(ActionEvent event) {
         System.exit(0);
    
}

    private void MIN1212(ActionEvent event) {
               Stage stage = (Stage) MINIMISER.getScene().getWindow();
        stage.setIconified(true);
    }

    private void out1236(ActionEvent event) throws IOException {
        EventTable.getScene().getWindow().hide();
         Parent root = FXMLLoader.load(getClass().getResource("test.fxml"));
         Stage mainStage = new Stage();
         Scene scene = new Scene(root);
         mainStage.setScene(scene);
         mainStage.show();
    }

    @FXML
    private void SuprEv(ActionEvent event) throws IOException, SQLException {
        
        Interfaces.ev = EventTable.getSelectionModel().getSelectedItem();
       // Décccc.getScene().getWindow().hide();
       // SuppEvent1.getScene().getWindow();
        /*Parent root = FXMLLoader.load(getClass().getResource("SUP.fxml"));
             Stage mainStage = new Stage();
        Scene scene = new Scene(root);
        mainStage.setScene(scene);
       mainStage.show();*/
       
    
//        Evenement E = new Evenement(sss.getIdEvent(),EventNa.getText(),EventAdrr.getText(),Integer.parseInt(EventCap.getText()),Integer.parseInt(EventTick.getText()),abc,abc2,EventTyp.getValue(),EventCat.getValue(),  Float.valueOf(PrixEntEV.getText()));
              
        EvenementCRUD crd = new EvenementCRUD();
        crd.supprimerEvent(Interfaces.ev.getIdEvent());
         ObservableList EV =  crd.afficherEvenemets();
         
        ///idEvent.setCellValueFactory(new PropertyValueFactory<Evenement, Integer>("idEvent"));
	NomEvent.setCellValueFactory(new PropertyValueFactory<Evenement, String>("NomEvent"));
	AdresseEvent.setCellValueFactory(new PropertyValueFactory<Evenement, String>("AdresseEvent"));
	CapaciteEvent.setCellValueFactory(new PropertyValueFactory<Evenement, String>("CapaciteEvent"));
        NbrTicketsAcheté.setCellValueFactory(new PropertyValueFactory<Evenement, String>("nbrTicketAchete"));
        DateDebutEvent.setCellValueFactory(new PropertyValueFactory<Evenement,String>("DateDebutEvent"));
        DateFinEvent.setCellValueFactory(new PropertyValueFactory<Evenement,String>("DateFinEvent"));
        TypeEvent.setCellValueFactory(new PropertyValueFactory<Evenement,String>("TypeEvent"));
        CategorieEvent.setCellValueFactory(new PropertyValueFactory<Evenement,String>("CategorieEvent"));
        PrixEntrée.setCellValueFactory(new PropertyValueFactory<Evenement,String>("PrixEntre"));
        EV =  crd.afficherEvenemets();
        EventTable.setItems(EV); 

    
        
        
                     Notifications notificationBuilder = Notifications.create()
                    .title("Effectué")
                    .text("Evènement Supprimé Correctement")
                     .graphic(null)
                     .hideAfter(Duration.seconds(10))
                     .position(Pos.BASELINE_RIGHT);

             notificationBuilder.showConfirm();
    } 

    


///////////////////////////INSERTION IMAGE/////////////////
    @FXML
    public void SelectionEvent(){
    Evenement EventData = EventTable.getSelectionModel().getSelectedItem();
    int num = EventTable.getSelectionModel().getFocusedIndex();
        if((num-1) <-1)
        return;
       disponibl.setText(EventData.getNomEvent());
        String uri = "file:" + EventData.getImage1();
        
        image1 = new Image (uri, 285,135, false, true) ;
        EventsDispo.setImage(image1);
        

}

public void availableEventSelect(){
Evenement e = EventTable.getSelectionModel().getSelectedItem();
int num = EventTable.getSelectionModel().getSelectedIndex();
if((num - 1) < 1){return;}
 EventNa.setText(sss.getNomEvent());
                EventAdrr.setText(e.getAdresseEvent());
                EventCap.setText(String.valueOf(e.getCapaciteEvent()));
                PrixEntEV.setText(String.valueOf(e.getPrixEntre()));
 
String uri = "file:" + e.getImage1();
image1 = new Image(uri,285,135,false,true ) ;
EventsDispo.setImage(image1);
}
    @FXML
    public void availableEventImportImage() throws IOException{
FileChooser open = new FileChooser();
open.setTitle("Open Image File");
open.getExtensionFilters().add(new ExtensionFilter("Image File", "*jpg", "*png"));
File file= open.showOpenDialog(ownerWindow.getScene().getWindow());

if(file != null){
GetData212.path1 = file.getAbsolutePath();
image1 = new Image(file.toURI().toString() , 285, 135, false, true);
EventsDispo.setImage(image1);
}


}

    @FXML
    private void cler(ActionEvent event) {
        
    
        EventNa.setText("");
        EventAdrr.setText("");
        EventCap.setText("");
        EventTick.setText("");
        EventTyp.setValue("Veuillez Selectionner");
        PrixEntEV.setText("");
        EventTyp.setValue("Veuillez Selectionner");
       DD.setChronology(null);
       DF.setChronology(null);
       
    
    }

    @FXML
    private void godsh_btn(ActionEvent event) throws IOException, SQLException {
        int idCurrentUser = Integer.parseInt(Session.id);
        Session session = new Session();
        String roleArray = session.getRole(idCurrentUser);
        String role = roleArray.substring(2,roleArray.length()-2);
        System.err.println(role);
        
        
        if ("ROLE_ARTISTE".equals(role) ){
        Parent root = FXMLLoader.load(getClass().getResource("CCC.fxml"));
        EventTable.getScene().getWindow().hide();
        EventTable.getScene().getWindow();
        
        Stage mainStage = new Stage();
        Scene scene = new Scene(root);
        mainStage.setScene(scene);
        mainStage.show();

    }
        else if ("ROLE_PRESTATAIRE".equals(role)){
             Parent root = FXMLLoader.load(getClass().getResource("Prestataire.fxml"));
        EventTable.getScene().getWindow().hide();
        EventTable.getScene().getWindow();
        
        Stage mainStage = new Stage();
        Scene scene = new Scene(root);
        mainStage.setScene(scene);
        mainStage.show();
        }
        else if ("ROLE_SUPER_ADMIN".equals(role)){
        Parent root = FXMLLoader.load(getClass().getResource("AdminDashboard.fxml"));
        EventTable.getScene().getWindow().hide();
        EventTable.getScene().getWindow();
        
        Stage mainStage = new Stage();
        Scene scene = new Scene(root);
        mainStage.setScene(scene);
        mainStage.show();
        }
        else if ("ROLE_USER".equals(role) ){
              Parent root = FXMLLoader.load(getClass().getResource("UserDashboard.fxml"));
        EventTable.getScene().getWindow().hide();
        EventTable.getScene().getWindow();
        
        Stage mainStage = new Stage();
        Scene scene = new Scene(root);
        mainStage.setScene(scene);
        mainStage.show();
        }
     
    
        
      
    }

    private void getValue() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @FXML
    private void signout(ActionEvent event) throws IOException {
              Parent root = FXMLLoader.load(getClass().getResource("test.fxml"));
        EventTable.getScene().getWindow().hide();
        EventTable.getScene().getWindow();
        
        Stage mainStage = new Stage();
        Scene scene = new Scene(root);
        mainStage.setScene(scene);
        mainStage.show();
        }
    }
