package com.leachchen.testsystemalbum.Bean;

import android.database.Cursor;

/**
 * ClassName:   PhoneAlbumClassifyBean.java
 * Description:
 * Author :     leach.chen
 * Date:        2018/4/10 11:19
 **/

public class PhoneAlbumClassifyBean {

    //{"bucket_display_name":"Camera","fileCount":1,"filePath":"/storage/emulated/0/DCIM/Camera/IMG_20180410_103950667.jpg"}

    /**
     {
     "bucket_display_name": "Camera",
     "fileCount": 1,
     "filePath": "/storage/emulated/0/DCIM/Camera/IMG_20180410_103950667.jpg"
     }
     */

    private String bucket_display_name;
    private int fileCount;
    private String filePath;

    public PhoneAlbumClassifyBean(Cursor cursor)
    {
        this.bucket_display_name = cursor.getString(0);
        this.fileCount = cursor.getInt(1);
        this.filePath = cursor.getString(2);
    }

    public String getBucket_display_name() {
        return bucket_display_name;
    }

    public void setBucket_display_name(String bucket_display_name) {
        this.bucket_display_name = bucket_display_name;
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
