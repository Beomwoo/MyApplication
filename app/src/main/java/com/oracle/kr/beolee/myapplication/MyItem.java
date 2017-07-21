package com.oracle.kr.beolee.myapplication;

import java.io.Serializable;

/**
 * Created by BEOLEE on 2017-07-19.
 */

public class MyItem implements Serializable{
        int id,user_id;
    String title, desc;
    public MyItem (int id, int user_id, String title, String desc) {
        this.id = id;
        this.user_id = user_id;
        this.title = title;
        this.desc = desc;

    }
}
