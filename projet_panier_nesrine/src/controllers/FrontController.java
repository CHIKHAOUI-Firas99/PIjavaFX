/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import Alert.AlertDialog;
import Service.panier_Service;
import Service.abonnement_Service;
import entites.panier;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.util.Callback;
import print.bill;
import print.billprint;

/**
 * FXML Controller class
 *
 * @author Aymen
 */
public class FrontController implements Initializable {

    @FXML
    private Pane pnl_panier;
    @FXML
    private Label prixTotal;
    @FXML
    private TextField txt_quantite_change;
    @FXML
    private Button btn_Modifier;
    @FXML
    private TableView<panier> tabpanier;
    @FXML
    private TableColumn<panier, String> col_lib_produit;
    @FXML
    private TableColumn<panier, Integer> col_quantite_produit;
  
    @FXML
    private Pane pnl_accessoire;
    @FXML
    private ScrollPane scrollpaneProduit;
    @FXML
    private HBox hboxProduit;
    @FXML
    private Label username;
    @FXML
    private Button btn_product;
    @FXML
    private Button btn_Panier;
        private TableColumn<panier, String> col_btnDelet;
   static int indiceProduit = 0;
      private int tailleProduit=0;
         abonnement_Service service_Produit = new abonnement_Service();
        panier_Service service = new panier_Service();

            panier panier = null;
    @FXML
    private TableColumn<panier, Date> col_date_Debut;
    @FXML
    private TableColumn<panier, Date> col_Date_Fin;
     
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        setCellfromtabletotxt();
        prixTotal.setText(String.valueOf(service.prixtotal()) + " DT");
          col_lib_produit.setCellValueFactory(new PropertyValueFactory<>("labelle"));
        col_quantite_produit.setCellValueFactory(new PropertyValueFactory<>("quantite"));
        col_Date_Fin.setCellValueFactory(new PropertyValueFactory<>("Date_Fin"));
        col_date_Debut.setCellValueFactory(new PropertyValueFactory<>("Date_Debut"));
        try {
            tabpanier.setItems(service.Affichertout_user(1));
        } catch (SQLException ex) {
         }
        
        
        /*-----------------*/
                
        try {
            tailleProduit = service_Produit.Affichertaille();
        } catch (SQLException ex) {
          
        }
          Node[] nodes_accessoire = new Node[tailleProduit];
           scrollpaneProduit.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
           
           
        for (indiceProduit = 0; indiceProduit < tailleProduit; indiceProduit++) {
            try {

                nodes_accessoire[indiceProduit] = FXMLLoader.load(getClass().getResource("/GUI/Item_abonnement.fxml"));

                hboxProduit.getChildren().add(nodes_accessoire[indiceProduit]);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        
           col_btnDelet = new TableColumn("Supprimer");
                javafx.util.Callback<TableColumn<panier, String>, TableCell<panier, String>> cellFactory
                = new Callback<TableColumn<panier, String>, TableCell<panier, String>>() {
            public TableCell call(final TableColumn<panier, String> param) {
                final TableCell<panier, String> cell = new TableCell<panier, String>() {

                    final Button btn = new Button("supprimer");

                    @Override
                    public void updateItem(String item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                            setText(null);
                        } else {
                            btn.setOnAction(event -> {
                                panier u = getTableView().getItems().get(getIndex());

                          
                              
                                try {
                                    service.Supprimer(u.getId());
                                } catch (SQLException ex) {
                                 }
                               
                                AlertDialog.showNotification("suppression confirmée!", "suppression a été bien faite", AlertDialog.image_checked);

                                col_lib_produit.setCellValueFactory(new PropertyValueFactory<>("labelle"));
        col_quantite_produit.setCellValueFactory(new PropertyValueFactory<>("quantite"));
          col_Date_Fin.setCellValueFactory(new PropertyValueFactory<>("Date_Fin"));
        col_date_Debut.setCellValueFactory(new PropertyValueFactory<>("Date_Debut"));
      
                                try {
                                    tabpanier.setItems(service.Affichertout_user(1));
                                } catch (SQLException ex) {
                                    Logger.getLogger(FrontController.class.getName()).log(Level.SEVERE, null, ex);
                                }
                                    prixTotal.setText(String.valueOf(service.prixtotal()) + " DT");
     

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
        tabpanier.getColumns().add(col_btnDelet);
    
    }    

    @FXML
    private void Modifier_quantite(ActionEvent event) throws Exception {
           if (txt_quantite_change.getText().equals("")) {
            AlertDialog.showNotification("Error !", "champ vide", AlertDialog.image_cross);
        }
        else if (txt_quantite_change.getText().matches("^[a-zA-Z]+$")) {
            AlertDialog.showNotification("Error !", "quantite incorrect", AlertDialog.image_cross);
        }
           else
        {
                 panier p = new panier();
                 p.setQuantite(Integer.valueOf(txt_quantite_change.getText()));
            service.Modifier(p, panier.getId());
        col_lib_produit.setCellValueFactory(new PropertyValueFactory<>("labelle"));
        col_quantite_produit.setCellValueFactory(new PropertyValueFactory<>("quantite"));
          col_Date_Fin.setCellValueFactory(new PropertyValueFactory<>("Date_Fin"));
        col_date_Debut.setCellValueFactory(new PropertyValueFactory<>("Date_Debut"));
      
                tabpanier.setItems(service.Affichertout_user(1));
                prixTotal.setText(String.valueOf(service.prixtotal()) + " DT");
                     AlertDialog.showNotification("Modification !","Modification aves sucess",AlertDialog.image_checked);
            
        }
    }

    @FXML
    private void handleClicks(ActionEvent event) throws SQLException {
         if (event.getSource() == btn_Panier) {
            pnl_panier.toFront();
            tabpanier.setItems(service.Affichertout_user(1));
            prixTotal.setText(String.valueOf(service.prixtotal()) + " DT");
         }
            if (event.getSource() == btn_product) {
            pnl_accessoire.toFront();
        }
    }
     private void setCellfromtabletotxt() {
        tabpanier.setOnMouseClicked(e -> {
            panier = tabpanier.getItems().get(tabpanier.getSelectionModel().getSelectedIndex());
            txt_quantite_change.setText(String.valueOf(panier.getQuantite()));
        }
        );

    }

    @FXML
    private void Ticket(ActionEvent event) {
          PrinterJob pj = PrinterJob.getPrinterJob();        
        pj.setPrintable(new billprint(),bill.getPageFormat(pj));
      
        try {
             pj.print();
          
        }
         catch (PrinterException ex) {
                 ex.printStackTrace();
        }
    }
}
