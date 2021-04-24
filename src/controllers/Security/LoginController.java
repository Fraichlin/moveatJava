/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers.Security;

import Service.serviceUser;
import Utils.globalMethods;
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
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

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
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void connexion(ActionEvent event) throws IOException {
        if(lbEmail.getText().equals("") && lbPassword.getText().equals("")){
             Alert alert = new Alert(Alert.AlertType.INFORMATION, "Veuillez remplir les champs !!");
             alert.setHeaderText("Champs manquants !!");
                     alert.showAndWait();
        }
        else{
            if(!su.isUserExist(lbEmail.getText(), lbPassword.getText())){
                 Alert alert = new Alert(Alert.AlertType.INFORMATION, "Email ou mot de passe invalide !!");
                 alert.setHeaderText("Identifiants invalides !!");
                     alert.showAndWait();
            }
            if(su.isUserExist(lbEmail.getText(), lbPassword.getText())){
                 Alert alert = new Alert(Alert.AlertType.INFORMATION, "Connexion réussie !!");
                 alert.setHeaderText("Bienvenue "+su.searchUser(lbEmail.getText(), lbPassword.getText()).getUsername()+" !");
                     alert.showAndWait();
   
                     if("admin".equals(su.searchUser(lbEmail.getText(), lbPassword.getText()).getType())){
                         FXMLLoader loader = new FXMLLoader();
                        loader.setLocation( getClass().getResource("/views/templates/admin.fxml") ) ;
                        Parent itemUpdateViewParent = loader.load();
                        Scene homeViewScene = new Scene( itemUpdateViewParent ) ;
                        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
                        window.setScene( homeViewScene );
                        window.setResizable(true);
                        window.show();
                     }
                      if("memmber".equals(su.searchUser(lbEmail.getText(), lbPassword.getText()).getType())){
                         FXMLLoader loader = new FXMLLoader();
                        loader.setLocation( getClass().getResource("/views/admin/homeMember.fxml") ) ;
                        Parent itemUpdateViewParent = loader.load();
                        Scene homeViewScene = new Scene( itemUpdateViewParent ) ;
                        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
                        window.setScene( homeViewScene );
                        window.setResizable(true);
                        window.show();
                     }
                       if("coach".equals(su.searchUser(lbEmail.getText(), lbPassword.getText()).getType())){
                         FXMLLoader loader = new FXMLLoader();
                        loader.setLocation( getClass().getResource("/views/admin/homeCoach.fxml") ) ;
                        Parent itemUpdateViewParent = loader.load();
                        Scene homeViewScene = new Scene( itemUpdateViewParent ) ;
                        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
                        window.setScene( homeViewScene );
                        window.setResizable(true);
                        window.show();
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
    
}
