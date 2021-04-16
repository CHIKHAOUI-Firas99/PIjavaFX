/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import Alert.AlertDialog;
import Service.info_user_nutrition_Service;
import Service.nutritionist_Service;
import static controllers.Item_NouristeController.nutritionis_pour_affichage_program;
import entites.info_user_nutrition;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Aymen
 */
public class Affichageprograms_nutriste_Front implements Initializable {

    @FXML
    private TextField txt_objectif;
    @FXML
    private TextField txt_blessure;
    @FXML
    private TextField txt_mangezpas;
    @FXML
    private TextField txt_supplementali;
    @FXML
    private Button btn_Ajout;
    @FXML
    private TextField txt_probleme;
    @FXML
    private TextField txt_age;
    @FXML
    private TextField txt_taille;
    @FXML
    private TextField txt_poids;
    @FXML
    private TextField txt_sexe;
    @FXML
    private Button btn_Back_front;
    @FXML
    private Pane pnl_nutration;

    info_user_nutrition_Service service = new info_user_nutrition_Service();
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void handleClicks(ActionEvent event) throws IOException {
          if (event.getSource() == btn_Back_front) {
            Node node = (Node) event.getSource();
            Stage stage = (Stage) node.getScene().getWindow();
            //stage.setMaximized(true);
            stage.close();
            Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/GUI/Front.fxml")));
            stage.setScene(scene);
            stage.show();
        }
          
    }

    @FXML
    private void Ajouter(ActionEvent event) throws SQLException {
            if (txt_objectif.getText().equals(""))
        {
               AlertDialog.showNotification("Error !","txt_objectif",AlertDialog.image_cross);

        }
         else if (txt_objectif.getText().matches("^[0-9]+$")) {
            AlertDialog.showNotification("Erreur txt_objectif !", "il faut saisir des caracteres  !", AlertDialog.image_cross);
        }
         else   if (txt_blessure.getText().equals(""))
        {
               AlertDialog.showNotification("Error !","txt_blessure",AlertDialog.image_cross);

        }
         else if (txt_blessure.getText().matches("^[0-9]+$")) {
            AlertDialog.showNotification("Erreur txt_blessure !", "il faut saisir des caracteres  !", AlertDialog.image_cross);
        }
          else   if (txt_mangezpas.getText().equals(""))
        {
               AlertDialog.showNotification("Error !","txt_mangezpas",AlertDialog.image_cross);

        }
         else if (txt_mangezpas.getText().matches("^[0-9]+$")) {
            AlertDialog.showNotification("Erreur txt_mangezpas !", "il faut saisir des caracteres  !", AlertDialog.image_cross);
        }
          else   if (txt_supplementali.getText().equals(""))
        {
               AlertDialog.showNotification("Error !","txt_supplementali",AlertDialog.image_cross);

        }
         else if (txt_supplementali.getText().matches("^[0-9]+$")) {
            AlertDialog.showNotification("Erreur txt_supplementali !", "il faut saisir des caracteres  !", AlertDialog.image_cross);
        }
         
          else   if (txt_probleme.getText().equals(""))
        {
               AlertDialog.showNotification("Error !","txt_probleme",AlertDialog.image_cross);

        }
         else if (txt_probleme.getText().matches("^[0-9]+$")) {
            AlertDialog.showNotification("Erreur txt_probleme !", "il faut saisir des caracteres  !", AlertDialog.image_cross);
        }
         
        else  if (txt_age.getText().equals(""))
        {
               AlertDialog.showNotification("Error !","txt_age",AlertDialog.image_cross);

        }
         else if (txt_age.getText().matches("^[a-zA-Z]+$")) {
            AlertDialog.showNotification("Erreur ", "txt_age incorrect", AlertDialog.image_cross);
        } else if (Integer.valueOf(txt_age.getText()) <= 0) {
            AlertDialog.showNotification("Error !", "Champ de txt_age", AlertDialog.image_cross);
        }
               else  if (txt_taille.getText().equals(""))
        {
               AlertDialog.showNotification("Error !","txt_taille",AlertDialog.image_cross);

        }
         else if (txt_taille.getText().matches("^[a-zA-Z]+$")) {
            AlertDialog.showNotification("Erreur ", "txt_taille incorrect", AlertDialog.image_cross);
        } else if (Integer.valueOf(txt_taille.getText()) <= 0) {
            AlertDialog.showNotification("Error !", "Champ de txt_taille", AlertDialog.image_cross);
        }
            else  if (txt_poids.getText().equals(""))
        {
               AlertDialog.showNotification("Error !","txt_poids",AlertDialog.image_cross);

        }
         else if (txt_poids.getText().matches("^[a-zA-Z]+$")) {
            AlertDialog.showNotification("Erreur ", "txt_poids incorrect", AlertDialog.image_cross);
        } else if (Integer.valueOf(txt_poids.getText()) <= 0) {
            AlertDialog.showNotification("Error !", "Champ de txt_poids", AlertDialog.image_cross);
        }
        else   if (txt_sexe.getText().equals(""))
        {
               AlertDialog.showNotification("Error !","txt_sexe",AlertDialog.image_cross);

        }
         else if (txt_sexe.getText().matches("^[0-9]+$")) {
            AlertDialog.showNotification("Erreur txt_sexe !", "il faut saisir des caracteres  !", AlertDialog.image_cross);
        }
         
        
            else
        {
            info_user_nutrition info = new info_user_nutrition(5, 1, 1, nutritionis_pour_affichage_program.getId_noutri(),txt_objectif.getText(), txt_blessure.getText(), txt_mangezpas.getText(), txt_supplementali.getText(), txt_probleme.getText(), txt_sexe.getText(), Integer.valueOf(txt_age.getText()), Integer.valueOf(txt_taille.getText()), Double.valueOf(txt_poids.getText()));
            service.Ajouter(info);
                AlertDialog.showNotification("Ajout!", "Ajout!", AlertDialog.image_checked);
       
        }
    }
    
}
