package com.itheima.leon.bottombardemo.app.club.fragment;

import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import com.itheima.leon.bottombardemo.app.base.BaseFragment;

/**
 * Created by Lancen on 2017/4/17.
 * 作用：社团的Fragment
 */

public class ClubFragment extends BaseFragment {
    private TextView textView;
    @Override
    public View initView() {
        textView=new TextView(mContext);
        textView.setGravity(Gravity.CENTER);
        textView.setTextSize(25);
        return textView;
    }

    @Override
    public void initDate() {
        super.initDate();
        textView.setText("我好酷");
    }
}
