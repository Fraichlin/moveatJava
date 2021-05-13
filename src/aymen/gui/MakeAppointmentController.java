/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aymen.gui;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTimePicker;

import Enitities.Appointment;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import Service.AppointmentService;


/**
 * FXML Controller class
 *
 * @author acer
 */
public class MakeAppointmentController implements Initializable {

    @FXML
    private TextField tfnom;
    @FXML
    private TextField tfprenom;
    @FXML
    private TextField tfemail;
    @FXML
    private TextField tftel;
    @FXML
    private TextArea tamessage;
    @FXML
    private Button btnenvoyer;
    @FXML
    private JFXDatePicker tfdate;
    @FXML
    private JFXTimePicker tfheure;
    @FXML
    private JFXButton btnPayment;
    @FXML
    private JFXButton btnrota;
    
private boolean update;
 int appointmentId;
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void addAppointment(ActionEvent event) {
        
//        try {
            String nom = tfnom.getText();
            String prenom = tfprenom.getText();
            String email = tfemail.getText();
            int tel = Integer.parseInt(tftel.getText());
            String message = tamessage.getText();
            String date = String.valueOf(tfdate.getValue());
            String time = String.valueOf(tfheure.getValue());
            
            
            Appointment a = new Appointment(nom, prenom,email,tel,date,time,message);
            AppointmentService as = new AppointmentService();
            as.addAppointment(a);
            infoBox("Demande de RDV envoyée "+appointmentId,null,"Success" );
            
//            FXMLLoader loader = new FXMLLoader(getClass().getResource("DisplayAppointment.fxml"));
//            Parent root = loader.load();
//            DisplayAppointmentController dac = loader.getController();
//            dac.setTxtaff(as.afficherAppointment().toString());
//            tfnom.getScene().setRoot(root);
            
//        } catch (IOException ex) {
//            System.out.println(ex.getMessage());
//        }
     
  }

    public static void infoBox(String infoMessage, String headerText, String title){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText(infoMessage);
        alert.setTitle(title);
        alert.setHeaderText(headerText);
        alert.showAndWait();
    }
    
    @FXML
    private void switchPayment(ActionEvent event) {
        try {
            FXMLLoader fxmlloader =new FXMLLoader(getClass().getResource("/aymen/gui/Payment.fxml"));
            Parent root1=(Parent) fxmlloader.load();
            Stage stage =new Stage();
            stage.setTitle("Payment");
            stage.setScene(new Scene(root1));
            stage.show();
            
           
        } catch (IOException ex) {
            Logger.getLogger(NewFXMain.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
   
    
 

     void setTextField(int id, String nom, String prenom, String email, int tel, String date, String time, String message) {

        appointmentId = id;
        tfnom.setText(nom);
        tfprenom.setText(prenom);
        tfemail.setText(email);
//        tftel.setText(tel.getindex().toString());
        tamessage.setText(message);
//        tfdate.setValue(toLocalDate);
//        tfheure.setValue(heure);
        
    }

    void setUpdate(boolean b) {
        this.update = b;

    }

    @FXML
    private void switchRotation(ActionEvent event) {
        try {
            FXMLLoader fxmlloader =new FXMLLoader(getClass().getResource("Rotation.fxml"));
            Parent root1=(Parent) fxmlloader.load();
            Stage stage =new Stage();
            stage.setTitle("How it works");
            stage.setScene(new Scene(root1));
            stage.show();
            
           
        } catch (IOException ex) {
            Logger.getLogger(NewFXMain.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    
    
    
    
    
    
    
    
    
}
