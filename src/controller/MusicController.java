/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

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

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }  
    
    public void music1(ActionEvent event) throws IOException{
        Media media = new Media(new File("C:\\wamp\\www\\coachini_java\\Coachini\\src\\Music\\gasba-chaoui.mp3").toURI().toString());
        MediaPlayer player = new MediaPlayer(media);
        player.play();
        System.out.println("ok ");
    }
    
}
