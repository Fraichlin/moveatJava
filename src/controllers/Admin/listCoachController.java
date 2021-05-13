/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers.Admin;

import Enitities.Coach;
import Service.serviceUser;
import aymen.gui.Mail;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author NGONGANG Loic F
 */
public class listCoachController implements Initializable {

    @FXML
    private AnchorPane listCoachPane;
    @FXML
    private TableView<Coach> listTableCoach;
    @FXML
    private TableColumn<Coach, String> nameCol;
    @FXML
    private TableColumn<Coach, String> surnameCol;
    @FXML
    private TableColumn<Coach, String> emailCol;
    @FXML
    private TableColumn<Coach, String> specialiteCol;
    @FXML
    private TableColumn<Coach, String> statutCol;
    @FXML
    private TableColumn<Coach, String> dateInsCol;
    @FXML
    private TableColumn<Coach, Void> actionsCol;
    ObservableList<Coach> listCoach = FXCollections.observableArrayList();
    
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        loadData();
        
    }    

    private void loadData(){
        Service.serviceUser su = new serviceUser();
        nameCol.setCellValueFactory(new PropertyValueFactory<>("nom"));
        surnameCol.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        emailCol.setCellValueFactory(new PropertyValueFactory<>("email"));
        specialiteCol.setCellValueFactory(new PropertyValueFactory<>("specialite"));
        statutCol.setCellValueFactory(new PropertyValueFactory<>("statut"));
        dateInsCol.setCellValueFactory(new PropertyValueFactory<>("dateInscription"));
        actionsCol.setCellFactory(param -> new TableCell<Coach, Void>() {
            
        private final ImageView viewImg = new ImageView("/ressources/images/view.png");
        private final ImageView deleteImg = new ImageView("/ressources/images/delete.png");
        private final ImageView blockImg = new ImageView("/ressources/images/block.png");
        private final ImageView unblockImg = new ImageView("/ressources/images/unblock.png");
        private final HBox pane = new HBox(viewImg,blockImg,unblockImg,deleteImg);

        {
            viewImg.setFitHeight(30);
            viewImg.setFitWidth(30);
            viewImg.setPreserveRatio(true);
            deleteImg.setFitHeight(30);
            deleteImg.setFitWidth(30);
            deleteImg.setPreserveRatio(true);
            blockImg.setFitHeight(30);
            blockImg.setFitWidth(30);
            blockImg.setPreserveRatio(true);
            unblockImg.setFitHeight(30);
            unblockImg.setFitWidth(30);
            unblockImg.setPreserveRatio(true);
            pane.setAlignment(Pos.CENTER);
            blockImg.setOnMouseClicked(event->{
               Coach c = getTableView().getItems().get(getIndex());
              
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Bloquer le coach " + c.getNom()+" "+c.getPrenom()+ " ?", ButtonType.YES, ButtonType.NO, ButtonType.CANCEL);
                alert.showAndWait();
                if (alert.getResult() == ButtonType.YES) {
                   su.blockUser(c.getIdUser());
                  
                   loadData();
                   Mail mail = new Mail();
                      mail.sendMail(c.getEmail(), "Blocage de votre compte", "Votre compte a été bloqué. Vous ne pouvez plus vous connecter."
                              + "Vous recevrez un mail en cas de déblocage", "ngongangloic151@gmail.com", "lenovoa5600");
                }
                if("blocked".equals(c.getStatut())){
                    pane.getChildren().remove(blockImg);
                if(!pane.getChildren().contains(unblockImg))pane.getChildren().add(unblockImg);
                }   
            });
            unblockImg.setOnMouseClicked(event->{
               Coach c = getTableView().getItems().get(getIndex());
                     Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Débloquer le coach " + c.getNom()+" "+c.getPrenom()+ " ?", ButtonType.YES, ButtonType.NO, ButtonType.CANCEL);
                     alert.showAndWait();
                  if (alert.getResult() == ButtonType.YES) {
                     su.unblockUser(c.getIdUser());
                     loadData();  
                      Mail mail = new Mail();
                      mail.sendMail(c.getEmail(), "Déblocage de votre compte", "Votre compte a été débloqué. Vous pouvez vous connecter maintenant.", "ngongangloic151@gmail.com", "lenovoa5600");
                    }
                  if("unblocked".equals(c.getStatut())){
                     pane.getChildren().remove(unblockImg);
                     if(!pane.getChildren().contains(blockImg))pane.getChildren().add(blockImg);
                  }
            });
            deleteImg.setOnMouseClicked(event->{
               Coach c = getTableView().getItems().get(getIndex());
                System.out.println(c.getDateValidation());
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Supprimer le coach " + c.getNom()+" "+c.getPrenom()+ " ?", ButtonType.YES, ButtonType.NO, ButtonType.CANCEL);
                alert.showAndWait();
                if (alert.getResult() == ButtonType.YES) {
                     su.deleteUser(c.getIdUser());
                     loadData();
                     Mail mail = new Mail();
                      mail.sendMail(c.getEmail(), "Suppression de votre compte", ""
                              + "Votre compte a été supprimé. Vous ne pouvez plus vous connecter.", "ngongangloic151@gmail.com", "lenovoa5600");
                }
            });
            viewImg.setOnMouseClicked(event->{
                Coach c = getTableView().getItems().get(getIndex());
                try {
                    FXMLLoader loader = new FXMLLoader();
                    loader.setLocation( getClass().getResource("/views/admin/viewProfileCoach.fxml") ) ;
                    Parent itemUpdateViewParent = loader.load();
                    Scene homeViewScene = new Scene( itemUpdateViewParent ) ;
                    viewProfileCoachController controller = (viewProfileCoachController)loader.getController();
                    try {
                         controller.initializeData( c );                 
                         Stage window = new Stage();
                         window.setTitle("Profile Coach");
                         window.setScene( homeViewScene );
                         window.show();
                     } catch (Exception ex) {
                         Logger.getLogger(listCoachController.class.getName()).log(Level.SEVERE, null, ex);
                     }
//                    AnchorPane pane = FXMLLoader.load(getClass().getResource("/views/coach/registerCoach.fxml"));
//                    listDemandCoachPane.getChildren().setAll(pane);
                } catch (IOException ex) {
                    Logger.getLogger(listCoachController.class.getName()).log(Level.SEVERE, null, ex);
                }
            });
            
//            
        }

            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);

                setGraphic(empty ? null : pane);
            }
        });
        listCoach = FXCollections.observableArrayList(su.listCoach());
        listTableCoach.setItems(listCoach);
    }


    private void test(MouseEvent event) {
        System.out.println("test");
    }

    @FXML
    private void refresh(MouseEvent event) {
        loadData();
    }
    
}
