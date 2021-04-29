/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import IService.IService;
import Utils.MyConnexion;
import entites.abonnement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author Aymen
 */
public class abonnement_Service implements IService<abonnement> {



    private Connection c = MyConnexion.getInsCon().getcnx();

    @Override
    public void Ajouter(abonnement u) throws SQLException {
         
        PreparedStatement ps;

        
        String query = "INSERT INTO `abonnement`( `prix`, `type`, `labelle`, `quantite`, `image`,`id_nutisonist`) VALUES (?,?,?,?,?,?)";
        try {
            ps = c.prepareStatement(query);
             ps.setInt(1, u.getPrix());
             ps.setString(2, u.getType());
             ps.setString(3, u.getLabelle());
             ps.setInt(4, u.getQuantite());
              ps.setString(5, u.getImage());
               ps.setInt(6, u.getId_nutisonist());
            
         
            ps.execute();
            
            System.out.println(u);
        } catch (Exception e) {
              
       
            System.out.println(e);

        }
  }
     public abonnement get_produit_affichage(int i) {
        abonnement p = null;
        int nombre = 0;
      String requete = "SELECT * FROM  abonnement where quantite!=0 "       ;
         try {
            PreparedStatement ps = c.prepareStatement(requete);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                if (i == nombre) {
                    
       p=new abonnement(rs.getInt("id"),rs.getInt("prix"),rs.getString("type"),rs.getString("labelle"),rs.getInt("quantite"),rs.getString("image"),rs.getInt("id_nutisonist"));
               
                }
                nombre++;
                         }

        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return p;

    }

    @Override
    public void Supprimer(int t) throws SQLException {
     PreparedStatement ps;

        String query = "DELETE FROM `abonnement` WHERE `id`=?  ";
      //  Acheter_Service s = new Acheter_Service();
      //  s.SupprimerU(id);
        try {
            ps = c.prepareStatement(query);

            ps.setInt(1, t);

            ps.execute();

            System.out.println("suppression de USer");
        } catch (Exception e) {
            System.out.println(e);
        } }
  public int Affichertaille() throws SQLException {
        int nbr = 0;        
        String requete = "SELECT COUNT(*) as nbr FROM `abonnement` where quantite!=0"       ;
                try {
            PreparedStatement ps = c.prepareStatement(requete);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
              nbr=rs.getInt("nbr");
            }
                   
        } catch (SQLException ex) {
            System.out.println(ex);
        }
                        System.out.println("le nombre est : "+nbr);   
        return nbr;

    }
    @Override
    public void Modifier(abonnement u, int id) throws SQLException {
   PreparedStatement ps;
        String query = "UPDATE `abonnement` SET `prix`=?,`type`=?,`labelle`=?,`quantite`=?,`image`=? WHERE  id = ?";
        try {
            
            ps = c.prepareStatement(query);
        ps.setInt(1, u.getPrix());
             ps.setString(2, u.getType());
             ps.setString(3, u.getLabelle());
             ps.setInt(4, u.getQuantite());
              ps.setString(5, u.getImage());
               ps.setInt(6, id);
           
            ps.execute();
    

        } catch (Exception e) {
        }
    
    }

    @Override
    public ObservableList<abonnement> Affichertout() throws SQLException {
    ObservableList<abonnement> list = FXCollections.observableArrayList();
      String requete = "select * from abonnement ";
        try {
            PreparedStatement ps = c.prepareStatement(requete);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                list.add(new abonnement(rs.getInt("id"),rs.getInt("prix"),rs.getString("type"),rs.getString("labelle"),rs.getInt("quantite"),rs.getString("image"),rs.getInt("id_nutisonist")));

            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return list;
    }
       public ObservableList<abonnement> serach(String cas) throws SQLException {
        ObservableList<abonnement> list = FXCollections.observableArrayList();
        String requete = "SELECT * FROM  abonnement p where  p.labelle LIKE '%" + cas + "%'or  p.type LIKE '%" + cas + "%' or  p.prix LIKE '%" + cas   + "%' or  p.quantite LIKE '%" + cas + "%' ";
       try {
            PreparedStatement ps = c.prepareStatement(requete);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
  list.add(new abonnement(rs.getInt("id"),rs.getInt("prix"),rs.getString("type"),rs.getString("labelle"),rs.getInt("quantite"),rs.getString("image"),rs.getInt("id_nutisonist")));

            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return list;
    }
            public ObservableList<Integer> read_ids()

    {
        ObservableList<Integer> mylist= FXCollections.observableArrayList();

        Statement st;
        try {
            PreparedStatement ps = c.prepareStatement("SELECT id FROM `users` WHERE roles='[\"ROLE_NUTRITIONIST\"]'");
            ResultSet res = ps.executeQuery();
            while (res.next()){
        
                mylist.add(res.getInt("id"));
            }


        } catch (SQLException ex) {
           
        }
        System.out.print(mylist);
        return mylist;
    }
}
