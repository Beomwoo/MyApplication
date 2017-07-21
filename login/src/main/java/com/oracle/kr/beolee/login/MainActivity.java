package com.oracle.kr.beolee.login;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    EditText id, pwd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "onCreate: +++");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent();
                intent.putExtra( "return" , "Hello World!");
                setResult(1, intent);
                finish();
            }
        });
        id = (EditText) findViewById(R.id.etxt_login_id);
        pwd = (EditText) findViewById(R.id.etxt_pwd);

        findViewById(R.id.btn_login).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(id.getText().toString().equals("") || pwd.getText().toString().equals(""))
                    Toast.makeText(MainActivity.this, "칸을 모두채워주세요", Toast.LENGTH_SHORT).show();
                new LoginAsyncTask().execute(id.getText().toString(), pwd.getText().toString());
            }
        });
        Log.d(TAG, "onCreate: ---");



    }


    private class LoginAsyncTask extends AsyncTask<String, Void, String> {
        private static final String TAG = "LoginAsyncTask";
        ProgressDialog pd;
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pd = new ProgressDialog(MainActivity.this);
            pd.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            pd.setTitle("로그인 중");
            pd.show();

        }

        @Override
        protected String doInBackground(String... params) {
            Log.d(TAG, "doInBackground: +++");
            InputStream in;
            StringBuffer sb = new StringBuffer();
            String jsonStr;

            try {
                URL url = new URL("http://oracletest.run.goorm.io/login/");
                HttpURLConnection urlConn = (HttpURLConnection) url.openConnection();
                urlConn.setDoInput(true);
                urlConn.setDoOutput(true);
                urlConn.setRequestMethod("POST");
                urlConn.setUseCaches(false);
                urlConn.setConnectTimeout(10000);
                urlConn.setReadTimeout(10000);

                jsonStr = "data=";
                JSONObject jsonObj = new JSONObject();
                jsonObj.put ("user_id", params[0]);
                jsonObj.put ("password", params[1]);
                jsonStr += jsonObj.toString();
                DataOutputStream out = new DataOutputStream(urlConn.getOutputStream());
                out.writeBytes(jsonStr);
                out.flush();
                out.close();

                in = urlConn.getInputStream();
                BufferedReader br = new BufferedReader( new InputStreamReader(in));

                String line = "";

                while ((line=br.readLine())!=null) {
                    sb.append(line);
                }
                in.close();
                Log.d(TAG, sb.toString());
            } catch(Exception e) {
                e.printStackTrace();
            }

            return  sb.toString();
        }

        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            pd.dismiss();
        }
    }


}
