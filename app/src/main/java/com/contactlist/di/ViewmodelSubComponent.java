package com.contactlist.di;

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
}
