package com.umeng.soexample.weidumovie.startactivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.RelativeLayout;

import com.umeng.soexample.weidumovie.LoginAndZhuce.LoginActivity;
import com.umeng.soexample.weidumovie.MainActivity;
import com.umeng.soexample.weidumovie.R;
import com.umeng.soexample.weidumovie.utils.StatusBarUtil;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class StartActivity extends AppCompatActivity {


    private Unbinder bind;
    //设定倒计时时长 n 单位 s
    private int time =3 ;
    private Timer timer;

    //初始化 Handler
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 1:
                    startActivity(new Intent(StartActivity.this, LoginActivity.class));
                    finish();
            }
            super.handleMessage(msg);
        }
    };
    private SharedPreferences sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        bind = ButterKnife.bind(this);
        sp = getSharedPreferences("first_in",MODE_PRIVATE);
        sp.edit().putBoolean("first",true);



    }
    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus && Build.VERSION.SDK_INT >= 19) {
            View decorView = getWindow().getDecorView();
            decorView.setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                            | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
        }
    }




    @Override
    protected void onResume() {
        timer = new Timer(true);
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                time--;
                handler.sendEmptyMessage((time == 0 ? 1 : 0));
            }
        }, 1000, 1000);
        super.onResume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        bind.unbind();
    }
}
