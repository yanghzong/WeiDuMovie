package com.umeng.soexample.weidumovie.api;


import com.umeng.soexample.weidumovie.utils.RetrofitManager;

public class ApiManager {
    //我的页面接口
    public static MineApi getMindApi(){
        return RetrofitManager.getInstance().create(MineApi.class);
    }

    //登录页面接口
    public static LoginApi getLoginApi(){
        return RetrofitManager.getInstance().create(LoginApi.class);
    }

    //影院页面接口
    public static CinemaApi getCinemaApi(){
        return RetrofitManager.getInstance().create(CinemaApi.class);
    }

    //电影页面接口
    public static IFilmApi getFilmApi(){
        return RetrofitManager.getInstance().create(IFilmApi.class);
    }
}
