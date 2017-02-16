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
 * Created by xuliangchun on 2017/2/14.
 */
public class TwoFragment extends BaseFragment {
    private Button btnNext;
    private EditText etText;
    @Override
    protected void setContentView(LayoutInflater inflater, ViewGroup container) {
        rootView = inflater.inflate(R.layout.fragment_two,container,false);
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
                transaction.add(R.id.flContainer, new ThreeFragment(), ThreeFragment.class
                        .getName());
                transaction.hide(MyApplication.getLastFragment());
                transaction.addToBackStack(null);
                transaction.commit();

                Log.i("xlc", "有人click了 two");
            }
        });
    }

    @Override
    public void underCreate() {
        Log.i("xlc","第2个fragment");
        btnNext.requestFocus();

        new Handler(){
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                etText.setText("我是2请求回来的数据");
                etText.startAnimation(AnimationUtils.loadAnimation(getContext(), R.anim.translateanim));
            }
        }.sendEmptyMessageDelayed(0, 200);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.i("xlc", "第2个fragment onCreateView");
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.i("xlc", "第2个fragment onDestroyView");
    }

}
