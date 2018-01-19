package com.contactlist.di;

import com.contactlist.network.ContactListService;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by ankit on 19/01/18.
 */

@Module
public class AppModule {
    @Singleton
    @Provides
    ContactListService provideContactListService() {
        return new Retrofit.Builder()
                .baseUrl("")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(ContactListService.class);
    }
}
