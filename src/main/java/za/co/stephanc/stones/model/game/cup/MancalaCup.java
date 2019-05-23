package za.co.stephanc.stones.model.game.cup;

import za.co.stephanc.stones.enums.CupType;
import za.co.stephanc.stones.enums.Player;

public class MancalaCup extends Cup {

    public MancalaCup(Player owner){
        this.owner = owner;
        this.type = CupType.MANCALA;
        this.stones = 0;

    }
}
