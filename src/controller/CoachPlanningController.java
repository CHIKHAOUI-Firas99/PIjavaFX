/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;


import Alert.AlertDialog;
import entities.Coach;
import entities.Entrainement;
import entities.User;
import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import services.AbonnementService;
import services.CoachService;
import services.EntrainementService;
import services.UserService;
import utils.JavaMailUtil;

/**
 * FXML Controller class
 *
 * @author 21652
 */
public class CoachPlanningController implements Initializable {
    
    
    @FXML
    private Label lundi_8,lundi_12,lundi_17,lundi_19,
                      mardi_8,mardi_12,mardi_17,mardi_19,
                      mercredi_8,mercredi_12,mercredi_17,mercredi_19,
                      jeudi_8,jeudi_12,jeudi_17,jeudi_19,
                      vendredi_8,vendredi_12,vendredi_17,vendredi_19,
                      samedi_8,samedi_12,samedi_17,samedi_19,
                      dimanche_8,dimanche_12,dimanche_17,dimanche_19;
    @FXML 
    private TextField titre,jour,heure,type,meet,chrono;
    @FXML
    private Hyperlink md_lundi_8,md_lundi_12,md_lundi_17,md_lundi_19,
                      md_mardi_8,md_mardi_12,md_mardi_17,md_mardi_19,
                      md_mercredi_8,md_mercredi_12,md_mercredi_17,md_mercredi_19,
                      md_jeudi_8,md_jeudi_12,md_jeudi_17,md_jeudi_19,
                      md_vendredi_8,md_vendredi_12,md_vendredi_17,md_vendredi_19,
                      md_samedi_8,md_samedi_12,md_samedi_17,md_samedi_19,
                      md_dimanche_8,md_dimanche_12,md_dimanche_17,md_dimanche_19,
                      
