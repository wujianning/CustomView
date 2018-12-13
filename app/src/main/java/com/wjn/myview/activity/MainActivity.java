package com.wjn.myview.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.wjn.myview.R;

public class MainActivity extends AppCompatActivity {

    private TextView textView1;
    private TextView textView2;
    private TextView textView3;
    private TextView textView4;
    private TextView textView5;
    private TextView textView6;
    private TextView textView7;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //ScrollView滑动切换标题
        textView1=findViewById(R.id.activity_main_textview1);
        textView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,SwitchScrollViewActivity.class);
                startActivity(intent);
            }
        });

        //ScrollView滑动拉伸头部ImageView
        textView2=findViewById(R.id.activity_main_textview2);
        textView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,ImageViewScrollViewActivity.class);
                startActivity(intent);
            }
        });

        //ListView左划删除
        textView3=findViewById(R.id.activity_main_textview3);
        textView3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,LeftListViewActivity.class);
                startActivity(intent);
            }
        });

        //ListView拖动排序
        textView4=findViewById(R.id.activity_main_textview4);
        textView4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,DragSortListViewActivity.class);
                startActivity(intent);
            }
        });

        //TextView跑马灯
        textView5=findViewById(R.id.activity_main_textview5);
        textView5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,MarqueeTextActivity.class);
                startActivity(intent);
            }
        });

        //View实现刮刮卡效果
        textView6=findViewById(R.id.activity_main_textview6);
        textView6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,GuaGuaLeActivity.class);
                startActivity(intent);
            }
        });

        //
        textView7=findViewById(R.id.activity_main_textview7);
        textView7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,FlowLayoutActivity.class);
                startActivity(intent);
            }
        });

    }
}
