package com.contactlist.data.datasource.remotedatasource;

import com.contactlist.data.datasource.DataSource;
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
public class RemoteDataSource implements DataSource {
    private ContactListService contactListService;

    @Inject
    public RemoteDataSource(ContactListService contactListService) {
       this.contactListService = contactListService;
    }
    @Override
    public Flowable<List<Contact>> getContactList() {
        return contactListService.getContactList().toFlowable();
    }
}
