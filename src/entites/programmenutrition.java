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
public class programmenutrition {
        private int id;
            private int info_user_nutrition_id;
                private int user_id;
                  private int nutritionist_id;
    private String repas1;
     private String repas2;
      private String repas3;
       private String repas4;
        private String repas5;
           private int duree;
    private String jourrepot;

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 31 * hash + this.id;
        hash = 31 * hash + this.info_user_nutrition_id;
        hash = 31 * hash + this.user_id;
        hash = 31 * hash + this.nutritionist_id;
        hash = 31 * hash + Objects.hashCode(this.repas1);
        hash = 31 * hash + Objects.hashCode(this.repas2);
        hash = 31 * hash + Objects.hashCode(this.repas3);
        hash = 31 * hash + Objects.hashCode(this.repas4);
        hash = 31 * hash + Objects.hashCode(this.repas5);
        hash = 31 * hash + this.duree;
        hash = 31 * hash + Objects.hashCode(this.jourrepot);
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
        final programmenutrition other = (programmenutrition) obj;
        if (this.id != other.id) {
            return false;
        }
        if (this.info_user_nutrition_id != other.info_user_nutrition_id) {
            return false;
        }
        if (this.user_id != other.user_id) {
            return false;
        }
        if (this.nutritionist_id != other.nutritionist_id) {
            return false;
        }
        if (this.duree != other.duree) {
            return false;
        }
        if (!Objects.equals(this.repas1, other.repas1)) {
            return false;
        }
        if (!Objects.equals(this.repas2, other.repas2)) {
            return false;
        }
        if (!Objects.equals(this.repas3, other.repas3)) {
            return false;
        }
        if (!Objects.equals(this.repas4, other.repas4)) {
            return false;
        }
        if (!Objects.equals(this.repas5, other.repas5)) {
            return false;
        }
        if (!Objects.equals(this.jourrepot, other.jourrepot)) {
            return false;
        }
        return true;
    }

    public programmenutrition(int info_user_nutrition_id, int user_id, int nutritionist_id, String repas1, String repas2, String repas3, String repas4, String repas5, int duree, String jourrepot) {
        this.info_user_nutrition_id = info_user_nutrition_id;
        this.user_id = user_id;
        this.nutritionist_id = nutritionist_id;
        this.repas1 = repas1;
        this.repas2 = repas2;
        this.repas3 = repas3;
        this.repas4 = repas4;
        this.repas5 = repas5;
        this.duree = duree;
        this.jourrepot = jourrepot;
    }

    public programmenutrition(int id, int info_user_nutrition_id, int user_id, int nutritionist_id, String repas1, String repas2, String repas3, String repas4, String repas5, int duree, String jourrepot) {
        this.id = id;
        this.info_user_nutrition_id = info_user_nutrition_id;
        this.user_id = user_id;
        this.nutritionist_id = nutritionist_id;
        this.repas1 = repas1;
        this.repas2 = repas2;
        this.repas3 = repas3;
        this.repas4 = repas4;
        this.repas5 = repas5;
        this.duree = duree;
        this.jourrepot = jourrepot;
    }

    @Override
    public String toString() {
        return "programmenutrition{" + "id=" + id + ", info_user_nutrition_id=" + info_user_nutrition_id + ", user_id=" + user_id + ", nutritionist_id=" + nutritionist_id + ", repas1=" + repas1 + ", repas2=" + repas2 + ", repas3=" + repas3 + ", repas4=" + repas4 + ", repas5=" + repas5 + ", duree=" + duree + ", jourrepot=" + jourrepot + '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getInfo_user_nutrition_id() {
        return info_user_nutrition_id;
    }

    public void setInfo_user_nutrition_id(int info_user_nutrition_id) {
        this.info_user_nutrition_id = info_user_nutrition_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getNutritionist_id() {
        return nutritionist_id;
    }

    public void setNutritionist_id(int nutritionist_id) {
        this.nutritionist_id = nutritionist_id;
    }

    public String getRepas1() {
        return repas1;
    }

    public void setRepas1(String repas1) {
        this.repas1 = repas1;
    }

    public String getRepas2() {
        return repas2;
    }

    public void setRepas2(String repas2) {
        this.repas2 = repas2;
    }

    public String getRepas3() {
        return repas3;
    }

    public void setRepas3(String repas3) {
        this.repas3 = repas3;
    }

    public String getRepas4() {
        return repas4;
    }

    public void setRepas4(String repas4) {
        this.repas4 = repas4;
    }

    public String getRepas5() {
        return repas5;
    }

    public void setRepas5(String repas5) {
        this.repas5 = repas5;
    }

    public int getDuree() {
        return duree;
    }

    public void setDuree(int duree) {
        this.duree = duree;
    }

    public String getJourrepot() {
        return jourrepot;
    }

    public void setJourrepot(String jourrepot) {
        this.jourrepot = jourrepot;
    }
    

}
