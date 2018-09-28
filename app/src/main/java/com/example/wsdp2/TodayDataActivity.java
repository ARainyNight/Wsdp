package com.example.wsdp2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.LinearLayout;

import com.example.wsdp2.adapter.DataAdapter;
import com.example.wsdp2.gson.DataJSON;
import com.example.wsdp2.utils.L;
import com.example.wsdp2.utils.Utils;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class TodayDataActivity extends AppCompatActivity {

    private ArrayList<DataJSON> dataJSONArrayList  = new ArrayList<>();

    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_today_data);

        initRecyclerView();
    }

    private void initRecyclerView() {
        initRecyclerviewData();

        recyclerView =(RecyclerView)findViewById(R.id.today_data_recyclerview);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
    }

    private void initRecyclerviewData() {
        OkHttpClient client = new OkHttpClient();
        final Request request = new Request.Builder()
                .url(Utils.QUERY_TODAY_DATA)
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
                    runOnUiThread(new Runnable() {
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
        JsonParser parser = new JsonParser();
        JsonArray jsonArray = parser.parse(json).getAsJsonArray();

        Gson gson = new Gson();

        for (JsonElement data : jsonArray) {
            DataJSON dataJSON = gson.fromJson(data, DataJSON.class);
            double temp = dataJSON.getTemp();
            if (temp>=600){
                Utils.Dialog(this,"温度超标日期"+dataJSON.getDate());
            }
            dataJSONArrayList.add(dataJSON);
        }

        DataAdapter adapter = new DataAdapter(dataJSONArrayList);
        recyclerView.setAdapter(adapter);
    }
}
