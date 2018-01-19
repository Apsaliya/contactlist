package com.contactlist.di;

import javax.inject.Singleton;

import dagger.Component;
import dagger.android.AndroidInjectionModule;

/**
 * Created by ankit on 19/01/18.
 */
@Singleton
@Component(modules = {
        AppModule.class
})
public interface AppComponent {
}
