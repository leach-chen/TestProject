package com.leachchen.testsystemalbum.Bean;

import android.database.Cursor;

/**
 * ClassName:   PhoneVideoThumbBean.java
 * Description:
 * Author :     leach.chen
 * Date:        2018/4/10 11:13
 **/

public class PhoneImageThumbBean {

    //{"_data":"/storage/emulated/0/DCIM/.thumbnails/1519887372725+1579989740.jpg","_id":207,"height":270,"image_id":94814,"kind":135956,"width":480}
    /**
     {
     "_data": "/storage/emulated/0/DCIM/.thumbnails/1519887372725+1579989740.jpg",
     "_id": 207,
     "height": 270,
     "image_id": 94814,
     "kind": 135956,
     "width": 480
     }
     */

    private int _id;
    private String _data;
    private int image_id;
    private int kind;
    private int width;
    private int height;

    public PhoneImageThumbBean(Cursor cursor)
    {
        this._id = cursor.getInt(0);
        this._data = cursor.getString(1);
        this.image_id = cursor.getInt(2);
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

    public int getImage_id() {
        return image_id;
    }

    public void setImage_id(int image_id) {
        this.image_id = image_id;
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
