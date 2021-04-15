/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import entities.User;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import services.UserService;

/**
 * FXML Controller class
 *
 * @author 21652
 */
public class CoachlogController implements Initializable {

    @FXML
    private Label text;


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        User user = UserService.getCurrentUser();
        text.setText("Bienvenue coach :"+user.getNom());
        // TODO
    }    
    
}
