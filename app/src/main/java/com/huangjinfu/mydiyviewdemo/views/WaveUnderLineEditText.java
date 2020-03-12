package com.huangjinfu.mydiyviewdemo.views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.drawable.Drawable;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.widget.EditText;

/**
 * @Author Huangjinfu
 * @Date 2020-03-04 17:19
 * @Description:
 */
public class WaveUnderLineEditText extends android.support.v7.widget.AppCompatEditText {
    // 画笔 用来画下划线
    private Paint paint;


    private void initPaint(){
        paint = new Paint();
//        paint.setStyle(Paint.Style.STROKE);
        paint.setColor(Color.RED);
        paint.setFlags(Paint.UNDERLINE_TEXT_FLAG);
        // 开启抗锯齿 较耗内存
        paint.setAntiAlias(true);
    }

    public WaveUnderLineEditText(Context context) {
        super(context);
        initPaint();
    }

    public WaveUnderLineEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        initPaint();
    }

    public WaveUnderLineEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initPaint();
    }



    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        // 得到总行数
        int lineCount = getLineCount();
        // 得到每行的高度
        int lineHeight = getLineHeight();
        // 获取EditText中文字的物理长度
        TextPaint mTextPaint = getPaint();

        float textWidth = mTextPaint.measureText(getText().toString());
//
////        // 根据行数循环画线
//        for (int i = 0; i < lineCount; i++) {
//            int lineY = (i + 1) * lineHeight;
//            canvas.drawLine(0, lineY, textWidth, lineY, paint);
//        }
        Path path = new Path();
        path.cubicTo(0,0,,textWidth,0,);

    }
}
