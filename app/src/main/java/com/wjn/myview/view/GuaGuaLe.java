package com.wjn.myview.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.wjn.myview.R;

public class GuaGuaLe extends View {

    private Paint mOutterPaint = new Paint();//图层画笔
    private Path mPath = new Path();
    private Canvas mCanvas;
    private Bitmap mBitmap;
    private boolean isComplete;
    private Paint mBackPint = new Paint();//刮开后字体画笔
    private Rect mTextBound = new Rect();
    private String mText = "￥500,0000";
    private int mLastX;
    private int mLastY;

    public GuaGuaLe(Context context, String mText) {
        super(context);
        this.mText=mText;
        init();
    }

    public GuaGuaLe(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public GuaGuaLe(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    /**
     * 初始化view
     * */

    private void init() {
        mPath = new Path();
        setUpOutPaint();
        setUpBackPaint();
    }

    /**
     * 初始化canvas的绘制用的画笔
     */
    private void setUpBackPaint() {
        mBackPint.setStyle(Paint.Style.FILL);
        mBackPint.setTextScaleX(2f);
        mBackPint.setColor(Color.WHITE);
        mBackPint.setTextSize(32);
        mBackPint.getTextBounds(mText, 0, mText.length(), mTextBound);
    }

    /**
     * onDraw方法
     * */

    @Override
    protected void onDraw(Canvas canvas) {
        // 绘制奖项
        canvas.drawText(mText, getWidth() / 2 - mTextBound.width() / 2, getHeight() / 2 + mTextBound.height() / 2, mBackPint);
        if (!isComplete) {
            drawPath();
            canvas.drawBitmap(mBitmap, 0, 0, null);
        }
    }

    /**
     * onMeasure方法
     * */

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int width = getMeasuredWidth();
        int height = getMeasuredHeight();
        // 初始化bitmap
        mBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        mCanvas = new Canvas(mBitmap);
        // 绘制遮盖层
        mOutterPaint.setStyle(Paint.Style.FILL);
        mCanvas.drawRoundRect(new RectF(0, 0, width, height), 30, 30, mOutterPaint);//有圆弧
        mCanvas.drawRoundRect(new RectF(0, 0, width, height), 0, 0, mOutterPaint);//没圆弧
        mCanvas.drawBitmap(BitmapFactory.decodeResource(getResources(), R.mipmap.followup_def), null, new RectF(0, 0, width, height), null);
    }

    /**
     * 设置画笔的一些参数
     */

    private void setUpOutPaint() {
        mOutterPaint.setColor(Color.parseColor("#c0c0c0"));
        mOutterPaint.setAntiAlias(true);
        mOutterPaint.setDither(true);
        mOutterPaint.setStyle(Paint.Style.STROKE);
        mOutterPaint.setStrokeJoin(Paint.Join.ROUND); // 圆角
        mOutterPaint.setStrokeCap(Paint.Cap.ROUND); // 圆角
        // 设置画笔宽度
        mOutterPaint.setStrokeWidth(40);
    }

    /**
     * 绘制线条
     */

    private void drawPath() {
        mOutterPaint.setStyle(Paint.Style.STROKE);
        mOutterPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_OUT));
        mCanvas.drawPath(mPath, mOutterPaint);
    }

    /**
     * onTouchEvent方法
     * */

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int action = event.getAction();
        int x = (int) event.getX();
        int y = (int) event.getY();
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                mLastX = x;
                mLastY = y;
                mPath.moveTo(mLastX, mLastY);
                break;
            case MotionEvent.ACTION_MOVE:
                int dx = Math.abs(x - mLastX);
                int dy = Math.abs(y - mLastY);
                if (dx > 3 || dy > 3)
                    mPath.lineTo(x, y);
                mLastX = x;
                mLastY = y;
                break;
            case MotionEvent.ACTION_UP:
                new Thread(mRunnable).start();
                break;
        }
        invalidate();//刷新View UI线程中
        return true;
    }

    /**
     * 统计擦除区域任务
     */

    private Runnable mRunnable = new Runnable() {

        private int[] mPixels;

        @Override
        public void run() {
            int w = getWidth();
            int h = getHeight();
            float wipeArea = 0;
            float totalArea = w * h;
            Bitmap bitmap = mBitmap;
            mPixels = new int[w * h];

            /**
             * 拿到所有的像素信息
             */

            bitmap.getPixels(mPixels, 0, w, 0, 0, w, h);

            /**
             * 遍历统计擦除的区域
             */

            for (int i = 0; i < w; i++) {
                for (int j = 0; j < h; j++) {
                    int index = i + j * w;
                    if (mPixels[index] == 0) {
                        wipeArea++;
                    }
                }
            }

            /**
             * 根据所占百分比，进行一些操作
             */

            if (wipeArea > 0 && totalArea > 0) {
                int percent = (int) (wipeArea * 100 / totalArea);
                if (percent > 40) {//清除区域达到40%，下面自动清除
                    isComplete = true;
                    postInvalidate();
                }
            }
        }
    };
}
