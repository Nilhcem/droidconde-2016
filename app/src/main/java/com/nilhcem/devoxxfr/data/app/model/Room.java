package com.nilhcem.devoxxfr.data.app.model;

import android.support.annotation.NonNull;

public enum Room {

    EXHIBITION_FLOOR(0, "Exhibition floor"),
    AMPHI_BLEU(1, "Amphi Bleu"),
    MAILLOT(2, "Maillot"),
    NEUILLY_251(3, "Neuilly 251"),
    NEUILLY_252_AB(4, "Neuilly 252 AB"),
    NEUILLY_253_LAB(5, "Neuilly 253 Lab"),
    NEUILLY_253(6, "Neuilly 253"),
    NEUILLY_212_213M_LAB(7, "Neuilly 212-213M Lab"),
    NEUILLY_231_232M_LAB(8, "Neuilly 231-232M Lab"),
    NEUILLY_234_234M_LAB(9, "Neuilly 234_234M Lab"),
    OPEN_DATA_CAMP(10, "Open Data Camp"),
    PARIS_201(11, "Paris 201"),
    PARIS_202_203_LAB(12, "Paris 202-203 Lab"),
    PARIS_204(13, "Paris 204"),
    PARIS_221M_222M_LAB(14, "Paris 221M-222M Lab"),
    PARIS_224M_225M_LAB(15, "Paris 224M-225M Lab"),
    PARIS_241(16, "Paris 241"),
    PARIS_242A(17, "Paris 242A"),
    PARIS_242B(18, "Paris 242B"),
    PARIS_242AB(19, "Paris 242-AB"),
    PARIS_242A_LAB(20, "Paris 242A Lab"),
    PARIS_242B_LAB(21, "Paris 242B Lab"),
    PARIS_243(22, "Paris 243"),
    PARIS_243_T(23, "Paris 243");

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
        return EXHIBITION_FLOOR;
    }

    public static Room getFromName(@NonNull String name) {
        for (Room room : Room.values()) {
            if (name.equals(room.name)) {
                return room;
            }
        }
        return EXHIBITION_FLOOR;
    }
}
