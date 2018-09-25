package com.example.wsdp2.fragment;


import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.wsdp2.R;
import com.example.wsdp2.gson.WeatherJSON;
import com.example.wsdp2.utils.L;
import com.example.wsdp2.utils.Utils;
import com.google.gson.Gson;
import com.xiasuhuei321.loadingdialog.view.LoadingDialog;

import java.io.IOException;
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
public class WeatherFragment extends Fragment {

    private TextView weather_location;
    private TextView weather_tmp;
    private TextView weather_cond_txt;
    private TextView weather_wind_dir;
    private TextView weather_wind_sc;
    private TextView weather_wind_spd;
    private TextView weather_hum;
    private TextView weather_pcpn;
    private TextView weather_pres;
    private TextView weather_vis;

    private LoadingDialog mDialog;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_weather, container, false);

        initView(view);

        initWeather(view);

        return view;
    }

    private void initView(View view) {
        weather_location = (TextView) view.findViewById(R.id.weather_location);
        weather_tmp = (TextView) view.findViewById(R.id.weather_tmp);
        weather_cond_txt = (TextView) view.findViewById(R.id.weather_cond_txt);
        weather_wind_dir = (TextView) view.findViewById(R.id.weather_wind_dir);
        weather_wind_sc = (TextView) view.findViewById(R.id.weather_wind_sc);
        weather_wind_spd = (TextView) view.findViewById(R.id.weather_wind_spd);
        weather_hum = (TextView) view.findViewById(R.id.weather_hum);
        weather_pcpn = (TextView) view.findViewById(R.id.weather_pcpn);
        weather_pres = (TextView) view.findViewById(R.id.weather_pres);
        weather_vis = (TextView) view.findViewById(R.id.weather_vis);
    }

    private void initWeather(View view) {
        mDialog = new LoadingDialog(getActivity());
        mDialog.setLoadingText("加载中...")
                .setSuccessText("加载成功!")
                .setFailedText("加载失败!")
                .setInterceptBack(true)
                .setLoadSpeed(LoadingDialog.Speed.SPEED_TWO)
                .setRepeatCount(0)
                .setDrawColor(Color.WHITE)
                .show();

        //调用和风天气接口
        initHeFeng(view);
    }

    private void initHeFeng(View view) {
        OkHttpClient client = new OkHttpClient();
        final Request request = new Request.Builder()
                .url(Utils.WEATHER_KEY)
                .get().build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                L.d("请求调用失败。。。");
                mDialog.loadFailed();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()){
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

        WeatherJSON weatherJSON = gson.fromJson(json, WeatherJSON.class);

        List<WeatherJSON.HeWeather6Bean> list = weatherJSON.getHeWeather6();

        weather_location.setText(list.get(0).getBasic().getLocation());
        weather_tmp.setText(list.get(0).getNow().getTmp());
        weather_cond_txt.setText(list.get(0).getNow().getCond_txt());
        weather_wind_dir.setText(list.get(0).getNow().getWind_dir());
        weather_wind_sc.setText(list.get(0).getNow().getWind_sc());
        weather_wind_spd.setText(list.get(0).getNow().getWind_spd());
        weather_hum.setText(list.get(0).getNow().getHum());
        weather_pcpn.setText(list.get(0).getNow().getPcpn());
        weather_pres.setText(list.get(0).getNow().getPres());
        weather_vis.setText(list.get(0).getNow().getVis());
    }

}
