package com.umeng.soexample.weidumovie.utils;


import android.util.Log;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.umeng.soexample.weidumovie.inter.AppUrl;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitUtils {

    public static RetrofitUtils instance;
    public OkHttpClient client;
    public Retrofit.Builder builder;
    private final HttpLoggingInterceptor httpLoggingInterceptor;

    private RetrofitUtils() {
        //创建日志拦截器
        HttpLoggingInterceptor.Level level = HttpLoggingInterceptor.Level.BODY;
        //创建日志拦截器
        httpLoggingInterceptor = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
            @Override
            public void log(String message) {
                Log.i("信息打印", "log: " + message);
            }
        });
        httpLoggingInterceptor.setLevel(level);
        //创建okhttp的对象
        client = new OkHttpClient.Builder()
                .connectTimeout(10, TimeUnit.SECONDS)
                .readTimeout(10, TimeUnit.SECONDS)
                .writeTimeout(10, TimeUnit.SECONDS)
                .addInterceptor(httpLoggingInterceptor)
                .build();
        //创建Retrofit的对象
        builder = new Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl(AppUrl.BasekUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client);

    }

    //单例双重锁
    public static RetrofitUtils getInstance() {
        if (instance == null) {
            synchronized (RetrofitUtils.class) {
                if (instance == null) {
                    instance = new RetrofitUtils();
                }
            }
        }
        return instance;
    }


    //创建一个doget方法
    public RetrofitUtils GetData(String url, RxRetrofitListener rxRetrofitListener) {
        //通过方法  得到RetrofitIView对象
        MovieInterface movieInterface = getRetrofitM(url);
        //通过对象调用方法
        movieInterface.doget()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(getObserverM(rxRetrofitListener));
        return instance;
    }


    private MovieInterface getRetrofitM(String url) {
        Retrofit retrofit = this.builder.baseUrl(url).build();
        MovieInterface movieInterface = retrofit.create(MovieInterface.class);
        return movieInterface;
    }


    //创建观察者
    private Observer getObserverM(final RxRetrofitListener rxRetrofitListener) {
        Observer observer = new Observer<ResponseBody>() {


            @Override
            public void onError(Throwable e) {
                //失败
                if (rxRetrofitListener != null) {
                    rxRetrofitListener.onFailed(e.getMessage());
                }

            }

            @Override
            public void onComplete() {

            }

            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(ResponseBody responseBody) {
                //成功得到数据

                try {
                    String string = responseBody.string();
                    //判断接口是否为空
                    if (rxRetrofitListener != null) {
                        rxRetrofitListener.onSuccess(string);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                    if (rxRetrofitListener != null) {
                        rxRetrofitListener.onFailed(e.getMessage());
                    }
                }

            }


        };

        return observer;
    }


    //定义一个接口
    public interface RxRetrofitListener {
        void onSuccess(String result);

        void onFailed(String str);
    }
}
