package za.co.stephanc.stones.model.game;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import za.co.stephanc.stones.controller.ReactiveController;
import za.co.stephanc.stones.enums.CupType;
import za.co.stephanc.stones.enums.Player;
import za.co.stephanc.stones.model.game.cup.Cup;
import za.co.stephanc.stones.model.game.cup.MancalaCup;
import za.co.stephanc.stones.model.game.cup.PlayerCup;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.IntStream;

public class Mancala {

    private static final Logger logger = LoggerFactory.getLogger(Mancala.class);

    private Boolean isGameOver;
    private List<Cup> cups;
    private Player activePlayer;
    private Player winner;

    public Mancala(int stonesPerCup, int cupsPerPlayer){
        isGameOver = false;
        activePlayer = Player.A;
        cups = new ArrayList<>();
        Arrays.asList(Player.values())
                .forEach( player -> createPlayerCups(player, stonesPerCup, cupsPerPlayer));
    }

    public void sow(int selectedCup){
        logger.info("Sowing from cup " + selectedCup);
        int currentIndex = selectedCup;
        Cup currentCup = this.getCup(currentIndex);

        //"Pick up" the current cup's stones
        int stonesInHand = currentCup.getStones();
        currentCup.empty();

        while(stonesInHand > 0){
            logger.info("Stones: " + stonesInHand + " selected: " + selectedCup + " index: " + currentIndex + " player: " + activePlayer);

            //First, get the next cup in the sequence
            currentIndex = this.getNextCupIndex(currentIndex);
            currentCup = this.getCup(currentIndex);

            //Only incrementStones stones in your own cups, or your mancala.
            if (currentCup.getOwner().equals(activePlayer) || currentCup.getType().equals(CupType.CUP)){
                currentCup.incrementStones();
                stonesInHand--;
            }
        }

        //If the last stone landed in the current player's previously empty cup, take the opposite cup's stones + current cup's stone and add to score
        //Finally, empty both cups
        if (currentCup.getOwner().equals(activePlayer) && currentCup.getType().equals(CupType.CUP) && currentCup.getStones() == 1){
            logger.info("Stealing opponent's stones for cup " + currentIndex + " from " + this.getOppositeCupIndex(currentIndex));
            Cup oppositeCup = this.getCup(this.getOppositeCupIndex(currentIndex));
            Cup mancala = this.getPlayerMancalaCup();
            mancala.addStones(oppositeCup.getStones() + 1);

            oppositeCup.empty();
            currentCup.empty();
        }

        //If the player's turn ended in his mancala, have another turn.
        if (!(currentCup.getType().equals(CupType.MANCALA) && currentCup.getOwner().equals(activePlayer))){
            swopPlayers();
        }

        if (this.checkGameOver()){
            setGameWinner();
        }
    }

    protected void setGameWinner(){
        this.isGameOver = true;
        this.winner = Arrays.stream(Player.values())
                .max(Comparator.comparing(this::getSumOfPlayerStones))
                .get();
    }

    private int getSumOfPlayerStones(Player player){
        return this.cups.stream()
                .filter(cup -> cup.getType().equals(CupType.MANCALA) && cup.getOwner().equals(player))
                .mapToInt(Cup::getStones)
                .sum();
    }

    private Cup getCup(int index){
        return this.cups.get(index);
    }

    public Cup getPlayerMancalaCup(){
        return this.cups.stream()
                .filter(cup -> cup.getType().equals(CupType.MANCALA) && cup.getOwner().equals(activePlayer))
                .findFirst()
                .orElse(null);
    }

    private int getNextCupIndex(int currentIndex){
        currentIndex++;
        return (currentIndex == cups.size()) ? 0 : currentIndex;
    }

    private void swopPlayers(){
        this.activePlayer = getOtherPlayer(activePlayer);
    }

    private Player getOtherPlayer(Player player){
        return (player.equals(Player.A)) ? Player.B : Player.A;
    }

    private void createPlayerCups(Player owner, int stonesPerCup, int cupsPerPlayer){
        IntStream.rangeClosed(1,cupsPerPlayer)
                .forEach( i -> cups.add(new PlayerCup(owner, stonesPerCup)));
        cups.add(new MancalaCup(owner));
    }

    private int getOppositeCupIndex(int currentIndex){
        //return <amount of cups> - <1 (because 0-based index) + 1 (because of the mancala in the way)> - <current cup>
        return cups.size() - 2 - currentIndex;
    }

    private Boolean checkGameOver(){
         int sumStones = this.cups.stream()
                .filter(cup -> cup.getType().equals(CupType.CUP) && cup.getOwner().equals(activePlayer))
                .mapToInt(Cup::getStones)
                .sum();

         logger.info("Sum stones = " + sumStones);
        return sumStones == 0;

    }

    public Boolean getIsGameOver() {
        return isGameOver;
    }

    public void setIsGameOver(Boolean won) {
        isGameOver = won;
    }

    public List<Cup> getCups() {
        return cups;
    }

    public void setCups(List<Cup> cups) {
        this.cups = cups;
    }

    public Player getActivePlayer() {
        return activePlayer;
    }

    public void setActivePlayer(Player activePlayer) {
        this.activePlayer = activePlayer;
    }


    public Player getWinner() {
        return winner;
    }

    public void setWinner(Player winner) {
        this.winner = winner;
    }
}
