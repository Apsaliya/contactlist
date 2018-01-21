package com.contactlist.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.ViewModel;
import android.support.annotation.NonNull;

import com.contactlist.data.datasource.ContactRepo;

import javax.inject.Inject;

/**
 * Created by ankit on 19/01/18.
 */

public class ContactListViewModel extends AndroidViewModel {

    @Inject
    public ContactListViewModel(@NonNull ContactRepo contactRepo, @NonNull Application application) {
        super(application);
    }
}
