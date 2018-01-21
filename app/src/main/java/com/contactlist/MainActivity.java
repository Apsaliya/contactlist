package com.contactlist;

import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.contactlist.network.model.Contact;
import com.contactlist.view.adapter.ContactListAdapter;
import com.contactlist.view.callback.ContactClickCallback;
import com.contactlist.view.ui.States;
import com.contactlist.viewmodel.ContactListViewModel;
import com.github.ybq.android.spinkit.SpinKitView;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity {

    @Inject
    ViewModelProvider.Factory viewModelFactory;

    @BindView(R.id.contact_list)
    RecyclerView contactList;

    @BindView(R.id.spin_kit)
    SpinKitView spinKitView;

    private ContactListAdapter contactListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_main);
        super.onCreate(savedInstanceState);
        setAdapter();
    }

    private void setAdapter() {
        Log.d("setAdapter", "setAdapter");
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this.getApplicationContext());
        contactList.setLayoutManager(linearLayoutManager);
        contactListAdapter = new ContactListAdapter(contactClickCallback);
        contactList.setAdapter(contactListAdapter);

        final ContactListViewModel viewModel = ViewModelProviders.of(this,
                viewModelFactory).get(ContactListViewModel.class);

        observeViewModel(viewModel);
        observeStates(viewModel);

    }

    private void observeStates(ContactListViewModel viewModel) {
        viewModel.statesLiveData.observe(this, state -> {
            if (States.ERROR == state.getState()) {

            } else if (States.LOADING == state.getState()) {
                spinKitView.setVisibility(View.VISIBLE);
                contactList.setVisibility(View.VISIBLE);
            } else if (States.LOADING_FINISHED == state.getState()) {
                spinKitView.setVisibility(View.GONE);
                contactList.setVisibility(View.VISIBLE);
            }
        });
    }

    private void observeViewModel(ContactListViewModel viewModel) {
        viewModel.contactListLiveData.observe(this, contacts -> {
            Log.d("change received", "change");
            contactListAdapter.updateData(contacts);
        });
    }

    private ContactClickCallback contactClickCallback = contact -> {
        if (getLifecycle().getCurrentState().isAtLeast(Lifecycle.State.STARTED)) {
            ContactDetailsActivity.start(activity, contact);
        }
    };

    @Override
    public void initDagger() {
        ((ContactList)getApplicationContext()).getMyComponent().inject(this);
    }

    @Override
    public void initButterKnife() {
        ButterKnife.bind(this);
    }
}
