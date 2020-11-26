package com.timetablereader.app.Controllers;

import com.gluonhq.charm.glisten.control.Icon;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import com.timetablereader.app.data.User;
import javafx.event.ActionEvent;
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

    private Alert alert=new Alert(Alert.AlertType.NONE);

    @FXML
    void attemptLogin(ActionEvent event) throws IOException {
       String email=username.getText().toString();
       String password1=password.getText().toString();
       User user=new User(email,password1);
       if(!user.AuthenticateUser()){
           alert.setAlertType(Alert.AlertType.ERROR);
           alert.setTitle("Jipange Application");
           alert.setHeaderText("Oops! An error occurred");
           alert.setContentText("the email and password dont match to a registered user");
           alert.show();
       }
       else {
           FXMLLoader fxmlLoader=new FXMLLoader();
           AnchorPane root =fxmlLoader.load(getClass().getClassLoader().getResource("FileScreen.fxml"));



           Node node = (Node) event.getSource();

           Stage stage = (Stage) node.getScene().getWindow();

           FileScreen.setStage(stage);
           Scene scene = new Scene(root);
           scene.setFill(Color.TRANSPARENT);
           stage.setScene(scene);
       }
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

}
