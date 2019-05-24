package za.co.stephanc.stones.repository;

import reactor.core.publisher.Flux;
import za.co.stephanc.stones.model.Session;


public interface ChatroomRepository {

    Session findOrCreateById(String id);

    Flux<Session> findById(String id);

}
