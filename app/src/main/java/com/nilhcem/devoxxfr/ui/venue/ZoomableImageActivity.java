package com.nilhcem.devoxxfr.ui.venue;

import android.os.Bundle;
import android.support.annotation.DrawableRes;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v4.content.ContextCompat;

import com.nilhcem.devoxxfr.R;
import com.nilhcem.devoxxfr.ui.BaseActivity;
import com.nilhcem.devoxxfr.ui.BaseActivityPresenter;

import se.emilsjolander.intentbuilder.Extra;
import se.emilsjolander.intentbuilder.IntentBuilder;
import uk.co.senab.photoview.PhotoView;

@IntentBuilder
public class ZoomableImageActivity extends BaseActivity<ZoomableImageActivity.ZoomableImageActivityPresenter> {

    public static final Integer TYPE_ROOMS = 0;
    public static final Integer TYPE_EXHIBITORS = 1;

    @Extra Integer type;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ZoomableImageActivityIntentBuilder.inject(getIntent(), this);
        PhotoView view = new PhotoView(this);

        @DrawableRes int drawableRes;
        @StringRes int titleRes;
        if (type.equals(TYPE_ROOMS)) {
            drawableRes = R.drawable.venue_rooms;
            titleRes = R.string.venue_see_rooms;
        } else {
            drawableRes = R.drawable.venue_exhibitors;
            titleRes = R.string.venue_see_exhibitors;
        }

        view.setImageDrawable(ContextCompat.getDrawable(this, drawableRes));
        getSupportActionBar().setTitle(titleRes);
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

