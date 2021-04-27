/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import entities.Coach;
import entities.User;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.sql.SQLException;
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
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import services.AbonnementService;
import services.CoachService;
import services.UserService;

/**
 * FXML Controller class
 *
 * @author 21652
 */
public class ClassementController implements Initializable {
    @FXML
    private ImageView img1,img2,img3,img4;
    @FXML
    private Label nom1,nom2,nom3,nom4,num_abb1,num_abb2,num_abb3,num_abb4,
            m1,m2,m3,m4,
            bio1,bio2,bio3,bio4;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
     AbonnementService as = new AbonnementService();
     List <Integer> mm; 
     UserService user = new UserService();
     CoachService cs = new CoachService();
        try {
            mm = as.readAllAbonnements();
            int m = 1;
            for(int j=0; j<mm.size(); j+=2){    
            int i = cs.getuseridbycoachid(mm.get(j));    
            Coach coach = cs.InfoCoach(i);     
            System.out.println("le coach qui a l'id "+mm.get(j)+" a "+mm.get(j+1)+" abonnements ");
            System.out.println(coach.user_id);
            System.out.println(coach.image);
            System.out.println(m); 
            if(m == 1){
                InputStream stream;
                try {
                    stream = new FileInputStream(coach.image);
                Image Image = new Image(stream);
                img1.setImage(Image);
                nom1.setText(user.getUserByID(coach.user_id).getNom());
                num_abb1.setText("numéro d'abonnés : "+String.valueOf(mm.get(j+1)));
                m1.setText(String.valueOf(m));
                bio1.setText(coach.bio);
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(ClassementController.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
            else if(m == 2){
                InputStream stream;
                try {
                stream = new FileInputStream(coach.image);
                Image Image = new Image(stream);
                img2.setImage(Image);
                nom2.setText(user.getUserByID(coach.user_id).getNom());
                num_abb2.setText("numéro d'abonnés : "+String.valueOf(mm.get(j+1)));
                m2.setText(String.valueOf(m));
                bio2.setText(coach.bio);
                
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(ClassementController.class.getName()).log(Level.SEVERE, null, ex);
                }    
            }
            else if(m == 3){
                InputStream stream;
                try {
                stream = new FileInputStream(coach.image);
                Image Image = new Image(stream);
                img3.setImage(Image);
                nom3.setText(user.getUserByID(coach.user_id).getNom());
                num_abb3.setText("numéro d'abonnés : "+String.valueOf(mm.get(j+1))); 
                m3.setText(String.valueOf(m));
                bio3.setText(coach.bio);                

                } catch (FileNotFoundException ex) {
                    Logger.getLogger(ClassementController.class.getName()).log(Level.SEVERE, null, ex);
                }    
            }
                else if(m == 4){
                InputStream stream;
                try {
                stream = new FileInputStream(coach.image);
                Image Image = new Image(stream);
                img4.setImage(Image);
                nom4.setText(user.getUserByID(coach.user_id).getNom());
                num_abb4.setText("numéro d'abonnés : "+String.valueOf(mm.get(j+1)));
                m4.setText(String.valueOf(m));
                bio4.setText(coach.bio);
                
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(ClassementController.class.getName()).log(Level.SEVERE, null, ex);
                }    
            }
            m++;
  
            }
           
        } catch (SQLException ex) {
            Logger.getLogger(ClassementController.class.getName()).log(Level.SEVERE, null, ex);
        }
    

    
        
        
        // TODO
    }    
    
    
        public void profile(ActionEvent event) throws IOException{
              Parent root = FXMLLoader.load(getClass().getResource("/GUI/coachlog.fxml")); 
              Scene scene = new Scene(root);
              Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
              stage.setScene(scene);
              stage.show();
    }
    public void planning(ActionEvent event) throws IOException{
              Parent root = FXMLLoader.load(getClass().getResource("/GUI/CoachPlanning.fxml")); 
              Scene scene = new Scene(root);
              Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
              stage.setScene(scene);
              stage.show();
    }   
    
    public void classement(ActionEvent event) throws IOException{
        
              Parent root = FXMLLoader.load(getClass().getResource("/GUI/Classement.fxml")); 
              Scene scene = new Scene(root);
              Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
              stage.setScene(scene);
              stage.show();   
    
    
    }
        public void Accueil(ActionEvent event) throws IOException{          
              Parent root = FXMLLoader.load(getClass().getResource("/GUI/Accueil.fxml")); 
              Scene scene = new Scene(root);
              Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
              stage.setScene(scene);
              stage.show();    
    }
    
    
}
