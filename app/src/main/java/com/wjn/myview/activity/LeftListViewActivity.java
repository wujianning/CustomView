package com.wjn.myview.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.wjn.myview.R;
import com.wjn.myview.adapter.LeftAdapter;
import com.wjn.myview.bean.MyJaveBean;
import com.wjn.myview.myinterface.onRightItemClickListener;
import com.wjn.myview.view.LeftListView;

import java.util.ArrayList;
import java.util.List;

/**
 * 自定义 ListView左划删除
 * */

public class LeftListViewActivity extends AppCompatActivity {

    private LeftListView leftListView;
    private LeftAdapter leftAdapter;
    private List<MyJaveBean> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leftlistview);

        leftListView=findViewById(R.id.activity_leftlistview_listview);

        list=new ArrayList<MyJaveBean>();
        MyJaveBean myJaveBean1=new MyJaveBean();
        myJaveBean1.setName("张三");

        MyJaveBean myJaveBean2=new MyJaveBean();
        myJaveBean2.setName("李四");

        MyJaveBean myJaveBean3=new MyJaveBean();
        myJaveBean3.setName("王五");

        MyJaveBean myJaveBean4=new MyJaveBean();
        myJaveBean4.setName("韦德");

        MyJaveBean myJaveBean5=new MyJaveBean();
        myJaveBean5.setName("詹姆斯");

        MyJaveBean myJaveBean6=new MyJaveBean();
        myJaveBean6.setName("蒿俊闵");

        for(int i=0;i<10;i++){
            list.add(myJaveBean1);
            list.add(myJaveBean2);
            list.add(myJaveBean3);
            list.add(myJaveBean4);
            list.add(myJaveBean5);
            list.add(myJaveBean6);
        }

        leftAdapter=new LeftAdapter(LeftListViewActivity.this,list,leftListView.getRightViewWidth());
        leftListView.setAdapter(leftAdapter);
        //左划删除
        leftAdapter.setOnRightItemClickListener(new onRightItemClickListener() {

            @Override
            public void onRightItemClick(View v, int position) {
                String result=list.get(position).getName()+"\n"+position;
                Toast.makeText(LeftListViewActivity.this,result,Toast.LENGTH_LONG).show();
            }
        });

    }
}
