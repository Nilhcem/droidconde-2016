package com.nilhcem.droidconde.data.app.model;

import android.support.annotation.NonNull;

public enum Room {

    NONE(0, ""),
    STAGE_1(1, "Stage1"),
    STAGE_2(2, "Stage2"),
    STAGE_3(3, "Stage3"),
    STAGE_4(4, "Stage4"),
    WORKSPACE(5, "Workspace");

    public final int id;
    public final String label;

    Room(int id, String label) {
        this.id = id;
        this.label = label;
    }

    public static Room getFromId(int id) {
        for (Room room : Room.values()) {
            if (room.id == id) {
                return room;
            }
        }
        return NONE;
    }

    public static Room getFromLabel(@NonNull String label) {
        for (Room room : Room.values()) {
            if (label.equals(room.label)) {
                return room;
            }
        }
        return NONE;
    }
}
