package com.leachchen.testsystemalbum.Bean;

import android.database.Cursor;

/**
 * ClassName:   ResourceBean.java
 * Description:
 * Author :     leach.chen
 * Date:        2018/4/10 14:20
 **/

public class AlbumResourceBean {



    private String folderPath;

    private long id;
    private String filePath;
    private String fileName;
    private String folderName;
    private long size;
    private long date;
    private long modifyDate;
    private String resourceType;
    private double latitude;
    private double longitude;
    private long duration;

    public AlbumResourceBean(Cursor cursor, boolean isVideo)
    {
        this.id = cursor.getLong(0);
        this.filePath = cursor.getString(1);
        this.fileName = cursor.getString(2);
        this.folderName = cursor.getString(3);
        this.size = cursor.getLong(4);
        this.date = cursor.getLong(5);
        this.modifyDate = cursor.getLong(6);
        this.resourceType = cursor.getString(7);
        this.latitude = cursor.getLong(8);
        this.longitude = cursor.getLong(9);

        if(isVideo)
        {
            this.duration = cursor.getLong(10);
        }

    }


    public String getFolderPath() {
        return folderPath;
    }

    public void setFolderPath(String folderPath) {
        this.folderPath = folderPath;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFolderName() {
        return folderName;
    }

    public void setFolderName(String folderName) {
        this.folderName = folderName;
    }

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }

    public long getDate() {
        return date;
    }

    public void setDate(long date) {
        this.date = date;
    }

    public long getModifyDate() {
        return modifyDate;
    }

    public void setModifyDate(long modifyDate) {
        this.modifyDate = modifyDate;
    }

    public String getResourceType() {
        return resourceType;
    }

    public void setResourceType(String resourceType) {
        this.resourceType = resourceType;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public long getDuration() {
        return duration;
    }

    public void setDuration(long duration) {
        this.duration = duration;
    }
}
