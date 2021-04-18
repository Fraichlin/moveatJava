/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers.Admin;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;


/**
 * FXML Controller class
 *
 * @author NGONGANG Loic F
 */
public class homeController implements Initializable {

  
    @FXML
    private AnchorPane homeAdminPane;
    @FXML
    private Button btnListDemand;
    @FXML
    private Button btnListCoach;
    @FXML
    private Button btnListMember;
     @FXML
    private Button btnBackHome;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    @FXML
    private void listCoach(ActionEvent event) throws IOException {
         AnchorPane pane = FXMLLoader.load(getClass().getResource("/views/admin/listCoach.fxml"));
          homeAdminPane.getChildren().setAll(pane);
    }
    @FXML
    private void listMember(ActionEvent event) throws IOException{
         AnchorPane pane = FXMLLoader.load(getClass().getResource("/views/admin/listMember.fxml"));
          homeAdminPane.getChildren().setAll(pane);
    }
    @FXML
    private void listDemandCoach(ActionEvent event) throws IOException{
         AnchorPane pane = FXMLLoader.load(getClass().getResource("/views/admin/listDemandCoach.fxml"));
          homeAdminPane.getChildren().setAll(pane);
    }
    @FXML
    private void btnBackHome(ActionEvent event) throws IOException{
         AnchorPane pane = FXMLLoader.load(getClass().getResource("/moveat/FXMLDocument.fxml"));
          homeAdminPane.getChildren().setAll(pane);
    }

}
