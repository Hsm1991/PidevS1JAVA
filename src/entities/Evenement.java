/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;




import java.util.Date;
import java.sql.Timestamp;

import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;


/**
 *
 * @author User
 */

public class Evenement {
    private  int idEvent;
    private  String NomEvent;
    private  String AdresseEvent;
    private  int CapaciteEvent;
    private  int nbrTicketAchete;
    private  Timestamp  DateDebutEvent;
    private  Timestamp  DateFinEvent;
    private  String TypeEvent;
    private  String CategorieEvent;
    private  float PrixEntre;
    private  String image1;
    
    public Evenement(){

}

    public Evenement(int idEvent, String NomEvent, String AdresseEvent, int CapaciteEvent, int nbrTicketAchete, Timestamp  DateDebutEvent, Timestamp  DateFinEvent, String TypeEvent, String CategorieEvent, float PrixEntre, String image1) {
        this.idEvent = idEvent;
        this.NomEvent = NomEvent;
        this.AdresseEvent = AdresseEvent;
        this.CapaciteEvent = CapaciteEvent;
        this.nbrTicketAchete = nbrTicketAchete;
        this.DateDebutEvent = DateDebutEvent;
        this.DateFinEvent = DateFinEvent;
        this.TypeEvent = TypeEvent;
        this.CategorieEvent = CategorieEvent;
        this.PrixEntre = PrixEntre;
        this.image1 = image1;
    }

    public Evenement(String NomEvent, String AdresseEvent, int CapaciteEvent, int nbrTicketAchete, Timestamp  DateDebutEvent, Timestamp  DateFinEvent, String TypeEvent, String CategorieEvent, float PrixEntre, String image1) {
        this.NomEvent = NomEvent;
        this.AdresseEvent = AdresseEvent;
        this.CapaciteEvent = CapaciteEvent;
        this.nbrTicketAchete = nbrTicketAchete;
        this.DateDebutEvent = DateDebutEvent;
        this.DateFinEvent = DateFinEvent;
        this.TypeEvent = TypeEvent;
        this.CategorieEvent = CategorieEvent;
        this.PrixEntre = PrixEntre;
        this.image1 = image1;
    }
    
    

    public Evenement(int idEvent, String NomEvent, String AdresseEvent, int CapaciteEvent, int nbrTicketAchete, Timestamp  DateDebutEvent, Timestamp  DateFinEvent, String TypeEvent, String CategorieEvent, float PrixEntre) {
        this.idEvent = idEvent;
        this.NomEvent = NomEvent;
        this.AdresseEvent = AdresseEvent;
        this.CapaciteEvent = CapaciteEvent;
        this.nbrTicketAchete = nbrTicketAchete;
        this.DateDebutEvent = DateDebutEvent;
        this.DateFinEvent = DateFinEvent;
        this.TypeEvent = TypeEvent;
        this.CategorieEvent = CategorieEvent;
        this.PrixEntre = PrixEntre;
    }

    public Evenement(String NomEvent, String AdresseEvent, int CapaciteEvent, int nbrTicketAchete, Timestamp  DateDebutEvent, Timestamp  DateFinEvent, String TypeEvent, String CategorieEvent, float PrixEntre) {
        this.NomEvent = NomEvent;
        this.AdresseEvent = AdresseEvent;
        this.CapaciteEvent = CapaciteEvent;
        this.nbrTicketAchete = nbrTicketAchete;
        this.DateDebutEvent = DateDebutEvent;
        this.DateFinEvent = DateFinEvent;
        this.TypeEvent = TypeEvent;
        this.CategorieEvent = CategorieEvent;
        this.PrixEntre = PrixEntre;
    }

 



    public Evenement(String evenement1, String tunis, int i, int i0) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public Evenement(TextField EventN, TextField EventCap, TextField NBtick, Button AddEV, TextField PrEV, TextField stEv, TextField finEv, ComboBox tyEV, ComboBox CATev) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public Evenement(String text, String text0, int parseInt, int parseInt0, Timestamp abc, Timestamp abc2, String value, String value0, Float valueOf) {
           this.NomEvent = text;
        this.AdresseEvent = text0;
        this.CapaciteEvent = parseInt;
        this.nbrTicketAchete = parseInt0;
        this.DateDebutEvent = abc;
        this.DateFinEvent = abc2;
        this.TypeEvent = value;
        this.CategorieEvent = value0;
        this.PrixEntre = valueOf;
    
    }

