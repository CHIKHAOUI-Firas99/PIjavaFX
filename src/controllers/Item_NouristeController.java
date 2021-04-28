/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import Alert.AlertDialog;
import Service.nutritionist_Service;
import Service.rate_Service;
import entites.nutritionist;
import entites.rate;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import org.controlsfx.control.Rating;

/**
 * FXML Controller class
 *
 * @author Aziz
 */
public class Item_NouristeController implements Initializable {

    @FXML
    private HBox itemC;
    @FXML
    private Label libelle;
    @FXML
    private Button btn_produit_dispo;
    @FXML
    private Label email;
    @FXML
    private Label diet;
        nutritionist_Service service = new nutritionist_Service();
    nutritionist nutritionis = null;
static nutritionist nutritionis_pour_affichage_program; 
    @FXML
    private Rating rating;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
     nutritionis = service.get_nutritionist(FrontCrontroller.indiceNouristes);

        libelle.setText(nutritionis.getNom() + " " +nutritionis.getPrenom());
   
        email.setText(nutritionis.getEmail());
           diet.setText(nutritionis.getDiet());
           
           
           
            rate_Service service = new rate_Service();
        int exist = 0;
        int stars = 0;
        try {
            for (rate rw : service.Affichertout()) {
                if (rw.getId_nutritionist()==nutritionis.getId() && rw.getId_user() == 5)
                {
                   exist=1;
                   stars=rw.getRate();
                }
                   
                
            }
        } catch (SQLException ex) {
        }
             if (exist ==1 )
        {
            rating.setRating(stars);
            rating.setDisable(true);
        
        }
           
        
    }    

    @FXML
    private void handleClicks(ActionEvent event) throws IOException {
             if(event.getSource()==btn_produit_dispo)
        {     
     System.out.println(nutritionis.getId());            
            nutritionis_pour_affichage_program=nutritionis;    
            Node node = (Node) event.getSource();
            Stage stage = (Stage) node.getScene().getWindow();
            //stage.setMaximized(true);
            stage.close();
            Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/GUI/Affichageprograms_nutriste.fxml")));
            stage.setScene(scene);
            stage.show();

        }
    }

    @FXML
    private void Rate(MouseEvent event) throws SQLException {
        rate r = new rate();
   
          int stars = (int) rating.getRating();
          r.setRate(stars);
          System.out.println("id" + nutritionis.getId());
          r.setId_nutritionist(nutritionis.getId());
             r.setId_user(5);
             r.setNom("boughattas");
             r.setPrenom("Aziz");
        rate_Service service = new rate_Service();
        int exist = 0;
        for (rate rw : service.Affichertout()) {
            if (rw.getId_nutritionist()==nutritionis.getId() && rw.getId_user() == 5)
                exist=1;
            
        }
        if (exist ==0 )
        {
            service.Ajouter(r);
                AlertDialog.showNotification("Rate","Rate : "+stars,AlertDialog.image_checked);

        }
        else
        {
               AlertDialog.showNotification("error","deja Rate "+stars,AlertDialog.image_cross);
   
        }
        
    }
    
}
