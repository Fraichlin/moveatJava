/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;


import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
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
import javafx.geometry.Insets;

import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableCell;

import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;

import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Callback;

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
    @FXML
    private TableColumn <Appointment, String> editcol;
   
    
    Connection cnx;
    PreparedStatement ste;
    
   Appointment appointment=null;

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
        loadDate();
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
            
            String sql  = "SELECT * FROM `appointment`";
            
            ste = cnx.prepareStatement(sql);
            ResultSet rs = ste.executeQuery();
            
            while (rs.next()) {   
                AppointmentList.add(new  Appointment(
                        
                        rs.getString("nom"),
                        rs.getString("prenom"),
                        rs.getString("email"),
                        rs.getInt("tel"),
                        rs.getString("date"),
                        rs.getString("time"),
                        rs.getString("message")));
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
        
        //add cell of button edit 
         Callback<TableColumn<Appointment, String>, TableCell<Appointment, String>> cellFoctory = (TableColumn<Appointment, String> param) -> {
            // make cell containing buttons
            final TableCell<Appointment, String> cell = new TableCell<Appointment, String>() {
                @Override
                public void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);
                    //that cell created only on non-empty rows
                    if (empty) {
                        setGraphic(null);
                        setText(null);

                    } else {

                        FontAwesomeIconView deleteIcon = new FontAwesomeIconView(FontAwesomeIcon.TRASH);
                        FontAwesomeIconView editIcon = new FontAwesomeIconView(FontAwesomeIcon.PENCIL_SQUARE);

                        deleteIcon.setStyle(
                                " -fx-cursor: hand ;"
                                + "-glyph-size:28px;"
                                + "-fx-fill:#ff1744;"
                        );
                        editIcon.setStyle(
                                " -fx-cursor: hand ;"
                                + "-glyph-size:28px;"
                                + "-fx-fill:#00E676;"
                        );
                        deleteIcon.setOnMouseClicked((MouseEvent event) -> {
                            
                            try {
                                appointment = tvappointments.getSelectionModel().getSelectedItem();
                                String sql = "DELETE FROM `appointment` WHERE id  ="+appointment.getId();
                                

                                  ste = cnx.prepareStatement(sql);
                                  ste.execute();
                                
                                refreshTable();
                                
                            } catch (SQLException ex) {
                                Logger.getLogger(TableViewController.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            
                           

                          

                        });
                        editIcon.setOnMouseClicked((MouseEvent event) -> {
                            
                            appointment = tvappointments.getSelectionModel().getSelectedItem();
                            FXMLLoader loader = new FXMLLoader ();
                            loader.setLocation(getClass().getResource("MakeAppointment.fxml"));
                            try {
                                loader.load();
                            } catch (IOException ex) {
                                Logger.getLogger(TableViewController.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            
                            MakeAppointmentController makeAppointmentController = loader.getController();
                            
                            makeAppointmentController.setUpdate(true);
                            
                            makeAppointmentController.setTextField(appointment.getId(), appointment.getNom() , appointment.getPrenom(),
                                    appointment.getEmail(),appointment.getTel(),appointment.getDate(),appointment.getTime(),appointment.getMessage()
                                    
                            );
                                    
                            Parent parent = loader.getRoot();
                            Stage stage = new Stage();
                            stage.setScene(new Scene(parent));
                            stage.initStyle(StageStyle.UTILITY);
                            stage.show();
                            

                           

                        });

                        HBox managebtn = new HBox(editIcon, deleteIcon);
                        managebtn.setStyle("-fx-alignment:center");
                        HBox.setMargin(deleteIcon, new Insets(2, 2, 0, 3));
                        HBox.setMargin(editIcon, new Insets(2, 3, 0, 2));

                        setGraphic(managebtn);

                        setText(null);

                    }
                }

            };

            return cell;
        };
         editcol.setCellFactory(cellFoctory);
         tvappointments.setItems(AppointmentList);
         
        
      
    
    
    }


}
