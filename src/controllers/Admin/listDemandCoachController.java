/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers.Admin;

import Enitities.Coach;
import Service.serviceUser;
import aymen.gui.Mail;
import java.awt.Desktop;
import java.awt.event.MouseEvent;
import java.io.File;
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
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.util.Callback;

/**
 *
 * @author NGONGANG Loic F
 */
public class listDemandCoachController implements Initializable{

    @FXML
    private AnchorPane listDemandCoachPane;
    @FXML
    private TableView<Coach> listDemandTable;
    @FXML
    private TableColumn<Coach, String> nameCol;
    @FXML
    private TableColumn<Coach, String> surnameCol;
    @FXML
    private TableColumn<Coach, String> emailCol;
    @FXML
    private TableColumn<Coach, String> specialiteCol;
    @FXML
    private TableColumn<Coach, String> dateInsCol;
    @FXML
    private TableColumn<Coach, Void> docCol;
    @FXML
    private TableColumn<Coach, Void> actionsCol;
    
    ObservableList<Coach> listCoach = FXCollections.observableArrayList();
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        loadData();
    } 
    
    private void btnBackHome(ActionEvent event) throws IOException{
           FXMLLoader loader = new FXMLLoader();
        loader.setLocation( getClass().getResource("/views/admin/homeAdmin.fxml") ) ;
        Parent itemUpdateViewParent = loader.load();
        Scene homeViewScene = new Scene( itemUpdateViewParent ) ;
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene( homeViewScene );
        window.show();
    }
    

    private void loadData(){
        Service.serviceUser su = new serviceUser();
        nameCol.setCellValueFactory(new PropertyValueFactory<>("nom"));
        surnameCol.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        emailCol.setCellValueFactory(new PropertyValueFactory<>("email"));
        specialiteCol.setCellValueFactory(new PropertyValueFactory<>("specialite"));
        dateInsCol.setCellValueFactory(new PropertyValueFactory<>("dateInscription"));
        
        docCol.setCellFactory(param -> new TableCell<Coach, Void>() {
            private final ImageView viewFile = new ImageView("/ressources/images/viewFile.png");
            private final HBox pane = new HBox(viewFile);
            {
                viewFile.setFitHeight(30);
                viewFile.setFitWidth(30);
                viewFile.setPreserveRatio(true);
                pane.setAlignment(Pos.CENTER);
                
                viewFile.setOnMouseClicked(event -> {
                Coach c = getTableView().getItems().get(getIndex());
                File file = new File("C:\\xampp\\htdocs\\moveat2\\public\\upload\\fichiers\\"+c.getJustificatif());
                    try {
                        Desktop.getDesktop().open(file);
                    } catch (IOException ex) {
                        Logger.getLogger(listDemandCoachController.class.getName()).log(Level.SEVERE, null, ex);
                    }
            });
            }
            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);

                setGraphic(empty ? null : pane);
            }
        });
        
        actionsCol.setCellFactory(param -> new TableCell<Coach, Void>() {
        private final ImageView viewImg = new ImageView("/ressources/images/view.png");
        private final ImageView deleteImg = new ImageView("/ressources/images/delete.png");
        private final ImageView validateImg = new ImageView("/ressources/images/valid.png");
        private final HBox pane = new HBox(viewImg,validateImg,deleteImg);

        {
            viewImg.setFitHeight(30);
            viewImg.setFitWidth(30);
            viewImg.setPreserveRatio(true);
            deleteImg.setFitHeight(30);
            deleteImg.setFitWidth(30);
            deleteImg.setPreserveRatio(true);
            validateImg.setFitHeight(30);
            validateImg.setFitWidth(30);
            validateImg.setPreserveRatio(true);
            pane.setAlignment(Pos.CENTER);
             deleteImg.setOnMouseClicked(event -> {
                Coach c = getTableView().getItems().get(getIndex());
                Alert alert = new Alert(AlertType.CONFIRMATION, "Supprimer la demande d'inscription de " + c.getNom()+" "+c.getPrenom()+ " ?", ButtonType.YES, ButtonType.NO, ButtonType.CANCEL);
                alert.showAndWait();
                if (alert.getResult() == ButtonType.YES) {
                     su.deleteUser(c.getIdUser());
                     loadData();
                     Mail mail = new Mail();
                      mail.sendMail(c.getEmail(), "Refus de votre demande d'inscription", ""
                              + "Votre demande a été refusée. Vérifier vos informations et réessayez", "ngongangloic151@gmail.com", "lenovoa5600");
                }
            });

            validateImg.setOnMouseClicked(event -> {
                 Coach c = getTableView().getItems().get(getIndex());
                 Alert alert = new Alert(AlertType.CONFIRMATION, "Valider la demande d'inscription de " + c.getNom()+" "+c.getPrenom()+ " ?", ButtonType.YES, ButtonType.NO, ButtonType.CANCEL);
                 alert.showAndWait();
                  if (alert.getResult() == ButtonType.YES) {
                     su.validCoach(c.getIdUser());
                     loadData();
                      Mail mail = new Mail();
                      mail.sendMail(c.getEmail(), "Validation de votre inscription", "Votre inscription a été validé. Vous pouvez vous connecter maintenant.", "ngongangloic151@gmail.com", "lenovoa5600");
                }
            });
            
             viewImg.setOnMouseClicked(event -> {
                 Coach c = getTableView().getItems().get(getIndex());
                 Coach coach = su.selectCoach(c);
                try {
                    FXMLLoader loader = new FXMLLoader();
                    loader.setLocation( getClass().getResource("/views/admin/viewProfileDemandCoach.fxml") ) ;
                    Parent itemUpdateViewParent = loader.load();
                    Scene homeViewScene = new Scene( itemUpdateViewParent ) ;
                    viewProfileDemandCoachController controller = (viewProfileDemandCoachController)loader.getController();
                     try {
                         controller.initializeData( coach );                 
                         Stage window = new Stage();
                         window.setTitle("Informations Demande Coach");
                        window.setScene( homeViewScene );
                        window.show();
                     } catch (Exception ex) {
                         Logger.getLogger(listDemandCoachController.class.getName()).log(Level.SEVERE, null, ex);
                     }
//                    AnchorPane pane = FXMLLoader.load(getClass().getResource("/views/coach/registerCoach.fxml"));
//                    listDemandCoachPane.getChildren().setAll(pane);
                } catch (IOException ex) {
                    Logger.getLogger(listDemandCoachController.class.getName()).log(Level.SEVERE, null, ex);
                }
            });
        }

            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);

                setGraphic(empty ? null : pane);
            }
        });
        listCoach = FXCollections.observableArrayList(su.listDemandCoach());
        listDemandTable.setItems(listCoach);
        
    }

    private void refreshTable(ActionEvent event) {
         listCoach.clear();
        Service.serviceUser su = new serviceUser();
        listCoach = FXCollections.observableArrayList(su.listDemandCoach());
        listDemandTable.setItems(listCoach);
    }

    @FXML
    private void refresh(javafx.scene.input.MouseEvent event) {
         loadData();
    }
    
    
}
