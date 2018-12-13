package com.wjn.myview.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.LinearLayout;

import com.wjn.myview.R;
import com.wjn.myview.view.GuaGuaLe;

public class GuaGuaLeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guaguale);

        LinearLayout linearLayout= findViewById(R.id.activity_guaguale_linearlayout);
        GuaGuaLe guaGuaLe=new GuaGuaLe(GuaGuaLeActivity.this,"二等奖—五百万");
        linearLayout.addView(guaGuaLe);
    }
}
