package za.co.stephanc.stones.model.game;

import za.co.stephanc.stones.enums.CupType;
import za.co.stephanc.stones.enums.Player;
import za.co.stephanc.stones.model.game.cup.Cup;
import za.co.stephanc.stones.model.game.cup.MancalaCup;
import za.co.stephanc.stones.model.game.cup.PlayerCup;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

public class Mancala {

    Boolean isWon;
    List<Cup> cups;
    Player activePlayer;

    public Mancala(Integer stonesPerCup, Integer cupsPerPlayer){
        isWon = false;
        activePlayer = Player.A;
        cups = new ArrayList<>();
        Arrays.asList(Player.values())
                .forEach( player -> createPlayerCups(player, stonesPerCup, cupsPerPlayer));
    }

    public void sow(Integer selectedCup){
        Integer currentIndex = selectedCup;
        Cup currentCup = this.getCup(currentIndex);

        //"Pick up" the current cup's stones
        Integer stonesInHand = currentCup.getStones();
        currentCup.empty();


        while(stonesInHand > 0){
            //First, get the next cup in the sequence
            currentIndex = this.getNextCupIndex(currentIndex);
            currentCup = this.getCup(currentIndex);

            //Only increment stones in your own cups, or your mancala.
            if (currentCup.getOwner().equals(activePlayer) || currentCup.getType().equals(CupType.CUP)){
                currentCup.increment();
                stonesInHand--;
            }
        }

        //If the last stone landed in the current player's previously empty cup, take the opposite cup's stones + current cup's stone and add to score
        //Finally, empty both cups
        if (currentCup.getOwner().equals(activePlayer) && currentCup.getStones() == 1 && stonesInHand == 0){
            Cup oppositeCup = this.getCup(this.getOppositeCupIndex(currentIndex));
            Cup mancala = this.getPlayerMancalaCup();
            mancala.addStones(oppositeCup.getStones() + 1);

            oppositeCup.empty();
            currentCup.empty();
        }

        isWon = this.isWinConditionReached();

        //If the player's turn ended in his mancala, have another turn.
        if (!(currentCup.getType().equals(CupType.MANCALA) && currentCup.getOwner().equals(activePlayer))){
            swopPlayers();
        }
    }

    private Cup getCup(Integer index){
        return this.cups.get(index);
    }

    private Cup getPlayerMancalaCup(){
        return this.cups.stream()
                .filter(cup -> cup.getType().equals(CupType.CUP) && cup.getOwner().equals(activePlayer))
                .findFirst()
                .orElse(null);
    }

    private Integer getNextCupIndex(Integer currentIndex){
        currentIndex++;
        return (currentIndex.equals(cups.size())) ? 0 : currentIndex;
    }

    private void swopPlayers(){
        this.activePlayer = getOtherPlayer(activePlayer);
    }

    private Player getOtherPlayer(Player player){
        return (player.equals(Player.A)) ? Player.B : Player.A;
    }

    private void createPlayerCups(Player owner, Integer stonesPerCup, Integer cupsPerPlayer){
        IntStream.rangeClosed(1,cupsPerPlayer)
                .forEach( i -> cups.add(new PlayerCup(owner, stonesPerCup)));
        cups.add(new MancalaCup(owner));
    }

    private Integer getOppositeCupIndex(Integer currentIndex){
        //return <amount of cups> - <1 (because 0-based index) + 1 (because of the mancala in the way)> - <current cup>
        return cups.size() - 2 - currentIndex;
    }

    private Boolean isWinConditionReached(){
        return this.cups.stream()
                .filter(cup -> cup.getType().equals(CupType.CUP) && cup.getOwner().equals(activePlayer))
                .mapToInt(Cup::getStones)
                .sum() == 0;
    }
}
