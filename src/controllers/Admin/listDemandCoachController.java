/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers.Admin;

import Enitities.Coach;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

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
    private TableColumn<Coach, String> actionCol;
    
    ObservableList<Coach> coach = FXCollections.observableArrayList();
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        loadData();
    } 
    
    @FXML
    private void btnBackHome(ActionEvent event) throws IOException{
         AnchorPane pane = FXMLLoader.load(getClass().getResource("views/admin/homeAdmin.fxml"));
          listDemandCoachPane.getChildren().setAll(pane);
    }
    
     @FXML
    private void refreshTable(MouseEvent event) throws IOException{
         AnchorPane pane = FXMLLoader.load(getClass().getResource("views/admin/homeAdmin.fxml"));
          listDemandCoachPane.getChildren().setAll(pane);
    }

    private void loadData() {
        nameCol.setCellValueFactory(new PropertyValueFactory<>("nom"));
        surnameCol.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        emailCol.setCellValueFactory(new PropertyValueFactory<>("email"));
        specialiteCol.setCellValueFactory(new PropertyValueFactory<>("specialite"));
        
    }
    
}
