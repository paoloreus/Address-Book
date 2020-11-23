/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package comp2130_assignment2;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


public class COMP2130_Assignment2 extends Application {
    
    Button add;
    Button delete;
    Button edit;
    Button viewAll;
    Button find;
    Button view;
    Scene home;
    
    @Override
    public void start(Stage window) {
        window.setTitle("Star Wars Address Book");
        
        //setting up Add section
        add = new Button("Add");
        Label lblAdd = new Label("Add new contact:");
        lblAdd.setMinSize(140, 10);
        HBox layAdd = new HBox(20);
        layAdd.getChildren().addAll(lblAdd, add);
        add.setMinSize(100, 5);
        
        //setting up delete section
        delete = new Button("Delete");
        Label lblDel = new Label("Delete contact:");
        lblDel.setMinSize(140, 10);
        HBox layDel = new HBox(20);
        layDel.getChildren().addAll(lblDel, delete);
        delete.setMinSize(100, 5);
        
        //setting up edit section
        edit = new Button("Edit");
        Label lblEd = new Label("Edit contact:");
        lblEd.setMinSize(140, 10);
        HBox layEd = new HBox(20);
        layEd.getChildren().addAll(lblEd, edit);
        edit.setMinSize(100, 5);
        
        //setting up viewall section
        viewAll = new Button("View All");
        Label lblViewAll = new Label("View all contacts:");
        lblViewAll.setMinSize(140, 10);
        HBox layAll = new HBox(20);
        layAll.getChildren().addAll(lblViewAll, viewAll);
        viewAll.setMinSize(100, 5);
        
        //setting up find section
        find = new Button("Find");
        Label lblFind = new Label("Find contact:");
        lblFind.setMinSize(140, 10);
        HBox layFind = new HBox(20);
        layFind.getChildren().addAll(lblFind, find);
        find.setMinSize(100, 5);
        
        //setting up view by city section
        view = new Button("View");
        Label lblCity = new Label("View contacts by city:");
        lblCity.setMinSize(120, 10);
        HBox layCity = new HBox(20);
        layCity.getChildren().addAll(lblCity, view);
        view.setMinSize(100, 5);
        
        //setting up home layout
        Label lblHome = new Label("Welcome to our Address Book");        
        VBox homeLayout = new VBox(30);
        homeLayout.getChildren().addAll(lblHome, layAdd, layDel, layEd, layAll, layFind, layCity);
        
        home = new Scene(homeLayout, 500, 500);
        
        window.setScene(home);
        window.show();
    }

   
    public static void main(String[] args) {
        launch(args);
    }
    
}
