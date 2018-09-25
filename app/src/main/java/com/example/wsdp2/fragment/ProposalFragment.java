package com.example.wsdp2.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.wsdp2.R;
import com.example.wsdp2.proposal.AppleActivity;
import com.example.wsdp2.proposal.CabbageActivity;
import com.example.wsdp2.proposal.CornActivity;
import com.example.wsdp2.proposal.PotatoActivity;

/**
 * Created by lin on 2018/9/25.
 * 描述:
 */
public class ProposalFragment extends Fragment implements View.OnClickListener{

    private Button corn_btn;
    private Button potato_btn;
    private Button cabbage_btn;
    private Button apple_btn;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view =inflater.inflate(R.layout.fragment_proposal,container,false);

        initView(view);
        return view;
    }

    private void initView(View view) {
        corn_btn =(Button)view.findViewById(R.id.corn_btn);
        potato_btn=(Button)view.findViewById(R.id.potato_btn);
        cabbage_btn=(Button)view.findViewById(R.id.cabbage_btn);
        apple_btn=(Button)view.findViewById(R.id.apple_btn);

        corn_btn.setOnClickListener(this);
        potato_btn.setOnClickListener(this);
        cabbage_btn.setOnClickListener(this);
        apple_btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.corn_btn:
                Intent intent1 = new Intent(getActivity(), CornActivity.class);
                startActivity(intent1);
                break;
            case R.id.potato_btn:
                Intent intent2 = new Intent(getActivity(), PotatoActivity.class);
                startActivity(intent2);
                break;
            case R.id.cabbage_btn:
                Intent intent3 = new Intent(getActivity(), CabbageActivity.class);
                startActivity(intent3);
                break;
            case R.id.apple_btn:
                Intent intent4 = new Intent(getActivity(), AppleActivity.class);
                startActivity(intent4);
                break;
        }
    }
}
