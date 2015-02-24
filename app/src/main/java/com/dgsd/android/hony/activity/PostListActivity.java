package com.dgsd.android.hony.activity;

import android.os.Bundle;

import com.dgsd.android.hony.R;

public class PostListActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle(R.string.hony_title);
        setContentView(R.layout.act_post_list);
    }
}
