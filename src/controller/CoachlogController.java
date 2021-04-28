/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import Alert.AlertDialog;
import entities.Coach;
import entities.User;
import java.awt.Desktop;
import java.awt.event.MouseEvent;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
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
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import services.AbonnementService;
import services.CoachService;
import services.UserService;

/**
 * FXML Controller class
 *
 * @author 21652
 */
public class CoachlogController implements Initializable {

    @FXML
    private Label text,age,poid,bio,hauteur,coachname;
    
    @FXML
    private ImageView Image;
    @FXML 
    ListView<String> List_Abb;
    @FXML
    private Button planning,gmail,fb,insta;
    @FXML
    private ImageView image;

  

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try{
            
        User user = UserService.getCurrentUser();
        UserService srvUser = new UserService();
        if(user.getRoles().contains("[\"ROLE_COACH\"]")){
        text.setText("Bienvenue coach "+user.getNom());
        }
        else {
        text.setText("Bienvenue "+user.getNom());
 
        }
        CoachService cch = new CoachService(); 
        AbonnementService as = new AbonnementService();
           Coach coach = cch.InfoCoach(srvUser.coachidabb(srvUser.getCurrentUser().getId()));
           if(user.getRoles().contains("[]")){
           coachname.setText("vote coach est : "+srvUser.getUserByID(coach.user_id).getNom());
           }
           
        System.out.println(coach.getAge());
        InputStream stream;
         stream = new FileInputStream("C:\\wamp\\www\\pi_final\\pi\\public\\uploads\\images\\"+coach.image+"");
         Image Image = new Image(stream);
         image.setImage(Image);
              
           
            
        age.setText(String.valueOf(coach.getAge()));
        poid.setText(String.valueOf(coach.getPoid()));
        bio.setText(String.valueOf(coach.getBio()));
        hauteur.setText(String.valueOf(coach.getHauteur()));
        List <String> abb = as.Abonnes(coach.getId());
        System.out.println(abb.size());
        for(int j=0; j<abb.size(); j+=2)
        {                 
        List_Abb.getItems().addAll(abb.get(j));
        List_Abb.getItems().addAll(abb.get(j+1));
        }
        
        }
        catch (Exception e){
            System.out.println (e.getMessage());
        }
        
        // TODO
    } 
    public void planning(ActionEvent event) throws IOException{
              Parent root = FXMLLoader.load(getClass().getResource("/GUI/CoachPlanning.fxml")); 
              Scene scene = new Scene(root);
              Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
              stage.setScene(scene);
              stage.show();
    }
    public void profile(ActionEvent event) throws IOException{
              Parent root = FXMLLoader.load(getClass().getResource("/GUI/coachlog.fxml")); 
              Scene scene = new Scene(root);
              Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
              stage.setScene(scene);
              stage.show();
    }
    public void md_profile(ActionEvent event) throws IOException{
        User user = UserService.getCurrentUser();
        if(user.getRoles().contains("[]")){
        AlertDialog.showNotification("Cher Abonné","Seulement votre coach peut ajouter un entrainnement ", AlertDialog.image_cross);
        }
        else{
              Parent root = FXMLLoader.load(getClass().getResource("/GUI/ProfileEdit.fxml")); 
              Scene scene = new Scene(root);
              Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
              stage.setScene(scene);
              stage.show();
        }
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
       public void gmail(ActionEvent event) throws IOException, SQLException{
        UserService srvUser = new UserService();
        CoachService cch = new CoachService(); 
        AbonnementService as = new AbonnementService();
        Coach coach = cch.InfoCoach(srvUser.coachidabb(srvUser.getCurrentUser().getId()));
        Desktop desktop = java.awt.Desktop.getDesktop();
        URI oURL;
        try {
        oURL = new URI(coach.insta);
        desktop.browse(oURL);
        } catch (URISyntaxException ex) {
            AlertDialog.showErrorMessage(ex);
        }
       }
        public void fb(ActionEvent event) throws IOException, SQLException{
        UserService srvUser = new UserService();
        CoachService cch = new CoachService(); 
        AbonnementService as = new AbonnementService();
        Coach coach = cch.InfoCoach(srvUser.coachidabb(srvUser.getCurrentUser().getId()));
        Desktop desktop = java.awt.Desktop.getDesktop();
        URI oURL;
        try {
        oURL = new URI(coach.facebook);
        desktop.browse(oURL);
        } catch (URISyntaxException ex) {
            AlertDialog.showErrorMessage(ex);
        } 
        }
        
        public void insta(ActionEvent event) throws IOException, SQLException{
        UserService srvUser = new UserService();
        CoachService cch = new CoachService(); 
        AbonnementService as = new AbonnementService();
        Coach coach = cch.InfoCoach(srvUser.coachidabb(srvUser.getCurrentUser().getId()));
        Desktop desktop = java.awt.Desktop.getDesktop();
        URI oURL;
        try {
        oURL = new URI(coach.insta);
        desktop.browse(oURL);
        } catch (URISyntaxException ex) {
            AlertDialog.showErrorMessage(ex);
        }
    
        
       }


}
