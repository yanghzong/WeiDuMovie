package com.umeng.soexample.weidumovie.LoginAndZhuce;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.umeng.soexample.weidumovie.LoginAndZhuce.bean.LoginBean;
import com.umeng.soexample.weidumovie.LoginAndZhuce.presenter.LoginPresenter;
import com.umeng.soexample.weidumovie.LoginAndZhuce.view.LoginView;
import com.umeng.soexample.weidumovie.MainActivity;
import com.umeng.soexample.weidumovie.R;
import com.umeng.soexample.weidumovie.utils.EncryptUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnTouch;
import butterknife.Unbinder;

public class LoginActivity extends AppCompatActivity implements LoginView {

    @BindView(R.id.img_login_phone)
    ImageView imgLoginPhone;
    @BindView(R.id.et_login_phone)
    EditText etLoginPhone;
    @BindView(R.id.img_login_pwd)
    ImageView imgLoginPwd;
    @BindView(R.id.et_login_pwd)
    EditText etLoginPwd;
    @BindView(R.id.img_show_hide_pwd)
    ImageView imgShowHidePwd;
    @BindView(R.id.cb_remember_the_pwd)
    CheckBox cbRememberThePwd;
    @BindView(R.id.cb_automatic_login)
    CheckBox cbAutomaticLogin;
    @BindView(R.id.txt_res)
    TextView txtRes;
    @BindView(R.id.btn_login)
    Button btnLogin;
    @BindView(R.id.img_login_weixin)
    ImageView imgLoginWeixin;
    private Unbinder bind;
    private String loginphone;
    private String loginpwd;
    private SharedPreferences sp;
    private boolean showPwd = false;
    private LoginPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        bind = ButterKnife.bind(this);
        //初始化p层
        initPresenter();
        initData();
    }

    private void initPresenter() {
        presenter = new LoginPresenter();
        presenter.attach(this);
        presenter.login();
    }

    private void initData() {
        //获取Sp的config.xml文件
        sp = getSharedPreferences("config", MODE_PRIVATE);
        //获取是否记住密码
        boolean rememberPwd = sp.getBoolean("rememberPwd", false);
        if (rememberPwd) {
            //在SharedPreferences获取手机号和密码
            String phone = sp.getString("phone", "");
            String pwd = sp.getString("pwd", "");
            //将密码进行解密
            String decrypt = EncryptUtil.decrypt(pwd);
            //将手机号密码赋值给输入框
            etLoginPhone.setText(phone);
            etLoginPwd.setText(decrypt);
            //将记住密码复选框的值改变
            cbRememberThePwd.setChecked(rememberPwd);
            //获取是否自动登录
            boolean automaticLogin = sp.getBoolean("automaticLogin", false);
            //改变自动登录复选框的值
            cbAutomaticLogin.setChecked(automaticLogin);
            //若为自动登录直接登录
            if (automaticLogin) {
                //调用登录接口，自动登录
            }
        }
    }


    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus && Build.VERSION.SDK_INT >= 19) {
            View decorView = getWindow().getDecorView();
            decorView.setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                            | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
        }
    }

    //ButterKnife点击事件
    @OnClick({R.id.img_show_hide_pwd, R.id.txt_res, R.id.btn_login, R.id.img_login_weixin, R.id.cb_automatic_login, R.id.cb_remember_the_pwd})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            //点击记住密码如果为false那么自动登录也为false
            case R.id.cb_remember_the_pwd:
                if (!cbRememberThePwd.isChecked()) {
                    cbAutomaticLogin.setChecked(false);
                }
                break;
            //自动登录未true记住密码也为true
            case R.id.cb_automatic_login:
                cbRememberThePwd.setChecked(cbAutomaticLogin.isChecked());
                break;
            //显示隐藏密码图片按钮
            case R.id.img_show_hide_pwd:
                if (showPwd) {
                    //变量改变为false（密码显示密码格式）
                    showPwd = false;
                    //将输入框类型转换为密码格式
                    etLoginPwd.setTransformationMethod(PasswordTransformationMethod
                            .getInstance());  //以密文显示，以.代替
                } else {
                    //改变变量为true（密码显示普通文本格式）
                    showPwd = true;
                    //将输入框类型转换为普通文本格式
                    etLoginPwd.setTransformationMethod(HideReturnsTransformationMethod
                            .getInstance());  //密码以明文显示
                }
                break;
            //点击注册按钮
            case R.id.txt_res:
                //跳转到注册页面
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
                break;
            //登录按钮
            case R.id.btn_login:
                //调用P层的登录方法
                presenter.login();
                break;
            //第三方微信登录
            case R.id.img_login_weixin:

                break;
        }
    }


    @OnTouch(R.id.img_show_hide_pwd)
    public  boolean  onTouchImg( MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_UP://松开事件发生后执行代码的区域
                Log.e("", "密码不可见");
                etLoginPwd.setTransformationMethod(PasswordTransformationMethod.getInstance());
                break;
            case MotionEvent.ACTION_DOWN://按住事件发生后执行代码的区域
                Log.e("", "密码可见");
                etLoginPwd.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                break;

        }

        return true;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        bind.unbind();
        presenter.detach();
    }

    @Override
    public String getInputPhoneNumber() {
        return etLoginPhone.getText().toString().trim();
    }

    @Override
    public String getInputPwd() {
        String pwd = etLoginPwd.getText().toString();
        //加密
        String encrypt = EncryptUtil.encrypt(pwd);
        return encrypt;
    }

    @Override
    public void onLoginSuccess(LoginBean loginBean) {
//获取sp的编辑器
        SharedPreferences.Editor edit = sp.edit();
        //吐司登录接口返回的信息
        if (loginBean.getMessage().equals("登陆成功")) {
            Toast.makeText(this, ""+loginBean.getMessage(), Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, ""+loginBean.getMessage(), Toast.LENGTH_SHORT).show();
        }
        //判断下返回的状态码是否是成功的状态码
        if ("0000".equals(loginBean.getStatus())) {
            //如果记住密码则保存密码和手机号到sp中（密码已被加密过）
            if (cbRememberThePwd.isChecked()) {
                edit.putString("phone", getInputPhoneNumber())
                        .putString("pwd", getInputPwd());
            }
            //保存是否自动登录、记住密码，手机号、密码、用户的UserId和SessionId
            edit.putBoolean("automaticLogin", cbAutomaticLogin.isChecked())
                    .putBoolean("rememberPwd", cbRememberThePwd.isChecked())
                    .putInt("userId", loginBean.getResult().getUserId())
                    .putString("sessionId", loginBean.getResult().getSessionId())
                    //存入是否登录
                    .putBoolean("isLogined", true);
            edit.commit();
           Intent intent=new Intent(LoginActivity.this,MainActivity.class);
           startActivity(intent);
           finish();
        }
    }

    @Override
    public void onFailed(Throwable t) {

    }

}
