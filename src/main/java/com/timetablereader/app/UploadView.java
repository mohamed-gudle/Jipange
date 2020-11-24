package com.timetablereader.app;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;


import java.io.File;
import java.io.IOException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.logging.Logger;

public class UploadView extends Application {

String[] args;
    private Button button;

    @Override
    public void start(final Stage primaryStage) {
        primaryStage.setTitle("JavaFX App");

        FileChooser fileChooser = new FileChooser();


        button = new Button("Select File");
        button.setOnAction(e -> {
            final File selectedFile= fileChooser.showOpenDialog(primaryStage);
            try {
                ArrayList<SchoolDay> schoolDays = TimeTableReader.readFile(selectedFile);
                CalendarApp calendarApp=new CalendarApp();
                calendarApp.setSchoolDays(schoolDays);
                calendarApp.init(primaryStage);
            } catch (IOException ex) {
                ex.printStackTrace();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });


        VBox vBox = new VBox(button);
        Scene scene = new Scene(vBox, 960, 600);

        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }



}
