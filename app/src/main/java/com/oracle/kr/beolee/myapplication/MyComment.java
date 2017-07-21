package com.oracle.kr.beolee.myapplication;

/**
 * Created by BEOLEE on 2017-07-19.
 */

public class MyComment {
    int id, post_id;
    String name, email, body;
    public MyComment(int id, int post_id, String name, String email, String body) {
        this.id = id;
        this.post_id = post_id;
        this.name = name;
        this.email = email;
        this.body = body;
    }
}
