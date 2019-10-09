package com.huangjinfu.mydiyviewdemo.views;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.CornerPathEffect;
import android.graphics.DashPathEffect;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.huangjinfu.mydiyviewdemo.R;

/**
 * @Author Huangjinfu
 * @Date 2019-10-09 14:10
 * @Description: 自定义虚线矩形框View
 */
public class SquareView extends View  {
    public static final String TAG="SquareView";
    //正方形画笔
    private Paint paint;

    private RectF rectF;

    private Bitmap bitmap;

    //控件宽度
    private int widgetWidth;

    //控件高度
    private int widgetHeight;
    //控件原有高度
    private int viewHeight;
    //控件原有宽度
    private int viewWidth;

    private boolean isNeedCircle;

    public boolean isNeedCircle() {
        return isNeedCircle;
    }

    public void setNeedCircle(boolean needCircle) {
        isNeedCircle = needCircle;
    }

    private void initAll(Context context){
        bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.ic_launcher);

        initPaint();
        initRectF();
    }
    private void initPaint(){
        paint = new Paint();
        paint.setAntiAlias(true);
        paint.setColor(Color.RED);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(5);
        paint.setPathEffect(new CornerPathEffect(10f));
        paint.setPathEffect(new DashPathEffect(new float[]{25f,15f},0));

    }
    private void initRectF(){
         viewWidth = bitmap.getWidth();
         viewHeight = bitmap.getHeight();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        //view的宽
         widgetWidth = MeasureSpec.getSize(widthMeasureSpec);
        //view的高
        widgetHeight = MeasureSpec.getSize(heightMeasureSpec);
        Log.e(TAG,"onMeasure");

    }

    public SquareView(Context context) {
        super(context);
        initAll(context);
    }

    public SquareView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initAll(context);
    }

    public SquareView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initAll(context);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Log.e(TAG,"onDraw");
        int newHeight = widgetHeight-10;
        int newWidth = newHeight*viewWidth /viewHeight;
        rectF = new RectF(10,10,0+newWidth,0+newHeight);

        Bitmap finalBitmap = imageScale(bitmap,newWidth,newHeight);
        if (isNeedCircle){
            canvas.drawRect(rectF,paint);
        }
        canvas.drawBitmap(finalBitmap,0,0,null);
    }
    /**
     * 调整图片大小
     *
     * @param bitmap
     *            源
     * @param dst_w
     *            输出宽度
     * @param dst_h
     *            输出高度
     * @return
     */
    public static Bitmap imageScale(Bitmap bitmap, int dst_w, int dst_h) {
        int src_w = bitmap.getWidth();
        int src_h = bitmap.getHeight();
        float scale_w = ((float) dst_w) / src_w;
        float scale_h = ((float) dst_h) / src_h;
        Matrix matrix = new Matrix();
        matrix.postScale(scale_w, scale_h);
        Bitmap dstbmp = Bitmap.createBitmap(bitmap, 0, 0, src_w, src_h, matrix,
                true);
        return dstbmp;
    }

    public void refreshView(boolean isNeedCirCle){
        this.isNeedCircle = isNeedCirCle;
        postInvalidate();
    }
}
