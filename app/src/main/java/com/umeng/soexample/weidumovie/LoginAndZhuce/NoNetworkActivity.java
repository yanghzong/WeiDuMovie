package com.umeng.soexample.weidumovie.LoginAndZhuce;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.ImageView;

import com.umeng.soexample.weidumovie.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class NoNetworkActivity extends AppCompatActivity {

    @BindView(R.id.btn_open_setting)
    Button btnOpenSetting;
    @BindView(R.id.img_no_network)
    ImageView imgNoNetwork;
    private Unbinder bind;
    public static NoNetworkActivity instance;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_no_network);
        bind = ButterKnife.bind(this);
    }
}
