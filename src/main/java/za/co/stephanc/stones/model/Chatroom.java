package za.co.stephanc.stones.model;

import java.util.ArrayList;
import java.util.List;

public class Chatroom {

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
        System.out.println("Creating new chatroom " + id);
        this.id = id;
        this.messages = new ArrayList<>();
    }



    @Override
    public String toString(){
        return "Comment{" +
                "author='" + id + '\'' +
                ", message='" + id + '\'' +
                ", timestamp='" + id + '\'' +
                '}';
    }
}
