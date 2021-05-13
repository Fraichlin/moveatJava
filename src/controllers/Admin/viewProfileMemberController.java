/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers.Admin;

import Enitities.Member;
import Service.serviceUser;
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
public class viewProfileMemberController implements Initializable {

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
    private Label lbPoids;
    @FXML
    private Label lbTaille;
    @FXML
    private Label lbDateins;
    @FXML
    private Button btnBlock;
    @FXML
    private Button btnDelete;
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
    Member member;
    Service.serviceUser su = new serviceUser(); 
    @FXML
    private AnchorPane viewProfileMemberPane;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    public void initializeData(Member m) throws Exception{
        id = m.getIdUser();
        member = m;
        lbName.setText(m.getNom());
        lbSurname.setText(m.getPrenom());
        lbUsername.setText(m.getUsername());
        lbEmail.setText(m.getEmail());
        lbSex.setText(m.getSexe());
        lbPoids.setText(m.getPoids());
        lbTaille.setText(m.getTaille());
        lbStatut.setText(m.getStatut());
        lbDateins.setText(m.getDateInscription().toString());
        if(m.getDateModification()==null){
            lbDatemod.setText("/");
        }else lbDatemod.setText(m.getDateModification().toString());
        if(m.getDateBlocage()==null){
            lbDateblock.setText("/");
        }else lbDateblock.setText(m.getDateBlocage().toString());
        if(m.getDateDeblocage()==null){
            lbDateunblock.setText("/");
        }else lbDateunblock.setText(m.getDateDeblocage().toString());
        imageView.setImage(new Image("file:C:\\xampp\\htdocs\\moveat2\\public\\upload\\images\\"+m.getPhoto()));
        
    }

    @FXML
    private void blockMember(ActionEvent event) throws Exception {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Bloquer le membre ?", ButtonType.YES, ButtonType.NO, ButtonType.CANCEL);
                alert.showAndWait();
                if (alert.getResult() == ButtonType.YES) {
                     su.blockUser(id);
                     initializeData(member);
                }
    }

    @FXML
    private void deleteMember(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Supprimer le membre ?", ButtonType.YES, ButtonType.NO, ButtonType.CANCEL);
                alert.showAndWait();
                if (alert.getResult() == ButtonType.YES) {
                     su.deleteUser(id);
                }
    }

    @FXML
    private void unblockMember(ActionEvent event) throws Exception {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Débloquer le membre ?", ButtonType.YES, ButtonType.NO, ButtonType.CANCEL);
                alert.showAndWait();
                if (alert.getResult() == ButtonType.YES) {
                     su.unblockUser(id);
                     initializeData(member);
                }
    }
    
}
