/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import IService.IService;
import Utils.MyConnexion;
import entites.User;
import entites.rate;
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
public class rate_Service  implements IService<rate>{

    private Connection c = MyConnexion.getInsCon().getcnx();

    
    @Override
    public void Ajouter(rate u) throws SQLException {
         PreparedStatement ps;
        
        
        String query = "INSERT INTO `rate`( `id_user`, `id_nutritionist`, `rate`, `nom`, `prenom`) VALUES (?,?,?,?,?)";
        try {
            ps = c.prepareStatement(query);
             ps.setInt(1, u.getId_user());
             ps.setInt(2, u.getId_nutritionist());
             ps.setInt(3, u.getRate());
             ps.setString(4, u.getNom());
       ps.setString(5, u.getPrenom());
         
            ps.execute();
         
       } catch (Exception e) {
              
       
            System.out.println(e);

        }  }

    @Override
    public void Supprimer(int t) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void Modifier(rate t, int id) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ObservableList<rate> Affichertout() throws SQLException {
   ObservableList<rate> list = FXCollections.observableArrayList();
        String requete = "select * from rate ";
        try {
            PreparedStatement ps = c.prepareStatement(requete);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
       

         list.add(new rate(rs.getInt("id_rate"),rs.getInt("id_user"),rs.getInt("id_nutritionist"),rs.getInt("rate"),rs.getString("nom"),rs.getString("prenom")));

            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return list;  }
    
}
