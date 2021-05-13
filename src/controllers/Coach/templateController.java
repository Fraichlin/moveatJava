/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers.Coach;

import Enitities.Coach;
import Enitities.User;
import Service.serviceUser;
import Utils.globalMethods;
import controllers.Admin.adminProfilController;
import controllers.Admin.listCoachController;
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
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 *
 * @author NGONGANG Loic F
 */
public class templateController implements Initializable{

    @FXML
    private BorderPane borderPane;
    @FXML
    private ImageView imgProfil;
    @FXML
    private Label lbName;
    @FXML
    private AnchorPane bodyPane;
    @FXML
    private ImageView btnLogout;
    globalMethods g = new globalMethods();
    Coach userConnected;
    Service.serviceUser su = new serviceUser();
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
//        try {
//             dashboard = FXMLLoader.load(getClass().getResource("/views/admin/dashboard.fxml"));
//             coach = FXMLLoader.load(getClass().getResource("/views/admin/listCoach.fxml"));
//             demandcoach = FXMLLoader.load(getClass().getResource("/views/admin/listDemandCoach.fxml"));
//             member = FXMLLoader.load(getClass().getResource("/views/admin/listMember.fxml"));
//             appointement = FXMLLoader.load(getClass().getResource("/views/admin/appointment.fxml"));
//             program = FXMLLoader.load(getClass().getResource("/views/admin/program.fxml"));
//            
//        } catch (IOException ex) {
//            Logger.getLogger(controllers.Admin.templateController.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        setNode(dashboard);
    } 
    
     public void initializeData(Coach u){
       userConnected = u;
       imgProfil.setImage(new Image("file:C:\\xampp\\htdocs\\moveat2\\public\\upload\\images\\"+userConnected.getPhoto()));
       lbName.setText(userConnected.getNom()+" "+userConnected.getPrenom());
     }
    @FXML
    private void profilCoach(MouseEvent event) {
        try {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation( getClass().getResource("/views/coach/profilCoach.fxml") ) ;
                Parent itemUpdateViewParent = loader.load();
                Scene homeViewScene = new Scene( itemUpdateViewParent ) ;
                coachProfilController controller = (coachProfilController)loader.getController();
                try {
                     controller.initializeData(userConnected );                 
                     Stage window = new Stage();
                     window.setTitle("Profil Coach");
                     window.setScene( homeViewScene );
                     window.show();
                 } catch (Exception ex) {
                     Logger.getLogger(listCoachController.class.getName()).log(Level.SEVERE, null, ex);
                 }                   
            } catch (IOException ex) {
                Logger.getLogger(listCoachController.class.getName()).log(Level.SEVERE, null, ex);
            }
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
    private void dashboard(ActionEvent event) {
    }

    @FXML
    private void coach(ActionEvent event) {
    }

    @FXML
    private void appointement(ActionEvent event) {
    }

    @FXML
    private void program(ActionEvent event) {
    }

    @FXML
    private void menu(MouseEvent event) {
    }

    @FXML
    private void refresh(MouseEvent event) {
         initializeData(su.searchCoach(userConnected.getEmail(), userConnected.getPassword()));
    }

    @FXML
    private void logout(MouseEvent event) throws IOException {
        g.goTo("/views/security/login.fxml", event);
    }
    
}
