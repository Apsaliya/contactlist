package com.contactlist.view.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.contactlist.R;
import com.contactlist.network.model.Contact;
import com.contactlist.view.callback.ContactClickCallback;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by ankit on 21/01/18.
 */

public class ContactListAdapter extends RecyclerView.Adapter<ContactListAdapter.GenericViewHolder> {
    private List<Contact> contactList = new ArrayList<>();
    private ContactClickCallback contactClickCallback;

    public ContactListAdapter(ContactClickCallback contactClickCallback) {
        this.contactClickCallback = contactClickCallback;
    }

    @Override
    public GenericViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.contact, parent, false);
        view.setOnClickListener(v -> contactClickCallback.onContactClicked((Contact) v.getTag()));
        return new ContactViewHolder(view);
    }

    @Override
    public void onBindViewHolder(GenericViewHolder holder, int position) {
        holder.itemView.setTag(contactList.get(position));
        holder.bindView(position);
    }

    @Override
    public int getItemCount() {
        return contactList.size();
    }

    public static abstract class GenericViewHolder extends RecyclerView.ViewHolder {

        public GenericViewHolder(View rowView) {
            super(rowView);
        }

        protected abstract void bindView(int position);
    }

    static class ContactViewHolder extends GenericViewHolder {

        @BindView(R.id.header_text) TextView header;
        @BindView(R.id.subheader_text) TextView subHeader;
        @BindView(R.id.info_text) TextView info;

         ContactViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }

        @Override
        protected void bindView(int position) {
            Contact contact = (Contact) this.itemView.getTag();
            header.setText(contact.getName());
            subHeader.setText(contact.getEmail());
            info.setText(contact.getPhone());
        }
    }

    public void updateData(List<? extends Contact> newData) {
        contactList.clear();
        contactList.addAll(newData);
        notifyDataSetChanged();
    }
}
