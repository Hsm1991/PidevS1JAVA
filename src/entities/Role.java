/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

/**
 *
 * @author user
 */
public class Role {
    
    private int role_id;
    private String role_name;
    private String role_detail;
    private int status;
    
    
    public Role(int role_id, String role_name, String role_detail) {
        this.role_id = role_id;
        this.role_name = role_name;
        this.role_detail = role_detail;
    }

    public Role(String role_name, String role_detail) {
        this.role_name = role_name;
        this.role_detail = role_detail;
    }

    public Role() {
    }

    public int getRole_id() {
        return role_id;
    }

    public void setRole_id(int role_id) {
        this.role_id = role_id;
    }

    public String getRole_name() {
        return role_name;
    }

    public void setRole_name(String role_name) {
        this.role_name = role_name;
    }

    public String getRole_detail() {
        return role_detail;
    }

    public void setRole_detail(String role_detail) {
        this.role_detail = role_detail;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
    
    
    

    @Override
    public String toString() {
        return "Role : " + role_name + " d'id " + role_id + " |" + role_detail; 
    }
    
    
    
}
