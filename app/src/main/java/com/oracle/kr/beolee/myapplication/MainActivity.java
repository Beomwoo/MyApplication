package com.oracle.kr.beolee.myapplication;

import android.content.Intent;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "onCreate: +++");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final TextView textView = (TextView) findViewById(R.id.textView);
        final EditText editText = (EditText) findViewById(R.id.editText);
        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "button 1 clicked.");
                textView.setText("button 1 clicked");
                if(Build.VERSION.SDK_INT > 23) {
                    textView.setBackgroundColor(MainActivity.this.getColor(R.color.my_yellow));
                }
                Intent intent = new Intent(MainActivity.this, ShowNumberActivity.class);
                startActivity(intent);
            }
        });
        findViewById(R.id.button2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "button 2 clicked.");
                textView.setText("button 2 clicked");
                if(Build.VERSION.SDK_INT > 23) {
                    textView.setBackgroundColor(MainActivity.this.getColor(R.color.my_white));
                }
                /*String temp = editText.getText().toString();
                Toast.makeText(MainActivity.this, temp + " 을 입력햇습니다.", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(MainActivity.this, Main2Activity.class);
                intent.putExtra("msg", temp);
                startActivity(intent);*/

                Intent intent = new Intent(MainActivity.this, ShowNumber2Activity.class);
                startActivity(intent);
            }
        });
        Log.d(TAG, "onCreate: ---");

    }
}
