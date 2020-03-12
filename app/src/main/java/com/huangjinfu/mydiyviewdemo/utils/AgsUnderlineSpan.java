package com.huangjinfu.mydiyviewdemo.utils;

import android.annotation.SuppressLint;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Parcel;
import android.text.TextPaint;
import android.text.style.LineBackgroundSpan;
import android.text.style.UnderlineSpan;
import android.text.style.UpdateAppearance;

@SuppressLint("ParcelCreator")
public class AgsUnderlineSpan extends UnderlineSpan implements UpdateAppearance, LineBackgroundSpan {

    public AgsUnderlineSpan() {

    }
    public AgsUnderlineSpan(Parcel src) {

    }
    public int getSpanTypeId() {
        return 6;
    }
    public int describeContents() {
        return 0;
    }
    public void writeToParcel(Parcel dest, int flags) {

    }

    @Override
    public void updateDrawState(TextPaint ds) {
        ds.setUnderlineText(true);
    }

    @Override
    public void drawBackground(Canvas c, Paint p, int left, int right, int top, int baseline, int bottom, CharSequence text, int start, int end, int lnum) {

    }
}
