/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.User;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import utils.MyConnection;

/**
 *
 * @author LENOVO
 */
public class UserService {
      
    
    private Connection c;
    private Statement ste;
    private PreparedStatement pst ;
    public UserService() {
    c = MyConnection.getInstance().getCnx();
     }
    
    private static User currentUser;

    public void ajouterUser(User u){
        try {
            PreparedStatement pt = c.prepareStatement("insert into users values( ?, ?, ?, ?, ?, ?, ?, ? , ?)");
            pt.setInt(1, u.getId());
            pt.setString(2, u.getEmail());
            pt.setString(3, u.getRoles());
            pt.setString(4, u.getPassword());
            pt.setString(5, u.getActivation_token());
            pt.setString(6, u.getNom());
            pt.setString(7, u.getPrenom());
            pt.setString(8, u.getTel());
            pt.setDate(9, (Date) u.getDatenaissance());
            pt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public List<User> readAllusers() throws SQLException {
        List<User> arr = new ArrayList<>();
        String req = ("SELECT * FROM Users");
        ste = c.createStatement();
        ResultSet rs = ste.executeQuery(req);
            while (rs.next()) {
            User p = new User();         
            p.setId(rs.getInt(1));
            p.setEmail(rs.getString(2));
            p.setRoles(rs.getString(3));
            p.setPassword(rs.getString(4));
            p.setActivation_token(rs.getString(5));
            p.setNom(rs.getString(6));
            p.setPrenom(rs.getString(7)); 
            p.setTel(rs.getString(8));
            p.setDatenaissance(rs.getDate(9));
            arr.add(p);
        }
        return arr;
    }
       
    public void modifierUser(User u, int id){
        try {
            String requete = " update Users set roles=? where id='" + id + "'";
            pst = c.prepareStatement(requete);
            pst.setString(1, u.getRoles());
            pst.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
   
    
     public User getUserByUsername(String username){
        try {
            PreparedStatement pt= c.prepareStatement("select * from users where nom = ?");
            pt.setString(1, username);
            ResultSet rs = pt.executeQuery();
            if(rs.next()){
  User u = new User(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8), rs.getDate(8));
                return u;
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
     
     
    
    public static User getCurrentUser() {
        return currentUser;
    }

    public static void setCurrentUser(User currentUser) {
        UserService.currentUser = currentUser;
    }
    
}

