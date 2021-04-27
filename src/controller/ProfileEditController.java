/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import Alert.AlertDialog;
import entities.Coach;
import entities.User;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Blob;
import java.util.ResourceBundle;
import java.util.logging.FileHandler;
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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.apache.commons.io.FileUtils;
import services.CoachService;
import services.UserService;

/**
 * FXML Controller class
 *
 * @author 21652
 */
public class ProfileEditController implements Initializable {
    
        @FXML
        private ImageView imageshow ;
        @FXML 
        private TextField imagepath,age,poid,hauteur,insta,fb,gm,bio,path;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
          User user = UserService.getCurrentUser();
          CoachService cs = new CoachService();
            Coach coach = cs.InfoCoach(user.getId());
            if(coach.image != null){
                path.setText("C:\\wamp\\www\\pi_final\\pi\\public\\uploads\\images\\"+coach.image+"");
                imagepath.setText(coach.image);
                age.setText(String.valueOf(coach.age));
                poid.setText(String.valueOf(coach.poid));
                hauteur.setText(String.valueOf(coach.hauteur));
                insta.setText(coach.insta);
                fb.setText(coach.facebook);
                gm.setText(coach.gmail);
                bio.setText(coach.bio);
                InputStream stream;
                    try {
                        stream = new FileInputStream("C:\\wamp\\www\\pi_final\\pi\\public\\uploads\\images\\"+coach.image+"");
                        Image Image = new Image(stream);
                        imageshow.setImage(Image);

                    } catch (FileNotFoundException ex) {
                        Logger.getLogger(ProfileEditController.class.getName()).log(Level.SEVERE, null, ex);
                    }
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
    public void imageAction(ActionEvent event) throws IOException{
        CoachService cs = new CoachService();
        Coach c = new Coach();
        FileChooser fc = new FileChooser(); 
        File selectedfile = fc.showOpenDialog(null);
        if (selectedfile !=null){
        System.out.println();
        InputStream stream = new FileInputStream(selectedfile.getAbsolutePath().toString());
        Image Image = new Image(stream);
        imageshow.setImage(Image);
        imagepath.setText(selectedfile.getName());
         path.setText(selectedfile.getAbsolutePath().toString()); 
        System.out.println(selectedfile.getName());
        System.out.println(imageshow.getAccessibleText());
        
        }    
    
}
    public void Modif(ActionEvent event) throws IOException{
            User user = UserService.getCurrentUser();
            CoachService cs = new CoachService();
            Coach coach = cs.InfoCoach(user.getId());
            Coach c = new Coach();
            c.setAge(Integer.parseInt(age.getText()));
            c.setPoid(Integer.parseInt(poid.getText()));
            c.setHauteur(Integer.parseInt(hauteur.getText()));
            c.setFacebook(fb.getText());
            c.setInsta(insta.getText());
            c.setGmail(gm.getText());
            c.setBio(bio.getText());
            File src = new File (path.getText());
            File dest = new File("C:\\wamp\\www\\pi_final\\pi\\public\\uploads\\images");     
            if(src.equals(new File("C:\\wamp\\www\\pi_final\\pi\\public\\uploads\\images\\"+coach.image+""))){
            c.setImage(coach.image);
            }
            else{
            FileUtils.copyFileToDirectory(src, dest);      
            c.setImage(imagepath.getText());
            }
            int x =  user.getId();     
            cs.modifiercoach(c, x);
            if((c.age+c.bio+c.hauteur+c.facebook+c.gmail+c.image+c.insta+c.poid).toString().equalsIgnoreCase((coach.age+coach.bio+coach.hauteur+coach.facebook+coach.gmail+coach.image+coach.insta+coach.poid).toString()))
            {
                AlertDialog.showNotification("Modification","Aucune modification éffectué", AlertDialog.image_simple);     
            }
            else
            {                
            AlertDialog.showNotification("Modification","Modification bien effectué", AlertDialog.image_checked);    
            }
   
            
            
            Parent root = FXMLLoader.load(getClass().getResource("/GUI/coachlog.fxml")); 
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