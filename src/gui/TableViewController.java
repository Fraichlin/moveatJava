/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;


import entities.Appointment;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;

import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;

import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

import javafx.stage.Stage;
import javafx.stage.StageStyle;

import tools.MaConnexion;

/**
 * FXML Controller class
 *
 * @author acer
 */
public class TableViewController implements Initializable {

    @FXML
    private TableView<Appointment> tvappointments;
    @FXML
    private TableColumn<Appointment, String> colnom;
    @FXML
    private TableColumn<Appointment, String> colprenom;
    @FXML
    private TableColumn<Appointment, String> colemail;
    @FXML
    private TableColumn<Appointment, Integer> coltel;
    @FXML
    private TableColumn<Appointment, String> coldate;
    @FXML
    private TableColumn<Appointment, String> coltime;
    @FXML
    private TableColumn<Appointment, String> colmessage;
    
    String query = null;
    Connection connection = null ;
    PreparedStatement preparedStatement = null ;
    ResultSet resultSet = null ;
    Appointment appointment = null ;
    
    Connection cnx;
    PreparedStatement ste;

    public TableViewController() {
    cnx = MaConnexion.getinstance().getCnx();
    
    }
    
    
    ObservableList<Appointment>  AppointmentList = FXCollections.observableArrayList();
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    
    
    
    

    @FXML
    private void close(MouseEvent event) {
        Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }

    @FXML
    private void getAddView(MouseEvent event) {
         try {
            Parent parent = FXMLLoader.load(getClass().getResource("MakeAppointment.fxml"));
            Scene scene = new Scene(parent);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.initStyle(StageStyle.UTILITY);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(TableViewController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void refreshTable() {
         try {
            AppointmentList.clear();
            
            query = "SELECT * FROM `appointment`";
            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();
            
            while (resultSet.next()){
                AppointmentList.add(new  Appointment(
                        
                        resultSet.getString("nom"),
                        resultSet.getString("prenom"),
                        resultSet.getString("email"),
                        resultSet.getInt("tel"),
                        resultSet.getString("date"),
                        resultSet.getString("time"),
                        resultSet.getString("message")));
               tvappointments.setItems(AppointmentList);
                
            }
            
            
        } catch (SQLException ex) {
            Logger.getLogger(TableViewController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    @FXML
    private void print(MouseEvent event) {
    }
    
    
    private void loadDate() {
        
        
        refreshTable();
        
        
        colnom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        colprenom.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        colemail.setCellValueFactory(new PropertyValueFactory<>("email"));
        coltel.setCellValueFactory(new PropertyValueFactory<>("tel"));
        coldate.setCellValueFactory(new PropertyValueFactory<>("date"));
        coltime.setCellValueFactory(new PropertyValueFactory<>("time"));
        colmessage.setCellValueFactory(new PropertyValueFactory<>("message"));    
        
      
    
    
    }


}
