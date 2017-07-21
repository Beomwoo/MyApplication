package com.oracle.kr.beolee.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class ListActivity extends AppCompatActivity {
    private static final String TAG = "ListActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        //데이타 원본 준비
        String[] items = {"item111", "item222","item333", "item444","item555", "item666","item777", "item888","item999"};

        //어댑터 생성
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_expandable_list_item_1, items);

        //어댑터 연결
        ListView list = (ListView) findViewById(R.id.listView);
        list.setAdapter(adapter);


    }
}
