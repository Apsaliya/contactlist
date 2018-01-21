package com.contactlist;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by ankit on 21/01/18.
 */

public abstract class BaseActivity extends AppCompatActivity {
    public abstract void initDagger();
    public abstract void initButterKnife();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initDagger();
        initButterKnife();
    }
}
