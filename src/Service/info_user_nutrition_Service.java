/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import Alert.AlertDialog;
import IService.IService;
import Utils.MyConnexion;
import doryan.windowsnotificationapi.fr.Notification;
import entites.info_user_nutrition;
import java.awt.TrayIcon;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.mindrot.jbcrypt.BCrypt;

/**
 *
 * @author Aymen
 */
public class info_user_nutrition_Service  implements IService<info_user_nutrition>  {
    private Connection c = MyConnexion.getInsCon().getcnx();
    @Override
    public void Ajouter(info_user_nutrition t) throws SQLException {
 
        
        PreparedStatement ps;

        
        String query = "INSERT INTO `info_user_nutrition`( `user_id`, `programmenutrition_id`, `abonnement_id`, `nutritionist_id`, `ojectif`, `blessure`, `mangezpas`, `supplementali`, `probleme`, `age`, `taille`, `poids`, `sexe`) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?)";
        try {
            ps = c.prepareStatement(query);
             ps.setInt(1, t.getUser_id());
               ps.setInt(2, t.getProgrammenutrition_id());
                 ps.setInt(3, t.getAbonnement_id());
          ps.setInt(4, t.getNutritionist_id());
         ps.setString(5, t.getOjectif());
         ps.setString(6, t.getBlessure());
          ps.setString(7, t.getMangezpas());
         ps.setString(8, t.getSupplementali());
         ps.setString(9, t.getProbleme());
         ps.setInt(10, t.getAge());
         ps.setInt(11, t.getTaille());
          ps.setDouble(12, t.getPoids());
           ps.setString(13, t.getSexe());
            ps.execute();
    } catch (Exception e) {
              
       
            System.out.println(e);

        }
    }

    @Override
    public void Supprimer(int id) throws SQLException {
     PreparedStatement ps;

        String query = "DELETE FROM `info_user_nutrition` WHERE `id`=?  ";
      //  Acheter_Service s = new Acheter_Service();
      //  s.SupprimerU(id);
        try {
            ps = c.prepareStatement(query);

            ps.setInt(1, id);

            ps.execute();

            System.out.println("suppression de USer");
        } catch (Exception e) {
            System.out.println(e);
        } }

    @Override
    public void Modifier(info_user_nutrition t, int id) throws SQLException {
   PreparedStatement ps;
        String query = "UPDATE `info_user_nutrition` SET `programmenutrition_id`=? WHERE id=?";
        try {
            
            ps = c.prepareStatement(query);
            ps.setInt(1, t.getProgrammenutrition_id());
              ps.setInt(2,id);
          
            ps.execute();
       
        } catch (Exception e) {
             AlertDialog.showNotification("Erreur ! ", e.getMessage(), AlertDialog.image_cross);
    }  }

    @Override
    public ObservableList<info_user_nutrition> Affichertout() throws SQLException {
     ObservableList<info_user_nutrition> list = FXCollections.observableArrayList();
        String requete = "select * from info_user_nutrition ";
        try {
            PreparedStatement ps = c.prepareStatement(requete);
            
            ResultSet rs = ps.executeQuery();
            while (rs.next())
            {                                                                                                                                                                                                                                                        
                list.add(new info_user_nutrition(rs.getInt("id"),rs.getInt("user_id"),rs.getInt("programmenutrition_id"),rs.getInt("abonnement_id"),rs.getInt("nutritionist_id"),rs.getString("ojectif"),rs.getString("blessure"),rs.getString("mangezpas"),rs.getString("supplementali"),rs.getString("probleme"),rs.getString("sexe"),rs.getInt("age"),rs.getInt("taille"),rs.getDouble("poids")));
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return list; 
    }
     public ObservableList<info_user_nutrition> Affichertout_nutrisoniste(int id) throws SQLException {
     ObservableList<info_user_nutrition> list = FXCollections.observableArrayList();
        String requete = "select * from info_user_nutrition where `nutritionist_id` = ? ";
        try {
            PreparedStatement ps = c.prepareStatement(requete);
                ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next())
            {                                                                                                                                                                                                                                                        
                list.add(new info_user_nutrition(rs.getInt("id"),rs.getInt("user_id"),rs.getInt("programmenutrition_id"),rs.getInt("abonnement_id"),rs.getInt("nutritionist_id"),rs.getString("ojectif"),rs.getString("blessure"),rs.getString("mangezpas"),rs.getString("supplementali"),rs.getString("probleme"),rs.getString("sexe"),rs.getInt("age"),rs.getInt("taille"),rs.getDouble("poids")));
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return list; 
    }
       public ObservableList<info_user_nutrition> serach(String cas) throws SQLException {
        ObservableList<info_user_nutrition> list = FXCollections.observableArrayList();
        String requete = "select * from info_user_nutrition where ( ojectif LIKE '%" + cas + "%' or blessure LIKE '%" + cas + "%' or mangezpas LIKE '%" + cas + "%' or supplementali LIKE '%" + cas   + "%' or probleme LIKE '%" + cas + "%' ) ";
        try {
            PreparedStatement ps = c.prepareStatement(requete);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

         list.add(new info_user_nutrition(rs.getInt("id"),rs.getInt("user_id"),rs.getInt("programmenutrition_id"),rs.getInt("abonnement_id"),rs.getInt("nutritionist_id"),rs.getString("ojectif"),rs.getString("blessure"),rs.getString("mangezpas"),rs.getString("supplementali"),rs.getString("probleme"),rs.getString("sexe"),rs.getInt("age"),rs.getInt("taille"),rs.getDouble("poids")));
         
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return list;
    }
}
