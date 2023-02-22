/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.sql.Date;
import java.util.Objects;


/**
 *
 * @author elbootic.com
 */
public class ProduitPM {

    public static void setAll(boolean chercher) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    private int IDProd ;
    private String NomProd ;
    private int referenceP;
    private int quantiteP;
    private  String type;
    private float  prixPM ;
    private String dateAjoutPM;




    public String getDateAjoutPM() {
        return dateAjoutPM;
    }

    public void setDateAjoutPM(String dateAjoutPM) {
        this.dateAjoutPM = dateAjoutPM;
    }

    public ProduitPM(String NomProd, int referenceP, int quantiteP, String type, float prixPM, String dateAjoutPM) {
        this.NomProd = NomProd;
        this.referenceP = referenceP;
        this.quantiteP = quantiteP;
        this.type = type;
        this.prixPM = prixPM;
        this.dateAjoutPM = dateAjoutPM;
    }

    public ProduitPM(int IDProd, String NomProd, int referenceP, int quantiteP, String type, float prixPM, String dateAjoutPM) {
        this.IDProd = IDProd;
        this.NomProd = NomProd;
        this.referenceP = referenceP;
        this.quantiteP = quantiteP;
        this.type = type;
        this.prixPM = prixPM;
        this.dateAjoutPM = dateAjoutPM;
    }
    
    
    


    

    public int getIDProd() {
        return IDProd;
    }

    public void setIDProd(int IDProd) {
        this.IDProd = IDProd;
    }

    public String getNomProd() {
        return NomProd;
    }

    public void setNomProd(String NomProd) {
        this.NomProd = NomProd;
    }

    public int getReferenceP() {
        return referenceP;
    }

    public void setReferenceP(int referenceP) {
        this.referenceP = referenceP;
    }

    public int getQuantiteP() {
        return quantiteP;
    }

    public void setQuantiteP(int quantiteP) {
        this.quantiteP = quantiteP;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public float getPrixPM() {
        return prixPM;
    }

    public void setPrixPM(float prixPM) {
        this.prixPM = prixPM;
    }

    public String getDateAjout() {
        return dateAjoutPM;
    }

    public void setDateAjout(String dateAjout) {
        this.dateAjoutPM = dateAjout;
    }

    

    public ProduitPM() {
        
    }

    @Override
    public String toString() {
        return NomProd;
    }

   
   
  
}















