package com.nilhcem.devoxxfr.data.network;

import com.nilhcem.devoxxfr.data.network.model.Session;
import com.nilhcem.devoxxfr.data.network.model.Speaker;

import java.util.List;

import retrofit2.http.GET;
import rx.Observable;

public interface DevoxxService {

    @GET("sessions")
    Observable<List<Session>> loadSessions();

    @GET("speakers")
    Observable<List<Speaker>> loadSpeakers();
}
