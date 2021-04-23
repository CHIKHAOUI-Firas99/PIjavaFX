/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import Alert.AlertDialog;
import Service.info_user_nutrition_Service;
import Service.programmenutrition_Service;
import entites.info_user_nutrition;
import entites.programmenutrition;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Aziz
 */
public class Partie_nutritionistController implements Initializable {

    @FXML
    private VBox vboxdrawer;
    @FXML
    private ImageView imagechange;
    @FXML
    private Label fullName;
    @FXML
    private Button btn_affichage;
    @FXML
    private Pane pnl_affichage_user;
    @FXML
    private TextField txt_Seach;
    @FXML
    private TableView<info_user_nutrition> tabview;
    @FXML
    private TableColumn<info_user_nutrition, Integer> col_id;
    @FXML
    private TableColumn<info_user_nutrition, Integer> col_user_id;
    @FXML
    private TableColumn<info_user_nutrition, Integer> col_programme_id;
    @FXML
    private TableColumn<info_user_nutrition, String> col_objectif;
    @FXML
    private TableColumn<info_user_nutrition, String> col_blessure;
    @FXML
    private TableColumn<info_user_nutrition, String> col_mangezpas;
    @FXML
    private TableColumn<info_user_nutrition, String> col_supplementali;
    @FXML
    private TableColumn<info_user_nutrition, String> col_probleme;
    @FXML
    private TableColumn<info_user_nutrition, Integer> col_age;
    @FXML
    private TableColumn<info_user_nutrition, Integer> col_taille;
    @FXML
    private TableColumn<info_user_nutrition, Double> col_poids;
    @FXML
    private TableColumn<info_user_nutrition, String> col_sexe;

    @FXML
    private Button btn_afficher_programme_Nutriste;
    @FXML
    private TextField txt_repas_1;
    @FXML
    private TextField txt_repas_2;
    @FXML
    private TextField txt_repas_3;
    @FXML
    private TextField txt_repas_4;
    @FXML
    private TextField txt_repas_5;
    @FXML
    private TextField txt_duree;
    @FXML
    private TextField txt_jourrepot;
    @FXML
    private Button Ajouter_programe_Noutriste;
    
