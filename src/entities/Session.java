/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import utils.MyConnection;

/**
 *
 * @author user
 */
public class Session {
    
   public static String id ;
   
   
      Connection cnx2;

    public Session() {
        cnx2 = MyConnection.getInstance().getCnx();
    }
    

    @Override
    public String toString() {
        return "Session{" + "id=" + id + '}';
    }
    
    
    public  String getRole(int id) throws SQLException{
       String sql = "select * from user where id = " + id ;
       Statement st = cnx2.createStatement();;
            try {
                st = cnx2.createStatement();
            } catch (SQLException ex) {
                Logger.getLogger(Session.class.getName()).log(Level.SEVERE, null, ex);
            }
       ResultSet rs = st.executeQuery(sql);
       while (rs.next()){
           User user = new User();
           user.setUser_role(rs.getString("roles"));
           return user.getUser_role();
       }
       
       return "";
   }
   
   
   
    
}
