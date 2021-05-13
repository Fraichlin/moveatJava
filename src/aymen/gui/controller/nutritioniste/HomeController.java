/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aymen.gui.controller.nutritioniste;

import Enitities.Coach;
import Enitities.Member;
import Utils.globalMethods;
import aymen.gui.DesignController;
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
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class HomeController implements Initializable {

    @FXML
    private BorderPane borderPane;
    @FXML
    private AnchorPane bodyPane;
    @FXML
    private ImageView btnLogout;
    public Pane context;
    globalMethods g = new globalMethods();
    Coach userConnected;
    AnchorPane Repairpassword,AddP,psy;
    @FXML
    private ImageView imgProfil;
    @FXML
    private Label lbName;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //Load all fxmls in a cache
        try {
           //  Repairpassword = FXMLLoader.load(getClass().getResource("Repairpassword.fxml"));
             AddP = FXMLLoader.load(getClass().getResource("/aymen/gui/TableView_1.fxml"));
             psy = FXMLLoader.load(getClass().getResource("/aymen/gui/Programme.fxml"));
            
        } catch (IOException ex) {
            Logger.getLogger(DesignController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
     public void initializeData(Coach m){
        userConnected = m;
        imgProfil.setImage(new Image("file:C:\\xampp\\htdocs\\moveat2\\public\\upload\\images\\"+userConnected.getPhoto()));
       lbName.setText(userConnected.getNom()+" "+userConnected.getPrenom());
    }

    //Set selected node to a content holder
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
    private void appointement(ActionEvent event) {
    }

    @FXML
    private void program(ActionEvent event){
        setNode(AddP);
        //new FadeIn(context).play();
        
    }


    @FXML
    private void menu(MouseEvent event) {
    }

    @FXML
    private void logout(MouseEvent event) throws IOException {
        g.goTo("/views/security/login.fxml", event);
    }

    @FXML
    private void psyProgram(ActionEvent event) {
        setNode(psy);
    }

    @FXML
    private void profilCoach(MouseEvent event) {
         try {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation( getClass().getResource("/views/coach/profilCoach.fxml") ) ;
                Parent itemUpdateViewParent = loader.load();
                Scene homeViewScene = new Scene( itemUpdateViewParent ) ;
              controllers.Coach.coachProfilController controller = (controllers.Coach.coachProfilController)loader.getController();
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
    
}
