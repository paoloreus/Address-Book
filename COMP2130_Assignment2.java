/*
* Name: Paolo Tous      -- ID: 101325245
* Name: Yukina Ishiguro -- ID: 101274311
* Name: Satabdi Sangma  -- ID: 101287632
 */
package comp2130_assignment2;


import javafx.animation.PauseTransition;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Duration;

public class COMP2130_Assignment2 extends Application {
    
    //setting up components
    
    Button btnAdd;
    Button btnDelete;
    Button btnEdit;
    Button btnViewAll;
    Button btnFind;
    Button btnView;

    Scene home;                 //home scene
    Scene addPage;             //scene for when user adds a new contact
    Scene view;               //scene for when user would like to view all contacts
    Scene search;            //scene for when user would like to search for a contact by name        
    Scene viewOne;          //scene to display just one contact based on the search by name
    Scene searchCity;      //scene for when user would like to search for contacts within a city
    Scene viewByCity;     //scene to display a list of contacts based on the search by city
    Scene displayAll;    //scene to display all details 
    
    Stage window;     //main stage

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
        btnAdd.setOnAction(e -> addPage(window, false, c));
        Label lblAdd = new Label("Add new contact:");
        lblAdd.setMinSize(140, 10);
        HBox layAdd = new HBox(20);
        layAdd.getChildren().addAll(lblAdd, btnAdd);
        btnAdd.setMinSize(100, 5);

        //setting up delete section
        btnDelete = new Button("Delete");
        btnDelete.setOnAction(e -> search(window, true, cm));
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
        btnViewAll.setOnAction(e -> display(window, false, cm));

        //setting up find section
        btnFind = new Button("Find");
        btnFind.setOnAction(e -> search(window, false, cm));
        Label lblFind = new Label("Find contact:");
        lblFind.setMinSize(140, 10);
        HBox layFind = new HBox(20);
        layFind.getChildren().addAll(lblFind, btnFind);
        btnFind.setMinSize(100, 5);

        //setting up view by city section
        btnView = new Button("View");
        btnView.setOnAction(e -> searchCity(window, cm));
        Label lblCity = new Label("View contacts by city:");
        lblCity.setMinSize(120, 10);
        HBox layCity = new HBox(20);
        layCity.getChildren().addAll(lblCity, btnView);
        btnView.setMinSize(100, 5);

        //setting up home layout
        Label lblHome = new Label("Welcome to our Address Book");
        lblHome.setFont(Font.font(30));
        VBox homeLayout = new VBox(30);
        homeLayout.getChildren().addAll(lblHome, layAdd, layDel, layEd, layAll, layFind, layCity);

        home = new Scene(homeLayout, 500, 500);

