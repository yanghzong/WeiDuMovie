package com.umeng.soexample.weidumovie.startactivity.welcomefragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.umeng.soexample.weidumovie.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class FourFragment extends Fragment {

    @BindView(R.id.img_guide_four)
    ImageView imgGuideFour;
    @BindView(R.id.txt_guide_four)
    TextView txtGuideFour;
    @BindView(R.id.txt2_guide_four)
    TextView txt2GuideFour;
    @BindView(R.id.view01)
    View view01;
    @BindView(R.id.view02)
    View view02;
    @BindView(R.id.view03)
    View view03;
    @BindView(R.id.view04)
    View view04;
    Unbinder unbinder;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_four, container, false);
        unbinder = ButterKnife.bind(this, v);
        return v;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }


}
