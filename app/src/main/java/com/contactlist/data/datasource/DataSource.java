package com.contactlist.data.datasource;

import com.contactlist.network.model.Contact;

import java.util.List;

import io.reactivex.Flowable;

/**
 * Created by ankit on 19/01/18.
 */

public interface DataSource {
    Flowable<List<Contact>> getContactList();
}
