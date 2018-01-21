package com.contactlist.data.datasource;

import android.arch.lifecycle.LiveData;

import com.contactlist.network.ContactListService;
import com.contactlist.network.model.Contact;

import java.util.Comparator;
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

    public Flowable<List<Contact>> sortList(List<Contact> contacts, int order) {
        return Flowable.just(contacts)
                .flatMapIterable(contacts1 -> contacts1)
                .toSortedList((o1, o2) -> {
                    if (order == -1) {
                        return String.CASE_INSENSITIVE_ORDER.compare(o2.getName(), o1.getName());
                    } else {
                        return String.CASE_INSENSITIVE_ORDER.compare(o1.getName(), o2.getName());
                    }
                }).toFlowable();
    }

}
