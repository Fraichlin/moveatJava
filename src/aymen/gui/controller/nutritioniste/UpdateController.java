/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aymen.gui.controller.nutritioniste;

import Utils.MyConnexion;
import java.io.File;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.Node;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.StringConverter;

/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class UpdateController implements Initializable {

    @FXML
    private TextField tfNom;
    @FXML
    private TextField tfDescription;
    @FXML
    private TextField tfBreakfast;
    @FXML
    private TextField tfLunch;
    @FXML
    private TextField tfDinner;
    @FXML
    private ChoiceBox<String> typeButton;
    @FXML
    private TextField nameImage;
    private String pathImage;
    private String extImage;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    // UPDATE `programme` SET "  + "`prog`= ?,

        
     @FXML
    private void ModifierProg(ActionEvent event) throws SQLException {
        
       String requette = " UPDATE `NutritionalProgram` SET `nom`=? ";
           // + " WHERE  idProgramme =" + idProgramme ;
/*
      WHERE 
       */

       
    Connection cn = MyConnexion.getInstance().getConnection();
    PreparedStatement ps = cn.prepareStatement(requette);
    
    ps.setString(1, tfNom.getText());
    //ps.setString(2,typeButton.getValue());
    ps.setString(3, tfDescription.getText());
    ps.setString(4, nameImage.getText());
    ps.setString(5, tfBreakfast.getText());
    ps.setString(6, tfLunch.getText());
    ps.setString(7, tfDinner.getText());
    ps.executeUpdate();
    System.out.println("Modifier avec succees !");

    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
    stage.close();
    }

    void text(String nom, String type, String description, String image, String breakfast, String lunch, String dinner ){

        tfNom.setText(nom);
        //StringConverter<typeButton>.setText(type);
        tfDescription.setText(description);
        nameImage.setText(image);
        tfBreakfast.setText(breakfast);
        tfLunch.setText(lunch);
        tfDinner.setText(dinner);
        
    }
    @FXML
    private void choisirImage(ActionEvent event) {
                 FileChooser fc = new FileChooser();
        fc.setInitialDirectory(new File("C:\\Users\\NGONGANG Loic F\\Pictures"));
        fc.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Images Files","*.png","*.jpg","*.jpeg"));
        File selectedFile = fc.showOpenDialog(null);
        if(selectedFile != null){
             nameImage.setText(selectedFile.getName());
             pathImage =  selectedFile.getAbsolutePath();
             extImage = getExtension(selectedFile);
        }
        else{
            System.out.println("aucun fichier choisi");
        }
    }
 private String getExtension( File filepath )
    {
        return  filepath.getName().substring( filepath.getName().lastIndexOf(".") );
    }
   
    
}
