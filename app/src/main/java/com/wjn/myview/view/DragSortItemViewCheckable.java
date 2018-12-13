package com.wjn.myview.view;

import android.content.Context;
import android.view.View;
import android.widget.Checkable;

public class DragSortItemViewCheckable extends DragSortItemView implements Checkable {

    public DragSortItemViewCheckable(Context context) {
        super(context);
    }

    @Override
    public boolean isChecked() {
        View child = getChildAt(0);
        if (child instanceof Checkable)
            return ((Checkable) child).isChecked();
        else
            return false;
    }

    @Override
    public void setChecked(boolean checked) {
        View child = getChildAt(0);
        if (child instanceof Checkable)
            ((Checkable) child).setChecked(checked);
    }

    @Override
    public void toggle() {
        View child = getChildAt(0);
        if (child instanceof Checkable)
            ((Checkable) child).toggle();
    }
}

