/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.Coach;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import utils.MyConnection;

/**
 *
 * @author LENOVO
 */
public class CoachService {
      public Connection con ;
    public PreparedStatement pst ;
    public Statement ste ;
    public ResultSet res ;
    
    public CoachService(){
        con = MyConnection.getInstance().getCnx();
    }

    public void ajouterCoach(Coach d) throws SQLException {
       
        try {
      String requeteInsert = "INSERT INTO Coach ( `user_id` , `speciality`, `salary`) VALUES ('"+d.getUser_id()+ "','"+d.getSpeciality()+ "','"+d.getSalary()+""+"')";
            ste = con.createStatement();
           ste.executeUpdate(requeteInsert);    
            
        } catch (SQLException ex) {
            Logger.getLogger(CoachService.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("Le Coach est ajoutée");     
    }
      public void modifiercoach(Coach u, int id){
        try {
            String requete = " update coach set age=? , poid=? , hauteur=? , facebook=? , insta=? , gmail=? , bio=?, image=? where user_id='" + id + "'";
            pst = con.prepareStatement(requete);
            pst.setInt(1, u.getAge());
            pst.setInt(2, u.getPoid());
            pst.setInt(3, u.getHauteur());
            pst.setString(4, u.getFacebook());
            pst.setString(5, u.getInsta());
            pst.setString(6, u.getGmail());
            pst.setString(7, u.getBio());
            pst.setString(8, u.getImage());                                         
            pst.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
      
              
    public void supprimerCoach(int id){
        try {
            PreparedStatement pt = con.prepareStatement("delete from Coach where id = ?");
            pt.setInt(1, id);
            pt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }
  
    }
    
      public int getuseridbycoachid(int id) throws SQLException {
           
            PreparedStatement pt= con.prepareStatement("select user_id from Coach WHERE id = ?");
            pt.setInt(1, id);
            ResultSet rs = pt.executeQuery();
            if(rs.next())
                return rs.getInt(1);
          return rs.getInt(1);
      
          
    }
          public int getcoachidbyuserid(int id) throws SQLException {
           
            PreparedStatement pt= con.prepareStatement("select id from Coach WHERE user_id = ?");
            pt.setInt(1, id);
            ResultSet rs = pt.executeQuery();
            if(rs.next())
                return rs.getInt(1);
          return rs.getInt(1);  
          }
    
    
    
    
}
