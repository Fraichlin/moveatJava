/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers.Coach;

import Enitities.Coach;
import Enitities.User;
import Service.serviceUser;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import java.util.UUID;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import org.mindrot.jbcrypt.BCrypt;

/**
 *
 * @author NGONGANG Loic F
 */
public class coachProfilController implements Initializable{

    @FXML
    private ImageView imageView;
    @FXML
    private TextField lbName;
    @FXML
    private TextField lbSurname;
    @FXML
    private TextField lbUsername;
    @FXML
    private TextField lbEmail;
    @FXML
    private PasswordField lbPassword;
    @FXML
    private PasswordField lbPasswordConfirm;
    @FXML
    private Button btnModif;
    @FXML
    private Button btnImage;
    @FXML
    private TextField lbImage;
    @FXML
    private TextField lbAdress;
    @FXML
    private TextField lbTel;
    Coach userConnected;
    private String pathImage;
    private String extImage;
    @FXML
    private AnchorPane viewProfileMemberPane;
    Service.serviceUser su = new serviceUser();
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }
    
     public void initializeData(Coach u){
        userConnected = u;
        lbName.setText(userConnected.getNom());
        lbSurname.setText(userConnected.getPrenom());
        lbUsername.setText(userConnected.getUsername());
        lbEmail.setText(userConnected.getEmail());
        lbAdress.setText(userConnected.getAdresse());
        lbTel.setText(userConnected.getTel());
        lbPassword.setText("12345678");
        lbPasswordConfirm.setText("12345678");
      
        imageView.setImage(new Image("file:C:\\xampp\\htdocs\\moveat2\\public\\upload\\images\\"+userConnected.getPhoto()));
       
        
    }
    
     private String getExtension( File filepath )
    {
        return  filepath.getName().substring( filepath.getName().lastIndexOf(".") );
    }

    @FXML
    private void btnImageAction(ActionEvent event) {
        FileChooser fc = new FileChooser();
        fc.setInitialDirectory(new File("C:\\Users\\NGONGANG Loic F\\Pictures"));
        fc.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Images Files","*.png","*.jpg","*.jpeg"));
        File selectedFile = fc.showOpenDialog(null);
        if(selectedFile != null){
             lbImage.setText(selectedFile.getName());
             pathImage =  selectedFile.getAbsolutePath();
             extImage = getExtension(selectedFile);
        }
        else{
            System.out.println("aucun fichier choisi");
        }
    }

    @FXML
    private void modifCoach(ActionEvent event) throws IOException {
        String imageId = UUID.randomUUID().toString();
        if("".equals(lbEmail.getText()) || "".equals(lbPassword.getText()) || "".equals(lbPasswordConfirm.getText())){
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "Veuillez remplir les champs vides !!");
                 alert.setHeaderText("Champs manquants !!");
                     alert.showAndWait();
        }
        else{
            if(!lbPasswordConfirm.getText().equals(lbPassword.getText())){
                    Alert alert = new Alert(Alert.AlertType.INFORMATION, "Veuillez saisir le meme mot de passe !!");
                 alert.setHeaderText("Mots de passe différents !!");
                     alert.showAndWait();
            }
            else{
                Coach coach = new Coach();
                coach = userConnected;
                coach.setNom(lbName.getText());
                coach.setPrenom(lbSurname.getText());
                coach.setUsername(lbUsername.getText());
                coach.setAdresse(lbAdress.getText());
                coach.setTel(lbTel.getText());
                coach.setEmail(lbEmail.getText());
                
                if(!"12345678".equals(lbPassword.getText())){
                    String hashedPassword = BCrypt.hashpw(lbPassword.getText(), BCrypt.gensalt());
                    hashedPassword = hashedPassword.replace("2a", "2y");
                    coach.setPassword(hashedPassword);
                }
                
                if(!"".equals(lbImage.getText())){
                    coach.setPhoto(imageId+extImage);
                     File srcImage = new File( pathImage ) ;
                Path desImage = Paths.get("C:\\xampp\\htdocs\\moveat2\\public\\upload\\images\\"+imageId+extImage);
                Files.copy( srcImage.toPath() , desImage  , StandardCopyOption.REPLACE_EXISTING) ;
                }
                long now = System.currentTimeMillis();
                java.sql.Date date = new java.sql.Date(now);
                coach.setDateModification(date);
                su.updateCoach(coach);
                 
               
                Alert alert = new Alert(Alert.AlertType.INFORMATION, "Mise à jour réussie !!");
                alert.showAndWait();
                
                 //refresh the view
                initializeData(coach);
                //refresh parent view
//                FXMLLoader loader = new FXMLLoader();
//                loader.setLocation( getClass().getResource("/aymen/gui/Home.fxml") ) ;
//                Parent itemUpdateViewParent = loader.load();
//                aymen.gui.controller.nutritioniste.HomeController controler = (aymen.gui.controller.nutritioniste.HomeController)loader.getController();
//                controler.initializeData(coach);
            }
        }
    }

    
    
}
