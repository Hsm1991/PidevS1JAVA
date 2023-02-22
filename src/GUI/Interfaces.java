/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import entities.Evenement;
import entities.HistoriqueVente;
import entities.Produit;
import entities.ProduitPM;
import entities.Ticket;
import java.io.IOException;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author User
 */
public class Interfaces extends Application {
    static  Evenement ev = new Evenement();
    static Ticket ev1 = new Ticket();
     
    static Produit p1 = new Produit();
    static HistoriqueVente h1= new HistoriqueVente();
    static HistoriqueVente h2= new HistoriqueVente();
    
     static ProduitPM pm =new ProduitPM();
   
    static ProduitPM p ;
     static  ObservableList<Produit> ListFavorit = FXCollections.observableArrayList();
    @Override
    public void start(Stage stage) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("test.fxml"));
            
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
     
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
           
    }
    
    
}
