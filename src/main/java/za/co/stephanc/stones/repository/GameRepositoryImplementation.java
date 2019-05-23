package za.co.stephanc.stones.repository;

import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import za.co.stephanc.stones.model.chat.Chatroom;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Repository
public class GameRepositoryImplementation implements GameRepository{

    public List<Mancala> mancalas;

    public GameRepositoryImplementation(){
        mancalas = new ArrayList<>();
    }

    public Flux<Chatroom> findById(String id){
        return Flux.interval(Duration.ofMillis(1000))
                .onBackpressureDrop()
                .map( m -> getChatroom(m, id))
                .flatMapIterable(x -> x);
    }

    public Chatroom findOrCreateById(String id){
        Chatroom room = this.mancalas.stream()
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
