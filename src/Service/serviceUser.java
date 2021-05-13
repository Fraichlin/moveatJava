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
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import org.mindrot.jbcrypt.BCrypt;

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
                    + ",roles,adresse,justificatif,is_verified) "
                    + "VALUES ('"+c.getEmail()+"','"+c.getUsername()+"','"+c.getPassword()+"',"
                    + "'"+c.getNom()+"','"+c.getPrenom()+"','"+c.getSexe()+"','"+c.getTel()+"','"+c.getStatut()+"',"
                    + "'"+c.getPhoto()+"','"+c.getSpecialite()+"','"+c.getType()[0]+"','"+c.getAdresse()+"','"+c.getJustificatif()+"','"+c.getIsVerified()+"')";
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
            String query = "SELECT * FROM user WHERE roles ='[\"ROLE_COACH\"]' and (statut = 'actived' or statut = 'blocked' or statut = 'unblocked')";
            ResultSet rst = stm.executeQuery(query);
  
            while(rst.next()){
                Coach c = new Coach();
                c.setIdUser(rst.getInt("id"));
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
                c.setDateInscription(rst.getDate("date_inscription"));
                c.setDateValidation(rst.getDate("date_validation"));
                c.setDateModification(rst.getDate("updated_at"));
                c.setDateBlocage(rst.getDate("date_blocage"));
                c.setDateDeblocage(rst.getDate("date_deblocage"));
                c.setJustificatif(rst.getString("justificatif"));
                coach.add(c);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            System.out.println("erreur de selection");
        }
        
        return coach;
    }
    
    @Override
    public List<Coach> listDemandCoach() {
        List<Coach> coach = new ArrayList<>();
        try {
            Statement stm = cnx.createStatement();
            String query = "SELECT * FROM user WHERE roles ='[\"ROLE_COACH\"]' and statut = 'nonactived'";
            ResultSet rst = stm.executeQuery(query);
  
            while(rst.next()){
                Coach c = new Coach();
                c.setIdUser(rst.getInt("id"));
                c.setNom(rst.getString("nom"));
                c.setPrenom(rst.getString("prenom"));
                c.setEmail(rst.getString("email"));
                c.setSpecialite(rst.getString("specialite"));
                c.setDateInscription(rst.getDate("date_inscription"));
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
                    + "(email,username,password,nom,prenom,sexe,statut,photo,taille,poids,roles,is_verified) "
                    + "VALUES ('"+m.getEmail()+"','"+m.getUsername()+"','"+m.getPassword()+"',"
                    + "'"+m.getNom()+"','"+m.getPrenom()+"','"+m.getSexe()+"','"+m.getStatut()+"',"
                    + "'"+m.getPhoto()+"','"+m.getTaille()+"','"+m.getPoids()+"','"+m.getType()[0]+"','"+m.getIsVerified()+"')";
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
            String query = "SELECT * FROM user WHERE roles ='[\"ROLE_MEMBER\"]'";
            ResultSet rst = stm.executeQuery(query);
  
            while(rst.next()){
                Member m = new Member();
                m.setIdUser(rst.getInt("id"));
                m.setNom(rst.getString("nom"));
                m.setPrenom(rst.getString("prenom"));
                m.setUsername(rst.getString("username"));
                m.setEmail(rst.getString("email"));
                m.setSexe(rst.getString("sexe"));
                m.setTaille(rst.getString("taille"));
                m.setPoids(rst.getString("poids"));
                m.setStatut(rst.getString("statut"));
                m.setPhoto(rst.getString("photo"));
                m.setDateInscription(rst.getDate("date_inscription"));
                m.setDateModification(rst.getDate("updated_at"));
                m.setDateBlocage(rst.getDate("date_blocage"));
                m.setDateDeblocage(rst.getDate("date_deblocage"));
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
            String query = "DELETE FROM `user` WHERE id =" + idUser ;
                PreparedStatement ps = cnx.prepareStatement(query);
                ps.execute();
                System.out.println("suppression faite");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    


    @Override
    public void blockUser(int idUser) {
        long now = System.currentTimeMillis();
        Date date = new Date(now);
        try {
            String query = " UPDATE `user` SET statut='blocked', updated_at='"+date+"'"
                    + ", date_deblocage='"+date+"'"
            + " WHERE  id =" + idUser ;
                PreparedStatement ps = cnx.prepareStatement(query);
                ps.executeUpdate();
                System.out.println("blocage fait");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void unblockUser(int idUser) {
        long now = System.currentTimeMillis();
        Date date = new Date(now);
         try {
            String query = " UPDATE `user` SET `statut`='unblocked', updated_at='"+date+"'"
                    + ", date_deblocage='"+date+"'"
            + " WHERE id =" + idUser ;
                PreparedStatement ps = cnx.prepareStatement(query);
                ps.executeUpdate();
                System.out.println("deblocage fait");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void validCoach(int idCoach) {
        long now = System.currentTimeMillis();
        Date date = new Date(now);
         try {
            String query = " UPDATE `user` SET `statut`='actived', updated_at='"+date+"'"
                    + ", date_validation='"+date+"'"
            + " WHERE id =" + idCoach ;
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
            String query = "SELECT * FROM user WHERE id ="+c.getIdUser();
            ResultSet rst = stm.executeQuery(query);
  
            while(rst.next()){
                coach.setIdUser(rst.getInt("id"));
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
                coach.setDateInscription(rst.getDate("date_inscription"));
                coach.setDateModification(rst.getDate("updated_at"));
                coach.setDateBlocage(rst.getDate("date_blocage"));
                coach.setDateDeblocage(rst.getDate("date_deblocage"));
                coach.setJustificatif(rst.getString("justificatif"));
            }
        } catch (SQLException ex) {
            System.out.println("erreur de selection");
        }
        
        return coach;
    }

    @Override
    public Coach searchCoach(String email, String password) {
         Coach user = new Coach();
         try {
            Statement stm = cnx.createStatement();
            String query = "SELECT * FROM user WHERE email ='"+email+"'";
            ResultSet rst = stm.executeQuery(query);
            
            while(rst.next()){
                user.setIdUser(rst.getInt("id"));
                user.setNom(rst.getString("nom"));
                user.setPrenom(rst.getString("prenom"));
                user.setPhoto(rst.getString("photo"));
                user.setEmail(rst.getString("email"));
                user.setAdresse(rst.getString("adresse"));
                user.setTel(rst.getString("telephone"));
                user.setStatut(rst.getString("statut"));
                user.setSpecialite(rst.getString("specialite"));
                user.setUsername(rst.getString("username"));
                user.setPassword(rst.getString("password"));
                
                String[] role = {rst.getString("roles")} ;
                user.setType(role);
            }
            
        } catch (SQLException ex) {
            System.out.println("erreur de selection");
        }
         System.out.println(user);
        return user;
    }
    @Override
    public Member searchMember(String email, String password) {
         Member user = new Member();
         if(getPassword(email)!=null && BCrypt.checkpw(password,getPassword(email).replace("2y","2a"))){ 
         try {
            Statement stm = cnx.createStatement();
            String query = "SELECT * FROM user WHERE email ='"+email+"'";
            ResultSet rst = stm.executeQuery(query);
            
            while(rst.next()){
                user.setIdUser(rst.getInt("id"));
                user.setNom(rst.getString("nom"));
                user.setPrenom(rst.getString("prenom"));
                user.setPhoto(rst.getString("photo"));
                user.setEmail(rst.getString("email"));
                user.setPoids(rst.getString("poids"));
                user.setTaille(rst.getString("taille"));
                user.setStatut(rst.getString("statut"));
                user.setUsername(rst.getString("username"));
                user.setPassword(rst.getString("password"));
               String[] role = {rst.getString("roles")} ;
                user.setType(role);
            }
            
        } catch (SQLException ex) {
            System.out.println("erreur de selection");
        }
         System.out.println(user);
         }
        return user;
    }
    @Override
    public User searchUser(String email, String password) {
         User user = new User();
         if(getPassword(email)!=null && BCrypt.checkpw(password,getPassword(email).replace("2y","2a"))){      
         try {
            Statement stm = cnx.createStatement();
            String query = "SELECT * FROM user WHERE email ='"+email+"'";
            ResultSet rst = stm.executeQuery(query);
            
            while(rst.next()){
                user.setIdUser(rst.getInt("id"));
                user.setNom(rst.getString("nom"));
                user.setPrenom(rst.getString("prenom"));
                user.setPhoto(rst.getString("photo"));
                user.setEmail(rst.getString("email"));
                user.setUsername(rst.getString("username"));
                user.setPassword(rst.getString("password").replace("2y","2a"));
               String[] role = {rst.getString("roles")} ;
                user.setType(role);
                user.setStatut(rst.getString("statut"));
            }
            
        } catch (SQLException ex) {
            System.out.println("erreur de selection");
        }
         System.out.println(user);
         }
         
        return user;
    }
    public boolean isUserExist( String userLogin, String userPassword ){
        return this.searchUser( userLogin , userPassword).getIdUser() != 0 ; 
    }

    @Override
    public void updateAdmin(User u) {
        try {
            String query = " UPDATE `user` SET `nom`='"+u.getNom()+"', prenom='"+u.getPrenom()+"'"
                    + ", username='"+u.getUsername()+"', email='"+u.getEmail()+"', password='"+u.getPassword()+"'"
                    + ", photo='"+u.getPhoto()+"' , updated_at='"+u.getDateModification()+"'"
            + " WHERE  id =" + u.getIdUser() ;
                PreparedStatement ps = cnx.prepareStatement(query);
                ps.executeUpdate();
                System.out.println("mise à jour faite");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    @Override
    public void updateCoach(Coach c) {
        try {
            String query = " UPDATE `user` SET `nom`='"+c.getNom()+"', prenom='"+c.getPrenom()+"'"
                    + ", username='"+c.getUsername()+"', email='"+c.getEmail()+"', password='"+c.getPassword()+"'"
                    + ",adresse='"+c.getAdresse()+"', telephone='"+c.getTel()+"'"
                    + ", photo='"+c.getPhoto()+"' , updated_at='"+c.getDateModification()+"'"
            + " WHERE  id =" + c.getIdUser() ;
                PreparedStatement ps = cnx.prepareStatement(query);
                ps.executeUpdate();
                System.out.println("mise à jour faite");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    @Override
    public void updateMember(Member c) {
        try {
            String query = " UPDATE `user` SET `nom`='"+c.getNom()+"', prenom='"+c.getPrenom()+"'"
                    + ", username='"+c.getUsername()+"', email='"+c.getEmail()+"', password='"+c.getPassword()+"'"
                    + ",poids='"+c.getPoids()+"', taille='"+c.getTaille()+"'"
                    + ", photo='"+c.getPhoto()+"' , updated_at='"+c.getDateModification()+"'"
            + " WHERE  id =" + c.getIdUser() ;
                PreparedStatement ps = cnx.prepareStatement(query);
                ps.executeUpdate();
                System.out.println("mise à jour faite");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public String getPassword(String email) {
          User user = new User();
         try {
            Statement stm = cnx.createStatement();
            String query = "SELECT * FROM user WHERE email ='"+email+"'";
            ResultSet rst = stm.executeQuery(query);
            
            while(rst.next()){
                user.setIdUser(rst.getInt("id"));
                user.setPassword(rst.getString("password"));
               String[] role = {rst.getString("roles")} ;
                user.setType(role);
            }
            
        } catch (SQLException ex) {
            System.out.println("erreur de selection");
        }
         System.out.println(user.getPassword());
        return user.getPassword();
    }

    @Override
    public String getStatut(String email) {
         User user = new User();
         try {
            Statement stm = cnx.createStatement();
            String query = "SELECT * FROM user WHERE email ='"+email+"'";
            ResultSet rst = stm.executeQuery(query);
            
            while(rst.next()){
                user.setIdUser(rst.getInt("id"));
                user.setStatut(rst.getString("statut"));
            }
            
        } catch (SQLException ex) {
            System.out.println("erreur de selection");
        }
        return user.getStatut();
    }

    
}
