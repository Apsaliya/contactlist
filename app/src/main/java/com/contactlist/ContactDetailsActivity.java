package com.contactlist;

import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.TextView;
import android.widget.Toast;

import com.contactlist.network.model.Contact;
import com.contactlist.utils.Cache;
import com.contactlist.viewmodel.ContactDetailsViewModel;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by ankit on 21/01/18.
 */

public class ContactDetailsActivity extends BaseActivity {

    private static final String INTENT_KEY = "contactlist.details.contactkey";

    @Inject
    ViewModelProvider.Factory viewModelFactory;

    @BindView(R.id.name) TextView name;
    @BindView(R.id.username) TextView username;
    @BindView(R.id.email) TextView email;
    @BindView(R.id.address) TextView address;
    @BindView(R.id.phone) TextView phone;
    @BindView(R.id.website) TextView website;
    @BindView(R.id.company) TextView company;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        setContentView(R.layout.contact_details_view);
        super.onCreate(savedInstanceState);

        String cacheKey = getIntent().getStringExtra(INTENT_KEY);
        final ContactDetailsViewModel viewModel = ViewModelProviders.of(this,
                viewModelFactory).get(ContactDetailsViewModel.class);

        viewModel.setCacheKey(cacheKey);

        observeViewModel(viewModel);
        observeErrorData(viewModel);
    }

    private void observeErrorData(ContactDetailsViewModel viewModel) {
        viewModel.errorLiveData.observe(this, throwable -> Toast.makeText(activity, getString(R.string.something_went_wrong), Toast.LENGTH_LONG).show());
    }

    private void observeViewModel(ContactDetailsViewModel viewModel) {
        viewModel.contactLiveData.observe(this, contact -> {
            name.setText(contact.getName());
            email.setText(contact.getEmail());
            phone.setText(contact.getPhone());
            company.setText(contact.getCompanyString());
            website.setText(contact.getWebsite());
            String usernameString = "@  " +
                    contact.getUsername();
            username.setText(usernameString);
            address.setText(contact.getAddressString());
        });
    }

    public static void start(Context context, Contact contact) {
        if (contact == null) {
            throw new NullPointerException("Cannot show details of a null contact!");
        }

        Intent intent = new Intent(context, ContactDetailsActivity.class);
        Cache cache = Cache.getInstance();
        cache.put(String.valueOf(contact.getId()), contact);
        intent.putExtra(INTENT_KEY, String.valueOf(contact.getId()));
        context.startActivity(intent);
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
