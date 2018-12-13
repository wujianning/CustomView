package com.wjn.myview.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.wjn.myview.R;
import com.wjn.myview.adapter.DragSortAdapter;
import com.wjn.myview.bean.MyJaveBean;
import com.wjn.myview.view.DragSortListView;

import java.util.ArrayList;
import java.util.List;

public class DragSortListViewActivity extends AppCompatActivity {

    private DragSortListView dragSortListView;
    private DragSortAdapter dragSortAdapter;
    private List<MyJaveBean> list;

    // 监听器在手机拖动停下的时候触发
    private DragSortListView.DropListener onDrop = new DragSortListView.DropListener() {
        @Override
        public void drop(int from, int to) {// from to 分别表示 被拖动控件原位置 和目标位置
            if (from != to) {
                MyJaveBean item = (MyJaveBean) dragSortAdapter.getItem(from);// 得到listview的适配器
                dragSortAdapter.remove(from);// 在适配器中”原位置“的数据。
                dragSortAdapter.insert(item, to);// 在目标位置中插入被拖动的控件。
            }
        }
    };
    // 删除监听器，点击左边差号就触发。删除item操作。
    private DragSortListView.RemoveListener onRemove = new DragSortListView.RemoveListener() {
        @Override
        public void remove(int which) {
            dragSortAdapter.remove(which);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dragsortlistview);

        dragSortListView=findViewById(R.id.activity_dragsortlistview_listview);
        dragSortListView.setDropListener(onDrop);
        dragSortListView.setRemoveListener(onRemove);
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

        dragSortAdapter=new DragSortAdapter(DragSortListViewActivity.this,list);
        dragSortListView.setAdapter(dragSortAdapter);
        dragSortListView.setDragEnabled(true); // 设置是否可拖动。

    }
}
