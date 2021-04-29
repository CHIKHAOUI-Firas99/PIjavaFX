/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import Alert.AlertDialog;
import Service.abonnement_Service;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import entites.abonnement;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TablePosition;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.DragEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.util.Callback;
import javafx.util.converter.FloatStringConverter;
import javafx.util.converter.IntegerStringConverter;
import javax.imageio.ImageIO;

/**
 * FXML Controller class
 *
 * @author Aymen
 */
public class home_abonnementController implements Initializable {

    @FXML
    private VBox vboxdrawer;
    @FXML
    private ImageView imagechange;
    @FXML
    private Label fullName;
    @FXML
    private Button btn_affichage;
    @FXML
    private Pane pnl_abonnement;
    @FXML
    private TextField txt_Seach;
    @FXML
    private TableView<abonnement> tabview;
    @FXML
    private TableColumn<abonnement, String> col_libelle;
    @FXML
    private TableColumn<abonnement, String> col_type;
    @FXML
    private TableColumn<abonnement, Integer> col_prix;
    @FXML
    private TableColumn<abonnement, Integer> col_quantite;
    @FXML
    private TableColumn<abonnement, Integer> col_id;
    @FXML
    private TextField txt_libelle;
    @FXML
    private TextField txt_prix;
    @FXML
    private TextField txt_quantite;

    @FXML
    private Button btn_ajout;
    private ImageView imageview;
    @FXML
    private ImageView Image_Produit;
    abonnement_Service service = new abonnement_Service();
     private TableColumn<abonnement, String> col_btnDelet;
    
