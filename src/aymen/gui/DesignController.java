package aymen.gui;

import Enitities.Member;
import Enitities.User;
import Utils.globalMethods;
import com.jfoenix.controls.JFXButton;
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
import javafx.stage.Stage;
import javafx.util.Duration;


public class DesignController implements Initializable {

    @FXML
    private AnchorPane holderPane;
    @FXML
    private JFXButton btnHome;
    @FXML
    private JFXButton btnContacts;
    
    @FXML
    private JFXButton btnPsy;
    @FXML
    private JFXButton btnNutrition;
    @FXML
    private JFXButton btnSport;
    @FXML
    private JFXButton btnRdv;
    
    AnchorPane contacts,Appointments,UserProgramme,mail,nutritionalProgram,rdv;
    @FXML
    private ImageView imgProfil;
    @FXML
    private Label lbName;
    @FXML
    private ImageView btnLogout;
    globalMethods g = new globalMethods();
    Member userConnected;
    @FXML
    private JFXButton btnContacts1;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //Load all fxmls in a cache
        try {
             contacts = FXMLLoader.load(getClass().getResource("/aymen/gui/Contacts.fxml"));
             Appointments = FXMLLoader.load(getClass().getResource("/aymen/gui/MakeAppointment.fxml"));
             UserProgramme = FXMLLoader.load(getClass().getResource("/aymen/gui/UserProgramme.fxml"));
             nutritionalProgram = FXMLLoader.load(getClass().getResource("/aymen/gui/ConsultNutritional.fxml"));
             mail = FXMLLoader.load(getClass().getResource("/aymen/gui/Repairpassword.fxml"));
            
        } catch (IOException ex) {
            Logger.getLogger(DesignController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    public void initializeData(Member m){
        userConnected = m;
        imgProfil.setImage(new Image("file:C:\\xampp\\htdocs\\moveat2\\public\\upload\\images\\"+userConnected.getPhoto()));
       lbName.setText(userConnected.getNom()+" "+userConnected.getPrenom());
    }

    //Set selected node to a content holder
    private void setNode(Node node) {
        holderPane.getChildren().clear();
        holderPane.getChildren().add((Node) node);

        FadeTransition ft = new FadeTransition(Duration.millis(1500));
        ft.setNode(node);
        ft.setFromValue(0.1);
        ft.setToValue(1);
        ft.setCycleCount(1);
        ft.setAutoReverse(false);
        ft.play();
    }

  

    @FXML
    private void switchContacts(ActionEvent event) {
        setNode(contacts);
    }
    


    @FXML
    private void switchPsy(ActionEvent event) {
        setNode(UserProgramme);
    }

    @FXML
    private void switchNutrition(ActionEvent event) {
        setNode(nutritionalProgram);
    }

    @FXML
    private void switchSport(ActionEvent event) {
    }

    @FXML
    private void switchRdv(ActionEvent event) {
        setNode(Appointments);
    }

    

    @FXML
    private void profilMember(MouseEvent event) {
        try {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation( getClass().getResource("/views/member/profilMember.fxml") ) ;
                Parent itemUpdateViewParent = loader.load();
                Scene homeViewScene = new Scene( itemUpdateViewParent ) ;
              controllers.Member.memberProfilController controller = (controllers.Member.memberProfilController)loader.getController();
                try {
                     controller.initializeData(userConnected );                 
                     Stage window = new Stage();
                     window.setTitle("Profil Membre");
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
    private void logout(MouseEvent event) throws IOException {
        g.goTo("/views/security/login.fxml", event);
    }

    @FXML
    private void switchMail(ActionEvent event) {
        setNode(mail);
    }

   

}
