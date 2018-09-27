package com.example.wsdp2.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.wsdp2.R;
import com.example.wsdp2.adapter.DataAdapter;
import com.example.wsdp2.gson.DataJSON;
import com.example.wsdp2.utils.L;
import com.example.wsdp2.utils.Utils;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.veken.chartview.bean.ChartBean;
import com.veken.chartview.view.LineChartView;
import com.xiasuhuei321.loadingdialog.view.LoadingDialog;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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
public class DataFragment extends Fragment implements View.OnClickListener{

    private ArrayList<ChartBean> lineChartBeanList;
    private LineChartView lineChartView;

    private LoadingDialog mDialog;


    private ArrayList<DataJSON> dataJSONArrayList = new ArrayList<>();
//    private List<DataJSON> dataJSONList= new ArrayList<>();

    private DataAdapter adapter;
    private RecyclerView recyclerView;

    private Button open_btn;
    private Button close_btn;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_data, container, false);

        //初始化Recyclerview
        initRecyclerView(view);

        open_btn=(Button)view.findViewById(R.id.open_btn);
        close_btn=(Button)view.findViewById(R.id.close_btn);
        open_btn.setOnClickListener(this);
        close_btn.setOnClickListener(this);
        return view;
    }

    private void initRecyclerView(View view) {

//        lineChartView = view.findViewById(R.id.chart_view);
//        lineChartView.setyLableText("温度折线图");
//        lineChartView.setDrawBgType(DrawBgType.DrawBitmap);
//        lineChartView.setShowPicResource(R.mipmap.click_icon);
//
//        lineChartView.setDrawConnectLineType(DrawConnectLineType.DrawDottedLine);
//        lineChartView.setClickable(true);
//
//        lineChartView.setNeedDrawConnectYDataLine(true);
//        lineChartView.setConnectLineColor(getResources().getColor(R.color.default_color));
//
//        lineChartView.setNeedBg(true);
//        lineChartView.setDrawLineType(DrawLineType.Draw_Curve);
//
//        if (lineChartBeanList == null) {
//            lineChartBeanList = new ArrayList<>();
//        }
//        lineChartView.setDefaultTextSize(24);
//        Random random = new Random();
//        for (int i = 0; i < 8; i++) {
//            ChartBean lineChartBean = new ChartBean();
//            lineChartBean.setValue(String.valueOf(random.nextInt(30)));
//            lineChartBean.setDate(String.valueOf(i));
//            lineChartBeanList.add(lineChartBean);
//        }
//        lineChartView.setData(lineChartBeanList);



        //初始化温度数据
        L.i("......................initTempData........................................");
        System.out.println("......................initTempData........................................");
        initTempData(view);

        recyclerView = (RecyclerView)view.findViewById(R.id.data_recyclerview);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
//        DataAdapter adapter = new DataAdapter(dataList);
//        recyclerView.setAdapter(adapter);
    }

    private void initTempData(View view) {

//        mDialog = new LoadingDialog(getActivity());
//        mDialog.setLoadingText("加载中...")
//                .setSuccessText("加载成功!")
//                .setFailedText("加载失败!")
//                .setInterceptBack(true)
//                .setLoadSpeed(LoadingDialog.Speed.SPEED_TWO)
//                .setRepeatCount(0)
//                .setDrawColor(Color.WHITE)
//                .show();


        initTempDataJson(view);



    }

    private void initTempDataJson(View view) {
        OkHttpClient client = new OkHttpClient();
        final Request request = new Request.Builder()
                .url(Utils.DATA_JSON)
                .get().build();

        client.newCall(request).enqueue(new Callback() {

            @Override
            public void onFailure(Call call, IOException e) {
                L.d("请求调用失败。。。");
//                mDialog.loadFailed();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                L.d("请求调用成功");
                System.out.println("请求调用成功");
                if (response.isSuccessful()) {
                    final String json = response.body().string();
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            jsonToEntity(json);
//
//                            mDialog.loadSuccess();
                        }
                    });
                }
            }
        });
    }

    private void jsonToEntity(String json) {


        System.out.println(json+"..................!!!!!!!!!!!!1");
//        System.out.println(json);
        JsonParser parser = new JsonParser();
        JsonArray jsonArray = parser.parse(json).getAsJsonArray();

        Gson gson = new Gson();


        for (JsonElement data : jsonArray) {
            DataJSON dataJSON = gson.fromJson(data, DataJSON.class);
            double temp = dataJSON.getTemp();
            if (temp>=500){
                Utils.Dialog(getActivity(),"温度超标");
            }
            dataJSONArrayList.add(dataJSON);
        }

        DataAdapter adapter = new DataAdapter(dataJSONArrayList);
        recyclerView.setAdapter(adapter);



    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.open_btn:
                L.d("开灯");
                OkHttpClient client1= new OkHttpClient();
                Request request1 = new Request.Builder()
                        .url(Utils.CONTROL_URL+"?flag="+Utils.OPEN_URL)
                        .get().build();
                client1.newCall(request1).enqueue(new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {
                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(getActivity(),"开灯失败",Toast.LENGTH_SHORT).show();
                            }
                        });
                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(getActivity(),"开灯成功",Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                });
                //开灯
                break;
            case R.id.close_btn:
                L.d("关灯");
                //关灯
                OkHttpClient client2= new OkHttpClient();
                Request request2 = new Request.Builder()
                        .url(Utils.CONTROL_URL+"?flag="+Utils.CLOSE_URL)
                        .get().build();
                client2.newCall(request2).enqueue(new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {
                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(getActivity(),"关灯失败",Toast.LENGTH_SHORT).show();
                            }
                        });
                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(getActivity(),"关灯成功",Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                });
                break;
        }
    }
}
