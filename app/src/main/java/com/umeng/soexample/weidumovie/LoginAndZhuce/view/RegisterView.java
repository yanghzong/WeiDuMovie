package com.umeng.soexample.weidumovie.LoginAndZhuce.view;

import com.umeng.soexample.weidumovie.LoginAndZhuce.bean.RegisterBean;

public interface RegisterView {
    String getInputResPhoneNumber();
    String getInputResPwd();
    String getInputResName();
    int getInputResSex();
    String getInputResBirthday();
    String getInputResEmail();
    void onResSuccess(RegisterBean registerBean);
    void onFailed(Throwable t);
}
