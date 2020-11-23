package com.timetablereader.app;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TimeTableReader {

    private static XSSFWorkbook workbook;
    private static XSSFSheet sheet;
    private static List<CellRangeAddress> mergedRegions;
    private static List<RowOfClasses> days;
    private static List<MergedRegion> mergedRegionList;
    private static ArrayList<SchoolHour> schoolHours=new ArrayList<>();
    private static ArrayList<SchoolDay> schoolDays=new ArrayList<>();


    public static ArrayList<SchoolDay> readFile(File file) throws IOException {

       workbook = new XSSFWorkbook(new FileInputStream(file));
        sheet = workbook.getSheetAt(0);
        mergedRegions = sheet.getMergedRegions();
        Collections.sort(mergedRegions, new CellRangeAddressComperator());
        days = new ArrayList<>();
        mergedRegionList = new ArrayList<>();
        mySolver();
        for (int i = 0; i <= days.size() - 1; i++) {
            RowOfClasses row = days.get(i);
            System.out.println("In row " + row.getRowIndex() + " there are " + row.getNumOfMergedRegions() +
                    " merged regions \n region 1 starts from " + row.getMergedRegionList().get(0).getStartingColumn() + " and covers "
                    + row.getMergedRegionList().get(0).getRange());
        }
        dayCreator();
        displayMyHardWork();

        //Loading an existing document
        //File file = new File("src/pdfTimeTable.pdf");
        //PDDocument document = PDDocument.load(file);

        //Retrieving a page of the PDF Document
        //Instantiating the PDPageContentStream class
       // PDPageContentStream contentStream = new PDPageContentStream(document, page);

        //PDFTextStripper pdfTextStripperByArea=new PDFTextStripper();

       // System.out.println("getting text");
       // System.out.println(pdfTextStripperByArea.getText(document));

       // PDFTextStripperByArea stripper = new PDFTextStripperByArea();
       // stripper.setSortByPosition( true );
        //List allPages = document.getDocumentCatalog().getAllPages();
        //PDPage firstPage = (PDPage)allPages.get( 0 );

        //stripper.extractRegions(firstPage);
        //System.out.println( "Text in the area:");
        //System.out.println();

    return schoolDays;

    }


    private static void displayMyHardWork() {
        for(int i=0;i<=schoolDays.size()-1;i++){

            SchoolDay schoolDay = schoolDays.get(i);
            System.out.println(schoolDay.getDayName().toUpperCase());
            ArrayList<SchoolHour> schoolHourss = schoolDay.getSchoolHours();
            System.out.println("\t\t\tthese are the lessons you have today :");
            for(int j = 0; j<= schoolHourss.size()-1; j++){
                SchoolHour schoolHour = schoolHourss.get(j);
                if(!schoolHour.isFree()){
                    System.out.println( "\t\t\t\t time is "+ j+" "+schoolHour.getLesson().getName() + " at the following venue "+ schoolHour.getLesson().getVenue());
                }

            }
        }
    }

    private static void dayCreator() {
        int x=0;
        String[] dayNames={"monday","tuesday","wednesday","thursday","friday"};
        for(int i=3;i<=15;i=i+3){
            Row row=sheet.getRow(i);
            RowOfClasses rowOfClasses=days.get(x);
            int y=0;
            for(int j=2;j<=10;j++){
                Cell cell = row.getCell(j);
                if(cell.toString().trim()!=""){
                    String lessonCode= cell.toString();
                    String lessonNameAndVenue []=sheet.getRow(i+1).getCell(j).toString().split("\\s\\s+");
                    String lessonName=lessonNameAndVenue[0];
                    String lessonVenue=lessonNameAndVenue[1];
                    String lecturesName=sheet.getRow(i+2).getCell(j).toString();
                    Lesson lesson=new Lesson(lessonName,lessonVenue,lecturesName,lessonCode);
                    MergedRegion mergedRegion=rowOfClasses.getMergedRegionList().get(y);
                    if(cell.getColumnIndex()==mergedRegion.getStartingColumn())
                    {
                        y++;
                        j=j+(mergedRegion.getRange()-1);
                        for(int h=0;h<mergedRegion.getRange();h++)
                        {
                          schoolHours.add(new SchoolHour(lesson,false)) ;
                        }


                    }
                    else {
                        schoolHours.add(new SchoolHour(lesson,false));
                    }

                }
                else{
                    schoolHours.add(new SchoolHour(null,true));
                }
            }
            schoolDays.add(new SchoolDay(schoolHours,dayNames[x]));
            schoolHours=new ArrayList<>();
            x++;
        }
    }

    private static void  mySolver(){
        int x;
        for(int r=3;r<=15;r=r+3){
            x=0;
            for (int i=0;i<=mergedRegions.size()-1;i++){
                CellRangeAddress cellAddress=mergedRegions.get(i);
                if(mergedRegions.get(i).containsRow(r)){
                    mergedRegionList.add(new MergedRegion(cellAddress.getFirstColumn(),cellAddress.getNumberOfCells()));
                    x++;

                }

            }
            days.add(new RowOfClasses(r,x,mergedRegionList));
            mergedRegionList=new ArrayList<>();
        }
    }

    private static class CellRangeAddressComperator implements java.util.Comparator<CellRangeAddress> {
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
