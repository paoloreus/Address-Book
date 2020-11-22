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
        add = new Button("Add Contact");
        Label lblAdd = new Label("Add new contact to the list");
        HBox layAdd = new HBox(20);
        layAdd.getChildren().addAll(add, lblAdd);
        add.setMinSize(170, 20);
        
        //setting up delete section
        delete = new Button("Delete Contact");
        Label lblDel = new Label("Delete contact from the list");
        HBox layDel = new HBox(20);
        layDel.getChildren().addAll(delete, lblDel);
        delete.setMinSize(170, 20);
        
        //setting up edit section
        edit = new Button("Edit Contact");
        Label lblEd = new Label("Edit an existing account");
        HBox layEd = new HBox(20);
        layEd.getChildren().addAll(edit, lblEd);
        edit.setMinSize(170, 20);
        
        //setting up viewall section
        viewAll = new Button("View All Contacts");
        Label lblViewAll = new Label("View all existing contacts in the list");
        HBox layAll = new HBox(20);
        layAll.getChildren().addAll(viewAll, lblViewAll);
        viewAll.setMinSize(170, 20);
        
        //setting up find section
        find = new Button("Find Contact");
        Label lblFind = new Label("Display info of an existing contact");
        HBox layFind = new HBox(20);
        layFind.getChildren().addAll(find, lblFind);
        find.setMinSize(170, 20);
        
        //setting up view by city section
        view = new Button("View Contacts in a City");
        Label lblCity = new Label("View a list of contacts within a city");
        HBox layCity = new HBox(20);
        layCity.getChildren().addAll(view, lblCity);
        
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
