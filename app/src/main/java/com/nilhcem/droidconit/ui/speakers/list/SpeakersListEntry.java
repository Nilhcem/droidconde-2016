package com.nilhcem.droidconit.ui.speakers.list;

import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.nilhcem.droidconit.R;
import com.nilhcem.droidconit.data.app.model.Speaker;
import com.nilhcem.droidconit.ui.core.recyclerview.BaseViewHolder;
import com.squareup.picasso.Picasso;

import butterknife.Bind;

public class SpeakersListEntry extends BaseViewHolder {

    @Bind(R.id.speakers_list_entry_photo) ImageView photo;
    @Bind(R.id.speakers_list_entry_name) TextView name;

    private final Picasso picasso;

    public SpeakersListEntry(ViewGroup parent, Picasso picasso) {
        super(parent, R.layout.speakers_list_entry);
        this.picasso = picasso;
    }

    public void bindSpeaker(Speaker speaker) {
        picasso.load(speaker.getPhoto()).into(photo);
        name.setText(speaker.getName());
    }
}
