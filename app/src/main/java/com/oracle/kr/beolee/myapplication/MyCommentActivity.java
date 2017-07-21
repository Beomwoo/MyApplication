package com.oracle.kr.beolee.myapplication;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class MyCommentActivity extends AppCompatActivity {
    private static final String TAG = "MyCommentActivity";
    ArrayList<MyComment> myComments = new ArrayList<>();
    MyCommentAdapter adapter = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "onCreate: +++");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_comment);
        MyItem myItem = (MyItem)getIntent().getSerializableExtra("selectedItem");

        TextView tv_title = (TextView) findViewById(R.id.tv_title2);
        tv_title.setText(myItem.title);
        TextView tv_desc = (TextView) findViewById(R.id.tv_desc2);
        tv_desc.setText(myItem.desc);

        MyItem previousItem = (MyItem)getIntent().getSerializableExtra("previousItem");
        TextView tv_previous = (TextView) findViewById(R.id.tv_previous);
        tv_previous.setText(previousItem!=null ? previousItem.title : "");

        tv_previous.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MyCommentActivity.this, MyCommentActivity.class);
//                intent.putExtra("selectedItem", previousItem);
//                intent.putExtra("nextItem", myItem);
                startActivity(intent);
            }
        });

        MyItem nextItem = (MyItem)getIntent().getSerializableExtra("nextItem");
        TextView tv_next = (TextView) findViewById(R.id.tv_next);
        tv_next.setText(nextItem!=null ? nextItem.title : "");
        tv_previous.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MyCommentActivity.this, MyCommentActivity.class);
//                intent.putExtra("selectedItem", nextItem);
//                intent.putExtra("previousItem", myItem);
                startActivity(intent);
            }
        });

        adapter = new MyCommentAdapter(this, R.layout.my_comment, myComments);
        //어댑터 연결
        ListView listView = (ListView) findViewById(R.id.my_comment_list);
        listView.setAdapter(adapter);

        new MyCommentAsyncTask().execute(myItem.id + "");
        Log.d(TAG, "onCreate: ---");
    }

    void setData (int position ) {
//        TextView tv_title = (TextView) findViewById(R.id.tv_title2);
//        tv_title.setText(myItem.title);
//        TextView tv_desc = (TextView) findViewById(R.id.tv_desc2);
//        tv_desc.setText(myItem.desc);
//
//        new MyCommentAsyncTask().execute(myItem.id + "");

    }
    private class MyCommentAsyncTask extends AsyncTask<String, Void, ArrayList<MyComment>> {
        private static final String TAG = "MyCommentAsyncTask";
        @Override
        protected ArrayList<MyComment> doInBackground(String... params) {
            Log.d(TAG, "doInBackground: +++");
            String post_id =  params[0];
            InputStream in;
            StringBuffer sb = new StringBuffer();
            String jsonStr;
            ArrayList<MyComment> myComments = new ArrayList<>();

            try {
                URL url = new URL("http://jsonplaceholder.typicode.com/posts/" + post_id + "/comments");
                HttpURLConnection urlConn = (HttpURLConnection) url.openConnection();
                urlConn.setDoInput(true);
                urlConn.setRequestMethod("GET");
                urlConn.setRequestProperty("Content-Type", "application/json");
//                urlConn.setRequestProperty("Content-Type", "text/html");
//                                urlConn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
                urlConn.setUseCaches(false);
                urlConn.setConnectTimeout(10000);
                urlConn.setReadTimeout(10000);
                in = urlConn.getInputStream();
                Log.d(TAG, "===2");
                BufferedReader br = new BufferedReader(new InputStreamReader(in));
                Log.d(TAG, "===3");

                String line = "";

                while ((line = br.readLine()) != null) {
                    sb.append(line);
                }
                in.close();
                Log.d(TAG, sb.toString());
            } catch (Exception e) {
                e.printStackTrace();
            }

            // JSON 파싱
            try {
                JSONArray jsonArray = new JSONArray(sb.toString());
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject jsonObj = jsonArray.getJSONObject(i);
                    myComments.add(new MyComment(jsonObj.getInt("id"),
                            jsonObj.getInt("postId"),
                            jsonObj.getString("name"),
                            jsonObj.getString("email"),
                            jsonObj.getString("body")));
                }
            } catch (JSONException e) {

            }
            Log.d(TAG, "doInBackground: ---");
            return myComments;
        }

        protected void onPostExecute(ArrayList<MyComment> s) {
            super.onPostExecute(s);
            myComments.clear();
            myComments.addAll(s);
            adapter.notifyDataSetChanged();
        }
    }
}
