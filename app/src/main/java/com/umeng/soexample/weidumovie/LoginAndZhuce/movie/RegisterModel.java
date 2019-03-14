package com.umeng.soexample.weidumovie.LoginAndZhuce.movie;

import com.umeng.soexample.weidumovie.LoginAndZhuce.bean.RegisterBean;
import com.umeng.soexample.weidumovie.api.ApiManager;
import com.umeng.soexample.weidumovie.custom.Constant;

import io.reactivex.Observable;

public class RegisterModel {
    public Observable<RegisterBean> registerUser(String nickName, String phone, String pwd, String pwd2, int sex, String birthday, String email){
        return Constant.addMain(ApiManager.getLoginApi().registerUser(nickName,phone,pwd,pwd2,sex,birthday,email));
    }
}
