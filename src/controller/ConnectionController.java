/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import Alert.AlertDialog;
import coachini.maintest;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import services.LoginService;
import services.UserService;

/**
 * FXML Controller class
 *
 * @author 21652
 */
public class ConnectionController implements Initializable {
    @FXML
    private Button button;
    @FXML
    private TextField username;
    @FXML
    private PasswordField password;

    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }  
    public void login(ActionEvent event) throws IOException, SQLException{
    LoginService ls = new LoginService();
    maintest m = new maintest();
    
    String userName = username.getText().toString();
    String passWord = password.getText().toString();
    UserService srvUser = new UserService();
    
    if(username.getText().toString().equalsIgnoreCase("")){
        AlertDialog.showNotification("Error !"," vous-devez saisir votre Nom ",AlertDialog.image_cross);
    }
    else if(password.getText().toString().equalsIgnoreCase("")){
    AlertDialog.showNotification("Error !","vous-devez saisir votre mot de passe ",AlertDialog.image_cross);
    }
    
    else if(username.getText().toString() != "" || password.getText().toString() != "" ){
            ls.check(userName, passWord);
            if (srvUser.getCurrentUser().getRoles().contains("[\"ROLE_COACH\"]") || srvUser.getCurrentUser().getRoles().contains("[]"))       
            {Parent root = FXMLLoader.load(getClass().getResource("/GUI/Accueil.fxml")); 
              Scene scene = new Scene(root);
              Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
              stage.setScene(scene);
              stage.show();       
      System.out.println(srvUser.coachidabb(srvUser.getCurrentUser().getId()));}
    
    }
    }
    }


    

