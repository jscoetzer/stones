package za.co.stephanc.stones.repository;

import reactor.core.publisher.Flux;
import za.co.stephanc.stones.model.Chatroom;


public interface ChatroomRepository {

    Chatroom findOrCreateById(String id);

    Flux<Chatroom> findById(String id);
}
