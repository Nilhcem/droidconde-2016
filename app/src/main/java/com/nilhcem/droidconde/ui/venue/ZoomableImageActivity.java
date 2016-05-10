package com.nilhcem.droidconde.ui.venue;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;

import com.nilhcem.droidconde.R;
import com.nilhcem.droidconde.ui.BaseActivity;
import com.nilhcem.droidconde.ui.BaseActivityPresenter;

import se.emilsjolander.intentbuilder.IntentBuilder;
import uk.co.senab.photoview.PhotoView;

@IntentBuilder
public class ZoomableImageActivity extends BaseActivity<ZoomableImageActivity.ZoomableImageActivityPresenter> {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ZoomableImageActivityIntentBuilder.inject(getIntent(), this);
        PhotoView view = new PhotoView(this);

        view.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.venue_rooms));
        getSupportActionBar().setTitle(R.string.venue_see_rooms);
        setContentView(view);
    }

    @Override
    protected ZoomableImageActivityPresenter newPresenter() {
        return new ZoomableImageActivityPresenter(this);
    }

    static class ZoomableImageActivityPresenter extends BaseActivityPresenter<ZoomableImageActivity> {
        public ZoomableImageActivityPresenter(ZoomableImageActivity view) {
            super(view);
        }
    }
}

