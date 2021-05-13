/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import java.sql.SQLException;
import Enitities.NutritionalProgram;
import java.util.List;
/**
 *
 * @author ASUS
 */
public interface IServiceNutritionalProgram {
     void ajouter(NutritionalProgram np) throws SQLException;
    boolean delete(NutritionalProgram np) throws SQLException;
    boolean update(NutritionalProgram np) throws SQLException;
    List<NutritionalProgram> readAll() throws SQLException;
    
}
