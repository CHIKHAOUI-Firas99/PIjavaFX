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
import entites.User;
import entites.nutritionist;
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
public class nutritionist_Service implements IService<nutritionist>{
  
 private Connection c = MyConnexion.getInsCon().getcnx();
  private User_Service service = new User_Service();
    @Override
    public void Ajouter(nutritionist u) throws SQLException {
             
        
        PreparedStatement ps;
        
        
        String query = "INSERT INTO `nutritionist`( `user_id`, `diet`, `salary`, `note`) VALUES (?,?,?,?)";
        try {
            ps = c.prepareStatement(query);
             ps.setInt(1, u.getId());
             ps.setString(2, u.getDiet());
             ps.setDouble(3, u.getSalary());
             ps.setInt(4, u.getNote());
      
         
            ps.execute();
         
       } catch (Exception e) {
              
       
            System.out.println(e);

        }   }

    @Override
    public void Supprimer(int t) throws SQLException {
       PreparedStatement ps;


      //  Acheter_Service s = new Acheter_Service();
      //  s.SupprimerU(id);
      
      service.Supprimer(t);
              String query = "DELETE FROM `nutritionist` WHERE `user_id`=?  ";
        try {
            ps = c.prepareStatement(query);

            ps.setInt(1, t);

            ps.execute();

            System.out.println("suppression de USer");
        } catch (Exception e) {
            System.out.println(e);
        }  }

    @Override
    public void Modifier(nutritionist t, int id) throws SQLException {
        PreparedStatement ps;
        String query = "UPDATE `nutritionist` SET `diet`=?,`salary`=?,`note`=? WHERE  user_id = ?";
        try {
            
            ps = c.prepareStatement(query);
            ps.setString(1, t.getDiet());
              ps.setDouble(2, t.getSalary());
                ps.setInt(3, t.getNote());
            ps.setInt(4, id);
           
            ps.execute();
        AlertDialog.showNotification("Modification", "Modification avec sucess", AlertDialog.image_checked);
 

        } catch (Exception e) {
             AlertDialog.showNotification("Erreur ! ", e.getMessage(), AlertDialog.image_cross);
    }

     }

    @Override
    public ObservableList<nutritionist> Affichertout() throws SQLException {
     ObservableList<nutritionist> list = FXCollections.observableArrayList();
        String requete = "SELECT u.id, `email`,password, `roles`, `nom`, `prenom`, `tel`,`diet`, `salary`, `note` FROM `nutritionist` n INNER JOIN users u WHERE u.id=n.user_id ";
        try {
            PreparedStatement ps = c.prepareStatement(requete);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
   

                list.add(new nutritionist(rs.getString("diet"),rs.getDouble("salary"),rs.getInt("note"),rs.getInt("id"),rs.getString("email"),rs.getString("roles"),rs.getString("password"),rs.getInt("tel"),rs.getString("nom"),rs.getString("prenom")));

            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return list;
    }
    
public nutritionist get_nutritionist(int i) {
        nutritionist nutritionis = null;
        int nombre = 0;
        String requete = "SELECT u.id, n.id as idd,`email`,password, `roles`, `nom`, `prenom`, `tel`,`diet`, `salary`, `note` FROM `nutritionist` n INNER JOIN users u WHERE u.id=n.user_id";
        try {
            PreparedStatement ps = c.prepareStatement(requete);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                if (i == nombre) {
                    nutritionis=new nutritionist(rs.getString("diet"),rs.getDouble("salary"),rs.getInt("note"),rs.getInt("id"),rs.getString("email"),rs.getString("roles"),rs.getString("password"),rs.getInt("tel"),rs.getString("nom"),rs.getString("prenom"));
nutritionis.setId_noutri(rs.getInt("idd"));
                   
                }
                nombre++;

            }

        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return nutritionis;

    }
      public ObservableList<nutritionist> serach(String cas) throws SQLException {
        ObservableList<nutritionist> list = FXCollections.observableArrayList();
        String requete = "SELECT u.id, `email`,password, `roles`, `nom`, `prenom`, `tel`,`diet`, `salary`, `note` FROM `nutritionist` n INNER JOIN users u WHERE u.id=n.user_id and ( email LIKE '%" + cas + "%' or prenom LIKE '%" + cas + "%' or nom LIKE '%" + cas + "%' or tel LIKE '%" + cas + "%' or diet LIKE '%" + cas + "%' or salary LIKE '%" + cas + "%' or note LIKE '%" + cas + "%' ) ";
        try {
            PreparedStatement ps = c.prepareStatement(requete);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

       list.add(new nutritionist(rs.getString("diet"),rs.getDouble("salary"),rs.getInt("note"),rs.getInt("id"),rs.getString("email"),rs.getString("roles"),rs.getString("password"),rs.getInt("tel"),rs.getString("nom"),rs.getString("prenom")));

            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return list;
    }
}
