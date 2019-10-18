package com.huangjinfu.mydiyviewdemo.views;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

/**
 * @Author Huangjinfu
 * @Date 2019-10-18 15:34
 * @Description:
 */
public class DownloadProgressView extends View {
    public static final String TAG = "DownloadProgressView";
    private Paint allProgressPaint;
    private RectF allProgressRectf;
    private RectF allLeftRectf;
    private RectF allRightRectf;
    //控件宽度
    private int widgetWidth;
    //控件高度
    private int widgetHeight;
    private int allProgressLength = 800;
    private int progressLength = 0;

    private Paint progressPaint, progressPaintCircle;
    private RectF progressRectf;
    private RectF leftRectf;
    private RectF rightRectf;

    private void init() {
        allProgressPaint = new Paint();
        allProgressPaint.setAntiAlias(true);
        allProgressPaint.setColor(Color.RED);
        allProgressPaint.setStyle(Paint.Style.FILL);
        allProgressRectf = new RectF();
        allLeftRectf = new RectF(0, 0, 100, 100);
        allRightRectf = new RectF(allProgressLength - 50, 0, allProgressLength + 50, 100);

        progressPaint = new Paint();
        progressPaint.setAntiAlias(true);
        progressPaint.setColor(Color.GREEN);
        progressPaint.setStyle(Paint.Style.FILL);

        progressPaintCircle = new Paint();
        progressPaintCircle.setAntiAlias(true);
        progressPaintCircle.setColor(Color.GREEN);
        progressPaintCircle.setStyle(Paint.Style.FILL);
        progressPaintCircle.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        setLayerType(View.LAYER_TYPE_SOFTWARE, null);
        progressRectf = new RectF();
        leftRectf = new RectF(0, 0, 100, 100);
        rightRectf = new RectF(progressLength - 50, 0, progressLength + 50, 100);

    }

    public DownloadProgressView(Context context) {
        super(context);
        init();
    }

    public DownloadProgressView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public DownloadProgressView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        //view的宽
        widgetWidth = MeasureSpec.getSize(widthMeasureSpec);
        //view的高
        widgetHeight = MeasureSpec.getSize(heightMeasureSpec);
        Log.e(TAG, "onMeasure");

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        allProgressRectf.left = 50;
        allProgressRectf.top = 0;
        allProgressRectf.bottom = 100;
        allProgressRectf.right = 800;

        progressRectf.left = 50;
        progressRectf.top = 0;
        progressRectf.bottom = 100;
        progressRectf.right = progressLength;

            rightRectf.left = progressLength - 100;
            rightRectf.top = 0;
            rightRectf.bottom = 100;
            rightRectf.right = progressLength + 0;

        canvas.drawRect(allProgressRectf, allProgressPaint);
        canvas.drawArc(allLeftRectf, 90, 180, true, allProgressPaint);
        canvas.drawArc(allRightRectf, 270, 180, true, allProgressPaint);
        if (progressLength > 50) {
            canvas.drawRect(progressRectf, progressPaint);
        }
//        canvas.drawArc(leftRectf, 90, 180, true, progressPaintCircle);
        canvas.drawArc(rightRectf, 270, 180, true, progressPaintCircle);


    }

    public void startDraw() {
        final boolean[] isStop = {false};
        progressLength = 0;
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (!isStop[0]) {
                    try {
                        Thread.sleep(200);
                        progressLength++;
                        postInvalidate();
                        if (progressLength > 800) {
                            isStop[0] = true;
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

            }
        }).start();
    }
}
