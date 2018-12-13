package com.wjn.myview.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.wjn.myview.R;
import com.wjn.myview.constant.MyConstant;
import com.wjn.myview.view.ImageViewScrollView;

public class ImageViewScrollViewActivity extends AppCompatActivity {

    private ImageViewScrollView imageViewScrollView;
    private ImageView imageView;
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_imageviewscrollview);

        imageViewScrollView=findViewById(R.id.activity_imageviewscrollview_scrollview);
        imageView=findViewById(R.id.activity_imageviewscrollview_imageview);
        textView=findViewById(R.id.activity_imageviewscrollview_textview);
        textView.setText(MyConstant.switchVaule);
    }
}
