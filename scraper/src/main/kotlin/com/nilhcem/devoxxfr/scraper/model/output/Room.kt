package com.nilhcem.devoxxfr.scraper.model.output

enum class Room(val id: Int, val devoxxId: String, val roomName: String) {

    EXHIBITION_FLOOR(0, "a_hall", "Exhibition floor"),
    AMPHI_BLEU(1, "b_amphi", "Amphi Bleu"),
    MAILLOT(2, "c_maillot", "Maillot"),
    NEUILLY_251(3, "f_neu251", "Neuilly 251"),
    NEUILLY_252_AB(4, "e_neu252", "Neuilly 252 AB"),
    NEUILLY_253_LAB(5, "neu253", "Neuilly 253 Lab"),
    NEUILLY_253(6, "neu253_t", "Neuilly 253"),
    NEUILLY_212_213M_LAB(7, "neu_212_213", "Neuilly 212-213M Lab"),
    NEUILLY_231_232M_LAB(8, "neu_232_232", "Neuilly 231-232M Lab"),
    NEUILLY_234_234M_LAB(9, "neu_234_235", "Neuilly 234_234M Lab"),
    OPEN_DATA_CAMP(10, "x_hall_a", "Open Data Camp"),
    PARIS_201(11, "par201", "Paris 201"),
    PARIS_202_203_LAB(12, "par202_203", "Paris 202-203 Lab"),
    PARIS_204(13, "par204", "Paris 204"),
    PARIS_221M_222M_LAB(14, "par221M-222M", "Paris 221M-222M Lab"),
    PARIS_224M_225M_LAB(15, "par224M-225M", "Paris 224M-225M Lab"),
    PARIS_241(16, "d_par241", "Paris 241"),
    PARIS_242A(17, "par242AT", "Paris 242A"),
    PARIS_242B(18, "par242BT", "Paris 242B"),
    PARIS_242AB(19, "par242AB", "Paris 242-AB"),
    PARIS_242A_LAB(20, "par242A", "Paris 242A Lab"),
    PARIS_242B_LAB(21, "par242B", "Paris 242B Lab"),
    PARIS_243(22, "par243", "Paris 243"),
    PARIS_243_T(23, "par243_t", "Paris 243");

    companion object {
        fun getRoomId(devoxxId: String) = Room.values().filter { devoxxId == it.devoxxId }.map { it.id }.getOrElse(0, { EXHIBITION_FLOOR.id })
    }
}