        window.setScene(home);
        window.show();
    }

    //any close request will be redirected to and handled from here
    public void closeProgram() {

        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setContentText("Are you sure you want to exit the application?");
        alert.showAndWait();
        if (alert.getResult() == ButtonType.OK) {
            Alert confirm = new Alert(AlertType.INFORMATION);
            confirm.setContentText("Thank you for using Star Wars Address Book!");
            confirm.showAndWait();
            window.close();
        }
    }

    //this method validates email addresses
    public boolean isValidEmail(TextField txt) {

        if (!isValid(txt)) {
            return false;
        }

        if (!txt.getText().matches("^[a-zA-Z0-9_+&*-]+(?:\\." + "[a-zA-Z0-9_+&*-]+)*@"
                + "(?:[a-zA-Z0-9-]+\\.)+[a-z" + "A-Z]{2,7}$")) {

            return false;
        }

        return true;
    }

    //this method checks if a field is numeric/alphabetic
    public boolean isValidNumeric(TextField txt, int type) {

        //type 1 means numeric field
        //type 2 means alphabet field
        //any other type means it's a field that can have both numbers and alphabets
        if (!isValid(txt)) {
            return false;
        }

        if (type == 2) {
            if (!txt.getText().matches("-?\\d+(\\.\\d+)?")) {
                return false;
            }
        } else if (type == 1) {
            if (txt.getText().matches("-?\\d+(\\.\\d+)?")) {
                return false;
            }
        }

        return true;
    }

    //this method checks if date is valid
    public boolean isValidDate(TextField txt, int type) {

        //type 1 is for day
        //type 2 is for month
        //type 3 is for year
        if (type == 1) {
            if (!txt.getText().matches("\\d{1}|\\d{2}") || Integer.parseInt(txt.getText()) > 31
                    || Integer.parseInt(txt.getText()) < 1) {
                return false;
            }
        } else if (type == 2) {
            if (!txt.getText().matches("\\d{1}|\\d{2}") || Integer.parseInt(txt.getText()) > 12
                    || Integer.parseInt(txt.getText()) < 1) {
                return false;
            }
        } else if (type == 3) {
            if (!txt.getText().matches("\\d{4}|\\d{4}") || Integer.parseInt(txt.getText()) > 2020
                    || Integer.parseInt(txt.getText()) < 1900) {
                return false;
            }
        }

        return true;
    }

    //this method checks if a field is not empty
    public boolean isValid(TextField txt) {
        if (txt.getText() == null || txt.getText().trim().isEmpty() || txt.getText().length() < 1) {
            return false;
        }

        return true;
    }
    
    //validates phone numbers and postal codes
    public boolean isValidPhone(TextField txt, int type) {

        //type 1 is for phone numbers
        //type 2 is for postal codes
        if (type == 1) {
            if (!txt.getText().matches("\\d{10}|\\d{10}")) {
                return false;
            }
        } else if (type == 2) {
            if (txt.getText().length() != 6) {
                return false;
            }
        }

        return true;
    }
    
    //use this to make an error label visible and display the appropriate message
    public void showLimited(Label lbl, String message) {
        //type 1 if alphabetic
        //type 2 if numeric

        lbl.setText(message);
        lbl.setVisible(true);
        PauseTransition visiblePause = new PauseTransition(Duration.seconds(4));

        visiblePause.setOnFinished(e -> lbl.setVisible(false));

        visiblePause.play();

    }
    
    //adding a new contact
    public void addPage(Stage window, boolean isEditable, Contact c) {

        //setting up first name and associated error label
        Label firstName = new Label("First Name*:");
        Label firstErr = new Label();
        firstName.setMinSize(140, 10);
        firstErr.setTextFill(Color.RED);
        firstErr.setVisible(false);
        TextField txtFirst = new TextField();
        txtFirst.setMinSize(187, 10);
        if (isEditable) {
            txtFirst.setText(c.getFirstName());
        }
        HBox layFirst = new HBox(20);
        layFirst.getChildren().addAll(firstName, txtFirst, firstErr);

        //setting up last name and associated error label
        Label lastName = new Label("Last Name*:");
        Label lastErr = new Label();
        lastName.setMinSize(140, 10);
        lastErr.setTextFill(Color.RED);
        lastErr.setVisible(false);
        TextField txtLast = new TextField();
        if (isEditable) {
            txtLast.setText(c.getLastName());
        }
        HBox laySecond = new HBox(20);
        laySecond.getChildren().addAll(lastName, txtLast, lastErr);

        //setting up home phone
        Label phone = new Label("Home Phone*:");
        Label phoneErr = new Label();
        phone.setMinSize(140, 10);
        phoneErr.setTextFill(Color.RED);
        phoneErr.setVisible(false);
        TextField txtPhone = new TextField();
        if (isEditable) {
            txtPhone.setText(c.getHomePhone());
        }
        HBox layPhone = new HBox(20);
        layPhone.getChildren().addAll(phone, txtPhone, phoneErr);

        //setting up work phone
        Label work = new Label("Work Phone:");
        work.setMinSize(140, 10);
        TextField txtWork = new TextField();
        if (isEditable) {
            txtWork.setText(c.getWorkPhone());
        }
        HBox layWork = new HBox(20);
        layWork.getChildren().addAll(work, txtWork);

        //setting up street address1
        Label home1 = new Label("Street Address 1*:");
        Label addressErr = new Label();
        home1.setMinSize(140, 10);
        addressErr.setTextFill(Color.RED);
        addressErr.setVisible(false);
        TextField txtAddress1 = new TextField();
        if (isEditable) {
            txtAddress1.setText(c.getHomeAddress().streetInfo1);
        }
        HBox layAddress1 = new HBox(20);
        layAddress1.getChildren().addAll(home1, txtAddress1, addressErr);

        //setting up street address2
        Label home2 = new Label("Street Address 2:");
        home2.setMinSize(140, 10);
        TextField txtAddress2 = new TextField();
        if (isEditable) {
            txtAddress2.setText(c.getHomeAddress().streetInfo2);
        }
        HBox layAddress2 = new HBox(20);
        layAddress2.getChildren().addAll(home2, txtAddress2);

        //setting up city 
        Label city = new Label("City*:");
        Label cityErr = new Label();
        city.setMinSize(140, 10);
        cityErr.setTextFill(Color.RED);
        cityErr.setVisible(false);
        TextField txtCity = new TextField();
        if (isEditable) {
            txtCity.setText(c.getHomeAddress().city);
        }
        HBox layCity = new HBox(20);
        layCity.getChildren().addAll(city, txtCity, cityErr);

        //setting up postal code
        Label postalCode = new Label("Postal Code*:");
        Label postalErr = new Label();
        postalCode.setMinSize(140, 10);
        postalErr.setTextFill(Color.RED);
        postalErr.setVisible(false);
        TextField txtPostal = new TextField();
        if (isEditable) {
            txtPostal.setText(c.getHomeAddress().postalCode);
        }
        HBox layPostal = new HBox(20);
        layPostal.getChildren().addAll(postalCode, txtPostal, postalErr);

        //setting up province
        Label province = new Label("Province*:");
        Label provinceErr = new Label();
        province.setMinSize(140, 10);
        provinceErr.setTextFill(Color.RED);
        provinceErr.setVisible(false);
        TextField txtProvince = new TextField();
        if (isEditable) {
            txtProvince.setText(c.getHomeAddress().province);
        }
        HBox layProvince = new HBox(20);
        layProvince.getChildren().addAll(province, txtProvince, provinceErr);

        //setting up country
        Label country = new Label("Country*:");
        Label countryErr = new Label();
        country.setMinSize(140, 10);
        countryErr.setTextFill(Color.RED);
        countryErr.setVisible(false);
        TextField txtCountry = new TextField();
        if (isEditable) {
            txtCountry.setText(c.getHomeAddress().country);
        }
        HBox layCountry = new HBox(20);
        layCountry.getChildren().addAll(country, txtCountry, countryErr);

        //setting up email
        Label email = new Label("Email*:");
        Label emailErr = new Label();
        email.setMinSize(140, 10);
        emailErr.setTextFill(Color.RED);
        emailErr.setVisible(false);
        TextField txtEmail = new TextField();
        if (isEditable) {
            txtEmail.setText(c.getEmail());
        }
        HBox layEmail = new HBox(20);
        layEmail.getChildren().addAll(email, txtEmail, emailErr);

        //setting up birthday
        Label birthday = new Label("Date of Birth*:");
        TextField txtYear = new TextField();
        txtYear.setMaxSize(70, 10);
        Label bdayErr = new Label();
        bdayErr.setTextFill(Color.RED);
        bdayErr.setVisible(false);
        if (isEditable) {
            txtYear.setText(Integer.toString(c.getBirthday().getYear()));
        }
        txtYear.setPromptText("Year");
        TextField txtMonth = new TextField();
        txtMonth.setMaxSize(70, 10);
        if (isEditable) {
            txtMonth.setText(Integer.toString(c.getBirthday().getMonth()));
        }
        txtMonth.setPromptText("Month");
        TextField txtDay = new TextField();
        txtDay.setMaxSize(70, 10);
        if (isEditable) {
            txtDay.setText(Integer.toString(c.getBirthday().getDay()));
        }
        txtDay.setPromptText("Day");

        //DatePicker txtBirthday = new DatePicker();
        //LocalDate getBirthday = txtBirthday.getValue();
        //Calendar cal = Calendar.getInstance();
        birthday.setMinSize(100, 10);
        HBox layBirthday = new HBox(20);
        layBirthday.getChildren().addAll(birthday, txtDay, txtMonth, txtYear, bdayErr);

        //setting up notes
        Label notes = new Label("Additional Notes:");
        notes.setMinSize(140, 10);
        TextField txtNotes = new TextField();
        HBox layNotes = new HBox(20);
        layNotes.getChildren().addAll(notes, txtNotes);

        Label message = new Label("Add New Contact");
        Label note = new Label("Note: (Fields that contain asterisk (*) are required fields)");
        if (isEditable) {
            message.setText("Manage Contact");
        }
        message.setFont(Font.font(30));
        Button btnSubmit = new Button("Submit");
        Button btnBack = new Button("Back");
        HBox laySubmit = new HBox(5);
        laySubmit.setAlignment(Pos.BASELINE_LEFT);
        laySubmit.getChildren().addAll(btnSubmit, btnBack);

        VBox layout = new VBox(10);
        layout.getChildren().addAll(message, layFirst, laySecond, layPhone, layWork, layAddress1, layAddress2, layCity, layPostal,
                layProvince, layCountry, layEmail, layBirthday, layNotes, laySubmit, note);

        addPage = new Scene(layout, 660, 660);

        btnBack.setOnAction(e -> window.setScene(home));

        //setting up submit
        btnSubmit.setOnAction(e -> {

            //check if all required inputs are valid    
            if (isValidNumeric(txtFirst, 1) && isValidNumeric(txtLast, 1) && isValidPhone(txtPhone, 1)
                    && isValid(txtAddress1) && isValidNumeric(txtCity, 1)
                    && isValidPhone(txtPostal, 2) && isValidNumeric(txtProvince, 1) && isValidNumeric(txtCountry, 1)
                    && isValidEmail(txtEmail) && isValidDate(txtYear, 3)
                    && isValidDate(txtMonth, 2) && isValidDate(txtDay, 1)) {

                //making a new address object using the address related input
                address = new Address(txtAddress1.getText(), txtAddress2.getText(), txtCity.getText(), txtPostal.getText(),
                        txtProvince.getText(), txtCountry.getText());

                //making a new date of birth object using the data stored in datepicker -- debugging failed, optional to attempt later
                //dateBirth = new MyDate(getBirthday.getDayOfMonth(), getBirthday.getMonthValue() - 1, getBirthday.getYear());
                //making a new MyDate object using data acquired through textfields
                dateBirth = new MyDate(Integer.parseInt(txtDay.getText()), Integer.parseInt(txtMonth.getText()),
                        Integer.parseInt(txtYear.getText()));

                //if this page is not for editing
                if (!isEditable) {
                    boolean isSuccess = cm.addContact(txtFirst.getText(), txtLast.getText(),
                            txtPhone.getText(), txtWork.getText(), address, txtEmail.getText(), dateBirth,
                            txtNotes.getText());

                    if (isSuccess) {
                        Alert alert = new Alert(AlertType.INFORMATION);
                        alert.setContentText("Success");
                        alert.showAndWait();
                        window.setScene(home);

                    }
                } //if this page was called to perform editing
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
                    if (alert.getResult() == ButtonType.OK) {
                        Alert confirm = new Alert(AlertType.INFORMATION);
                        confirm.setContentText("Changes Applied!");
                        confirm.showAndWait();
                        window.setScene(home);
                    }
                }

            } //in case required inputs are not valid
            else {
                //issues with first name? handle it here
                if (!isValid(txtFirst)) {
                    showLimited(firstErr, "Name cannot be empty");
                } else if (!isValidNumeric(txtFirst, 1)) {
                    showLimited(firstErr, "Name can only contain letters");
                }

                //issues with last name?
                if (!isValid(txtLast)) {
                    showLimited(lastErr, "Name cannot be empty");
                } else if (!isValidNumeric(txtLast, 1)) {
                    showLimited(lastErr, "Name can only contain letters");
                }

                //issues with phone?
                if (!isValid(txtPhone)) {
                    showLimited(phoneErr, "Phone cannot be empty");
                } else if (!isValidPhone(txtPhone, 1)) {
                    showLimited(phoneErr, "Phone can only contain 10 numbers");
                }

                //issues with address?
                if (!isValid(txtAddress1)) {
                    showLimited(addressErr, "Address cannot be empty");
                }

                //issues with city?
                if (!isValid(txtCity)) {
                    showLimited(cityErr, "City cannot be empty");
                } else if (!isValidNumeric(txtCity, 1)) {
                    showLimited(cityErr, "City can only contain letters");
                }

                //issues with postal?
                if (!isValid(txtPostal)) {
                    showLimited(postalErr, "Postal Code cannot be empty");
                } else if (!isValidPhone(txtPostal, 2)) {
                    showLimited(postalErr, "Postal can only contain 6 letters/numbers");
                }

                //issues with province?
                if (!isValid(txtProvince)) {
                    showLimited(provinceErr, "Province cannot be empty");
                } else if (!isValidNumeric(txtProvince, 1)) {
                    showLimited(provinceErr, "Province can only contain letters");
                }

                //issues with country?
                if (!isValid(txtCountry)) {
                    showLimited(countryErr, "Country cannot be empty");
                } else if (!isValidNumeric(txtCountry, 1)) {
                    showLimited(countryErr, "Country can only contain letters");
                }

                //issues with email?
                if (!isValid(txtEmail)) {
                    showLimited(emailErr, "Email cannot be empty");
                } else if (!isValidEmail(txtEmail)) {
                    showLimited(emailErr, "Invalid Email Address");
                }

                //issues with date of birth?
                if (!isValid(txtDay)) {
                    showLimited(bdayErr, "Day cannot be empty");
                } else if (!isValidDate(txtDay, 1)) {
                    showLimited(bdayErr, "Invalid Day");
                }

                if (!isValid(txtMonth)) {
                    showLimited(bdayErr, "Month cannot be empty");
                } else if (!isValidDate(txtMonth, 2)) {
                    showLimited(bdayErr, "Invalid Month");
                }

                if (!isValid(txtYear)) {
                    showLimited(bdayErr, "Year cannot be empty");
                } else if (!isValidDate(txtYear, 3)) {
                    showLimited(bdayErr, "Invalid Year");
                }

                Alert alert = new Alert(AlertType.ERROR);
                alert.setContentText("Please make sure all required inputs are valid");
                alert.showAndWait();
            }

        });

        window.setScene(addPage);

    }
    
    //displays a list of all registered contacts, this method includes an editable version
    public void display(Stage window, boolean isEditable, ContactManager cm) {
        //this is an array of Vbox layouts that will dynamically expand
        //as we add more content based on the number of contacts
        VBox[] layVert = new VBox[cm.getNumContacts()];
        for (int i = 0; i < cm.getNumContacts(); i++) { //looping through the array of contacts
            Contact cont = cm.getContacts()[i];

            //setting up name
            Label lblfullName = new Label("Contact Name:");
            Label lblName = new Label(cm.getContacts()[i].getFirstName() + " " + cm.getContacts()[i].getLastName());
            lblfullName.setMinSize(140, 10);
            lblName.setMinSize(140, 10);
            HBox layName = new HBox(20);
            layName.getChildren().addAll(lblfullName, lblName);

            //setting up the manage/edit button
            //visibility will depend on whether the user would like to view or also edit
            Button btnManage = new Button("Manage Contact");
            btnManage.setVisible(isEditable);
            btnManage.setOnAction(e -> addPage(window, true, cont));

            //setting up display details button
            //in case user wanted to view detailed info about a contact
            Button btnDetails = new Button("View Contact Details");
            btnDetails.setOnAction(e -> displayAllDetails(window, cont));

            //setting up birthdays
            Label lblBday = new Label("Date of Birth:");
            Label lblBirthday = new Label(cm.getContacts()[i].getBirthday().getMonthLongForm() + " "
                    + cm.getContacts()[i].getBirthday().getDay() + ", " + cm.getContacts()[i].getBirthday().getYear());
            lblBday.setMinSize(140, 10);
            lblBirthday.setMinSize(140, 10);
            HBox layBirthday = new HBox(20);
            layBirthday.getChildren().addAll(lblBday, lblBirthday);

            layVert[i] = new VBox(10);
            layVert[i].getChildren().addAll(layName, layBirthday, btnDetails, btnManage);

        }

        //Go back button
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
    
    //displays all details about the specific contact the user selects to view
    public void displayAllDetails(Stage window, Contact c) {

        Label lblDetails = new Label(c.toString());
        Button btnBack = new Button("Back to main");
        btnBack.setOnAction(e -> window.setScene(home));

        VBox layDisplay = new VBox(30);
        VBox layBack = new VBox(10);
        layDisplay.getChildren().addAll(lblDetails);
        layBack.getChildren().add(btnBack);
        layBack.setAlignment(Pos.CENTER);
        layDisplay.getChildren().add(layBack);

        displayAll = new Scene(layDisplay, 500, 500);
        window.setScene(displayAll);
    }
    
    //displays one contact based on the search, this method includes a deletable version
    public void displayOne(Stage window, boolean isDeletable, Contact c) {

        //setting up full name
        Label lblFullName = new Label("Contact Name:");
        Label lblName = new Label(c.getFirstName() + " " + c.getLastName());
        lblFullName.setMinSize(140, 10);
        lblName.setMinSize(140, 10);
        HBox layName = new HBox(20);
        layName.getChildren().addAll(lblFullName, lblName);

        //setting up birthday
        Label lblBday = new Label("Date of Birth:");
        Label lblBirthday = new Label(c.getBirthday().getMonthLongForm() + c.getBirthday().getDay() + ", "
                + c.getBirthday().getYear());
        lblBday.setMinSize(140, 10);
        lblBirthday.setMinSize(140, 10);
        HBox layBday = new HBox(20);
        layBday.getChildren().addAll(lblBday, lblBirthday);

        //setting up button
        //if this method was called from a deletable request then 
        //this will be performing deleting functions instead
        Button btnManage = new Button("Manage Contact");
        if (isDeletable) {
            btnManage.setText("Delete Contact");
        }
        Button btnBack = new Button("Back");
        btnBack.setOnAction(e -> window.setScene(search));
        Button btnDetails = new Button("View Contact Details");
        btnDetails.setOnAction(e -> displayAllDetails(window, c));
        btnManage.setOnAction(e -> {
            if (!isDeletable) {
                addPage(window, true, c);
            } else if (isDeletable) {
                cm.deleteContact(c.getFirstName(), c.getLastName());
                Alert alert = new Alert(AlertType.INFORMATION);
                alert.setContentText("Contact Deleted!");
                alert.showAndWait();
                window.setScene(home);
            }
        });
        VBox layButton = new VBox(10);
        layButton.getChildren().addAll(btnManage, btnDetails);

        HBox layBack = new HBox();
        layBack.getChildren().add(btnBack);
        layBack.setAlignment(Pos.CENTER);

        VBox layout = new VBox(10);
        layout.getChildren().addAll(layName, layBday, layButton, layBack);

        viewOne = new Scene(layout, 500, 500);
        window.setScene(viewOne);

    }
    
    //searches for a contact, whether it's to view or to delete
    public void search(Stage window, boolean isDeletable, ContactManager cm) {

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

        btnSearch.setOnAction(e -> {
            boolean delete = false;

            if (isDeletable) {
                delete = true;
            }

            Contact con = cm.findContact(txtFirstName.getText(), txtLastName.getText());

            if (con != null) {
                displayOne(window, delete, con);
            } else {
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
    
    //displays a list of users that live in a specific city
    public void displayByCity(Stage window, Contact[] cList) {

        VBox[] layVert = new VBox[cList.length];
        for (int i = 0; i < cList.length; i++) {
            Contact con = cList[i];

            //setting up name
            Label lblfullName = new Label("Contact Name:");
            Label lblName = new Label(cList[i].getFirstName() + " " + cList[i].getLastName());
            lblfullName.setMinSize(140, 10);
            lblName.setMinSize(140, 10);
            HBox layName = new HBox(20);
            layName.getChildren().addAll(lblfullName, lblName);

            //setting up birthday
            Label lblBday = new Label("Date of Birth:");
            Label lblBirthday = new Label(cList[i].getBirthday().getMonthLongForm() + " " + cList[i].getBirthday().getDay() + ", "
                    + cList[i].getBirthday().getYear());
            lblBday.setMinSize(140, 10);
            lblBirthday.setMinSize(140, 10);
            HBox layBirthday = new HBox(20);
            layBirthday.getChildren().addAll(lblBday, lblBirthday);

            Button btnDetails = new Button("View Contact Details");
            btnDetails.setOnAction(e -> displayAllDetails(window, con));

            layVert[i] = new VBox(10);
            layVert[i].getChildren().addAll(layName, layBirthday, btnDetails);

        }

        Button btnBack = new Button("Back");
        btnBack.setOnAction(e -> window.setScene(searchCity));

        VBox layDisplay = new VBox(30);
        layDisplay.getChildren().addAll(layVert);

        VBox layBack = new VBox(10);
        layBack.getChildren().add(btnBack);
        layBack.setAlignment(Pos.CENTER);
        layDisplay.getChildren().add(layBack);

        viewByCity = new Scene(layDisplay, 500, 500);
        window.setScene(viewByCity);
    }
    
    //searches for users that live in a specific city
    public void searchCity(Stage window, ContactManager cm) {

        //setting up city
        Label lblCity = new Label("City:");
        TextField txtCity = new TextField();
        lblCity.setMinSize(140, 10);
        HBox layCity = new HBox(20);
        layCity.getChildren().addAll(lblCity, txtCity);

        //setting up buttons
        Button btnSearch = new Button("Search");
        Button btnBack = new Button("Back");
        HBox layButton = new HBox(5);
        layButton.getChildren().addAll(btnSearch, btnBack);
        layButton.setAlignment(Pos.CENTER);

        btnBack.setOnAction(e -> window.setScene(home));

        btnSearch.setOnAction(e -> {
            Contact[] conList = cm.findByCity(txtCity.getText());

            if (conList != null) {
                displayByCity(window, conList);
            } else {
                Alert alert = new Alert(AlertType.INFORMATION);
                alert.setContentText("No Contact was found in this city!");
                alert.showAndWait();
            }
        });

        VBox layout = new VBox(10);
        layout.getChildren().addAll(layCity, layButton);
        searchCity = new Scene(layout, 500, 500);
        window.setScene(searchCity);

    }

    public static void main(String[] args) {
        launch(args);

    }

}
