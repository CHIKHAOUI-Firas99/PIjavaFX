/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import Service.rate_Service;
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
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Aziz
 */
public class Profile_nutritionistController implements Initializable {

    @FXML
    private VBox vboxdrawer;
    @FXML
    private ImageView imagechange;
    @FXML
    private Label fullName;
    @FXML
    private Button btn_affichage;
    @FXML
    private Button btn_afficher_programme_Nutriste;
    @FXML
    private Button btn_afficher_profile;
    @FXML
    private Pane pnl_affichage_user;
    @FXML
    private Label rate;
    
    private rate_Service service = new rate_Service();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       String list="";
        try {
            for (rate r : service.Affichertout()) {
                if (r.getId_nutritionist()==1)
                {
                   list+="Nom : "+r.getNom()+" Prenom : "+r.getPrenom()+" rate : "+r.getRate();
                }
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(Profile_nutritionistController.class.getName()).log(Level.SEVERE, null, ex);
        }
        rate.setText(list);
       
    }    

    @FXML
    private void handleClicks(ActionEvent event) throws IOException {
           if (event.getSource() == btn_affichage) {
            Node node = (Node) event.getSource();

            Stage stage = (Stage) node.getScene().getWindow();
            //stage.setMaximized(true);
            stage.close();
            Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/GUI/Partie_nutritionist.fxml")));
            stage.setScene(scene);
            stage.show();

        }
        if (event.getSource() == btn_afficher_programme_Nutriste) {
            Node node = (Node) event.getSource();

            Stage stage = (Stage) node.getScene().getWindow();
            //stage.setMaximized(true);
            stage.close();
            Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/GUI/Partie_nutritionist_program.fxml")));
            stage.setScene(scene);
            stage.show();
        }
        if (event.getSource() == btn_afficher_profile) {
            Node node = (Node) event.getSource();

            Stage stage = (Stage) node.getScene().getWindow();
            //stage.setMaximized(true);
            stage.close();
            Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/GUI/Profile_nutritionist_program.fxml")));
            stage.setScene(scene);
            stage.show();
        }
    }
    
}
