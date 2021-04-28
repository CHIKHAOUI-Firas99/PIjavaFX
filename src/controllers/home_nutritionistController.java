/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import Alert.AlertDialog;
import Service.User_Service;
import Service.nutritionist_Service;
import entites.nutritionist;
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
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TablePosition;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.converter.DoubleStringConverter;
import javafx.util.converter.IntegerStringConverter;

/**
 * FXML Controller class
 *
 * @author Aziz
 */
public class home_nutritionistController implements Initializable {

    @FXML
    private VBox vboxdrawer;
    @FXML
    private ImageView imagechange;
    @FXML
    private Label fullName;
    @FXML
    private Button btn_affichage;
    @FXML
    private Button btn_affichage_Nutriste;
    @FXML
    private Pane pnl_affichage_user;
    @FXML
    private TextField txt_Seach;
    @FXML
    private Label Nombre_User_Nutriste;
    @FXML
    private TableView<nutritionist> tabview;
    @FXML
    private TableColumn<nutritionist, String> col_email;
    @FXML
    private TableColumn<nutritionist, String>col_roles;
    @FXML
    private TableColumn<nutritionist, String> col_nom;
    @FXML
    private TableColumn<nutritionist, String> col_prenom;
    @FXML
    private TableColumn<nutritionist, Integer> col_tel;

    @FXML
    private TableColumn<nutritionist, String> col_diet;
    @FXML
    private TableColumn<nutritionist, Double> col_salary;
    @FXML
    private TableColumn<nutritionist, Integer> col_note;

    nutritionist_Service service = new nutritionist_Service();
    User_Service service_user = new User_Service();
        private TableColumn<nutritionist, String> col_btnDelet;
    @FXML
    private Button Stat;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
              tabview.setEditable(true);
          search();
        try {
            Modifier();
            refreche();
        } catch (SQLException ex) {
         
        }
              
   Nombre_User_Nutriste.setText(String.valueOf(service_user.nombre_user()));
   
     col_btnDelet = new TableColumn("Supprimer");
        javafx.util.Callback<TableColumn<nutritionist, String>, TableCell<nutritionist, String>> cellFactory
                = (final TableColumn<nutritionist, String> param) -> {
                    final TableCell<nutritionist, String> cell = new TableCell<nutritionist, String>() {
                        
                        final Button btn = new Button("supprimer");
                        
                        @Override
                        public void updateItem(String item, boolean empty) {
                            super.updateItem(item, empty);
                            if (empty) {
                                setGraphic(null);
                                setText(null);
                            } else {
                                btn.setOnAction(event -> {
                                    nutritionist u = getTableView().getItems().get(getIndex());
                                    
                                    
                                    
                                    
                                    try {
                                        service.Supprimer(u.getId());
                                    } catch (SQLException ex) {
                                    }
                                    
                                    
                                    AlertDialog.showNotification("suppression confirmée!", "suppression a été bien faite", AlertDialog.image_checked);
                                    
                                    
                                    try {
                                        refreche();
                                    } catch (SQLException ex) {
                                    }
                                    
                                    
                                });
                                setGraphic(btn);
                                setText(null);
                            }
                        }
                    };
                    return cell;
           };
        col_btnDelet.setCellFactory(cellFactory);
        tabview.getColumns().add(col_btnDelet);
   
    }    

    @FXML
    private void handleClicks(ActionEvent event) throws IOException {
         if (event.getSource() == btn_affichage) {
            Node node = (Node) event.getSource();

            Stage stage = (Stage) node.getScene().getWindow();
            //stage.setMaximized(true);
            stage.close();
            Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/GUI/Home.fxml")));
            stage.setScene(scene);
            stage.show();

        }
        if (event.getSource() == btn_affichage_Nutriste) {
            Node node = (Node) event.getSource();

            Stage stage = (Stage) node.getScene().getWindow();
            //stage.setMaximized(true);
            stage.close();
            Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/GUI/Home_nutritionist.fxml")));
            stage.setScene(scene);
            stage.show();
        }
    }
    public void refreche() throws SQLException {
     
    
        col_email.setCellValueFactory(new PropertyValueFactory<>("email"));
        col_roles.setCellValueFactory(new PropertyValueFactory<>("roles"));
        col_nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        col_prenom.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        col_tel.setCellValueFactory(new PropertyValueFactory<>("tel"));   
          col_diet.setCellValueFactory(new PropertyValueFactory<>("diet")); 
            col_diet.setCellFactory(TextFieldTableCell.<nutritionist> forTableColumn());
            col_salary.setCellValueFactory(new PropertyValueFactory<>("salary")); 
           
             col_salary.setCellFactory(TextFieldTableCell.forTableColumn(new DoubleStringConverter()));
              col_note.setCellValueFactory(new PropertyValueFactory<>("note"));   
                col_note.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
      
         tabview.getItems().clear();       
        tabview.setItems(service.Affichertout());
       
    }
      public void search() {
        txt_Seach.setOnKeyReleased(e
                -> {
            if (txt_Seach.getText().equals("") ) {

                try {
                    refreche();
                } catch (SQLException ex) {
                }

            } else {

                try {
                      
        col_email.setCellValueFactory(new PropertyValueFactory<>("email"));
        col_roles.setCellValueFactory(new PropertyValueFactory<>("roles"));
        col_nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        col_prenom.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        col_tel.setCellValueFactory(new PropertyValueFactory<>("tel"));   
          col_diet.setCellValueFactory(new PropertyValueFactory<>("diet"));   
            col_salary.setCellValueFactory(new PropertyValueFactory<>("salary"));   
              col_note.setCellValueFactory(new PropertyValueFactory<>("note"));   
      

        tabview.getItems().clear();



                    tabview.setItems(service.serach(txt_Seach.getText()));

                } catch (SQLException ex) {
                }
        

            }
        }
        );

    }

    public void Modifier()
    {
               
                col_diet.setOnEditCommit((CellEditEvent<nutritionist, String> event) -> {
            TablePosition<nutritionist, String> pos = event.getTablePosition();
                System.out.println(pos);
            String diet = event.getNewValue();
                 
            int row = pos.getRow();
            nutritionist nutritionist = event.getTableView().getItems().get(row);
            System.out.println(nutritionist);
  
            nutritionist.setDiet(diet);
                    try {
                        service.Modifier(nutritionist,nutritionist.getId());
                    } catch (Exception ex) {
                    }
        });
                
                
      
                
           
                
                
                
    }

    @FXML
    private void faire_stat(ActionEvent event) {
           Scene scene;
        Stage stage = new Stage();
        try {

            scene = new Scene(FXMLLoader.load(getClass().getResource("/GUI/Statistique.fxml")));
            stage.setScene(scene);
            stage.setTitle("Statistique");
            stage.show();

        } catch (IOException ex) {
          }
    }

}
