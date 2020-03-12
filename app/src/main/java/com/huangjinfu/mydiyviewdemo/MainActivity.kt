package com.huangjinfu.mydiyviewdemo

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.huangjinfu.mydiyviewdemo.activity.WaveUnderLineActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btn_edit.setOnClickListener {
            var intent = Intent(this,WaveUnderLineActivity::class.java);
            startActivity(intent)
        }
    }
}