    info_user_nutrition info = new info_user_nutrition();
    info_user_nutrition_Service service = new info_user_nutrition_Service();
    @FXML
    private Button btn_afficher_profile;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        search();
      try {
            refreche();
        } catch (SQLException ex) {
         
        }
                setCellfromtabletotxt();
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
            Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/GUI/Profile_nutritionist.fxml")));
            stage.setScene(scene);
            stage.show();
        }
    }
     private void setCellfromtabletotxt() {
        tabview.setOnMouseClicked(e -> {

            info = tabview.getItems().get(tabview.getSelectionModel().getSelectedIndex());
          
            

        }
        );

    }
    public void refreche() throws SQLException {
        col_id.setCellValueFactory(new PropertyValueFactory<>("id"));
        col_user_id.setCellValueFactory(new PropertyValueFactory<>("user_id"));
        col_programme_id.setCellValueFactory(new PropertyValueFactory<>("programmenutrition_id"));
        col_objectif.setCellValueFactory(new PropertyValueFactory<>("ojectif"));
        col_blessure.setCellValueFactory(new PropertyValueFactory<>("blessure"));
        col_mangezpas.setCellValueFactory(new PropertyValueFactory<>("mangezpas"));   
      col_supplementali.setCellValueFactory(new PropertyValueFactory<>("supplementali"));   
      col_probleme.setCellValueFactory(new PropertyValueFactory<>("probleme"));   
      col_age.setCellValueFactory(new PropertyValueFactory<>("age"));   
      col_taille.setCellValueFactory(new PropertyValueFactory<>("taille"));   
      col_poids.setCellValueFactory(new PropertyValueFactory<>("poids")); 
      col_sexe.setCellValueFactory(new PropertyValueFactory<>("sexe")); 
         tabview.getItems().clear();       
         // f oudh 1 tbdlha id ta nutrisoniste
        tabview.setItems(service.Affichertout_nutrisoniste(7));
       
    }

    @FXML
    private void Ajouter(ActionEvent event) throws SQLException {
            if (txt_repas_1.getText().equals(""))
        {
               AlertDialog.showNotification("Error !","txt_repas_1",AlertDialog.image_cross);

        }
         else if (txt_repas_1.getText().matches("^[0-9]+$")) {
            AlertDialog.showNotification("Erreur txt_repas_1 !", "il faut saisir des caracteres  !", AlertDialog.image_cross);
        }
         else  if (txt_repas_2.getText().equals(""))
        {
               AlertDialog.showNotification("Error !","txt_repas_2",AlertDialog.image_cross);

        }
         else if (txt_repas_2.getText().matches("^[0-9]+$")) {
            AlertDialog.showNotification("Erreur txt_repas_2 !", "il faut saisir des caracteres  !", AlertDialog.image_cross);
        }
         else   if (txt_repas_3.getText().equals(""))
        {
               AlertDialog.showNotification("Error !","txt_repas_3",AlertDialog.image_cross);

        }
         else if (txt_repas_3.getText().matches("^[0-9]+$")) {
            AlertDialog.showNotification("Erreur txt_repas_3 !", "il faut saisir des caracteres  !", AlertDialog.image_cross);
        }
        else   if (txt_repas_4.getText().equals(""))
        {
               AlertDialog.showNotification("Error !","txt_repas_4",AlertDialog.image_cross);

        }
         else if (txt_repas_4.getText().matches("^[0-9]+$")) {
            AlertDialog.showNotification("Erreur txt_repas_4 !", "il faut saisir des caracteres  !", AlertDialog.image_cross);
        }
          else if (txt_repas_5.getText().equals(""))
        {
               AlertDialog.showNotification("Error !","txt_repas_5",AlertDialog.image_cross);

        }
         else if (txt_repas_5.getText().matches("^[0-9]+$")) {
            AlertDialog.showNotification("Erreur txt_repas_5 !", "il faut saisir des caracteres  !", AlertDialog.image_cross);
        }
        else  if (txt_duree.getText().equals(""))
        {
               AlertDialog.showNotification("Error !","txt_duree",AlertDialog.image_cross);

        }
         else if (txt_duree.getText().matches("^[a-zA-Z]+$")) {
            AlertDialog.showNotification("Erreur ", "duree incorrect", AlertDialog.image_cross);
        } else if (Integer.valueOf(txt_duree.getText()) <= 0) {
            AlertDialog.showNotification("Error !", "Champ de duree", AlertDialog.image_cross);
        }
            else if (txt_jourrepot.getText().equals(""))
        {
               AlertDialog.showNotification("Error !","txt_jourrepot",AlertDialog.image_cross);

        }
         else if (txt_jourrepot.getText().matches("^[0-9]+$")) {
            AlertDialog.showNotification("Erreur txt_jourrepot !", "il faut saisir des caracteres  !", AlertDialog.image_cross);
        }
         else {
             if ( info.getProgrammenutrition_id() == 1)
             {
                 // 1 -> maneha dima nutisoniste id teeou 1 baed f login tbdl 1 hasb el id de nutionist
                 programmenutrition p = new programmenutrition(info.getId(),info.getUser_id() , 7, txt_repas_1.getText(), txt_repas_2.getText(), txt_repas_3.getText(), txt_repas_4.getText(), txt_repas_5.getText(), Integer.valueOf(txt_duree.getText()), txt_jourrepot.getText());
               programmenutrition_Service servi = new programmenutrition_Service();
            
               servi.Ajouter(p);
              int id=servi.recap_nutrisoniste(p);
               System.out.println(id);
                  info_user_nutrition jdid = new info_user_nutrition();
                  jdid.setProgrammenutrition_id(id);
                  service.Modifier(jdid, info.getId());
                        AlertDialog.showNotification("program de nutrition", "program nutrition affecter", AlertDialog.image_checked);

     refreche();
           
             }
             else
             {
                               AlertDialog.showNotification("program de nutrition", "program nutrition deja affecter", AlertDialog.image_cross);
  
             }
             
            
             
            
         
         }
    }
        public void search() {
        txt_Seach.setOnKeyReleased(e
                -> {
            if (txt_Seach.getText().equals("") ) {

                try {
                    refreche();
                } catch (SQLException ex) {
                }

            } else {

                try {
                         col_id.setCellValueFactory(new PropertyValueFactory<>("id"));
        col_user_id.setCellValueFactory(new PropertyValueFactory<>("user_id"));
        col_programme_id.setCellValueFactory(new PropertyValueFactory<>("programmenutrition_id"));
        col_objectif.setCellValueFactory(new PropertyValueFactory<>("ojectif"));
        col_blessure.setCellValueFactory(new PropertyValueFactory<>("blessure"));
        col_mangezpas.setCellValueFactory(new PropertyValueFactory<>("mangezpas"));   
      col_supplementali.setCellValueFactory(new PropertyValueFactory<>("supplementali"));   
      col_probleme.setCellValueFactory(new PropertyValueFactory<>("probleme"));   
      col_age.setCellValueFactory(new PropertyValueFactory<>("age"));   
      col_taille.setCellValueFactory(new PropertyValueFactory<>("taille"));   
      col_poids.setCellValueFactory(new PropertyValueFactory<>("poids")); 
      col_sexe.setCellValueFactory(new PropertyValueFactory<>("sexe")); 

        tabview.getItems().clear();



                    tabview.setItems(service.serach(txt_Seach.getText()));

                } catch (SQLException ex) {
                }
        

            }
        }
        );

    }
      
      
  
}
