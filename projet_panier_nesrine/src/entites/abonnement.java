/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entites;

/**
 *
 * @author Aymen
 */
public class abonnement {
 int id;
 int prix;
 String type;
 String labelle;
 int quantite;
 String image;
 int id_nutisonist;

    public abonnement(int id, int prix, String type, String labelle, int quantite, String image, int id_nutisonist) {
        this.id = id;
        this.prix = prix;
        this.type = type;
        this.labelle = labelle;
        this.quantite = quantite;
        this.image = image;
        this.id_nutisonist = id_nutisonist;
    }

    public int getId_nutisonist() {
        return id_nutisonist;
    }

    public void setId_nutisonist(int id_nutisonist) {
        this.id_nutisonist = id_nutisonist;
    }

    public abonnement() {
    }

    public abonnement(int prix, String type, String labelle, int quantite, String image) {
        this.prix = prix;
        this.type = type;
        this.labelle = labelle;
        this.quantite = quantite;
        this.image = image;
    }

    public abonnement(int id, int prix, String type, String labelle, int quantite, String image) {
        this.id = id;
        this.prix = prix;
        this.type = type;
        this.labelle = labelle;
        this.quantite = quantite;
        this.image = image;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPrix() {
        return prix;
    }

    public void setPrix(int prix) {
        this.prix = prix;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getLabelle() {
        return labelle;
    }

    public void setLabelle(String labelle) {
        this.labelle = labelle;
    }

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
 
}
