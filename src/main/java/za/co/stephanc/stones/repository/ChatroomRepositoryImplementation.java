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

    public ChatroomRepositoryImplementation(){
        chatrooms = new ArrayList<>();
    }

    public Flux<Chatroom> findById(String id){
        return Flux.interval(Duration.ofMillis(1000))
                .onBackpressureDrop()
                .map( m -> getChatroom(m, id))
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

    private List<Chatroom> getChatroom(Long interval, String id){
        Chatroom room = findOrCreateById(id);
        return Arrays.asList(room);
    }
}
