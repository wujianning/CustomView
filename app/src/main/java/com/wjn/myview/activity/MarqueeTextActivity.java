package com.wjn.myview.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.wjn.myview.R;
import com.wjn.myview.view.MarqueeText;

/**
 * 自定义TextView TextView跑马灯
 * */

public class MarqueeTextActivity extends AppCompatActivity {

    private MarqueeText marqueeText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_marqueetext);

        marqueeText= findViewById(R.id.activity_motionevent_marqueetext);
        marqueeText.setText("借助 Android Things，您可以大规模构建和维护 IoT 设备。" +
                "我们最近发布了 Android Things 1.0 正式版，它将为生产设备提供长期支持，" +
                "帮助您轻松地将 IoT 设备从原型设计推进到商品化。");
    }
}
