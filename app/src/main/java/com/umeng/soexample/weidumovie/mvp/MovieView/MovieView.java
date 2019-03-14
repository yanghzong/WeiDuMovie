package com.umeng.soexample.weidumovie.mvp.MovieView;

public interface MovieView<T> {
    void  onSuccess(T t);
    void  onFailed(String str);
}
