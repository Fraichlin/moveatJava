/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aymen.gui;

import Enitities.ProgrammeTable;
import Utils.MyConnexion;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import java.sql.* ;
import java.util.Optional;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.cell.PropertyValueFactory;
/**
 *
 * @author ASUS
 */
public class FXMLDocumentController implements Initializable {
    
    private Label label;
    @FXML
    private TableView<ProgrammeTable> TableProgramme;
    @FXML
    private TableColumn<ProgrammeTable, String > col_id;
    @FXML
    private TableColumn<ProgrammeTable, String> col_programme;
    
   
    Connection connection ;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;
    ProgrammeTable tabView = null;
 
    ObservableList<ProgrammeTable>  TabViewList = FXCollections.observableArrayList();

    private void handleButtonAction(ActionEvent event) {
        System.out.println("You clicked me!");
        label.setText("Hello World!");
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       loadTableau() ;
    }    
 private void loadTableau() {
 
       this.connection = MyConnexion.getInstance().getConnection();
        try {
            TabViewList = FXCollections.observableArrayList();
           String rq = "SELECT * FROM `programme` " ;
                      
            ResultSet rs = connection.createStatement().executeQuery(rq);

            while (rs.next()) {
                TabViewList.add(
                new ProgrammeTable(rs.getInt(1) , rs.getString(2))
                );
                
            this.col_id.setCellValueFactory(new PropertyValueFactory<ProgrammeTable,String>("id"));
            this.col_programme.setCellValueFactory(new PropertyValueFactory<ProgrammeTable,String>("prog"));
            this.TableProgramme.setItems(null);
            this.TableProgramme.setItems(this.TabViewList);
            }
        }catch (SQLException ex) {
            System.err.println("Error" + ex);
        }

 }
    @FXML
    private void Ajouter(ActionEvent event) {
        
         try {
            Parent parent = FXMLLoader.load(getClass().getResource("/aymen/gui/Add.fxml"));
            Scene scene = new Scene(parent);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.initStyle(StageStyle.UTILITY);
            stage.show();
        } catch (IOException ex) {
            System.out.println(ex);
        }
         
    }
    @FXML
    private void Delete(ActionEvent event)  throws SQLException {
        
        
         boolean choix0 = TableProgramme.getSelectionModel().isEmpty();

        if(!choix0) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmation de suppression !!");
            alert.setHeaderText(null);
            alert.setContentText("Voulez-vous vraiment de supprimer cette ligne");
            Optional<ButtonType> action = alert.showAndWait();

            if (action.get() == ButtonType.OK) {
                ProgrammeTable tab = TableProgramme.getSelectionModel().getSelectedItem();
             String   querry = "DELETE FROM `programme` WHERE id =" + tab.getId() ;
                connection = MyConnexion.getInstance().getConnection();
                PreparedStatement ps = connection.prepareStatement(querry);
                ps.execute();

                System.out.println("Supprimer avec success  !!!");

             
            }
        }
        else if (choix0) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Selectionner");
            alert.setHeaderText(null);
            alert.setContentText("Selectionner une ligne ");
            alert.showAndWait();
        }
    }
    @FXML
    private void Update(ActionEvent event) {
            boolean choix = TableProgramme.getSelectionModel().isEmpty();


            if(!choix) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmation de votre modification ");
            alert.setHeaderText(null);
            alert.setContentText("Voulez-vous vraiment de modifier cette ligne ??");
            Optional<ButtonType> action = alert.showAndWait();

            if (action.get() == ButtonType.OK) {


                try {

                    FXMLLoader loader = new FXMLLoader();
                    loader.setLocation(getClass().getResource("/aymen/gui/Update.fxml"));
                    loader.load();

                    ProgrammeTable tab2 = TableProgramme.getSelectionModel().getSelectedItem();
                    UpdateController updateController = loader.getController();


                    updateController.text( tab2.getId() , tab2.getProg()) ;

                    Parent parent = loader.getRoot();
                    Scene scene = new Scene(parent);
                    Stage stage = new Stage();
                    stage.setScene(scene);
                    stage.initStyle(StageStyle.UTILITY);
                    stage.show();

                } catch (IOException ex) {
                    System.out.println(ex);
                }
            }
        }  else if (choix) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Selectionner");
                alert.setHeaderText(null);
                alert.setContentText("Selectionner une ligne ");
                alert.showAndWait();
            }

    }

    @FXML
    private void refresh(ActionEvent event) {
        loadTableau();
    }

    @FXML
    private void close(ActionEvent event) {
        System.exit(0);
    }

    @FXML
    private void check(ActionEvent event) {
         boolean choix = TableProgramme.getSelectionModel().isEmpty();


            if(!choix) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
           // alert.setTitle("Confirmation de votre modification ");
            alert.setHeaderText(null);
            
            Optional<ButtonType> action = alert.showAndWait();

            if (action.get() == ButtonType.OK) {


                try {

                    FXMLLoader loader = new FXMLLoader();
                    loader.setLocation(getClass().getResource("/aymen/gui/Consult.fxml"));
                    loader.load();

                  

                    Parent parent = loader.getRoot();
                    Scene scene = new Scene(parent);
                    Stage stage = new Stage();
                    stage.setScene(scene);
                    stage.initStyle(StageStyle.UTILITY);
                    stage.show();

                } catch (IOException ex) {
                    System.out.println(ex);
                }
            }
        }  else if (choix) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Selectionner");
                alert.setHeaderText(null);
                alert.setContentText("Selectionner une ligne ");
                alert.showAndWait();
            }
    }
    }
    

