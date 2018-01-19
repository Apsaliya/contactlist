package com.contactlist.di;

import android.app.Application;

import com.contactlist.ContactList;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;

/**
 * Created by ankit on 19/01/18.
 */
@Singleton
@Component(modules = {
        AppModule.class
})
public interface AppComponent {
    @Component.Builder
    interface Builder {
        @BindsInstance
        Builder application(Application application);
        AppComponent build();
    }
    void inject(ContactList contactList);
}
