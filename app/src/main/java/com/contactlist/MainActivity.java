package com.contactlist;

import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.contactlist.view.adapter.ContactListAdapter;
import com.contactlist.view.callback.ContactClickCallback;
import com.contactlist.view.ui.States;
import com.contactlist.viewmodel.ContactListViewModel;
import com.github.ybq.android.spinkit.SpinKitView;

import java.io.IOException;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.HttpException;

public class MainActivity extends BaseActivity {

    @Inject
    ViewModelProvider.Factory viewModelFactory;

    @BindView(R.id.contact_list)
    RecyclerView contactList;

    @BindView(R.id.spin_kit)
    SpinKitView spinKitView;

    private ContactListAdapter contactListAdapter;
    private ContactListViewModel contactListViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_main);
        super.onCreate(savedInstanceState);
        setAdapter();
    }

    private void setAdapter() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this.getApplicationContext());
        contactList.setLayoutManager(linearLayoutManager);
        contactListAdapter = new ContactListAdapter(contactClickCallback);
        contactList.setAdapter(contactListAdapter);

        contactListViewModel = ViewModelProviders.of(this,
                viewModelFactory).get(ContactListViewModel.class);

        observeViewModel();
        observeStates();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.contact_list_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.aesc:
                contactListViewModel.sortList(0);
                return true;
            case R.id.desc:
                contactListViewModel.sortList(-1);
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void observeStates() {
        contactListViewModel.statesLiveData.observe(this, state -> {
            if (state == null) {
                Toast.makeText(activity, getString(R.string.something_went_wrong), Toast.LENGTH_LONG).show();
                return;
            }

            if (States.ERROR == state.getState()) {
                if (state.getErrorThrowable() != null && state.getErrorThrowable() instanceof IOException) {
                    Toast.makeText(activity, getString(R.string.no_internet), Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(activity, getString(R.string.something_went_wrong), Toast.LENGTH_LONG).show();
                }
                spinKitView.setVisibility(View.GONE);
            } else if (States.LOADING == state.getState()) {
                spinKitView.setVisibility(View.VISIBLE);
                contactList.setVisibility(View.VISIBLE);
            } else if (States.LOADING_FINISHED == state.getState()) {
                spinKitView.setVisibility(View.GONE);
                contactList.setVisibility(View.VISIBLE);
            }
        });
    }

    private void observeViewModel() {
        contactListViewModel.contactListLiveData.observe(this, contacts -> {
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
