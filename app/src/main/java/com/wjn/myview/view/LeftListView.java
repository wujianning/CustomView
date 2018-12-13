package com.wjn.myview.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ListView;

import com.wjn.myview.R;

public class LeftListView extends ListView {

    private Boolean mIsHorizontal;
    private View mPreItemView;
    private View mCurrentItemView;
    private float mFirstX;
    private float mFirstY;
    private int mRightViewWidth;
    private final int mDuration = 100;
    private final int mDurationStep = 10;
    private boolean mIsShown;

    public LeftListView(Context context) {
        this(context, null);
    }

    public LeftListView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public LeftListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray mTypedArray = context.obtainStyledAttributes(attrs,R.styleable.swipelistviewstyle);
        //获取自定义属性和默认值
        mRightViewWidth = (int) mTypedArray.getDimension(R.styleable.swipelistviewstyle_right_width, 200);
        mTypedArray.recycle();
    }

    /**
     * Handler 类
     * */

    private class MoveHandler extends Handler {
        int stepX = 0;
        int fromX;
        int toX;
        View view;
        private boolean mIsInAnimation = false;

        private void animatioOver() {
            mIsInAnimation = false;
            stepX = 0;
        }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (stepX == 0) {
                if (mIsInAnimation) {
                    return;
                }
                mIsInAnimation = true;
                view = (View) msg.obj;
                fromX = msg.arg1;
                toX = msg.arg2;
                stepX = (int) ((toX - fromX) * mDurationStep * 1.0 / mDuration);
                if (stepX < 0 && stepX > -1) {
                    stepX = -1;
                } else if (stepX > 0 && stepX < 1) {
                    stepX = 1;
                }
                if (Math.abs(toX - fromX) < 10) {
                    view.scrollTo(toX, 0);
                    animatioOver();
                    return;
                }
            }
            fromX += stepX;
            boolean isLastStep = (stepX > 0 && fromX > toX) || (stepX < 0 && fromX < toX);
            if (isLastStep) {
                fromX = toX;
            }
            view.scrollTo(fromX, 0);
            invalidate();
            if (!isLastStep) {
                this.sendEmptyMessageDelayed(0, mDurationStep);
            } else {
                animatioOver();
            }
        }
    }

    /**
     * onInterceptTouchEvent方法
     * */

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        float lastX = ev.getX();
        float lastY = ev.getY();
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mIsHorizontal = null;
                mFirstX = lastX;
                mFirstY = lastY;
                int motionPosition = pointToPosition((int) mFirstX, (int) mFirstY);
                if (motionPosition >= 0) {
                    View currentItemView = getChildAt(motionPosition - getFirstVisiblePosition());
                    mPreItemView = mCurrentItemView;
                    mCurrentItemView = currentItemView;
                }
                break;
            case MotionEvent.ACTION_MOVE:
                float dx = lastX - mFirstX;
                float dy = lastY - mFirstY;
                if (Math.abs(dx) >= 5 && Math.abs(dy) >= 5) {
                    return true;
                }
                break;
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_CANCEL:
                if (mIsShown && (mPreItemView != mCurrentItemView || isHitCurItemLeft(lastX))) {
                    hiddenRight(mPreItemView);
                }
                break;
        }
        return super.onInterceptTouchEvent(ev);
    }

    /**
     * onTouchEvent方法
     * */

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        float lastX = ev.getX();
        float lastY = ev.getY();
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                break;
            case MotionEvent.ACTION_MOVE:
                float dx = lastX - mFirstX;
                float dy = lastY - mFirstY;
                if (mIsHorizontal == null) {
                    if (!judgeScrollDirection(dx, dy)) {
                        break;
                    }
                }
                if (mIsHorizontal) {
                    if (mIsShown && mPreItemView != mCurrentItemView) {

                        /**
                         * 一个Item的右边布局已经显示
                         * 这时候左右滑动另外一个item,那个右边布局显示的item隐藏其右边布局
                         * 向左滑动只触发该情况，向右滑动还会触发情况五
                         */

                        hiddenRight(mPreItemView);
                    }
                    if (mIsShown && mPreItemView == mCurrentItemView) {
                        dx = dx - mRightViewWidth;
                    }
                    if (dx < 0 && dx > -mRightViewWidth) {
                        mCurrentItemView.scrollTo((int) (-dx), 0);
                    }
                    return true;
                } else {
                    if (mIsShown) {

                        /**
                         * 一个Item的右边布局已经显示
                         * 这时候上下滚动ListView,那么那个右边布局显示的item隐藏其右边布局
                         */

                        hiddenRight(mPreItemView);
                    }
                }
                break;
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_CANCEL:
                clearPressedState();
                if (mIsShown) {

                    /**
                     * 一个Item的右边布局已经显示
                     * 这时候左右滑动当前一个item,那个右边布局显示的item隐藏其右边布局
                     */

                    hiddenRight(mPreItemView);
                }
                if (mIsHorizontal != null && mIsHorizontal) {
                    if (mFirstX - lastX > mRightViewWidth / 2) {
                        showRight(mCurrentItemView);
                    } else {

                        /**
                         * 向右滑动一个item,且滑动的距离超过了右边View的宽度的一半，隐藏。
                         */

                        hiddenRight(mCurrentItemView);
                    }
                    return true;
                }
                break;
        }
        return super.onTouchEvent(ev);
    }

    /**
     * 滑动的方向
     * 纵向 横向
     * */

    private boolean judgeScrollDirection(float dx, float dy) {
        boolean canJudge = true;
        if (Math.abs(dx) > 30 && Math.abs(dx) > 2 * Math.abs(dy)) {
            mIsHorizontal = true;
        } else if (Math.abs(dy) > 30 && Math.abs(dy) > 2 * Math.abs(dx)) {
            mIsHorizontal = false;
        } else {
            canJudge = false;
        }
        return canJudge;
    }

    /**
     * 是否点击右侧删除布局
     * */

    private boolean isHitCurItemLeft(float x) {
        return x < getWidth() - mRightViewWidth;
    }

    /**
     * 清空状态
     * */

    private void clearPressedState() {
        if (mCurrentItemView != null) {
            mCurrentItemView.setPressed(false);
        }
        setPressed(false);
        refreshDrawableState();
    }

    /**
     * 显示右侧删除布局
     * */

    private void showRight(View view) {
        Message msg = new MoveHandler().obtainMessage();
        msg.obj = view;
        msg.arg1 = view.getScrollX();
        msg.arg2 = mRightViewWidth;
        msg.sendToTarget();
        mIsShown = true;
    }

    /**
     * 隐藏右侧删除布局
     * */

    private void hiddenRight(View view) {
        if (mCurrentItemView == null) {
            return;
        }
        Message msg = new MoveHandler().obtainMessage();
        msg.obj = view;
        msg.arg1 = view.getScrollX();
        msg.arg2 = 0;
        msg.sendToTarget();
        mIsShown = false;
    }

    /**
     * 获取右侧删除布局宽度
     * */

    public int getRightViewWidth() {
        return mRightViewWidth;
    }

    /**
     * 设置右侧删除布局宽度
     * */

    public void setRightViewWidth(int mRightViewWidth) {
        this.mRightViewWidth = mRightViewWidth;
    }

}
