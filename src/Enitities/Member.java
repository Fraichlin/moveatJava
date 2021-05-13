/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Enitities;

import java.sql.Date;


/**
 *
 * @author NGONGANG Loic F
 */
public class Member extends User{
    private String taille;
    private String poids;

    
    public Member(){
        
    }

    public Member(String taille, String poids, int idUser, String email, String username, String password, String nom, String prenom, String sexe, String tel, String statut, String photo, String[] type, Date dateInscription, Date dateBlocage, Date dateDeblocage, Date dateModification, int isVerified) {
        super(idUser, email, username, password, nom, prenom, sexe, tel, statut, photo, type, dateInscription, dateBlocage, dateDeblocage, dateModification, isVerified);
        this.taille = taille;
        this.poids = poids;
    }
    

    public String getTaille() {
        return taille;
    }

    public void setTaille(String taille) {
        this.taille = taille;
    }

    public String getPoids() {
        return poids;
    }

    public void setPoids(String poids) {
        this.poids = poids;
    }
    
    
}
