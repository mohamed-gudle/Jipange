package com.timetablereader.app.Controllers;

import com.gluonhq.charm.glisten.control.ProgressIndicator;
import com.jfoenix.controls.*;
import com.mongodb.client.MongoCollection;
import com.timetablereader.app.data.User;
import com.timetablereader.app.utils.MongoConnection;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;


import java.io.IOException;


public class Register {

    private ProgressIndicator progressIndicator=new ProgressIndicator();
    @FXML
    private AnchorPane mainContainer;
    @FXML
    private JFXTextField firstName;

    @FXML
    private JFXTextField lastName;

    @FXML
    private JFXTextField IdNumber;

    @FXML
    private JFXDatePicker dateOfBirth;

    @FXML
    private JFXTextField email;

    @FXML
    private JFXTextField address;

    @FXML
    private JFXPasswordField password;

    public JFXPasswordField getPassword() {
        return password;
    }

    public void setPassword(JFXPasswordField password) {
        this.password = password;
    }

    public JFXPasswordField getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(JFXPasswordField confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    @FXML
    private JFXPasswordField confirmPassword;
    @FXML
    private JFXButton createAccount;

    @FXML
    private JFXRadioButton genderMale;

    @FXML
    private JFXRadioButton genderFemale;

    @FXML
    private JFXButton login;


    public JFXTextField getFirstName() {
        return firstName;
    }

    public void setFirstName(JFXTextField firstName) {
        this.firstName = firstName;
    }

    public JFXTextField getLastName() {
        return lastName;
    }

    public void setLastName(JFXTextField lastName) {
        this.lastName = lastName;
    }

    public JFXTextField getIdNumber() {
        return IdNumber;
    }

    public void setIdNumber(JFXTextField idNumber) {
        IdNumber = idNumber;
    }

    public JFXDatePicker getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(JFXDatePicker dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public JFXTextField getEmail() {
        return email;
    }

    public void setEmail(JFXTextField email) {
        this.email = email;
    }

    public JFXTextField getAddress() {
        return address;
    }

    public void setAddress(JFXTextField address) {
        this.address = address;
    }

    public JFXButton getCreateAccount() {
        return createAccount;
    }

    public void setCreateAccount(JFXButton createAccount) {
        this.createAccount = createAccount;
    }

    public JFXRadioButton getGenderMale() {
        return genderMale;
    }

    public void setGenderMale(JFXRadioButton genderMale) {
        this.genderMale = genderMale;
    }

    public JFXRadioButton getGenderFemale() {
        return genderFemale;
    }

    public void setGenderFemale(JFXRadioButton genderFemale) {
        this.genderFemale = genderFemale;
    }

    public JFXButton getLogin() {
        return login;
    }

    public void setLogin(JFXButton login) {
        this.login = login;
    }

    @FXML
    void getLogin(ActionEvent event) throws IOException {
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getClassLoader().getResource("Login.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
            e.getCause();
            System.out.println(e.getMessage());
        }

        Node node = (Node) event.getSource();

        Stage stage = (Stage) node.getScene().getWindow();
        assert root != null;
        stage.setScene(new Scene(root));
    }


    @FXML
    void makeRegistration(ActionEvent event1) {
        mainContainer.getChildren().forEach(child-> {
            child.setOpacity(0);
        });
        progressIndicator.setLayoutX(220);
        progressIndicator.setLayoutY(283);
        mainContainer.getChildren().add(progressIndicator);


        RegisterUserAsync userAsync=new RegisterUserAsync();
        userAsync.setOnSucceeded(new EventHandler<WorkerStateEvent>() {
            @Override
            public void handle(WorkerStateEvent event) {
                FXMLLoader fxmlLoader=new FXMLLoader();
                AnchorPane root = null;
                try {
                    root = fxmlLoader.load(getClass().getClassLoader().getResource("FileScreen.fxml"));
                } catch (IOException e) {
                    e.printStackTrace();
                }


                Node node = (Node) event1.getSource();

                Stage stage = (Stage) node.getScene().getWindow();


                Scene scene = new Scene(root);
                scene.setFill(Color.TRANSPARENT);
                stage.setScene(scene);
            }
        });
        userAsync.start();

    }
    private class RegisterUserAsync extends Service {

        @Override
        protected Task createTask() {
            return new Task() {
                @Override
                protected Object call() throws Exception {
                    MongoConnection.init();
                    MongoCollection user = MongoConnection.getCollection("user");
                    User user1=new User(firstName.getText(),lastName.getText().toString(),dateOfBirth.toString(),email.getText().toString(),password.getText().toString());
                    user1.registerUser(user1);
                    return null;
                }
            };
        }
    }

}
