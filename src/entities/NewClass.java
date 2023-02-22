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
public class NewClass {
    
    public class res {
    private String nom_artiste;
     private int num_salle;
    private String date_res;
    

    public res() {
    }

        public String getNom_artiste() {
            return nom_artiste;
        }

        public int getNum_salle() {
            return num_salle;
        }

        public void setNom_artiste(String nom_artiste) {
            this.nom_artiste = nom_artiste;
        }

        public void setNum_salle(int num_salle) {
            this.num_salle = num_salle;
        }

        public void setDate_res(String date_res) {
            this.date_res = date_res;
        }

        public String getDate_res() {
            return date_res;
        }

        public res(String nom_artiste, int num_salle, String date_res) {
            this.nom_artiste = nom_artiste;
            this.num_salle = num_salle;
            this.date_res = date_res;
        }
    
    }
}
