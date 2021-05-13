/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers.Member;

import Enitities.Member;
import Service.serviceUser;
import Utils.globalMethods;
import aymen.gui.Mail;
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
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import org.mindrot.jbcrypt.BCrypt;

/**
 *
 * @author NGONGANG Loic F
 */
public class registerMemberController implements Initializable{
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
    private ImageView btnBack;
    @FXML
    private TextField nameImage;
    
    private String pathImage;
    private String extImage;
    
    globalMethods g = new globalMethods();

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
             nameImage.setText(selectedFile.getName());
             pathImage =  selectedFile.getAbsolutePath();
             extImage = getExtension(selectedFile);
        }
        else{
            System.out.println("aucun fichier choisi");
        }
    }

    @FXML
    private void back(MouseEvent event) throws IOException {
        g.goTo("/views/security/login.fxml", event);
    }

    @FXML
    private void addMember(ActionEvent event) throws IOException{
        String imageId = UUID.randomUUID().toString();
        
        Service.serviceUser su = new serviceUser();
        Member m = new Member();
        m.setEmail(email.getText());
        m.setNom(nom.getText());
        m.setPrenom(prenom.getText());
        m.setUsername(username.getText());
        m.setSexe(sexe.getValue().toString());
        String hashedPassword = BCrypt.hashpw(password.getText(), BCrypt.gensalt());
        hashedPassword = hashedPassword.replace("2a", "2y");
        m.setPassword(hashedPassword);
        if(!this.nameImage.getText().equals(""))m.setPhoto(imageId+extImage);
        if(this.nameImage.getText().equals(""))m.setPhoto("profilUser.png");
        m.setPoids(poids.getText());
        m.setTaille(taille.getText());
        m.setIsVerified(0);
        m.setStatut("actived");
        String[] role = {"[\"ROLE_MEMBER\"]"} ;
        m.setType(role);
        su.addMember(m);
       
        if(!this.nameImage.getText().equals(""))
            {                   
                File srcImage = new File( pathImage ) ;
                Path desImage = Paths.get("C:\\xampp\\htdocs\\moveat2\\public\\upload\\images\\" + imageId+extImage); 
                Files.copy( srcImage.toPath() , desImage  , StandardCopyOption.REPLACE_EXISTING) ;
            }
        
        
//        Mail mail = new Mail();
//                      mail.sendMail(m.getEmail(), "Inscription effectuée", "Bienvenue sur MovEat !!", "ngongangloic151@gmail.com", "lenovoa5600");
        
        Alert alert = new Alert(Alert.AlertType.INFORMATION, "Un mail de confirmation vous sera envoyé !!");
        alert.setHeaderText("Incription effectuée !!");
        alert.showAndWait();
    }
    
}
