package com.timetablereader.app.Controllers;

import com.gluonhq.charm.glisten.control.Icon;
import com.gluonhq.charm.glisten.control.ProgressIndicator;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import com.timetablereader.app.data.User;
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
import javafx.scene.control.Alert;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;

public class Login {

    @FXML
    private AnchorPane mainContainer;
    @FXML
    private JFXTextField username;

    @FXML
    private JFXTextField password;

    @FXML
    private Text title;

    @FXML
    private JFXButton login;

    @FXML
    private JFXButton register;

    @FXML
    private Icon icon;

    @FXML
    private Text text;

    private ProgressIndicator progressIndicator=new ProgressIndicator();

    private Alert alert=new Alert(Alert.AlertType.NONE);
    private User user;
    private Node node;

    @FXML
    void attemptLogin(ActionEvent event1) throws IOException {
        mainContainer.getChildren().forEach(child-> {
            child.setOpacity(0);
        });
        progressIndicator.setLayoutX(260);
        progressIndicator.setLayoutY(283);
        mainContainer.getChildren().add(progressIndicator);

       String email=username.getText().toString();
       String password1=password.getText().toString();
       user = new User(email,password1);

       AuthenticateAsync async=new AuthenticateAsync();

       async.setOnSucceeded(new EventHandler<WorkerStateEvent>() {
           @Override
           public void handle(WorkerStateEvent event) {
               if(async.getValue()==false){
                   alert.setAlertType(Alert.AlertType.ERROR);
                   alert.setTitle("Jipange Application");
                   alert.setHeaderText("Oops! An error occurred");
                   alert.setContentText("the email and password dont match to a registered user");
                   alert.show();
               }
               else {
                   FXMLLoader fxmlLoader=new FXMLLoader();
                   AnchorPane root = null;
                   try {
                       root = fxmlLoader.load(getClass().getClassLoader().getResource("FileScreen.fxml"));
                   } catch (IOException e) {
                       e.printStackTrace();
                   }


                   node = (Node) event1.getSource();

                   Stage stage = (Stage) node.getScene().getWindow();


                   Scene scene = new Scene(root);
                   scene.setFill(Color.TRANSPARENT);
                   stage.setScene(scene);
               }
           }
       });
       async.start();



    }

    @FXML
    void getRegistration(ActionEvent event) {
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getClassLoader().getResource("Register.fxml"));
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
    private class AuthenticateAsync extends Service<Boolean>{
        boolean success=false;


        @Override
        protected Task<Boolean> createTask() {
            return new Task<Boolean>() {
                @Override
                protected Boolean call() throws Exception {
                     if(user.AuthenticateUser()){
                        success=true;

                    }
                    return success;
                }
            };
        }
    }

}
