package com.timetablereader.app;

import com.timetablereader.app.Controllers.FileScreen;
import com.timetablereader.app.data.SchoolDay;
import com.timetablereader.app.utils.JipangeView;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
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

        FXMLLoader fxmlLoader=new FXMLLoader();
        AnchorPane root =fxmlLoader.load(getClass().getClassLoader().getResource("FileScreen.fxml"));

        FileScreen.setStage(primaryStage);
        Scene scene = new Scene(root);
        scene.setFill(Color.TRANSPARENT);
        primaryStage.setScene(scene);
        primaryStage.show();


//        primaryStage.setTitle("JavaFX App");
//
//        FileChooser fileChooser = new FileChooser();
//
//
//      Button   button = new Button("Select File");
//        button.setOnAction(e -> {
//            final File selectedFile= fileChooser.showOpenDialog(primaryStage);
//            try {
//                ExcelReader excelReader=new ExcelReader();
//
//                excelReader.setWorkbook(selectedFile);
//
//                excelReader.setSheet(0);
////               if(excelReader.getNumberOfSheets()>1){
////                   //display getSheetPage;
////                   //get number pass it to excelReader.setSheet(0);
////                   //diplay getTable;
////
////               }
////               if(excelReader.getNumberOfTables()>1){
////                   //display getSheetPage;
////                   //get number pass it to excelReader.setSheet(0);
////
////                   //diplay getTable;
////
////               }
//
//                excelReader.setRegion(23);
//                ArrayList<SchoolDay> schoolDays = excelReader.readFile();
//
//                CalendarView calendarView=new CalendarView();
//                calendarView.setSchoolDays(schoolDays);
//                calendarView.init(primaryStage);
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
//
   }

 public static void main(String args[]){
        launch(args);
 }



}
