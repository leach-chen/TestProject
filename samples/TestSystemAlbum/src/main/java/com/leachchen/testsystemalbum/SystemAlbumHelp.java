package com.leachchen.testsystemalbum;

import android.content.Context;
import android.database.Cursor;
import android.provider.MediaStore;
import android.util.Log;

import com.leachchen.testsystemalbum.Bean.PhoneAlbumClassifyBean;
import com.leachchen.testsystemalbum.Bean.PhoneImageBean;
import com.leachchen.testsystemalbum.Bean.PhoneImageThumbBean;
import com.leachchen.testsystemalbum.Bean.PhoneVideoBean;
import com.leachchen.testsystemalbum.Bean.PhoneVideoThumbBean;

import java.util.ArrayList;
import java.util.List;

/**
 * ClassName:   SystemAlbumHelp.java
 * Description:
 * Author :     leach.chen
 * <p>
 * Date:        2018/4/10 11:12
 **/

public class SystemAlbumHelp {

    private static SystemAlbumHelp mInstance;
    private Context mContext;

    public SystemAlbumHelp(Context context) {
        this.mContext = context;
    }

    public static SystemAlbumHelp getInstance(Context context) {
        if (mInstance == null) {
            mInstance = new SystemAlbumHelp(context);
        }
        return mInstance;
    }

    public List<PhoneAlbumClassifyBean> getPhoneAlbumClassify() {
        List<PhoneAlbumClassifyBean> list = new ArrayList<>();
        String columns[] = new String[] { MediaStore.Images.Media.BUCKET_DISPLAY_NAME, "count(" + MediaStore.Images.Media.BUCKET_DISPLAY_NAME + ")", MediaStore.Images.Media.DATA };
        Cursor cursor = mContext.getContentResolver().query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, columns, "1=1) group by (" + MediaStore.Images.Media.BUCKET_DISPLAY_NAME, null,
                "count(" + MediaStore.Images.Media.BUCKET_DISPLAY_NAME + ") DESC");
        // 显示第一张图片，但是首先要判断一下，Cursor是否有值
        if(null != cursor) {
            while (cursor.moveToNext()) {
                PhoneAlbumClassifyBean phoneAlbumClassifyBean = new PhoneAlbumClassifyBean(cursor);
                list.add(phoneAlbumClassifyBean);
                Log.e("mytest",JsonUtil.objectToJson(phoneAlbumClassifyBean,JsonUtil.JSON_TYPE1));
                Log.e("mytest","-------------------------------------------------------------------------");
            }
            cursor.close();
        }
        //数据回传
        return list;
    }

    public List<PhoneImageThumbBean> getPhoneImageThumb() {
        ArrayList<PhoneImageThumbBean> dataList = new ArrayList<>();
        // 指定获取的列
        String columns[] = new String[]{MediaStore.Images.Thumbnails.DATA};
        Cursor cursor = mContext.getContentResolver().query(MediaStore.Images.Thumbnails.EXTERNAL_CONTENT_URI, null, null, null, null);
        while (cursor.moveToNext()) {
            PhoneImageThumbBean phoneImageBean = new PhoneImageThumbBean(cursor);
            dataList.add(phoneImageBean);
            Log.e("mytest",JsonUtil.objectToJson(phoneImageBean,JsonUtil.JSON_TYPE1));
            Log.e("mytest","-------------------------------------------------------------------------");
        }
        cursor.close();
        return dataList;
    }

    public List<PhoneImageBean> getPhoneImage() {
        ArrayList<PhoneImageBean> dataList = new ArrayList<>();
        // 指定获取的列
        String columns[] = new String[]{MediaStore.Images.Media.DATA};
        Cursor cursor = mContext.getContentResolver().query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, null, null, null, null);
        while (cursor.moveToNext()) {
            PhoneImageBean phoneImageBean = new PhoneImageBean(cursor);
            dataList.add(phoneImageBean);
            Log.e("mytest",JsonUtil.objectToJson(phoneImageBean,JsonUtil.JSON_TYPE1));
            Log.e("mytest","-------------------------------------------------------------------------");
        }
        cursor.close();
        return dataList;
    }

    public List<PhoneVideoThumbBean> getPhoneVideoThumb() {
        ArrayList<PhoneVideoThumbBean> dataList = new ArrayList<>();
        // 指定获取的列
        String columns[] = new String[]{MediaStore.Video.Thumbnails.DATA};
        Cursor cursor = mContext.getContentResolver().query(MediaStore.Video.Thumbnails.EXTERNAL_CONTENT_URI, null, null, null, null);
        while (cursor.moveToNext()) {
            PhoneVideoThumbBean phoneImageBean = new PhoneVideoThumbBean(cursor);
            dataList.add(phoneImageBean);
            Log.e("mytest",JsonUtil.objectToJson(phoneImageBean,JsonUtil.JSON_TYPE1));
            Log.e("mytest","-------------------------------------------------------------------------");
        }
        cursor.close();
        return dataList;
    }

    public List<PhoneVideoBean> getPhoneVideo() {
        ArrayList<PhoneVideoBean> dataList = new ArrayList<>();
        // 指定获取的列
        String columns[] = new String[]{MediaStore.Video.Media.MINI_THUMB_MAGIC};
        Cursor cursor = mContext.getContentResolver().query(MediaStore.Video.Media.EXTERNAL_CONTENT_URI, null, null, null, null);
        while (cursor.moveToNext()) {
            PhoneVideoBean phoneImageBean = new PhoneVideoBean(cursor);
            dataList.add(phoneImageBean);
            Log.e("mytest",JsonUtil.objectToJson(phoneImageBean,JsonUtil.JSON_TYPE1));
            Log.e("mytest","-------------------------------------------------------------------------");
        }
        cursor.close();
        return dataList;
    }


}
