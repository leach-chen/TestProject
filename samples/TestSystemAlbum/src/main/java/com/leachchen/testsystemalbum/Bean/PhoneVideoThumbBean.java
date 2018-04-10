package com.leachchen.testsystemalbum.Bean;

import android.database.Cursor;

/**
 * ClassName:   PhoneVideoThumbBean.java
 * Description:
 * Author :     leach.chen
 * Date:        2018/4/10 11:13
 **/

public class PhoneVideoThumbBean {

    //{"_data":"/storage/emulated/0/DCIM/.thumbnails/1523269283358.jpg","_id":1,"height":512,"kind":1,"video_id":95935,"width":288}

    /**
     {
     "_data": "/storage/emulated/0/DCIM/.thumbnails/1523269283358.jpg",
     "_id": 1,
     "height": 512,
     "kind": 1,
     "video_id": 95935,
     "width": 288
     }
     */

    private int _id;
    private String _data;
    private int video_id;
    private int kind;
    private int width;
    private int height;

    public PhoneVideoThumbBean(Cursor cursor)
    {
        this._id = cursor.getInt(0);
        this._data = cursor.getString(1);
        this.video_id = cursor.getInt(2);
        this.kind = cursor.getInt(3);
        this.width = cursor.getInt(4);
        this.height = cursor.getInt(5);
    }

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public String get_data() {
        return _data;
    }

    public void set_data(String _data) {
        this._data = _data;
    }

    public int getVideo_id() {
        return video_id;
    }

    public void setVideo_id(int video_id) {
        this.video_id = video_id;
    }

    public int getKind() {
        return kind;
    }

    public void setKind(int kind) {
        this.kind = kind;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }
}
