/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers.Security;

import Enitities.Coach;
import Enitities.Member;
import Enitities.User;
import Service.serviceUser;
import Utils.globalMethods;
import com.sun.javaws.BrowserSupport;
import controllers.Admin.templateController;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.json.JSONArray;
import org.mindrot.jbcrypt.BCrypt;

/**
 * FXML Controller class
 *
 * @author NGONGANG Loic F
 */
public class LoginController implements Initializable {

    @FXML
    private TextField lbEmail;
    @FXML
    private TextField lbPassword;
    @FXML
    private Button btnConnexion;
    
    Service.serviceUser su = new serviceUser();
    globalMethods g = new globalMethods();
    
    @FXML
    private Button btnRegister;
    @FXML
    private Label lbWebsite;
    @FXML
    private Label lbPasswordForgotten;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void connexion(ActionEvent event) throws IOException {
        String[] roleAdmin = {"[\"ROLE_ADMIN\"]"};
        String[] roleMember = {"[\"ROLE_MEMBER\"]"};
        String[] roleCoach = {"[\"ROLE_COACH\"]"};
       
        String email = lbEmail.getText();
        String password = lbPassword.getText();
        if(email.equals("") && password.equals("")){
             Alert alert = new Alert(Alert.AlertType.INFORMATION, "Veuillez remplir les champs !!");
             alert.setHeaderText("Champs manquants !!");
                     alert.showAndWait();
        }
        else{
            if(!su.isUserExist(email, password)){
                 Alert alert = new Alert(Alert.AlertType.INFORMATION, "Email ou mot de passe invalide !!");
                 alert.setHeaderText("Identifiants invalides !!");
                     alert.showAndWait();
            }
            
            if(su.isUserExist(email, password)){
                User user = su.searchUser(email, password);
                Member member = su.searchMember(email, password);
                Coach coach = su.searchCoach(email, password);
                if(user.getStatut().equals("nonactived")
                        || user.getStatut().equals("blocked")){
                    
                    if(user.getStatut().equals("nonactived")){
                     Alert alert = new Alert(Alert.AlertType.INFORMATION, "Refus de connexion !!");
                     alert.setHeaderText("Votre compte n'est pas encore activé !");
                     alert.showAndWait();}
                    if(user.getStatut().equals("blocked")){
                     Alert alert = new Alert(Alert.AlertType.INFORMATION, "Refus de connexion !!");
                     alert.setHeaderText("Votre compte est bloqué !");
                     alert.showAndWait();}
                }
                else if(user.getStatut().equals("actived")
                        || user.getStatut().equals("unblocked")){
                    
                 Alert alert = new Alert(Alert.AlertType.INFORMATION, "Connexion réussie !!");
                 alert.setHeaderText("Bienvenue "+user.getUsername()+" !");
                 
                     if(roleAdmin[0].equals(user.getType()[0])){
                         FXMLLoader loader = new FXMLLoader();
                        loader.setLocation( getClass().getResource("/views/templates/admin.fxml") ) ;
                        Parent itemUpdateViewParent = loader.load();
                        Scene homeViewScene = new Scene( itemUpdateViewParent ) ;
                        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
                         templateController controler = (templateController)loader.getController();
                         controler.initializeData(user);
                        window.setScene( homeViewScene );
                        window.setResizable(true);
                        window.show();
                     }
                      if(roleMember[0].equals(user.getType()[0])){
                        FXMLLoader loader = new FXMLLoader();
                        loader.setLocation( getClass().getResource("/aymen/gui/Design.fxml") ) ;
                        Parent itemUpdateViewParent = loader.load();
                        Scene homeViewScene = new Scene( itemUpdateViewParent ) ;
                        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
                        aymen.gui.DesignController controler = (aymen.gui.DesignController)loader.getController();
                        controler.initializeData(member);
                        window.setScene( homeViewScene );
                        window.setResizable(true);
                        window.show();
                     }
                       if(roleCoach[0].equals(user.getType()[0])){
                         FXMLLoader loader = new FXMLLoader();
                        loader.setLocation( getClass().getResource("/aymen/gui/Home.fxml") ) ;
                        Parent itemUpdateViewParent = loader.load();
                        Scene homeViewScene = new Scene( itemUpdateViewParent ) ;
                        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
                        aymen.gui.controller.nutritioniste.HomeController controler = (aymen.gui.controller.nutritioniste.HomeController)loader.getController();
                         controler.initializeData(coach);
                        window.setScene( homeViewScene );
                        window.setResizable(true);
                        window.show();
                     }
                }
            }
        }
    }

    @FXML
    private void register(ActionEvent event) throws IOException{
        ButtonType coach = new ButtonType("COACH", ButtonBar.ButtonData.OK_DONE);
        ButtonType member = new ButtonType("MEMBER", ButtonBar.ButtonData.OK_DONE);
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Vous voulez vous inscrire en tant que coach ou membre ?", coach, member, ButtonType.CANCEL);
        alert.setHeaderText("Choix d'inscription !");
        alert.showAndWait();
                if (alert.getResult() == coach) {
                    g.goTo("/views/coach/registerCoach.fxml", event);
                }
                else if (alert.getResult() == member) {
                    g.goTo("/views/member/registerMember.fxml", event);
                }
    }

    @FXML
    private void web(MouseEvent event) {
        
    }
    
}
