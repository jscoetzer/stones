package za.co.stephanc.stones.model.game;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import za.co.stephanc.stones.controller.MainController;
import za.co.stephanc.stones.enums.CupType;
import za.co.stephanc.stones.enums.Player;

import static org.junit.Assert.*;

public class MancalaTest {

    private static final Logger logger = LoggerFactory.getLogger(MainController.class);

    @Test
    public void constructorTestCupAmount(){

        Mancala mancala = new Mancala(4,4);

        assertEquals(mancala.getCups().size(), 10);
        assertEquals(mancala.getCups().get(4).getType(), CupType.MANCALA);
        assertEquals(mancala.getCups().get(0).getType(), CupType.CUP);
        assertEquals(mancala.getCups().get(0).getOwner(), Player.A);
        assertEquals(mancala.getCups().get(5).getOwner(), Player.B);
    }

    @Test
    public void constructorTestStonesInCups(){

        Mancala mancala = new Mancala(4,4);

        assert mancala.getCups().get(0).getStones() == 4;
    }

    @Test
    public void sowingTestStoneMovement(){
        Mancala mancala = new Mancala(2,3);
        mancala.sow(0);

        assert mancala.getCups().get(0).getStones() == 0;
        assert mancala.getCups().get(1).getStones() == 3;
        assert mancala.getCups().get(2).getStones() == 3;
        assert mancala.getCups().get(3).getStones() == 0;
    }

    @Test
    public void sowingTestStealingStones(){
        Mancala mancala = new Mancala(3, 6);
        mancala.getCups().get(1).setStones(0);
        mancala.getCups().get(0).setStones(1);

        mancala.sow(0);

        assertEquals(0, mancala.getCups().get(11).getStones());
        assertEquals(0, mancala.getCups().get(1).getStones());
        assertEquals(4, mancala.getCups().get(6).getStones());
    }

    @Test
    public void testSetWinner(){
        Mancala mancala = new Mancala(1,2);
        mancala.getCups().get(2).setStones(1);
        mancala.getCups().get(5).setStones(2);
        mancala.setGameWinner();

        assertEquals(Player.B, mancala.getWinner());
    }

    @Test
    public void sowingTestPlayerChange() {
        Mancala mancala = new Mancala(1,3);

        assertEquals(Player.A, mancala.getActivePlayer());

        mancala.sow(2);

        assertEquals(Player.A, mancala.getActivePlayer());

        mancala.sow(0);

        assertEquals(Player.B, mancala.getActivePlayer());;
    }

    @Test
    public void sowingTestWinCondition(){
        Mancala mancala = new Mancala(1, 4);

        for (int i = 0; i < 3; i++){
            mancala.getCups().get(i).setStones(0);
        }

        mancala.sow(3);

        assertEquals(true, mancala.getIsGameOver());
        assertEquals(Player.A, mancala.getWinner());
    }
}