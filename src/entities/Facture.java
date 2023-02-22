/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.sql.Date;

/**
 *
 * @author saif
 */
public class Facture {
    
   private Integer idFacture;
   private Integer idCmd;
   private Integer idUser;
   private Float montant;
   private String dateCmd;

    public Facture(Integer idFacture, Integer idCmd, Integer idUser, Float montant, String dateCmd) {
        this.idFacture = idFacture;
        this.idCmd = idCmd;
        this.idUser = idUser;
        this.montant = montant;
        this.dateCmd = dateCmd;
    }

    public Facture() {
    }

    public Integer getIdFacture() {
        return idFacture;
    }

    public void setIdFacture(Integer idFacture) {
        this.idFacture = idFacture;
    }

    public Integer getIdCmd() {
        return idCmd;
    }

    public void setIdCmd(Integer idCmd) {
        this.idCmd = idCmd;
    }

    public Integer getIdUser() {
        return idUser;
    }

    public void setIdUser(Integer idUser) {
        this.idUser = idUser;
    }

    public Float getMontant() {
        return montant;
    }

    public void setMontant(Float montant) {
        this.montant = montant;
    }

    public String getDateCmd() {
        return dateCmd;
    }

    public void setDateCmd(String dateCmd) {
        this.dateCmd = dateCmd;
    }

    @Override
    public String toString() {
        return "Facture{" + "idFacture=" + idFacture + ", idCmd=" + idCmd + ", idUser=" + idUser + ", montant=" + montant + ", dateCmd=" + dateCmd + '}';
    }

   
    
   
   
}
