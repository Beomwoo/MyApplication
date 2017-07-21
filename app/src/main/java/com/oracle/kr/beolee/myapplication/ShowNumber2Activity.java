package com.oracle.kr.beolee.myapplication;

import android.content.Intent;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class ShowNumber2Activity extends AppCompatActivity {
    private static final String TAG = "ShowNumber2Activity";
    static private int pageCount = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_number2);
        final TextView textView4 = (TextView) findViewById(R.id.textView4);
        textView4.setText(++pageCount + "");
        findViewById(R.id.button4).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "초기화 clicked.");
                pageCount = 0;
                textView4.setText(pageCount +"");
            }
        });
        findViewById(R.id.button5).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "종료 clicked.");
                moveTaskToBack(true);
                finish();
                android.os.Process.killProcess(android.os.Process.myPid());
            }
        });
    }

}
