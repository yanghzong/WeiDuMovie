package com.umeng.soexample.weidumovie.startactivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Point;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;

import com.umeng.soexample.weidumovie.R;
import com.umeng.soexample.weidumovie.startactivity.welcomefragment.FourFragment;
import com.umeng.soexample.weidumovie.startactivity.welcomefragment.OneFragment;
import com.umeng.soexample.weidumovie.startactivity.welcomefragment.ThreeFragment;
import com.umeng.soexample.weidumovie.startactivity.welcomefragment.TwoFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class WelComeActivity extends AppCompatActivity {

    @BindView(R.id.vp_welcom)
    ViewPager vpWelcom;
    private Unbinder bind;
    private List<Fragment> fragments;
    private SharedPreferences sp;
    private float startX;
    private float startY;//没有用到
    private float endX;
    private float endY;//没有用到
    private int currentItem;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wel_come);


        sp = getSharedPreferences("first_in", MODE_PRIVATE);
       boolean first = sp.getBoolean("first", true);
        if (first) {
            sp.edit().putBoolean("first", false).commit();
        } else {
            sp.edit().putBoolean("first", true).commit();
            startActivity(new Intent(WelComeActivity.this, StartActivity.class));
            finish();
        }


        bind = ButterKnife.bind(this);
        initView();
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


    public void initView() {
        //添加引导页
        fragments = new ArrayList<>();
        fragments.add(new OneFragment());
        fragments.add(new TwoFragment());
        fragments.add(new ThreeFragment());
        fragments.add(new FourFragment());

        vpWelcom.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return fragments.get(position);
            }

            @Override
            public int getCount() {
                return fragments.size();
            }
        });
        vpWelcom.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {


            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                //获取位置，即第几页
                currentItem = position;
                Log.i("Guide","监听改变"+position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });


        vpWelcom.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()){
                    case MotionEvent.ACTION_DOWN:
                        startX=event.getX();
                        startY=event.getY();
                        break;
                    case MotionEvent.ACTION_UP:
                        endX=event.getX();
                        endY=event.getY();
                        WindowManager windowManager= (WindowManager)                                   getApplicationContext().getSystemService(Context.WINDOW_SERVICE);

                        //获取屏幕的宽度
                        Point size = new Point();
                        windowManager.getDefaultDisplay().getSize(size);
                        int width=size.x;

                        //首先要确定的是，是否到了最后一页，然后判断是否向左滑动，并且滑动距离是否符合，我这里的判断距离是屏幕宽度的4分之一（这里可以适当控制）
                        if(currentItem==(fragments.size()-1)&&startX-endX>=(width/4)){
                            goToMainActivity();//进入主页
                            overridePendingTransition(R.anim.slide_in_right,R.anim.slide_in_left);//这部分代码是切换Activity时的动画，看起来就不会很生硬
                        }
                        break;
                }
               return false;
            }


        });
    }
    private void goToMainActivity() {
        Intent intent=new Intent(WelComeActivity.this,StartActivity.class);
        startActivity(intent);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        bind.unbind();
    }
}
