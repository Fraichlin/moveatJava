/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import Enitities.Coach;
import Enitities.Member;
import Services.IServicesUser;
import Utils.MyConnexion;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

/**
 *
 * @author NGONGANG Loic F
 */
public class serviceUser implements IServicesUser{
  
     Connection cnx;

    public serviceUser() {
        cnx = MyConnexion.getInstance().getConnection();
    }

    @Override
    public void addCoach(Coach c) {
          try {
            Statement stm = cnx.createStatement();
            String query = "INSERT INTO user "
                    + "(email,username,password,nom,prenom,sexe,telephone,statut,photo,specialite"
                    + ",type,adresse,justificatif) "
                    + "VALUES ('"+c.getEmail()+"','"+c.getUsername()+"','"+c.getPassword()+"',"
                    + "'"+c.getNom()+"','"+c.getPrenom()+"','"+c.getSexe()+"','"+c.getTel()+"','"+c.getStatut()+"',"
                    + "'"+c.getPhoto()+"','"+c.getSpecialite()+"','"+c.getType()+"','"+c.getAdresse()+"','"+c.getJustificatif()+"')";
            stm.executeUpdate(query);
            System.out.println("insertion effectue");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            System.out.println("erreur d'insertion");
        }
      
    }

    @Override
    public List<Coach> listCoach() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public List<Coach> listDemandCoach() {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void addMember(Member m) {
        try {
            Statement stm = cnx.createStatement();
            String query = "INSERT INTO user "
                    + "(email,username,password,nom,prenom,sexe,statut,photo,taille,poids,type) "
                    + "VALUES ('"+m.getEmail()+"','"+m.getUsername()+"','"+m.getPassword()+"',"
                    + "'"+m.getNom()+"','"+m.getPrenom()+"','"+m.getSexe()+"','"+m.getStatut()+"',"
                    + "'"+m.getPhoto()+"','"+m.getTaille()+"','"+m.getPoids()+"','"+m.getType()+"')";
            stm.executeUpdate(query);
            System.out.println("insertion effectue");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            System.out.println("erreur d'insertion");
        }
    }

    @Override
    public List<Member> listMember() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void deleteUser(int idUser) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void updateMember(int idMember, Member m) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void updateCoach(int idCoach, Coach c) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void blockUser(int idUser) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void unblockUser(int idUser) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void validCoach(int idCoach) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
}
