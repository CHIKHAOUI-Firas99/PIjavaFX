/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import Alert.AlertDialog;
import Service.User_Service;
import Service.nutritionist_Service;
import entites.User;
import entites.nutritionist;
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
public class homeController implements Initializable {

    @FXML
    private VBox vboxdrawer;
    @FXML
    private ImageView imagechange;
    @FXML
    private Label fullName;
    @FXML
    private Button btn_affichage;
    @FXML
    private Button btn_affichage_Nutriste;
    @FXML
    private Pane pnl_affichage_user;
    @FXML
    private TextField txt_Seach;
    @FXML
    private Label Nombre_User_Nutriste;
    @FXML
    private TableView<User> tabview;
    @FXML
    private TableColumn<User, String> col_email;
    @FXML
    private TableColumn<User, String> col_roles;
    @FXML
    private TableColumn<User, String> col_nom;
    @FXML
    private TableColumn<User, String> col_prenom;
    @FXML
    private TableColumn<User, Integer> col_tel;

    @FXML
    private TextField txt_diet;
    @FXML
    private Button Ajouter_Noutriste;
    @FXML
    private TextField txt_salary;
    @FXML
    private TextField txt_note;
 User_Service service = new User_Service();
    nutritionist_Service service_nutrist = new nutritionist_Service();
  User user ;
  private AutoCompletionBinding<String> autoCompletionBinding;
    private Set<String> possibleSuggestions ;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
           possibleSuggestions= new HashSet<>(service.get_List_email_users());
        TextFields.bindAutoCompletion(txt_Seach, service.get_List_email_users());
     
                  search();
           try {
            refreche();
        } catch (SQLException ex) {
         
        }
                setCellfromtabletotxt();
   Nombre_User_Nutriste.setText(String.valueOf(service.nombre_user()));
      
    }    

    @FXML
    private void handleClicks(ActionEvent event) throws IOException {
           if (event.getSource() == btn_affichage) {
            Node node = (Node) event.getSource();

            Stage stage = (Stage) node.getScene().getWindow();
            //stage.setMaximized(true);
            stage.close();
            Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/GUI/Home.fxml")));
            stage.setScene(scene);
            stage.show();

        }
        if (event.getSource() == btn_affichage_Nutriste) {
            Node node = (Node) event.getSource();

            Stage stage = (Stage) node.getScene().getWindow();
            //stage.setMaximized(true);
            stage.close();
            Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/GUI/Home_nutritionist.fxml")));
            stage.setScene(scene);
            stage.show();
        }
    }

    @FXML
    private void Ajouter(ActionEvent event) throws SQLException {
         if (txt_diet.getText().equals(""))
        {
               AlertDialog.showNotification("Error !","champ diet vide",AlertDialog.image_cross);

        }
         else if (txt_diet.getText().matches("^[0-9]+$")) {
            AlertDialog.showNotification("Erreur Champ diet !", "il faut saisir des caracteres  !", AlertDialog.image_cross);
        }
        else  if (txt_note.getText().equals(""))
        {
               AlertDialog.showNotification("Error !","champ note vide ",AlertDialog.image_cross);

        }
         else if (txt_note.getText().matches("^[a-zA-Z]+$")) {
            AlertDialog.showNotification("Erreur ", "note incorrect", AlertDialog.image_cross);
        } else if (Integer.valueOf(txt_note.getText()) <= 0) {
            AlertDialog.showNotification("Error !", "Champ de note incorrect", AlertDialog.image_cross);
        }
        else  if (txt_salary.getText().equals(""))
        {
               AlertDialog.showNotification("Error !","champ salary vide ",AlertDialog.image_cross);

        }
         else if (txt_salary.getText().matches("^[a-zA-Z]+$")) {
            AlertDialog.showNotification("Erreur!", "salary incorrect", AlertDialog.image_cross);
        }
        else if (Double.valueOf(txt_salary.getText()) <= 0) {
            AlertDialog.showNotification("Error !", "Champ de salary incorrect", AlertDialog.image_cross);
        } 
        else
        {
            System.out.println(user.getRoles());  
            if (user.getRoles().equals("[\"ROLE_NUTRITIONIST\"]"))
            {
                 AlertDialog.showNotification("Error", "Deja Nutrionist", AlertDialog.image_cross);
 
  
            }
            else 
            {
                    user.setRoles("[\"ROLE_NUTRITIONIST\"]");
                service.Modifier(user, user.getId());  
                nutritionist u = new nutritionist();
                
                u.setId(user.getId());
           
                u.setNote(Integer.valueOf(txt_note.getText()));
                 u.setSalary(Double.valueOf(txt_salary.getText()));
                     u.setDiet(txt_diet.getText());
                service_nutrist.Ajouter(u);
                
               AlertDialog.showNotification("Modification ","Nutrisioniste",AlertDialog.image_checked);
     
        Nombre_User_Nutriste.setText(String.valueOf(service.nombre_user()));
     try {
                    refreche();
                } catch (SQLException ex) {
                    Logger.getLogger(homeController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            
        
        }
    }
    private void setCellfromtabletotxt() {
        tabview.setOnMouseClicked(e -> {

            user = tabview.getItems().get(tabview.getSelectionModel().getSelectedIndex());
          
            

        }
        );

    }
       public void refreche() throws SQLException {
     

        col_email.setCellValueFactory(new PropertyValueFactory<>("email"));
        col_roles.setCellValueFactory(new PropertyValueFactory<>("roles"));
        col_nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        col_prenom.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        col_tel.setCellValueFactory(new PropertyValueFactory<>("tel"));   
      
         tabview.getItems().clear();       
        tabview.setItems(service.Affichertout());
       
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
                    
        col_email.setCellValueFactory(new PropertyValueFactory<>("email"));
        col_roles.setCellValueFactory(new PropertyValueFactory<>("roles"));
        col_nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        col_prenom.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        col_tel.setCellValueFactory(new PropertyValueFactory<>("tel"));   
         

        tabview.getItems().clear();



                    tabview.setItems(service.serach(txt_Seach.getText()));

                } catch (SQLException ex) {
                }
        

            }
        }
        );

    }
      
      
      
  
}
