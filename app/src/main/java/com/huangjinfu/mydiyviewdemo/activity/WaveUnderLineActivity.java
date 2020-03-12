package com.huangjinfu.mydiyviewdemo.activity;

import android.graphics.Paint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.huangjinfu.mydiyviewdemo.R;
import com.huangjinfu.mydiyviewdemo.views.WaveUnderLineEditText;

public class WaveUnderLineActivity extends AppCompatActivity {
    private WaveUnderLineEditText editText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wave_under_line);
        editText = findViewById(R.id.edittext);
        editText.getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG);
    }
}
