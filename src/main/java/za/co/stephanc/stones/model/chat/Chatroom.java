package za.co.stephanc.stones.model.chat;

import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Chatroom {

    private static final Logger logger = LoggerFactory.getLogger(Chatroom.class);

    private String id;

    private List<Message> messages;

    public List<Message> getMessages() {
        return messages;
    }

    public void addMessage(String sender, String message){
        this.messages.add(new Message(sender, message));
    }

    public String getId() {
        return id;
    }

    public Chatroom(String id){
        logger.info("Creating new chatroom: " + id);
        this.id = id;
        this.messages = new ArrayList<>();
    }
}
