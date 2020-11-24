package com.timetablereader.app;

import com.gluonhq.charm.glisten.control.Icon;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
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

    @FXML
    void attemptLogin(ActionEvent event) {

    }

    @FXML
    void getRegistration(ActionEvent event) {
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("../../../../resources/Register.fxml"));
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
