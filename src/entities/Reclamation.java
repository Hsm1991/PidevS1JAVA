/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

/**
 *
 * @author User
 */
public class Reclamation {
    private int id;
    private int iduser;
    private String description ;
    private String recdate ;
    private String selon;
    private String mail;

    public Reclamation() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIduser() {
        return iduser;
    }

    public void setIduser(int iduser) {
        this.iduser = iduser;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getRecdate() {
        return recdate;
    }

    public void setRecdate(String recdate) {
        this.recdate = recdate;
    }

    public String getSelon() {
        return selon;
    }

    public void setSelon(String selon) {
        this.selon = selon;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    @Override
    public String toString() {
        return "Reclamation{" + "id=" + id + ", iduser=" + iduser + ", description=" + description + ", recdate=" + recdate + ", selon=" + selon + ", mail=" + mail + '}';
    }
    
    
}
