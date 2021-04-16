/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import Service.User_Service;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Aymen
 */
public class FrontCrontroller implements Initializable {

    @FXML
    private Pane pnl_Home_Sites;
    @FXML
    private Button btn_Home;
    @FXML
    private ImageView btn_Acceuil;
    @FXML
    private Label username;
    @FXML
    private ScrollPane scrollpaneNouristes;
    @FXML
    private HBox hbox_Nouristes;
    private int taille_Nouristes = 0;
    User_Service service = new User_Service();
        static int indiceNouristes = 0;
    @FXML
    private Button btn_Program;
    @FXML
    private ImageView btn_Acceuil1;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         try {
            /*-------------------*/
            // site
            taille_Nouristes = service.nombre_user();
            System.out.println(taille_Nouristes);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());

        }
        Node[] nodes_Nouristes = new Node[taille_Nouristes];
        scrollpaneNouristes.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        for (indiceNouristes = 0; indiceNouristes < taille_Nouristes; indiceNouristes++) {
            try {

                nodes_Nouristes[indiceNouristes] = FXMLLoader.load(getClass().getResource("/GUI/Item_Nouriste.fxml"));

                hbox_Nouristes.getChildren().add(nodes_Nouristes[indiceNouristes]);
            } catch (Exception e) {

            }
        }
    }    

    @FXML
    private void handleClicks(ActionEvent event) throws IOException {
         if (event.getSource() == btn_Home) {
            Node node = (Node) event.getSource();

            Stage stage = (Stage) node.getScene().getWindow();
            //stage.setMaximized(true);
            stage.close();
            Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/GUI/Front.fxml")));
            stage.setScene(scene);
            stage.show();

        }
        if (event.getSource() == btn_Program) {
            Node node = (Node) event.getSource();

            Stage stage = (Stage) node.getScene().getWindow();
            //stage.setMaximized(true);
            stage.close();
            Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/GUI/Front_user_program.fxml")));
            stage.setScene(scene);
            stage.show();
        }
    }
    
}
