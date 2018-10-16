package com.leachchen.testpart;

import com.google.gson.annotations.SerializedName;

/**
 * ClassName:   TestBean.java
 * Description:
 * Author :     leach.chen
 * Date:        2018/10/9 15:36
 **/
public class TestBean
{

    /**
     * name : FHD P30+FHD P30
     * index : 0
     * relation : 1920*1080
     * rate : 30
     * type : 3
     */
    @SerializedName(value = "name", alternate = {"Name", "aa"})
    private String name;
    @SerializedName(value = "index", alternate = {"Index", "bb"})
    private String index;
    @SerializedName(value = "relation", alternate = {"Size", "cc"})
    private String relation;
    @SerializedName(value = "rate", alternate = {"FrameRate", "dd"})
    private String rate;
    @SerializedName(value = "type", alternate = {"Type", "ee"})
    private String type;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIndex() {
        return index;
    }

    public void setIndex(String index) {
        this.index = index;
    }

    public String getRelation() {
        return relation;
    }

    public void setRelation(String relation) {
        this.relation = relation;
    }

    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
