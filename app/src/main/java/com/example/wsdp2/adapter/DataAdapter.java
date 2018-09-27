package com.example.wsdp2.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.wsdp2.R;
import com.example.wsdp2.entity.Data;

import java.util.List;

/**
 * Created by lin on 2018/9/27.
 * 描述:
 */
public class DataAdapter extends RecyclerView.Adapter<DataAdapter.ViewHolder> {

    private List<Data> mDataList;

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.data_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    public DataAdapter(List<Data> dataList) {
        mDataList = dataList;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Data data = mDataList.get(position);
        holder.data_date.setText(data.getDate());
        holder.data_temp.setText(data.getTemp());
        holder.data_humi.setText(data.getHumi());
        holder.data_lllumi.setText(data.getLllumi());
    }

    @Override
    public int getItemCount() {
        return mDataList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        TextView data_date;
        TextView data_temp;
        TextView data_humi;
        TextView data_lllumi;

        public ViewHolder(View itemView) {
            super(itemView);

            data_date = (TextView) itemView.findViewById(R.id.data_date);
            data_temp = (TextView) itemView.findViewById(R.id.data_temp);
            data_humi = (TextView) itemView.findViewById(R.id.data_humi);
            data_lllumi = (TextView) itemView.findViewById(R.id.data_lllumi);
        }
    }
}
