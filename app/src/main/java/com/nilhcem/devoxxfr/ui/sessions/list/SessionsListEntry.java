package com.nilhcem.devoxxfr.ui.sessions.list;

import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.nilhcem.devoxxfr.R;
import com.nilhcem.devoxxfr.data.app.model.Session;
import com.nilhcem.devoxxfr.ui.core.picasso.CircleTransformation;
import com.nilhcem.devoxxfr.ui.core.recyclerview.BaseViewHolder;
import com.nilhcem.devoxxfr.utils.App;
import com.squareup.picasso.Picasso;

import butterknife.Bind;

public class SessionsListEntry extends BaseViewHolder {

    @Bind(R.id.sessions_list_entry_photo) ImageView photo;
    @Bind(R.id.sessions_list_entry_title) TextView title;
    @Bind(R.id.sessions_list_entry_selected_state) ImageView selectedState;
    @Bind(R.id.sessions_list_entry_room) TextView room;
    @Bind(R.id.sessions_list_entry_description) TextView description;

    private final Picasso picasso;

    public SessionsListEntry(ViewGroup parent, Picasso picasso) {
        super(parent, R.layout.sessions_list_entry);
        this.picasso = picasso;
    }

    public void bindSession(Session session, boolean isSelected) {
        String photoUrl = App.getPhotoUrl(session);
        if (TextUtils.isEmpty(photoUrl)) {
            photo.setImageDrawable(null);
        } else {
            picasso.load(photoUrl).transform(new CircleTransformation()).into(photo);
        }

        title.setText(session.getTitle());
        room.setText(session.getRoom());
        description.setText(session.getDescription());

        int selectedRes = isSelected ? R.drawable.sessions_list_entry_selected : R.drawable.sessions_list_entry_default;
        selectedState.setImageDrawable(ContextCompat.getDrawable(selectedState.getContext(), selectedRes));
    }
}
