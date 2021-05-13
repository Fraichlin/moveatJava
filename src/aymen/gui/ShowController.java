/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aymen.gui;

import Enitities.NutritionalProgram;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.print.PrinterJob;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Pagination;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import Utils.MyConnexion;

/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class ShowController implements Initializable {

    @FXML
    private Pagination page;
    @FXML
    private TextField comment;
    @FXML
    private Button comEnvoyer;
    @FXML
    private Button imprimer;
    @FXML
    private Button like;
    @FXML
    private Label nb_like;
    @FXML
    private TextArea comShow;
    @FXML
    private Button home;
    @FXML
    private TableView<NutritionalProgram> tablpro;
    @FXML
    private TableColumn<NutritionalProgram, String> colnom;
    @FXML
    private TableColumn<NutritionalProgram, String> coldes;
    @FXML
    private TableColumn<NutritionalProgram, String> colbreak;
    @FXML
    private TableColumn<NutritionalProgram, String> collun;
    @FXML
    private TableColumn<NutritionalProgram, String> coldinn;

     Connection connection ;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;
    NutritionalProgram tabView = null;
    ObservableList<NutritionalProgram>  TabViewList = FXCollections.observableArrayList();
    @FXML
    private TableColumn<NutritionalProgram, String> coltyp;
    @FXML
    private TableColumn<NutritionalProgram, String> colimg;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       loadTableau() ;
       
         imprimer.setOnAction(event -> {
        PrinterJob job = PrinterJob.createPrinterJob();
        if (job != null) 
        {
       // webEngine.print();
        job.endJob ();
    }
     });
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
                
            this.colnom.setCellValueFactory(new PropertyValueFactory<>("nom"));
            this.coltyp.setCellValueFactory(new PropertyValueFactory<>("type"));
            this.coldes.setCellValueFactory(new PropertyValueFactory<>("description"));
            this.colimg.setCellValueFactory(new PropertyValueFactory<>("image"));
            this.colbreak.setCellValueFactory(new PropertyValueFactory<>("breakfast"));
            this.collun.setCellValueFactory(new PropertyValueFactory<>("lunch"));
            this.coldinn.setCellValueFactory(new PropertyValueFactory<>("dinner"));
            this.tablpro.setItems(null);
            this.tablpro.setItems(this.TabViewList);
            }
        }catch (SQLException ex) {
            System.err.println("Error" + ex);
        }
       
      
        
        

}
                

}
        
        
      

    
    

