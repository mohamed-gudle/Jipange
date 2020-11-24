/*
 *  Copyright (C) 2017 Dirk Lemmermann Software & Consulting (dlsc.com)
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *          http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

package com.timetablereader.app;

import com.calendarfx.model.Calendar;
import com.calendarfx.model.Calendar.Style;
import com.calendarfx.model.CalendarSource;
import com.calendarfx.model.Entry;
import com.calendarfx.view.CalendarView;
import com.google.gson.Gson;
import com.mongodb.BasicDBObject;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;


import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import org.bson.Document;


import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

public class CalendarApp{

    private static ArrayList<SchoolDay> schoolDays;
    private MongoConnection connection=new MongoConnection();
    private MongoDatabase init = connection.init();
    private MongoCollection schoolDayTable = connection.getCollection("day");

    public void setSchoolDays(ArrayList<SchoolDay> schoolDays) {
        CalendarApp.schoolDays =schoolDays;

    }


    public void  init(Stage primaryStage) throws Exception {
        CalendarView calendarView = new CalendarView();

        Calendar classes = new Calendar(" Classes");
        Calendar birthdays = new Calendar("Birthdays");
        Calendar holidays = new Calendar("Holidays");

        classes.setShortName("C");
        birthdays.setShortName("B");
        holidays.setShortName("H");

        classes.setStyle(Style.STYLE1);
        birthdays.setStyle(Style.STYLE6);
        holidays.setStyle(Style.STYLE7);

        CalendarSource familyCalendarSource = new CalendarSource("Family");
        familyCalendarSource.getCalendars().addAll(birthdays, holidays,classes);


        calendarView.getCalendarSources().setAll(familyCalendarSource);
        calendarView.setRequestedTime(LocalTime.now());







        int j=0;
        String dayOfWeek= String.valueOf(LocalDate.now().getDayOfWeek());

        System.out.println("**************"+dayOfWeek);
        LocalDate localDate=LocalDate.now();
        switch (dayOfWeek){


            case "TUESDAY":{
                localDate=localDate.minusDays(1);
                System.out.println(localDate);
                break;
            }
            case "WEDNESDAY":{
                localDate=localDate.minusDays(2);
                break;
            }
            case "THURSDAY":{
                localDate=localDate.minusDays(3);
                System.out.println(localDate);
                break;
            }
            case "FRIDAY":{
                localDate=localDate.minusDays(4);
                System.out.println(localDate);
                break;
            }
            case "SATURDAY":{
                localDate=localDate.minusDays(5);
                System.out.println(localDate);
                break;
            }
            case "SUNDAY":{
                localDate=localDate.plusDays(1);
                System.out.println(localDate);
                break;
            }
            default:{
                localDate=LocalDate.now();
                System.out.println("its monday");
            }
        }
        final LocalDate monday=localDate;
        for (SchoolDay day:schoolDays) {
            int i=0;
            switch (j){

                case 1:{
                    localDate=localDate.plusDays(1);
                    System.out.println(j+"     "+localDate.getDayOfWeek());
                    break;
                }
                case 2:{
                    localDate=localDate.plusDays(2);
                    System.out.println(j+"     "+localDate.getDayOfWeek());
                    break;
                }case 3:{
                    localDate=localDate.plusDays(3);
                    System.out.println(j+"     "+localDate.getDayOfWeek());
                    break;
                }
                case 4:{
                    localDate=localDate.plusDays(4);
                    System.out.println(j+"     "+localDate.getDayOfWeek());
                    break;
                }
                default:{

                }

            }
            for (SchoolHour hour:day.getSchoolHours()) {
                Entry entry=new Entry();
                if(!hour.isFree()) {
                    Lesson lesson = hour.getLesson();
                    entry.setLocation(hour.getLesson().getVenue());
                    entry.setTitle(hour.getLesson().getName() + " :" + hour.getLesson().getLecturerName());
                    entry.setId(lesson.getCode());
                    entry.changeStartDate(localDate);
                    entry.changeEndDate(localDate);

                    switch (i)
                    {
                        case 0:{
                            entry.changeStartTime(LocalTime.of(8,15));
                            entry.changeEndTime(LocalTime.of(9,15));
                            break;
                        }
                        case 1:{
                            entry.changeStartTime(LocalTime.of(9,15));
                            entry.changeEndTime(LocalTime.of(10,15));
                            break;
                        }
                        case 2:{
                            entry.changeStartTime(LocalTime.of(10,15));
                            entry.changeEndTime(LocalTime.of(11,15));
                            break;
                        }
                        case 3:{
                            entry.changeStartTime(LocalTime.of(11,15));
                            entry.changeEndTime(LocalTime.of(12,15));
                            break;
                        }
                        case 4:{
                            entry.changeStartTime(LocalTime.of(12,15));
                            entry.changeEndTime(LocalTime.of(13,15));
                            break;
                        }
                        case 5:{
                            entry.changeStartTime(LocalTime.of(13,15));
                            entry.changeEndTime(LocalTime.of(14,15));
                            break;
                        }
                        case 6:{
                            entry.changeStartTime(LocalTime.of(14,15));
                            entry.changeEndTime(LocalTime.of(15,15));
                            break;
                        }
                        case 7:{
                            entry.changeStartTime(LocalTime.of(15,15));
                            entry.changeEndTime(LocalTime.of(16,15));
                            break;
                        }
                        case 8:{
                            entry.changeStartTime(LocalTime.of(16,15));
                            entry.changeEndTime(LocalTime.of(17,15));
                            break;
                        }
                        default:{
                            break;
                        }


                    }
                    entry.setRecurrenceRule("RRULE:FREQ=WEEKLY");
                    classes.addEntry(entry);

                }
                i++;


            }
            localDate=monday;
            j++;


            Gson gson = new Gson();
            String json = gson.toJson(day);
            // Parse to bson document and insert
            Document doc = Document.parse(json);

            schoolDayTable.insertOne(doc);

        }






        StackPane stackPane = new StackPane();
        stackPane.getChildren().addAll(calendarView); // introPane);

        Thread updateTimeThread = new Thread("Calendar: Update Time Thread") {
            @Override
            public void run() {
                while (true) {
                    Platform.runLater(() -> {
                        calendarView.setToday(LocalDate.now());
                        calendarView.setTime(LocalTime.now());
                    });

                    try {
                        // update every 10 seconds
                        sleep(10000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }
            }
        };

        updateTimeThread.setPriority(Thread.MIN_PRIORITY);
        updateTimeThread.setDaemon(true);
        updateTimeThread.start();

        Scene scene = new Scene(stackPane);
        primaryStage.setTitle("Calendar");
        primaryStage.setScene(scene);


        primaryStage.show();
    }


}
