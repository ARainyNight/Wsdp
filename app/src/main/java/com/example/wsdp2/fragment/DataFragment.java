package com.example.wsdp2.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.wsdp2.R;
import com.example.wsdp2.TodayDataActivity;
import com.example.wsdp2.adapter.DataAdapter;
import com.example.wsdp2.gson.DataJSON;
import com.example.wsdp2.utils.L;
import com.example.wsdp2.utils.Utils;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.veken.chartview.bean.ChartBean;
import com.veken.chartview.drawtype.DrawBgType;
import com.veken.chartview.drawtype.DrawConnectLineType;
import com.veken.chartview.drawtype.DrawLineType;
import com.veken.chartview.view.LineChartView;
import com.xiasuhuei321.loadingdialog.view.LoadingDialog;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by lin on 2018/9/25.
 * 描述:
 */
public class DataFragment extends Fragment implements View.OnClickListener{

    //温度折线图
    private ArrayList<ChartBean> lineChartBeanList1;
    private LineChartView lineChartView1;

    //湿度度折线图
    private ArrayList<ChartBean> lineChartBeanList2;
    private LineChartView lineChartView2;

    //光照度折线图
    private ArrayList<ChartBean> lineChartBeanList3;
    private LineChartView lineChartView3;

    private LoadingDialog mDialog;


    private ArrayList<DataJSON> dataJSONArrayList = new ArrayList<>();
//    private List<DataJSON> dataJSONList= new ArrayList<>();

    private DataAdapter adapter;
    private RecyclerView recyclerView;

    private Button open_btn;
    private Button close_btn;
    private Button query_today_data;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_data, container, false);



        lineChartView1 = view.findViewById(R.id.chart_view1);
        lineChartView2 = view.findViewById(R.id.chart_view2);
        lineChartView3 = view.findViewById(R.id.chart_view3);

        //初始化LineChart
        initLineChart(view);

        open_btn=(Button)view.findViewById(R.id.open_btn);
        close_btn=(Button)view.findViewById(R.id.close_btn);
        query_today_data=(Button)view.findViewById(R.id.query_today_data);

        open_btn.setOnClickListener(this);
        close_btn.setOnClickListener(this);
        query_today_data.setOnClickListener(this);
        return view;
    }

    private void initLineChart(View view) {


        //温度折线图
        if (lineChartBeanList1 == null) {
            lineChartBeanList1 = new ArrayList<>();
        }
        lineChartView1.setDefaultTextSize(24);
        Random random = new Random();
        for (int i = 0; i < 7; i++) {
            ChartBean lineChartBean = new ChartBean();
            lineChartBean.setValue(String.valueOf(random.nextInt(30)));
            lineChartBean.setDate(String.valueOf(i));
            lineChartBeanList1.add(lineChartBean);
        }
        lineChartView1.setData(lineChartBeanList1);
        lineChartView1.setyLableText("温度折线图");
        lineChartView1.setDrawBgType(DrawBgType.DrawBitmap);
        lineChartView1.setShowPicResource(R.mipmap.click_icon);
        lineChartView1.setDrawConnectLineType(DrawConnectLineType.DrawDottedLine);
        lineChartView1.setClickable(true);
        lineChartView1.setNeedDrawConnectYDataLine(true);
        lineChartView1.setConnectLineColor(getResources().getColor(R.color.default_color));
        lineChartView1.setNeedBg(true);
        lineChartView1.setDrawLineType(DrawLineType.Draw_Curve);




        //湿度折线图
        if (lineChartBeanList2 == null) {
            lineChartBeanList2 = new ArrayList<>();
        }
        lineChartView2.setDefaultTextSize(24);
        Random random2 = new Random();
        for (int i = 0; i < 7; i++) {
            ChartBean lineChartBean = new ChartBean();
            lineChartBean.setValue(String.valueOf(random2.nextInt(30)));
            lineChartBean.setDate(String.valueOf(i));
            lineChartBeanList2.add(lineChartBean);
        }
        lineChartView2.setData(lineChartBeanList2);
        lineChartView2.setyLableText("湿度折线图");
        lineChartView2.setDrawBgType(DrawBgType.DrawBitmap);
        lineChartView2.setShowPicResource(R.mipmap.click_icon);
        lineChartView2.setDrawConnectLineType(DrawConnectLineType.DrawDottedLine);
        lineChartView2.setClickable(true);
        lineChartView2.setNeedDrawConnectYDataLine(true);
        lineChartView2.setConnectLineColor(getResources().getColor(R.color.default_color));
        lineChartView2.setNeedBg(true);
        lineChartView2.setDrawLineType(DrawLineType.Draw_Curve);




        //光照折线图
        if (lineChartBeanList3 == null) {
            lineChartBeanList3 = new ArrayList<>();
        }
        lineChartView3.setDefaultTextSize(24);
        Random random3 = new Random();
        for (int i = 0; i < 7; i++) {
            ChartBean lineChartBean = new ChartBean();
            lineChartBean.setValue(String.valueOf(random3.nextInt(30)));
            lineChartBean.setDate(String.valueOf(i));
            lineChartBeanList3.add(lineChartBean);
        }
        lineChartView3.setData(lineChartBeanList3);
        lineChartView3.setyLableText("光照折线图");
        lineChartView3.setDrawBgType(DrawBgType.DrawBitmap);
        lineChartView3.setShowPicResource(R.mipmap.click_icon);
        lineChartView3.setDrawConnectLineType(DrawConnectLineType.DrawDottedLine);
        lineChartView3.setClickable(true);
        lineChartView3.setNeedDrawConnectYDataLine(true);
        lineChartView3.setConnectLineColor(getResources().getColor(R.color.default_color));
        lineChartView3.setNeedBg(true);
        lineChartView3.setDrawLineType(DrawLineType.Draw_Curve);



        //初始化温度数据
        L.i("......................initTempData........................................");
        System.out.println("......................initTempData........................................");
        initTempData(view);

//        recyclerView = (RecyclerView)view.findViewById(R.id.data_recyclerview);
//        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
//        recyclerView.setLayoutManager(layoutManager);
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
                L.d("请求调用失败。。。----------------------------");
//                mDialog.loadFailed();
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                    }
                });

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
            if (temp>=600){
                Utils.Dialog(getActivity(),"温度超标日期"+dataJSON.getDate());
            }
            dataJSONArrayList.add(dataJSON);
        }

        lineChartBeanList1.clear();
        for (int i = 0; i < 7; i++) {
            ChartBean lineChartBean = new ChartBean();
            lineChartBean.setValue(String.valueOf(dataJSONArrayList.get(i).getTemp()));
            lineChartBean.setDate(String.valueOf(i));
            lineChartBeanList1.add(lineChartBean);
        }
        lineChartView1.setData(lineChartBeanList1);

        lineChartBeanList2.clear();
        for (int i = 0; i < 7; i++) {
            ChartBean lineChartBean = new ChartBean();
            lineChartBean.setValue(String.valueOf(dataJSONArrayList.get(i).getHumi()));
            lineChartBean.setDate(String.valueOf(i));
            lineChartBeanList2.add(lineChartBean);
        }
        lineChartView2.setData(lineChartBeanList2);

        lineChartBeanList3.clear();
        for (int i = 0; i < 7; i++) {
            ChartBean lineChartBean = new ChartBean();
            lineChartBean.setValue(String.valueOf(dataJSONArrayList.get(i).getIllu()));
            lineChartBean.setDate(String.valueOf(i));
            lineChartBeanList3.add(lineChartBean);
        }
        lineChartView3.setData(lineChartBeanList3);

