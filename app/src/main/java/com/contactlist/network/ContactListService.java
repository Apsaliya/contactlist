package com.contactlist.network;

import com.contactlist.network.model.Contact;

import java.util.List;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by ankit on 19/01/18.
 */

public interface ContactListService {
    @GET("/users")
    Single<List<Contact>> getContactList();
}
