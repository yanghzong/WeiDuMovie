package com.umeng.soexample.weidumovie.application;

import android.app.Application;
import android.content.Context;


import com.github.moduth.blockcanary.BlockCanary;
import com.squareup.leakcanary.LeakCanary;
import com.tencent.bugly.crashreport.CrashReport;

public class MyApplication extends Application {
    public static Context mContext;
    @Override
    public void onCreate() {
        super.onCreate();

        LeakCanary.install(this);
        BlockCanary.install(this, new AppBlockCanaryContext()).start();
        CrashReport.initCrashReport(getApplicationContext(), "fcc0db044f", false);
    }



}
