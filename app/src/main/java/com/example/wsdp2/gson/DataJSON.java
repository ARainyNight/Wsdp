package com.example.wsdp2.gson;

/**
 * Created by lin on 2018/9/26.
 * 描述:
 */
public class DataJSON {

    /**
     * date : 1537718400000
     * temp : 2.1
     * humi : 3.2
     * id : 1
     * illu : 222
     */

    private long date;
    private double temp;
    private double humi;
    private int id;
    private int illu;

    public long getDate() {
        return date;
    }

    public void setDate(long date) {
        this.date = date;
    }

    public double getTemp() {
        return temp;
    }

    public void setTemp(double temp) {
        this.temp = temp;
    }

    public double getHumi() {
        return humi;
    }

    public void setHumi(double humi) {
        this.humi = humi;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIllu() {
        return illu;
    }

    public void setIllu(int illu) {
        this.illu = illu;
    }
}
