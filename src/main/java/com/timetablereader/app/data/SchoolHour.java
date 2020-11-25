package com.timetablereader.app.data;

import com.timetablereader.app.data.Lesson;

public class SchoolHour {
    private Lesson lesson;
    private boolean isFree;

    public SchoolHour(Lesson lesson, boolean isFree) {
        this.lesson = lesson;
        this.isFree=isFree;
    }

    public Lesson getLesson() {
        return lesson;
    }

    public void setLesson(Lesson lesson) {
        this.lesson = lesson;
    }

    public boolean isFree() {
        return isFree;
    }

    public void setFree(boolean free) {
        isFree = free;
    }
}
