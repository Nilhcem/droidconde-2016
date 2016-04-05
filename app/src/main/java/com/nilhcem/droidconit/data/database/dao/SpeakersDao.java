package com.nilhcem.droidconit.data.database.dao;

import com.nilhcem.droidconit.data.app.AppMapper;
import com.nilhcem.droidconit.data.database.DbMapper;
import com.nilhcem.droidconit.data.database.model.Speaker;
import com.nilhcem.droidconit.utils.Preconditions;
import com.squareup.sqlbrite.BriteDatabase;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.inject.Singleton;

import rx.Observable;

@Singleton
public class SpeakersDao {

    private final BriteDatabase database;
    private final DbMapper dbMapper;
    private final AppMapper appMapper;

    @Inject
    public SpeakersDao(BriteDatabase database, DbMapper dbMapper, AppMapper appMapper) {
        this.database = database;
        this.dbMapper = dbMapper;
        this.appMapper = appMapper;
    }

    public Observable<Map<Integer, com.nilhcem.droidconit.data.app.model.Speaker>> getSpeakersMap() {
        return getSpeakers().map(appMapper::speakersToMap);
    }

    public Observable<List<com.nilhcem.droidconit.data.app.model.Speaker>> getSpeakers() {
        return database.createQuery(Speaker.TABLE, "SELECT * FROM " + Speaker.TABLE)
                .mapToList(Speaker.MAPPER)
                .first()
                .map(dbMapper::toAppSpeakers);
    }

    public void saveSpeakers(List<com.nilhcem.droidconit.data.app.model.Speaker> toSave) {
        Preconditions.checkNotOnMainThread();

        BriteDatabase.Transaction transaction = database.newTransaction();
        try {
            database.delete(Speaker.TABLE, null);
            for (com.nilhcem.droidconit.data.app.model.Speaker speaker : toSave) {
                database.insert(Speaker.TABLE, Speaker.createContentValues(dbMapper.fromAppSpeaker(speaker)));
            }
            transaction.markSuccessful();
        } finally {
            transaction.end();
        }
    }
}
