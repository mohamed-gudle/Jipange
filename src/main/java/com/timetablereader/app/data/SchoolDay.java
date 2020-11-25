package com.timetablereader.app.data;

import com.timetablereader.app.data.SchoolHour;

import java.util.ArrayList;

public class SchoolDay {
    private ArrayList<SchoolHour> schoolHours;
    private String dayName;

    public ArrayList<SchoolHour> getSchoolHours() {
        return schoolHours;
    }

    public void setSchoolHours(ArrayList<SchoolHour> schoolHours) {
        this.schoolHours = schoolHours;
    }

    public String getDayName() {
        return dayName;
    }

    public void setDayName(String dayName) {
        this.dayName = dayName;
    }

    public SchoolDay(ArrayList<SchoolHour> schoolHours, String dayName) {
        this.schoolHours = schoolHours;
        this.dayName = dayName;
    }


}
