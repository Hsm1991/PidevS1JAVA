/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package iservice;

import entities.ProduitPM;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author elbootic.com
 */
public interface IserviceProduit {
    
    public void ajouterProduit(ProduitPM produit)throws SQLException ;
    public List<ProduitPM> getProduits()throws SQLException ;
   public void supprimerProduit(int id) throws SQLException;
   public boolean modifierProduit(ProduitPM produit,int id) throws SQLException ;

    
}
