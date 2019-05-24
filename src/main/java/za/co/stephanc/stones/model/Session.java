package za.co.stephanc.stones.model;

import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import za.co.stephanc.stones.model.chat.Message;
import za.co.stephanc.stones.model.game.Mancala;

public class Session {

    private static final Logger logger = LoggerFactory.getLogger(Session.class);

    private String id;
    private Mancala mancala;
    private List<Message> messages;

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

    public Session(String id){
        logger.info("Creating new session: " + id);
        this.id = id;
        this.messages = new ArrayList<>();
        this.mancala = new Mancala(6,6);
    }
}
