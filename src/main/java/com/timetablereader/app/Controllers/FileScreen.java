package com.timetablereader.app.Controllers;

import com.gluonhq.charm.glisten.control.ProgressIndicator;
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
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.util.ArrayList;

public class FileScreen {


    private static int currentScreen = 1;

    @FXML
    private Label fileName;
    @FXML
    private AnchorPane main;

    @FXML
    private AnchorPane mainContent;

    @FXML
    private JFXButton fileSelector;

    @FXML
    private JFXButton continueBtn;
    @FXML
    private Label label;

    private Spinner<Integer> tableNumber;
    private Spinner<Integer> sheetNumber;
    private File selectedFile;


   static ExcelReader excelReader = new ExcelReader();


    private CalendarView calendarView=new CalendarView();
    private static Stage stage1;
    private static ProgressIndicator progressIndicator=new ProgressIndicator();
    private int numberOfSheets;


    @FXML
    void fileSelector(ActionEvent event) {
        selectedFile = new FileChooser().showOpenDialog(new Stage());
        fileName.setText("file name:"+selectedFile.getName());
        try {

            excelReader.setWorkbook(selectedFile);
            label.setText(selectedFile.getName());

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());

        }
        numberOfSheets = excelReader.getNumberOfSheets();
        Node node = (Node) event.getSource();

        stage1 = (Stage) node.getScene().getWindow();
        if (numberOfSheets > 1) {

            label.setText(numberOfSheets +" sheets in the file please select the one contains your table");
            mainContent.getChildren().clear();
            sheetNumber = new Spinner<Integer>();
            SpinnerValueFactory<Integer> valueFactory = //
                    new SpinnerValueFactory.IntegerSpinnerValueFactory(1, numberOfSheets, 1);

            sheetNumber.setValueFactory(valueFactory);
            sheetNumber.setLayoutX(91);
            sheetNumber.setLayoutY(41);
            mainContent.getChildren().add(sheetNumber);
            currentScreen=2;
            changeScreen(currentScreen);

        }
        else {
            currentScreen=2;
            changeScreen(currentScreen);
        }

    }

    @FXML
    void nextScreen(ActionEvent event) {
        changeScreen(currentScreen);

    }

    public void changeScreen(int screenNumber) {
        switch (screenNumber) {
            case 2: {
                if(numberOfSheets>1) {
                    excelReader.setSheet((int) sheetNumber.getValue());
                }
                else {
                    excelReader.setSheet(0);
                }

                mainContent.getChildren().clear();

                int numberOfTables = excelReader.getNumberOfTables();
                if (numberOfTables > 1) {
                    label.setText(numberOfTables+" tables found :select the table number for your timetable");
                    tableNumber = new Spinner<Integer>();
                    SpinnerValueFactory<Integer> valueFactory = //
                            new SpinnerValueFactory.IntegerSpinnerValueFactory(1, numberOfTables, 1);

                    tableNumber.setValueFactory(valueFactory);
                    tableNumber.setLayoutX(91);
                    tableNumber.setLayoutY(41);
                    mainContent.getChildren().add(tableNumber);
                }
                currentScreen++;
                break;
            }
            case 3: {
                int region = (((int) tableNumber.getValue()) - 1) * 20 + 3;
                System.out.println(region);
                excelReader.setRegion(region);

                try {
                    main.getChildren().forEach(child-> {
                        child.setOpacity(0);
                    });
                    progressIndicator.setLayoutX(260);
                    progressIndicator.setLayoutY(283);
                    main.getChildren().add(progressIndicator);
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

                        calendarView.setSchoolDays(schoolDays);

                        calendarView.convertFileToEntries();


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
