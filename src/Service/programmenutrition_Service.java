/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import IService.IService;
import Utils.MyConnexion;
import entites.programmenutrition;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author Aymen
 */
public class programmenutrition_Service implements IService<programmenutrition> {
   private Connection c = MyConnexion.getInsCon().getcnx();
    @Override
    public void Ajouter(programmenutrition t) throws SQLException {
     PreparedStatement ps;

        
        String query = "INSERT INTO `programmenutrition`( `info_user_nutrition_id`, `user_id`, `nutritionist_id`, `repas1`, `repas2`, `repas3`, `repas4`, `repas5`, `duree`, `jourrepot`) VALUES (?,?,?,?,?,?,?,?,?,?)";
        try {
            ps = c.prepareStatement(query);
             ps.setInt(1, t.getInfo_user_nutrition_id());
               ps.setInt(2, t.getUser_id());
                 ps.setInt(3, t.getNutritionist_id());
                  ps.setString(4, t.getRepas1());
                   ps.setString(5, t.getRepas2());
                    ps.setString(6, t.getRepas3());
                     ps.setString(7, t.getRepas4());
                      ps.setString(8, t.getRepas5());
                       ps.setInt(9, t.getDuree());
         ps.setString(10, t.getJourrepot());
   
            ps.execute();
    } catch (Exception e) {
              
       
            System.out.println(e);

        }
    
    }

    @Override
    public void Supprimer(int id) throws SQLException {
      PreparedStatement ps;

        String query = "DELETE FROM `programmenutrition` WHERE `id`=?  ";
      //  Acheter_Service s = new Acheter_Service();
      //  s.SupprimerU(id);
        try {
            ps = c.prepareStatement(query);

            ps.setInt(1, id);

            ps.execute();

            System.out.println("suppression de USer");
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    @Override
    public void Modifier(programmenutrition t, int id) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ObservableList<programmenutrition> Affichertout() throws SQLException {
    ObservableList<programmenutrition> list = FXCollections.observableArrayList();
        String requete = "select * from programmenutrition ";
        try {
            PreparedStatement ps = c.prepareStatement(requete);
            
            ResultSet rs = ps.executeQuery();
            while (rs.next())
            {                                                                                                                                                                                                                                                        
                list.add(new programmenutrition(rs.getInt("id"), rs.getInt("info_user_nutrition_id"), rs.getInt("user_id"), rs.getInt("nutritionist_id"),  rs.getString("repas1"), rs.getString("repas2"), rs.getString("repas3"),rs.getString("repas4"), rs.getString("repas5"), rs.getInt("duree"),  rs.getString("jourrepot")));
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return list;    }
        public int recap_nutrisoniste(programmenutrition t) throws SQLException {
   int id=0;
         String requete = " select id from `programmenutrition` WHERE nutritionist_id=? and user_id=? and duree=? and repas1=? and repas2=? and repas3=? ";
        try {
            PreparedStatement ps = c.prepareStatement(requete);
                ps.setInt(1, t.getNutritionist_id());
  ps.setInt(2, t.getUser_id());
    ps.setInt(3, t.getDuree());
    ps.setString(4, t.getRepas1());
    ps.setString(5, t.getRepas2());
    ps.setString(6, t.getRepas3());
            ResultSet rs = ps.executeQuery();
           
            while (rs.next())
            {                                                                                                                                                                                                                                                        
      id=rs.getInt("id")  ;    }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return id;   
        }     
          public ObservableList<programmenutrition> serach(String cas) throws SQLException {
        ObservableList<programmenutrition> list = FXCollections.observableArrayList();
        String requete = "select * from programmenutrition where ( repas1 LIKE '%" + cas + "%' or repas2 LIKE '%" + cas + "%' or repas3 LIKE '%" + cas + "%' or repas4 LIKE '%" + cas   + "%' or repas5 LIKE '%" + cas + "%' ) ";
        try {
            PreparedStatement ps = c.prepareStatement(requete);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

            list.add(new programmenutrition(rs.getInt("id"), rs.getInt("info_user_nutrition_id"), rs.getInt("user_id"), rs.getInt("nutritionist_id"),  rs.getString("repas1"), rs.getString("repas2"), rs.getString("repas3"),rs.getString("repas4"), rs.getString("repas5"), rs.getInt("duree"),  rs.getString("jourrepot")));
        
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return list;
    }
     public ObservableList<programmenutrition> Affichertout_nutrisoniste(int id) throws SQLException {
    ObservableList<programmenutrition> list = FXCollections.observableArrayList();
         String requete = "select * from programmenutrition where `nutritionist_id` = ? ";
        try {
            PreparedStatement ps = c.prepareStatement(requete);
                     ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next())
            {                                                                                                                                                                                                                                                        
                list.add(new programmenutrition(rs.getInt("id"), rs.getInt("info_user_nutrition_id"), rs.getInt("user_id"), rs.getInt("nutritionist_id"),  rs.getString("repas1"), rs.getString("repas2"), rs.getString("repas3"),rs.getString("repas4"), rs.getString("repas5"), rs.getInt("duree"),  rs.getString("jourrepot")));
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return list;    }
      public ObservableList<programmenutrition> Affichertout_user(int id) throws SQLException {
    ObservableList<programmenutrition> list = FXCollections.observableArrayList();
         String requete = "select * from programmenutrition where `user_id` = ? ";
        try {
            PreparedStatement ps = c.prepareStatement(requete);
                     ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next())
            {                                                                                                                                                                                                                                                        
                list.add(new programmenutrition(rs.getInt("id"), rs.getInt("info_user_nutrition_id"), rs.getInt("user_id"), rs.getInt("nutritionist_id"),  rs.getString("repas1"), rs.getString("repas2"), rs.getString("repas3"),rs.getString("repas4"), rs.getString("repas5"), rs.getInt("duree"),  rs.getString("jourrepot")));
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return list;    }
}
