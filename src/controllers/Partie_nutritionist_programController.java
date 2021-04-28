/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import Service.programmenutrition_Service;
import entites.programmenutrition;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.ResourceBundle;
import java.util.Set;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.controlsfx.control.textfield.AutoCompletionBinding;
import org.controlsfx.control.textfield.TextFields;

/**
 * FXML Controller class
 *
 * @author Aziz
 */
public class Partie_nutritionist_programController implements Initializable {

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
    private Pane pnl_affichage_user;
    @FXML
    private TextField txt_Seach;
    @FXML
    private TableView<programmenutrition> tabview;
  
    @FXML
    private TableColumn<programmenutrition, Integer> col_user_id;
    @FXML
    private TableColumn<programmenutrition, Integer> col_info_user_nutrition_id;
    @FXML
    private TableColumn<programmenutrition, String> col_repas1;
    @FXML
    private TableColumn<programmenutrition, String> col_repas2;
    @FXML
    private TableColumn<programmenutrition, String> col_repas3;
    @FXML
    private TableColumn<programmenutrition, String> col_repas4;
    @FXML
    private TableColumn<programmenutrition, String>col_repas5;
    @FXML
    private TableColumn<programmenutrition, Integer> col_duree;
    @FXML
    private TableColumn<programmenutrition, String> col_jourrepot;
    
    programmenutrition_Service service = new programmenutrition_Service();
    @FXML
    private Button btn_afficher_profile;
 private AutoCompletionBinding<String> autoCompletionBinding;
       private Set<String> possibleSuggestions ;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
          possibleSuggestions= new HashSet<>(service.get_List_jourrepot_users());
        TextFields.bindAutoCompletion(txt_Seach, service.get_List_jourrepot_users());
    
        search();
        try {
            refreche();
        } catch (SQLException ex) {
             }
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
            
        col_user_id.setCellValueFactory(new PropertyValueFactory<>("user_id"));
        col_info_user_nutrition_id.setCellValueFactory(new PropertyValueFactory<>("info_user_nutrition_id"));
        col_repas1.setCellValueFactory(new PropertyValueFactory<>("repas1"));
        col_repas2.setCellValueFactory(new PropertyValueFactory<>("repas2"));
        col_repas3.setCellValueFactory(new PropertyValueFactory<>("repas3"));   
        col_repas4.setCellValueFactory(new PropertyValueFactory<>("repas4"));   
        col_repas5.setCellValueFactory(new PropertyValueFactory<>("repas5"));   
        col_duree.setCellValueFactory(new PropertyValueFactory<>("duree"));   
        col_jourrepot.setCellValueFactory(new PropertyValueFactory<>("jourrepot"));   

        tabview.getItems().clear();



                    tabview.setItems(service.serach(txt_Seach.getText()));

                } catch (SQLException ex) {
                }
        

            }
        }
        );

    }
      
      
  
       public void refreche() throws SQLException {


        col_user_id.setCellValueFactory(new PropertyValueFactory<>("user_id"));
        col_info_user_nutrition_id.setCellValueFactory(new PropertyValueFactory<>("info_user_nutrition_id"));
        col_repas1.setCellValueFactory(new PropertyValueFactory<>("repas1"));
        col_repas2.setCellValueFactory(new PropertyValueFactory<>("repas2"));
        col_repas3.setCellValueFactory(new PropertyValueFactory<>("repas3"));   
        col_repas4.setCellValueFactory(new PropertyValueFactory<>("repas4"));   
        col_repas5.setCellValueFactory(new PropertyValueFactory<>("repas5"));   
        col_duree.setCellValueFactory(new PropertyValueFactory<>("duree"));   
        col_jourrepot.setCellValueFactory(new PropertyValueFactory<>("jourrepot"));   
         tabview.getItems().clear();       
        tabview.setItems(service.Affichertout_nutrisoniste(7));
       
    }
}
