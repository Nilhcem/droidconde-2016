package com.nilhcem.devoxxfr.ui.speakers.list;

import com.nilhcem.devoxxfr.data.app.model.Speaker;

import java.util.List;

public interface SpeakersListView {

    void displaySpeakers(List<Speaker> speakers);

    void displayLoadingError();

    void showSpeakerDetails(Speaker speaker);
}
