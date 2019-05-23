package za.co.stephanc.stones.model.game.cup;

import za.co.stephanc.stones.enums.CupType;
import za.co.stephanc.stones.enums.Player;

public abstract class Cup {
    Player owner;
    CupType type;
    Integer stones;

    public Cup(){

    }

    public void increment(){
        this.addStones(1);
    }

    public void addStones(Integer amount){
        this.stones += amount;
    }

    public void empty(){
        this.stones = 0;
    }

    public Player getOwner() {
        return owner;
    }

    public void setOwner(Player owner) {
        this.owner = owner;
    }

    public CupType getType() {
        return type;
    }

    public void setType(CupType type) {
        this.type = type;
    }

    public Integer getStones() {
        return stones;
    }

    public void setStones(Integer stones) {
        this.stones = stones;
    }

    public Cup(Player owner, CupType type, Integer stones) {
        this.owner = owner;
        this.type = type;
        this.stones = stones;
    }

    public String toString(){
        return String.format("Owner: %s, Stones: %d, Type: %s", this.type.name(), this.stones, this.owner.name());
    }
}
