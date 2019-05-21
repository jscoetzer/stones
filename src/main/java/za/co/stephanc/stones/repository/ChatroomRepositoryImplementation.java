package za.co.stephanc.stones.repository;

import org.springframework.stereotype.Repository;
import reactor.core.publisher.DirectProcessor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.FluxProcessor;
import reactor.core.publisher.FluxSink;
import za.co.stephanc.stones.model.Chatroom;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Repository
public class ChatroomRepositoryImplementation implements ChatroomRepository{

    public List<Chatroom> chatrooms;

    final FluxProcessor processor;
    final FluxSink sink;


    public ChatroomRepositoryImplementation(){
        chatrooms = new ArrayList<>();
        this.processor = DirectProcessor.create().serialize();
        this.sink = processor.sink();

    }

    public Flux<Chatroom> findById(String id){
        /*Chatroom chatroom = this.chatrooms.stream()
                .filter(c -> c.getId().equals(id))
                .findFirst()
                .orElse(new Chatroom(id));*/

        return Flux.interval(Duration.ofMillis(200))
                .onBackpressureDrop()
                .map( m -> generateChatroom(m, id)
                ).flatMapIterable(x -> x);
    }

    public Flux<String> findAll(String id){

        return Flux.interval(Duration.ofMillis(200))
                .onBackpressureDrop()
                .map( m -> getString(m))
                .flatMapIterable(x -> x);
    }

    public Chatroom findOrCreateById(String id){
        Chatroom room = this.chatrooms.stream()
                .filter(c -> c.getId().equalsIgnoreCase(id))
                .findFirst()
                .orElse(null);

        if (room == null) {
            room = new Chatroom(id);
            this.chatrooms.add(room);
        }

        return room;
    }

    private List<String> getString(Long interval){
        System.out.println(interval);
        return Arrays.asList(interval.toString() + "123123");
    }

    private List<Chatroom> generateChatroom(Long interval, String id){
        Chatroom room = new Chatroom("123123");
        room.addMessage("me", "test");

        return Arrays.asList(room);
    }

}
