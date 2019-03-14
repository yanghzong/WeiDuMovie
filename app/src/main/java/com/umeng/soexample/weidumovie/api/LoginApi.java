package com.umeng.soexample.weidumovie.api;

import io.reactivex.Observable;
import com.umeng.soexample.weidumovie.LoginAndZhuce.bean.LoginBean;
import com.umeng.soexample.weidumovie.LoginAndZhuce.bean.RegisterBean;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface LoginApi {
    //登录接口
    @FormUrlEncoded
    @POST("user/v1/login")
    Observable<LoginBean> login(@Field("phone") String phone, @Field("pwd") String pwd);
    //注册接口 @Field("ua")String ua,@Field("screenSize")String screenSize,@Field("os")String os,
    @FormUrlEncoded
    @POST("user/v1/registerUser")
    Observable<RegisterBean> registerUser(@Field("nickName") String nickName, @Field("phone") String phone, @Field("pwd") String pwd, @Field("pwd2") String pwd2, @Field("sex") int sex, @Field("birthday") String birthday, @Field("email") String email);
}
