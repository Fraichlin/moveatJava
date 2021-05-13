/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers.Admin;

import Enitities.Coach;
import Service.serviceUser;
import Utils.globalMethods;
import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author NGONGANG Loic F
 */
public class viewProfileCoachController implements Initializable {

    @FXML
    private ImageView imageView;
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
    private Button btnDelete;
    @FXML
    private Label lbDateval;
    @FXML
    private Label lbDatemod;
    @FXML
    private Label lbDateblock;
    @FXML
    private Label lbDateunblock;
    @FXML
    private Label lbUsername;
    @FXML
    private Label lbStatut;
    @FXML
    private Button btnUnblock;
    private int id;
    Coach coach;
    @FXML
    private AnchorPane viewProfileCoachPane;
     Service.serviceUser su = new serviceUser();
    @FXML
    private Button btnBlock;
    globalMethods g = new globalMethods();
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
    
    public void initializeData(Coach c) throws Exception{
            System.out.println(c.getSexe()+" "+c.getTel()+" "+c.getAdresse());
            id = c.getIdUser();
            coach = c;
            lbName.setText(c.getNom());
            lbSurname.setText(c.getPrenom());
            lbUsername.setText(c.getUsername());
            lbEmail.setText(c.getEmail());
            lbSex.setText(c.getSexe());
            lbTel.setText(c.getTel());
            lbAdress.setText(c.getAdresse());
            lbSpecialite.setText(c.getSpecialite());
            lbStatut.setText(c.getStatut());
            lbDateins.setText(c.getDateInscription().toString());
            if(c.getDateModification()==null){
                lbDatemod.setText("/");
            }else lbDatemod.setText(c.getDateModification().toString());
            if(c.getDateValidation()==null){
                lbDateval.setText("/");
            }else lbDateval.setText(c.getDateValidation().toString());
            if(c.getDateBlocage()==null){
                lbDateblock.setText("/");
            }else lbDateblock.setText(c.getDateBlocage().toString());
            if(c.getDateDeblocage()==null){
                lbDateunblock.setText("/");
            }else lbDateunblock.setText(c.getDateDeblocage().toString());
            imageView.setImage(new Image("file:C:\\xampp\\htdocs\\moveat2\\public\\upload\\images\\"+c.getPhoto()));
        }    

    @FXML
    private void openFile(ActionEvent event) throws IOException{
         File file = new File("C:\\xampp\\htdocs\\moveat2\\public\\upload\\fichiers\\"+coach.getJustificatif());
      Desktop.getDesktop().open(file);
    }

    @FXML
    private void blockCoach(ActionEvent event) throws Exception {
         Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Bloquer le coach ?", ButtonType.YES, ButtonType.NO, ButtonType.CANCEL);
                alert.showAndWait();
                if (alert.getResult() == ButtonType.YES) {
                     su.blockUser(id);
                     initializeData(coach);
                }
    }

    @FXML
    private void deleteCoach(ActionEvent event) {
         Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Supprimer le coach ?", ButtonType.YES, ButtonType.NO, ButtonType.CANCEL);
                alert.showAndWait();
                if (alert.getResult() == ButtonType.YES) {
                     su.deleteUser(id);
                }
    }

    @FXML
    private void unblockCoach(ActionEvent event) throws Exception {
         Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Débloquer le coach ?", ButtonType.YES, ButtonType.NO, ButtonType.CANCEL);
                alert.showAndWait();
                if (alert.getResult() == ButtonType.YES) {
                     su.unblockUser(id);
                     initializeData(coach);
                }
    }

    
}
