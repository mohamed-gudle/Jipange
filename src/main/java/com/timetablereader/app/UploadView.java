package com.timetablereader.app;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;
import javafx.stage.Stage;


import java.io.IOException;
import java.util.Arrays;
import java.util.Objects;

public class UploadView extends Application {

String[] args;
    private Button button;

    @Override
    public void start(final Stage primaryStage) throws IOException {
//        primaryStage.setTitle("JavaFX App");
//
//        FileChooser fileChooser = new FileChooser();
//
//
//        button = new Button("Select File");
//        button.setOnAction(e -> {
//            final File selectedFile= fileChooser.showOpenDialog(primaryStage);
//            try {
//                ArrayList<SchoolDay> schoolDays = TimeTableReader.readFile(selectedFile);
//                CalendarApp calendarApp=new CalendarApp();
//                calendarApp.setSchoolDays(schoolDays);
//                calendarApp.init(primaryStage);
//            } catch (IOException ex) {
//                ex.printStackTrace();
//            } catch (Exception ex) {
//                ex.printStackTrace();
//            }
//        });
//
//
//        VBox vBox = new VBox(button);
//        Scene scene = new Scene(vBox, 960, 600);
//
//        primaryStage.setScene(scene);
//        primaryStage.show();
//
//        FXMLLoader fxmlLoader = new FXMLLoader();
//        fxmlLoader.setLocation(UploadView.class.getResource("Register.fxml"));
//        Parent root = fxmlLoader.load();

        

        Parent  root = FXMLLoader.load(getClass().getResource("Login.fxml"));


        Scene scene = new Scene(root);
        scene.setFill(Color.TRANSPARENT);
        primaryStage.setScene(scene);
        primaryStage.show();

    }

    public static void main(String[] args) {
        launch(args);
    }



}
