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
public class nutritionist extends User{
    private String diet;
    private Double salary;
    private int note;
  private int id_noutri;
    public nutritionist() {
    }

    public int getId_noutri() {
        return id_noutri;
    }

    public void setId_noutri(int id_noutri) {
        this.id_noutri = id_noutri;
    }

    public nutritionist(String diet, Double salary, int note) {
        this.diet = diet;
        this.salary = salary;
        this.note = note;
    }

    public nutritionist(String diet, Double salary, int note, String email, String roles, String password, int tel, String nom, String prenom) {
        super(email, roles, password, tel, nom, prenom);
        this.diet = diet;
        this.salary = salary;
        this.note = note;
    }

    public nutritionist(String diet, Double salary, int note, int id, String email, String roles, String password, int tel, String nom, String prenom) {
        super(id, email, roles, password, tel, nom, prenom);
        this.diet = diet;
        this.salary = salary;
        this.note = note;
    }

    public String getDiet() {
        return diet;
    }

    public void setDiet(String diet) {
        this.diet = diet;
    }

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }

    public int getNote() {
        return note;
    }

    public void setNote(int note) {
        this.note = note;
    }
    
    
}
