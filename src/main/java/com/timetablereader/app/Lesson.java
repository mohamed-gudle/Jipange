package com.timetablereader.app;

public class Lesson {
    private String name;
    private String venue;
    private String lecturerName;
    private String code;

    public Lesson(String name, String venue, String lecturerName, String code) {
        this.name = name;
        this.venue = venue;
        this.lecturerName = lecturerName;
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getVenue() {
        return venue;
    }

    public void setVenue(String venue) {
        this.venue = venue;
    }

    public String getLecturerName() {
        return lecturerName;
    }

    public void setLecturerName(String lecturerName) {
        this.lecturerName = lecturerName;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
