package com.oracle.kr.beolee.myapplication;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by BEOLEE on 2017-07-19.
 */

public class MyCommentAdapter extends BaseAdapter {
    private static final String TAG = "MyCommentAdapter";
    private Context mCtx;
    private int mRsc;
    private ArrayList<MyComment> mComments = new ArrayList<>();
    public MyCommentAdapter(Context ctx, int rsc, ArrayList<MyComment> data) {
        mCtx = ctx;
        mRsc = rsc;
        mComments = data;
    }
    @Override
    public int getCount() {
        return mComments.size();
    }

    @Override
    public Object getItem(int position) {
        return mComments.get(position);
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
        TextView tv_id, tv_post_id,  tv_desc, tv_name;
        tv_id = (TextView) convertView.findViewById(R.id.tv_my_comment_id);
        tv_id.setText(((MyComment)getItem(position)).id+"");
        tv_post_id = (TextView) convertView.findViewById(R.id.tv_my_comment_post_id);
        tv_post_id.setText(((MyComment)getItem(position)).post_id +"");
        tv_desc = (TextView) convertView.findViewById(R.id.tv_mycomment_body);
        tv_desc.setText(((MyComment)getItem(position)).body);
        tv_name = (TextView) convertView.findViewById(R.id.tv_my_comment_name);
        tv_name.setText(((MyComment)getItem(position)).name + "(" + ((MyComment)getItem(position)).email + ")");
        Log.d(TAG, "getView: ---");
        return convertView;
    }
}
