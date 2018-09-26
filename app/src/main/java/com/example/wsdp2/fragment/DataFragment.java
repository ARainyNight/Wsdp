package com.example.wsdp2.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.wsdp2.R;
import com.veken.chartview.bean.ChartBean;
import com.veken.chartview.drawtype.DrawBgType;
import com.veken.chartview.drawtype.DrawConnectLineType;
import com.veken.chartview.drawtype.DrawLineType;
import com.veken.chartview.view.LineChartView;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by lin on 2018/9/25.
 * 描述:
 */
public class DataFragment extends Fragment {

    private ArrayList<ChartBean> lineChartBeanList;
    private LineChartView lineChartView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view =inflater.inflate(R.layout.fragment_data,container,false);

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
        if (lineChartBeanList ==null){
            lineChartBeanList =new ArrayList<>();
        }
        lineChartView.setDefaultTextSize(24);
        Random random =new Random();
        for (int i=0 ;i <8 ; i++){
            ChartBean lineChartBean =new ChartBean();
            lineChartBean.setValue(String.valueOf(random.nextInt(30)));
            lineChartBean.setDate(String.valueOf(i));
            lineChartBeanList.add(lineChartBean);
        }
        lineChartView.setData(lineChartBeanList);
    }

}
