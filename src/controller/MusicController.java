/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import static com.oracle.jrockit.jfr.FlightRecorder.isActive;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

/**
 * FXML Controller class
 *
 * @author 21652
 */
public class MusicController implements Initializable {
    private MediaPlayer player;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        // TODO
    }  
    
    public void music1(ActionEvent event) throws IOException{
        if(player != null) {
        player.stop();
            } 
        Media media = new Media(new File("C:\\wamp\\www\\coachini_java\\Coachini\\src\\Music\\Leck.mp3").toURI().toString());
        player = new MediaPlayer(media);
        player.play();

        }
       
        public void music2(ActionEvent event) throws IOException{
        if(player != null) {
        player.stop();
            }    
        Media media = new Media(new File("C:\\wamp\\www\\coachini_java\\Coachini\\src\\Music\\will i am.mp3").toURI().toString());
        player = new MediaPlayer(media);
        player.play();
        System.out.println(player);
    }
        public void music3(ActionEvent event) throws IOException{
        if(player != null) {
        player.stop();
            }    
        Media media = new Media(new File("C:\\wamp\\www\\coachini_java\\Coachini\\src\\Music\\Booba.mp3").toURI().toString());
        player = new MediaPlayer(media);
        player.play();
        System.out.println("ok ");
    }
        public void music4(ActionEvent event) throws IOException{
        if(player != null) {
        player.stop();
            }    
        Media media = new Media(new File("C:\\wamp\\www\\coachini_java\\Coachini\\src\\Music\\Paname.mp3").toURI().toString());
        player = new MediaPlayer(media);
        player.play();
        System.out.println("ok ");
    }
   
          
public void Stop(ActionEvent event) throws IOException{
            if(player != null) {
            player.stop();
            System.out.println("ok ");
            } 
        }

    } 
        
    

