/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import Alert.AlertDialog;
import entities.Entrainement;
import entities.User;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import services.CoachService;
import services.EntrainementService;
import services.UserService;

/**
 * FXML Controller class
 *
 * @author 21652
 */

public class UpdateplanningController implements Initializable {

    @FXML 
private TextField entid,titre,jour,heure,type,meet;

    @Override
    public void initialize(URL url, ResourceBundle rb) { 

        
                  
      
        
        
        // TODO
    }
 
   void setTextField(int id,String tr, String jr, int hr, String tp, String mt) {
       entid.setText(String.valueOf(id));   
       titre.setText(tr);
       jour.setText(jr);
       heure.setText(String.valueOf(hr));
       type.setText(tp);
       meet.setText(mt);  
        
        }
   public void Modif(ActionEvent event) throws IOException, SQLException{
            EntrainementService es = new EntrainementService();
            Entrainement e= new Entrainement();
             String Entid = entid.getText().toString();   
             String Titre = titre.getText().toString();
             String Jour = jour.getText().toString();
             String Heure = heure.getText().toString();
             String Type = type.getText().toString();
             String Meet = meet.getText().toString();
             e.setTitre(Titre);
             e.setJour(Jour);
             int id=Integer.parseInt(Entid);  
             int i=Integer.parseInt(Heure);  
             e.setHeure(i);
             e.setType(Type);
             e.setMeet(Meet);    
             es.updateEntrainement(e, id);
              Stage stage1 = (Stage) ((Node) event.getSource()).getScene().getWindow();              
              stage1.close(); 
            AlertDialog.showNotification("Modification","Entrainnement le "+Jour+" a "+Heure+" heures est bien modifier", AlertDialog.image_checked);  
              
       
   }
   public void supp(ActionEvent event) throws IOException, SQLException{
 
            EntrainementService es = new EntrainementService();
            String Entid = entid.getText().toString();  
            int id=Integer.parseInt(Entid);
            es.supprimerEntrainement(id);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();              
            stage.close();
            AlertDialog.showNotification("Modification","Entrainnement le "+titre.getText().toString()+" a "+heure.getText().toString()+" heures est bien supprimer", AlertDialog.image_checked);
   }
   
}


    
