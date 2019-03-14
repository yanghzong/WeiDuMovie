package com.umeng.soexample.weidumovie.utils;


import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.http.GET;

public interface MovieInterface {

    @GET
    Observable<ResponseBody> doget();
}
