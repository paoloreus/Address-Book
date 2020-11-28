/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package comp2130_assignment2;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;
import static javax.swing.text.StyleConstants.Italic;


public class COMP2130_Assignment2 extends Application implements EventHandler<ActionEvent>{
    
    Button btnAdd;
    Button btnDelete;
    Button btnEdit;
    Button btnViewAll;
    Button btnFind;
    Button btnView;
    
    
    Scene home;
    Scene addPage;
    Scene view;
    Scene search;
    Stage window;
    Scene viewOne;
    
    ContactManager cm;
    Contact c;
    Address address;
    MyDate dateBirth;
    
    @Override
    public void start(Stage primaryStage) {
        cm = new ContactManager(1000);
        window = primaryStage;
        window.setTitle("Star Wars Address Book");
        window.setOnCloseRequest(e -> {
            e.consume();
            closeProgram();
        });
        
        //setting up Add section
        btnAdd = new Button("Add");
        btnAdd.setOnAction(this);
        Label lblAdd = new Label("Add new contact:");
        lblAdd.setMinSize(140, 10);
        HBox layAdd = new HBox(20);
        layAdd.getChildren().addAll(lblAdd, btnAdd);
        btnAdd.setMinSize(100, 5);
        
        //setting up delete section
        btnDelete = new Button("Delete");
        Label lblDel = new Label("Delete contact:");
        lblDel.setMinSize(140, 10);
        HBox layDel = new HBox(20);
        layDel.getChildren().addAll(lblDel, btnDelete);
        btnDelete.setMinSize(100, 5);
        
        //setting up edit section
        btnEdit = new Button("Edit");
        Label lblEd = new Label("Edit contact:");
        lblEd.setMinSize(140, 10);
        HBox layEd = new HBox(20);
        layEd.getChildren().addAll(lblEd, btnEdit);
        btnEdit.setMinSize(100, 5);
        btnEdit.setOnAction(e -> display(window, true, cm));
        
        //setting up viewall section
        btnViewAll = new Button("View All");
        Label lblViewAll = new Label("View all contacts:");
        lblViewAll.setMinSize(140, 10);
        HBox layAll = new HBox(20);
        layAll.getChildren().addAll(lblViewAll, btnViewAll);
        btnViewAll.setMinSize(100, 5);
        btnViewAll.setOnAction(this);
        
        //setting up find section
        btnFind = new Button("Find");
        btnFind.setOnAction(e -> search(window, cm));
        Label lblFind = new Label("Find contact:");
        lblFind.setMinSize(140, 10);
        HBox layFind = new HBox(20);
        layFind.getChildren().addAll(lblFind, btnFind);
        btnFind.setMinSize(100, 5);
        
        //setting up view by city section
        btnView = new Button("View");
        Label lblCity = new Label("View contacts by city:");
        lblCity.setMinSize(120, 10);
        HBox layCity = new HBox(20);
        layCity.getChildren().addAll(lblCity, btnView);
        btnView.setMinSize(100, 5);
        
        //setting up home layout
        Label lblHome = new Label("Welcome to our Address Book");        
        VBox homeLayout = new VBox(30);
        homeLayout.getChildren().addAll(lblHome, layAdd, layDel, layEd, layAll, layFind, layCity);
        
        home = new Scene(homeLayout, 500, 500);
        
        window.setScene(home);
        window.show();
    }
    
    public void closeProgram(){
    
            Alert alert = new Alert(AlertType.CONFIRMATION);
            alert.setContentText("Are you sure you want to exit the application?");
            alert.showAndWait();
            if(alert.getResult() == ButtonType.OK){
            Alert confirm = new Alert(AlertType.INFORMATION);
            confirm.setContentText("Thank you for using Star Wars Address Book!");
            confirm.showAndWait();
            window.close();
            }
    }

    
    public void handle(ActionEvent e){
        
        if(e.getSource() == btnAdd){
            addPage(window, false, c);
        }
        
        if(e.getSource() == btnViewAll){
            
            display(window, false, cm);
            
        }
        
        
        
       
        
      
    }
    
    public boolean isValid(TextField txt){
        if(txt.getText() == null || txt.getText().trim().isEmpty() || txt.getText().length() < 1){
        return false;
        }
        
        return true;
        
    }
    
    public boolean isValidNumeric(TextField txt, int type){
        
         if(txt.getText() == null || txt.getText().trim().isEmpty() || txt.getText().length() < 1){
        return false;
        }
        
        if(type == 2){ 
        if(!txt.getText().matches("-?\\d+(\\.\\d+)?")){
            return false;
        }
        }
        
        else if(type == 1){
             if(txt.getText().matches("-?\\d+(\\.\\d+)?")){
            return false;
        }
        }
         
         return true;
    }
    
