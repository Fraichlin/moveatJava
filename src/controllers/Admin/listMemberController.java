/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers.Admin;

import Enitities.Coach;
import Enitities.Member;
import Service.serviceUser;
import aymen.gui.Mail;
import java.io.IOException;
import java.net.URL;
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
public class listMemberController implements Initializable {

    @FXML
    private AnchorPane listMemberPane;
    @FXML
    private TableView<Member> listTableMember;
    @FXML
    private TableColumn<Member, String> nameCol;
    @FXML
    private TableColumn<Member, String> surnameCol;
    @FXML
    private TableColumn<Member, String> emailCol;
    @FXML
    private TableColumn<Member, String> sexeCol;
    @FXML
    private TableColumn<Member, String> statutCol;
    @FXML
    private TableColumn<Member, String> dateInsCol;
    @FXML
    private TableColumn<Member, Void> actionsCol;
    ObservableList<Member> listMember = FXCollections.observableArrayList();
    

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
        sexeCol.setCellValueFactory(new PropertyValueFactory<>("sexe"));
        statutCol.setCellValueFactory(new PropertyValueFactory<>("statut"));
        dateInsCol.setCellValueFactory(new PropertyValueFactory<>("dateInscription"));
        
        actionsCol.setCellFactory(param -> new TableCell<Member, Void>() {
       
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
            deleteImg.setOnMouseClicked(event->{
               Member m = getTableView().getItems().get(getIndex());
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Supprimer le membre " + m.getNom()+" "+m.getPrenom()+ " ?", ButtonType.YES, ButtonType.NO, ButtonType.CANCEL);
                alert.showAndWait();
                if (alert.getResult() == ButtonType.YES) {
                     su.deleteUser(m.getIdUser());
                     loadData();
                     Mail mail = new Mail();
                      mail.sendMail(m.getEmail(), "Suppression de votre compte", ""
                              + "Votre compte a été supprimé. Vous ne pouvez plus vous connecter.", "ngongangloic151@gmail.com", "lenovoa5600");
                }
            });
            
            blockImg.setOnMouseClicked(event->{
                Member m = getTableView().getItems().get(getIndex());
                      Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Bloquer le membre " + m.getNom()+" "+m.getPrenom()+ " ?", ButtonType.YES, ButtonType.NO, ButtonType.CANCEL);
                      alert.showAndWait();
                 if (alert.getResult() == ButtonType.YES) {
                   su.blockUser(m.getIdUser());
                  
                   loadData();
                   Mail mail = new Mail();
                      mail.sendMail(m.getEmail(), "Blocage de votre compte", ""
                              + "Votre compte a été bloqué. Vous ne pouvez plus vous connecter. Vous recevrez un mail en cas de déblocage.", "ngongangloic151@gmail.com", "lenovoa5600");
                }
                if("blocked".equals(m.getStatut())){
                    pane.getChildren().remove(blockImg);
                if(!pane.getChildren().contains(unblockImg))pane.getChildren().add(unblockImg);
                }    
            });
            
            unblockImg.setOnMouseClicked(event->{
                Member m = getTableView().getItems().get(getIndex());
                     Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Débloquer le membre " + m.getNom()+" "+m.getPrenom()+ " ?", ButtonType.YES, ButtonType.NO, ButtonType.CANCEL);
                     alert.showAndWait();
                  if (alert.getResult() == ButtonType.YES) {
                     su.unblockUser(m.getIdUser());
                     loadData();      
                     Mail mail = new Mail();
                      mail.sendMail(m.getEmail(), "Déblocage de votre compte", ""
                               + "Votre compte a été débloqué. Vous pouvez vous connecter maintenant.", "ngongangloic151@gmail.com", "lenovoa5600");
                    }
                  if("unblocked".equals(m.getStatut())){
                     pane.getChildren().remove(unblockImg);
                     if(!pane.getChildren().contains(blockImg))pane.getChildren().add(blockImg);
                  }
            });
            
            viewImg.setOnMouseClicked(event->{
                Member m = getTableView().getItems().get(getIndex());
                try {
                    FXMLLoader loader = new FXMLLoader();
                    loader.setLocation( getClass().getResource("/views/admin/viewProfileMember.fxml") ) ;
                    Parent itemUpdateViewParent = loader.load();
                    Scene homeViewScene = new Scene( itemUpdateViewParent ) ;
                    viewProfileMemberController controller = (viewProfileMemberController)loader.getController();
                     try {
                         controller.initializeData( m );                 
                         Stage window = new Stage();
                         window.setTitle("Profile Membre");
                        window.setScene( homeViewScene );
                        window.show();
                     } catch (Exception ex) {
                         Logger.getLogger(listMemberController.class.getName()).log(Level.SEVERE, null, ex);
                     }
//                    AnchorPane pane = FXMLLoader.load(getClass().getResource("/views/coach/registerCoach.fxml"));
//                    listDemandCoachPane.getChildren().setAll(pane);
                } catch (IOException ex) {
                    Logger.getLogger(listMemberController.class.getName()).log(Level.SEVERE, null, ex);
                }
            });
            
        }

            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);

                setGraphic(empty ? null : pane);
            }
        });
        listMember = FXCollections.observableArrayList(su.listMember());
        listTableMember.setItems(listMember);
    }
    private void btnBackHome(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation( getClass().getResource("/views/admin/homeAdmin.fxml") ) ;
        Parent itemUpdateViewParent = loader.load();
        Scene homeViewScene = new Scene( itemUpdateViewParent ) ;
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene( homeViewScene );
        window.show();
    }

    @FXML
    private void refresh(MouseEvent event) {
         loadData();
    }

    
}
