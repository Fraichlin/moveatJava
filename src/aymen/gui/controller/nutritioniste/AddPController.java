/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aymen.gui.controller.nutritioniste;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXTextField;
import Enitities.NutritionalProgram;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.TilePane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javax.imageio.ImageIO;
import Service.ServiceNutritionalProgram;
import Utils.MyConnexion;

/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class AddPController implements Initializable {
    ObservableList typeList = FXCollections.observableArrayList();

    @FXML
    private JFXTextField txtNomProg;
    @FXML
    private JFXTextField txtDes;
    @FXML
    private JFXTextField txtDin;
    @FXML
    private JFXTextField txtLun;
    @FXML
    private ImageView imageid;
    @FXML
    private JFXButton setOnAction;
    @FXML
    private ChoiceBox<?> typeProg;
    @FXML
    private JFXTextField txtBreak;
    
   
    private String pathImage;
    private String extImage;

    /**
     * Initializes the controller class.
     */
    Connection connection ;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;
    NutritionalProgram tabView = null;
    ObservableList<NutritionalProgram>  TabViewList = FXCollections.observableArrayList();
    
    ServiceNutritionalProgram snp = new ServiceNutritionalProgram();
    @FXML
    private TextField nameImage;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
        // TODO
        loadType();
    } 
    private void loadType(){
        typeList.removeAll(typeList);
        String a = "A(prendre poid)";
        String b = "B(predre poid)";
        String c = "C(maintin)";
        typeList.addAll(a,b,c);
        typeProg.getItems().addAll(typeList);
    }
    


    @FXML
    private void addOnAction(ActionEvent event) throws IOException, SQLException {
         if( !this.nameImage.getText().equals(""))
            {                    
                DateTimeFormatter date = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
                LocalDateTime now = LocalDateTime.now();
                this.nameImage.setText( date.format( now )+extImage );
            }
            else
            {                   
                this.nameImage.setText("defaultImage.png");
            }
        Service.ServiceNutritionalProgram su = new ServiceNutritionalProgram();
        NutritionalProgram np = new NutritionalProgram();
        np.setNom(txtNomProg.getText());
        np.setType(typeProg.getValue().toString());
        np.setDescription(txtDes.getText());
        np.setImage(nameImage.getText());
        np.setBreakfast(txtBreak.getText());
        np.setLunch(txtLun.getText());
        np.setDinner(txtDin.getText());
        
        su.ajouter(np);
        
        File srcImage = new File( pathImage ) ;
        Path desImage = Paths.get("D:\\projetsCodename\\moveat\\src\\ressources\\images\\" + this.nameImage.getText());
        Files.copy( srcImage.toPath() , desImage  , StandardCopyOption.REPLACE_EXISTING) ;
       
    }
    


   
    @FXML
    private void setOnAction(ActionEvent event) {
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
