package com.nilhcem.droidconit.data.network;

import android.content.Context;

import com.nilhcem.droidconit.BuildConfig;

public enum ApiEndpoint {

    PROD(BuildConfig.API_ENDPOINT);

    public String url;

    ApiEndpoint(String url) {
        this.url = url;
    }

    public static ApiEndpoint get(Context context) {
        return PROD;
    }
}
