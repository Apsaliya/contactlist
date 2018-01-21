package com.contactlist.data.datasource;

import android.arch.lifecycle.LiveData;

import com.contactlist.network.ContactListService;
import com.contactlist.network.model.Contact;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Flowable;

/**
 * Created by ankit on 19/01/18.
 */

@Singleton
public class ContactRepo {
    private ContactListService contactListService;

    @Inject
    public ContactRepo(ContactListService contactListService) {
        this.contactListService = contactListService;
    }

    public Flowable<List<Contact>> getContactList() {
        return contactListService.getContactList().toFlowable();
    }

}
