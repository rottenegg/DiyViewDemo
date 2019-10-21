package com.huangjinfu.mydiyviewdemo.views;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
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

import com.huangjinfu.mydiyviewdemo.R;
import com.huangjinfu.mydiyviewdemo.utils.ScreenUtils;

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
    private int progressLength = 0;

    private Paint progressPaint, progressPaintCircle, progressPaintCircleLeft;
    private RectF progressRectf;
    private RectF leftRectf;
    private RectF rightRectf;

    private Paint textPaint;
    private Paint bitmapPaint;

    private int progressHeight = 8;//进度条高度为8dp
    private int progressWidth = 230;//进度条宽度为230dp
    private int progressMargin = 37;//左右边距默认为37dp
    final boolean[] isStop = {false};
    private Bitmap bitmap = BitmapFactory.decodeResource(getResources(),R.drawable.img_pop_air_bubbles);
    private void init() {
        allProgressPaint = new Paint();
        allProgressPaint.setAntiAlias(true);
        allProgressPaint.setColor(getResources().getColor(R.color.c_8d8d8d));
        allProgressPaint.setStyle(Paint.Style.FILL);
        allProgressRectf = new RectF();
        allLeftRectf = new RectF();
        allRightRectf = new RectF();

        progressPaint = new Paint();
        progressPaint.setAntiAlias(true);
        progressPaint.setColor(getResources().getColor(R.color.c_ffe785));
        progressPaint.setStyle(Paint.Style.FILL);

        progressPaintCircle = new Paint();
        progressPaintCircle.setAntiAlias(true);
        progressPaintCircle.setColor(getResources().getColor(R.color.c_ffe785));
        progressPaintCircle.setStyle(Paint.Style.FILL);
        progressPaintCircle.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        setLayerType(View.LAYER_TYPE_SOFTWARE, null);
        progressRectf = new RectF();
        leftRectf = new RectF();
        rightRectf = new RectF();

        progressPaintCircleLeft = new Paint();
        progressPaintCircleLeft.setAntiAlias(true);
        progressPaintCircleLeft.setColor(getResources().getColor(R.color.c_ffe785));
        progressPaintCircleLeft.setStyle(Paint.Style.FILL);

        textPaint = new Paint();
        textPaint.setAntiAlias(true);
        textPaint.setColor(getResources().getColor(R.color.white));
        textPaint.setStyle(Paint.Style.FILL);
        textPaint.setTextSize(ScreenUtils.sp2px(getContext(),10));

        bitmapPaint = new Paint();
        bitmapPaint.setAntiAlias(true);
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
        progressWidth = widgetWidth - 2 * ScreenUtils.dip2px(getContext(), 37);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        setProgress();
        //画整体进度条
        canvas.drawRect(allProgressRectf, allProgressPaint);
        canvas.drawArc(allLeftRectf, 90, 180, true, allProgressPaint);
        canvas.drawArc(allRightRectf, 270, 180, true, allProgressPaint);
        //画百分比进度条
        canvas.drawRect(progressRectf, progressPaint);
        canvas.drawArc(leftRectf, 90, 180, true, progressPaintCircleLeft);
        canvas.drawArc(rightRectf, 270, 180, true, progressPaintCircle);

        //显示文字
        int progress = (int) ( ((float)progressLength /(float) (widgetWidth-2*ScreenUtils.dip2px(getContext(),progressMargin)-ScreenUtils.dip2px(getContext(),progressHeight/2))) * 100);
        canvas.drawBitmap(bitmap,progressLength+ScreenUtils.dip2px(getContext(),progressMargin)+ScreenUtils.dip2px(getContext(),progressHeight/2)-bitmap.getWidth()/2,widgetHeight/2-ScreenUtils.dip2px(getContext(),progressHeight/2)-bitmap.getHeight()-ScreenUtils.dip2px(getContext(),5),bitmapPaint);
        canvas.drawText(progress+"%",progressLength+ScreenUtils.dip2px(getContext(),progressMargin)+ScreenUtils.dip2px(getContext(),progressHeight/2)-bitmap.getWidth()/4,widgetHeight/2-ScreenUtils.dip2px(getContext(),progressHeight/2)-bitmap.getHeight()/3-ScreenUtils.dip2px(getContext(),6),textPaint);

    }

    private void setProgress(){
        allProgressRectf.left = ScreenUtils.dip2px(getContext(), progressMargin) + ScreenUtils.dip2px(getContext(), progressHeight / 2)-1;
        allProgressRectf.top = widgetHeight / 2 - ScreenUtils.dip2px(getContext(), progressHeight / 2);
        allProgressRectf.bottom = widgetHeight / 2 + ScreenUtils.dip2px(getContext(), progressHeight / 2);
        allProgressRectf.right = widgetWidth - ScreenUtils.dip2px(getContext(), progressMargin) - ScreenUtils.dip2px(getContext(), progressHeight / 2)+1;

        allLeftRectf.left = ScreenUtils.dip2px(getContext(), progressMargin);
        allLeftRectf.top = widgetHeight / 2 - ScreenUtils.dip2px(getContext(), progressHeight / 2);
        allLeftRectf.bottom = widgetHeight / 2 + ScreenUtils.dip2px(getContext(), progressHeight / 2);
        allLeftRectf.right = ScreenUtils.dip2px(getContext(), progressMargin) + ScreenUtils.dip2px(getContext(), progressHeight);

        allRightRectf.left = widgetWidth - ScreenUtils.dip2px(getContext(), progressMargin) - ScreenUtils.dip2px(getContext(), progressHeight);
        allRightRectf.top = widgetHeight / 2 - ScreenUtils.dip2px(getContext(), progressHeight / 2);
        allRightRectf.bottom = widgetHeight / 2 + ScreenUtils.dip2px(getContext(), progressHeight / 2);
        allRightRectf.right = widgetWidth - ScreenUtils.dip2px(getContext(), progressMargin);

        leftRectf.left = ScreenUtils.dip2px(getContext(), progressMargin);
        leftRectf.top = widgetHeight / 2 - ScreenUtils.dip2px(getContext(), progressHeight / 2);
        leftRectf.bottom = widgetHeight / 2 + ScreenUtils.dip2px(getContext(), progressHeight / 2);
        leftRectf.right = ScreenUtils.dip2px(getContext(), progressMargin) + ScreenUtils.dip2px(getContext(), progressHeight);

        progressRectf.left = ScreenUtils.dip2px(getContext(), progressMargin) + ScreenUtils.dip2px(getContext(), progressHeight / 2);
        progressRectf.top = widgetHeight / 2 - ScreenUtils.dip2px(getContext(), progressHeight / 2);
        progressRectf.bottom = widgetHeight / 2 + ScreenUtils.dip2px(getContext(), progressHeight / 2);
        progressRectf.right = progressLength + ScreenUtils.dip2px(getContext(), progressMargin) + progressHeight / 2;

        rightRectf.left = ScreenUtils.dip2px(getContext(),progressMargin) +progressLength-ScreenUtils.dip2px(getContext(),progressHeight/2);
        rightRectf.top = widgetHeight / 2 - ScreenUtils.dip2px(getContext(), progressHeight / 2);
        rightRectf.bottom =  widgetHeight / 2 + ScreenUtils.dip2px(getContext(), progressHeight / 2);
        rightRectf.right = progressLength +ScreenUtils.dip2px(getContext(), progressMargin)+ ScreenUtils.dip2px(getContext(), progressHeight/2);

    }

    public void startDraw() {
        isStop[0] = false;
        progressLength = 0;
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (!isStop[0]) {
                    try {
                        Thread.sleep(10);
                        progressLength++;
                        postInvalidate();
                        if (progressLength > widgetWidth-2*ScreenUtils.dip2px(getContext(),progressMargin)-ScreenUtils.dip2px(getContext(),progressHeight/2)) {
                            isStop[0] = true;
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

            }
        }).start();
    }
    public void endDraw() {
        isStop[0] = true;
        progressLength = 0;
        postInvalidate();


    }
}
