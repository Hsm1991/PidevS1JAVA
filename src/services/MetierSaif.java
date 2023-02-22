/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.Panier;
import entities.Commande;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author saif
 */
public class MetierSaif {
    
    public static Float totalPanier(Panier p){
        
        Float t = 0f;
        Integer q;
        Float prixU;
        List<Panier> l = new  ArrayList<>();
        PanierCRUD pcrd = new PanierCRUD();
        l = pcrd.afficherParIdPanier(p);
        System.out.println(l);
        
        for (Panier pTest : l){
            q = pTest.getQuantite();
            prixU = pTest.getPrixProd();
            t = t + q * remisePanier(q,prixU);
        }
        
    return t; 
    }
    
    public static Float totalCommande(Commande c){
        Float t = 0f;
        Integer q;
        Float prixU;
        List<Commande> l = new  ArrayList<>();
        CommandeCRUD ccrd = new CommandeCRUD();
        l = ccrd.afficherParIdCommande(c);
        System.out.println(l);
        
        for (Commande pTest : l){
            q = pTest.getQuantite();
            prixU = pTest.getPrixProd();
            t = t + q * remisePanier(q,prixU);
        }
        
    return t;
    }
    
    public static Float remisePanier(Integer q , Float p ){
        
        if (q >= 5){
            return 0.8f * p;  
        }
        else {
            return p;
        }
        
            
        
    }
    
}
