/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

/**
 *
 * @author saif
 */
public class Panier {
    private Integer idPanier;
    private Integer idProd;
    private String nomProd;
    private Integer quantite;
    private Float prixProd;
    private Float prixRemise;

    public Panier() {
    }

    public Panier(Integer idPanier) {
        this.idPanier = idPanier;
    }

    public Panier(Integer idPanier, Integer idProd, String nomProd, Integer quantite, Float prixProd, Float prixRemise) {
        this.idPanier = idPanier;
        this.idProd = idProd;
        this.nomProd = nomProd;
        this.quantite = quantite;
        this.prixProd = prixProd;
        this.prixRemise = prixRemise;
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

    @Override
    public String toString() {
        return "Panier{" + "idPanier=" + idPanier + ", idProd=" + idProd + ", nomProd=" + nomProd + ", quantite=" + quantite + ", prixProd=" + prixProd + ", prixRemise=" + prixRemise + '}';
    }

    
    

    

    

    
    
            
    
    
}
