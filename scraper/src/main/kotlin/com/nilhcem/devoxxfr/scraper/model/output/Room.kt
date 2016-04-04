package com.nilhcem.devoxxfr.scraper.model.output

enum class Room(val id: Int, val devoxxId: String, val roomName: String) {

    EXHIBITION_FLOOR(1, "a_hall", "Exhibition floor"),
    OPEN_DATA_CAMP(2, "x_hall_a", "Open Data Camp"),
    AMPHI_BLEU(3, "b_amphi", "Amphi Bleu"),
    C_MAILLOT(4, "c_maillot", "Maillot"),
    PARIS_241(5, "d_par241", "Paris 241"),
    NEUILLY_251(6, "f_neu251", "Neuilly 251"),
    NEUILLY_252_AB(7, "e_neu252", "Neuilly 252 AB"),
    PARIS_242A_LAB(8, "par242A", "Paris 242A Lab"),
    PARIS_242B(9, "par242B", "Paris 242B Lab"),
    PARIS_243(10, "par243", "Paris 243"),
    PARIS_243_T(11, "par243_t", "Paris 243"),
    NEUILLY_253_LAB(12, "neu253", "Neuilly 253 Lab"),
    NEUILLY_253(13, "neu253_t", "Neuilly 253"),
    PARIS_202_203_LAB(14, "par202_203", "Paris 202-203 Lab"),
    PARIS_221M_222M_LAB(15, "par221M-222M", "Paris 221M-222M Lab"),
    PARIS_224M_225M_LAB(16, "par224M-225M", "Paris 224M-225M Lab"),
    PARIS_204(17, "par204", "Paris 204"),
    PARIS_201(18, "par201", "Paris 201"),
    UNKNOWN(0, "", "");

    companion object {
        fun getRoomId(devoxxId: String) = Room.values().filter { devoxxId == it.devoxxId }.map { it.id }.getOrElse(0, { UNKNOWN.id })
    }
}
