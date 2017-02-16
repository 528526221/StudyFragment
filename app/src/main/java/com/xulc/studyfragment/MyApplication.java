package com.xulc.studyfragment;

import android.app.Application;
import android.support.v4.app.Fragment;

import java.util.LinkedList;

/**
 * Created by xuliangchun on 2017/2/16.
 */
public class MyApplication extends Application {
    private static LinkedList<Fragment> fragmentLinkedList = new LinkedList<>();
    @Override
    public void onCreate() {
        super.onCreate();

    }

    /**
     * 添加fragment到管理中
     * @param fragment
     */
    public static void addFragment(Fragment fragment){
        fragmentLinkedList.add(fragment);
    }

    /**
     * 从管理中移除fragment
     * @param fragment
     */
    public static void  removeFragment(Fragment fragment){
        fragmentLinkedList.remove(fragment);
    }

    /**
     * 获取当前最顶层fragment
     * @return
     */
    public static Fragment getLastFragment(){
        return fragmentLinkedList.get(fragmentLinkedList.size()-1);
    }



}
