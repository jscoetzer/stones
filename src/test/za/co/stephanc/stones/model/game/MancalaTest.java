package za.co.stephanc.stones.model.game;

import org.junit.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.Assert.*;

public class MancalaTest {

    @Test
    public void constructorTestCupAmount(){
        Mancala mancala = new Mancala(4,4);
        assertEquals(mancala.cups.size(), 10);
    }

    @Test
    public void constructorTestStonesInCups(){
        Mancala mancala = new Mancala(4,4);
        assert mancala.cups.get(0).getStones() == 4;
    }

    @Test
    public void sow() {
    }
}