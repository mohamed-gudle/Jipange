package com.timetablereader.app.Controllers;

import com.jfoenix.controls.JFXButton;
import com.timetablereader.app.CalendarView;
import com.timetablereader.app.ExcelReader;
import com.timetablereader.app.data.SchoolDay;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.util.ArrayList;

public class FileScreen {


    private static int currentScreen = 1;

    @FXML
    private AnchorPane mainContent;

    @FXML
    private JFXButton fileSelector;

    @FXML
    private JFXButton continueBtn;

    private TextField tableNumber, textField;
    private File selectedFile;


   static ExcelReader excelReader = new ExcelReader();


    private CalendarView calendarView=new CalendarView();
    private static Stage stage1;
    private static ProgressBar progressBar;


    public static void setStage(Stage stage) {
        stage1 = stage;
    }

    @FXML
    void fileSelector(ActionEvent event) {
        selectedFile = new FileChooser().showOpenDialog(new Stage());

    }

    @FXML
    void nextScreen(ActionEvent event) {
        changeScreen(currentScreen);

    }

    public void changeScreen(int screenNumber) {
        switch (screenNumber) {
            case 1: {
                try {

                    excelReader.setWorkbook(selectedFile);

                } catch (Exception e) {
                    e.printStackTrace();
                    System.out.println(e.getMessage());

                }
                mainContent.getChildren().clear();
                if (excelReader.getNumberOfSheets() > 1) {
                    textField = new TextField("sheetNumber");
                    mainContent.getChildren().add(textField);

                }
                currentScreen++;
                break;
            }
            case 2: {
                excelReader.setSheet(0);
                mainContent.getChildren().clear();
                if (excelReader.getNumberOfTables() > 1) {
                    tableNumber = new TextField();
                    mainContent.getChildren().add(tableNumber);
                }
                currentScreen++;
                break;
            }
            case 3: {
                int region = ((Integer.parseInt(tableNumber.getText()) - 1) * 20) + 3;
                System.out.println(region);
                excelReader.setRegion(region);

                try {
                    progressBar = new ProgressBar();
                    mainContent.getChildren().add(progressBar);
                   convertFileAsync convertFileAsync=new convertFileAsync();
                   convertFileAsync.setCalendarView(calendarView);
                   convertFileAsync.setOnSucceeded(new EventHandler<WorkerStateEvent>() {
                       @Override
                       public void handle(WorkerStateEvent event) {
                           calendarView.init(stage1);
                           System.out.println("done *************");

                       }
                   });
                   convertFileAsync.start();

                }catch (Exception e){
                    e.printStackTrace();
                    System.out.println(e.getMessage());
                }
                currentScreen++;
                break;

            }

        }
    }
    private static class convertFileAsync extends Service{
        CalendarView calendarView;

        public void setCalendarView(CalendarView calendarView) {
            this.calendarView = calendarView;
        }

        @Override
        protected Task createTask() {
            return new Task() {
                @Override
                protected Object call() throws Exception {
                    try {

                        ArrayList<SchoolDay> schoolDays = excelReader.readFile();
                        progressBar.setProgress(0.25);
                        calendarView.setSchoolDays(schoolDays);
                        progressBar.setProgress(0.75);
                        calendarView.convertFileToEntries();
                        progressBar.setProgress(1);
                    }
                    catch (Exception e){
                        e.printStackTrace();
                        System.out.println(e.getMessage());

                    }
                    return null;
                }
            };
        }
    }
}
