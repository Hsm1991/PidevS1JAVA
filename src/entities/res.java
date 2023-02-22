/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.util.logging.Logger;

/**
 *
 * @author MLLE-BAHRI
 */
public class res {
   
    private int id_res;
    private int num_salle;
    private String nom_artiste;
    private String date_res;
    private float montant;

    public res() {
    }

    public res(int id_res) {
        this.id_res = id_res;
    }

    
    public res(int id_res, int num_salle, String nom_artiste, String date_res, float montant) {
        this.id_res = id_res;
        this.num_salle = num_salle;
        this.nom_artiste = nom_artiste;
        this.date_res = date_res;
        this.montant = montant;
    }

    public int getId_res() {
        return id_res;
    }

    public void setId_res(int id_res) {
        this.id_res = id_res;
    }

    public int getNum_salle() {
        return num_salle;
    }

    public void setNum_salle(int num_salle) {
        this.num_salle = num_salle;
    }

    public String getNom_artiste() {
        return nom_artiste;
    }

    public void setNom_artiste(String nom_artiste) {
        this.nom_artiste = nom_artiste;
    }

    public String getDate_res() {
        return date_res;
    }

    public void setDate_res(String date_res) {
        this.date_res = date_res;
    }

    public float getMontant() {
        return montant;
    }

    public void setMontant(float montant) {
        this.montant = montant;
    }

    @Override
    public String toString() {
        return "res{" + "id_res=" + id_res + ", num_salle=" + num_salle + ", nom_artiste=" + nom_artiste + ", date_res=" + date_res + ", montant=" + montant + '}';
    }

    
   

    
    
}
