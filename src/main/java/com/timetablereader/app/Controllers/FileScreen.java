package com.timetablereader.app.Controllers;

import com.jfoenix.controls.JFXButton;
import com.timetablereader.app.CalendarView;
import com.timetablereader.app.ExcelReader;
import com.timetablereader.app.data.SchoolDay;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.util.ArrayList;

public class FileScreen {


    private static int currentScreen = 1;

    @FXML
    private VBox mainContent;

    @FXML
    private JFXButton fileSelector;

    @FXML
    private JFXButton continueBtn;

    private TextField tableNumber, textField;
    private File selectedFile;


    ExcelReader excelReader = new ExcelReader();


    private CalendarView calendarView=new CalendarView();
    private static Stage stage1;


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
                    tableNumber = new TextField("Table number");
                    mainContent.getChildren().add(tableNumber);
                }
                currentScreen++;
                break;
            }
            case 3: {
                int region = (Integer.parseInt(tableNumber.getText()) - 1) * 10 + 3;
                excelReader.setRegion(23);
                ArrayList<SchoolDay> schoolDays = excelReader.readFile();
                try {
                    calendarView.setSchoolDays(schoolDays);
                    calendarView.init(stage1);

                }catch (Exception e){
                    e.printStackTrace();
                    System.out.println(e.getMessage());
                }
                currentScreen++;
                break;

            }

        }
    }
}