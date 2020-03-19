package com.huangjinfu.mydiyviewdemo.views;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.animation.LinearOutSlowInInterpolator;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.BounceInterpolator;
import android.view.animation.CycleInterpolator;
import android.view.animation.LinearInterpolator;
import android.view.animation.OvershootInterpolator;
import android.widget.FrameLayout;

import com.huangjinfu.mydiyviewdemo.R;

/**
 * @Author Huangjinfu
 * @Date 2020-03-19 16:43
 * @Description:
 */
public class ProgressNotifyView extends FrameLayout {
    private ProgressView progressView;

    public ProgressNotifyView(@NonNull Context context) {
        super(context);
        initView(context);
    }

    public ProgressNotifyView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public ProgressNotifyView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    private void initView(Context context) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_progress, this);
        progressView = view.findViewById(R.id.progress_view);

    }

    public void startAnaimal() {
        ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(progressView, "translationX", -500, 0);
        objectAnimator.setDuration(5000);
        objectAnimator.setInterpolator(new LinearOutSlowInInterpolator());
        objectAnimator.start();
    }

}
