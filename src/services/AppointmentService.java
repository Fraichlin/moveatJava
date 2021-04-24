/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.Appointment;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import tools.MaConnexion;

/**
 *
 * @author acer
 */
public class AppointmentService {
    
    Connection cnx;
    PreparedStatement ste;

    public AppointmentService() {
        cnx = MaConnexion.getinstance().getCnx();
    }
    
public void addAppointment(Appointment a){
   
        try {
            String sql = "insert into appointment(nom,prenom,email,tel,date,time,message)"+"values(?,?,?,?,?,?,?)";
            ste = cnx.prepareStatement(sql);
            ste.setString(1, a.getNom());
            ste.setString(2, a.getPrenom());
            ste.setString(3, a.getEmail());
            ste.setInt(4, a.getTel());
            ste.setString(5, a.getDate());
            ste.setString(6, a.getTime());
            ste.setString(7, a.getMessage());
            
           
            
            ste.executeUpdate();
            System.out.println("RDV envoyé");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }    
    
}

public List<Appointment> afficherAppointment(){
    
    List<Appointment> appointments = new ArrayList<>();
   
        try {
             String sql = "select * from appointment";
     
             ste = cnx.prepareStatement(sql);
             ResultSet rs = ste.executeQuery();
             while (rs.next()) {   
                 Appointment a = new Appointment();
                 a.setId(rs.getInt("id"));
                 a.setNom(rs.getString("nom"));
                 a.setPrenom(rs.getString("prenom"));
                 a.setEmail(rs.getString("email"));
                 a.setTel(rs.getInt("tel"));
                 a.setDate(rs.getString("date"));
                 a.setTime(rs.getString("time"));
                 a.setMessage(rs.getString("message"));
                 
                              
                 appointments.add(a);
               
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    
    return  appointments;
    
}    
    
    
     
    
    
    
    
}
