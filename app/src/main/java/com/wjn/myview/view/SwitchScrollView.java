package com.wjn.myview.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ScrollView;

import com.wjn.myview.myinterface.SwitchScrollViewListener;

/**
 * 自定义ScrollView 滑动切换标题
 * */

public class SwitchScrollView extends ScrollView {

    private SwitchScrollViewListener switchScrollViewListener = null;

    public void setScrollViewListener(SwitchScrollViewListener switchScrollViewListener) {
        this.switchScrollViewListener = switchScrollViewListener;
    }

    public SwitchScrollView(Context context) {
        super(context);
    }

    public SwitchScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public SwitchScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);
        if (null != switchScrollViewListener) {
            switchScrollViewListener.onScrollChanged(this, l, t, oldl, oldt);
        }
    }
}
