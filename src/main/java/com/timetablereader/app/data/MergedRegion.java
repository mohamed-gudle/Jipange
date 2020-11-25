package com.timetablereader.app.data;

public class MergedRegion {
   private int startingColumn;
    private int range;

    public MergedRegion(int startingColumn, int range) {
        this.startingColumn = startingColumn;
        this.range = range;
    }

    public int getStartingColumn() {
        return startingColumn;
    }

    public void setStartingColumn(int startingColumn) {
        this.startingColumn = startingColumn;
    }

    public int getRange() {
        return range;
    }

    public void setRange(int range) {
        this.range = range;
    }
}
