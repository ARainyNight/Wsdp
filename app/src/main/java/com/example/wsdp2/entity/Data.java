package com.example.wsdp2.entity;

/**
 * Created by lin on 2018/9/27.
 * 描述:
 */
public class Data {

    //日期
    private String date;
    //温度
    private String temp;
    //湿度
    private String humi;
    //光照
    private String lllumi;

    public Data(String date, String temp, String humi, String lllumi) {
        this.date = date;
        this.temp = temp;
        this.humi = humi;
        this.lllumi = lllumi;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTemp() {
        return temp;
    }

    public void setTemp(String temp) {
        this.temp = temp;
    }

    public String getHumi() {
        return humi;
    }

    public void setHumi(String humi) {
        this.humi = humi;
    }

    public String getLllumi() {
        return lllumi;
    }

    public void setLllumi(String lllumi) {
        this.lllumi = lllumi;
    }
}
