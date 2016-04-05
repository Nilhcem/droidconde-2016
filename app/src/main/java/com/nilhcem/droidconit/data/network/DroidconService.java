package com.nilhcem.droidconit.data.network;

import com.nilhcem.droidconit.data.network.model.Session;
import com.nilhcem.droidconit.data.network.model.Speaker;

import java.util.List;

import retrofit2.http.GET;
import rx.Observable;

public interface DroidconService {

    @GET("sessions")
    Observable<List<Session>> loadSessions();

    @GET("speakers")
    Observable<List<Speaker>> loadSpeakers();
}
