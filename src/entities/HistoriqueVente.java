/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.util.Date;

/**
 *
 * @author LENOVO
 */
public class HistoriqueVente {
    private Integer IdVent ;
    private String DateVent ;
    private float QteVendue ;
    private float PrixVente;
    private Integer IdPROD;

    public HistoriqueVente(String DateVent, float QteVendue ,float PrixVente,Integer IdPROD) {
        this.DateVent = DateVent;
        this.QteVendue = QteVendue;
        this.PrixVente=PrixVente;
        this.IdPROD=IdPROD;
    }

    public HistoriqueVente(Integer IdVent, String DateVent, float QteVendue,float PrixVente,Integer IdPROD) {
        this.IdVent = IdVent;
        this.DateVent = DateVent;
        this.QteVendue = QteVendue;
        this.PrixVente=PrixVente;
        this.IdPROD=IdPROD;
    }

    public HistoriqueVente() {
    }

  

    public Integer getIdVent() {
        return IdVent;
    }

    public void setIdVent(Integer IdVent) {
        this.IdVent = IdVent;
    }

    public String getDateVent() {
        return DateVent;
    }

    public void setDateVent(String DateVent) {
        this.DateVent = DateVent;
    }

    public float getQteVendue() {
        return QteVendue;
    }

    public void setQteVendue(float QteVendue) {
        this.QteVendue = QteVendue;
    }

    @Override
    public String toString() {
        return "HistoriqueVente{" + "IdVent=" + IdVent + ", DateVent=" + DateVent + ", QteVendue=" + QteVendue + ", PrixVente=" + PrixVente + ", IdPROD=" + IdPROD + '}';
    }

    public Integer getIdPROD() {
        return IdPROD;
    }

    public void setIdPROD(Integer IdPROD) {
        this.IdPROD = IdPROD;
    }

  
    public float getPrixVente() {
        return PrixVente;
    }

    public void setPrixVente(float PrixVente) {
        this.PrixVente = PrixVente;
    }

  
    
    
    
    
    
}