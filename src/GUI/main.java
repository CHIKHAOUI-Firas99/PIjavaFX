/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author aziz
 */
public class main extends Application {
 private double x, y;
    @Override
    public void start(Stage primaryStage) throws Exception {
      // partie admin  
//Parent root = FXMLLoader.load(getClass().getResource("Home.fxml"));
  // partie nutrisionnistte
   Parent root = FXMLLoader.load(getClass().getResource("Partie_nutritionist.fxml"));
     // partie client
 // Parent root = FXMLLoader.load(getClass().getResource("Front.fxml"));
        primaryStage.setScene(new Scene(root));
        //set stage borderless
        primaryStage.initStyle(StageStyle.TRANSPARENT);

        //drag it here
        root.setOnMousePressed(event -> {
            x = event.getSceneX();
            y = event.getSceneY();
        });
        root.setOnMouseDragged(event -> {

            primaryStage.setX(event.getScreenX() - x);
            primaryStage.setY(event.getScreenY() - y);

        });
        primaryStage.show();
    
    
    }
    
}