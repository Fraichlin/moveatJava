/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aymen.gui;

import Utils.MyConnexion;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import java.sql.* ;
import javafx.scene.Node;
import javafx.stage.Stage;
/**
 *
 * @author ASUS
 */
public class UpdateController implements Initializable{

    @FXML
    private TextArea twt_prog;
    int id ;
   

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
   
    
   // UPDATE `programme` SET "  + "`prog`= ?,

        
     @FXML
    private void ModifierProg(ActionEvent event) throws SQLException {
        
       String requette = " UPDATE `programme` SET `prog`=? "
            + " WHERE  id =" + id ;
/*
      WHERE 
       */

       
    Connection cn = MyConnexion.getInstance().getConnection();
    PreparedStatement ps = cn.prepareStatement(requette);
    
    ps.setString(1, twt_prog.getText());
    ps.executeUpdate();
    System.out.println("Modifier avec succees !");

    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
    stage.close();
    }

    void text( int id , String prog ){

        this.id=id;
       twt_prog.setText(prog);
    }

 
        
    
 
}
