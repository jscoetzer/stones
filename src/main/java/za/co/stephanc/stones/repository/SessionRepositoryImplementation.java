package za.co.stephanc.stones.repository;

import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import za.co.stephanc.stones.model.Session;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Repository
public class SessionRepositoryImplementation implements SessionRepository {

    public List<Session> sessions;

    public SessionRepositoryImplementation(){
        sessions = new ArrayList<>();
    }

    public Flux<Session> findById(String id){
        return Flux.interval(Duration.ofMillis(100))
                .onBackpressureDrop()
                .map( m -> getChatroom(m, id))
                .flatMapIterable(x -> x);
    }

    public Session findOrCreateById(String id){
        Session room = this.sessions.stream()
                .filter(c -> c.getId().equalsIgnoreCase(id))
                .findFirst()
                .orElse(null);

        if (room == null) {
            room = new Session(id);
            this.sessions.add(room);
        }

        return room;
    }

    private List<Session> getChatroom(Long interval, String id){
        Session room = findOrCreateById(id);
        return Arrays.asList(room);
    }
}
