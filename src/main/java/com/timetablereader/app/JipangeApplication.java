package com.timetablereader.app;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


import java.io.IOException;

public class JipangeApplication extends Application {

    @Override
    public void start(final Stage primaryStage) throws IOException {

        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getClassLoader().getResource("FileScreen.fxml"));
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
