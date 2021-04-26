package gui;

import com.jfoenix.controls.JFXButton;
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
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;


public class DesignController implements Initializable {

    @FXML
    private AnchorPane holderPane;
    @FXML
    private JFXButton btnHome;
    @FXML
    private JFXButton btnContacts;
    
    @FXML
    private JFXButton btnProfil;
    @FXML
    private JFXButton btnPsy;
    @FXML
    private JFXButton btnNutrition;
    @FXML
    private JFXButton btnSport;
    @FXML
    private JFXButton btnRdv;
    @FXML
    private JFXButton btnDisconnect;
    
    AnchorPane contacts,Appointments;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //Load all fxmls in a cache
        try {
             contacts = FXMLLoader.load(getClass().getResource("Contacts.fxml"));
             Appointments = FXMLLoader.load(getClass().getResource("MakeAppointment.fxml"));
            
        } catch (IOException ex) {
            Logger.getLogger(DesignController.class.getName()).log(Level.SEVERE, null, ex);
        }

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
    private void switchProfil(ActionEvent event) {
    }

    @FXML
    private void switchPsy(ActionEvent event) {
    }

    @FXML
    private void switchNutrition(ActionEvent event) {
    }

    @FXML
    private void switchSport(ActionEvent event) {
    }

    @FXML
    private void switchRdv(ActionEvent event) {
        setNode(Appointments);
    }

    @FXML
    private void Disconnect(ActionEvent event) {
    }

   

}
