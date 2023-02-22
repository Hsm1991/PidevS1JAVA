/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;



import entities.Session;
import entities.Facture;
import entities.Commande;
import java.awt.Color;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javax.mail.MessagingException;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;
import services.*;
import utils.MaConnexion;
import utils.MailerSaif;
import utils.PdfTableSaif;
import utils.PdfTextSaif;
/**
 * FXML Controller class
 *
 * @author saif
 */
public class CommandeSaifController implements Initializable {

    @FXML
    private Button btnDashboardCommandeSaif;
    @FXML
    private Button btnReclamationCommandeSaif;
    @FXML
    private TableView<Commande> tableCommandeSaif;
    @FXML
    private TableColumn<Commande, String> colNomProduitCommande;
    @FXML
    private TableColumn<Commande, Float> colPrixUnitaireCommande;
    @FXML
    private TableColumn<Commande, Integer> colQuantiteCommande;
    @FXML
    private TableColumn<Commande, Float> colPrixCommande;
    @FXML
    private Button btnAnnulerCommandeSaif;
    @FXML
    private Button btnConfirmerCommandeSaif;
    @FXML
    private Button btnRetourAuPanierSaif;
    @FXML
    private TextField tfTotalCommande;
    @FXML
    private Button btnCalculerTotalCommande;
    @FXML
    private Label lbDateCommandeSaif;
    @FXML
    private Label lbEtatCommandeSaif;
    @FXML
    private ChoiceBox<Integer> cbCommandeSaif;
    @FXML
    private Button btnImprimerFactureSaif;
    @FXML
    private AnchorPane anchorPaneCommande;

    
    public static Integer idc = 0  ;
    
    //session
    Integer idSession = Integer.parseInt(Session.id);
    
    //creation ob list
    CommandeCRUD ccrd = new CommandeCRUD();
    Commande c = new Commande(idc);
    List<Integer> allIdCmd = ccrd.getAllCommandeIdByUser(Integer.parseInt(Session.id));
    List<Commande> allProdLast = ccrd.afficherParIdCommande(c);
    List<Commande> allUserCommande = ccrd.getAllCommandeByUser(Integer.parseInt(Session.id));
    
