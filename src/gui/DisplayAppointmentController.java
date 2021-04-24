/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entities.Appointment;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author acer
 */
public class DisplayAppointmentController implements Initializable {

    @FXML
    private TableView<Appointment> tvappointments;
    @FXML
    private TableColumn<Appointment, Integer> colid;
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
    @FXML
    private Button btninsert;
    @FXML
    private Button btndelete;
    @FXML
    private Button btnupdate;
    
    
    String query = null;
    Connection connection = null ;
    PreparedStatement preparedStatement = null ;
    ResultSet resultSet = null ;
    Appointment appointment = null ;
    
    ObservableList<Appointment>  StudentList = FXCollections.observableArrayList();
    @FXML
    
    
    private TextField txtaff;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
    }    
    
    public void setTxtaff(String txtaff) {
        this.txtaff.setText(txtaff);
    }
    
    public void showAppointments(){
    
    }
    
   @FXML
    private void btndelete(ActionEvent event) {
    } 
            
            
            
            
            
            
}
