package com.contactlist.view.ui;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by ankit on 21/01/18.
 */

public abstract class BaseActivity extends AppCompatActivity {
    protected Activity activity;
    public abstract void initDagger();
    public abstract void initButterKnife();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activity = this;
        initDagger();
        initButterKnife();
    }
}
