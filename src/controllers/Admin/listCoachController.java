/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers.Admin;

import Enitities.Coach;
import Service.serviceUser;
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
    private TableColumn<Coach, String> dateInsCol;
    @FXML
    private TableColumn<Coach, Void> actionsCol;
    @FXML
    private Button btnBackHome;
    @FXML
    private Button refreshBtn;
    ObservableList<Coach> listCoach = FXCollections.observableArrayList();
    @FXML
    private ImageView img1;

    /**
     * Initializes the controller class.
     */
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
        
        actionsCol.setCellFactory(param -> new TableCell<Coach, Void>() {
       
        private final Button viewButton = new Button("Visualiser");
        private final Button blockButton = new Button("Bloquer");
        private final Button deleteButton = new Button("Supprimer");
        private final HBox pane = new HBox(viewButton,blockButton,deleteButton);

        {
           
            deleteButton.setOnAction(event -> {
                Coach c = getTableView().getItems().get(getIndex());
                System.out.println(c.getDateValidation());
                if("blocked".equals(c.getStatut()))blockButton.setText("Débloquer");
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Supprimer le coach " + c.getNom()+" "+c.getPrenom()+ " ?", ButtonType.YES, ButtonType.NO, ButtonType.CANCEL);
                alert.showAndWait();
                if (alert.getResult() == ButtonType.YES) {
                     su.deleteUser(c.getIdUser());
                     loadData();
                }
            });

            blockButton.setOnAction(event -> {
                 Coach c = getTableView().getItems().get(getIndex());
                 if("blocked".equals(c.getStatut()))blockButton.setText("Débloquer");
                 if("blocked".equals(c.getStatut())){
                     Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Débloquer le coach " + c.getNom()+" "+c.getPrenom()+ " ?", ButtonType.YES, ButtonType.NO, ButtonType.CANCEL);
                     alert.showAndWait();
                  if (alert.getResult() == ButtonType.YES) {
                     su.unblockUser(c.getIdUser());
                     loadData();
                }
                 }
                    else{
                      Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Bloquer le coach " + c.getNom()+" "+c.getPrenom()+ " ?", ButtonType.YES, ButtonType.NO, ButtonType.CANCEL);
                      alert.showAndWait();
                  if (alert.getResult() == ButtonType.YES) {
                     su.blockUser(c.getIdUser());
                     loadData();
                }
                 }              
                 
            });
            
             viewButton.setOnAction(event -> {
                 Coach c = getTableView().getItems().get(getIndex());
                 if("blocked".equals(c.getStatut()))blockButton.setText("Débloquer");
                try {
                    FXMLLoader loader = new FXMLLoader();
                    loader.setLocation( getClass().getResource("/views/admin/viewProfileCoach.fxml") ) ;
                    Parent itemUpdateViewParent = loader.load();
                    Scene homeViewScene = new Scene( itemUpdateViewParent ) ;
                    viewProfileCoachController controller = (viewProfileCoachController)loader.getController();
                     try {
                         controller.initializeData( c );                 
                         Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
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

    @FXML
    private void refreshTable(ActionEvent event) {
    }

    @FXML
    private void test(MouseEvent event) {
        System.out.println("test");
    }
    
}
