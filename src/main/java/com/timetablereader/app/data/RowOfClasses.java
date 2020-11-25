package com.timetablereader.app.data;

import com.timetablereader.app.data.MergedRegion;

import java.util.List;

public class RowOfClasses {
    private String day;
    private int rowIndex;
    private int numOfMergedRegions;
    private List<MergedRegion> mergedRegionList;

    public RowOfClasses(int index, int numOfMergedRegions, List<MergedRegion> mergedRegionList) {
        this.rowIndex = index;
        this.numOfMergedRegions = numOfMergedRegions;
        this.mergedRegionList = mergedRegionList;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }
    public int getRowIndex() {
        return rowIndex;
    }

    public void setRowIndex(int rowIndex) {
        this.rowIndex = rowIndex;
    }

    public int getNumOfMergedRegions() {
        return numOfMergedRegions;
    }

    public void setNumOfMergedRegions(int numOfMergedRegions) {
        this.numOfMergedRegions = numOfMergedRegions;
    }

    public List<MergedRegion> getMergedRegionList() {
        return mergedRegionList;
    }

    public void setMergedRegionList(List<MergedRegion> mergedRegionList) {
        this.mergedRegionList = mergedRegionList;
    }

}
