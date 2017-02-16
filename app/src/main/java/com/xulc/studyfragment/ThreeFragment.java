package com.xulc.studyfragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;

/**
 * Created by xuliangchun on 2017/2/14.
 */
public class ThreeFragment extends BaseFragment {

    @Override
    protected void setContentView(LayoutInflater inflater, ViewGroup container) {
        rootView = inflater.inflate(R.layout.fragment_three,container,false);
    }

    @Override
    public void findView() {

    }

    @Override
    public void setListener() {

    }

    @Override
    public void underCreate() {
        Log.i("xlc", "第3个fragment");
    }

}
