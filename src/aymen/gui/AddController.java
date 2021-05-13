
package aymen.gui;

import Utils.MyConnexion;
import java.net.URL;
import java.sql.PreparedStatement;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import java.sql.*;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
/**
 *
 * @author ASUS
 */
public class AddController implements Initializable{

    @FXML
    private TextArea twt_prog;

 

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }
    
    
    
    
      private boolean verfif() {

          if(twt_prog.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Remplir !!");
            alert.setHeaderText(null);
            alert.setContentText("Il faut remplir tous les champs !!");
            alert.showAndWait();
          }
          return true ;
      }
    
       @FXML
    private void AjouterProg(ActionEvent event) throws SQLException {

        if (verfif()) {

            String requette;
            requette = "INSERT INTO `programme`( `prog`) " +
                    "VALUES( ? ) ";

            Connection connection = MyConnexion.getInstance().getConnection();
            PreparedStatement ps = connection.prepareStatement(requette);            
             ps.setString(1, twt_prog.getText());

            ps.executeUpdate();
            System.out.println("Ajouter avec sucees !");

            
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.close();
        }
    
    }

  
    

}
