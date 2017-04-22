package com.itheima.leon.bottombardemo.app.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Lancen on 2017/4/17.
 * 作用:基类Fragment
 * 首页:HomeFragment
 *
 *
 * 都要继承该类
 */

public abstract class BaseFragment extends Fragment {
    protected Context mContext;
    /*
     * 当该类被系统创建的时候被调用
     */

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext=getActivity();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return  initView();
    }
    /*
      抽象类:由孩子实现不同的效果
     */
    public abstract View initView();

    /*
     当Activity被创建的时候回调这个方法
     */

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initDate();
    }
    /*
      当子类需要联网请求数据的时候，重写该方法,在该方法中联网
     */
    public void initDate() {

    }
}
