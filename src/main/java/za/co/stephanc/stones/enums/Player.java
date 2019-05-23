package za.co.stephanc.stones.enums;

public enum Player {
    A("A"),
    B("B");

    String name;

    Player(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }

}
