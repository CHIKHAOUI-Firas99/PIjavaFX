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
import java.awt.TrayIcon;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.mindrot.jbcrypt.BCrypt;

/**
 *
 * @author aymen
 */
public class User_Service implements IService<User> {



    private Connection c = MyConnexion.getInsCon().getcnx();

    
  
    @Override
    public void Ajouter(User u) throws SQLException {
        
        
        String hashedpw = BCrypt.hashpw(u.getPassword(), BCrypt.gensalt());
             hashedpw = hashedpw.replace("$2a$", "$2y$");
            String UserRole = "[]";
      
        
        PreparedStatement ps;

        
        String query = "INSERT INTO `users`( `email`, `roles`, `password`, `nom`, `prenom`, `tel`) VALUES (?,?,?,?,?,?)";
        try {
            ps = c.prepareStatement(query);
             ps.setString(1, u.getEmail());
             ps.setString(2, UserRole);
             ps.setString(3, hashedpw);
             ps.setString(4, u.getNom());
              ps.setString(5, u.getPrenom());
                ps.setInt(6, u.getTel());
         
            ps.execute();
            
            System.out.println(u);
   AlertDialog.showNotification("Sign UP","Sign up avec succés",AlertDialog.image_checked);    
   Notification.sendNotification("Client ajouté", "un nouveau client est ajouté ", TrayIcon.MessageType.INFO);
        } catch (Exception e) {
              
       
            System.out.println(e);

        }
    }
public ObservableList<String> get_List_email_users() {
        ObservableList<String> list = FXCollections.observableArrayList();

        String requete = "select * from users ";
        try {
            PreparedStatement ps = c.prepareStatement(requete);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                list.add(rs.getString("email"));

            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return list;
    }
    @Override
    public void Supprimer(int id) throws SQLException {
        PreparedStatement ps;

        String query = "DELETE FROM `users` WHERE `id`=?  ";
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
    public void Modifier(User u, int id) throws SQLException {

        PreparedStatement ps;
        String query = "UPDATE `users` SET `roles`=? WHERE  id = ?";
        try {
            
            ps = c.prepareStatement(query);
            ps.setString(1, u.getRoles());
            ps.setInt(2, id);
           
            ps.execute();
        AlertDialog.showNotification("Modification", "Modification avec sucess", AlertDialog.image_checked);
 

        } catch (Exception e) {
             AlertDialog.showNotification("Erreur ! ", e.getMessage(), AlertDialog.image_cross);
    }

   
    }

    @Override
    public ObservableList<User> Affichertout() throws SQLException {
        ObservableList<User> list = FXCollections.observableArrayList();
        String requete = "select * from users ";
        try {
            PreparedStatement ps = c.prepareStatement(requete);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                list.add(new User(rs.getInt("id"),rs.getString("email"),rs.getString("roles"),rs.getString("password"),rs.getInt("tel"),rs.getString("nom"),rs.getString("prenom")));

            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return list;
    }
        public ObservableList<User> Affichertoutpdf() throws SQLException {
        ObservableList<User> list = FXCollections.observableArrayList();
        String requete = "select * from users ";
        try {
            PreparedStatement ps = c.prepareStatement(requete);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
       

         list.add(new User(rs.getInt("id"),rs.getString("email"),rs.getString("roles"),rs.getString("password"),rs.getInt("tel"),rs.getString("nom"),rs.getString("prenom")));

            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return list;
    }

   public int nombre_user() {

        int i = 0;
        String requete = "SELECT COUNT(*) as nbr FROM users where roles='[\"ROLE_NUTRITIONIST\"]'";

        try {
            PreparedStatement ps = c.prepareStatement(requete);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                i = rs.getInt("nbr");

            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return i;
    }


    public User get_User(int id) {
        String requete = "SELECT * FROM `users` WHERE (id =" + String.valueOf(id) + ")";

        try {
            PreparedStatement ps = c.prepareStatement(requete);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

    
                User us =new User(rs.getInt("id"),rs.getString("email"),rs.getString("roles"),rs.getString("password"),rs.getInt("tel"),rs.getString("nom"),rs.getString("prenom"));
                return us;

            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }


    public ObservableList<User> serach(String cas) throws SQLException {
        ObservableList<User> list = FXCollections.observableArrayList();
        String requete = "select * from users where ( email LIKE '%" + cas + "%' or roles LIKE '%" + cas + "%' or nom LIKE '%" + cas + "%' or prenom LIKE '%" + cas   + "%' or tel LIKE '%" + cas + "%' ) ";
        try {
            PreparedStatement ps = c.prepareStatement(requete);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

      list.add(new User(rs.getInt("id"),rs.getString("email"),rs.getString("roles"),rs.getString("password"),rs.getInt("tel"),rs.getString("nom"),rs.getString("prenom")));

            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return list;
    }


}
