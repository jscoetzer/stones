package za.co.stephanc.stones.model;

import java.util.stream.IntStream;

public class Board {

    private Side playerOneSide;
    private Side playerTwoSide;

    public Board(Integer numberOfPits, Integer stonesPerPit){
        playerOneSide = new Side(numberOfPits, stonesPerPit);
        playerTwoSide = new Side(numberOfPits, stonesPerPit);
    }


    public Boolean Sow(Boolean isPlayerOneTurn, Integer selectedPitIndex){

        Side activeSide = (isPlayerOneTurn) ? playerOneSide : playerTwoSide;

        Integer amountOfStonesInHand = activeSide.stoneCount.get(selectedPitIndex);
        if (selectedPitIndex > stoneCount.size()){
            throw new RuntimeException("Selected pit does not exist!");
        }


        stoneCount
        IntStream.rangeClosed(1,6)
                .forEach( i -> );

        return false;
    }

}
