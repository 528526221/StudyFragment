package com.xulc.studyfragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.LinkedList;
import java.util.List;

/**
 * 懒加载功能的fragment基类
 * Created by acer on 2016/4/24.
 */
public abstract class BaseLazyFragment extends Fragment {
    public View rootView;
    private boolean isInit; // 是否可以开始加载数据

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        isInit = true;
        if (rootView == null) {
            setContentView(inflater, container);
            findView();
            setListener();
        } else {
            ViewGroup parent = (ViewGroup) rootView.getParent();
            if (parent != null) {
                parent.removeView(rootView);
            }
        }

        MyApplication.addFragment(this);

        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        MyApplication.removeFragment(this);
    }

    @Override
    public void onResume() {
        super.onResume();
        // 判断当前fragment是否显示
        if (getUserVisibleHint()) {
            showData();
        }
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        // 每次切换fragment时调用的方法
        if (isVisibleToUser) {
            showData();
        }

    }

    private void showData(){
        if (isInit) {
            isInit = false;//加载数据完成
            underCreate();
        }


    }

    /**
     * 设置上下文布局
     */
    protected abstract void setContentView(LayoutInflater inflater, ViewGroup container);

    /**
     * 初始化控件
     */
    public abstract void findView();

    /**
     * 设置监听器
     */
    public abstract void setListener();

    /**
     * onCreate()事件处理
     */
    public abstract void underCreate();


}
