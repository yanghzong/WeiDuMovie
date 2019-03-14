package com.umeng.soexample.weidumovie.startactivity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.jaeger.library.StatusBarUtil;
import com.umeng.soexample.weidumovie.MainActivity;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wel_come);


        bind = ButterKnife.bind(this);
        initView();
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
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        bind.unbind();
    }
}
