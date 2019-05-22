package za.co.stephanc.stones.model.chat;


import java.util.Date;

public class Message {

    public Message(String sender, String content){
        this.sender = sender;
        this.content = content;
        this.time = new Date();
    }

    private Date time;
    private String sender;

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    private String content;

}
