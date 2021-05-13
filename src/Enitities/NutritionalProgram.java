/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Enitities;


/**
 *
 * @author ASUS
 */
public class NutritionalProgram {
    private int id;
    private String nom;
    private String type;
    private String description;
    private String image;
    private String breakfast;
    private String lunch;
    private String dinner;

    public NutritionalProgram() {
    
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getBreakfast() {
        return breakfast;
    }

    public void setBreakfast(String breakfast) {
        this.breakfast = breakfast;
    }

    public String getLunch() {
        return lunch;
    }

    public void setLunch(String lunch) {
        this.lunch = lunch;
    }

    public String getDinner() {
        return dinner;
    }

    public void setDinner(String dinner) {
        this.dinner = dinner;
    }

    public NutritionalProgram(int id, String nom, String type, String description, String image, String breakfast, String lunch, String dinner) {
        this.id = id;
        this.nom = nom;
        this.type = type;
        this.description = description;
        this.image = image;
        this.breakfast = breakfast;
        this.lunch = lunch;
        this.dinner = dinner;
    }

    public NutritionalProgram(String nom, String type, String description, String image, String breakfast, String lunch, String dinner) {
        this.nom = nom;
        this.type = type;
        this.description = description;
        this.image = image;
        this.breakfast = breakfast;
        this.lunch = lunch;
        this.dinner = dinner;
    }
    
  
    
    
}
