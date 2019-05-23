package za.co.stephanc.stones.enums;

import za.co.stephanc.stones.model.game.cup.Cup;

public enum CupType {
    MANCALA("MANCALA"),
    CUP("CUP");

    String name;

    CupType(String name){
        this.name = name;
    }
}
