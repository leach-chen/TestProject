package com.leachchen.testsystemalbum.Bean;

import android.database.Cursor;

/**
 * ClassName:   PhoneVideoThumbBean.java
 * Description:
 * Author :     leach.chen
 * Date:        2018/4/10 11:13
 **/

public class PhoneImageBean {

    //{"_data":"/storage/emulated/0/Pictures/Screenshots/Screenshot_20170830-152053.png","_display_name":"Screenshot_20170830-152053.png","_id":48439,"_size":36114,"bucket_display_name":"Screenshots","bucket_id":"1028075469","date_added":1504077654,"date_modified":1504077654,"datetaken":839100244,"height":1280,"isprivate":0,"latitude":0.0,"longitude":0.0,"mime_type":"image/png","mini_thumb_magic":0,"orientation":0,"title":"Screenshot_20170830-152053.png","width":720}

    /**
     {
     "_data": "/storage/emulated/0/Pictures/Screenshots/Screenshot_20170830-152053.png",
     "_display_name": "Screenshot_20170830-152053.png",
     "_id": 48439,
     "_size": 36114,
     "bucket_display_name": "Screenshots",
     "bucket_id": "1028075469",
     "date_added": 1504077654,
     "date_modified": 1504077654,
     "datetaken": 839100244,
     "height": 1280,
     "isprivate": 0,
     "latitude": 0.0,
     "longitude": 0.0,
     "mime_type": "image/png",
     "mini_thumb_magic": 0,
     "orientation": 0,
     "title": "Screenshot_20170830-152053.png",
     "width": 720
     }
     **/


    private long _id;
    private String _data;
    private long _size;
    private String _display_name;
    private String mime_type;
    private String title;
    private long date_added;
    private long date_modified;
    private String description;
    private String picasa_id;
    private int isprivate;
    private double latitude;
    private double longitude;
    private int datetaken;
    private int orientation;
    private int mini_thumb_magic;
    private String bucket_id;
    private String bucket_display_name;
    private int width;
    private int height;

    public PhoneImageBean(Cursor cursor)
    {
        this._id = cursor.getLong(0);
        this._data = cursor.getString(1);
        this._size = cursor.getLong(2);
        this._display_name = cursor.getString(3);
        this.mime_type = cursor.getString(4);
        this.title = cursor.getString(5);
        this.date_added = cursor.getLong(6);
        this.date_modified = cursor.getLong(7);
        this.description = cursor.getString(8);
        this.picasa_id = cursor.getString(9);
        this.isprivate = cursor.getInt(10);
        this.latitude = cursor.getDouble(11);
        this.longitude = cursor.getDouble(12);
        this.datetaken = cursor.getInt(13);
        this.orientation = cursor.getInt(14);
        this.mini_thumb_magic = cursor.getInt(15);
        this.bucket_id = cursor.getString(16);
        this.bucket_display_name = cursor.getString(17);
        this.width = cursor.getInt(18);
        this.height = cursor.getInt(19);
    }

    public long get_id() {
        return _id;
    }

    public void set_id(long _id) {
        this._id = _id;
    }

    public String get_data() {
        return _data;
    }

    public void set_data(String _data) {
        this._data = _data;
    }

    public long get_size() {
        return _size;
    }

    public void set_size(long _size) {
        this._size = _size;
    }

    public String get_display_name() {
        return _display_name;
    }

    public void set_display_name(String _display_name) {
        this._display_name = _display_name;
    }

    public String getMime_type() {
        return mime_type;
    }

    public void setMime_type(String mime_type) {
        this.mime_type = mime_type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public long getDate_added() {
        return date_added;
    }

    public void setDate_added(long date_added) {
        this.date_added = date_added;
    }

    public long getDate_modified() {
        return date_modified;
    }

    public void setDate_modified(long date_modified) {
        this.date_modified = date_modified;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPicasa_id() {
        return picasa_id;
    }

    public void setPicasa_id(String picasa_id) {
        this.picasa_id = picasa_id;
    }

    public int getIsprivate() {
        return isprivate;
    }

    public void setIsprivate(int isprivate) {
        this.isprivate = isprivate;
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

    public int getDatetaken() {
        return datetaken;
    }

    public void setDatetaken(int datetaken) {
        this.datetaken = datetaken;
    }

    public int getOrientation() {
        return orientation;
    }

    public void setOrientation(int orientation) {
        this.orientation = orientation;
    }

    public int getMini_thumb_magic() {
        return mini_thumb_magic;
    }

    public void setMini_thumb_magic(int mini_thumb_magic) {
        this.mini_thumb_magic = mini_thumb_magic;
    }

    public String getBucket_id() {
        return bucket_id;
    }

    public void setBucket_id(String bucket_id) {
        this.bucket_id = bucket_id;
    }

    public String getBucket_display_name() {
        return bucket_display_name;
    }

    public void setBucket_display_name(String bucket_display_name) {
        this.bucket_display_name = bucket_display_name;
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
