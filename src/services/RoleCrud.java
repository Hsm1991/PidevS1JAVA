/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import entities.Role;
import utils.MyConnexion;

/**
 *
 * @author user
 */
public class RoleCrud {
    
    
       
    Connection cnx2 ; 

    public RoleCrud() {
        cnx2=MyConnexion.getInstance().getCnx();
    }
    
    
    public void createRole2(Role r){

       
     try {
      
          String add_role ="INSERT INTO category(id,nom,detail,status) VALUES (?,?,?,0)";
          PreparedStatement pst = cnx2.prepareStatement(add_role);
         
          pst.setInt(1,r.getRole_id());
          pst.setString(2,r.getRole_name());
          pst.setString(3,r.getRole_detail());

          pst.executeUpdate();  /* sans passer la requete car c'est inseré ds prepareStatement  */
          System.out.println("Role added");
                  
        } catch (SQLException ex) {
          System.err.println(ex.getMessage());
        }
    }
    
    
      public void createRole(int id, String name,String detail){

       
     try {
      
          String add_role ="INSERT INTO category(id,nom,detail,status) VALUES (?,?,?,0)";
          PreparedStatement pst = cnx2.prepareStatement(add_role);
         
          pst.setInt(1,id);
          pst.setString(2,name);
          pst.setString(3,detail);

          pst.executeUpdate();  /* sans passer la requete car c'est inseré ds prepareStatement  */
          System.out.println("Role added");
                  
        } catch (SQLException ex) {
          System.err.println(ex.getMessage());
        }
    }
    
    
     public ObservableList<Role> showAllRoles() {
       
         Connection cnx2 ; 
         cnx2=MyConnexion.getInstance().getCnx();
         
        ObservableList<Role> myList =FXCollections.observableArrayList();
        try {
            String showAllRoles= "SELECT * FROM category ";
           PreparedStatement st = cnx2.prepareStatement(showAllRoles);
          ResultSet rs =  st.executeQuery();  // contenaire de la req 
            // creation des personnes à partir des données de resultSet 
            while (rs.next()){
                Role r =new Role ();
                r.setRole_id(rs.getInt("id")); 
                r.setRole_name(rs.getString("nom"));
                r.setRole_detail(rs.getString("detail"));
                
                myList.add(r);
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserCrud.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return myList;

    }
     
     
     
          public List<Role> showOneRole(int id) {
         List<Role> myList =new ArrayList<>();
        try {
            
            String showOneRole= "SELECT * FROM category where id=?";
           PreparedStatement st = cnx2.prepareStatement(showOneRole);
            st.setInt(1, id);
            System.out.println(st);
            ResultSet rs = st.executeQuery();
          
          while (rs.next()){
                Role r =new Role ();
                r.setRole_id(rs.getInt("id")); 
                r.setRole_name(rs.getString("nom"));
                r.setRole_detail(rs.getString("detail"));
                myList.add(r);
            }
         
        } catch (SQLException ex) {
            Logger.getLogger(UserCrud.class.getName()).log(Level.SEVERE, null, ex);
        }return myList;
}
        
    /**
     *
     * @param r
     */
    public void updateRole(String n,String d,int id){

         
        try {
            
            String update_role = "UPDATE category SET nom=?, detail=? WHERE id=?";
 
             PreparedStatement statement = cnx2.prepareStatement(update_role);
             
             statement.setString(1,n);
             statement.setString(2,d);
             statement.setInt(3,id);
             
             int rowsUpdated = statement.executeUpdate();
             if (rowsUpdated > 0) {
            System.out.println(" role updated successfully");  }             
             
        } catch (SQLException ex) {
            Logger.getLogger(RoleCrud.class.getName()).log(Level.SEVERE, null, ex);
        }
   
}
         
         
        public void deleteRole(int id){

         
        try {
            
            String delete_role = "DELETE FROM category WHERE id=?";
 
             PreparedStatement statement = cnx2.prepareStatement(delete_role);
             statement.setInt(1,id);
             statement.executeUpdate();

            System.out.println(" role deleted successfully");            
             
        } catch (SQLException ex) {
            Logger.getLogger(RoleCrud.class.getName()).log(Level.SEVERE, null, ex);
        }
   
}
         
  
          public boolean checkRoleExists(int id, String name, String detail ) throws SQLException{
   
           boolean result = true;
          
           String checkroleExists= "SELECT * FROM category where id=? and nom=? and detail =?";
           PreparedStatement st = cnx2.prepareStatement(checkroleExists);
           st.setInt(1, id);
           st.setString(2, name);
           st.setString(3, detail);
          
           ResultSet rs = st.executeQuery();
           
           if (!( rs.isBeforeFirst()) )   // checks if empty : false if empty , true if not ) 
                { result = false; }  // true = exists , false = doesn't exist
                 return result;
                }
              
          
          
          
         public boolean checkRoleid(int id) throws SQLException{
   
           boolean result = true;
          
           String checkroleExists= "SELECT * FROM category where id=? ";
           PreparedStatement st = cnx2.prepareStatement(checkroleExists);
           st.setInt(1, id);
       
          
           ResultSet rs = st.executeQuery();
           
           if (!( rs.isBeforeFirst()) )   // checks if empty : false if empty , true if not ) 
                { result = false; }  // true = exists , false = doesn't exist
                 return result;
                }
              
           public boolean checkRole(String n,String d) throws SQLException{
   
           boolean result = true;
          
           String checkroleExists= "SELECT * FROM category where nom =? and detail=? ";
           PreparedStatement st = cnx2.prepareStatement(checkroleExists);
           st.setString(1, n);
           st.setString(2, d);
       
          
           ResultSet rs = st.executeQuery();
           
           if (!( rs.isBeforeFirst()) )   // checks if empty : false if empty , true if not ) 
                { result = false; }  // true = exists , false = doesn't exist
                 return result;
                }
         
           
           
         public ObservableList<String> ListRoleNames() {
       /*
         Connection cnx2 ; 
         cnx2=MyConnexion.getInstance().getCnx();
         
        ObservableList<String> myList =FXCollections.observableArrayList();
        try {
            String showAllRoles= "SELECT name FROM roles ";
           PreparedStatement st = cnx2.prepareStatement(showAllRoles);
          ResultSet rs =  st.executeQuery();  // contenaire de la req 
            // creation des personnes à partir des données de resultSet 
            while (rs.next()){
                String r;
                r=rs.getString("name"); 
                
                myList.add(r);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        */
        ArrayList<String> list = new ArrayList();
       list.add("[\"ROLE_SUPER_ADMIN\"]");
        list.add("[\"ROLE_USER\"]");
        list.add("[\"ROLE_ARTISTE\"]");
        list.add("[\"ROLE_PRESTATAIRE\"]");
       
         ObservableList<String> myList =FXCollections.observableArrayList(list);
         
        return myList;

    }
     
         
    public int getCategoryId(String role){
        String keyword = "error";
        int categ_id = 0;
        if("[\"ROLE_SUPER_ADMIN\"]".equals(role)){
            keyword = "super admin";
        }
        else if ("[\"ROLE_USER\"]".equals(role)){
            keyword = "simple utilisateur";
        }
        else if ("[\"ROLE_ARTISTE\"]".equals(role)){
            keyword = "artiste";
        }
        else if ("[\"ROLE_PRESTATAIRE\"]".equals(role)){
            keyword = "prestataire";
        }
        String sql = "select id from category where nom = ? " ;
        
        try {
            PreparedStatement pst = cnx2.prepareStatement(sql);
            
            pst.setObject(1, keyword);
            System.out.println(pst);
            ResultSet rs = pst.executeQuery();
            while (rs.next()){
                categ_id = rs.getInt("id");
            }
        } catch (SQLException ex) {
            Logger.getLogger(RoleCrud.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return categ_id;
    }
         
         
}



    
