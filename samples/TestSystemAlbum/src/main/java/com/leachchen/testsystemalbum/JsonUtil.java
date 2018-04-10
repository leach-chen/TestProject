package com.leachchen.testsystemalbum;


import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import org.json.JSONObject;
import org.json.XML;

import java.lang.reflect.Type;

/**
 * ClassName:   JsonHelp.java
 * Description:
 * Author :     leach.chen
 * Date:        2017/6/17 16:58
 **/

public class JsonUtil {

    public static final  int JSON_TYPE1 = 1;
    public static final  int JSON_TYPE2 = 2;

    public static String objectToJson(Object obj, int method) {
        if (obj == null) return "";
        try {
            if (method == 1) {
                Gson gson = new Gson();
                String obj2 = gson.toJson(obj);
                return obj2;
            } else if (method == 2) {
                Gson gson2 = new GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE).create();
                String obj2 = gson2.toJson(obj);
                return obj2;
            }
        } catch (Exception e) {

        }
        return "";
    }

    public static String xmlToJson(String xml) {
        String str = "";
        try {
            JSONObject jsonObj = null;
            jsonObj = XML.toJSONObject(xml);
            str = jsonObj.toString();
        } catch (Exception e) {
        }
        if (str == null) return "";
        return str;
    }

    public static  <T> T jsonToObject(String json,Type type) {
        try {
            Gson gson = new Gson();
            T data = gson.fromJson(json,type);
            return data;
        } catch (Exception e) {
        }
        return null;
    }

    /**
     * 设置值
     * @param object
     * @param key
     * @param value
     */
    public static void putValue(JSONObject object, String key, Object value){
        try{
            object.put(key, value);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * 获取String值
     * @param resultJson
     * @param key
     * @return
     */
    public static String getString(String resultJson, String key) {
        try {
            JSONObject jsonObject = new JSONObject(resultJson);
            return jsonObject.getString(key);
        }catch (Exception e){
            return resultJson;
        }
    }

    /**
     * 获取double值
     * @param resultJson
     * @param key
     * @return
     */
    public static double getDouble(String resultJson, String key) {
        try {
            JSONObject jsonObject = new JSONObject(resultJson);
            return jsonObject.getDouble(key);
        }catch (Exception e){
            return 0d;
        }
    }

    /**
     * 获取int值
     * @param resultJson
     * @param key
     * @return
     */
    public static int getInt(String resultJson, String key) {
        try {
            JSONObject jsonObject = new JSONObject(resultJson);
            return jsonObject.getInt(key);
        }catch (Exception e){
            return 0;
        }
    }

    /**
     * 将xml数据转成json,再将json转成对象
     * @param xml
     * @return
     */
    public static <T> T xmlToObject(String xml) {
        return  JsonUtil.jsonToObject(JsonUtil.xmlToJson(xml), new TypeToken<T>() {}.getType());
    }
}
