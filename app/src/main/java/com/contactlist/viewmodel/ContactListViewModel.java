package com.contactlist.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;
import android.util.Log;

import com.contactlist.data.datasource.ContactRepo;
import com.contactlist.network.model.Contact;
import com.contactlist.view.ui.States;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by ankit on 19/01/18.
 */

public class ContactListViewModel extends AndroidViewModel {
    private ContactRepo contactRepo;
    private States states = new States();
    public final MutableLiveData<List<Contact>> contactListLiveData = new MutableLiveData<>();
    public final MutableLiveData<States> statesLiveData = new MutableLiveData<>();

    @Inject
    public ContactListViewModel(@NonNull ContactRepo contactRepo, @NonNull Application application) {
        super(application);
        this.contactRepo = contactRepo;
        getContactList();
    }

    private void getContactList() {
        Log.d("getContactList", "getContactList");
        contactRepo.getContactList()
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .doOnSubscribe(subscription -> {
                        states.setState(States.LOADING);
                        statesLiveData.setValue(states);
                    })
                    .doOnComplete(() -> {
                        states.setState(States.LOADING_FINISHED);
                        statesLiveData.setValue(states);
                    })
                    .subscribe(contactListLiveData::setValue, value -> {
                        states.setState(States.ERROR);
                        statesLiveData.setValue(states);
                    });
    }
}
