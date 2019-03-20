package com.huangjinfu.mydiyviewdemo.views;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.huangjinfu.mydiyviewdemo.R;

/**
 * Created by huangjinfu on 2019/3/20.
 */

public class TableView extends View{
    private int stockWidth = 1;
    private Paint paint;
    public TableView(Context context) {
        super(context);
        init();
    }

    public TableView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public TableView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.TableView,defStyleAttr,0);
        stockWidth = typedArray.getInteger(R.styleable.TableView_mb_stockWidth,1);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }

    private void init(){
        paint = new Paint();
        paint.setAntiAlias(true);
    }
}
