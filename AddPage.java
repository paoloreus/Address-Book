
package comp2130_assignment2;

import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.geometry.*;

public class AddPage {
    
    
    public static void display(){
        
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Add Contact");
        //window.setMinWidth(500);
        
        //setting up first name
        Label firstName = new Label("First Name:");
        firstName.setMinSize(140, 10);
        TextField txtFirst = new TextField();
        HBox layFirst = new HBox(20);
        layFirst.getChildren().addAll(firstName, txtFirst);
        
        //setting up last name
        Label lastName = new Label("Last Name:");
        lastName.setMinSize(140, 10);
        TextField txtSecond = new TextField();
        HBox laySecond = new HBox(20);
        laySecond.getChildren().addAll(lastName, txtSecond);
        
        VBox layout = new VBox(10);
        layout.getChildren().addAll(layFirst, laySecond);
        Scene add = new Scene(layout);
        window.setScene(add);
        window.showAndWait();
    }
}
