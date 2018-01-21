package com.contactlist.di;

import com.contactlist.viewmodel.ContactDetailsViewModel;
import com.contactlist.viewmodel.ContactListViewModel;

import dagger.Subcomponent;

/**
 * Created by ankit on 19/01/18.
 */
@Subcomponent
public interface ViewmodelSubComponent {
    @Subcomponent.Builder
    interface Builder {
        ViewmodelSubComponent build();
    }

    ContactListViewModel contactListViewModel();
    ContactDetailsViewModel contactDetailsViewModel();
}