    public Evenement(int idEvent, String text, String text0, int parseInt, int parseInt0, Timestamp abc3, Timestamp abc4, String value, String value0, Float valueOf) {
            this.NomEvent = text;
        this.AdresseEvent = text0;
        this.CapaciteEvent = parseInt;
        this.nbrTicketAchete = parseInt0;
        this.DateDebutEvent = abc3;
        this.DateFinEvent = abc4;
        this.TypeEvent = value;
        this.CategorieEvent = value0;
        this.PrixEntre = valueOf;
    }




    public Evenement(int idEvent, String text, String text0, int parseInt, int parseInt0, Date abc, Date abc2, String value, String value0, Float valueOf) {
            this.NomEvent = text;
        this.AdresseEvent = text0;
        this.CapaciteEvent = parseInt;
        this.nbrTicketAchete = parseInt0;
        this.DateDebutEvent = (Timestamp) abc;
        this.DateFinEvent = (Timestamp) abc2;
        this.TypeEvent = value;
        this.CategorieEvent = value0;
        this.PrixEntre = valueOf;
    }

    public Evenement(String text, String text0, int parseInt, int parseInt0, Date abc, Date abc2, String value, String value0, Float valueOf) {
               this.NomEvent = text;
        this.AdresseEvent = text0;
        this.CapaciteEvent = parseInt;
        this.nbrTicketAchete = parseInt0;
        this.DateDebutEvent = (Timestamp) abc;
        this.DateFinEvent = (Timestamp) abc2;
        this.TypeEvent = value;
        this.CategorieEvent = value0;
        this.PrixEntre = valueOf;
    }

   

  

 

    public int getIdEvent() {
        return idEvent;
    }

    public String getNomEvent() {
        return NomEvent;
    }

    public String getAdresseEvent() {
        return AdresseEvent;
    }

    /**
     *
     * @return
     */
    public int getCapaciteEvent() {
        return CapaciteEvent;
    }

    public Date  getDateDebutEvent() {
        return DateDebutEvent;
    }

    public Date  getDateFinEvent() {
        return DateFinEvent;
    }

    public String getTypeEvent() {
        return TypeEvent;
    }

    public String getCategorieEvent() {
        return CategorieEvent;
    }

    public float getPrixEntre() {
        return PrixEntre;
    }

    public void setidEvent(int id) {
        this.idEvent = id;
    }

    public void setNomEvent(String NomEvent) {
        this.NomEvent = NomEvent;
    }

    public void setAdresseEvent(String AdresseEvent) {
        this.AdresseEvent = AdresseEvent;
    }

    public void setCapaciteEvent(int CapaciteEvent) {
        this.CapaciteEvent = CapaciteEvent;
    }

    public void setDateDebutEvent(Timestamp  DateDebutEvent) {
        this.DateDebutEvent = DateDebutEvent;
    }

    public void setDateFinEvent(Timestamp  DateFinEvent) {
        this.DateFinEvent = DateFinEvent;
    }

    public void setTypeEvent(String TypeEvent) {
        this.TypeEvent = TypeEvent;
    }

    public void setCategorieEvent(String CategorieEvent) {
        this.CategorieEvent = CategorieEvent;
    }

    public void setPrixEntre(float PrixEntre) {
        this.PrixEntre = PrixEntre;
    }

    @Override
    public String toString() {
        return  NomEvent;
    }

    

    public int getNbrTicketAchete() {
        return nbrTicketAchete;
    }

    public void setNbrTicketAchete(int nbrTicketAchete) {
        this.nbrTicketAchete = nbrTicketAchete;
    }

    public Object toLowerCase() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public String getImage1() {
        return image1;
    }

    public void setImage1(String image1) {
        this.image1 = image1;
    }

    public Object setDateDebutEvent() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public Object setDateFinEvent() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    
    
}
