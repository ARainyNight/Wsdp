package com.example.wsdp2.utils;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

/**
 * Created by lin on 2018/9/25.
 * 描述:
 */
public class Utils {

    //和风天气请求地址
    public static String WEATHER_KEY ="https://free-api.heweather.com/s6/weather/now?location=taiyuan&key=387ff5d6544740efab596a2e6a935974";

    //崔哲的json数据
    public static String CUI_JSON="http://10.0.116.74:8080/sensor/json";

    //DATA
    public static String DATA_JSON ="http://10.0.116.57:9998/GreenHouse/lists";

    //开关灯url
    public static String CONTROL_URL="http://10.0.116.57:9998/GreenHouse/control";

    //开灯
    public static String OPEN_URL="0F";

    //关灯
    public static String CLOSE_URL="00";

    //查询今天数据
    public static String QUERY_TODAY_DATA="http://10.0.116.57:9998/GreenHouse/listCategory";

    //封装提示框
    public static void Dialog(Context mContext, String message){
        final AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
        builder.setTitle("提示")
                .setMessage(message)
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .create()
                .show();
    }
}
