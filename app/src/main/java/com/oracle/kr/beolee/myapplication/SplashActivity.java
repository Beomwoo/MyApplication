package com.oracle.kr.beolee.myapplication;

import android.app.*;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

public class SplashActivity extends AppCompatActivity {
    private static final String TAG = "SplashActivity";
    private static final int LOGIN_REQ_CODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "onCreate: +++");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        new Handler().postDelayed(
                new Runnable() {
                    @Override
                    public void run() {
                        //startActivity(new Intent(SplashActivity.this, MainActivity.class));
//                        startActivity(new Intent(SplashActivity.this, ListActivity.class));
//                        startActivity(new Intent(SplashActivity.this, MyItemActivity.class));
//                        startActivity(new Intent("com.oracle.kr.beolee.login.MYLOGIN"));
//                        finish();
                        startActivityForResult(new Intent("com.oracle.kr.beolee.login.MYLOGIN"), LOGIN_REQ_CODE);

                    }
                }, 3000
        );
        Log.d(TAG, "onCreate: ---");
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        Log.d(TAG, "onActivityResult: +++");
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == LOGIN_REQ_CODE) {
            Log.d(TAG, "onActivityResult: " + data.getStringExtra("return"));
            Toast.makeText(SplashActivity.this, data.toString(), Toast.LENGTH_SHORT);
            if(resultCode == 1) {
                startActivity(new Intent(SplashActivity.this, MyItemActivity.class));
            }
        }
        Log.d(TAG, "onActivityResult: ---");
    }
}
