package com.timetablereader.app;

import com.timetablereader.app.Controllers.FileScreen;
import com.timetablereader.app.data.SchoolDay;
import com.timetablereader.app.utils.JipangeView;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Stage;


import java.io.File;
import java.io.IOException;
import java.sql.Time;
import java.util.ArrayList;

public class JipangeApplication extends Application {

    @Override
    public void start(final Stage primaryStage) throws IOException {

        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getClassLoader().getResource("Register.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
            e.getCause();
            System.out.println(e.getMessage());
        }
        assert root != null;
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
   }

 public static void main(String args[]){
        launch(args);
 }



}
