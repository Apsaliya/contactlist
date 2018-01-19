package com.contactlist;

import android.app.Application;

import com.contactlist.di.DaggerAppComponent;


/**
 * Created by ankit on 19/01/18.
 */

public class ContactList extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        DaggerAppComponent.builder().application(this)
                .build().inject(this);
    }
}
