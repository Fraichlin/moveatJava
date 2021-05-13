/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import Enitities.NutritionalProgram;
import Services.IServiceNutritionalProgram;
import java.sql.SQLException;
import java.util.List;
import java.sql.*;
import Utils.MyConnexion;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import static javafx.scene.input.KeyCode.INSERT;

/**
 *
 * @author ASUS
 */
public class ServiceNutritionalProgram implements IServiceNutritionalProgram {
      private Connection con;
    private Statement ste;

    public ServiceNutritionalProgram() {
        con = MyConnexion.getInstance().getConnection();

    }


    @Override
    public void ajouter(NutritionalProgram np) throws SQLException {
                ste = con.createStatement();
            
        String requeteInsert = "INSERT INTO `nutritionalprogram` ( `nom` , `type`, `description`, `image`, `breakfast`, `lunch`, `dinner`) VALUES ('" + np.getNom() + "','" + np.getType() + "','" + np.getDescription() + "','" + np.getImage() + "','" + np.getBreakfast() + "', '" + np.getLunch() + "', '" + np.getDinner() + "');";
        ste.executeUpdate(requeteInsert);
    }
    public void ajouter1(NutritionalProgram np) throws SQLException
    {
    PreparedStatement pre=con.prepareStatement("INSERT INTO `nutritionalprogram` ( `nom` , `type`, `description`, `image`, `breakfast`, `lunch`, `dinner`) VALUES ( ?, ?, ?, ?, ?, ?, ?);");
    pre.setString(1, np.getNom());
    pre.setString(2, np.getType());
    pre.setString(3, np.getDescription());
    pre.setString(4, np.getImage());
    pre.setString(5, np.getBreakfast());
    pre.setString(6, np.getLunch());
    pre.setString(7, np.getDinner());
    pre.executeUpdate();
    }

    @Override
    public boolean delete(NutritionalProgram np) throws SQLException {
      throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean update(NutritionalProgram np) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<NutritionalProgram> readAll() throws SQLException {
         List<NutritionalProgram> ntriprog = new ArrayList<>();
   
        try {
             String sql = "select * from `nutritionalprogram`";
     
             ste = con.createStatement();
             ResultSet rs;
             rs = ste.executeQuery(sql);
             while (rs.next()) {   
                NutritionalProgram p = new NutritionalProgram();
                 //p.setId(rs.getInt("idProgramme"));
                 p.setNom(rs.getString(1));
                 p.setType(rs.getString("type"));
                 p.setDescription(rs.getString(3));
                 p.setImage(rs.getString(4));
                 p.setBreakfast(rs.getString(5));
                 p.setLunch(rs.getString(6));
                 p.setDinner(rs.getString(7));
                 ntriprog.add(p);
               
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    
    return  ntriprog;
    }

    //public void NutritionalProgram(NutritionalProgram p) {
      //  throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    //}

    public void NutritionalProgram(NutritionalProgram p) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
       
    

   
    
}
