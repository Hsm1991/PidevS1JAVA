package entities;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.Date;

/**
 *
 * @author user
 */
public class User {
    private String user_id;
    private String user_mdp;
    private String user_nom ;
    private String user_prenom;
    private String user_mail;
    private String user_adresse;
    private String user_tel;
    private String user_role;
    private int    category_id;

    public User() {
    }

    
    /*
    public User(String user_mdp, String user_nom, String user_prenom, String user_mail, String user_tel, String user_adresse, Role user_role) {
        this.user_mdp = user_mdp;
        this.user_nom = user_nom;
        this.user_prenom = user_prenom;
        this.user_mail = user_mail;
        this.user_tel = user_tel;
        this.user_adresse = user_adresse;
        this.user_role = user_role;
    }
     public User(String user_mdp, String user_nom, String user_prenom, String user_mail, String user_tel, String user_adresse, String user_role) {
        this.user_mdp = user_mdp;
        this.user_nom = user_nom;
        this.user_prenom = user_prenom;
        this.user_mail = user_mail;
        this.user_tel = user_tel;
        this.user_adresse = user_adresse;
        this.user_role = user_role;
    }  */
    
    public User(String user_id, String user_mdp, String user_nom, String user_prenom, String user_mail, String user_tel, String user_adresse, String user_role) {
        this.user_id = user_id;
        this.user_mdp = user_mdp;
        this.user_nom = user_nom;
        this.user_prenom = user_prenom;
        this.user_mail = user_mail;
        this.user_tel = user_tel;
        this.user_adresse = user_adresse;
        this.user_role = user_role;
    }

    
 
    
    
    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getUser_mdp() {
        return user_mdp;
    }

    public void setUser_mdp(String user_mdp) {
        this.user_mdp = user_mdp;
    }

    public String getUser_nom() {
        return user_nom;
    }

    public void setUser_nom(String user_nom) {
        this.user_nom = user_nom;
    }

    public String getUser_prenom() {
        return user_prenom;
    }

    public void setUser_prenom(String user_prenom) {
        this.user_prenom = user_prenom;
    }

    public String getUser_mail() {
        return user_mail;
    }

    public void setUser_mail(String user_mail) {
        this.user_mail = user_mail;
    }

    public String getUser_tel() {
        return user_tel;
    }

    public void setUser_tel(String user_tel) {
        this.user_tel = user_tel;
    }

    public String getUser_adresse() {
        return user_adresse;
    }

    public void setUser_adresse(String user_adresse) {
        this.user_adresse = user_adresse;
    }


    public String getUser_role() {
        return user_role;
    }

    public void setUser_role(String user_role) {
        this.user_role = user_role;
    }

    public int getCategory_id() {
        return category_id;
    }

    public void setCategory_id(int category_id) {
        this.category_id = category_id;
    }

   
    
    

    @Override
    public String toString() {
        return "User" + user_id + "mot de passe : " + user_mdp + "Nom :" + user_nom + "Prenom" + user_prenom + "MAil:" + user_mail + "Adresse :"+ user_adresse + "TEl :" + user_tel  + "Role " + user_role ; //To change body of generated methods, choose Tools | Templates.
    }

   


    

}
