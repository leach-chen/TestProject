package com.leachchen.testsystemalbum.Bean;

import android.database.Cursor;

/**
 * ClassName:   PhoneVideoThumbBean.java
 * Description:
 * Author :     leach.chen
 * Date:        2018/4/10 11:13
 **/

public class PhoneVideoBean {


    //{"_data":"/storage/emulated/0/IPCAM/THUMBNAIL/2017_0911_005247_018.MP4","_display_name":"2017_0911_005247_018.MP4","_id":59722,"_size":24708,"album":"THUMBNAIL","artist":"\u003cunknown\u003e","bookmark":0,"bucket_display_name":"THUMBNAIL","bucket_id":"138189003","date_added":1505353363,"date_modified":1505100394,"datetaken":1861840400,"duration":587614016,"height":0,"isprivate":0,"latitude":0.0,"longitude":0.0,"mime_type":"video/mp4","mini_thumb_magic":0,"title":"2017_0911_005247_018","width":0}

    /**
     {
     "_data": "/storage/emulated/0/IPCAM/THUMBNAIL/2017_0911_005247_018.MP4",
     "_display_name": "2017_0911_005247_018.MP4",
     "_id": 59722,
     "_size": 24708,
     "album": "THUMBNAIL",
     "artist": "<unknown>",
     "bookmark": 0,
     "bucket_display_name": "THUMBNAIL",
     "bucket_id": "138189003",
     "date_added": 1505353363,
     "date_modified": 1505100394,
     "datetaken": 1861840400,
     "duration": 587614016,
     "height": 0,
     "isprivate": 0,
     "latitude": 0.0,
     "longitude": 0.0,
     "mime_type": "video/mp4",
     "mini_thumb_magic": 0,
     "title": "2017_0911_005247_018",
     "width": 0
     }
     */

    private int _id;
    private String _data;
    private String _display_name;
    private long _size;
    private String mime_type;
    private long date_added;
    private long date_modified;
    private String title;
    private long duration;
    private String artist;
    private String album;
    private String resolution;
    private String description;
    private int isprivate;
    private String tags;
    private String category;
    private String language;
    private String mini_thumb_data;
    private double latitude;
    private double longitude;
    private int datetaken;
    private int mini_thumb_magic;
    private String bucket_id;
    private String bucket_display_name;
    private int bookmark;
    private int width;
    private int height;

    public PhoneVideoBean(Cursor cursor)
    {
        this._id= cursor.getInt(0);
        this._data= cursor.getString(1);
        this._display_name= cursor.getString(2);
        this._size= cursor.getLong(3);
        this.mime_type= cursor.getString(4);
        this.date_added= cursor.getLong(5);
        this.date_modified= cursor.getLong(6);
        this.title= cursor.getString(7);
        this.duration= cursor.getLong(8);
        this.artist= cursor.getString(9);
        this.album= cursor.getString(10);
        this.resolution= cursor.getString(11);
        this.description= cursor.getString(12);
        this.isprivate= cursor.getInt(13);
        this.tags= cursor.getString(14);
        this.category= cursor.getString(15);
        this.language= cursor.getString(16);
        this.mini_thumb_data= cursor.getString(17);
        this.latitude= cursor.getDouble(18);
        this.longitude= cursor.getDouble(19);
        this.datetaken= cursor.getInt(20);
        this.mini_thumb_magic= cursor.getInt(21);
        this.bucket_id= cursor.getString(22);
        this.bucket_display_name= cursor.getString(23);
        this.bookmark= cursor.getInt(24);
        this.width= cursor.getInt(25);
        this.height= cursor.getInt(26);
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

    public String get_display_name() {
        return _display_name;
    }

    public void set_display_name(String _display_name) {
        this._display_name = _display_name;
    }

    public long get_size() {
        return _size;
    }

    public void set_size(long _size) {
        this._size = _size;
    }

    public String getMime_type() {
        return mime_type;
    }

    public void setMime_type(String mime_type) {
        this.mime_type = mime_type;
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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public long getDuration() {
        return duration;
    }

    public void setDuration(long duration) {
        this.duration = duration;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String getAlbum() {
        return album;
    }

    public void setAlbum(String album) {
        this.album = album;
    }

    public String getResolution() {
        return resolution;
    }

    public void setResolution(String resolution) {
        this.resolution = resolution;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getIsprivate() {
        return isprivate;
    }

    public void setIsprivate(int isprivate) {
        this.isprivate = isprivate;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getMini_thumb_data() {
        return mini_thumb_data;
    }

    public void setMini_thumb_data(String mini_thumb_data) {
        this.mini_thumb_data = mini_thumb_data;
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

    public int getBookmark() {
        return bookmark;
    }

    public void setBookmark(int bookmark) {
        this.bookmark = bookmark;
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
