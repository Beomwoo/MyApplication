package com.oracle.kr.beolee.myapplication;

import android.content.Intent;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class ShowNumberActivity extends AppCompatActivity {
    private static final String TAG = "ShowNumberActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_number);
        final TextView textView3 = (TextView) findViewById(R.id.textView3);
        textView3.setText("0");
        findViewById(R.id.button3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "show number 1");
                int tempInt = Integer.parseInt((String) textView3.getText());
                textView3.setText(tempInt + 1 + "");
            }
        });
    }
}
