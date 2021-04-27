/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import Alert.AlertDialog;
import entities.User;

/**
 *
 * @author LENOVO
 */
public class LoginService {
     UserService srvUser = new UserService();
        
     public void check(String username , String password){ 
        User p = srvUser.getUserByUsername(username);
        
        if(p == null){
        AlertDialog.showNotification("Error !","vous-devez verifier vos information  ",AlertDialog.image_cross);
        }        
        else if(enc.checkPassword(password,p.getPassword()) == false){
              AlertDialog.showNotification("Error !","vous-devez verifier votre mot de passe ",AlertDialog.image_cross);  
            }
        else if(enc.checkPassword(password,p.getPassword())){ 
               UserService.setCurrentUser(p);   
                if(p.getRoles().toLowerCase().contains("admin"))
                {
                 System.out.println("bonjour monsieur l'admin "+username);              
                }
                else if(p.getRoles().toLowerCase().contains("coach"))
                {
                    System.out.println("bonjour monsieur le coach "+username);
                }
                else if(p.getRoles().toLowerCase().contains("[]")){
                    System.out.println("ok");
                }
                
            }
        }
     }
                    
    
    