                      meet1,meet11,meet12,meet13,meet14,
                      meet15,meet16,meet17,meet18,
                      meet19,meet110,meet111,meet112,
                      meet113,meet114,meet115,meet116,
                      meet117,meet118,meet119,meet120,
                      meet121,meet122,meet123,meet124,
                      meet125,meet126,meet127;
                      
                      
    @FXML 
    private Button ajouter,starstop,reset;
    @FXML
    ComboBox box,Heure,Type;
    
    
    
    
    Timeline timeline;
    int mins = 0, secs = 0, millis = 0;
    boolean sos = true;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {       
    chrono.setText("00:00:000");
		timeline = new Timeline(new KeyFrame(Duration.millis(1), new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
            	change(chrono);
			}
		}));
                
		timeline.setCycleCount(Timeline.INDEFINITE);
		timeline.setAutoReverse(false);
		
            starstop.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
            	if(sos) {
            		timeline.play();
            		sos = false;
            		starstop.setText("Stop");
            	} else {
            		timeline.pause();
            		sos = true;
            		starstop.setText("Start");
            	}
            }
        });
		
            reset.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
            	mins = 0;
            	secs = 0;
            	millis = 0;
            	timeline.pause();
            	chrono.setText("00:00:000");
            	if(!sos) {
            		sos = true;
            		starstop.setText("Start");
            	}
            }
        });    
           
    
    UserService srvUser = new UserService();
    AbonnementService abb = new AbonnementService();
     EntrainementService es = new EntrainementService();
     CoachService CoS = new CoachService();
     box.getItems().addAll("Lundi","Mardi","Mercredi","Jeudi","Vendredi","Samedi","Dimanche");
     Heure.getItems().addAll("6.00am - 8.00am","10.00am - 12.00am","5.00pm - 7.00pm","7.00pm - 9.00pm");
        try {
        List<Entrainement> e = es.getEntrainementByCoachId(CoS.getcoachidbyuserid(srvUser.coachidabb(srvUser.getCurrentUser().getId())));
            for(int j=0; j<e.size(); j++){    
            
            if(e.get(j).jour.equals("Lundi") && e.get(j).heure == 8 ){
              lundi_8.setText(e.get(j).titre);
              meet1.setText("meet");
              
            if(srvUser.getCurrentUser().getRoles().contains("[\"ROLE_COACH\"]")){  
              md_lundi_8.setText("Modifier");
            } 
              }
            else if (e.get(j).jour.equals("Lundi") && e.get(j).heure == 12 ){
                              lundi_12.setText(e.get(j).titre);
                              meet11.setText("meet");
                              
            if(srvUser.getCurrentUser().getRoles().contains("[\"ROLE_COACH\"]")){                  
                              md_lundi_12.setText("Modifier");
            }
            
                }
            else if (e.get(j).jour.equals("Lundi") && e.get(j).heure == 16 ){
                              lundi_17.setText(e.get(j).titre);
                              meet12.setText("meet");
            if(srvUser.getCurrentUser().getRoles().contains("[\"ROLE_COACH\"]")){                   
                              md_lundi_17.setText("Modifier");
            }              
            
                }
            else if (e.get(j).jour.equals("Lundi") && e.get(j).heure == 20 ){
                              lundi_19.setText(e.get(j).titre);
                              meet13.setText("meet");
            if(srvUser.getCurrentUser().getRoles().contains("[\"ROLE_COACH\"]")){                  
                              md_lundi_19.setText("Modifier");
            }
                }
            else if (e.get(j).jour.equals("Mardi") && e.get(j).heure == 8 ){
                              mardi_8.setText(e.get(j).titre);
                              meet14.setText("meet");
            if(srvUser.getCurrentUser().getRoles().contains("[\"ROLE_COACH\"]")){                  
                              md_mardi_8.setText("Modifier");
            }              
            
                }
            else if (e.get(j).jour.equals("Mardi") && e.get(j).heure == 12 ){
                              mardi_12.setText(e.get(j).titre);
                              meet15.setText("meet");
                              
            if(srvUser.getCurrentUser().getRoles().contains("[\"ROLE_COACH\"]")){                  
                              md_mardi_12.setText("Modifier");
            }            
                }
            else if (e.get(j).jour.equals("Mardi") && e.get(j).heure == 16 ){
                              mardi_17.setText(e.get(j).titre);       
                              meet16.setText("meet");
                              
            if(srvUser.getCurrentUser().getRoles().contains("[\"ROLE_COACH\"]")){                  
                              md_mardi_17.setText("Modifier");
            }  
            
                }
            else if (e.get(j).jour.equals("Mardi") && e.get(j).heure == 20 ){
                              mardi_19.setText(e.get(j).titre);
                              meet17.setText("meet");
                              
            if(srvUser.getCurrentUser().getRoles().contains("[\"ROLE_COACH\"]")){                  
                              md_mardi_19.setText("Modifier");
            }                                
            
                }
            else if (e.get(j).jour.equals("Mercredi") && e.get(j).heure == 8 ){
                              mercredi_8.setText(e.get(j).titre); 
                              meet18.setText("meet");
                              
           if(srvUser.getCurrentUser().getRoles().contains("[\"ROLE_COACH\"]")){                  
                              md_mercredi_8.setText("Modifier");
            }                               
            
                }            
            else if (e.get(j).jour.equals("Mercredi") && e.get(j).heure == 12 ){
                              mercredi_12.setText(e.get(j).titre);
                              meet19.setText("meet");
                              
            if(srvUser.getCurrentUser().getRoles().contains("[\"ROLE_COACH\"]")){                  
                              md_mercredi_12.setText("Modifier");
            }                                
            
            }
            else if (e.get(j).jour.equals("Mercredi") && e.get(j).heure == 16 ){
                              mercredi_17.setText(e.get(j).titre);
                              meet110.setText("meet");
                              
            if(srvUser.getCurrentUser().getRoles().contains("[\"ROLE_COACH\"]")){                  
                              md_mercredi_17.setText("Modifier");
            }                               
            } 
            else if (e.get(j).jour.equals("Mercredi") && e.get(j).heure == 20 ){
                              mercredi_19.setText(e.get(j).titre);
                              meet111.setText("meet");
                              
           if(srvUser.getCurrentUser().getRoles().contains("[\"ROLE_COACH\"]")){                  
                              md_mercredi_19.setText("Modifier");
            }                               
            }
            else if (e.get(j).jour.equals("Jeudi") && e.get(j).heure == 8 ){
                              jeudi_8.setText(e.get(j).titre);
                              meet112.setText("meet");
                              
           if(srvUser.getCurrentUser().getRoles().contains("[\"ROLE_COACH\"]")){                  
                              md_jeudi_8.setText("Modifier");
            }                               
        }
            else if (e.get(j).jour.equals("Jeudi") && e.get(j).heure == 12 ){
                              jeudi_12.setText(e.get(j).titre);
                              meet113.setText("meet");
                              
           if(srvUser.getCurrentUser().getRoles().contains("[\"ROLE_COACH\"]")){                  
                              md_jeudi_12.setText("Modifier");
            }                               
                              
        }
            else if (e.get(j).jour.equals("Jeudi") && e.get(j).heure == 16 ){
                              jeudi_17.setText(e.get(j).titre);
                              meet114.setText("meet");
                              
                   if(srvUser.getCurrentUser().getRoles().contains("[\"ROLE_COACH\"]")){                  
                              md_jeudi_17.setText("Modifier");
            }                      
        }  
            else if (e.get(j).jour.equals("Jeudi") && e.get(j).heure == 20 ){
                              jeudi_19.setText(e.get(j).titre);

                              meet115.setText("meet");
           if(srvUser.getCurrentUser().getRoles().contains("[\"ROLE_COACH\"]")){                  
                              md_jeudi_19.setText("Modifier");
            }                              
        }
            else if (e.get(j).jour.equals("Vendredi") && e.get(j).heure == 8 ){
                              vendredi_8.setText(e.get(j).titre);
                              
                              meet116.setText("meet");
            if(srvUser.getCurrentUser().getRoles().contains("[\"ROLE_COACH\"]")){                  
                              md_vendredi_8.setText("Modifier");
            }                  
        } 
            else if (e.get(j).jour.equals("Vendredi") && e.get(j).heure == 12 ){
                              vendredi_12.setText(e.get(j).titre);
                              
                              meet117.setText("meet");
            if(srvUser.getCurrentUser().getRoles().contains("[\"ROLE_COACH\"]")){                  
                              md_vendredi_12.setText("Modifier");
            }                               
        }
            else if (e.get(j).jour.equals("Vendredi") && e.get(j).heure == 16 ){
                              vendredi_17.setText(e.get(j).titre);
                              
                              meet118.setText("meet");
            if(srvUser.getCurrentUser().getRoles().contains("[\"ROLE_COACH\"]")){                  
                              md_vendredi_17.setText("Modifier");
            }                               
        }
            else if (e.get(j).jour.equals("Vendredi") && e.get(j).heure == 20 ){
                              vendredi_19.setText(e.get(j).titre);
                              meet119.setText("meet");
                              
            if(srvUser.getCurrentUser().getRoles().contains("[\"ROLE_COACH\"]")){                  
                              md_vendredi_19.setText("Modifier");
            }                               
        }
            else if (e.get(j).jour.equals("Samedi") && e.get(j).heure == 8 ){
                              samedi_8.setText(e.get(j).titre);
                              meet120.setText("meet");
                              
            if(srvUser.getCurrentUser().getRoles().contains("[\"ROLE_COACH\"]")){                  
                              md_samedi_8.setText("Modifier");
            }                               
                              
        }            
            else if (e.get(j).jour.equals("Samedi") && e.get(j).heure == 12 ){
                              samedi_12.setText(e.get(j).titre);
                              meet121.setText("meet");
                              
            if(srvUser.getCurrentUser().getRoles().contains("[\"ROLE_COACH\"]")){                  
                              md_samedi_12.setText("Modifier");
            }                              
                              
            
        }
            else if (e.get(j).jour.equals("Samedi") && e.get(j).heure == 16 ){
                              samedi_17.setText(e.get(j).titre);
                              meet122.setText("meet");
                              
            if(srvUser.getCurrentUser().getRoles().contains("[\"ROLE_COACH\"]")){                  
                              md_samedi_17.setText("Modifier");
            }                              
            
        }
            else if (e.get(j).jour.equals("Samedi") && e.get(j).heure == 20 ){
                              samedi_19.setText(e.get(j).titre);
                              meet123.setText("meet");

            if(srvUser.getCurrentUser().getRoles().contains("[\"ROLE_COACH\"]")){                  
                              md_samedi_19.setText("Modifier");
            }                              
            
        }
            else if (e.get(j).jour.equals("Dimanche") && e.get(j).heure == 8 ){
                              dimanche_8.setText(e.get(j).titre);
                              meet124.setText("meet");
                              
            if(srvUser.getCurrentUser().getRoles().contains("[\"ROLE_COACH\"]")){                  
                              md_dimanche_8.setText("Modifier");
            }            
        }
            else if (e.get(j).jour.equals("Dimanche") && e.get(j).heure == 12 ){
                              dimanche_12.setText(e.get(j).titre);
                              meet125.setText("meet");
                              
            if(srvUser.getCurrentUser().getRoles().contains("[\"ROLE_COACH\"]")){                  
                              md_dimanche_12.setText("Modifier");
            }                               
            
        }
            else if (e.get(j).jour.equals("Dimanche") && e.get(j).heure == 16 ){
                              dimanche_17.setText(e.get(j).titre);
                              meet126.setText("meet");
                              
            if(srvUser.getCurrentUser().getRoles().contains("[\"ROLE_COACH\"]")){                  
                              md_dimanche_17.setText("Modifier");
            }                               
            
        }
            else if (e.get(j).jour.equals("Dimanche") && e.get(j).heure == 20 ){
                              dimanche_19.setText(e.get(j).titre); 
                              meet127.setText("meet");
                              
            if(srvUser.getCurrentUser().getRoles().contains("[\"ROLE_COACH\"]")){                  
                              md_dimanche_19.setText("Modifier");
            }                       
            
        }            
        } 
        }
        
        
        catch (SQLException ex) {
            Logger.getLogger(CoachPlanningController.class.getName()).log(Level.SEVERE, null, ex);
        }
            // TODO
    }
              public void planning(ActionEvent event) throws IOException{
              Parent root = FXMLLoader.load(getClass().getResource("/GUI/CoachPlanning.fxml")); 
              Scene scene = new Scene(root);
              Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
              stage.setScene(scene);
              stage.show();
    }
              public void ajouter(ActionEvent event) throws IOException, SQLException, Exception{                   
                                 
              UserService user = new UserService();
              if(user.getCurrentUser().getRoles().contains("[]")){   
               AlertDialog.showNotification("Cher Abonn??","Seulement votre coach peut ajouter un entrainnement ", AlertDialog.image_cross);   
              }
              else if(user.getCurrentUser().getRoles().contains("[\"ROLE_COACH\"]"))
              {
              Entrainement e= new Entrainement();
              EntrainementService es = new EntrainementService();
              CoachService CoS = new CoachService();
              Coach coach = CoS.InfoCoach(user.getCurrentUser().getId());
              AbonnementService as = new AbonnementService();  
              String Titre = titre.getText().toString();
              String jour = (String) box.getSelectionModel().getSelectedItem();
              String heure = (String) Heure.getSelectionModel().getSelectedItem();
              String Type = type.getText().toString();
              String Meet = meet.getText().toString();   
              e.setCoach_id(CoS.getcoachidbyuserid(user.getCurrentUser().getId()));
          
              if(jour != null && heure!= null && titre.getText().length()!=0  ){
                e.setTitre(Titre);  
                e.setJour(jour);
                if(heure.equalsIgnoreCase("6.00am - 8.00am")){    
                int i= 8 ;
                e.setHeure(i);
                }
                else if(heure.equalsIgnoreCase("10.00am - 12.00am")){
                 int i = 12;       
                e.setHeure(i);
                }
                else if(heure.equalsIgnoreCase("5.00pm - 7.00pm")){
                 int i = 16;       
                 e.setHeure(i);
                } 
               else if(heure.equalsIgnoreCase("7.00pm - 9.00pm")){
                 int i = 20;   
                 e.setHeure(i);
                }
              e.setType(Type);
              e.setMeet(Meet);    
              es.ajouterEntrainement(e);
              
              Parent root = FXMLLoader.load(getClass().getResource("/GUI/CoachPlanning.fxml")); 
              Scene scene = new Scene(root);
              Stage stage1 = (Stage) ((Node) event.getSource()).getScene().getWindow();
              stage1.setScene(scene);
              stage1.show();
              List <String> abb = as.Abonnes(coach.getId());
                      for(int j=0; j<abb.size(); j+=2)
                {                 
                   JavaMailUtil.sendMail(abb.get(j+1));
                 }
              AlertDialog.showNotification("Ajout","Entrainnement le "+box.getSelectionModel().getSelectedItem()+" a "+Heure.getSelectionModel().getSelectedItem()+" bien Ajouter", AlertDialog.image_checked);        
              }
              else if(heure == null){ 
                  AlertDialog.showNotification("Ajout","vous devez choisir l'heure", AlertDialog.image_cross);
                
              }
              else if(jour == null){ 
                  AlertDialog.showNotification("Ajout","vous devez choisir un jour", AlertDialog.image_cross);    
              }
             else if(titre.getText().length()== 0){ 
                  AlertDialog.showNotification("Ajout","vous devez choisir un titre", AlertDialog.image_cross);    
              }
                
              }
              }
          
    public void md_lundi_8(ActionEvent event) throws IOException, SQLException{
            FXMLLoader loader = new FXMLLoader ();
            User user = UserService.getCurrentUser();
            EntrainementService es = new EntrainementService();
            CoachService CoS = new CoachService();
            List<Entrainement> e = es.getEntrainementByCoachId(CoS.getcoachidbyuserid(user.getId()));
            loader.setLocation(getClass().getResource("/GUI/updateplanning.fxml"));
               try {
                loader.load();
                    } catch (IOException ex) {
                      Logger.getLogger(CoachPlanningController.class.getName()).log(Level.SEVERE, null, ex);
                         } 
                         UpdateplanningController up = loader.getController();
                         for(int j=0; j<e.size(); j++){
                         if(e.get(j).jour.equals("Lundi") && e.get(j).heure == 8 ){  
                         up.setTextField(e.get(j).id,e.get(j).titre, e.get(j).jour,e.get(j).heure, e.get(j).type, e.get(j).meet);
                         }
                         }
                         Parent parent = loader.getRoot();
                         Stage stage = new Stage();
                         stage.setScene(new Scene(parent));
                         stage.initStyle(StageStyle.UTILITY);
                         stage.show();
                        Stage stage1 = (Stage) ((Node) event.getSource()).getScene().getWindow();
                                                 
                         }  
    public void md_lundi_12(ActionEvent event) throws IOException, SQLException{
        FXMLLoader loader = new FXMLLoader ();
            User user = UserService.getCurrentUser();
            EntrainementService es = new EntrainementService();
            CoachService CoS = new CoachService();
            List<Entrainement> e = es.getEntrainementByCoachId(CoS.getcoachidbyuserid(user.getId()));
            loader.setLocation(getClass().getResource("/GUI/updateplanning.fxml"));
               try {
                loader.load();
                    } catch (IOException ex) {
                      Logger.getLogger(CoachPlanningController.class.getName()).log(Level.SEVERE, null, ex);
                         } 
                         UpdateplanningController up = loader.getController();
                         for(int j=0; j<e.size(); j++){
                         if(e.get(j).jour.equals("Lundi") && e.get(j).heure == 12 ){  
                         up.setTextField(e.get(j).id,e.get(j).titre, e.get(j).jour,e.get(j).heure, e.get(j).type, e.get(j).meet);
                         }
                         }
                         Parent parent = loader.getRoot();
                         Stage stage = new Stage();
                         stage.setScene(new Scene(parent));
                         stage.initStyle(StageStyle.UTILITY);
                         stage.show();
        
        }
    public void md_lundi_17(ActionEvent event) throws IOException, SQLException{
        FXMLLoader loader = new FXMLLoader ();
            User user = UserService.getCurrentUser();
            EntrainementService es = new EntrainementService();
            CoachService CoS = new CoachService();
            List<Entrainement> e = es.getEntrainementByCoachId(CoS.getcoachidbyuserid(user.getId()));
            loader.setLocation(getClass().getResource("/GUI/updateplanning.fxml"));
               try {
                loader.load();
                    } catch (IOException ex) {
                      Logger.getLogger(CoachPlanningController.class.getName()).log(Level.SEVERE, null, ex);
                         } 
                         UpdateplanningController up = loader.getController();
                         for(int j=0; j<e.size(); j++){
                         if(e.get(j).jour.equals("Lundi") && e.get(j).heure == 16 ){  
                         up.setTextField(e.get(j).id,e.get(j).titre, e.get(j).jour,e.get(j).heure, e.get(j).type, e.get(j).meet);
                         }
                         }
                         Parent parent = loader.getRoot();
                         Stage stage = new Stage();
                         stage.setScene(new Scene(parent));
                         stage.initStyle(StageStyle.UTILITY);
                         stage.show();
        }
    public void md_lundi_19(ActionEvent event) throws IOException, SQLException{
        FXMLLoader loader = new FXMLLoader ();
            User user = UserService.getCurrentUser();
            EntrainementService es = new EntrainementService();
            CoachService CoS = new CoachService();
            List<Entrainement> e = es.getEntrainementByCoachId(CoS.getcoachidbyuserid(user.getId()));
            loader.setLocation(getClass().getResource("/GUI/updateplanning.fxml"));
               try {
                loader.load();
                    } catch (IOException ex) {
                      Logger.getLogger(CoachPlanningController.class.getName()).log(Level.SEVERE, null, ex);
                         } 
                         UpdateplanningController up = loader.getController();
                         for(int j=0; j<e.size(); j++){
                         if(e.get(j).jour.equals("Lundi") && e.get(j).heure == 20 ){  
                         up.setTextField(e.get(j).id,e.get(j).titre, e.get(j).jour,e.get(j).heure, e.get(j).type, e.get(j).meet);
                         }
                         }
                         Parent parent = loader.getRoot();
                         Stage stage = new Stage();
                         stage.setScene(new Scene(parent));
                         stage.initStyle(StageStyle.UTILITY);
                         stage.show();
        }
    public void md_mardi_8(ActionEvent event) throws IOException, SQLException{
        FXMLLoader loader = new FXMLLoader ();
            User user = UserService.getCurrentUser();
            EntrainementService es = new EntrainementService();
            CoachService CoS = new CoachService();
            List<Entrainement> e = es.getEntrainementByCoachId(CoS.getcoachidbyuserid(user.getId()));
            loader.setLocation(getClass().getResource("/GUI/updateplanning.fxml"));
               try {
                loader.load();
                    } catch (IOException ex) {
                      Logger.getLogger(CoachPlanningController.class.getName()).log(Level.SEVERE, null, ex);
                         } 
                         UpdateplanningController up = loader.getController();
                         for(int j=0; j<e.size(); j++){
                         if(e.get(j).jour.equals("Mardi") && e.get(j).heure == 8 ){  
                         up.setTextField(e.get(j).id,e.get(j).titre, e.get(j).jour,e.get(j).heure, e.get(j).type, e.get(j).meet);
                         }
                         }
                         Parent parent = loader.getRoot();
                         Stage stage = new Stage();
                         stage.setScene(new Scene(parent));
                         stage.initStyle(StageStyle.UTILITY);
                         stage.show();
        }
    public void md_mardi_12(ActionEvent event) throws IOException, SQLException{
                FXMLLoader loader = new FXMLLoader ();
            User user = UserService.getCurrentUser();
            EntrainementService es = new EntrainementService();
            CoachService CoS = new CoachService();
            List<Entrainement> e = es.getEntrainementByCoachId(CoS.getcoachidbyuserid(user.getId()));
            loader.setLocation(getClass().getResource("/GUI/updateplanning.fxml"));
               try {
                loader.load();
                    } catch (IOException ex) {
                      Logger.getLogger(CoachPlanningController.class.getName()).log(Level.SEVERE, null, ex);
                         } 
                         UpdateplanningController up = loader.getController();
                         for(int j=0; j<e.size(); j++){
                         if(e.get(j).jour.equals("Mardi") && e.get(j).heure == 12 ){  
                         up.setTextField(e.get(j).id,e.get(j).titre, e.get(j).jour,e.get(j).heure, e.get(j).type, e.get(j).meet);
                         }
                         }
                         Parent parent = loader.getRoot();
                         Stage stage = new Stage();
                         stage.setScene(new Scene(parent));
                         stage.initStyle(StageStyle.UTILITY);
                         stage.show();
        }
    public void md_mardi_17(ActionEvent event) throws IOException, SQLException{
                FXMLLoader loader = new FXMLLoader ();
            User user = UserService.getCurrentUser();
            EntrainementService es = new EntrainementService();
            CoachService CoS = new CoachService();
            List<Entrainement> e = es.getEntrainementByCoachId(CoS.getcoachidbyuserid(user.getId()));
            loader.setLocation(getClass().getResource("/GUI/updateplanning.fxml"));
               try {
                loader.load();
                    } catch (IOException ex) {
                      Logger.getLogger(CoachPlanningController.class.getName()).log(Level.SEVERE, null, ex);
                         } 
                         UpdateplanningController up = loader.getController();
                         for(int j=0; j<e.size(); j++){
                         if(e.get(j).jour.equals("Mardi") && e.get(j).heure == 16 ){  
                         up.setTextField(e.get(j).id,e.get(j).titre, e.get(j).jour,e.get(j).heure, e.get(j).type, e.get(j).meet);
                         }
                         }
                         Parent parent = loader.getRoot();
                         Stage stage = new Stage();
                         stage.setScene(new Scene(parent));
                         stage.initStyle(StageStyle.UTILITY);
                         stage.show();
        }
    public void md_mardi_19(ActionEvent event) throws IOException, SQLException{
                FXMLLoader loader = new FXMLLoader ();
            User user = UserService.getCurrentUser();
            EntrainementService es = new EntrainementService();
            CoachService CoS = new CoachService();
            List<Entrainement> e = es.getEntrainementByCoachId(CoS.getcoachidbyuserid(user.getId()));
            loader.setLocation(getClass().getResource("/GUI/updateplanning.fxml"));
               try {
                loader.load();
                    } catch (IOException ex) {
                      Logger.getLogger(CoachPlanningController.class.getName()).log(Level.SEVERE, null, ex);
                         } 
                         UpdateplanningController up = loader.getController();
                         for(int j=0; j<e.size(); j++){
                         if(e.get(j).jour.equals("Mardi") && e.get(j).heure == 20 ){  
                         up.setTextField(e.get(j).id,e.get(j).titre, e.get(j).jour,e.get(j).heure, e.get(j).type, e.get(j).meet);
                         }
                         }
                         Parent parent = loader.getRoot();
                         Stage stage = new Stage();
                         stage.setScene(new Scene(parent));
                         stage.initStyle(StageStyle.UTILITY);
                         stage.show();
        }
    public void md_mercredi_8(ActionEvent event) throws IOException, SQLException{
                FXMLLoader loader = new FXMLLoader ();
            User user = UserService.getCurrentUser();
            EntrainementService es = new EntrainementService();
            CoachService CoS = new CoachService();
            List<Entrainement> e = es.getEntrainementByCoachId(CoS.getcoachidbyuserid(user.getId()));
            loader.setLocation(getClass().getResource("/GUI/updateplanning.fxml"));
               try {
                loader.load();
                    } catch (IOException ex) {
                      Logger.getLogger(CoachPlanningController.class.getName()).log(Level.SEVERE, null, ex);
                         } 
                         UpdateplanningController up = loader.getController();
                         for(int j=0; j<e.size(); j++){
                         if(e.get(j).jour.equals("Mercredi") && e.get(j).heure == 8 ){  
                         up.setTextField(e.get(j).id,e.get(j).titre, e.get(j).jour,e.get(j).heure, e.get(j).type, e.get(j).meet);
                         }
                         }
                         Parent parent = loader.getRoot();
                         Stage stage = new Stage();
                         stage.setScene(new Scene(parent));
                         stage.initStyle(StageStyle.UTILITY);
                         stage.show();
        }
    public void md_mercredi_12(ActionEvent event) throws IOException, SQLException{
                        FXMLLoader loader = new FXMLLoader ();
            User user = UserService.getCurrentUser();
            EntrainementService es = new EntrainementService();
            CoachService CoS = new CoachService();
            List<Entrainement> e = es.getEntrainementByCoachId(CoS.getcoachidbyuserid(user.getId()));
            loader.setLocation(getClass().getResource("/GUI/updateplanning.fxml"));
               try {
                loader.load();
                    } catch (IOException ex) {
                      Logger.getLogger(CoachPlanningController.class.getName()).log(Level.SEVERE, null, ex);
                         } 
                         UpdateplanningController up = loader.getController();
                         for(int j=0; j<e.size(); j++){
                         if(e.get(j).jour.equals("Mercredi") && e.get(j).heure == 12 ){  
                         up.setTextField(e.get(j).id,e.get(j).titre, e.get(j).jour,e.get(j).heure, e.get(j).type, e.get(j).meet);
                         }
                         }
                         Parent parent = loader.getRoot();
                         Stage stage = new Stage();
                         stage.setScene(new Scene(parent));
                         stage.initStyle(StageStyle.UTILITY);
                         stage.show();
        }
    public void md_mercredi_17(ActionEvent event) throws IOException, SQLException{
                        FXMLLoader loader = new FXMLLoader ();
            User user = UserService.getCurrentUser();
            EntrainementService es = new EntrainementService();
            CoachService CoS = new CoachService();
            List<Entrainement> e = es.getEntrainementByCoachId(CoS.getcoachidbyuserid(user.getId()));
            loader.setLocation(getClass().getResource("/GUI/updateplanning.fxml"));
               try {
                loader.load();
                    } catch (IOException ex) {
                      Logger.getLogger(CoachPlanningController.class.getName()).log(Level.SEVERE, null, ex);
                         } 
                         UpdateplanningController up = loader.getController();
                         for(int j=0; j<e.size(); j++){
                         if(e.get(j).jour.equals("Mercredi") && e.get(j).heure == 16 ){  
                         up.setTextField(e.get(j).id,e.get(j).titre, e.get(j).jour,e.get(j).heure, e.get(j).type, e.get(j).meet);
                         }
                         }
                         Parent parent = loader.getRoot();
                         Stage stage = new Stage();
                         stage.setScene(new Scene(parent));
                         stage.initStyle(StageStyle.UTILITY);
                         stage.show();
        }
    public void md_mercredi_19(ActionEvent event) throws IOException, SQLException{
                        FXMLLoader loader = new FXMLLoader ();
            User user = UserService.getCurrentUser();
            EntrainementService es = new EntrainementService();
            CoachService CoS = new CoachService();
            List<Entrainement> e = es.getEntrainementByCoachId(CoS.getcoachidbyuserid(user.getId()));
            loader.setLocation(getClass().getResource("/GUI/updateplanning.fxml"));
               try {
                loader.load();
                    } catch (IOException ex) {
                      Logger.getLogger(CoachPlanningController.class.getName()).log(Level.SEVERE, null, ex);
                         } 
                         UpdateplanningController up = loader.getController();
                         for(int j=0; j<e.size(); j++){
                         if(e.get(j).jour.equals("Mercredi") && e.get(j).heure == 20 ){  
                         up.setTextField(e.get(j).id,e.get(j).titre, e.get(j).jour,e.get(j).heure, e.get(j).type, e.get(j).meet);
                         }
                         }
                         Parent parent = loader.getRoot();
                         Stage stage = new Stage();
                         stage.setScene(new Scene(parent));
                         stage.initStyle(StageStyle.UTILITY);
                         stage.show();
        }
    public void md_jeudi_8(ActionEvent event) throws IOException, SQLException{
                        FXMLLoader loader = new FXMLLoader ();
            User user = UserService.getCurrentUser();
            EntrainementService es = new EntrainementService();
            CoachService CoS = new CoachService();
            List<Entrainement> e = es.getEntrainementByCoachId(CoS.getcoachidbyuserid(user.getId()));
            loader.setLocation(getClass().getResource("/GUI/updateplanning.fxml"));
               try {
                loader.load();
                    } catch (IOException ex) {
                      Logger.getLogger(CoachPlanningController.class.getName()).log(Level.SEVERE, null, ex);
                         } 
                         UpdateplanningController up = loader.getController();
                         for(int j=0; j<e.size(); j++){
                         if(e.get(j).jour.equals("Jeudi") && e.get(j).heure == 8 ){  
                         up.setTextField(e.get(j).id,e.get(j).titre, e.get(j).jour,e.get(j).heure, e.get(j).type, e.get(j).meet);
                         }
                         }
                         Parent parent = loader.getRoot();
                         Stage stage = new Stage();
                         stage.setScene(new Scene(parent));
                         stage.initStyle(StageStyle.UTILITY);
                         stage.show();        
        }
    public void md_jeudi_12(ActionEvent event) throws IOException, SQLException{
          FXMLLoader loader = new FXMLLoader ();
            User user = UserService.getCurrentUser();
            EntrainementService es = new EntrainementService();
            CoachService CoS = new CoachService();
            List<Entrainement> e = es.getEntrainementByCoachId(CoS.getcoachidbyuserid(user.getId()));
            loader.setLocation(getClass().getResource("/GUI/updateplanning.fxml"));
               try {
                loader.load();
                    } catch (IOException ex) {
                      Logger.getLogger(CoachPlanningController.class.getName()).log(Level.SEVERE, null, ex);
                         } 
                         UpdateplanningController up = loader.getController();
                         for(int j=0; j<e.size(); j++){
                         if(e.get(j).jour.equals("Jeudi") && e.get(j).heure == 12 ){  
                         up.setTextField(e.get(j).id,e.get(j).titre, e.get(j).jour,e.get(j).heure, e.get(j).type, e.get(j).meet);
                         }
                         }
                         Parent parent = loader.getRoot();
                         Stage stage = new Stage();
                         stage.setScene(new Scene(parent));
                         stage.initStyle(StageStyle.UTILITY);
                         stage.show();
        }
    public void md_jeudi_17(ActionEvent event) throws IOException, SQLException{
          FXMLLoader loader = new FXMLLoader ();
            User user = UserService.getCurrentUser();
            EntrainementService es = new EntrainementService();
            CoachService CoS = new CoachService();
            List<Entrainement> e = es.getEntrainementByCoachId(CoS.getcoachidbyuserid(user.getId()));
            loader.setLocation(getClass().getResource("/GUI/updateplanning.fxml"));
               try {
                loader.load();
                    } catch (IOException ex) {
                      Logger.getLogger(CoachPlanningController.class.getName()).log(Level.SEVERE, null, ex);
                         } 
                         UpdateplanningController up = loader.getController();
                         for(int j=0; j<e.size(); j++){
                         if(e.get(j).jour.equals("Jeudi") && e.get(j).heure == 16 ){  
                         up.setTextField(e.get(j).id,e.get(j).titre, e.get(j).jour,e.get(j).heure, e.get(j).type, e.get(j).meet);
                         }
                         }
                         Parent parent = loader.getRoot();
                         Stage stage = new Stage();
                         stage.setScene(new Scene(parent));
                         stage.initStyle(StageStyle.UTILITY);
                         stage.show();
        }
    public void md_jeudi_19(ActionEvent event) throws IOException, SQLException{
          FXMLLoader loader = new FXMLLoader ();
            User user = UserService.getCurrentUser();
            EntrainementService es = new EntrainementService();
            CoachService CoS = new CoachService();
            List<Entrainement> e = es.getEntrainementByCoachId(CoS.getcoachidbyuserid(user.getId()));
            loader.setLocation(getClass().getResource("/GUI/updateplanning.fxml"));
               try {
                loader.load();
                    } catch (IOException ex) {
                      Logger.getLogger(CoachPlanningController.class.getName()).log(Level.SEVERE, null, ex);
                         } 
                         UpdateplanningController up = loader.getController();
                         for(int j=0; j<e.size(); j++){
                         if(e.get(j).jour.equals("Jeudi") && e.get(j).heure == 20 ){  
                         up.setTextField(e.get(j).id,e.get(j).titre, e.get(j).jour,e.get(j).heure, e.get(j).type, e.get(j).meet);
                         }
                         }
                         Parent parent = loader.getRoot();
                         Stage stage = new Stage();
                         stage.setScene(new Scene(parent));
                         stage.initStyle(StageStyle.UTILITY);
                         stage.show();
        }
    public void md_vendredi_8(ActionEvent event) throws IOException, SQLException{
          FXMLLoader loader = new FXMLLoader ();
            User user = UserService.getCurrentUser();
            EntrainementService es = new EntrainementService();
            CoachService CoS = new CoachService();
            List<Entrainement> e = es.getEntrainementByCoachId(CoS.getcoachidbyuserid(user.getId()));
            loader.setLocation(getClass().getResource("/GUI/updateplanning.fxml"));
               try {
                loader.load();
                    } catch (IOException ex) {
                      Logger.getLogger(CoachPlanningController.class.getName()).log(Level.SEVERE, null, ex);
                         } 
                         UpdateplanningController up = loader.getController();
                         for(int j=0; j<e.size(); j++){
                         if(e.get(j).jour.equals("Vendredi") && e.get(j).heure == 8 ){  
                         up.setTextField(e.get(j).id,e.get(j).titre, e.get(j).jour,e.get(j).heure, e.get(j).type, e.get(j).meet);
                         }
                         }
                         Parent parent = loader.getRoot();
                         Stage stage = new Stage();
                         stage.setScene(new Scene(parent));
                         stage.initStyle(StageStyle.UTILITY);
                         stage.show();
        }
    public void md_vendredi_12(ActionEvent event) throws IOException, SQLException{
                  FXMLLoader loader = new FXMLLoader ();
            User user = UserService.getCurrentUser();
            EntrainementService es = new EntrainementService();
            CoachService CoS = new CoachService();
            List<Entrainement> e = es.getEntrainementByCoachId(CoS.getcoachidbyuserid(user.getId()));
            loader.setLocation(getClass().getResource("/GUI/updateplanning.fxml"));
               try {
                loader.load();
                    } catch (IOException ex) {
                      Logger.getLogger(CoachPlanningController.class.getName()).log(Level.SEVERE, null, ex);
                         } 
                         UpdateplanningController up = loader.getController();
                         for(int j=0; j<e.size(); j++){
                         if(e.get(j).jour.equals("Vendredi") && e.get(j).heure == 12 ){  
                         up.setTextField(e.get(j).id,e.get(j).titre, e.get(j).jour,e.get(j).heure, e.get(j).type, e.get(j).meet);
                         }
                         }
                         Parent parent = loader.getRoot();
                         Stage stage = new Stage();
                         stage.setScene(new Scene(parent));
                         stage.initStyle(StageStyle.UTILITY);
                         stage.show();
        }
    public void md_vendredi_17(ActionEvent event) throws IOException, SQLException{
                  FXMLLoader loader = new FXMLLoader ();
            User user = UserService.getCurrentUser();
            EntrainementService es = new EntrainementService();
            CoachService CoS = new CoachService();
            List<Entrainement> e = es.getEntrainementByCoachId(CoS.getcoachidbyuserid(user.getId()));
            loader.setLocation(getClass().getResource("/GUI/updateplanning.fxml"));
               try {
                loader.load();
                    } catch (IOException ex) {
                      Logger.getLogger(CoachPlanningController.class.getName()).log(Level.SEVERE, null, ex);
                         } 
                         UpdateplanningController up = loader.getController();
                         for(int j=0; j<e.size(); j++){
                         if(e.get(j).jour.equals("Vendredi") && e.get(j).heure == 16 ){  
                         up.setTextField(e.get(j).id,e.get(j).titre, e.get(j).jour,e.get(j).heure, e.get(j).type, e.get(j).meet);
                         }
                         }
                         Parent parent = loader.getRoot();
                         Stage stage = new Stage();
                         stage.setScene(new Scene(parent));
                         stage.initStyle(StageStyle.UTILITY);
                         stage.show();
        }
    public void md_vendredi_19(ActionEvent event) throws IOException, SQLException{
                  FXMLLoader loader = new FXMLLoader ();
            User user = UserService.getCurrentUser();
            EntrainementService es = new EntrainementService();
            CoachService CoS = new CoachService();
            List<Entrainement> e = es.getEntrainementByCoachId(CoS.getcoachidbyuserid(user.getId()));
            loader.setLocation(getClass().getResource("/GUI/updateplanning.fxml"));
               try {
                loader.load();
                    } catch (IOException ex) {
                      Logger.getLogger(CoachPlanningController.class.getName()).log(Level.SEVERE, null, ex);
                         } 
                         UpdateplanningController up = loader.getController();
                         for(int j=0; j<e.size(); j++){
                         if(e.get(j).jour.equals("Vendredi") && e.get(j).heure == 20 ){  
                         up.setTextField(e.get(j).id,e.get(j).titre, e.get(j).jour,e.get(j).heure, e.get(j).type, e.get(j).meet);
                         }
                         }
                         Parent parent = loader.getRoot();
                         Stage stage = new Stage();
                         stage.setScene(new Scene(parent));
                         stage.initStyle(StageStyle.UTILITY);
                         stage.show();
        }
    public void md_samedi_8(ActionEvent event) throws IOException, SQLException{
                  FXMLLoader loader = new FXMLLoader ();
            User user = UserService.getCurrentUser();
            EntrainementService es = new EntrainementService();
            CoachService CoS = new CoachService();
            List<Entrainement> e = es.getEntrainementByCoachId(CoS.getcoachidbyuserid(user.getId()));
            loader.setLocation(getClass().getResource("/GUI/updateplanning.fxml"));
               try {
                loader.load();
                    } catch (IOException ex) {
                      Logger.getLogger(CoachPlanningController.class.getName()).log(Level.SEVERE, null, ex);
                         } 
                         UpdateplanningController up = loader.getController();
                         for(int j=0; j<e.size(); j++){
                         if(e.get(j).jour.equals("Samedi") && e.get(j).heure == 8 ){  
                         up.setTextField(e.get(j).id,e.get(j).titre, e.get(j).jour,e.get(j).heure, e.get(j).type, e.get(j).meet);
                         }
                         }
                         Parent parent = loader.getRoot();
                         Stage stage = new Stage();
                         stage.setScene(new Scene(parent));
                         stage.initStyle(StageStyle.UTILITY);
                         stage.show();
        }
    public void md_samedi_12(ActionEvent event) throws IOException, SQLException{
                          FXMLLoader loader = new FXMLLoader ();
            User user = UserService.getCurrentUser();
            EntrainementService es = new EntrainementService();
            CoachService CoS = new CoachService();
            List<Entrainement> e = es.getEntrainementByCoachId(CoS.getcoachidbyuserid(user.getId()));
            loader.setLocation(getClass().getResource("/GUI/updateplanning.fxml"));
               try {
                loader.load();
                    } catch (IOException ex) {
                      Logger.getLogger(CoachPlanningController.class.getName()).log(Level.SEVERE, null, ex);
                         } 
                         UpdateplanningController up = loader.getController();
                         for(int j=0; j<e.size(); j++){
                         if(e.get(j).jour.equals("Samedi") && e.get(j).heure == 12 ){  
                         up.setTextField(e.get(j).id,e.get(j).titre, e.get(j).jour,e.get(j).heure, e.get(j).type, e.get(j).meet);
                         }
                         }
                         Parent parent = loader.getRoot();
                         Stage stage = new Stage();
                         stage.setScene(new Scene(parent));
                         stage.initStyle(StageStyle.UTILITY);
                         stage.show();
        }
    public void md_samedi_17(ActionEvent event) throws IOException, SQLException{
                          FXMLLoader loader = new FXMLLoader ();
            User user = UserService.getCurrentUser();
            EntrainementService es = new EntrainementService();
            CoachService CoS = new CoachService();
            List<Entrainement> e = es.getEntrainementByCoachId(CoS.getcoachidbyuserid(user.getId()));
            loader.setLocation(getClass().getResource("/GUI/updateplanning.fxml"));
               try {
                loader.load();
                    } catch (IOException ex) {
                      Logger.getLogger(CoachPlanningController.class.getName()).log(Level.SEVERE, null, ex);
                         } 
                         UpdateplanningController up = loader.getController();
                         for(int j=0; j<e.size(); j++){
                         if(e.get(j).jour.equals("Samedi") && e.get(j).heure == 16 ){  
                         up.setTextField(e.get(j).id,e.get(j).titre, e.get(j).jour,e.get(j).heure, e.get(j).type, e.get(j).meet);
                         }
                         }
                         Parent parent = loader.getRoot();
                         Stage stage = new Stage();
                         stage.setScene(new Scene(parent));
                         stage.initStyle(StageStyle.UTILITY);
                         stage.show();
        }
    public void md_samedi_19(ActionEvent event) throws IOException, SQLException{
                          FXMLLoader loader = new FXMLLoader ();
            User user = UserService.getCurrentUser();
            EntrainementService es = new EntrainementService();
            CoachService CoS = new CoachService();
            List<Entrainement> e = es.getEntrainementByCoachId(CoS.getcoachidbyuserid(user.getId()));
            loader.setLocation(getClass().getResource("/GUI/updateplanning.fxml"));
               try {
                loader.load();
                    } catch (IOException ex) {
                      Logger.getLogger(CoachPlanningController.class.getName()).log(Level.SEVERE, null, ex);
                         } 
                         UpdateplanningController up = loader.getController();
                         for(int j=0; j<e.size(); j++){
                         if(e.get(j).jour.equals("Samedi") && e.get(j).heure == 20 ){  
                         up.setTextField(e.get(j).id,e.get(j).titre, e.get(j).jour,e.get(j).heure, e.get(j).type, e.get(j).meet);
                         }
                         }
                         Parent parent = loader.getRoot();
                         Stage stage = new Stage();
                         stage.setScene(new Scene(parent));
                         stage.initStyle(StageStyle.UTILITY);
                         stage.show();
        }
    public void md_dimanche_8(ActionEvent event) throws IOException, SQLException{
                          FXMLLoader loader = new FXMLLoader ();
            User user = UserService.getCurrentUser();
            EntrainementService es = new EntrainementService();
            CoachService CoS = new CoachService();
            List<Entrainement> e = es.getEntrainementByCoachId(CoS.getcoachidbyuserid(user.getId()));
            loader.setLocation(getClass().getResource("/GUI/updateplanning.fxml"));
               try {
                loader.load();
                    } catch (IOException ex) {
                      Logger.getLogger(CoachPlanningController.class.getName()).log(Level.SEVERE, null, ex);
                         } 
                         UpdateplanningController up = loader.getController();
                         for(int j=0; j<e.size(); j++){
                         if(e.get(j).jour.equals("Dimanche") && e.get(j).heure == 8 ){  
                         up.setTextField(e.get(j).id,e.get(j).titre, e.get(j).jour,e.get(j).heure, e.get(j).type, e.get(j).meet);
                         }
                         }
                         Parent parent = loader.getRoot();
                         Stage stage = new Stage();
                         stage.setScene(new Scene(parent));
                         stage.initStyle(StageStyle.UTILITY);
                         stage.show();
        }
    public void md_dimanche_12(ActionEvent event) throws IOException, SQLException{
                          FXMLLoader loader = new FXMLLoader ();
            User user = UserService.getCurrentUser();
            EntrainementService es = new EntrainementService();
            CoachService CoS = new CoachService();
            List<Entrainement> e = es.getEntrainementByCoachId(CoS.getcoachidbyuserid(user.getId()));
            loader.setLocation(getClass().getResource("/GUI/updateplanning.fxml"));
               try {
                loader.load();
                    } catch (IOException ex) {
                      Logger.getLogger(CoachPlanningController.class.getName()).log(Level.SEVERE, null, ex);
                         } 
                         UpdateplanningController up = loader.getController();
                         for(int j=0; j<e.size(); j++){
                         if(e.get(j).jour.equals("Dimanche") && e.get(j).heure == 12 ){  
                         up.setTextField(e.get(j).id,e.get(j).titre, e.get(j).jour,e.get(j).heure, e.get(j).type, e.get(j).meet);
                         }
                         }
                         Parent parent = loader.getRoot();
                         Stage stage = new Stage();
                         stage.setScene(new Scene(parent));
                         stage.initStyle(StageStyle.UTILITY);
                         stage.show();        
        }
    public void md_dimanche_17(ActionEvent event) throws IOException, SQLException{
            FXMLLoader loader = new FXMLLoader ();
            User user = UserService.getCurrentUser();
            EntrainementService es = new EntrainementService();
            CoachService CoS = new CoachService();
            List<Entrainement> e = es.getEntrainementByCoachId(CoS.getcoachidbyuserid(user.getId()));
            loader.setLocation(getClass().getResource("/GUI/updateplanning.fxml"));
               try {
                loader.load();
                    } catch (IOException ex) {
                      Logger.getLogger(CoachPlanningController.class.getName()).log(Level.SEVERE, null, ex);
                         } 
                         UpdateplanningController up = loader.getController();
                         for(int j=0; j<e.size(); j++){
                         if(e.get(j).jour.equals("Dimanche") && e.get(j).heure == 16 ){  
                         up.setTextField(e.get(j).id, e.get(j).titre, e.get(j).jour,e.get(j).heure, e.get(j).type, e.get(j).meet);
                         }
                         }
                         Parent parent = loader.getRoot();
                         Stage stage = new Stage();
                         stage.setScene(new Scene(parent));
                         stage.initStyle(StageStyle.UTILITY);
                         stage.show();
        }
    public void md_dimanche_19(ActionEvent event) throws IOException, SQLException{
            FXMLLoader loader = new FXMLLoader ();
            User user = UserService.getCurrentUser();
            EntrainementService es = new EntrainementService();
            CoachService CoS = new CoachService();
            List<Entrainement> e = es.getEntrainementByCoachId(CoS.getcoachidbyuserid(user.getId()));
            loader.setLocation(getClass().getResource("/GUI/updateplanning.fxml"));
               try {
                loader.load();
                    } catch (IOException ex) {
                      Logger.getLogger(CoachPlanningController.class.getName()).log(Level.SEVERE, null, ex);
                         } 
                         UpdateplanningController up = loader.getController();
                         for(int j=0; j<e.size(); j++){
                         if(e.get(j).jour.equals("Dimanche") && e.get(j).heure == 20 ){  
                         up.setTextField(e.get(j).id ,e.get(j).titre, e.get(j).jour,e.get(j).heure, e.get(j).type, e.get(j).meet);
                         }
                         }
                         Parent parent = loader.getRoot();
                         Stage stage = new Stage();
                         stage.setScene(new Scene(parent));
                         stage.initStyle(StageStyle.UTILITY);
                         stage.show();
        }
    
       
        public void profile(ActionEvent event) throws IOException{
              Parent root = FXMLLoader.load(getClass().getResource("/GUI/coachlog.fxml")); 
              Scene scene = new Scene(root);
              Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
              stage.setScene(scene);
              stage.show();
    }
        public void meet1(ActionEvent event) throws IOException, SQLException, URISyntaxException{
            UserService srvUser = new UserService();
            EntrainementService es = new EntrainementService();
            CoachService CoS = new CoachService();
            List<Entrainement> e = es.getEntrainementByCoachId(CoS.getcoachidbyuserid(srvUser.coachidabb(srvUser.getCurrentUser().getId())));
                        
            for(int j=0; j<e.size(); j++){
            if(e.get(j).jour.equals("Lundi") && e.get(j).heure == 8 ){  
            Desktop desktop = java.awt.Desktop.getDesktop();
            URI oURL = new URI(e.get(j).meet);
            desktop.browse(oURL);
            }
            }
           }

        public void meet11(ActionEvent event) throws IOException, SQLException, URISyntaxException{
UserService srvUser = new UserService();
            EntrainementService es = new EntrainementService();
            CoachService CoS = new CoachService();
            List<Entrainement> e = es.getEntrainementByCoachId(CoS.getcoachidbyuserid(srvUser.coachidabb(srvUser.getCurrentUser().getId())));            
            for(int j=0; j<e.size(); j++){
            if(e.get(j).jour.equals("Lundi") && e.get(j).heure == 12 ){  
            Desktop desktop = java.awt.Desktop.getDesktop();
            URI oURL = new URI(e.get(j).meet);
            desktop.browse(oURL);
            }
            }
        }
        public void meet12(ActionEvent event) throws IOException, SQLException, URISyntaxException{
UserService srvUser = new UserService();
            EntrainementService es = new EntrainementService();
            CoachService CoS = new CoachService();
            List<Entrainement> e = es.getEntrainementByCoachId(CoS.getcoachidbyuserid(srvUser.coachidabb(srvUser.getCurrentUser().getId())));           
            for(int j=0; j<e.size(); j++){
            if(e.get(j).jour.equals("Lundi") && e.get(j).heure == 16 ){  
            Desktop desktop = java.awt.Desktop.getDesktop();
            URI oURL = new URI(e.get(j).meet);
            desktop.browse(oURL);
            }
            }
        
        }
        public void meet13(ActionEvent event) throws IOException, SQLException, URISyntaxException{
            UserService srvUser = new UserService();
            EntrainementService es = new EntrainementService();
            CoachService CoS = new CoachService();
            List<Entrainement> e = es.getEntrainementByCoachId(CoS.getcoachidbyuserid(srvUser.coachidabb(srvUser.getCurrentUser().getId())));          
            for(int j=0; j<e.size(); j++){
            if(e.get(j).jour.equals("Lundi") && e.get(j).heure == 20 ){  
            Desktop desktop = java.awt.Desktop.getDesktop();
            URI oURL = new URI(e.get(j).meet);
            desktop.browse(oURL);
            }
            }
        }
        public void meet14(ActionEvent event) throws IOException, SQLException, URISyntaxException{
            UserService srvUser = new UserService();
            EntrainementService es = new EntrainementService();
            CoachService CoS = new CoachService();
            List<Entrainement> e = es.getEntrainementByCoachId(CoS.getcoachidbyuserid(srvUser.coachidabb(srvUser.getCurrentUser().getId())));          
            for(int j=0; j<e.size(); j++){
            if(e.get(j).jour.equals("Mardi") && e.get(j).heure == 8 ){  
            Desktop desktop = java.awt.Desktop.getDesktop();
            URI oURL = new URI(e.get(j).meet);
            desktop.browse(oURL);
            }
            }}
        public void meet15(ActionEvent event) throws IOException, SQLException, URISyntaxException{
            UserService srvUser = new UserService();
            EntrainementService es = new EntrainementService();
            CoachService CoS = new CoachService();
            List<Entrainement> e = es.getEntrainementByCoachId(CoS.getcoachidbyuserid(srvUser.coachidabb(srvUser.getCurrentUser().getId())));          
            for(int j=0; j<e.size(); j++){
            if(e.get(j).jour.equals("Mardi") && e.get(j).heure == 12 ){  
            Desktop desktop = java.awt.Desktop.getDesktop();
            URI oURL = new URI(e.get(j).meet);
            desktop.browse(oURL);
            }
           }
        }
        public void meet16(ActionEvent event) throws IOException, SQLException, URISyntaxException{
            UserService srvUser = new UserService();
            EntrainementService es = new EntrainementService();
            CoachService CoS = new CoachService();
            List<Entrainement> e = es.getEntrainementByCoachId(CoS.getcoachidbyuserid(srvUser.coachidabb(srvUser.getCurrentUser().getId())));          
            for(int j=0; j<e.size(); j++){
            if(e.get(j).jour.equals("Mardi") && e.get(j).heure == 16 ){  
            Desktop desktop = java.awt.Desktop.getDesktop();
            URI oURL = new URI(e.get(j).meet);
            desktop.browse(oURL);
            }
           }
        
        }
        public void meet17(ActionEvent event) throws IOException, SQLException, URISyntaxException{
            UserService srvUser = new UserService();
            EntrainementService es = new EntrainementService();
            CoachService CoS = new CoachService();
            List<Entrainement> e = es.getEntrainementByCoachId(CoS.getcoachidbyuserid(srvUser.coachidabb(srvUser.getCurrentUser().getId())));          
            for(int j=0; j<e.size(); j++){
            if(e.get(j).jour.equals("Mardi") && e.get(j).heure == 20 ){  
            Desktop desktop = java.awt.Desktop.getDesktop();
            URI oURL = new URI(e.get(j).meet);
            desktop.browse(oURL);
            }
           }
        }
        public void meet18(ActionEvent event) throws IOException, SQLException, URISyntaxException{
            UserService srvUser = new UserService();
            EntrainementService es = new EntrainementService();
            CoachService CoS = new CoachService();
            List<Entrainement> e = es.getEntrainementByCoachId(CoS.getcoachidbyuserid(srvUser.coachidabb(srvUser.getCurrentUser().getId())));          
            for(int j=0; j<e.size(); j++){
            if(e.get(j).jour.equals("Mercredi") && e.get(j).heure == 8 ){  
            Desktop desktop = java.awt.Desktop.getDesktop();
            URI oURL = new URI(e.get(j).meet);
            desktop.browse(oURL);
            }
           }
        
        }
        public void meet19(ActionEvent event) throws IOException, SQLException, URISyntaxException{
            UserService srvUser = new UserService();
            EntrainementService es = new EntrainementService();
            CoachService CoS = new CoachService();
            List<Entrainement> e = es.getEntrainementByCoachId(CoS.getcoachidbyuserid(srvUser.coachidabb(srvUser.getCurrentUser().getId())));          
            for(int j=0; j<e.size(); j++){
            if(e.get(j).jour.equals("Mercredi") && e.get(j).heure == 12 ){  
            Desktop desktop = java.awt.Desktop.getDesktop();
            URI oURL = new URI(e.get(j).meet);
            desktop.browse(oURL);
            }
           }
        }
        public void meet110(ActionEvent event) throws IOException, SQLException, URISyntaxException{
            UserService srvUser = new UserService();
            EntrainementService es = new EntrainementService();
            CoachService CoS = new CoachService();
            List<Entrainement> e = es.getEntrainementByCoachId(CoS.getcoachidbyuserid(srvUser.coachidabb(srvUser.getCurrentUser().getId())));          
            for(int j=0; j<e.size(); j++){
            if(e.get(j).jour.equals("Mercredi") && e.get(j).heure == 16 ){  
            Desktop desktop = java.awt.Desktop.getDesktop();
            URI oURL = new URI(e.get(j).meet);
            desktop.browse(oURL);
            }
           }
        
        }
        public void meet111(ActionEvent event) throws IOException, SQLException, URISyntaxException{
            UserService srvUser = new UserService();
            EntrainementService es = new EntrainementService();
            CoachService CoS = new CoachService();
            List<Entrainement> e = es.getEntrainementByCoachId(CoS.getcoachidbyuserid(srvUser.coachidabb(srvUser.getCurrentUser().getId())));          
            for(int j=0; j<e.size(); j++){
            if(e.get(j).jour.equals("Mercredi") && e.get(j).heure == 20 ){  
            Desktop desktop = java.awt.Desktop.getDesktop();
            URI oURL = new URI(e.get(j).meet);
            desktop.browse(oURL);
            }
           }
        
        }
        public void meet112(ActionEvent event) throws IOException, SQLException, URISyntaxException{
            UserService srvUser = new UserService();
            EntrainementService es = new EntrainementService();
            CoachService CoS = new CoachService();
            List<Entrainement> e = es.getEntrainementByCoachId(CoS.getcoachidbyuserid(srvUser.coachidabb(srvUser.getCurrentUser().getId())));          
            for(int j=0; j<e.size(); j++){
            if(e.get(j).jour.equals("Jeudi") && e.get(j).heure == 8 ){  
            Desktop desktop = java.awt.Desktop.getDesktop();
            URI oURL = new URI(e.get(j).meet);
            desktop.browse(oURL);
            }
           }
        
        
        }
        public void meet113(ActionEvent event) throws IOException, SQLException, URISyntaxException{
            UserService srvUser = new UserService();
            EntrainementService es = new EntrainementService();
            CoachService CoS = new CoachService();
            List<Entrainement> e = es.getEntrainementByCoachId(CoS.getcoachidbyuserid(srvUser.coachidabb(srvUser.getCurrentUser().getId())));          
            for(int j=0; j<e.size(); j++){
            if(e.get(j).jour.equals("Jeudi") && e.get(j).heure == 12 ){  
            Desktop desktop = java.awt.Desktop.getDesktop();
            URI oURL = new URI(e.get(j).meet);
            desktop.browse(oURL);
            }
        
        }
        }
        public void meet114(ActionEvent event) throws IOException, SQLException, URISyntaxException{
            UserService srvUser = new UserService();
            EntrainementService es = new EntrainementService();
            CoachService CoS = new CoachService();
            List<Entrainement> e = es.getEntrainementByCoachId(CoS.getcoachidbyuserid(srvUser.coachidabb(srvUser.getCurrentUser().getId())));          
            for(int j=0; j<e.size(); j++){
            if(e.get(j).jour.equals("Jeudi") && e.get(j).heure == 16 ){  
            Desktop desktop = java.awt.Desktop.getDesktop();
            URI oURL = new URI(e.get(j).meet);
            desktop.browse(oURL);
           }
        }        
        
        }
        public void meet115(ActionEvent event) throws IOException, SQLException, URISyntaxException{
            UserService srvUser = new UserService();
            EntrainementService es = new EntrainementService();
            CoachService CoS = new CoachService();
            List<Entrainement> e = es.getEntrainementByCoachId(CoS.getcoachidbyuserid(srvUser.coachidabb(srvUser.getCurrentUser().getId())));          
            for(int j=0; j<e.size(); j++){
            if(e.get(j).jour.equals("Jeudi") && e.get(j).heure == 20 ){  
            Desktop desktop = java.awt.Desktop.getDesktop();
            URI oURL = new URI(e.get(j).meet);
            desktop.browse(oURL);
           }
        }
        }
        public void meet116(ActionEvent event) throws IOException, SQLException, URISyntaxException{
            UserService srvUser = new UserService();
            EntrainementService es = new EntrainementService();
            CoachService CoS = new CoachService();
            List<Entrainement> e = es.getEntrainementByCoachId(CoS.getcoachidbyuserid(srvUser.coachidabb(srvUser.getCurrentUser().getId())));          
            for(int j=0; j<e.size(); j++){
            if(e.get(j).jour.equals("Vendredi") && e.get(j).heure == 8 ){  
            Desktop desktop = java.awt.Desktop.getDesktop();
            URI oURL = new URI(e.get(j).meet);
            desktop.browse(oURL);
           }
        }
        
        }
        public void meet117(ActionEvent event) throws IOException, SQLException, URISyntaxException{
            UserService srvUser = new UserService();
            EntrainementService es = new EntrainementService();
            CoachService CoS = new CoachService();
            List<Entrainement> e = es.getEntrainementByCoachId(CoS.getcoachidbyuserid(srvUser.coachidabb(srvUser.getCurrentUser().getId())));          
            for(int j=0; j<e.size(); j++){
            if(e.get(j).jour.equals("Vendredi") && e.get(j).heure == 12 ){  
            Desktop desktop = java.awt.Desktop.getDesktop();
            URI oURL = new URI(e.get(j).meet);
            desktop.browse(oURL);
           }
        }        
        
        }
        public void meet118(ActionEvent event) throws IOException, SQLException, URISyntaxException{
            UserService srvUser = new UserService();
            EntrainementService es = new EntrainementService();
            CoachService CoS = new CoachService();
            List<Entrainement> e = es.getEntrainementByCoachId(CoS.getcoachidbyuserid(srvUser.coachidabb(srvUser.getCurrentUser().getId())));          
            for(int j=0; j<e.size(); j++){
            if(e.get(j).jour.equals("Vendredi") && e.get(j).heure == 16 ){  
            Desktop desktop = java.awt.Desktop.getDesktop();
            URI oURL = new URI(e.get(j).meet);
            desktop.browse(oURL);
           }
        }        
        
        }
        public void meet119(ActionEvent event) throws IOException, SQLException, URISyntaxException{
            UserService srvUser = new UserService();
            EntrainementService es = new EntrainementService();
            CoachService CoS = new CoachService();
            List<Entrainement> e = es.getEntrainementByCoachId(CoS.getcoachidbyuserid(srvUser.coachidabb(srvUser.getCurrentUser().getId())));          
            for(int j=0; j<e.size(); j++){
            if(e.get(j).jour.equals("Vendredi") && e.get(j).heure == 20 ){  
            Desktop desktop = java.awt.Desktop.getDesktop();
            URI oURL = new URI(e.get(j).meet);
            desktop.browse(oURL);
           }
        }
        
        }
        public void meet120(ActionEvent event) throws IOException, SQLException, URISyntaxException{
            UserService srvUser = new UserService();
            EntrainementService es = new EntrainementService();
            CoachService CoS = new CoachService();
            List<Entrainement> e = es.getEntrainementByCoachId(CoS.getcoachidbyuserid(srvUser.coachidabb(srvUser.getCurrentUser().getId())));          
            for(int j=0; j<e.size(); j++){
            if(e.get(j).jour.equals("Samedi") && e.get(j).heure == 8 ){  
            Desktop desktop = java.awt.Desktop.getDesktop();
            URI oURL = new URI(e.get(j).meet);
            desktop.browse(oURL);
           }
        }
        
        }
        public void meet121(ActionEvent event) throws IOException, SQLException, URISyntaxException{
            UserService srvUser = new UserService();
            EntrainementService es = new EntrainementService();
            CoachService CoS = new CoachService();
            List<Entrainement> e = es.getEntrainementByCoachId(CoS.getcoachidbyuserid(srvUser.coachidabb(srvUser.getCurrentUser().getId())));          
            for(int j=0; j<e.size(); j++){
            if(e.get(j).jour.equals("Samedi") && e.get(j).heure == 12 ){  
            Desktop desktop = java.awt.Desktop.getDesktop();
            URI oURL = new URI(e.get(j).meet);
            desktop.browse(oURL);
           }
        }
        
        
        }
        public void meet122(ActionEvent event) throws IOException, SQLException, URISyntaxException{
            UserService srvUser = new UserService();
            EntrainementService es = new EntrainementService();
            CoachService CoS = new CoachService();
            List<Entrainement> e = es.getEntrainementByCoachId(CoS.getcoachidbyuserid(srvUser.coachidabb(srvUser.getCurrentUser().getId())));          
            for(int j=0; j<e.size(); j++){
            if(e.get(j).jour.equals("Samedi") && e.get(j).heure == 16 ){  
            Desktop desktop = java.awt.Desktop.getDesktop();
            URI oURL = new URI(e.get(j).meet);
            desktop.browse(oURL);
           }
        }
        
        }
        public void meet123(ActionEvent event) throws IOException, SQLException, URISyntaxException{
            UserService srvUser = new UserService();
            EntrainementService es = new EntrainementService();
            CoachService CoS = new CoachService();
            List<Entrainement> e = es.getEntrainementByCoachId(CoS.getcoachidbyuserid(srvUser.coachidabb(srvUser.getCurrentUser().getId())));          
            for(int j=0; j<e.size(); j++){
            if(e.get(j).jour.equals("Samedi") && e.get(j).heure == 20 ){  
            Desktop desktop = java.awt.Desktop.getDesktop();
            URI oURL = new URI(e.get(j).meet);
            desktop.browse(oURL);
           }
        }        
        
        
        
        }
        public void meet124(ActionEvent event) throws IOException, SQLException, URISyntaxException{
            UserService srvUser = new UserService();
            EntrainementService es = new EntrainementService();
            CoachService CoS = new CoachService();
            List<Entrainement> e = es.getEntrainementByCoachId(CoS.getcoachidbyuserid(srvUser.coachidabb(srvUser.getCurrentUser().getId())));          
            for(int j=0; j<e.size(); j++){
            if(e.get(j).jour.equals("Dimanche") && e.get(j).heure == 8 ){  
            Desktop desktop = java.awt.Desktop.getDesktop();
            URI oURL = new URI(e.get(j).meet);
            desktop.browse(oURL);
           }
        }          
        }
        public void meet125(ActionEvent event) throws IOException, SQLException, URISyntaxException{
            UserService srvUser = new UserService();
            EntrainementService es = new EntrainementService();
            CoachService CoS = new CoachService();
            List<Entrainement> e = es.getEntrainementByCoachId(CoS.getcoachidbyuserid(srvUser.coachidabb(srvUser.getCurrentUser().getId())));          
            for(int j=0; j<e.size(); j++){
            if(e.get(j).jour.equals("Dimanche") && e.get(j).heure == 12 ){  
            Desktop desktop = java.awt.Desktop.getDesktop();
            URI oURL = new URI(e.get(j).meet);
            desktop.browse(oURL);
           }
        }         
        
        }
        public void meet126(ActionEvent event) throws IOException, SQLException, URISyntaxException{
            UserService srvUser = new UserService();
            EntrainementService es = new EntrainementService();
            CoachService CoS = new CoachService();
            List<Entrainement> e = es.getEntrainementByCoachId(CoS.getcoachidbyuserid(srvUser.coachidabb(srvUser.getCurrentUser().getId())));          
            for(int j=0; j<e.size(); j++){
            if(e.get(j).jour.equals("Dimanche") && e.get(j).heure == 16 ){  
            Desktop desktop = java.awt.Desktop.getDesktop();
            URI oURL = new URI(e.get(j).meet);
            desktop.browse(oURL);
           }
        }            
        }
        public void meet127(ActionEvent event) throws IOException, SQLException, URISyntaxException{
            UserService srvUser = new UserService();
            EntrainementService es = new EntrainementService();
            CoachService CoS = new CoachService();
            List<Entrainement> e = es.getEntrainementByCoachId(CoS.getcoachidbyuserid(srvUser.coachidabb(srvUser.getCurrentUser().getId())));          
            for(int j=0; j<e.size(); j++){
            if(e.get(j).jour.equals("Dimanche") && e.get(j).heure == 20 ){  
            Desktop desktop = java.awt.Desktop.getDesktop();
            URI oURL = new URI(e.get(j).meet);
            desktop.browse(oURL);
           }
        } 
        
        
        
        
        }
    
        
    public void Accueil(ActionEvent event) throws IOException{          
              Parent root = FXMLLoader.load(getClass().getResource("/GUI/Accueil.fxml")); 
              Scene scene = new Scene(root);
              Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
              stage.setScene(scene);
              stage.show();    
    }
    public void Music(ActionEvent event) throws IOException{
        FXMLLoader loader = new FXMLLoader ();
        loader.setLocation(getClass().getResource("/GUI/Music.fxml"));
        loader.load();
        Parent parent = loader.getRoot();
        Stage stage = new Stage();
        stage.setScene(new Scene(parent));
        stage.initStyle(StageStyle.UTILITY);
        stage.show(); 
        if(titre.getText().length()==0){
       System.out.println("ok");
        }
    }
    void change(TextField text) {
		if(millis == 1000) {
			secs++;
			millis = 0;
		}
		if(secs == 60) {
			mins++;
			secs = 0;
		}
		text.setText((((mins/10) == 0) ? "0" : "") + mins + ":"
		 + (((secs/10) == 0) ? "0" : "") + secs + ":" 
			+ (((millis/10) == 0) ? "00" : (((millis/100) == 0) ? "0" : "")) + millis++);
    }
}   



           
           
  
    


