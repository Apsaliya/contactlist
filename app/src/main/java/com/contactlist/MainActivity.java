package com.contactlist;

import android.arch.lifecycle.ViewModelProvider;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.contactlist.view.adapter.ContactListAdapter;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity {

    @Inject
    ViewModelProvider.Factory viewModelFactory;

    @BindView(R.id.contact_list)
    RecyclerView contactList;

    private ContactListAdapter contactListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setAdapter();
    }

    private void setAdapter() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this.getApplicationContext());
        contactList.setLayoutManager(linearLayoutManager);
        contactListAdapter = new ContactListAdapter();
        contactList.setAdapter(contactListAdapter);

    }

    @Override
    public void initDagger() {
        ((ContactList)getApplicationContext()).getMyComponent().inject(this);
    }

    @Override
    public void initButterKnife() {
        ButterKnife.bind(this);
    }
}
