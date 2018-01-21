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
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import retrofit2.HttpException;

/**
 * Created by ankit on 19/01/18.
 */

public class ContactListViewModel extends AndroidViewModel {
    private ContactRepo contactRepo;
    private States states = new States();
    public final MutableLiveData<List<Contact>> contactListLiveData = new MutableLiveData<>();
    public final MutableLiveData<States> statesLiveData = new MutableLiveData<>();
    private CompositeDisposable compositeDisposable = new CompositeDisposable();

    @Inject
    public ContactListViewModel(@NonNull ContactRepo contactRepo, @NonNull Application application) {
        super(application);
        this.contactRepo = contactRepo;
        getContactList();
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        compositeDisposable.clear();
    }

    private void getContactList() {
        compositeDisposable.add(contactRepo.getContactList()
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
                    states.setErrorThrowable(value);
                    statesLiveData.setValue(states);
                }));
    }

    public void sortList(int order) {
        if (contactListLiveData.getValue() != null) {
            compositeDisposable.add(contactRepo.sortList(contactListLiveData.getValue(), order)
                    .subscribeOn(Schedulers.computation())
                    .observeOn(AndroidSchedulers.mainThread())
                    .doOnSubscribe(subscription -> {
                        states.setState(States.LOADING);
                        statesLiveData.setValue(states);
                    })
                    .doOnComplete(() -> {
                        states.setState(States.LOADING_FINISHED);
                        statesLiveData.setValue(states);
                    })
                    .subscribe(contactListLiveData::setValue,
                            value -> {
                                states.setState(States.ERROR);
                                statesLiveData.setValue(states);
                            }));
        } else {
            getContactList();
        }

    }
}