    @FXML
    private ComboBox<String> combo_type;
   ObservableList<String> list = FXCollections.observableArrayList();
    @FXML
    private ComboBox<Integer> combo_nutrisoniste;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
           list.addAll("Moins","3 Moins","un ans");
         combo_type.setItems(list);
         combo_nutrisoniste.setItems(service.read_ids());
            combo_type.getSelectionModel().select(0);
               combo_nutrisoniste.getSelectionModel().select(0);
         setCellfromtabletoImage();
        Modifier();
        tabview.setEditable(true);  
                search();
        try {
            refreche();
        } catch (SQLException ex) {
        }
           col_btnDelet = new TableColumn("Supprimer");
                javafx.util.Callback<TableColumn<abonnement, String>, TableCell<abonnement, String>> cellFactory
                = new Callback<TableColumn<abonnement, String>, TableCell<abonnement, String>>() {
            public TableCell call(final TableColumn<abonnement, String> param) {
                final TableCell<abonnement, String> cell = new TableCell<abonnement, String>() {

                    final Button btn = new Button("supprimer");

                    @Override
                    public void updateItem(String item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                            setText(null);
                        } else {
                            btn.setOnAction(event -> {
                                abonnement u = getTableView().getItems().get(getIndex());
 File f = new File("C:/wamp64/www/images/"+u.getImage());
                            
                                System.out.println(f.delete());                             

                          
                              
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
            }
        };
        col_btnDelet.setCellFactory(cellFactory);
        tabview.getColumns().add(col_btnDelet);
        
    }    

    @FXML
    private void handleClicks(ActionEvent event) {
    }



    @FXML
    private void ajouter_produit(ActionEvent event) throws SQLException {
        
          if (txt_libelle.getText().equals("")) {
            AlertDialog.showNotification("Error !", "Champ vide de libelle", AlertDialog.image_cross);
        } else if (txt_libelle.getText().matches("^[0-9]+$")) {
            AlertDialog.showNotification("Erreur txt_libelle !", "il faut saisir des caracteres  !", AlertDialog.image_cross);
        } else if (txt_prix.getText().equals("")) {
            AlertDialog.showNotification("Error !", "Champ vide de Prix", AlertDialog.image_cross);
        } else if (Integer.valueOf(txt_prix.getText()) <= 0) {
            AlertDialog.showNotification("Error !", "Champ de Prix", AlertDialog.image_cross);
        } else if (txt_prix.getText().matches("^[a-zA-Z]+$")) {
            AlertDialog.showNotification("Erreur txt_prix !", "Prix incorrect", AlertDialog.image_cross);
        } else if (txt_quantite.getText().equals("")) {
            AlertDialog.showNotification("Error !", "Champ vide de quantite", AlertDialog.image_cross);
        } else if (txt_quantite.getText().matches("^[a-zA-Z]+$")) {
            AlertDialog.showNotification("Erreur quantite !", "quantite incorrect", AlertDialog.image_cross);
        } else if (Integer.valueOf(txt_quantite.getText()) <= 0) {
            AlertDialog.showNotification("Error !", "Champ de quantite", AlertDialog.image_cross);
        } 
      
        else
        {
              String code = "image"+Calendar.getInstance().getTimeInMillis();
                    

        QRCodeWriter qrCodeWriter = new QRCodeWriter();
 
        String data = "labelle : "+ txt_libelle.getText()+"Type  : "+combo_type.getSelectionModel().getSelectedItem()+"Prix  : "+ Integer.parseInt(txt_prix.getText())+" Quantite : "+Integer.parseInt(txt_quantite.getText());
        int width = 300;
        int height = 300;

        BufferedImage bufferedImage = null;
        try {
            BitMatrix byteMatrix = qrCodeWriter.encode(data, BarcodeFormat.QR_CODE, width, height);
            bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
            bufferedImage.createGraphics();

            Graphics2D image = (Graphics2D) bufferedImage.getGraphics();
            image.setColor(Color.WHITE);
            image.fillRect(0, 0, width, height);
            image.setColor(Color.BLACK);

            for (int i = 0; i < height; i++) {
                for (int j = 0; j < width; j++) {
                    if (byteMatrix.get(i, j)) {
                        image.fillRect(i, j, 1, 1);
                    }
                }
            }
            if (ImageIO.write(bufferedImage, "png", new File("C:/wamp64/www/images/" + code + ".png"))) {
                System.out.println("-- saved");
            }
            System.out.println("QR created successfully....");

        } catch (WriterException ex) {
            System.out.println(ex);
        } catch (IOException ex) {
            System.out.println(ex);
        }
          
          
          
          
          
          
          
          
          
        abonnement p = new abonnement(Integer.parseInt(txt_prix.getText()), combo_type.getSelectionModel().getSelectedItem(), txt_libelle.getText() ,Integer.parseInt(txt_quantite.getText()),code + ".png");
        p.setId_nutisonist(combo_nutrisoniste.getSelectionModel().getSelectedItem());
        service.Ajouter(p);

            
            
            
            
            
            
            
            refreche();
        }
          
          
          
           
          
    }
    
               public void Modifier()
    {
         
                   
                  
                   
            
        
               
                col_libelle.setOnEditCommit((TableColumn.CellEditEvent<abonnement, String> event) -> {
            TablePosition<abonnement, String> pos = event.getTablePosition();
                
            String libelle = event.getNewValue();
                 
            int row = pos.getRow();
            abonnement ac = event.getTableView().getItems().get(row);
           
  
            ac.setLabelle(libelle);
                    try {
                        service.Modifier(ac,ac.getId());
                    } catch (SQLException ex) {
                    }
        });
                
                
          
            
              
              
                        col_type.setOnEditCommit((TableColumn.CellEditEvent<abonnement, String> event) -> {
            TablePosition<abonnement, String> pos = event.getTablePosition();
           
            String type = event.getNewValue();
                  
            int row = pos.getRow();
            abonnement ab = event.getTableView().getItems().get(row);
          
  
            ab.setType(type);
                    try {
                        service.Modifier(ab,ab.getId());
                    } catch (SQLException ex) {
                    }
        });       
              
     col_quantite.setOnEditCommit((TableColumn.CellEditEvent<abonnement, Integer> event) -> {
            TablePosition<abonnement, Integer> pos = event.getTablePosition();
           
            Integer Quantite_Ab = event.getNewValue();
                  
            int row = pos.getRow();
            abonnement ab = event.getTableView().getItems().get(row);
          
  
            ab.setQuantite(Quantite_Ab);
                    try {
                        service.Modifier(ab,ab.getId());
                    } catch (SQLException ex) {
                    }
        });  
     
     col_prix.setOnEditCommit((TableColumn.CellEditEvent<abonnement, Integer> event) -> {
            TablePosition<abonnement, Integer> pos = event.getTablePosition();
           
            Integer Prix = event.getNewValue();
                  
            int row = pos.getRow();
            abonnement ab = event.getTableView().getItems().get(row);
          
  
            ab.setPrix(Prix);
              
                    try {
                        service.Modifier(ab,ab.getId());
                    } catch (SQLException ex) {
                    }
                  
        });
     
     
     
                
    }
               
                 public void refreche() throws SQLException {
        col_libelle.setCellValueFactory(new PropertyValueFactory<>("labelle"));
        col_libelle.setCellFactory(TextFieldTableCell.<abonnement> forTableColumn());
       
        col_type.setCellValueFactory(new PropertyValueFactory<>("type"));
        col_type.setCellFactory(TextFieldTableCell.<abonnement> forTableColumn());
        col_prix.setCellValueFactory(new PropertyValueFactory<>("prix"));
        col_prix.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        col_quantite.setCellValueFactory(new PropertyValueFactory<>("quantite"));
     col_quantite.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        col_id.setCellValueFactory(new PropertyValueFactory<>("id_nutisonist"));
        tabview.getItems().clear();

        tabview.setItems(service.Affichertout());

    }
                 private void setCellfromtabletoImage() {
        tabview.setOnMouseClicked(e -> {

            abonnement ac = tabview.getItems().get(tabview.getSelectionModel().getSelectedIndex());
            String ImageUrl ="http://localhost/images/";
        

        Image image = new Image(ImageUrl + ac.getImage());
        Image_Produit.setImage(image);
        }
        );

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
               col_libelle.setCellValueFactory(new PropertyValueFactory<>("labelle"));
        col_libelle.setCellFactory(TextFieldTableCell.<abonnement> forTableColumn());
       
        col_type.setCellValueFactory(new PropertyValueFactory<>("type"));
        col_type.setCellFactory(TextFieldTableCell.<abonnement> forTableColumn());
        col_prix.setCellValueFactory(new PropertyValueFactory<>("prix"));
        col_prix.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        col_quantite.setCellValueFactory(new PropertyValueFactory<>("quantite"));
     col_quantite.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        col_id.setCellValueFactory(new PropertyValueFactory<>("id_nutisonist"));
             tabview.getItems().clear();

                    tabview.setItems(service.serach(txt_Seach.getText()));

                } catch (SQLException ex) {
                    }
        

            }
        }
        );

    }
 
}
