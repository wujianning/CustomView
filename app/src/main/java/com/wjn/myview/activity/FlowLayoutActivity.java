package com.wjn.myview.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.wjn.myview.R;
import com.wjn.myview.bean.MyJaveBean;
import com.wjn.myview.view.TagsLayout;

import java.util.ArrayList;
import java.util.List;

public class FlowLayoutActivity extends AppCompatActivity {

    private TagsLayout tagsLayout;
    private ViewGroup.MarginLayoutParams lp;
    private List<MyJaveBean> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flowlayout);

        tagsLayout=findViewById(R.id.activity_flowlayout_tagsLayout);
        lp= new ViewGroup.MarginLayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);

        list=new ArrayList<MyJaveBean>();
        MyJaveBean myJaveBean1=new MyJaveBean();
        myJaveBean1.setName("北京航空航天大学");
        myJaveBean1.setState("0");

        MyJaveBean myJaveBean2=new MyJaveBean();
        myJaveBean2.setName("李四");
        myJaveBean2.setState("0");

        MyJaveBean myJaveBean3=new MyJaveBean();
        myJaveBean3.setName("王五");
        myJaveBean3.setState("0");

        MyJaveBean myJaveBean4=new MyJaveBean();
        myJaveBean4.setName("韦德");
        myJaveBean4.setState("0");

        MyJaveBean myJaveBean5=new MyJaveBean();
        myJaveBean5.setName("詹姆斯你好好好");
        myJaveBean5.setState("0");

        MyJaveBean myJaveBean6=new MyJaveBean();
        myJaveBean6.setName("蒿俊闵三哥");
        myJaveBean6.setState("0");

        for(int i=0;i<5;i++){
            list.add(myJaveBean1);
            list.add(myJaveBean2);
            list.add(myJaveBean3);
            list.add(myJaveBean4);
            list.add(myJaveBean5);
            list.add(myJaveBean6);
        }

        initLabel(list);

    }

    /**
     * 定义终止随访标签
     * */

    private void initLabel(final List<MyJaveBean> list){
        if(null==list||list.size()<=0){
            return;
        }
        int num=list.size();
        tagsLayout.removeAllViews();
        for(int i=0;i<num;i++){
            TextView textview=new TextView(this);
            textview.setGravity(Gravity.CENTER);
            textview.setTextSize(14);
            String name=list.get(i).getName();
            String state=list.get(i).getState();
            textview.setText(name);
            if("1".equals(state)){//选中
                textview.setBackgroundResource(R.drawable.label_p_background);
                textview.setTextColor(getResources().getColor(R.color.whilecolor));
            }else{//未选中
                textview.setBackgroundResource(R.drawable.label_background);
                textview.setTextColor(getResources().getColor(R.color.textviewcolor_696969));
            }
            tagsLayout.addView(textview, lp);
            final int position=i;
            textview.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(list.size()>0&&position<list.size()){
                        MyJaveBean oldjavabean=list.get(position);
                        String state=oldjavabean.getState();
                        String name=oldjavabean.getName();
                        String states=state;
                        if("1".equals(state)){//选中状态 点击 取消选中
                            states="0";
                        }else {//未选中状态 点击 选中
                            states="1";
                        }
                        MyJaveBean newjavabean=new MyJaveBean();
                        newjavabean.setName(name);
                        newjavabean.setState(states);
                        list.set(position,newjavabean);
                        initLabel(list);
                    }
                }
            });
        }
    }

}
