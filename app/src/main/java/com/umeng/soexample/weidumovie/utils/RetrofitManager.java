package com.umeng.soexample.weidumovie.utils;


import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.umeng.soexample.weidumovie.application.MyApplication;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.Headers;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * 工具类在使用的时传入context
 */
public class RetrofitManager {


    private final Retrofit mRetrofit;
    private static final String BASE_URL = "http://172.17.8.100/movieApi/";


    private static final class SINGLE_INSTANCE {
        private static final Context MCONTEXT = MyApplication.mContext;
        private static final RetrofitManager INSTANCE = new RetrofitManager();
    }

    public static RetrofitManager getInstance() {
        return SINGLE_INSTANCE.INSTANCE;
    }

    private RetrofitManager() {
        mRetrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(buildOkHttpClient())
                .build();
    }

    private OkHttpClient buildOkHttpClient() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY);

        return new OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .addInterceptor(new UserAgentInterceptor())
                .addNetworkInterceptor(new HttpCacheInterceptor())
                .cache(new Cache(SINGLE_INSTANCE.MCONTEXT.getCacheDir(), 20 * 1024 * 1024)) // 设置缓存路径和缓存容量
                .connectTimeout(5, TimeUnit.SECONDS)
                .readTimeout(5, TimeUnit.SECONDS)
                .build();
    }

    public <T> T create(Class<T> tClass) {
        return mRetrofit.create(tClass);
    }

    /**
     * 封装公共请求头的自定义拦截器
     */
    private class UserAgentInterceptor implements Interceptor {

        private final SharedPreferences sp;

        private UserAgentInterceptor() {
            sp = SINGLE_INSTANCE.MCONTEXT.getSharedPreferences("config", Context.MODE_PRIVATE);
        }

        @Override
        public Response intercept(Chain chain) throws IOException {
            Request request = chain.request();
            String url = request.url().toString();
            if (!url.contains("registerUser") || !url.contains("login")) {
                // 如果包含的话就是注册或登录,这里是非注册or登录
                int userId = sp.getInt("userId", 0);
                String sessionId = sp.getString("sessionId", "");
                Log.i("RetrofitManager", "userId:  --- " + userId + ";     sessionId:  ---" + sessionId);
                Request.Builder builder = request.newBuilder();
                Headers.Builder headerBuilder = request.headers().newBuilder();

                headerBuilder.add("sessionId", sessionId);
                Headers headers = headerBuilder.add("userId", String.valueOf(userId))
                        .build();
                builder.headers(headers);
                request = builder.build();
            }
            return chain.proceed(request);
        }
    }


    public class HttpCacheInterceptor implements Interceptor {
        @Override
        public Response intercept(Chain chain) throws IOException {
            Request request = chain.request();
            Response response = chain.proceed(request);
            if (NetworkUtils.isNetWorkAvailable(SINGLE_INSTANCE.MCONTEXT)) {
                int maxAge = 0;                     // 有网络时 设置缓存超时时间0个小时
                response.newBuilder()
                        .header("Cache-Control", "public, max-age=" + maxAge)
                        .removeHeader("Pragma")     // 清除头信息
                        .build();
            } else {
                int maxStale = 60 * 60 * 24;        // 无网络时，设置超时为1天
                response.newBuilder()
                        .header("Cache-Control", "public, only-if-cached, max-stale=" + maxStale)
                        .removeHeader("Pragma")
                        .build();
            }
            return response;
        }
    }
}


