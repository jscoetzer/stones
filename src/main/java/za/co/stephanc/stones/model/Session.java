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
    private List<Message> messages;

    private List<Player> players;

    public String getId() {
        return id;
    }

    public List<Message> getMessages() {
        return messages;
    }

    public void addMessage(String sender, String message){
        this.messages.add(new Message(sender, message));
    }

    public Mancala getMancala() { return mancala; }

    public List<Player> getPlayers() { return players; }

    public void setPlayers(List<Player> players) { this.players = players; }

    public Session(String id){
        logger.info("Creating new session: " + id);
        this.id = id;
        this.messages = new ArrayList<>();
        this.players = Arrays.asList(Player.values());
        this.mancala = new Mancala(6,6);
    }


}
