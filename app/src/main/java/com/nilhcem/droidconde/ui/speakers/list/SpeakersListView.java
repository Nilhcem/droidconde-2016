package com.nilhcem.droidconde.ui.speakers.list;

import com.nilhcem.droidconde.data.app.model.Speaker;

import java.util.List;

public interface SpeakersListView {

    void displaySpeakers(List<Speaker> speakers);

    void displayLoadingError();

    void showSpeakerDetails(Speaker speaker);
}
