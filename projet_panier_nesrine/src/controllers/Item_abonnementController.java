/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import Alert.AlertDialog;
import Service.panier_Service;
import Service.abonnement_Service;
import static controllers.FrontController.indiceProduit;
import entites.panier;
import entites.abonnement;
import java.net.URL;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Aymen
 */
public class Item_abonnementController implements Initializable {

    @FXML
    private HBox itemC;
    @FXML
    private Button btn_acheter;
    @FXML
    private Label type;
    @FXML
    private Label prix;
    @FXML
    private TextField txt_quantite;
    @FXML
    private ImageView image_acc;
    @FXML
    private Label libelle;
        abonnement acc = null; 
    abonnement_Service ser = new abonnement_Service();
    panier_Service service = new panier_Service();
    @FXML
    private Label quantite;
    @FXML
    private DatePicker date_Debut;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
            acc=ser.get_produit_affichage(indiceProduit);
  libelle.setText(acc.getLabelle());
      type.setText(acc.getType());
   
      prix.setText(String.valueOf(acc.getPrix()) + " DT");
   quantite.setText(String.valueOf(acc.getQuantite()));
        String ImageUrl = "http://localhost/images/";
        Image image = new Image(ImageUrl + acc.getImage());
      
        image_acc.setImage(image);
    }    

    @FXML
    private void acheter(ActionEvent event) throws SQLException {
           if (txt_quantite.getText().equals("")) {
       AlertDialog.showNotification("Error !","Champ vide de quantite",AlertDialog.image_cross);
        } 
                else if (txt_quantite.getText().matches("^[a-zA-Z]+$")) {
            AlertDialog.showNotification("Error !","quantite incorrect",AlertDialog.image_cross);
        }
              else if (date_Debut.getValue() == null ) {
                      AlertDialog.showNotification("Error !", "champ vide de date_Debut", AlertDialog.image_cross);


        }    
              else
                {
                      
                    java.sql.Date dd = new java.sql.Date(new Date(date_Debut.getEditor().getText()).getTime());

                 
  
        

        
                      
                      
                      
                      
                      
                      panier p = new panier(acc.getId(),1,Integer.valueOf(txt_quantite.getText()),acc.getLabelle());
                   p.setDate_Debut(dd);
                 Calendar C = Calendar.getInstance();
                  C.setTime(dd);
                    System.out.println(dd);
                    java.sql.Date futureDate=null;
                  if (acc.getType().equals("Moins"))
                  { 
                     futureDate =  this.addDays(dd, 30);   
                      
                  }
                  else  if (acc.getType().equals("3 Moins"))
                  {
               futureDate =  this.addDays(dd, 90);   
                  }
                  else
                  {
                  futureDate =  this.addDays(dd, 365);   
                      
                  }
                      
                 
                
                    System.out.println(futureDate);
          
                       p.setDate_Fin(futureDate);
                    service.Ajouter(p);
                    AlertDialog.showNotification("Achat !","Achat aves sucess",AlertDialog.image_checked);
            
                }
    }
        public static java.sql.Date  addDays(Date date, int days) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.DATE, days);
        return new java.sql.Date (c.getTimeInMillis());
    }

    @FXML
    private void Map(ActionEvent event) throws Exception {
           google_map g =  new google_map();
    g.start(new Stage());
    }
    
}
