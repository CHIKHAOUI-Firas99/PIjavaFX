/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coachini;

import entities.Coach;
import entities.Entrainement;
import entities.User;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;
import services.AbonnementService;
import services.CoachService;
import services.EntrainementService;
import services.LoginService;
import services.UserService;
import services.enc;


/**
 *
 * @author LENOVO
 */

public class Coachini {

    public static void main(String[] args) throws SQLException {
        
        
        UserService us = new UserService();
                   
        User u = new User();

        u.setEmail("user@gmail.com");
        u.setRoles("[]");
        u.setPassword("azerty123");
        u.setActivation_token("Activation_token");        
        u.setNom("user");
        u.setPrenom("user");
        u.setTel("28606573");
        u.setDatenaissance(java.sql.Date.valueOf("1999-09-04") );
        String a = enc.encryptPassword(u.getPassword());
        //u.setPassword(a);
       //us.ajouterUser(u);
       
       
       
          
        //login        
    Scanner myObj = new Scanner(System.in);  // Create a Scanner object
    System.out.println("Enter login");
    String userName = myObj.nextLine();  // Read user input
    System.out.println("Username is: " + userName);          
    Scanner myObj2= new Scanner(System.in);  // Create a Scanner object
    System.out.println("Enter password");
    String password = myObj2.nextLine();  // Read user input
    System.out.println("password is: " + password); 
    UserService srvUser = new UserService();      
    LoginService ls = new LoginService();
    ls.check(userName, password);
    
    }
    
