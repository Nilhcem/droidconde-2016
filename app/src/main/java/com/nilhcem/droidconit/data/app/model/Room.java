package com.nilhcem.droidconit.data.app.model;

import android.support.annotation.NonNull;

public enum Room {

    NONE(0, ""),
    EXHIBITION_FLOOR(1, "Exhibition floor"),
    OPEN_DATA_CAMP(2, "Open Data Camp"),
    AMPHI_BLEU(3, "Amphi Bleu"),
    C_MAILLOT(4, "Maillot"),
    PARIS_241(5, "Paris 241"),
    NEUILLY_251(6, "Neuilly 251"),
    NEUILLY_252_AB(7, "Neuilly 252 AB"),
    PARIS_242A_LAB(8, "Paris 242A Lab"),
    PARIS_242B(9, "Paris 242B Lab"),
    PARIS_243(10, "Paris 243"),
    PARIS_243_T(11, "Paris 243"),
    NEUILLY_253_LAB(12, "Neuilly 253 Lab"),
    NEUILLY_253(13, "Neuilly 253"),
    PARIS_202_203_LAB(14, "Paris 202-203 Lab"),
    PARIS_221M_222M_LAB(15, "Paris 221M-222M Lab"),
    PARIS_224M_225M_LAB(16, "Paris 224M-225M Lab"),
    PARIS_204(17, "Paris 204"),
    PARIS_201(18, "Paris 201");

    public final int id;
    public final String name;

    Room(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public static Room getFromId(int id) {
        for (Room room : Room.values()) {
            if (room.id == id) {
                return room;
            }
        }
        return NONE;
    }

    public static Room getFromName(@NonNull String name) {
        for (Room room : Room.values()) {
            if (name.equals(room.name)) {
                return room;
            }
        }
        return NONE;
    }
}
