package com.leachchen.testsystemalbum.Bean;

import android.database.Cursor;

/**
 * ClassName:   PhoneAlbumClassifyBean.java
 * Description:
 * Author :     leach.chen
 * Date:        2018/4/10 11:19
 **/

public class AlbumClassifyBean {

    //{"bucket_display_name":"Camera","fileCount":1,"filePath":"/storage/emulated/0/DCIM/Camera/IMG_20180410_103950667.jpg"}

    /**
     {
     "bucket_display_name": "Camera",
     "fileCount": 1,
     "filePath": "/storage/emulated/0/DCIM/Camera/IMG_20180410_103950667.jpg"
     }
     */

    private long id;
    private String folderName;
    private int fileCount;
    private String filePath;

    public AlbumClassifyBean(Cursor cursor)
    {
        this.id = cursor.getLong(0);
        this.folderName = cursor.getString(1);
        this.fileCount = cursor.getInt(2);
        this.filePath = cursor.getString(3);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFolderName() {
        return folderName;
    }

    public void setFolderName(String folderName) {
        this.folderName = folderName;
    }

    public int getFileCount() {
        return fileCount;
    }

    public void setFileCount(int fileCount) {
        this.fileCount = fileCount;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }
}
