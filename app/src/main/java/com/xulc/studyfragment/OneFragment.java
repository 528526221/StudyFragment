package com.xulc.studyfragment;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;

/**
 * fragment碎片化开发时，为了返回栈的上一层时不要重新加载数据，因此不需要懒加载形式的fragment
 * Created by xuliangchun on 2017/2/14.
 */
public class OneFragment extends BaseFragment {
    private Button btnNext;
    private EditText etText;


    @Override
    protected void setContentView(LayoutInflater inflater, ViewGroup container) {
        rootView = inflater.inflate(R.layout.fragment_one,container,false);
    }

    @Override
    public void findView() {
        btnNext = (Button) rootView.findViewById(R.id.btnNext);
        etText = (EditText) rootView.findViewById(R.id.etText);
    }

    @Override
    public void setListener() {
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.setCustomAnimations(R.anim.trans_right_in, R.anim.trans_left_out);
                transaction.add(R.id.flContainer, new TwoFragment(), TwoFragment.class
                        .getName());
                transaction.hide(MyApplication.getLastFragment());
                transaction.addToBackStack(null);
                transaction.commit();
                Log.i("xlc", "有人click了 one");
            }
        });
    }


    @Override
    public void underCreate() {
        Log.i("xlc","第1个fragment");
        btnNext.requestFocus();
        new Handler(){
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                etText.setText("我是1请求回来的数据");
                etText.startAnimation(AnimationUtils.loadAnimation(getContext(),R.anim.alphaanim));
            }
        }.sendEmptyMessageDelayed(0, 200);
    }


}
