package com.contactlist.di;

import android.arch.lifecycle.ViewModelProvider;
import android.util.Base64;

import com.contactlist.network.ContactListService;
import com.contactlist.network.NetworkConstants;
import com.contactlist.viewmodel.ContactListViewModelFactory;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by ankit on 19/01/18.
 */

@Module(subcomponents = ViewmodelSubComponent.class)
public class AppModule {
    @Singleton
    @Provides
    ContactListService provideContactListService() {

        OkHttpClient.Builder okHttpBuilder = new OkHttpClient.Builder();

        okHttpBuilder.addInterceptor(chain -> {
            Request original = chain.request();

            Request request = original.newBuilder()
                    .header("Accept", "application/json")
                    .build();

            return chain.proceed(request);
        });
        OkHttpClient okHttpClient = okHttpBuilder.build();


        return new Retrofit.Builder()
                .baseUrl(NetworkConstants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(okHttpClient)
                .build()
                .create(ContactListService.class);
    }

    @Singleton
    @Provides
    ViewModelProvider.Factory provideViewModelFactory(
            ViewmodelSubComponent.Builder viewModelSubComponent) {

        return new ContactListViewModelFactory(viewModelSubComponent.build());
    }
}