    ObservableList<Commande> obList = FXCollections.observableArrayList(allProdLast);
    @FXML
    private Label lbWarningFacture;
    @FXML
    private Button ao_btn_signout;
    @FXML
    private Label labelsignout;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       if (idc!=0) {
       
           colNomProduitCommande.setCellValueFactory(new PropertyValueFactory<Commande , String>("nomProd"));
        
            colPrixUnitaireCommande.setCellValueFactory(new PropertyValueFactory<Commande , Float>("prixProd"));
            colQuantiteCommande.setCellValueFactory(new PropertyValueFactory<Commande , Integer>("quantite"));
            colPrixCommande.setCellValueFactory(new PropertyValueFactory<Commande , Float>("prixRemise"));
            tableCommandeSaif.setItems(obList);
            
            cbCommandeSaif.setValue(idc);
             cbCommandeSaif.getItems().addAll(allIdCmd);
             
              cbCommandeSaif.setOnAction(this::getCommandeCB);
            //initialiser etat
                String etatInit = allProdLast.get(0).getEtatCmd();
                fixColors(lbEtatCommandeSaif, etatInit);
            //initialiser date
                lbDateCommandeSaif.setText(allProdLast.get(0).getDateCmd());
            }
       else{
           if (!allUserCommande.isEmpty()) {
             ObservableList<Commande> obList2 = FXCollections.observableArrayList(ccrd.afficherParIdCommande(new Commande(allUserCommande.get(0).getIdCmd())));

            colNomProduitCommande.setCellValueFactory(new PropertyValueFactory<Commande , String>("nomProd"));
        
            colPrixUnitaireCommande.setCellValueFactory(new PropertyValueFactory<Commande , Float>("prixProd"));
            colQuantiteCommande.setCellValueFactory(new PropertyValueFactory<Commande , Integer>("quantite"));
            colPrixCommande.setCellValueFactory(new PropertyValueFactory<Commande , Float>("prixRemise"));
            tableCommandeSaif.setItems(obList2);
            
            
             cbCommandeSaif.setValue(allIdCmd.get(0));
             cbCommandeSaif.getItems().addAll(allIdCmd);
             
              cbCommandeSaif.setOnAction(this::getCommandeCB);
            //initialiser etat
                String etatInit = allUserCommande.get(0).getEtatCmd();
                fixColors(lbEtatCommandeSaif, etatInit);
            //initialiser date
                lbDateCommandeSaif.setText(allUserCommande.get(0).getDateCmd());
       
     
           }
        
        }
    }

    @FXML
    private void redirectDashboardCommandeSaif(ActionEvent event) throws IOException, SQLException {
        int idCurrentUser = Integer.parseInt(Session.id);
        Session session = new Session();
        String roleArray = session.getRole(idCurrentUser);
        String role = roleArray.substring(2,roleArray.length()-2);
        System.err.println(role);
        
        
        if ("ROLE_ARTISTE".equals(role) ){
        Parent root = FXMLLoader.load(getClass().getResource("CCC.fxml"));
        anchorPaneCommande.getScene().getWindow().hide();
        anchorPaneCommande.getScene().getWindow();
        
        Stage mainStage = new Stage();
        Scene scene = new Scene(root);
        mainStage.setScene(scene);
        mainStage.show();

    }
        else if ("ROLE_PRESTATAIRE".equals(role)){
             Parent root = FXMLLoader.load(getClass().getResource("Prestataire.fxml"));
        anchorPaneCommande.getScene().getWindow().hide();
        anchorPaneCommande.getScene().getWindow();
        
        Stage mainStage = new Stage();
        Scene scene = new Scene(root);
        mainStage.setScene(scene);
        mainStage.show();
        }
        else if ("ROLE_SUPER_ADMIN".equals(role)){
        Parent root = FXMLLoader.load(getClass().getResource("AdminDashboard.fxml"));
        anchorPaneCommande.getScene().getWindow().hide();
        anchorPaneCommande.getScene().getWindow();
        
        Stage mainStage = new Stage();
        Scene scene = new Scene(root);
        mainStage.setScene(scene);
        mainStage.show();
        }
        else if ("ROLE_USER".equals(role) ){
              Parent root = FXMLLoader.load(getClass().getResource("UserDashboard.fxml"));
        anchorPaneCommande.getScene().getWindow().hide();
        anchorPaneCommande.getScene().getWindow();
        
        Stage mainStage = new Stage();
        Scene scene = new Scene(root);
        mainStage.setScene(scene);
        mainStage.show();
        }
    }

    @FXML
    private void redirectReclamationCommandeSaif(ActionEvent event) throws IOException {
             anchorPaneCommande.getScene().getWindow().hide();
        anchorPaneCommande.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("ReclamationFront.fxml"));
        Stage mainStage = new Stage();
        Scene scene = new Scene(root);
        mainStage.setScene(scene);
        mainStage.show();    }

    private void logoutCommandeSaif(MouseEvent event) {
        try {
            AnchorPane pane = FXMLLoader.load(getClass().getResource("panier.fxml"));
            anchorPaneCommande.getChildren().setAll(pane);
        } catch (IOException ex) {
            Logger.getLogger(CommandeSaifController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void annulerCommandeSaif(ActionEvent event) {
        if (cbCommandeSaif.getValue() != null){
            Integer id = cbCommandeSaif.getValue();
            c = new Commande(id);
            c.setIdPanier(idSession);
            ccrd.annulerCommande(c);
            c.setIdPanier(idSession);
            //repopulation choicebox
            cbCommandeSaif.getItems().clear();
            List<Integer> l = ccrd.afficherParUserCommande(c);
            cbCommandeSaif.getItems().addAll(l);
            //selection valuer choicebox
            if (l != null){
                cbCommandeSaif.setValue(l.get(0));
                //repopulation tab
                c = new Commande(l.get(0));
                allProdLast = ccrd.afficherParIdCommande(c);
                obList = FXCollections.observableArrayList(allProdLast);
                tableCommandeSaif.setItems(obList);
            }

            //update etat
            if (l == null){
                lbEtatCommandeSaif.setText("pas de commande selectionnée");
                lbEtatCommandeSaif.setStyle("-fx-text-fill: black;");
            }
            else{
                String etat = allProdLast.get(0).getEtatCmd();
                fixColors(lbEtatCommandeSaif,etat);
            }
        }
    }

    @FXML
    private void confirmerCommandeSaif(ActionEvent event) {
        
        if (cbCommandeSaif.getValue() != null){
            Integer id = cbCommandeSaif.getValue();
            Commande c = new Commande(id);
            String etat = "payée";
            ccrd.updateEtatCommande(c, etat);
            fixColors(lbEtatCommandeSaif,etat);
        }
    }

    @FXML
    private void retourAuPanierSaif(ActionEvent event) {
        try {
            AnchorPane pane = FXMLLoader.load(getClass().getResource("panier.fxml"));
            anchorPaneCommande.getChildren().setAll(pane);
        } catch (IOException ex) {
            Logger.getLogger(CommandeSaifController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void calculerTotalCommande(ActionEvent event) {
        if (cbCommandeSaif.getValue() != null){
            Commande c = new Commande(cbCommandeSaif.getValue());
            Float t = MetierSaif.totalCommande(c);
            tfTotalCommande.setText(""+ t);
        }
    }

    @FXML
    private void imprimerFactureSaif(ActionEvent event) throws IOException {
        lbWarningFacture.setVisible(false);
        Integer id = cbCommandeSaif.getValue();
        if (id == null){
            lbWarningFacture.setVisible(true);
        }
        else{
            //capturer les donnees commande
            c = new Commande(id);
            allProdLast = ccrd.afficherParIdCommande(c);
            //enregistrer facture
            Facture f = new Facture();
            f.setIdCmd(id);
            f.setIdUser(idSession);
            f.setMontant(MetierSaif.totalCommande(c));
            f.setDateCmd(allProdLast.get(0).getDateCmd());
            FactureCRUD fcrd = new FactureCRUD();
            fcrd.ajouterFacture(f);
            //creer pdf
            PDDocument document = new PDDocument();
            PDPage firstPage = new PDPage(PDRectangle.A4);
            document.addPage(firstPage);
            int pageWidth = (int) firstPage.getTrimBox().getWidth();
            int pageHeight = (int) firstPage.getTrimBox().getHeight();

            PDPageContentStream contentStream = new PDPageContentStream(document, firstPage);
            PdfTextSaif myTextClass = new PdfTextSaif(document,contentStream);

            PDFont font = PDType1Font.HELVETICA;


            PDImageXObject headImage = PDImageXObject.createFromFile("C:\\Users\\User\\Desktop\\BACKUP INTEG SAIF\\HoussemProject\\src\\Resources\\logoSaif.png", document);
            contentStream.drawImage(headImage, 20, pageHeight-pageHeight/7, pageWidth/6, pageHeight/7);

            String contactDetails[] = new String[]{"bazart@gmail.com","+21699202178"};
            myTextClass.addMultiLineText(contactDetails, 30, (int)(pageWidth-font.getStringWidth("bazart@gmail.com++")/1000*12-10),
                    pageHeight-25, font, 12, Color.BLACK);
            myTextClass.addSingleLineText("Baz'Art", (int)(pageWidth/2-font.getStringWidth("Baz'Art")/1000*30-10), pageHeight-70, font, 50, Color.BLACK);
            
            String nom = afficherParIdNom(idSession);
            String tel = afficherParIdTel(idSession);
            myTextClass.addSingleLineText("Nom Client: "+ nom, 25, pageHeight-170, font, 15, Color.BLACK);
            myTextClass.addSingleLineText("Num Tel: " + tel , 25, pageHeight-200, font, 15, Color.BLACK);
            
            String idCmd = "id Commande: " + c.getIdCmd();
            float textWidth = myTextClass.getTextWidth(idCmd,font,15);
            myTextClass.addSingleLineText(idCmd, (int)(pageWidth-25-textWidth), pageHeight-170, font, 15, Color.BLACK);
            
            String dateCmd = "date Commande: " + allProdLast.get(0).getDateCmd();
            textWidth = myTextClass.getTextWidth(dateCmd, font, 15);
            myTextClass.addSingleLineText(dateCmd, (int)(pageWidth-25-textWidth), pageHeight-200, font, 15, Color.BLACK);
            
            //tableau
            PdfTableSaif table = new PdfTableSaif(document, contentStream);
            
            int[] cellWidths = {150,130,70,170};
            table.setTable(cellWidths, 28, 25, pageHeight-250);
            table.setTableFont(font, 12 , Color.BLACK);
            
            Color tableHeadColor = new Color(51,102,255);
            Color tableBodyColor = new Color(102,153,255);
                //head
            table.addCell("Nom Produit",tableHeadColor,pageHeight);
            table.addCell("prix unitaire",tableHeadColor,pageHeight);
            table.addCell("quantite",tableHeadColor,pageHeight);
            table.addCell("prix apres remise",tableHeadColor,pageHeight);
                //body
            for (Commande cTest:allProdLast){
                table.addCell(cTest.getNomProd(),tableBodyColor,pageHeight);
                table.addCell(""+cTest.getPrixProd(), tableBodyColor,pageHeight);
                table.addCell(""+cTest.getQuantite(), tableBodyColor,pageHeight);
                table.addCell(""+cTest.getPrixRemise(), tableBodyColor,pageHeight);
            }
            //total
            table.addCell("", null, pageHeight);
            table.addCell("", null, pageHeight);
            table.addCell("Total ", null, pageHeight);
            table.addCell("   "+f.getMontant(), null, pageHeight);
            //signature
            contentStream.setStrokingColor(Color.BLACK);
            contentStream.setLineWidth(2);
            contentStream.moveTo(pageWidth-250, 150);
            contentStream.lineTo(pageWidth-25, 150);
            contentStream.stroke();
            String sign = "Signature";
            float signWidth = myTextClass.getTextWidth(sign, font, 16);
            int   xpos = pageWidth-250+(250-25)/2;
            myTextClass.addSingleLineText(sign, (int)(xpos-signWidth/2), 125, font, 16, Color.BLACK);
            //save
            contentStream.close();
            document.save("facture.pdf");
            document.close();
            System.out.println("PDF created");
            
            //send mail
            String mail = afficherParIdMail(idSession);
            String path = "facture.pdf";
            MailerSaif mailer = new MailerSaif();
            try {
                mailer.sendMail(mail, path);
                System.out.println("mail sent");
            } catch (MessagingException ex) {
                System.err.println(ex.getMessage());
            }
        }
    }
    
    private void fixColors(Label l1,String etat){
        if ("En attente de confirmation".equals(etat)){
            l1.setText(etat);
            l1.setStyle("-fx-text-fill: red;");
        }
        else{
            l1.setText("En livraison");
            l1.setStyle("-fx-text-fill: green;");
        }
    }
    
    private void getCommandeCB(ActionEvent event){
        Integer id = cbCommandeSaif.getValue();
        if (id!=null){
            Commande c1 = new Commande(id);
            allProdLast = ccrd.afficherParIdCommande(c1);
            obList = FXCollections.observableArrayList(allProdLast);
            tableCommandeSaif.setItems(obList);
            //update etat
            fixColors(lbEtatCommandeSaif,allProdLast.get(0).getEtatCmd());
            //update date
            lbDateCommandeSaif.setText(allProdLast.get(0).getDateCmd());
        }
    }
    
    private String afficherParIdNom(Integer id){
        Connection cnx= MaConnexion.getInstance().getCnx();
        Statement st;
        String nomComplet = "";
        
        String req = "select * from user";
        try {
            st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while(rs.next()){
                if (rs.getInt(1) == id){
                    nomComplet = nomComplet + rs.getString("firstname") + " ";
                    nomComplet = nomComplet + rs.getString("lastname");
                    break;
                }
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        
        
    return nomComplet;
    }
    
    private String afficherParIdTel(Integer id){
        Connection cnx= MaConnexion.getInstance().getCnx();
        Statement st;
        String tel = "";
        
        String req = "select * from user";
        try {
            st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while(rs.next()){
                if (rs.getInt(1) == id){
                    tel = tel + rs.getString("tel") ;
                    break;
                }
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        
        
    return tel;
    }
    
    public String afficherParIdMail(Integer id ){
        Connection cnx= MaConnexion.getInstance().getCnx();
        Statement st;
        String mail = "";
        
        String req = "select * from user";
        try {
            st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while(rs.next()){
                if (rs.getInt(1) == id){
                    mail = mail + rs.getString("email") ;
                    break;
                }
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        
        
    return mail;
    }

    @FXML
    private void signout(ActionEvent event) throws IOException {
        
         anchorPaneCommande.getScene().getWindow().hide();
        anchorPaneCommande.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("test.fxml"));
        Stage mainStage = new Stage();
        Scene scene = new Scene(root);
        mainStage.setScene(scene);
        mainStage.show(); 
    }
}
