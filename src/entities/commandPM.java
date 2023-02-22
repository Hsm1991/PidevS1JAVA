/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.sql.Date;

/**
 *
 * @author elbootic.com
 */

public class commandPM {
    private int idCPM ;
    private int IDProd ;
    private String NomProd;
    private int referenceCM;
    private Date date ;
    private int quantiteCpm;
    private int iduser ;
    private  String type;

    public int getReferenceCM() {
        return referenceCM;
    }

    public void setReferenceCM(int referenceCM) {
        this.referenceCM = referenceCM;
    }
    
    public int getIdCPM() {
        return idCPM;
    }

    public void setIdCPM(int idCPM) {
        this.idCPM = idCPM;
    }

    public int getIduser() {
        return iduser;
    }

    public void setIduser(int iduser) {
        this.iduser = iduser;
    }

    public int getIDProd() {
        return IDProd;
    }

    public void setIDProd(int IDProd) {
        this.IDProd = IDProd;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getQuantiteCpm() {
        return quantiteCpm;
    }

    public void setQuantiteCpm(int quantiteCpm) {
        this.quantiteCpm = quantiteCpm;
    }

    public commandPM() {
    }

    public String getNomProd() {
        return NomProd;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setNomProd(String NomProd) {
        this.NomProd = NomProd;
    }

    public commandPM(int idCPM, int IDProd, String NomProd, int referenceCM, Date date, int quantiteCpm, int iduser, String type) {
        this.idCPM = idCPM;
        this.IDProd = IDProd;
        this.NomProd = NomProd;
        this.referenceCM = referenceCM;
        this.date = date;
        this.quantiteCpm = quantiteCpm;
        this.iduser = iduser;
        this.type = type;
    }

   public commandPM( int IDProd, String NomProd, int referenceCM, Date date, int quantiteCpm, int iduser, String type) {
        this.IDProd = IDProd;
        this.NomProd = NomProd;
        this.referenceCM = referenceCM;
        this.date = date;
        this.quantiteCpm = quantiteCpm;
        this.iduser = iduser;
        this.type = type;
    }

   

 

    

    @Override
    public String toString() {
        return "commandPM{" + "idCPM=" + idCPM + ", IDProd=" + IDProd + ",NomProd=" + NomProd + ", date=" + date + ", refrenceCM=" + referenceCM +", quantiteCpm=" + quantiteCpm + ", iduser=" + iduser +", type=" + type + '}';
    }
   
}
