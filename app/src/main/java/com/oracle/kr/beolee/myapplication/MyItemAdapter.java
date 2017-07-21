package com.oracle.kr.beolee.myapplication;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

/**
 * Created by BEOLEE on 2017-07-19.
 */

public class MyItemAdapter extends BaseAdapter {
    private static final String TAG = "MyItemAdapter";
    private Context mCtx;
    private int mRsc;
    private ArrayList<MyItem> mItems = new ArrayList<>();

    public MyItemAdapter(Context ctx, int rsc, ArrayList<MyItem> data) {
        mCtx = ctx;
        mRsc = rsc;
        mItems = data;
    }
    @Override
    public int getCount() {
        return mItems.size();
    }

    @Override
    public Object getItem(int position) {
        return mItems.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Log.d(TAG, "getView: +++");
        if(convertView == null) {
            LayoutInflater inflater = (LayoutInflater) mCtx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(mRsc, parent, false);
        }
        TextView tv_id, tv_user_id, tv_title, tv_desc;
        tv_id = (TextView) convertView.findViewById(R.id.tv_id);
        tv_id.setText(((MyItem)getItem(position)).id+"");
        tv_user_id = (TextView) convertView.findViewById(R.id.tv_user_id);
        tv_user_id.setText(((MyItem)getItem(position)).user_id +"");
        tv_title = (TextView) convertView.findViewById(R.id.tv_titie);
        tv_title.setText(((MyItem)getItem(position)).title);
        tv_desc = (TextView) convertView.findViewById(R.id.tv_desc);
        tv_desc.setText(((MyItem)getItem(position)).desc);
        Log.d(TAG, "getView: ---");
        return convertView;
    }
}
