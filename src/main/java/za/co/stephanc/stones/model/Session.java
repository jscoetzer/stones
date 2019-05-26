package za.co.stephanc.stones.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import za.co.stephanc.stones.enums.Player;
import za.co.stephanc.stones.model.chat.Message;
import za.co.stephanc.stones.model.game.Mancala;

public class Session {

    private static final Logger logger = LoggerFactory.getLogger(Session.class);

    private String id;
    private Mancala mancala;

    public String getId() {
        return id;
    }

    public Mancala getMancala() { return mancala; }

    public Session(String id){
        logger.info("Creating new session: " + id);
        this.id = id;
        this.mancala = new Mancala(6,6);
    }


}
