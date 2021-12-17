package com.archerswet.examinationsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class MainActivity extends AppCompatActivity {

    @BindView(R.id.btn_post)
    Button btn_post;

    @BindView(R.id.tv_show)
    TextView tv_show;

    private String result;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btn_post)
    public void onViewClicked(View view){
        new Thread(() -> {
            try {
//                ForOkhttpUtil.post("http://192.168.1.123:3000/login","");
                result = ForOkhttpUtil.get("http://120.79.164.150:3000/query?name=1");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();
    }



}