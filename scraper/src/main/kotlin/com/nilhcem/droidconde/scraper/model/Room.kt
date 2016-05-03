package com.nilhcem.droidconde.scraper.model

enum class Room(val id: Int, val roomName: String) {

    NONE(0, ""),
    STAGE_1(1, "Stage 1"),
    STAGE_2(2, "Stage 2"),
    STAGE_3(3, "Stage 3"),
    STAGE_4(4, "Stage 4"),
    WORKSPACE(5, "Workspace");

    companion object {
        fun getRoomId(name: String) = Room.values().filter { name == it.roomName }.map { it.id }.getOrElse(0, { NONE.id })
    }
}
