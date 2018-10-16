package com.leachchen.testpart;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.google.gson.Gson;

import java.util.Map;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //String json1 = "{\"Item\":{\"Name\":\"FHD P30+FHD P30\",\"Index\":\"0\",\"Size\":\"1920*1080\",\"FrameRate\":\"30\",\"Type\":\"3\"}}";
        String json1 = "{\n" +
                "\t\"LIST\": {\n" +
                "\t\t\"ALLFile\": {\n" +
                "\t\t\t\"File\": {\n" +
                "\t\t\t\t\"NAME\": \"20171018_094703_005.MP4\",\n" +
                "\t\t\t\t\"FPATH\": \"A:\\\\VAVA\\\\SNAPSHOT\\\\20171018_094703_005.MP4\",\n" +
                "\t\t\t\t\"SPATH\": \"A:\\\\VAVA\\\\SNAPSHOT\\\\20171018_094703_005_S.MP4\",\n" +
                "\t\t\t\t\"SIZE\": 43057561,\n" +
                "\t\t\t\t\"TIME\": \"2017/10/18 09:47:22\"\n" +
                "\t\t\t}\n" +
                "\t\t}\n" +
                "\t}\n" +
                "}";
  /*      String json1 = "{\n" +
                "\n" +
                "\t\t\"aaa\": \"FHD P30+FHD P30\",\n" +
                "\t\t\"bbb\": \"0\",\n" +
                "\t\t\"ccc\": \"1920*1080\",\n" +
                "\t\t\"ddd\": \"30\",\n" +
                "\t\t\"eee\": \"3\"\n" +
                "\n" +
                "}";*/


        String json2 = "{\n" +
                "\n" +
                "\t\t\"aa\": \"FHD P30+FHD P30\",\n" +
                "\t\t\"bb\": \"0\",\n" +
                "\t\t\"cc\": \"1920*1080\",\n" +
                "\t\t\"dd\": \"30\",\n" +
                "\t\t\"ee\": \"3\"\n" +
                "\n" +
                "}";

        Gson gson = new Gson();
        Map testBean = gson.fromJson(json1, Map.class);

        Map testBean1 = gson.fromJson(json2, Map.class);

    }
}
