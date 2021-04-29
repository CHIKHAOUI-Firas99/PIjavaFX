/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import IService.IService;
import Utils.MyConnexion;
import entites.panier;
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
public class panier_Service implements IService<panier> {



    private Connection c = MyConnexion.getInsCon().getcnx();

    @Override
    public void Ajouter(panier u) throws SQLException {
      PreparedStatement ps;

        
        String query = "INSERT INTO `panier`( `produit_id`, `user_id`, `quantite`,`Date_Debut`, `Date_Fin`) VALUES (?,?,?,?,?)";
        try {
            ps = c.prepareStatement(query);
             ps.setInt(1, u.getId_produit());
          ps.setInt(2, u.getId_user());
             ps.setInt(3, u.getQuantite());
               ps.setDate(4, u.getDate_Debut());
                  ps.setDate(5, u.getDate_Fin());
         
            ps.execute();
            
            System.out.println(u);
        } catch (Exception e) {
              
       
            System.out.println(e);

        }
    
    }
 public int prixtotal()
       {
           int total=0;
               String requete = "SELECT SUM(p.prix * pa.quantite) as total FROM `panier` pa inner join abonnement p where p.id = pa.produit_id";
                   
                     try {
            PreparedStatement ps = c.prepareStatement(requete);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                total= rs.getInt("total");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }    
           
           return total;
       }
    @Override
    public void Supprimer(int t) throws SQLException {
    PreparedStatement ps;

        String query = "DELETE FROM `panier` WHERE `id`=?  ";
      //  Acheter_Service s = new Acheter_Service();
      //  s.SupprimerU(id);
        try {
            ps = c.prepareStatement(query);

            ps.setInt(1, t);

            ps.execute();

            System.out.println("suppression de USer");
        } catch (Exception e) {
            System.out.println(e);
        }  }

    @Override
    public void Modifier(panier t, int id) throws SQLException {
     PreparedStatement ps;
        String query = "UPDATE `panier` SET `quantite`=? WHERE  id = ?";
        try {
            
            ps = c.prepareStatement(query);
        ps.setInt(1, t.getQuantite());
          
               ps.setInt(2, id);
           
            ps.execute();
    

        } catch (Exception e) {
        }  }

    @Override
    public ObservableList<panier> Affichertout() throws SQLException {
        ObservableList<panier> list = FXCollections.observableArrayList();
      String requete = "SELECT pa.id, `produit_id`, `user_id`,pa.Date_Debut,pa.Date_Fin, pa.quantite,labelle FROM `panier` pa INNER JOIN abonnement p WHERE p.id = pa.produit_id";

       try {
            PreparedStatement ps = c.prepareStatement(requete);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                list.add(new panier(rs.getInt("id"),rs.getInt("produit_id"),rs.getInt("user_id"),rs.getInt("quantite"),rs.getString("labelle"),rs.getDate("Date_Debut"),rs.getDate("Date_Fin")));

            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return list;
  
    
    }
     public ObservableList<panier> Affichertout_user(int id) throws SQLException {
        ObservableList<panier> list = FXCollections.observableArrayList();
       String requete = "SELECT pa.id, `produit_id`, `user_id`,pa.Date_Debut,pa.Date_Fin, pa.quantite,labelle FROM `panier` pa INNER JOIN abonnement p WHERE p.id = pa.produit_id and user_id="+id;

       try {
            PreparedStatement ps = c.prepareStatement(requete);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

           list.add(new panier(rs.getInt("id"),rs.getInt("produit_id"),rs.getInt("user_id"),rs.getInt("quantite"),rs.getString("labelle"),rs.getDate("Date_Debut"),rs.getDate("Date_Fin")));

            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return list;
  
    
    }
}
