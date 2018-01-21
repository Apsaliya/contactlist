package com.contactlist.data.datasource;

import com.contactlist.network.ContactListService;

import javax.inject.Inject;
import javax.inject.Singleton;

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
}
