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
import utils.MyConnection;

/**
 *
 * @author LENOVO
 */
public class AbonnementService {
       
    private Connection c;
    private Statement ste;
    public PreparedStatement pt ;
    public AbonnementService() {
    c = MyConnection.getInstance().getCnx();
     }

    
       public List<Integer> readAllAbonnements() throws SQLException {
        List<Integer> arr = new ArrayList<>();
        String req = ("SELECT coach_id ,COUNT(user_id) from Abonnement GROUP by coach_id ORDER by COUNT(user_id) DESC");
        ste = c.createStatement();
        ResultSet rs = ste.executeQuery(req);
        rs.beforeFirst();
      
            while (rs.next()) 
            {    
                arr.add(rs.getInt("coach_id")); 
                arr.add(rs.getInt("COUNT(user_id)"));                 
            } 
             
        return arr ;
        
    }
       
       public List<String> Abonnes(int id){
         try{
        List<String> arr = new ArrayList<>();
        String req = ("SELECT nom,email FROM abonnement INNER JOIN users ON abonnement.user_id = users.id WHERE coach_id='" + id + "'");
        pt = c.prepareStatement(req);
        ResultSet rs = pt.executeQuery(req);
        rs.beforeFirst();
        while (rs.next()) 
            {    
                arr.add(rs.getString("nom")); 
                arr.add(rs.getString("email"));                 
            } 
            return arr ;
        }
                  catch (Exception e){
        System.out.println (e.getMessage());
        }
         return null;
       }


}
