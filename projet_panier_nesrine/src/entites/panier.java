/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entites;

import java.sql.Date;

/**
 *
 * @author Aymen
 */
public class panier {
    int id;
    int id_produit;
    int id_user;
    int quantite;
   
    String labelle;
 Date Date_Debut;
    Date Date_Fin;

    public Date getDate_Debut() {
        return Date_Debut;
    }

    public panier(int id, int id_produit, int id_user, int quantite, String labelle, Date Date_Debut, Date Date_Fin) {
        this.id = id;
        this.id_produit = id_produit;
        this.id_user = id_user;
        this.quantite = quantite;
        this.labelle = labelle;
        this.Date_Debut = Date_Debut;
        this.Date_Fin = Date_Fin;
    }

    public void setDate_Debut(Date Date_Debut) {
        this.Date_Debut = Date_Debut;
    }

    public Date getDate_Fin() {
        return Date_Fin;
    }

    public void setDate_Fin(Date Date_Fin) {
        this.Date_Fin = Date_Fin;
    }
    
    public panier() {
    }

    public panier(int id_produit, int id_user, int quantite, String labelle) {
        this.id_produit = id_produit;
        this.id_user = id_user;
        this.quantite = quantite;
        this.labelle = labelle;
    }

    public panier(int id, int id_produit, int id_user, int quantite, String labelle) {
        this.id = id;
        this.id_produit = id_produit;
        this.id_user = id_user;
        this.quantite = quantite;
        this.labelle = labelle;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_produit() {
        return id_produit;
    }

    public void setId_produit(int id_produit) {
        this.id_produit = id_produit;
    }

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    public String getLabelle() {
        return labelle;
    }

    public void setLabelle(String labelle) {
        this.labelle = labelle;
    }
    
}
