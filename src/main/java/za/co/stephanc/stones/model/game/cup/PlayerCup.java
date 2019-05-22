package za.co.stephanc.stones.model.game;

import za.co.stephanc.stones.enums.PitType;
import za.co.stephanc.stones.enums.Player;

public class Cup {
    Player owner;
    Integer stones;
    PitType type;


    public Cup(Player owner, Integer stones, PitType type){
        this.owner = owner;
        this.stones = stones;
        this.type = type;
    }

    public Integer getStones(){
        return stones;
    }

    public Player getOwner(){
        return owner;
    }

    public void increment(){
        stones++;
    }
}
