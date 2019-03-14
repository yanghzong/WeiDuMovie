package com.umeng.soexample.weidumovie.LoginAndZhuce.view;

import com.umeng.soexample.weidumovie.LoginAndZhuce.bean.LoginBean;

public interface LoginView {
    String getInputPhoneNumber();
    String getInputPwd();
    void onLoginSuccess(LoginBean loginBean);
    void onFailed(Throwable t);

}
