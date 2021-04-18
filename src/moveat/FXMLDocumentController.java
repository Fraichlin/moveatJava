/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package moveat;

import controllers.CoachController;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;


/**
 *
 * @author NGONGANG Loic F
 */
public class FXMLDocumentController implements Initializable {
    
    @FXML
    private Label label;
    @FXML
    private Button button;
    @FXML
    private Button button1;
    @FXML
    private Button button2;
    @FXML
    private AnchorPane rootPane;
    @FXML
    private Button btnAdmin;
    
    private void handleButtonAction(ActionEvent event) {
        System.out.println("You clicked me!");
        label.setText("Hello World!");
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void registerCoach(ActionEvent event) throws IOException{
          AnchorPane pane = FXMLLoader.load(getClass().getResource("/views/coach/registerCoach.fxml"));
          rootPane.getChildren().setAll(pane);
    }

    @FXML
    private void registerMember(ActionEvent event) throws IOException{
        AnchorPane pane = FXMLLoader.load(getClass().getResource("/views/member/registerMember.fxml"));
          rootPane.getChildren().setAll(pane);
    }

    @FXML
    private void login(ActionEvent event) {
    }

    @FXML
    private void admin(ActionEvent event) throws IOException{
        AnchorPane pane = FXMLLoader.load(getClass().getResource("/views/admin/homeAdmin.fxml"));
          rootPane.getChildren().setAll(pane);
    }
    
}
