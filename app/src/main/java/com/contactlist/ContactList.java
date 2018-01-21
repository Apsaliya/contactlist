package com.contactlist;

import android.app.Application;

import com.contactlist.di.AppComponent;
import com.contactlist.di.DaggerAppComponent;


/**
 * Created by ankit on 19/01/18.
 */

public class ContactList extends Application {

    private AppComponent myComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        myComponent = DaggerAppComponent.builder().application(this)
                .build();
        myComponent.inject(this);
    }

    public AppComponent getMyComponent() {
        return myComponent;
    }
}
