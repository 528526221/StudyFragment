package com.xulc.studyfragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * 基本功能的fragment基类
 * Created by acer on 2016/4/24.
 */
public abstract class BaseFragment extends Fragment {
    public View rootView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (rootView == null) {
            setContentView(inflater, container);
            findView();
            setListener();
            underCreate();
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
