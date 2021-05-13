/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author NGONGANG Loic F
 */
public class MyConnexion {
    final static String url = "jdbc:mysql://127.0.0.1:3306/moveat";
    final static String login = "root";
    final static String password = "Lenovoa5600";
    static MyConnexion instance = null;
    private Connection cnx;
    
    private MyConnexion(){
        try {
            cnx = DriverManager.getConnection(url,login,password);
            System.out.println("connexion établie");
        } catch (SQLException ex) {
            System.out.println("échec de connexion");
        }
    }
     
    public static MyConnexion getInstance(){
        if(instance == null){
            instance = new MyConnexion();
        }
        
        return instance;
    }
    
    public Connection getConnection(){
        return cnx;
    }
}