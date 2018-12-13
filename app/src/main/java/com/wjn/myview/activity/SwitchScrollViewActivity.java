package com.wjn.myview.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.wjn.myview.R;
import com.wjn.myview.constant.MyConstant;
import com.wjn.myview.myinterface.SwitchScrollViewListener;
import com.wjn.myview.view.SwitchScrollView;

/**
 * ScrollView滑动切换标题
 * */

public class SwitchScrollViewActivity extends AppCompatActivity implements SwitchScrollViewListener {

    private SwitchScrollView switchScrollView;
    private int height=30;
    private TextView titleTextView;
    private TextView contextTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_switchscrollview);

        switchScrollView=findViewById(R.id.activity_switchscrollview_switchscrollview);
        switchScrollView.setScrollViewListener(this);
        titleTextView=findViewById(R.id.activity_switchscrollview_titletextview);
        contextTextView=findViewById(R.id.activity_switchscrollview_contexttextview);
        contextTextView.setText(MyConstant.switchVaule);
    }

    /**
     * 重写 监听ScrollView滑动方法
     * */

    @Override
    public void onScrollChanged(SwitchScrollView scrollView, int x, int y, int oldx, int oldy) {
        if(y<=height){
            titleTextView.setText("职位详情");
        }else{
            titleTextView.setText("Android工程师实习岗位");
        }

    }
}
