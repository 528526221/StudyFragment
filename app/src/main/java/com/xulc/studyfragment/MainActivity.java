package com.xulc.studyfragment;

import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private FrameLayout flContainer;
    private Button btnGetTips;
    private Button btnSmall;
    private TextView tvTips;
    private boolean exit;
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            exit = false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        flContainer = (FrameLayout) findViewById(R.id.flContainer);
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        transaction.add(R.id.flContainer, new OneFragment(), OneFragment.class.getName());
        /**
         * 第一个fragment 不需要addToBackStack，后面的都需要
         * 避免fragment覆盖时会出现点击击穿，有2种解决方案：
         * 1.后面的fragment采用replace方式载入
         * 但是会存在新的问题 就是页面返回时数据的恢复问题
         * 2、所有的fragment都采用add的方式载入，布局文件根元素加上click_able = true属性
         * 但是这样的会引入另一个问题，就是遥控器上下操作时fragment的焦点一直会被最底层的所捕获
         *
         * 结论：采用非懒加载的fragment，使用add的方式载入，但每次载入前都将最新的fragment hide
         * （在application中定义了fragment的一个管理栈，用户获取最新的fragment对象）
         * 目前未发现hide之后需要手工控制使其显示，应该是不要的
         */
        transaction.commit();
        manager.addOnBackStackChangedListener(new FragmentManager.OnBackStackChangedListener() {
            @Override
            public void onBackStackChanged() {
                Log.i("xlc", "当前第" + (getSupportFragmentManager().getBackStackEntryCount() + 1) +
                        "页");
            }
        });
        btnGetTips = (Button) findViewById(R.id.btnGetTips);
        tvTips = (TextView) findViewById(R.id.tvTips);
        btnGetTips.setOnClickListener(this);
        btnSmall = (Button) findViewById(R.id.btnSmall);
        btnSmall.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {

            }
        });

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK){
            if (getSupportFragmentManager().getBackStackEntryCount()==0){
                /**
                 * 栈中没有fragment了
                 */
                if (!exit){
                    exit = true;
                    Toast.makeText(this,"再按一次退出",Toast.LENGTH_SHORT).show();
                    handler.sendEmptyMessageDelayed(0,2000);
                    return true;
                }
            }
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public void onClick(View v) {
        List<Fragment> fragments = getSupportFragmentManager().getFragments();
        tvTips.setText("总共有"+(getSupportFragmentManager().getBackStackEntryCount()+1)+"个fragment,"+"当前的fragment为"+fragments.get(getSupportFragmentManager().getBackStackEntryCount()).getTag());
    }



}
