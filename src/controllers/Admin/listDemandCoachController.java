/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers.Admin;

import Enitities.Coach;
import Service.serviceUser;
import java.awt.event.MouseEvent;
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
    private Button btnBackHome;
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
    private TableColumn<Coach, String> docCol;
    @FXML
    private TableColumn<Coach, Void> actionsCol;
    @FXML
    private Button refreshBtn;
    
    ObservableList<Coach> listCoach = FXCollections.observableArrayList();
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        loadData();
    } 
    
    @FXML
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
        docCol.setCellValueFactory(new PropertyValueFactory<>("justificatif"));
        
        actionsCol.setCellFactory(param -> new TableCell<Coach, Void>() {
        private final Button viewButton = new Button("Visualiser");
        private final Button validButton = new Button("Valider");
        private final Button deleteButton = new Button("Supprimer");
        private final HBox pane = new HBox(viewButton,validButton,deleteButton);

        {
            deleteButton.setOnAction(event -> {
                Coach c = getTableView().getItems().get(getIndex());
                Alert alert = new Alert(AlertType.CONFIRMATION, "Supprimer la demande d'inscription de " + c.getNom()+" "+c.getPrenom()+ " ?", ButtonType.YES, ButtonType.NO, ButtonType.CANCEL);
                alert.showAndWait();
                if (alert.getResult() == ButtonType.YES) {
                     su.deleteUser(c.getIdUser());
                     loadData();
                }
            });

            validButton.setOnAction(event -> {
                 Coach c = getTableView().getItems().get(getIndex());
                 Alert alert = new Alert(AlertType.CONFIRMATION, "Valider la demande d'inscription de " + c.getNom()+" "+c.getPrenom()+ " ?", ButtonType.YES, ButtonType.NO, ButtonType.CANCEL);
                 alert.showAndWait();
                  if (alert.getResult() == ButtonType.YES) {
                     su.validCoach(c.getIdUser());
                     loadData();
                }
            });
            
             viewButton.setOnAction(event -> {
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
                         Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
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

    @FXML
    private void refreshTable(ActionEvent event) {
         listCoach.clear();
        Service.serviceUser su = new serviceUser();
        listCoach = FXCollections.observableArrayList(su.listDemandCoach());
        listDemandTable.setItems(listCoach);
    }
    
    
}
