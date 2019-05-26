package za.co.stephanc.stones.model.game.cup;

import za.co.stephanc.stones.enums.CupType;
import za.co.stephanc.stones.enums.Player;

public abstract class Cup {
    Player owner;
    CupType type;
    int stones;
    int index;

    public Cup(){

    }

    public void incrementStones(){
        this.addStones(1);
    }

    public void addStones(int amount){
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

    public int getStones() {
        return stones;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public void setStones(int stones) {
        this.stones = stones;
    }

    public Cup(Player owner, CupType type, int stones) {
        this.owner = owner;
        this.type = type;
        this.stones = stones;
    }

    public String toString(){
        return String.format("Owner: %s, Stones: %d, Type: %s", this.type.name(), this.stones, this.owner.name());
    }
}
