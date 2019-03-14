package com.umeng.soexample.weidumovie.LoginAndZhuce.movie;

import com.umeng.soexample.weidumovie.LoginAndZhuce.bean.LoginBean;
import com.umeng.soexample.weidumovie.api.ApiManager;
import com.umeng.soexample.weidumovie.custom.Constant;

import io.reactivex.Observable;

public class LoginModel {
    public Observable<LoginBean> login(String phone, String pwd){
        return Constant.addMain(ApiManager.getLoginApi().login(phone,pwd));
    }
}
