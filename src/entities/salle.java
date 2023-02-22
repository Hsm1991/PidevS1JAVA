/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

/**
 *
 * @author MLLE-BAHRI
 */
public class salle {
    
    private int num_salle;
    private String type;
    private String statu;
    private float prix;
    private String image ;
    private int capacite;
    private String nom;

    public salle(int num_salle, String type, String statu, float prix) {
        this.num_salle = num_salle;
        this.type = type;
        this.statu = statu;
        this.prix = prix;
    }

    public salle(String type, String statu, float prix) {
        this.type = type;
        this.statu = statu;
        this.prix = prix;
    }

    
    public salle() {
    }

    public int getNum_salle() {
        return num_salle;
    }

    public void setNum_salle(int num_salle) {
        this.num_salle = num_salle;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getStatu() {
        return statu;
    }

    public void setStatu(String statu) {
        this.statu = statu;
    }

    public float getPrix() {
        return prix;
    }

    public void setPrix(float prix) {
        this.prix = prix;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getCapacite() {
        return capacite;
    }

    public void setCapacite(int capacite) {
        this.capacite = capacite;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    
    
    @Override
    public String toString() {
        return "salle{" + "num_salle=" + num_salle + ", type=" + type + ", statu=" + statu + ", prix=" + prix + '}';
    }

    
    
    
    
}
