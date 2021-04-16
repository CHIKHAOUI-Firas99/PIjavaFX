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
public class info_user_nutrition {
    
      private int id;
      private int user_id ;
      private int programmenutrition_id  ;
      private int abonnement_id  ;
      private int nutritionist_id  ;
      private String ojectif ;
      private String blessure ;
      private String mangezpas ;
      private String supplementali;
      private String probleme ;
      private String sexe ;
      private int age  ;
      private int taille  ;
      private double poids  ;

    public info_user_nutrition() {
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 71 * hash + this.id;
        hash = 71 * hash + this.user_id;
        hash = 71 * hash + this.programmenutrition_id;
        hash = 71 * hash + this.abonnement_id;
        hash = 71 * hash + this.nutritionist_id;
        hash = 71 * hash + Objects.hashCode(this.ojectif);
        hash = 71 * hash + Objects.hashCode(this.blessure);
        hash = 71 * hash + Objects.hashCode(this.mangezpas);
        hash = 71 * hash + Objects.hashCode(this.supplementali);
        hash = 71 * hash + Objects.hashCode(this.probleme);
        hash = 71 * hash + Objects.hashCode(this.sexe);
        hash = 71 * hash + this.age;
        hash = 71 * hash + this.taille;
        hash = 71 * hash + (int) (Double.doubleToLongBits(this.poids) ^ (Double.doubleToLongBits(this.poids) >>> 32));
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
        final info_user_nutrition other = (info_user_nutrition) obj;
        if (this.id != other.id) {
            return false;
        }
        if (this.user_id != other.user_id) {
            return false;
        }
        if (this.programmenutrition_id != other.programmenutrition_id) {
            return false;
        }
        if (this.abonnement_id != other.abonnement_id) {
            return false;
        }
        if (this.nutritionist_id != other.nutritionist_id) {
            return false;
        }
        if (this.age != other.age) {
            return false;
        }
        if (this.taille != other.taille) {
            return false;
        }
        if (Double.doubleToLongBits(this.poids) != Double.doubleToLongBits(other.poids)) {
            return false;
        }
        if (!Objects.equals(this.ojectif, other.ojectif)) {
            return false;
        }
        if (!Objects.equals(this.blessure, other.blessure)) {
            return false;
        }
        if (!Objects.equals(this.mangezpas, other.mangezpas)) {
            return false;
        }
        if (!Objects.equals(this.supplementali, other.supplementali)) {
            return false;
        }
        if (!Objects.equals(this.probleme, other.probleme)) {
            return false;
        }
        if (!Objects.equals(this.sexe, other.sexe)) {
            return false;
        }
        return true;
    }

    public info_user_nutrition(int user_id, int programmenutrition_id, int abonnement_id, int nutritionist_id, String ojectif, String blessure, String mangezpas, String supplementali, String probleme, String sexe, int age, int taille, double poids) {
        this.user_id = user_id;
        this.programmenutrition_id = programmenutrition_id;
        this.abonnement_id = abonnement_id;
        this.nutritionist_id = nutritionist_id;
        this.ojectif = ojectif;
        this.blessure = blessure;
        this.mangezpas = mangezpas;
        this.supplementali = supplementali;
        this.probleme = probleme;
        this.sexe = sexe;
        this.age = age;
        this.taille = taille;
        this.poids = poids;
    }

    public info_user_nutrition(int id, int user_id, int programmenutrition_id, int abonnement_id, int nutritionist_id, String ojectif, String blessure, String mangezpas, String supplementali, String probleme, String sexe, int age, int taille, double poids) {
        this.id = id;
        this.user_id = user_id;
        this.programmenutrition_id = programmenutrition_id;
        this.abonnement_id = abonnement_id;
        this.nutritionist_id = nutritionist_id;
        this.ojectif = ojectif;
        this.blessure = blessure;
        this.mangezpas = mangezpas;
        this.supplementali = supplementali;
        this.probleme = probleme;
        this.sexe = sexe;
        this.age = age;
        this.taille = taille;
        this.poids = poids;
    }

    @Override
    public String toString() {
        return "info_user_nutrition{" + "id=" + id + ", user_id=" + user_id + ", programmenutrition_id=" + programmenutrition_id + ", abonnement_id=" + abonnement_id + ", nutritionist_id=" + nutritionist_id + ", ojectif=" + ojectif + ", blessure=" + blessure + ", mangezpas=" + mangezpas + ", supplementali=" + supplementali + ", probleme=" + probleme + ", sexe=" + sexe + ", age=" + age + ", taille=" + taille + ", poids=" + poids + '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getProgrammenutrition_id() {
        return programmenutrition_id;
    }

    public void setProgrammenutrition_id(int programmenutrition_id) {
        this.programmenutrition_id = programmenutrition_id;
    }

    public int getAbonnement_id() {
        return abonnement_id;
    }

    public void setAbonnement_id(int abonnement_id) {
        this.abonnement_id = abonnement_id;
    }

    public int getNutritionist_id() {
        return nutritionist_id;
    }

    public void setNutritionist_id(int nutritionist_id) {
        this.nutritionist_id = nutritionist_id;
    }

    public String getOjectif() {
        return ojectif;
    }

    public void setOjectif(String ojectif) {
        this.ojectif = ojectif;
    }

    public String getBlessure() {
        return blessure;
    }

    public void setBlessure(String blessure) {
        this.blessure = blessure;
    }

    public String getMangezpas() {
        return mangezpas;
    }

    public void setMangezpas(String mangezpas) {
        this.mangezpas = mangezpas;
    }

    public String getSupplementali() {
        return supplementali;
    }

    public void setSupplementali(String supplementali) {
        this.supplementali = supplementali;
    }

    public String getProbleme() {
        return probleme;
    }

    public void setProbleme(String probleme) {
        this.probleme = probleme;
    }

    public String getSexe() {
        return sexe;
    }

    public void setSexe(String sexe) {
        this.sexe = sexe;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getTaille() {
        return taille;
    }

    public void setTaille(int taille) {
        this.taille = taille;
    }

    public double getPoids() {
        return poids;
    }

    public void setPoids(double poids) {
        this.poids = poids;
    }
      
}