//        if (lineChartBeanList1 ==null){
//            lineChartBeanList1 =new ArrayList<>();
//        }
//        lineChartView1.setDefaultTextSize(24);
//        Random random = new Random();
//        for (int i = 0 ; i<7 ;i++){
//            ChartBean lineChartBean = new ChartBean();
//            lineChartBean.setValue(String.valueOf(random.nextInt(1000)));
//            lineChartBean.setDate(String.valueOf(i));
//            lineChartBeanList1.add(lineChartBean);
//        }
//        lineChartView1.setData(lineChartBeanList1);
//        lineChartView1.setyLableText("折线图");
//        lineChartView1.setDrawBgType(DrawBgType.DrawBitmap);
//        lineChartView1.setShowPicResource(R.mipmap.click_icon);
//        lineChartView1.setDrawConnectLineType(DrawConnectLineType.DrawDottedLine);
//        lineChartView1.setClickable(true);
//        lineChartView1.setNeedDrawConnectYDataLine(true);
//        lineChartView1.setConnectLineColor(getResources().getColor(R.color.default_color));
//        lineChartView1.setNeedBg(true);
//        lineChartView1.setDrawLineType(DrawLineType.Draw_Curve);

//        DataAdapter adapter = new DataAdapter(dataJSONArrayList);
//        recyclerView.setAdapter(adapter);



    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.open_btn:
                //开灯
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
            case R.id.query_today_data:
                //查询今天数据
                Intent intent1 = new Intent(getActivity(), TodayDataActivity.class);
                startActivity(intent1);
                break;
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        if(lineChartBeanList1 !=null&& lineChartBeanList1.size()>0){
            lineChartBeanList1.clear();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        lineChartView1.recycleBitmap();
    }
}