    public void addPage(Stage window, boolean isEditable, Contact c){
        
        //setting up first name
        Label firstName = new Label("First Name:");
        firstName.setMinSize(140, 10);
        TextField txtFirst = new TextField();
        if(isEditable){txtFirst.setText(c.getFirstName());}
        HBox layFirst = new HBox(20);
        layFirst.getChildren().addAll(firstName, txtFirst);
        
        //setting up last name
        Label lastName = new Label("Last Name:");
        lastName.setMinSize(140, 10);
        TextField txtLast = new TextField();
        if(isEditable){txtLast.setText(c.getLastName());}
        HBox laySecond = new HBox(20);
        laySecond.getChildren().addAll(lastName, txtLast);
        
        //setting up home phone
        Label phone = new Label("Home Phone:");
        phone.setMinSize(140, 10);
        TextField txtPhone = new TextField();
        if(isEditable){txtPhone.setText(c.getHomePhone());}
        HBox layPhone = new HBox(20);
        layPhone.getChildren().addAll(phone, txtPhone);
        //if(txtPhone.getText() == null || txtPhone.getText().trim().isEmpty()){isValid = false;}
        
        //setting up work phone
        Label work = new Label("Work Phone:");
        work.setMinSize(140, 10);
        TextField txtWork = new TextField();
        if(isEditable){txtWork.setText(c.getWorkPhone());}
        HBox layWork = new HBox(20);
        layWork.getChildren().addAll(work, txtWork);
        
        //setting up street address1
        Label home1 = new Label("Street Address 1:");
        home1.setMinSize(140, 10);
        TextField txtAddress1 = new TextField();
        if(isEditable){txtAddress1.setText(c.getHomeAddress().streetInfo1);}
        HBox layAddress1 = new HBox(20);
        layAddress1.getChildren().addAll(home1, txtAddress1);
        
        //setting up street address2
        Label home2 = new Label("Street Address 2:");
        home2.setMinSize(140, 10);
        TextField txtAddress2 = new TextField();
        if(isEditable){txtAddress2.setText(c.getHomeAddress().streetInfo2);}
        HBox layAddress2 = new HBox(20);
        layAddress2.getChildren().addAll(home2, txtAddress2);
        
        //setting up city 
        Label city = new Label("City:");
        city.setMinSize(140, 10);
        TextField txtCity = new TextField();
        if(isEditable){txtCity.setText(c.getHomeAddress().city);}
        HBox layCity = new HBox(20);
        layCity.getChildren().addAll(city, txtCity);
        
        //setting up postal code
        Label postalCode = new Label("Postal Code:");
        postalCode.setMinSize(140, 10);
        TextField txtPostal = new TextField();
        if(isEditable){txtPostal.setText(c.getHomeAddress().postalCode);}
        HBox layPostal = new HBox(20);
        layPostal.getChildren().addAll(postalCode, txtPostal);
        
        //setting up province
        Label province = new Label("Province:");
        province.setMinSize(140, 10);
        TextField txtProvince = new TextField();
        if(isEditable){txtProvince.setText(c.getHomeAddress().province);}
        HBox layProvince = new HBox(20);
        layProvince.getChildren().addAll(province, txtProvince);
        
        //setting up country
        Label country = new Label("Country:");
        country.setMinSize(140, 10);
        TextField txtCountry = new TextField();
        if(isEditable){txtCountry.setText(c.getHomeAddress().country);}
        HBox layCountry = new HBox(20);
        layCountry.getChildren().addAll(country, txtCountry);
        
        //setting up email
        Label email = new Label("Email:");
        email.setMinSize(140, 10);
        TextField txtEmail = new TextField();
        if(isEditable){txtEmail.setText(c.getEmail());}
        HBox layEmail = new HBox(20);
        layEmail.getChildren().addAll(email, txtEmail);
        
        //setting up birthday
        Label birthday = new Label("Date of Birth:");
        TextField txtYear = new TextField();
        if(isEditable){txtYear.setText(Integer.toString(c.getBirthday().getYear()));}
        txtYear.setPromptText("Year");
        TextField txtMonth = new TextField();
        if(isEditable){txtMonth.setText(Integer.toString(c.getBirthday().getMonth()));}
        txtMonth.setPromptText("Month");
        TextField txtDay = new TextField();
        if(isEditable){txtDay.setText(Integer.toString(c.getBirthday().getDay()));}
        txtDay.setPromptText("Day");
        
        //DatePicker txtBirthday = new DatePicker();
        //LocalDate getBirthday = txtBirthday.getValue();
        //Calendar cal = Calendar.getInstance();
        
        birthday.setMinSize(100, 10);
        //TextField txtBirthday = new TextField();
        HBox layBirthday = new HBox(20);
        layBirthday.getChildren().addAll(birthday, txtDay, txtMonth, txtYear);
        
        //setting up notes
        Label notes = new Label("Additional Notes:");
        notes.setMinSize(140, 10);
        TextField txtNotes = new TextField();
        HBox layNotes = new HBox(20);
        layNotes.getChildren().addAll(notes, txtNotes);
        
        Label message = new Label("Add New Contact");
        if(isEditable){message.setText("Manage Contact");}
        message.setFont(Font.font(30));
        Button btnSubmit = new Button("Submit");
        Button btnBack = new Button("Back");
        HBox laySubmit = new HBox(5);
        laySubmit.setAlignment(Pos.CENTER);
        laySubmit.getChildren().addAll(btnSubmit, btnBack);
        
        VBox layout = new VBox(10);
        layout.getChildren().addAll(message, layFirst, laySecond, layPhone, layWork, layAddress1, layAddress2, layCity, layPostal,
        layProvince, layCountry, layEmail, layBirthday, layNotes, laySubmit);
        
        addPage = new Scene(layout, 400, 630);
        
        btnBack.setOnAction( e -> window.setScene(home));
        
         //setting up submit
         btnSubmit.setOnAction(e ->{
             
         if(isValidNumeric(txtFirst, 1) && isValidNumeric(txtLast, 1) && isValidNumeric(txtPhone, 2)
           && isValid(txtAddress1) && isValidNumeric(txtCity, 1)
           && isValid(txtPostal) && isValidNumeric(txtProvince, 1) && isValidNumeric(txtCountry, 1)
           && isValid(txtEmail) && isValidNumeric(txtYear, 2)
           && isValidNumeric(txtMonth, 2) && isValidNumeric(txtDay, 2)){
        //making a new address object using the address related input
        address = new Address(txtAddress1.getText(), txtAddress2.getText(), txtCity.getText(), txtPostal.getText(),
         txtProvince.getText(), txtCountry.getText());
        
        //making a new date of birth object using the data stored in datepicker
        //dateBirth = new MyDate(getBirthday.getDayOfMonth(), getBirthday.getMonthValue() - 1, getBirthday.getYear());
        dateBirth = new MyDate(Integer.parseInt(txtDay.getText()), Integer.parseInt(txtMonth.getText()),
        Integer.parseInt(txtYear.getText()));
        //System.out.println((int) getBirthday.getDayOfMonth());
        
        
        if(!isEditable){
        boolean isSuccess = cm.addContact(txtFirst.getText(), txtLast.getText()
      , txtPhone.getText(), txtWork.getText(), address, txtEmail.getText(), dateBirth,
        txtNotes.getText());
       
            if(isSuccess){
           Alert alert = new Alert(AlertType.INFORMATION);     
           alert.setContentText("Success");
           alert.showAndWait();
           window.setScene(home);
           
       }
             }
        
        else {
            c.setFirstName(txtFirst.getText());
            c.setLastName(txtLast.getText());
            c.setHomePhone(txtPhone.getText());
            c.setWorkPhone(txtWork.getText());
            c.setHomeAddress(address);
            c.setEmail(txtEmail.getText());
            c.setBirthday(dateBirth);
            c.setNotes(txtNotes.getText());
            
            Alert alert = new Alert(AlertType.CONFIRMATION);
            alert.setContentText("Would you like to submit changes?");
            alert.showAndWait();
            if(alert.getResult() == ButtonType.OK){
                Alert confirm = new Alert(AlertType.INFORMATION);
                confirm.setContentText("Changes Applied!");
                confirm.showAndWait();
                window.setScene(home);
            }
        }
        
         }
         else {
             Alert alert = new Alert(AlertType.ERROR);
             alert.setContentText("Please make sure all inputs are valid");
             alert.showAndWait();
             
         }
             
                 
               
            //window.setScene(home);
                });
        
        window.setScene(addPage);
        
               
    }
    
    
    public void display(Stage window, boolean isEditable, ContactManager cm) {
    
        
        VBox[] layVert = new VBox[cm.getNumContacts()];
     for(int i = 0; i < cm.getNumContacts(); i++){
         Contact cont = cm.getContacts()[i];
    //setting up name
    Label lblfullName = new Label("Contact Name:");    
    Label lblName = new Label(cm.getContacts()[i].getFirstName() + " " + cm.getContacts()[i].getLastName());
    lblfullName.setMinSize(140, 10);
    lblName.setMinSize(140, 10);
    HBox layName = new HBox(20);
    layName.getChildren().addAll(lblfullName, lblName);
    Button btnManage = new Button("Manage Contact");
    btnManage.setVisible(isEditable);
    btnManage.setOnAction(e -> addPage(window, true, cont));
    
    //setting up birthdays
    Label lblBday = new Label("Date of Birth:");
    Label lblBirthday = new Label(cm.getContacts()[i].getBirthday().getMonthLongForm() + " " +
            cm.getContacts()[i].getBirthday().getDay() + ", " + cm.getContacts()[i].getBirthday().getYear());
    lblBday.setMinSize(140, 10);
    lblBirthday.setMinSize(140, 10);
    HBox layBirthday = new HBox(20);
    layBirthday.getChildren().addAll(lblBday, lblBirthday); 
    
    layVert[i] = new VBox(10);
    layVert[i].getChildren().addAll(layName, layBirthday, btnManage);
    
     }
     
     Button btnBack = new Button("Back");
     btnBack.setOnAction(e -> window.setScene(home));
     
     VBox layDisplay = new VBox(10);   
     layDisplay.getChildren().addAll(layVert);
     
     VBox layBack = new VBox(10);
     layBack.setAlignment(Pos.BOTTOM_CENTER);
     layBack.getChildren().add(btnBack);
     layDisplay.getChildren().add(layBack);
      
     view = new Scene(layDisplay, 500, 500);
     window.setScene(view);
     
     
      }
    
