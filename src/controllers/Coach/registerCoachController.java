/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers.Coach;

import Enitities.Coach;
import Utils.globalMethods;
import Service.serviceUser;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
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
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;

/**
 *
 * @author NGONGANG Loic F
 */
public class registerCoachController implements Initializable {

   ObservableList sexeList = FXCollections.observableArrayList();
    ObservableList specialiteList = FXCollections.observableArrayList();
    @FXML
    private AnchorPane registerCoachPane;
    @FXML
    private TextField nom;
    @FXML
    private TextField prenom;
    @FXML
    private TextField email;
    @FXML
    private TextField username;
    @FXML
    private ChoiceBox sexe;
    @FXML
    private ChoiceBox specialite;
    @FXML
    private TextField tel;
    @FXML
    private TextField adresse;
    @FXML
    private PasswordField password;
    @FXML
    private PasswordField confirmPassoword;
    @FXML
    private Button btnInscription;
    @FXML
    private Button btnImage;
    @FXML
    private Button btnPdf;
    @FXML
    private TextField nameImage;
    @FXML
    private TextField namePdf;
    @FXML
    private ImageView btnBack;
    
    private String pathImage;
    private String extImage;
    private String pathPdf;
    private String extPdf;
   
    globalMethods g = new globalMethods();
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        loadSexe();
        loadSpecialite();
    }    
    
    private void loadSexe(){
        sexeList.removeAll(sexeList);
        String a = "Homme";
        String b = "Femme";
        sexeList.addAll(a,b);
        sexe.getItems().addAll(sexeList);
    }   
    private void loadSpecialite(){
        specialiteList.removeAll(specialiteList);
        String a = "Nutritioniste";
        String b = "Coach Sportif";
        String c = "Psychothérapeute";
        specialiteList.addAll(a,b,c);
        specialite.getItems().addAll(specialiteList);
    }
    private String getExtension( File filepath )
    {
        return  filepath.getName().substring( filepath.getName().lastIndexOf(".") );
    }
    @FXML
    private void btnPdfAction(ActionEvent event) {
        FileChooser fc = new FileChooser();
        fc.setInitialDirectory(new File("C:\\Users\\NGONGANG Loic F\\Documents"));
        fc.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Pdf Files","*.pdf"));
        File selectedFile = fc.showOpenDialog(null);
       
        if(selectedFile != null){
             namePdf.setText(selectedFile.getName());
             pathPdf =  selectedFile.getAbsolutePath();
             extPdf = getExtension(selectedFile);
        }
        else{
            System.out.println("aucun fichier choisi");
        }
    }

    @FXML
    private void btnImageAction(ActionEvent event) {
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
    
    @FXML
    private void addCoach(ActionEvent event) throws IOException{
        
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
        if( !this.namePdf.getText().equals(""))
            {                    
                DateTimeFormatter date = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
                LocalDateTime now = LocalDateTime.now();
                this.namePdf.setText( date.format( now )+extPdf );
            }
            else
            {                   
                this.namePdf.setText("defaultImage.png");
            }
        Service.serviceUser su = new serviceUser();
        Coach c = new Coach();
        c.setEmail(email.getText());
        c.setNom(nom.getText());
        c.setPrenom(prenom.getText());
        c.setUsername(username.getText());
        c.setSexe(sexe.getValue().toString());
        c.setSpecialite(specialite.getValue().toString());
        c.setTel(tel.getText());
        c.setAdresse(adresse.getText());
        c.setPassword(password.getText());
        c.setPhoto(nameImage.getText());
        c.setJustificatif(namePdf.getText());
        c.setStatut("nonactived");
        c.setType("coach");
        su.addCoach(c);
        
        File srcImage = new File( pathImage ) ;
        Path desImage = Paths.get("E:\\projetsCodename\\moveat\\src\\ressources\\images\\" + this.nameImage.getText());
        Files.copy( srcImage.toPath() , desImage  , StandardCopyOption.REPLACE_EXISTING) ;
        File srcPdf = new File( pathPdf ) ;
        Path desPdf = Paths.get("E:\\projetsCodename\\moveat\\src\\ressources\\fichiers\\" + this.namePdf.getText());
        Files.copy( srcPdf.toPath() , desPdf  , StandardCopyOption.REPLACE_EXISTING) ;
        
    }

    @FXML
    private void back(MouseEvent event) throws IOException {
         g.goTo("/views/security/login.fxml", event);
    }
    
}
