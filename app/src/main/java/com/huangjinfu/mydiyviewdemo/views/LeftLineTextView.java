package com.huangjinfu.mydiyviewdemo.views;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.huangjinfu.mydiyviewdemo.R;
import com.huangjinfu.mydiyviewdemo.utils.ScreenUtils;


/**
 * @Author Huangjinfu
 * @Date 2019-12-09 19:15
 * @Description:
 */
public class LeftLineTextView extends FrameLayout {
    private Context mContext;

    private int paddingLeft;
    private int lineHeight;
    private int lineWidth;
    private float radius;//圆角半径
    private static final int DEFAULT_PADDING_LEFT = 9;
    private static final int DEFAULT_LINE_HEIGHT = 12;
    private static final int DEFAULT_LINE_WIDGET = 3;
    private static final int DEFAULT_RADIUS = 2;
    private String textStr;

    private TextView tvContent;
    private View viewLine;
    public LeftLineTextView(Context context) {
        super(context);

    }

    public LeftLineTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public LeftLineTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        mContext = context;
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.LeftLineTextView);
        if (typedArray == null) {
            return;
        }
        paddingLeft = (int) typedArray.getDimension(R.styleable.LeftLineTextView_paddingLeft, DEFAULT_PADDING_LEFT);
        lineHeight = (int) typedArray.getDimension(R.styleable.LeftLineTextView_lineHeight, DEFAULT_LINE_HEIGHT);
        lineWidth = (int) typedArray.getDimension(R.styleable.LeftLineTextView_lineWidth, DEFAULT_LINE_WIDGET);
        radius = typedArray.getDimension(R.styleable.LeftLineTextView_radius, DEFAULT_RADIUS);
        textStr = typedArray.getString(R.styleable.LeftLineTextView_text);
        View view = LayoutInflater.from(mContext).inflate(R.layout.layout_left_line,this);
        viewLine = view.findViewById(R.id.view_line);
        tvContent = view.findViewById(R.id.tv_content);

        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        layoutParams.rightMargin = ScreenUtils.dip2px(context,paddingLeft);
        layoutParams.height = lineHeight;
        layoutParams.width = lineWidth;
        viewLine.setLayoutParams(layoutParams);
        tvContent.setText(textStr);

        typedArray.recycle();
    }

}
