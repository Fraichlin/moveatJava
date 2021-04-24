/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers.Admin;

import Enitities.Coach;
import Enitities.Member;
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
    private TableColumn<Member, String> dateInsCol;
    @FXML
    private TableColumn<Member, Void> actionsCol;
    @FXML
    private Button btnBackHome;
    @FXML
    private Button refreshBtn;
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
        dateInsCol.setCellValueFactory(new PropertyValueFactory<>("dateInscription"));
        
        actionsCol.setCellFactory(param -> new TableCell<Member, Void>() {
       
        private final Button viewButton = new Button("Visualiser");
        private final Button blockButton = new Button("Bloquer");
        private final Button deleteButton = new Button("Supprimer");
        private final HBox pane = new HBox(viewButton,blockButton,deleteButton);

        {
           
            deleteButton.setOnAction(event -> {
                Member m = getTableView().getItems().get(getIndex());
                if("blocked".equals(m.getStatut()))blockButton.setText("Débloquer");
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Supprimer le membre" + m.getNom()+" "+m.getPrenom()+ " ?", ButtonType.YES, ButtonType.NO, ButtonType.CANCEL);
                alert.showAndWait();
                if (alert.getResult() == ButtonType.YES) {
                     su.deleteUser(m.getIdUser());
                     loadData();
                }
            });

            blockButton.setOnAction(event -> {
                  Member m = getTableView().getItems().get(getIndex());
                 if("blocked".equals(m.getStatut()))blockButton.setText("Débloquer");
                 if("blocked".equals(m.getStatut())){
                     Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Débloquer le membre " + m.getNom()+" "+m.getPrenom()+ " ?", ButtonType.YES, ButtonType.NO, ButtonType.CANCEL);
                     alert.showAndWait();
                  if (alert.getResult() == ButtonType.YES) {
                     su.unblockUser(m.getIdUser());
                     loadData();
                }
                 }
                    else{
                      Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Bloquer le membre " + m.getNom()+" "+m.getPrenom()+ " ?", ButtonType.YES, ButtonType.NO, ButtonType.CANCEL);
                      alert.showAndWait();
                  if (alert.getResult() == ButtonType.YES) {
                     su.blockUser(m.getIdUser());
                     loadData();
                }
                 }              
                 
            });
            
             viewButton.setOnAction(event -> {
                 Member m = getTableView().getItems().get(getIndex());
                 if("blocked".equals(m.getStatut()))blockButton.setText("Débloquer");
                try {
                    FXMLLoader loader = new FXMLLoader();
                    loader.setLocation( getClass().getResource("/views/admin/viewProfileMember.fxml") ) ;
                    Parent itemUpdateViewParent = loader.load();
                    Scene homeViewScene = new Scene( itemUpdateViewParent ) ;
                    viewProfileMemberController controller = (viewProfileMemberController)loader.getController();
                     try {
                         controller.initializeData( m );                 
                         Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
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
    @FXML
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
    private void refreshTable(ActionEvent event) {
    }
    
}
