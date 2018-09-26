package com.example.wsdp2.gson;

/**
 * Created by lin on 2018/9/26.
 * 描述:
 */
public class DataJSON {


    /**
     * date : 2018-09-17 21:54:07.0
     * humi : 100.0
     * id : 6
     * light : 129.58
     * temp : 615.72
     */

    private String date;
    private String humi;
    private int id;
    private String light;
    private String temp;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getHumi() {
        return humi;
    }

    public void setHumi(String humi) {
        this.humi = humi;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLight() {
        return light;
    }

    public void setLight(String light) {
        this.light = light;
    }

    public String getTemp() {
        return temp;
    }

    public void setTemp(String temp) {
        this.temp = temp;
    }
}
