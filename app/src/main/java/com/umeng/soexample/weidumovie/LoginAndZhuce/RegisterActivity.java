package com.umeng.soexample.weidumovie.LoginAndZhuce;

import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.umeng.soexample.weidumovie.MainActivity;
import com.umeng.soexample.weidumovie.R;
import com.umeng.soexample.weidumovie.utils.DateUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnTouch;

public class RegisterActivity extends AppCompatActivity {

    @BindView(R.id.img_res_name)
    ImageView imgResName;
    @BindView(R.id.et_res_name)
    EditText etResName;
    @BindView(R.id.img_res_sex)
    ImageView imgResSex;
    @BindView(R.id.rbtn_male)
    RadioButton rbtnMale;
    @BindView(R.id.rbtn_female)
    RadioButton rbtnFemale;
    @BindView(R.id.rgroup_sex)
    RadioGroup rgroupSex;
    @BindView(R.id.img_res_birthday)
    ImageView imgResBirthday;
    @BindView(R.id.txt_res_birthday)
    TextView txtResBirthday;
    @BindView(R.id.img_res_phone)
    ImageView imgResPhone;
    @BindView(R.id.et_res_phone)
    EditText etResPhone;
    @BindView(R.id.img_res_email)
    ImageView imgResEmail;
    @BindView(R.id.et_res_email)
    EditText etResEmail;
    @BindView(R.id.img_res_pwd)
    ImageView imgResPwd;
    @BindView(R.id.et_res_pwd)
    EditText etResPwd;
    @BindView(R.id.img_res_show_hide_pwd)
    ImageView imgResShowHidePwd;
    @BindView(R.id.btn_res)
    Button btnRes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ButterKnife.bind(this);
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

    @OnClick({R.id.txt_res_birthday,  R.id.btn_res})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.txt_res_birthday:
                DateUtil.setYearDate(RegisterActivity.this, new DateListener() {
                    @Override
                    public void setYear(String year) {

                    }

                    @Override
                    public void setMonth(String month) {

                    }

                    @Override
                    public void setDay(String day) {

                    }

                    @Override
                    public void setMouthDate(String year, String month) {

                    }

                    @Override
                    public void setYearDate(String year, String month, String day) {
                        txtResBirthday.setText(year + "-" + month + "-" + day);

                    }
                });



                break;

            case R.id.btn_res:


                break;
        }
    }
    @OnTouch(R.id.img_res_show_hide_pwd)
    public  boolean  onTouchImg( MotionEvent event){
        switch (event.getAction()) {
            case MotionEvent.ACTION_UP://松开事件发生后执行代码的区域
                Log.e("","密码不可见");
                etResPwd.setTransformationMethod(PasswordTransformationMethod.getInstance());
                break;
            case MotionEvent.ACTION_DOWN://按住事件发生后执行代码的区域
                Log.e("","密码可见");
                etResPwd.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                break;

        }

        return true;

    }
}
