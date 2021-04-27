/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.Entrainement;
import entities.Coach;
import java.sql.Connection;
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
public class EntrainementService {
   
    private Connection c;
    private Statement ste;
    private PreparedStatement pst ;
    public EntrainementService() {
    c = MyConnection.getInstance().getCnx();
     }
     public void ajouterEntrainement(Entrainement d) throws SQLException {
       
        try {
            String requeteInsert = "INSERT INTO Entrainement ( `id` , `coach_id` , `titre` , `jour`, `heure`, `type`, `meet` ) VALUES ('"+d.getId()+"', '"+d.getCoach_id()+"', '"+d.getTitre()+"' , '"+d.getJour()+"' , '"+d.getHeure()+"','"+d.getType()+"' ,'"+d.getMeet()+""+"')";
            ste = c.createStatement();
            ste.executeUpdate(requeteInsert);    
           System.out.println("L'Entrainement est ajoutée"); 
        } catch (SQLException ex) {
            Logger.getLogger(EntrainementService.class.getName()).log(Level.SEVERE, null, ex);
        }
                        
    }
    
    
    public List<Entrainement> readAllEntrainements() throws SQLException {
        List<Entrainement> arr = new ArrayList<>();
        String req = ("SELECT * FROM Entrainement");
        ste = c.createStatement();
        ResultSet rs = ste.executeQuery(req);
            while (rs.next()) {
            Entrainement p = new Entrainement();         
            p.setId(rs.getInt(1));
            p.setCoach_id(rs.getInt(2));
            p.setTitre(rs.getString(3));
            p.setJour(rs.getString(4));
            p.setHeure(rs.getInt(5));
            p.setType(rs.getString(6));
            p.setMeet(rs.getString(7)); 
            arr.add(p);
        }
        return arr;
    }
     public List<Entrainement> getEntrainementByCoachId(int id) throws SQLException {
        List<Entrainement> arr = new ArrayList<>();
        String req = ("SELECT * FROM Entrainement where coach_id= '" + id + "'");
        ste = c.prepareStatement(req);
        ResultSet rs = ste.executeQuery(req);
       
        while (rs.next()) {
            Entrainement p = new Entrainement();
            p.setId(rs.getInt(1));
            p.setCoach_id(rs.getInt(2));
            p.setTitre(rs.getString(3));
            p.setJour(rs.getString(4));
            p.setHeure(rs.getInt(5));
            p.setType(rs.getString(6));
            p.setMeet(rs.getString(7));  
            arr.add(p);
        }
        return arr;
    }
    
    
         public void updateEntrainement(Entrainement u, int id){
        try {
            String requete = " update Entrainement set titre=? , jour=? , heure=? , type=? , meet=?  where id='" + id + "'";
            pst = c.prepareStatement(requete);
            pst.setString(1, u.getTitre());
            pst.setString(2, u.getJour());
            pst.setInt(3, u.getHeure());
            pst.setString(4, u.getType());
            pst.setString(5, u.getMeet());
                
            pst.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(EntrainementService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
         
           
    public void supprimerEntrainement(int id){
        try {
            PreparedStatement pt = c.prepareStatement("delete from Entrainement where id = ?");
            pt.setInt(1, id);
            pt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }
  
    }
 
    
}
