package com.huangjinfu.mydiyviewdemo.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.huangjinfu.mydiyviewdemo.R;
import com.huangjinfu.mydiyviewdemo.views.ProgressNotifyView;

public class ProgressViewActivity extends AppCompatActivity {
    private ProgressNotifyView progressNotifyView;
    private Button btnAnimal;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_progress_view);
        progressNotifyView = findViewById(R.id.progress);
        btnAnimal = findViewById(R.id.btn_start_animal);
        btnAnimal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progressNotifyView.startAnaimal();
            }
        });
    }
}
