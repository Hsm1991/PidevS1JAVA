/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package iservice;

import entities.commandPM;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author elbootic.com
 */
public interface Iservicecommand {
        public void ajouterCommande(commandPM  commande) ;
    public List<commandPM> getcommands()throws SQLException ;
    public void supprimercommande(int idCPM) throws SQLException;
   public boolean modifiercommande(commandPM C,int id) throws SQLException ;
}
