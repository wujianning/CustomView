package com.wjn.myview.view;

import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;

public class MarqueeText extends android.support.v7.widget.AppCompatTextView {
    public MarqueeText(Context con) {
        super(con);
    }

    public MarqueeText(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MarqueeText(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    /**
     * 当前控件的焦点,第一次xml加载 的情况
     * */

    @Override
    public boolean isFocused() {
        return true;
    }

    /**
     * 在更改焦点时,有别的控件申请焦点的情况下
     * */

    @Override
    protected void onFocusChanged(boolean focused, int direction, Rect previouslyFocusedRect) {
        if(focused){
            super.onFocusChanged(focused, direction, previouslyFocusedRect);
        }
    }

    /**
     * 弹出对话框的情况下
     * */

    @Override
    public void onWindowFocusChanged(boolean hasWindowFocus) {
        if(hasWindowFocus){
            super.onWindowFocusChanged(hasWindowFocus);
        }
    }
}
