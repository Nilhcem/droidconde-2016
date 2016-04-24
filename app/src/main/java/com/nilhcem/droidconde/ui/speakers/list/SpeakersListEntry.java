package com.nilhcem.droidconde.ui.speakers.list;

import android.text.TextUtils;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.nilhcem.droidconde.R;
import com.nilhcem.droidconde.data.app.model.Speaker;
import com.nilhcem.droidconde.ui.core.recyclerview.BaseViewHolder;
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
        String photoUrl = speaker.getPhoto();
        if (TextUtils.isEmpty(photoUrl)) {
            photo.setImageDrawable(null);
        } else {
            picasso.load(photoUrl).into(photo);
        }
        name.setText(speaker.getName());
    }
}
