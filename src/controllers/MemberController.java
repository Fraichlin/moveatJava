/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import Enitities.Coach;
import Enitities.Member;
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
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;

/**
 * FXML Controller class
 *
 * @author NGONGANG Loic F
 */
public class MemberController implements Initializable {
    ObservableList sexeList = FXCollections.observableArrayList();
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
    private PasswordField password;
    @FXML
    private PasswordField confirmPassoword;
    @FXML
    private Button btnInscription;
    @FXML
    private AnchorPane registerMemberPane;
    @FXML
    private TextField taille;
    @FXML
    private TextField poids;
    @FXML
    private Button btnImage;
    @FXML
    private Button btnBack;
    @FXML
    private TextField nameImage;
    
    private String pathImage;
    private String extImage;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        loadSexe();
    }
    
    private void loadSexe(){
        sexeList.removeAll(sexeList);
        String a = "Homme";
        String b = "Femme";
        sexeList.addAll(a,b);
        sexe.getItems().addAll(sexeList);
    }   

    @FXML
    private void addMember(ActionEvent event) throws IOException {
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
        Service.serviceUser su = new serviceUser();
        Member m = new Member();
        m.setEmail(email.getText());
        m.setNom(nom.getText());
        m.setPrenom(prenom.getText());
        m.setUsername(username.getText());
        m.setSexe(sexe.getValue().toString());
        m.setPassword(password.getText());
        m.setPhoto(nameImage.getText());
        m.setPoids(poids.getText());
        m.setTaille(taille.getText());
        m.setStatut("actived");
        m.setType("member");
        su.addMember(m);
        
        File srcImage = new File( pathImage ) ;
        Path desImage = Paths.get("E:\\projetsCodename\\moveat\\src\\ressources\\images\\" + this.nameImage.getText());
        Files.copy( srcImage.toPath() , desImage  , StandardCopyOption.REPLACE_EXISTING) ;
       
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
    private void back(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("/moveat/FXMLDocument.fxml"));
          registerMemberPane.getChildren().setAll(pane);
    }

    private String getExtension( File filepath )
    {
        return  filepath.getName().substring( filepath.getName().lastIndexOf(".") );
    }
    
}
