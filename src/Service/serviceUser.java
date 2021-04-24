/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import Enitities.Coach;
import Enitities.Member;
import Enitities.User;
import Services.IServicesUser;
import Utils.MyConnexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
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
         List<Coach> coach = new ArrayList<>();
        try {
            Statement stm = cnx.createStatement();
            String query = "SELECT * FROM user WHERE type ='coach' and statut = 'actived' or statut = 'blocked' or statut = 'unblocked'";
            ResultSet rst = stm.executeQuery(query);
  
            while(rst.next()){
                Coach c = new Coach();
                c.setIdUser(rst.getInt("idUser"));
                c.setNom(rst.getString("nom"));
                c.setPrenom(rst.getString("prenom"));
                c.setUsername(rst.getString("username"));
                c.setEmail(rst.getString("email"));
                c.setSexe(rst.getString("sexe"));
                c.setTel(rst.getString("telephone"));
                c.setAdresse(rst.getString("adresse"));
                c.setStatut(rst.getString("statut"));
                c.setSpecialite(rst.getString("specialite"));
                c.setPhoto(rst.getString("photo"));
                c.setDateInscription(rst.getDate("dateInscription"));
                c.setDateModification(rst.getDate("dateModification"));
                c.setDateBlocage(rst.getDate("dateBlocage"));
                c.setDateDeblocage(rst.getDate("dateDeblocage"));
                c.setJustificatif(rst.getString("justificatif"));
                coach.add(c);
            }
        } catch (SQLException ex) {
            System.out.println("erreur de selection");
        }
        
        return coach;
    }
    
    @Override
    public List<Coach> listDemandCoach() {
        List<Coach> coach = new ArrayList<>();
        try {
            Statement stm = cnx.createStatement();
            String query = "SELECT * FROM user WHERE type ='coach' and statut = 'nonactived'";
            ResultSet rst = stm.executeQuery(query);
  
            while(rst.next()){
                Coach c = new Coach();
                c.setIdUser(rst.getInt("idUser"));
                c.setNom(rst.getString("nom"));
                c.setPrenom(rst.getString("prenom"));
                c.setEmail(rst.getString("email"));
                c.setSpecialite(rst.getString("specialite"));
                c.setDateInscription(rst.getDate("dateInscription"));
                c.setJustificatif(rst.getString("justificatif"));
                coach.add(c);
            }
        } catch (SQLException ex) {
            System.out.println("erreur de selection");
        }
        
        return coach;
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
        List<Member> member = new ArrayList<>();
        try {
            Statement stm = cnx.createStatement();
            String query = "SELECT * FROM user WHERE type ='member'";
            ResultSet rst = stm.executeQuery(query);
  
            while(rst.next()){
                Member m = new Member();
                m.setIdUser(rst.getInt("idUser"));
                m.setNom(rst.getString("nom"));
                m.setPrenom(rst.getString("prenom"));
                m.setUsername(rst.getString("username"));
                m.setEmail(rst.getString("email"));
                m.setSexe(rst.getString("sexe"));
                m.setTaille(rst.getString("taille"));
                m.setPoids(rst.getString("poids"));
                m.setStatut(rst.getString("statut"));
                m.setPhoto(rst.getString("photo"));
                m.setDateInscription(rst.getDate("dateInscription"));
                m.setDateModification(rst.getDate("dateModification"));
                m.setDateBlocage(rst.getDate("dateBlocage"));
                m.setDateDeblocage(rst.getDate("dateDeblocage"));
                member.add(m);
            }
        } catch (SQLException ex) {
            System.out.println("erreur de selection");
        }
        
        return member;
    }

    @Override
    public void deleteUser(int idUser) {
        try {
            String query = "DELETE FROM `user` WHERE idUser =" + idUser ;
                PreparedStatement ps = cnx.prepareStatement(query);
                ps.execute();
                System.out.println("suppression faite");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void updateMember(Member m) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void updateCoach(Coach c) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void blockUser(int idUser) {
        try {
            String query = " UPDATE `user` SET `statut`='blocked'"
            + " WHERE  idUser =" + idUser ;
                PreparedStatement ps = cnx.prepareStatement(query);
                ps.executeUpdate();
                System.out.println("blocage fait");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void unblockUser(int idUser) {
         try {
            String query = " UPDATE `user` SET `statut`='unblocked'"
            + " WHERE  idUser =" + idUser ;
                PreparedStatement ps = cnx.prepareStatement(query);
                ps.executeUpdate();
                System.out.println("deblocage fait");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void validCoach(int idCoach) {
         try {
            String query = " UPDATE `user` SET `statut`='actived'"
            + " WHERE  idUser =" + idCoach ;
                PreparedStatement ps = cnx.prepareStatement(query);
                ps.executeUpdate();
                System.out.println("Validation faite");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public Coach selectCoach(Coach c) {
        Coach coach = new Coach();
         try {
            Statement stm = cnx.createStatement();
            String query = "SELECT * FROM user WHERE idUser ="+c.getIdUser();
            ResultSet rst = stm.executeQuery(query);
  
            while(rst.next()){
                coach.setIdUser(rst.getInt("idUser"));
                coach.setNom(rst.getString("nom"));
                coach.setPrenom(rst.getString("prenom"));
                coach.setUsername(rst.getString("username"));
                coach.setEmail(rst.getString("email"));
                coach.setSexe(rst.getString("sexe"));
                coach.setTel(rst.getString("telephone"));
                coach.setAdresse(rst.getString("adresse"));
                coach.setStatut(rst.getString("statut"));
                coach.setSpecialite(rst.getString("specialite"));
                coach.setPhoto(rst.getString("photo"));
                coach.setDateInscription(rst.getDate("dateInscription"));
                coach.setDateModification(rst.getDate("dateModification"));
                coach.setDateBlocage(rst.getDate("dateBlocage"));
                coach.setDateDeblocage(rst.getDate("dateDeblocage"));
                coach.setJustificatif(rst.getString("justificatif"));
            }
        } catch (SQLException ex) {
            System.out.println("erreur de selection");
        }
        
        return coach;
    }

    @Override
    public User searchUser(String email, String password) {
         User user = new Coach();
//          ArrayList parameters = new ArrayList( Arrays.asList( email , password ) );
         try {
            Statement stm = cnx.createStatement();
            String query = "SELECT * FROM user WHERE email ='"+email+"' and password ='"+password+"'";
            ResultSet rst = stm.executeQuery(query);
            
            while(rst.next()){
                user.setIdUser(rst.getInt("idUser"));
                user.setEmail(rst.getString("email"));
                user.setUsername(rst.getString("username"));
                user.setPassword(rst.getString("password"));
                user.setType(rst.getString("type"));
            }
            
        } catch (SQLException ex) {
            System.out.println("erreur de selection");
        }
         System.out.println(user);
        return user;
    }
    public boolean isUserExist( String userLogin, String userPassword ){
        return this.searchUser( userLogin , userPassword).getIdUser() != 0 ; 
    }

    
}
