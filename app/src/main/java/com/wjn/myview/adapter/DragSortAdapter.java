package com.wjn.myview.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.wjn.myview.R;
import com.wjn.myview.bean.MyJaveBean;

import java.util.List;

public class DragSortAdapter extends BaseAdapter {

    private Context context;
    private List<MyJaveBean> list;
    public DragSortAdapter(Context context, List<MyJaveBean> list){
        this.context=context;
        this.list=list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public void remove(int arg0) {// 删除指定位置的item
        list.remove(arg0);
        this.notifyDataSetChanged();// 不要忘记更改适配器对象的数据源
    }

    public void insert(MyJaveBean item, int arg0) {// 在指定位置插入item
        list.add(arg0, item);
        this.notifyDataSetChanged();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if(null==convertView){
            convertView=View.inflate(context, R.layout.dragsort_listview_item,null);
            viewHolder=new ViewHolder();
            viewHolder.dellayout= (RelativeLayout) convertView.findViewById(R.id.dragsort_listview_item_layout);
            viewHolder.titletextview= (TextView) convertView.findViewById(R.id.dragsort_listview_item_textview);
            convertView.setTag(viewHolder);
        }else{
            viewHolder= (ViewHolder) convertView.getTag();
        }

        String name=list.get(position).getName();
        viewHolder.titletextview.setText(name);

        return convertView;
    }

    public static class ViewHolder{
        private TextView titletextview;
        private RelativeLayout dellayout;
    }

}
