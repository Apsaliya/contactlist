package com.contactlist.view.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.contactlist.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by ankit on 21/01/18.
 */

public class ContactListAdapter extends RecyclerView.Adapter<ContactListAdapter.ContactViewHolder> {
    //rivate List<? extends Project> projectList;

    @Override
    public ContactViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(ContactViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public static abstract class GenericViewHolder extends RecyclerView.ViewHolder {

        public GenericViewHolder(View rowView) {
            super(rowView);
        }

        protected abstract void bindView(int position);
    }

    static class ContactViewHolder extends GenericViewHolder {

        @BindView(R.id.profile_photo) ImageView profilePhoto;
        @BindView(R.id.header_text) TextView header;
        @BindView(R.id.subheader_text) TextView subHeader;
        @BindView(R.id.info_text) TextView infp;

        public ContactViewHolder(View view) {
            super(view);
            ButterKnife.bind(this.itemView);
        }

        @Override
        protected void bindView(int position) {

        }
    }
}
