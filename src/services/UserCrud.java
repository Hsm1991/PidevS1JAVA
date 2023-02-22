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
import entities.User;
import utils.MyConnexion;



/**
 *
 * @author user
 */
public class UserCrud {
    
    
   Connection cnx2 ; 

    public UserCrud() {
        cnx2=MyConnexion.getInstance().getCnx();
    }
    
    

    public void createUser(User p){

       
     try {
      
          String add_user ="INSERT INTO user(id,password,lastname,firstname,email,address,tel,roles,category_id) VALUES (?,?,?,?,?,?,?,?,?)";
          PreparedStatement pst = cnx2.prepareStatement(add_user);
          pst.setString(1, p.getUser_id());
          pst.setString(2, p.getUser_mdp());
          pst.setString(3,p.getUser_nom());
          pst.setString(4,p.getUser_prenom());  
          pst.setString(5,p.getUser_mail());
          pst.setString(6,p.getUser_adresse());
          pst.setString(7,p.getUser_tel());
          pst.setString(8, p.getUser_role());
          pst.setInt(9,p.getCategory_id());
         // pst.setRole(6,p.getUser_role()); 
          pst.executeUpdate();  // sans passer la requete car c'est inseré ds prepareStatement  
          System.out.println("User added");
                  
        } catch (SQLException ex) {
          System.err.println(ex.getMessage());
        }
    
     
  
}  

