package com.umeng.soexample.weidumovie.custom;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.umeng.soexample.weidumovie.R;


public class TakePhotoPopWin extends PopupWindow implements View.OnClickListener {
    private Context mContext;

    private View view;

    private TextView tvTake, tvAlbum, tvCancel;

    public TakePhotoPopWin(Context mContext, View.OnClickListener onClickListener) {
        this.view = LayoutInflater.from(mContext).inflate(R.layout.popwindow_camera_photo, null);

        tvTake = view.findViewById(R.id.choos_photo_popup_pz);
        tvAlbum = view.findViewById(R.id.choos_photo_popup_xc);
        tvCancel = view.findViewById(R.id.choos_photo_popup_qx);

        tvTake.setOnClickListener(onClickListener);
        tvAlbum.setOnClickListener(onClickListener);
        tvCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });

        this.view.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                int height = view.findViewById(R.id.id_pop_layout).getTop();
                int y = (int) event.getY();
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    if (height > y) {
                        dismiss();
                    }
                }
                return true;
            }
        });
 /*  设置弹出窗口特征*/
        // 设置视图
        this.setContentView(this.view);
        // 设置弹出窗体的宽和高
        this.setHeight(RelativeLayout.LayoutParams.MATCH_PARENT);
        this.setWidth(RelativeLayout.LayoutParams.MATCH_PARENT);
        // 设置弹出窗体可点击
        this.setFocusable(true);
        // 实例化一个ColorDrawable颜色为半透明
        ColorDrawable drawable = new ColorDrawable(0xb0000000);
        // 设置弹出窗体的背景
        this.setBackgroundDrawable(drawable);
        // 设置弹出窗体显示时的动画，从底部向上弹出
        this.setAnimationStyle(R.style.popwin_anim_style);
    }

    @Override
    public void onClick(View v) {
    }
}
