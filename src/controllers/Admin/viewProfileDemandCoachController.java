/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers.Admin;

import Enitities.Coach;
import Service.serviceUser;
import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
/**
 * FXML Controller class
 *
 * @author NGONGANG Loic F
 */
public class viewProfileDemandCoachController implements Initializable {

    @FXML
    private Button btnBackHome;
    @FXML
    private Label lbName;
    @FXML
    private Label lbSurname;
    @FXML
    private Label lbEmail;
    @FXML
    private Label lbSex;
    @FXML
    private Label lbTel;
    @FXML
    private Label lbAdress;
    @FXML
    private Label lbSpecialite;
    @FXML
    private Label lbDateins;
    @FXML
    private Button btnFile;
    @FXML
    private Button btnValid;
    @FXML
    private Button btnDelete;
    @FXML
    private AnchorPane viewDemandCoachPane;
    Service.serviceUser su = new serviceUser();
    private int id;
    Coach coach;
    @FXML
    private ImageView imageView;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
     
    }
    
    
     public void initializeData(Coach c) throws Exception{
         System.out.println(c.getSexe()+" "+c.getTel()+" "+c.getAdresse());
         id = c.getIdUser();
         coach = c;
         lbName.setText(c.getNom());
         lbSurname.setText(c.getPrenom());
         lbEmail.setText(c.getEmail());
         lbSex.setText(c.getSexe());
         lbTel.setText(c.getTel());
         lbAdress.setText(c.getAdresse());
         lbSpecialite.setText(c.getSpecialite());
         lbDateins.setText(c.getDateInscription().toString());
         imageView.setImage(new Image("/ressources/images/"+c.getPhoto()));
     }
    

    @FXML
    private void btnBackHome(ActionEvent event) throws IOException{
        AnchorPane pane = FXMLLoader.load(getClass().getResource("/views/admin/listDemandCoach.fxml"));
          viewDemandCoachPane.getChildren().setAll(pane);
    }

    @FXML
    private void openFile(ActionEvent event) throws IOException{
      File file = new File("D:\\projetsCodename\\moveat\\src\\ressources\\fichiers\\"+coach.getJustificatif());
      Desktop.getDesktop().open(file);
    }

    @FXML
    private void validDemand(ActionEvent event) {
         Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Valider la demande d'inscription ?", ButtonType.YES, ButtonType.NO, ButtonType.CANCEL);
                alert.showAndWait();
                if (alert.getResult() == ButtonType.YES) {
                     su.validCoach(id);
                }
    }

    @FXML
    private void deleteDemand(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Supprimer la demande d'inscription ?", ButtonType.YES, ButtonType.NO, ButtonType.CANCEL);
                alert.showAndWait();
                if (alert.getResult() == ButtonType.YES) {
                     su.deleteUser(id);
                }
    }
    
}