     /*       
        if(srvUser.getCurrentUser().getRoles().contains("[\"ROLE_ADMIN\"]")){
        
        UserService uu = new UserService();
        System.out.println("voila la liste de tous les utilisateurs");
        System.out.println(uu.readAllusers());
        System.out.println(" vous etes un admin de cette application ");
        Scanner dd= new Scanner(System.in);  // Create a Scanner object
        System.out.println("voulez vous proumouvoir un utilisateur ? ");
        String ww = dd.nextLine(); 
        if(ww.equals("yes")){  
             Scanner qq= new Scanner(System.in);  // Create a Scanner object
        System.out.println("quel est le role a ajouter ? ");
        String qs = qq.nextLine();  
        if(qs.equals("coach")){
            Scanner ddd= new Scanner(System.in);  // Create a Scanner object
        System.out.println("quel est l'id de user que vous voulez proumouvoir en tant que coach ? ");
        String cc = ddd.nextLine();        
        int i=Integer.parseInt(cc);      
         User aa = new User();
         aa.setRoles("[\"ROLE_COACH\"]");
         us.modifierUser(aa,i);
          CoachService mds = new CoachService();
            Coach mm = new Coach ();
        Scanner ap= new Scanner(System.in);  // Create a Scanner object
        System.out.println("quel est la spécialité de ce coach ? ");
        String wp = ap.nextLine(); 
        
        Scanner qg= new Scanner(System.in);  // Create a Scanner object
        System.out.println("quel est le salaire pour ce coach ? ");
        String rr = qg.nextLine();  
         int k=Integer.parseInt(rr);      
            mm.setUser_id(i);
            mm.setSpeciality(wp);
            mm.setSalary(k);
            mds.ajouterCoach(mm);         
            
        }   
       
        }
        Scanner qg= new Scanner(System.in);  // Create a Scanner object
        System.out.println("voulez vous supprimer un coach ? ");
        String rr = qg.nextLine();  
        if(rr.equals("yes")){
            Scanner ad= new Scanner(System.in);  // Create a Scanner object
        System.out.println("quel est l'id de coach que vous voulez supprimer ? ");
        String eg = ad.nextLine(); 
                        int k=Integer.parseInt(eg);  
                       CoachService cch = new CoachService();     
                       UserService oo = new UserService();
                       User o = new User();
                       o.setRoles("[]");
                       int m = cch.getuseridbycoachid(k);
                       
                       oo.modifierUser(o, m);  
                       cch.supprimerCoach(k); 
        }
        
        
    }
    
    else if(srvUser.getCurrentUser().getRoles().contains("[\"ROLE_COACH\"]")){
        
        //ajout de données personelles
          Scanner qq= new Scanner(System.in);  // Create a Scanner object
        System.out.println("voulez vous modifer vos données personelles ? ");
        String qs = qq.nextLine();  
        if(qs.equals("yes")){
            CoachService cs = new CoachService();
            Coach c = new Coach();
            c.setAge(30);
            c.setPoid(1);
            c.setHauteur(12);
            c.setFacebook("test facebook");
            c.setInsta("test instaa");
            c.setGmail("@esprit");
            c.setBio("ouiii");
            c.setImage("immg"); 
            int x =  us.getCurrentUser().getId();   
            System.out.println(x);
            
            cs.modifiercoach(c, x);    
            
        }
        
        //ajout d'entrainements
        Scanner df= new Scanner(System.in);  // Create a Scanner object
        
        System.out.println("voulez vous ajouter de nouveaux entrainements ?");
        String dd = df.nextLine();  
        if(dd.equals("yes")){
            System.out.println("voila la liste des entrainements que vous avez déja ajouté");
            EntrainementService es = new EntrainementService();
            CoachService CoS = new CoachService();
            System.out.println(es.getEntrainementByCoachId(CoS.getcoachidbyuserid(us.getCurrentUser().getId())));
            System.out.println("Maintenant vous pouvez ajouter d'autres entrainements");
            Entrainement e= new Entrainement();
            e.setCoach_id(CoS.getcoachidbyuserid(us.getCurrentUser().getId()));
                
           //création de l'entrainement   
            Scanner xx= new Scanner(System.in);  // Create a Scanner object
            System.out.println("quel est le titre de l'entrainement ?");
            String bt = xx.nextLine(); 
            Scanner yy= new Scanner(System.in);  // Create a Scanner object
            System.out.println("ce entrainement est valable pour combien de jour ?");
            String by = yy.nextLine();
            Scanner xa= new Scanner(System.in);  // Create a Scanner object
            System.out.println("quel est l'heure l'entrainement ?");
            String xc = xa.nextLine();
            
            Scanner xq= new Scanner(System.in);  // Create a Scanner object
            System.out.println("quel est le type de l'entrainement ?");
            String xr = xq.nextLine();
            Scanner pm= new Scanner(System.in);  // Create a Scanner object
            System.out.println("passez le lien meet pour vous rejoindre ?");
            String az = pm.nextLine();            
            e.setTitre(bt);
            e.setJour(by);
            int i=Integer.parseInt(xc);  
            e.setHeure(i);
            e.setType(xr);
            e.setMeet(az);    
            es.ajouterEntrainement(e);
           
        }
        //modification des entrainements
        Scanner xx= new Scanner(System.in);  // Create a Scanner object
        System.out.println("voulez vous modifier un entrainement?");
        String bt = xx.nextLine();  
        if(bt.equals("yes")){
        EntrainementService es = new EntrainementService();
            Entrainement e= new Entrainement();
             Scanner qf= new Scanner(System.in);  // Create a Scanner object
            System.out.println("quel est l'id de l'entrainement que vous voulez mettre a jour ?");
            String br = qf.nextLine();            
            int j=Integer.parseInt(br);
             
            Scanner gh= new Scanner(System.in);  // Create a Scanner object
            System.out.println("quel est le nouveau titre de l'entrainement ?");
            String tt = gh.nextLine(); 
            Scanner yy= new Scanner(System.in);  // Create a Scanner object
            System.out.println("ce entrainement est valable pour combien de jour ?");
            String by = yy.nextLine();
            Scanner xa= new Scanner(System.in);  // Create a Scanner object
            System.out.println("a quel heure l'entrainement ?");
            String xc = xa.nextLine();         
            Scanner xq= new Scanner(System.in);  // Create a Scanner object
            System.out.println("quel est le nouveau type de l'entrainement ?");
            String xr = xq.nextLine();
            Scanner pm= new Scanner(System.in);  // Create a Scanner object
            System.out.println("passez le nouveau lien meet pour vous rejoindre ?");
            String az = pm.nextLine();            
            e.setTitre(tt);
            e.setJour(by);
            int i=Integer.parseInt(xc);  
            e.setHeure(i);
            e.setType(xr);
            e.setMeet(az);              
            es.updateEntrainement(e, j);
      

     }
         
            //suppression d'entrainements 
            EntrainementService es = new EntrainementService();
             Scanner fa= new Scanner(System.in);  // Create a Scanner object
            System.out.println("voulez vous supprimer un entrainement ?");
            String ge = fa.nextLine(); 
            if(ge.equals("yes")){
            Scanner qg= new Scanner(System.in);  // Create a Scanner object
            System.out.println("quel est l'id de l'entrainement que vous voulez supprimer ?");
            String vd = qg.nextLine();
            int m =Integer.parseInt(vd);  
            es.supprimerEntrainement(m); 
                System.out.println("l'entrainement est bien supprimé");
            }
         
      }
                 
             
     AbonnementService as = new AbonnementService();
     List <Integer> mm = as.readAllAbonnements();  
    
    for(int j=0; j<mm.size(); j+=2)                  
     System.out.println("le coach qui a l'id "+mm.get(j)+" a "+mm.get(j+1)+" abonnements "); 
 
    }


    
    
    
    /*
    requete sql pour récupérer les noms des abonnées:
    SELECT nom FROM abonnement INNER JOIN users ON abonnement.user_id = users.id WHERE coach_id=27
    */
   
    }
    



 

