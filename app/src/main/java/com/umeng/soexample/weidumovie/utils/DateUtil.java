package com.umeng.soexample.weidumovie.utils;

import android.app.Activity;

import android.text.format.Time;

import com.umeng.soexample.weidumovie.LoginAndZhuce.DateListener;
import com.umeng.soexample.weidumovie.R;




import cn.qqtheme.framework.picker.DatePicker;

public class DateUtil {

    public static void setYearDate(final Activity mContext, final DateListener dateListener) {
        DatePicker picker = new DatePicker(mContext);
        //获取当前时间
        Time time = new Time();
        time.setToNow();
        int year = time.year;
        int month = time.month;
        int monthDay = time.monthDay;
        //设置时间区间
        picker.setRange(1000, 2020);
        //设置默认显示时间
        picker.setSelectedItem(year, month + 1, monthDay);
        //加入动画
        picker.setAnimationStyle(R.style.Animation_CustomPopup);
        //回传数据
        picker.setOnDatePickListener(new DatePicker.OnYearMonthDayPickListener() {
            @Override
            public void onDatePicked(String year, String month, String day) {
                dateListener.setYearDate(year, month, day);
            }
        });
        picker.show();
    }
   /* public static void setPlace(Activity mContext, final PlaceListener placeListener) {
        ArrayList<Province> data = new ArrayList<>();
        String json = AssetsUtils.getStringFromAssert(mContext, "city.json");
        data.addAll(JSON.parseArray(json, Province.class));
        AddressPicker picker = new AddressPicker(mContext, data);
        picker.setSelectedItem("上海", "上海市", "浦东新区");
        //设置取消按钮文字颜色
        picker.setCancelTextColor(ContextCompat.getColor(mContext, R.color.gray1));
        //设置取消按钮文字大小
        picker.setCancelTextSize(14);
        //设置顶部标题栏下划线颜色
        picker.setTopLineColor(ContextCompat.getColor(mContext, R.color.white1));
        //设置分割线颜色
        picker.setDividerColor(ContextCompat.getColor(mContext, R.color.white1));
        //设置确定按钮文字颜色
        picker.setSubmitTextColor(ContextCompat.getColor(mContext, R.color.blue2));
        picker.setTextColor(ContextCompat.getColor(mContext, R.color.blue2));
        picker.setSubmitTextSize(14);
        //设置标题文字颜色
        picker.setTitleTextColor(ContextCompat.getColor(mContext, R.color.gray5));
        //设置标题文字
        picker.setTitleText("选择城市区域");
        //设置标题文字大小
        picker.setTitleTextSize(14);
        picker.setOnAddressPickListener(new AddressPicker.OnAddressPickListener() {
            @Override
            public void onAddressPicked(Province province, City city, County county) {
                placeListener.setProvince(province.getAreaName());
                //城市
                placeListener.setCity(city.getAreaName());
                //区县（如果设定了两级联动，那么该项返回空）
                placeListener.setDistrict(county.getAreaName());
                //全部地址
                placeListener.setAllPlace(province.getAreaName() + " " + city.getAreaName() + " " + county.getAreaName());
            }
        });
        picker.show();
    }*/
}
