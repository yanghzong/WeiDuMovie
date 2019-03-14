package com.umeng.soexample.weidumovie.LoginAndZhuce.presenter;


import android.util.Log;

import com.umeng.soexample.weidumovie.LoginAndZhuce.bean.LoginBean;
import com.umeng.soexample.weidumovie.LoginAndZhuce.movie.LoginModel;
import com.umeng.soexample.weidumovie.LoginAndZhuce.view.LoginView;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class LoginPresenter {

    private LoginView loginView;
    private LoginModel loginModel;
    public  void attach(LoginView loginView){
        this.loginView=loginView;
        loginModel=new LoginModel();
    }
    public void login() {
        String pwd = loginView.getInputPwd();
        Log.d("hhaha", "initData: " + pwd);
        String phone = loginView.getInputPhoneNumber();
        loginModel.login(phone, pwd)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<LoginBean>() {
                    @Override
                    public void accept(LoginBean loginBean) throws Exception {
                        if (loginBean != null) {
                            if (loginView != null) {
                                loginView.onLoginSuccess(loginBean);
                                return;
                            }
                        }
                        if (loginView != null) {
                            loginView.onFailed(new Throwable("网络连接超时"));
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        if (loginView != null) {
                            loginView.onFailed(throwable);
                        }
                    }
                });
    }
    public  void detach(){
        if (loginView!=null){
            loginView=null;
        }
        if (loginModel!=null){
            loginModel=null;
        }
    }

}
