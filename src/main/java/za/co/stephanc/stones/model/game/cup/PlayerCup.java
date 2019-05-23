package za.co.stephanc.stones.model.game.cup;

import za.co.stephanc.stones.enums.CupType;
import za.co.stephanc.stones.enums.Player;

public class PlayerCup extends Cup {

    public PlayerCup(Player owner, Integer stones){
        this.type = CupType.CUP;
        this.owner = owner;
        this.stones = stones;
    }
}
