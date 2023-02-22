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
public class Commande {
    
    private Integer idCmd ;
    private Integer idPanier;
    private Integer idProd;
    private String  nomProd;
    private Integer quantite;
    private Float prixProd;
    private Float prixRemise;    
    private String etatCmd;
    private String dateCmd;

    public Commande() {
    }

    public Commande(Integer idCmd) {
        this.idCmd = idCmd;
    }

    

    

    
    public Commande(Integer idCmd, Integer idPanier, Integer idProd, String nomProd, Integer quantite, Float prixProd, Float prixRemise, String etatCmd, String dateCmd) {
        this.idCmd = idCmd;
        this.idPanier = idPanier;
        this.idProd = idProd;
        this.nomProd = nomProd;
        this.quantite = quantite;
        this.prixProd = prixProd;
        this.prixRemise = prixRemise;
        this.etatCmd = etatCmd;
        this.dateCmd = dateCmd;
    }

    public Integer getIdCmd() {
        return idCmd;
    }

    public void setIdCmd(Integer idCmd) {
        this.idCmd = idCmd;
    }

    public Integer getIdPanier() {
        return idPanier;
    }

    public void setIdPanier(Integer idPanier) {
        this.idPanier = idPanier;
    }

    public Integer getIdProd() {
        return idProd;
    }

    public void setIdProd(Integer idProd) {
        this.idProd = idProd;
    }

    public String getNomProd() {
        return nomProd;
    }

    public void setNomProd(String nomProd) {
        this.nomProd = nomProd;
    }

    public Integer getQuantite() {
        return quantite;
    }

    public void setQuantite(Integer quantite) {
        this.quantite = quantite;
    }

    public Float getPrixProd() {
        return prixProd;
    }

    public void setPrixProd(Float prixProd) {
        this.prixProd = prixProd;
    }

    public Float getPrixRemise() {
        return prixRemise;
    }

    public void setPrixRemise(Float prixRemise) {
        this.prixRemise = prixRemise;
    }

    public String getEtatCmd() {
        return etatCmd;
    }

    public void setEtatCmd(String etatCmd) {
        this.etatCmd = etatCmd;
    }

    public String getDateCmd() {
        return dateCmd;
    }

    public void setDateCmd(String dateCmd) {
        this.dateCmd = dateCmd;
    }

    @Override
    public String toString() {
        return "Commande{" + "idCmd=" + idCmd + ", idPanier=" + idPanier + ", idProd=" + idProd + ", nomProd=" + nomProd + ", quantite=" + quantite + ", prixProd=" + prixProd + ", prixRemise=" + prixRemise + ", etatCmd=" + etatCmd + ", dateCmd=" + dateCmd + '}';
    }

    
    

}  
