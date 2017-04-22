package com.itheima.leon.bottombardemo.app;

import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.widget.FrameLayout;

import com.itheima.leon.bottombardemo.R;
import com.itheima.leon.bottombardemo.app.base.BaseFragment;
import com.itheima.leon.bottombardemo.app.club.fragment.ClubFragment;
import com.itheima.leon.bottombardemo.app.home.fragment.HomeFragment;
import com.itheima.leon.bottombardemo.app.news.fragment.NewsFragmet;
import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.OnTabSelectListener;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Lancen on 2017/4/17.
 */

public class MainActivity extends FragmentActivity {


    @Bind(R.id.contentContainer)
    FrameLayout contentContainer;
    @Bind(R.id.bottomBar)
    BottomBar bottomBar;

    private ArrayList<BaseFragment> fragments;
    private int position;
    private Fragment tempFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //ButterKnife和当前Activity绑定
        ButterKnife.bind(this);
        initFragment();
        bottomBar.selectTabWithId(R.id.tab_favorites);


        BottomBar bottomBar = (BottomBar) findViewById(R.id.bottomBar);
        bottomBar.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelected(@IdRes int tabId) {
                switch (tabId) {
                    case R.id.tab_favorites:
                        position = 0;
                        break;
                    case R.id.tab_nearby:
                        position = 1;
                        break;
                    case R.id.tab_friends:
                        position = 2;
                        break;
                    default:
                        position = 0;
                        break;
                }
                //根据位置取不同的Fragment
                BaseFragment baseFragment=getFragment(position);
                /*
                 * 第一参数：上次显示的Fragment
                 * 第二参数：当前正要显示的Fragment
                 */
                switchFragment(tempFragment,baseFragment);

            }
        });
    }

    private void initFragment() {
        fragments = new ArrayList<>();
        fragments.add(new HomeFragment());
        fragments.add(new ClubFragment());
        fragments.add(new NewsFragmet());
    }

    private BaseFragment getFragment(int position) {
        if (fragments!=null && fragments.size()>0){
            BaseFragment baseFragment=fragments.get(position);
            return baseFragment;
        }
        return null;
    }
    /*
     * 切换Fragment
     */
    private void switchFragment(Fragment fromFragment,BaseFragment nextFragment){
        if (tempFragment!=nextFragment){
            tempFragment=nextFragment;
            if (nextFragment!=null){
                FragmentTransaction transaction=getSupportFragmentManager().beginTransaction();
                //判断nextFragment是否添加
                if (!nextFragment.isAdded()){
                    //隐藏当前Fragment
                    if (fromFragment!=null){
                        transaction.hide(fromFragment);
                    }
                    //添加Fragment
                    transaction.add(R.id.contentContainer,nextFragment).commit();
                }else {
                    //隐藏当前Fragment
                    if (fromFragment!=null){
                        transaction.hide(fromFragment);
                    }
                    transaction.show(nextFragment).commit();
                }

            }
        }
    }



}

