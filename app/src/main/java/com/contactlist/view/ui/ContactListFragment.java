package com.contactlist.view.ui;

import android.arch.lifecycle.ViewModelProvider;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import javax.inject.Inject;

/**
 * Created by ankit on 19/01/18.
 */

public class ContactListFragment extends Fragment {

    @Inject
    ViewModelProvider.Factory viewModelFactory;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (viewModelFactory != null) {
            Log.d("ContactListFragment", "DI worked");
        }
        return super.onCreateView(inflater, container, savedInstanceState);
    }
}
