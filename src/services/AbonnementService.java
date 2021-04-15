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
     
    
}
