package com.contactlist.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;

import com.contactlist.network.model.Contact;
import com.contactlist.utils.Cache;

import java.util.List;

import javax.inject.Inject;

/**
 * Created by ankit on 21/01/18.
 */

public class ContactDetailsViewModel extends AndroidViewModel {
    public final MutableLiveData<Contact> contactLiveData = new MutableLiveData<>();
    public final MutableLiveData<Throwable> errorLiveData = new MutableLiveData<>();

    @Inject
    public ContactDetailsViewModel(@NonNull Application application) {
        super(application);
    }

    public void setCacheKey(String key) {
        try {
            Cache cache = Cache.getInstance();
            Object object = cache.get(key);
            Contact contact = (Contact) object;
            contactLiveData.setValue(contact);
        } catch (Exception e) {
            errorLiveData.setValue(e);
        }
    }
}
