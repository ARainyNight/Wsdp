package com.example.wsdp2.fragment;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.wsdp2.R;
import com.example.wsdp2.gson.DataJSON;
import com.example.wsdp2.utils.L;
import com.example.wsdp2.utils.Utils;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.reflect.TypeToken;
import com.veken.chartview.bean.ChartBean;
import com.veken.chartview.drawtype.DrawBgType;
import com.veken.chartview.drawtype.DrawConnectLineType;
import com.veken.chartview.drawtype.DrawLineType;
import com.veken.chartview.view.LineChartView;
import com.xiasuhuei321.loadingdialog.view.LoadingDialog;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.internal.Util;

/**
 * Created by lin on 2018/9/25.
 * 描述:
 */
public class DataFragment extends Fragment {

    private ArrayList<ChartBean> lineChartBeanList;
    private LineChartView lineChartView;

    private LoadingDialog mDialog;

    private List<DataJSON> dataJSONList= new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_data, container, false);

        //初始化温度折线图
        initTempLineChartView(view);
        return view;
    }

    private void initTempLineChartView(View view) {
        lineChartView = view.findViewById(R.id.chart_view);
        lineChartView.setyLableText("温度折线图");
        lineChartView.setDrawBgType(DrawBgType.DrawBitmap);
        lineChartView.setShowPicResource(R.mipmap.click_icon);

        lineChartView.setDrawConnectLineType(DrawConnectLineType.DrawDottedLine);
        lineChartView.setClickable(true);

        lineChartView.setNeedDrawConnectYDataLine(true);
        lineChartView.setConnectLineColor(getResources().getColor(R.color.default_color));

        lineChartView.setNeedBg(true);
        lineChartView.setDrawLineType(DrawLineType.Draw_Curve);


        //初始化温度数据
        initTempData(view);
    }

    private void initTempData(View view) {
//        if (lineChartBeanList ==null){
//            lineChartBeanList =new ArrayList<>();
//        }
//        lineChartView.setDefaultTextSize(24);
//        Random random =new Random();
//        for (int i=0 ;i <8 ; i++){
//            ChartBean lineChartBean =new ChartBean();
//            lineChartBean.setValue(String.valueOf(random.nextInt(30)));
//            lineChartBean.setDate(String.valueOf(i));
//            lineChartBeanList.add(lineChartBean);
//        }
//        lineChartView.setData(lineChartBeanList);
        mDialog = new LoadingDialog(getActivity());
        mDialog.setLoadingText("加载中...")
                .setSuccessText("加载成功!")
                .setFailedText("加载失败!")
                .setInterceptBack(true)
                .setLoadSpeed(LoadingDialog.Speed.SPEED_TWO)
                .setRepeatCount(0)
                .setDrawColor(Color.WHITE)
                .show();

        initTempDataJson(view);

    }

    private void initTempDataJson(View view) {
        OkHttpClient client = new OkHttpClient();
        final Request request = new Request.Builder()
                .url(Utils.CUI_JSON)
                .get().build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                L.d("请求调用失败。。。");
                mDialog.loadFailed();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()) {
                    final String json = response.body().string();

                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            jsonToEntity(json);
                            mDialog.loadSuccess();
                        }
                    });
                }
            }
        });
    }

    private void jsonToEntity(String json) {
        Gson gson = new Gson();

        DataJSON dataJSON = gson.fromJson(json,new TypeToken<DataJSON>(){}.getType());

        System.out.println(json);




    }

}
