package za.co.stephanc.stones.model;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class Side {
    private List<Integer> stoneCount;
    private Integer scoringPitStoneCount;


    public List<Integer> getStoneCount() {
        return stoneCount;
    }

    public Integer getScore() {
        return scoringPitStoneCount;
    }

    public void incrementPitStoneCount(Integer pitNumber){
        stoneCount.set(pitNumber, stoneCount.get(pitNumber) + 1);
    }

    public void addScore(Integer amountToAdd){
        this.scoringPitStoneCount += amountToAdd;
    }

    public void incrementScore(){
        this.addScore(1);
    }

    public Side(Integer numberOfPits, Integer stonesPerPit){
        this.scoringPitStoneCount = 0;
        this.stoneCount = new ArrayList<>();
        IntStream.rangeClosed(1, numberOfPits)
                .forEach( i -> this.stoneCount.add(stonesPerPit));
    }


}