    public ObservableList<User> showAllUsers() {
       Connection cnx2 ; 
         cnx2=MyConnexion.getInstance().getCnx();
        ObservableList<User> myList =FXCollections.observableArrayList();
        try {
            String showAll= "SELECT * FROM user";
            Statement st =cnx2.createStatement();
          ResultSet rs =  st.executeQuery(showAll);  // contenaire de la req 
            // creation des personnes à partir des données de resultSet 
            while (rs.next()){
                User P =new User ();
                P.setUser_id(rs.getString(1));  // num colone //
                P.setUser_nom(rs.getString("lastname")) ;
                P.setUser_prenom(rs.getString("firstname")) ;
                P.setUser_role(rs.getString("roles")) ;
                P.setUser_mail(rs.getString("email")) ;
                P.setUser_adresse(rs.getString("address")) ;
                P.setUser_tel(rs.getString("tel")) ;
                P.setUser_mdp(rs.getString("password")) ;
           
                myList.add(P);
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserCrud.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return myList;

    }
    
    
    
    
              public List<User> showOneUser(String id) {
         List<User> myList =new ArrayList<>();
        try {
            
            String showOneUser= "SELECT * FROM user where id=?";
           PreparedStatement st = cnx2.prepareStatement(showOneUser);
            st.setString(1, id);
            System.out.println(st);
            ResultSet rs = st.executeQuery();
          
          while (rs.next()){
                User r =new User ();
                r.setUser_id(rs.getString("id")); 
                r.setUser_mdp(rs.getString("password"));
                r.setUser_nom(rs.getString("lastname"));
                r.setUser_prenom(rs.getString("firstname"));
                r.setUser_mail(rs.getString("email"));
                r.setUser_adresse(rs.getString("address"));
                r.setUser_tel(rs.getString("tel"));
                r.setUser_role(rs.getString("role"));
               
                myList.add(r);
            }
         
        } catch (SQLException ex) {
            Logger.getLogger(UserCrud.class.getName()).log(Level.SEVERE, null, ex);
        }return myList;
              
              
              }
              
              
              
              
              
                  public void updateUser(String m,String n,String p,String mi,String a,String t,String r, String id,int categ_id){

         
        try {
            
            String update_user = "UPDATE user SET password=?, lastname=?, firstname=?, email=?,address=?,tel=?,roles=?,category_id=? WHERE id=?";
 
             PreparedStatement statement = cnx2.prepareStatement(update_user);
             
             statement.setString(1,m);
             statement.setString(2,n);
             statement.setString(3,p);
             statement.setString(4,mi);
             statement.setString(5,a);
             statement.setString(6,t);
             statement.setString(7,r);
             statement.setString(9,id);
             statement.setInt(8,categ_id);
             
             int rowsUpdated = statement.executeUpdate();
             if (rowsUpdated > 0) {
            System.out.println(" role updated successfully");  }             
             
        } catch (SQLException ex) {
            Logger.getLogger(RoleCrud.class.getName()).log(Level.SEVERE, null, ex);
        }
   
}
                  
                  
                  
                  
          public void deleteUser(String id){

         
        try {
            
            String delete_user = "DELETE FROM user WHERE id=?";
 
             PreparedStatement statement = cnx2.prepareStatement(delete_user);
            
             statement.setString(1, id);
             statement.executeUpdate();

            System.out.println(" User deleted successfully");            
             
        } catch (SQLException ex) {
            Logger.getLogger(RoleCrud.class.getName()).log(Level.SEVERE, null, ex);
        }
   
}
             
   
          
          
          public boolean checkUserExists(String mail, String pwd ) throws SQLException{
   
           boolean result = true;
           
              
           String checkUserExists= "SELECT * FROM user where email=?  ";
           PreparedStatement st = cnx2.prepareStatement(checkUserExists);
           st.setString(1, mail);
        
           
          String hashedPwd = null ;
          String email = null ;
           ResultSet rs = st.executeQuery();
           
          while(rs.next()){
               hashedPwd = rs.getString("password");
               email = rs.getString("email");
          }
         // boolean hashCondition = BCrypt.checkpw(pwd, hashedPwd);
          
          
          if (email == null ){
              result = false;
          }
          return result;
          }
          
          
          public boolean checkMailExists(String mail ) throws SQLException{
   
           boolean result = true;
          
           String checkMailExists= "SELECT * FROM user where email=?";
           PreparedStatement st = cnx2.prepareStatement(checkMailExists);
           st.setString(1, mail);
          
           
           ResultSet rs = st.executeQuery();
           
           if (!( rs.isBeforeFirst()) )   // checks if empty : false if empty , true if not ) 
                { result = false; } 
                 return result;
                }
          
          
          
          public boolean isValidEmailAddress(String email) {
           String ePattern = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
           java.util.regex.Pattern p = java.util.regex.Pattern.compile(ePattern);
           java.util.regex.Matcher m = p.matcher(email);
           return m.matches();
}
          
          
          
          
          
              public String getPassword(String mail) {
         String r =null;
         
        try {
            
            String showOneUser= "SELECT password FROM user where email=?";
           PreparedStatement st = cnx2.prepareStatement(showOneUser);
            st.setString(1, mail);
            System.out.println(st);
            ResultSet rs = st.executeQuery();
          
          while (rs.next()){
                
                r = rs.getString("password");
                
                    }
         
        } catch (SQLException ex) {
            Logger.getLogger(UserCrud.class.getName()).log(Level.SEVERE, null, ex);
        }return r;
              
              
              }
              
              
              
            public String getRolefromUser(String id) {
         String r =null;
         
        try {
            
            String showOneUserRole= "SELECT roles FROM user where id = ?";
           PreparedStatement st = cnx2.prepareStatement(showOneUserRole);
            st.setString(1, id);
            System.out.println(st);
            ResultSet rs = st.executeQuery();
          
          while (rs.next()){
                
                r = rs.getString("roles").toLowerCase();
                System.out.println(r);
                    }
         
        } catch (SQLException ex) {
            Logger.getLogger(UserCrud.class.getName()).log(Level.SEVERE, null, ex);
        }return r;
              
              
              }
      
            
                  public String getRolefromMail(String id) {
         String r =null;
         
        try {
            
            String showOneUserRole= "SELECT roles FROM user where email = ?";
           PreparedStatement st = cnx2.prepareStatement(showOneUserRole);
            st.setString(1, id);
            System.out.println(st);
            ResultSet rs = st.executeQuery();
          
          while (rs.next()){
                
                r = rs.getString("roles").toLowerCase();
                System.out.println(r);
                    }
         
        } catch (SQLException ex) {
            Logger.getLogger(UserCrud.class.getName()).log(Level.SEVERE, null, ex);
        }return r;
              
              
              }
                  
                  public String getPass(){
                      
                      String pass = "$2y$13$Bj9qw/Z3os0w9RyGNcpjK.uXxoGajP./SNnQIiJhl0Qc4l8YGjPjK";
                      
                      
                      
                      return pass;
                  }
              
}
    
    

    





