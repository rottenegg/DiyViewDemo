package com.huangjinfu.mydiyviewdemo.views;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Shader;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.huangjinfu.mydiyviewdemo.R;
import com.huangjinfu.mydiyviewdemo.utils.ScreenUtils;

import java.util.logging.Handler;

public class ProgressView extends View {
    public static final String TAG = "ProgressView";
    //控件宽度
    private int widgetWidth;
    //控件高度
    private int widgetHeight;
    //圆角大小
    private int mRadius = ScreenUtils.dip2px(getContext(),5);

    private Paint mPaint;
    RectF rectF ;
    private Paint mProgressPaint;
    RectF progressRectF ;
    private LinearGradient backGradient;
    private int colorS = getResources().getColor(R.color.progress_bg_start);
    private int colorE = getResources().getColor(R.color.progress_bg_end);

    public ProgressView(Context context) {
        super(context);
    }

    public ProgressView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public ProgressView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Log.e(TAG,"onDraw");
        initView();
        drawView(canvas);
    }

    private void initView(){
        rectF =  new RectF(0,0,widgetWidth,widgetHeight);
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setColor(getResources().getColor(R.color.white));
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(ScreenUtils.dip2px(getContext(),1));

        progressRectF =  new RectF(ScreenUtils.dip2px(getContext(),2),ScreenUtils.dip2px(getContext(),2),widgetWidth/2,widgetHeight-ScreenUtils.dip2px(getContext(),2));
        mProgressPaint = new Paint();
        mProgressPaint.setAntiAlias(true);
        mProgressPaint.setColor(getResources().getColor(R.color.white));
        mProgressPaint.setStyle(Paint.Style.FILL);
        backGradient = new LinearGradient(0, 0, widgetWidth-ScreenUtils.dip2px(getContext(),2), 0, new int[]{colorS, colorE}, null, Shader.TileMode.CLAMP);
        mProgressPaint.setShader(backGradient);

    }
    private void drawView(Canvas canvas){
        //绘制外圈
        canvas.drawRoundRect(rectF,mRadius,mRadius,mPaint);
        //绘制内圈
        canvas.drawRoundRect(progressRectF, mRadius, mRadius, mProgressPaint);

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        Log.e(TAG,"onMeasure");
        //view的宽
        widgetWidth = MeasureSpec.getSize(widthMeasureSpec);
        //view的高
        widgetHeight = MeasureSpec.getSize(heightMeasureSpec);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        Log.e(TAG,"onLayout");
    }

    public void startAnimal(){
        widgetWidth = 0;
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (widgetWidth<=800){
                    widgetWidth++;
                    postInvalidate();
                    try {
                        Thread.sleep(5);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

            }
        }).start();
    }


}