    public void displayOne(Stage window, Contact c){
        
        //setting up full name
        Label lblFullName = new Label("Contact Name:");
        Label lblName = new Label(c.getFirstName() + " " + c.getLastName());
        lblFullName.setMinSize(140, 10);
        lblName.setMinSize(140, 10);
        HBox layName = new HBox(20);
        layName.getChildren().addAll(lblFullName, lblName);
        
        //setting up birthday
        Label lblBday = new Label("Date of Birth:");
        Label lblBirthday = new Label(c.getBirthday().getMonthLongForm() + c.getBirthday().getDay() + ", " +
                c.getBirthday().getYear());
        lblBday.setMinSize(140, 10);
        lblBirthday.setMinSize(140, 10);
        HBox layBday = new HBox(20);
        layBday.getChildren().addAll(lblBday, lblBirthday);
        
        //setting up button
        Button btnManage = new Button("Manage Contact");
        Button btnBack = new Button("Back");
        btnBack.setOnAction( e-> window.setScene(search));
        btnManage.setOnAction(e -> addPage(window, true, c));
        HBox layButton = new HBox(5);
        layButton.getChildren().addAll(btnManage, btnBack);
        
        VBox layout = new VBox(10);
        layout.getChildren().addAll(layName, layBday, layButton);
        
        viewOne = new Scene(layout, 500, 500);
        window.setScene(viewOne);
        
    }
    
    
    public void search(Stage window, ContactManager cm){
        
        //setting up first name
        Label lblFirst = new Label("First Name:");
        TextField txtFirstName = new TextField();
        lblFirst.setMinSize(140, 10);
        HBox layFirstN = new HBox(20);
        layFirstN.getChildren().addAll(lblFirst, txtFirstName);
        
        //setting up last name
        Label lblLastN = new Label("Last Name:");
        TextField txtLastName = new TextField();
        lblLastN.setMinSize(140, 10);
        HBox layLast = new HBox(20);
        layLast.getChildren().addAll(lblLastN, txtLastName);
        
        Button btnSearch = new Button("Search");
        Button btnBack = new Button("Back");
       
       
        HBox layButton = new HBox(5);
        layButton.getChildren().addAll(btnSearch, btnBack);
        layButton.setAlignment(Pos.CENTER);
      
        btnBack.setOnAction(e -> window.setScene(home));
        
        btnSearch.setOnAction(e ->{
         Contact con = cm.findContact(txtFirstName.getText(), txtLastName.getText());
            
        
           //addPage(window, true, con);
           if(con != null){
           displayOne(window, con);
           }
           else {
               Alert alert = new Alert(AlertType.INFORMATION);
               alert.setContentText("Contact Not Found");
               alert.showAndWait();
           }
                });
        
        VBox layout = new VBox(10);
        layout.getChildren().addAll(layFirstN, layLast, layButton);
        
        search = new Scene(layout, 500, 500);
        window.setScene(search);
        
        
      
    }
    
    
    public static void main(String[] args) {
        launch(args);
        
    }
    
   
    
}
