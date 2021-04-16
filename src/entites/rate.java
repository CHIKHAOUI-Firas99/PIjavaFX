/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entites;

import java.util.Objects;

/**
 *
 * @author Aymen
 */
public class rate {
       private int id_rate;
            private int id_user;
                private int id_nutritionist ;
                  private int rate;
    private String nom;
    private String prenom;

    public rate() {
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 37 * hash + this.id_rate;
        hash = 37 * hash + this.id_user;
        hash = 37 * hash + this.id_nutritionist;
        hash = 37 * hash + this.rate;
        hash = 37 * hash + Objects.hashCode(this.nom);
        hash = 37 * hash + Objects.hashCode(this.prenom);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final rate other = (rate) obj;
        if (this.id_rate != other.id_rate) {
            return false;
        }
        if (this.id_user != other.id_user) {
            return false;
        }
        if (this.id_nutritionist != other.id_nutritionist) {
            return false;
        }
        if (this.rate != other.rate) {
            return false;
        }
        if (!Objects.equals(this.nom, other.nom)) {
            return false;
        }
        if (!Objects.equals(this.prenom, other.prenom)) {
            return false;
        }
        return true;
    }

    public rate(int id_user, int id_nutritionist, int rate, String nom, String prenom) {
        this.id_user = id_user;
        this.id_nutritionist = id_nutritionist;
        this.rate = rate;
        this.nom = nom;
        this.prenom = prenom;
    }

    public rate(int id_rate, int id_user, int id_nutritionist, int rate, String nom, String prenom) {
        this.id_rate = id_rate;
        this.id_user = id_user;
        this.id_nutritionist = id_nutritionist;
        this.rate = rate;
        this.nom = nom;
        this.prenom = prenom;
    }

    public int getId_rate() {
        return id_rate;
    }

    public void setId_rate(int id_rate) {
        this.id_rate = id_rate;
    }

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public int getId_nutritionist() {
        return id_nutritionist;
    }

    public void setId_nutritionist(int id_nutritionist) {
        this.id_nutritionist = id_nutritionist;
    }

    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }
    
}
