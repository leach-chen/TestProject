package com.leachchen.testsystemalbum;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.leachchen.testsystemalbum.Bean.AlbumResourceBean;
import com.leachchen.testsystemalbum.Bean.AlbumClassifyBean;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //SystemAlbumHelp.getInstance(this).getPhoneImage();
        //SystemAlbumHelp.getInstance(this).getPhoneImageThumb();
        //SystemAlbumHelp.getInstance(this).getPhoneVideo();
        //SystemAlbumHelp.getInstance(this).getPhoneVideoThumb();
        //SystemAlbumHelp.getInstance(this).getPhoneAlbumClassify();

        List<AlbumClassifyBean> classifyBeanList = SystemAlbumHelp.getInstance(this).getPhoneAlbumClassify();

        Map<Long, List<AlbumResourceBean>> listMap = new HashMap<>();
        if (classifyBeanList != null) {
            for (int i = 0; i < classifyBeanList.size(); i++) {
                AlbumClassifyBean bean = classifyBeanList.get(i);

                //if(bean.getId() == -1739773001) {
                    List<AlbumResourceBean> resourceBeanList = new ArrayList<>();
                    List<AlbumResourceBean> imageList = SystemAlbumHelp.getInstance(this).getAlbumResource(bean.getId(), false);
                    List<AlbumResourceBean> videoList = SystemAlbumHelp.getInstance(this).getAlbumResource(bean.getId(), true);

                    resourceBeanList.addAll(imageList);
                    resourceBeanList.addAll(videoList);
                    SystemAlbumHelp.getInstance(this).sortPhotoList(resourceBeanList);

                    listMap.put(bean.getId(), resourceBeanList);

                    for(int j = 0; j < resourceBeanList.size();j++)
                    {
                        Log.e("mmm",resourceBeanList.get(j).getFileName()+"  "+date2String(resourceBeanList.get(j).getModifyDate() * 1000,"yyyy-MM-dd HH:mm:ss"));
                    }
               // }
            }
        }
    }

    public  String date2String(long time, String format) {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        String s = sdf.format(time);
        return s;
    }


}
