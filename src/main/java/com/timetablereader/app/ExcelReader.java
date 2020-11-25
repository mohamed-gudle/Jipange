package com.timetablereader.app;

import com.timetablereader.app.data.*;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ExcelReader {

    private XSSFWorkbook workbook;
    private XSSFSheet sheet;
    private List<CellRangeAddress> mergedRegions;
    private List<RowOfClasses> rowOfClasses;
    private List<MergedRegion> mergedRegionList;
    private ArrayList<SchoolHour> schoolHours = new ArrayList<>();
    private ArrayList<SchoolDay> schoolDays=new ArrayList<SchoolDay>();
    private int startingRow;
    private int numberOfTables;

    public int getNumberOfTables() {
        return numberOfTables;
    }

    public int getNumberOfSheets() {
        return numberOfSheets;
    }

    private int numberOfSheets;

    public void setMergedRegionList() {
        this.mergedRegionList = mergedRegionList;
        mergedRegions = sheet.getMergedRegions();
        Collections.sort(mergedRegions, new CellRangeAddressComperator());
        rowOfClasses = new ArrayList<>();
        mergedRegionList = new ArrayList<>();
    }

    public void setWorkbook(File file) throws FileNotFoundException {
        try {
            workbook = new XSSFWorkbook(new FileInputStream(file));
            numberOfSheets=workbook.getNumberOfSheets();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setSheet(int number){
        sheet = workbook.getSheetAt(number);
        numberOfTables=sheet.getPhysicalNumberOfRows()/18;
        System.out.println(numberOfTables);
        setMergedRegionList();
    }


    public ArrayList<SchoolDay> readFile(){
        readRegion();
      /*  for (int i = 0; i <= rowOfClasses.size() - 1; i++) {
            RowOfClasses row = rowOfClasses.get(i);
            System.out.println("In row " + row.getRowIndex() + " there are " + row.getNumOfMergedRegions() +
                    " merged regions \n region 1 starts from " + row.getMergedRegionList().get(0).getStartingColumn() + " and covers "
                    + row.getMergedRegionList().get(0).getRange());
        }

       */
        setDayData();
        displaySchedule();


        return schoolDays;
    }

    public void setRegion(int startingRow){
        this.startingRow =startingRow;
    }



    private void displaySchedule() {
        for (int i = 0; i <= schoolDays.size() - 1; i++) {

            SchoolDay schoolDay = schoolDays.get(i);
            System.out.println(schoolDay.getDayName().toUpperCase());
            ArrayList<SchoolHour> schoolHourss = schoolDay.getSchoolHours();
            System.out.println("\t\t\tthese are the lessons you have today :");
            for (int j = 0; j <= schoolHourss.size() - 1; j++) {
                SchoolHour schoolHour = schoolHourss.get(j);
                if (!schoolHour.isFree()) {
                    System.out.println("\t\t\t\t time is " + j + " " + schoolHour.getLesson().getName() + " at the following venue " + schoolHour.getLesson().getVenue());
                }

            }
        }
    }

    private void setDayData() {
        int x = 0;
        String[] dayNames = {"monday", "tuesday", "wednesday", "thursday", "friday"};
        for (int i = startingRow; i <= startingRow+12; i = i + 3) {
            Row row = sheet.getRow(i);
            RowOfClasses rowOfClasses = this.rowOfClasses.get(x);
            int y = 0;
            for (int j = 2; j <= 10; j++) {
                Cell cell = row.getCell(j);
                if (cell.toString().trim() != "" || sheet.getRow(i + 1).getCell(j).toString().trim() !="") {
                    String lessonCode = cell.toString();
                    String lessonNameAndVenue[] = sheet.getRow(i + 1).getCell(j).toString().split("\\s\\s+");
                    String lessonName = lessonNameAndVenue[0];
                    String lessonVenue = lessonNameAndVenue[1];
                    String lecturesName = sheet.getRow(i + 2).getCell(j).toString();
                    Lesson lesson = new Lesson(lessonName, lessonVenue, lecturesName, lessonCode);
                    MergedRegion mergedRegion = rowOfClasses.getMergedRegionList().get(y);
                    if (cell.getColumnIndex() == mergedRegion.getStartingColumn()) {
                        y++;
                        j = j + (mergedRegion.getRange() - 1);
                        for (int h = 0; h < mergedRegion.getRange(); h++) {
                            schoolHours.add(new SchoolHour(lesson, false));
                        }


                    } else {
                        schoolHours.add(new SchoolHour(lesson, false));
                    }

                } else {
                    schoolHours.add(new SchoolHour(null, true));
                }
            }
            schoolDays.add(new SchoolDay(schoolHours, dayNames[x]));
            schoolHours = new ArrayList<>();
            x++;
        }
    }

    private void readRegion() {
        int x;
        for (int r = startingRow; r <= startingRow+12; r = r + 3) {
            x = 0;
            for (int i = 0; i <= mergedRegions.size() - 1; i++) {
                CellRangeAddress cellAddress = mergedRegions.get(i);
                if (mergedRegions.get(i).containsRow(r)) {
                    mergedRegionList.add(new MergedRegion(cellAddress.getFirstColumn(), cellAddress.getNumberOfCells()));
                    x++;

                }

            }
            rowOfClasses.add(new RowOfClasses(r, x, mergedRegionList));
            mergedRegionList = new ArrayList<>();
        }
    }

    private class CellRangeAddressComperator implements java.util.Comparator<CellRangeAddress> {
        public int compare(CellRangeAddress o1, CellRangeAddress o2) {
            int r = 0;
            // first compare first rows
            r = o1.getFirstRow() - o2.getFirstRow();
            if (r != 0) return r;
            // if first rows are equal, compare first columns
            r = o1.getFirstColumn() - o2.getFirstColumn();
            if (r != 0) return r;
            // if first rows and first columns are equal, compare last rows
            r = o1.getLastRow() - o2.getLastRow();
            if (r != 0) return r;
            // if even last rows are equal, compare last columns
            r = o1.getLastColumn() - o2.getLastColumn();
            if (r != 0) return r;
            // if all is equal, return 0
            return r;
        }
    }


}
