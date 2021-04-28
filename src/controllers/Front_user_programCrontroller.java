/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import Alert.AlertDialog;
import Service.programmenutrition_Service;
import entites.programmenutrition;
import java.awt.AWTException;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javax.imageio.ImageIO;


/**
 * FXML Controller class
 *
 * @author Aziz
 */
public class Front_user_programCrontroller implements Initializable {

    @FXML
    private Pane pnl_Home_Sites;
    @FXML
    private TableView<programmenutrition> tabview;

    @FXML
    private TableColumn<programmenutrition, Integer> col_user_id;
    @FXML
    private TableColumn<programmenutrition, Integer> col_info_user_nutrition_id;
    @FXML
    private TableColumn<programmenutrition, String> col_repas1;
    @FXML
    private TableColumn<programmenutrition, String> col_repas2;
    @FXML
    private TableColumn<programmenutrition, String> col_repas3;
    @FXML
    private TableColumn<programmenutrition, String> col_repas4;
    @FXML
    private TableColumn<programmenutrition, String> col_repas5;
    @FXML
    private TableColumn<programmenutrition, Integer> col_duree;
    @FXML
    private TableColumn<programmenutrition, String> col_jourrepot;
    @FXML
    private Button btn_Home;
    @FXML
    private ImageView btn_Acceuil;
    @FXML
    private Button btn_Program;
    @FXML
    private ImageView btn_Acceuil1;
    @FXML
    private Label username;
 programmenutrition_Service service = new programmenutrition_Service();
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            refreche();
        } catch (SQLException ex) {
            Logger.getLogger(Front_user_programCrontroller.class.getName()).log(Level.SEVERE, null, ex);
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
    
  
       public void refreche() throws SQLException {

      
        col_user_id.setCellValueFactory(new PropertyValueFactory<>("user_id"));
        col_info_user_nutrition_id.setCellValueFactory(new PropertyValueFactory<>("info_user_nutrition_id"));
        col_repas1.setCellValueFactory(new PropertyValueFactory<>("repas1"));
        col_repas2.setCellValueFactory(new PropertyValueFactory<>("repas2"));
        col_repas3.setCellValueFactory(new PropertyValueFactory<>("repas3"));   
        col_repas4.setCellValueFactory(new PropertyValueFactory<>("repas4"));   
        col_repas5.setCellValueFactory(new PropertyValueFactory<>("repas5"));   
        col_duree.setCellValueFactory(new PropertyValueFactory<>("duree"));   
        col_jourrepot.setCellValueFactory(new PropertyValueFactory<>("jourrepot"));   
         tabview.getItems().clear();       
                  // f oudh 5 nbdlha id ta user
        tabview.setItems(service.Affichertout_user(5));
       
    }

    @FXML
    private void doTakeScreenShot(ActionEvent event) {
        try {
            Robot robot = new Robot();
            Rectangle rectangle = new Rectangle(555,267,850,405);
            BufferedImage image = robot.createScreenCapture(rectangle);
            Image myImage = SwingFXUtils.toFXImage(image, null);
            ImageIO.write(image, "jpg", new File("Screen.jpg"));
                        AlertDialog.showNotification("Screen effectué", "Screen effectué avec succées", AlertDialog.image_checked);

                    } catch (Exception ex) {
            Logger.getLogger(Front_user_programCrontroller.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
