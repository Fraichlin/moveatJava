/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aymen.gui.controller.nutritioniste;

import Enitities.NutritionalProgram;
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
 * FXML Controller class
 *
 * @author ASUS
 */
public class TableViewController implements Initializable {

    private Label label;
    @FXML
    private Button add;
    @FXML
    private Button delete;
    @FXML
    private Button update;
    @FXML
    private TableColumn<NutritionalProgram, String> col_Nom;
    @FXML
    private TableColumn<NutritionalProgram, String> col_Type;
    @FXML
    private TableColumn<NutritionalProgram, String> col_Desciption;
    @FXML
    private TableColumn<NutritionalProgram, String> col_Image;
    @FXML
    private TableColumn<NutritionalProgram, String> col_Breakfast;
    @FXML
    private TableColumn<NutritionalProgram, String> col_Lunch;
    @FXML
    private TableColumn<NutritionalProgram, String> col_Dinner;
    @FXML
    private TableView<NutritionalProgram> TableProgramme;

    Connection connection ;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;
    NutritionalProgram tabView = null;
    ObservableList<NutritionalProgram>  TabViewList = FXCollections.observableArrayList();
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       loadTableau() ;
    }
private void loadTableau() {
 
       this.connection = MyConnexion.getInstance().getConnection();
        try {
            TabViewList = FXCollections.observableArrayList();
           String rq = "SELECT * FROM `NutritionalProgram` " ;
                      
            ResultSet rs = connection.createStatement().executeQuery(rq);

            while (rs.next()) {
                TabViewList.add(
                new NutritionalProgram(rs.getString(1) , rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7) )
                );
                
            this.col_Nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
            this.col_Type.setCellValueFactory(new PropertyValueFactory<>("type"));
            this.col_Desciption.setCellValueFactory(new PropertyValueFactory<>("description"));
            this.col_Image.setCellValueFactory(new PropertyValueFactory<>("image"));
            this.col_Breakfast.setCellValueFactory(new PropertyValueFactory<>("breakfast"));
            this.col_Lunch.setCellValueFactory(new PropertyValueFactory<>("lunch"));
            this.col_Dinner.setCellValueFactory(new PropertyValueFactory<>("dinner"));
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
            Parent parent = FXMLLoader.load(getClass().getResource("/aymen/gui/AddP.fxml"));
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
                    loader.setLocation(getClass().getResource("/aymen/gui/UpdateNutritional.fxml"));
                    loader.load();

                    NutritionalProgram tab2 = TableProgramme.getSelectionModel().getSelectedItem();
                    UpdateController updateController = loader.getController();


                    updateController.text( tab2.getNom() , tab2.getType(), tab2.getDescription(), tab2.getImage(), tab2.getBreakfast(), tab2.getLunch(), tab2.getDinner()) ;

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
    private void Delete(ActionEvent event) throws SQLException {
         boolean choix0 = TableProgramme.getSelectionModel().isEmpty();

        if(!choix0) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmation de suppression !!");
            alert.setHeaderText(null);
            alert.setContentText("Voulez-vous vraiment de supprimer cette ligne");
            Optional<ButtonType> action = alert.showAndWait();

            if (action.get() == ButtonType.OK) {
                NutritionalProgram tab = TableProgramme.getSelectionModel().getSelectedItem();
             String   querry = "DELETE FROM `NutritionalProgram` WHERE id =" + tab.getId() ;
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
    private void Refresh(ActionEvent event) {
        loadTableau();
    }
    
}
