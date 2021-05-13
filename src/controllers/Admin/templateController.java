/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers.Admin;

import Enitities.Coach;
import Enitities.User;
import Service.serviceUser;
import Utils.globalMethods;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author NGONGANG Loic F
 */
public class templateController implements Initializable {

    @FXML
    private AnchorPane bodyPane;
    
    AnchorPane dashboard,coach,demandcoach,member,appointement,program;
    @FXML
    private ImageView btnLogout;
    
    globalMethods g = new globalMethods();
    @FXML
    private BorderPane borderPane;
    User userConnected;
    @FXML
    private ImageView imgProfil;
    @FXML
    private Label lbName;
    Service.serviceUser su = new serviceUser();
    @FXML
    private AnchorPane generalPane;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
             dashboard = FXMLLoader.load(getClass().getResource("/views/admin/dashboard.fxml"));
             coach = FXMLLoader.load(getClass().getResource("/views/admin/listCoach.fxml"));
             demandcoach = FXMLLoader.load(getClass().getResource("/views/admin/listDemandCoach.fxml"));
             member = FXMLLoader.load(getClass().getResource("/views/admin/listMember.fxml"));
             appointement = FXMLLoader.load(getClass().getResource("/aymen/gui/tableView.fxml"));
             program = FXMLLoader.load(getClass().getResource("/views/admin/program.fxml"));
            
        } catch (IOException ex) {
            Logger.getLogger(templateController.class.getName()).log(Level.SEVERE, null, ex);
        }
        setNode(dashboard);
    } 
   public void initializeData(User u){
       User tempUser = new User(); 
       tempUser = u;userConnected = u;
       imgProfil.setImage(new Image("file:C:\\xampp\\htdocs\\moveat2\\public\\upload\\images\\"+tempUser.getPhoto()));
       lbName.setText(tempUser.getNom()+" "+tempUser.getPrenom());
   }
    
     private void setNode(Node node) {
        bodyPane.getChildren().clear();
        bodyPane.getChildren().add((Node) node);

        FadeTransition ft = new FadeTransition(Duration.millis(1500));
        ft.setNode(node);
        ft.setFromValue(0.1);
        ft.setToValue(1);
        ft.setCycleCount(1);
        ft.setAutoReverse(false);
        ft.play();
    }

    @FXML
    private void logout(MouseEvent event) throws IOException {
        g.goTo("/views/security/login.fxml", event);
    }

    @FXML
    private void dashboard(ActionEvent event) {
        setNode(dashboard);
    }

    @FXML
    private void coach(ActionEvent event) {
        setNode(coach);
    }

    @FXML
    private void member(ActionEvent event) {
        setNode(member);
    }

    @FXML
    private void appointement(ActionEvent event) {
        setNode(appointement);
    }

    @FXML
    private void program(ActionEvent event) {
    }

    @FXML
    private void demandCoach(ActionEvent event) {
        setNode(demandcoach);
    }

    @FXML
    private void menu(MouseEvent event) {
        borderPane.toBack();
    }

    @FXML
    private void profilAdmin(MouseEvent event) {
         try {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation( getClass().getResource("/views/admin/profilAdmin.fxml") ) ;
                Parent itemUpdateViewParent = loader.load();
                Scene homeViewScene = new Scene( itemUpdateViewParent ) ;
                adminProfilController controller = (adminProfilController)loader.getController();
                try {
                     controller.initializeData(userConnected );                 
                     Stage window = new Stage();
                     window.setTitle("Profil Admin");
                     window.setScene( homeViewScene );
                     window.show();
                 } catch (Exception ex) {
                     Logger.getLogger(listCoachController.class.getName()).log(Level.SEVERE, null, ex);
                 }
            } catch (IOException ex) {
                Logger.getLogger(listCoachController.class.getName()).log(Level.SEVERE, null, ex);
            }
    }

    @FXML
    private void refresh(MouseEvent event) throws IOException {
        g.goTo("/views/templates/admin.fxml", userConnected, event);
    }
    
}
