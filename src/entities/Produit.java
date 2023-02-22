/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;



/**
 *
 * @author LENOVO
 */
public class Produit {
    private  int IdPROD;
    private String NomProd ;
    private float PrixProd ;
    private String LocalisationProd;
    private String TypeProd ;
    private String TypeStatue ;
    private String imagem1;
    private String rating ;
    
    public Produit() {
    }

    public Produit(String NomProd, float PrixProd, String LocalisationProd, String TypeProd, String TypeStatue, String imagem1) {
        this.NomProd = NomProd;
        this.PrixProd = PrixProd;
        this.LocalisationProd = LocalisationProd;
        this.TypeProd = TypeProd;
        this.TypeStatue = TypeStatue;
        this.imagem1 = imagem1;
    }

    public Produit(int IdPROD, String NomProd, float PrixProd, String LocalisationProd, String TypeProd, String TypeStatue, String imagem1) {
        this.IdPROD = IdPROD;
        this.NomProd = NomProd;
        this.PrixProd = PrixProd;
        this.LocalisationProd = LocalisationProd;
        this.TypeProd = TypeProd;
        this.TypeStatue = TypeStatue;
        this.imagem1 = imagem1;
    }

    public Produit(int IdPROD, String NomProd, float PrixProd, String LocalisationProd, String TypeProd, String TypeStatue) {
        this.IdPROD = IdPROD;
        this.NomProd = NomProd;
        this.PrixProd = PrixProd;
        this.LocalisationProd = LocalisationProd;
        this.TypeProd = TypeProd;
        this.TypeStatue = TypeStatue;
       
    }

    public Produit(String NomProd, float PrixProd, String LocalisationProd, String TypeProd, String TypeStatue) {
        this.NomProd = NomProd;
        this.PrixProd = PrixProd;
        this.LocalisationProd = LocalisationProd;
        this.TypeProd = TypeProd;
        this.TypeStatue = TypeStatue;
       
    }

    public String getImagem1() {
        return imagem1;
    }

    public void setImagem1(String imagem1) {
        this.imagem1 = imagem1;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }



    public int getIdPROD() {
        return IdPROD;
    }

    public void setIdPROD(int IdPROD) {
        this.IdPROD = IdPROD;
    }

    public String getNomProd() {
        return NomProd;
    }

    public void setNomProd(String NomProd) {
        this.NomProd = NomProd;
    }

    public float getPrixProd() {
        return PrixProd;
    }

    public void setPrixProd(float PrixProd) {
        this.PrixProd = PrixProd;
    }

    public String getLocalisationProd() {
        return LocalisationProd;
    }

    public void setLocalisationProd(String LocalisationProd) {
        this.LocalisationProd = LocalisationProd;
    }

    public String getTypeProd() {
        return TypeProd;
    }

    public void setTypeProd(String TypeProd) {
        this.TypeProd = TypeProd;
    }

    public String getTypeStatue() {
        return TypeStatue;
    }

    public void setTypeStatue(String TypeStatue) {
        this.TypeStatue = TypeStatue;
    }

    @Override
    public String toString() {
        return "Produit{" + "IdPROD=" + IdPROD + ", NomProd=" + NomProd + ", PrixProd=" + PrixProd + ", LocalisationProd=" + LocalisationProd + ", TypeProd=" + TypeProd + ", TypeStatue=" + TypeStatue + ", imagem1=" + imagem1 + '}';
    }

   

    public String getimagem1() {
        return imagem1;
    }

    public void setimagem1(String imagem1) {
        this.imagem1 = imagem1;
    }
    


}
