package za.co.stephanc.stones.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.*;
import za.co.stephanc.stones.model.Chatroom;
import za.co.stephanc.stones.repository.ChatroomRepository;
import za.co.stephanc.stones.repository.ChatroomRepositoryImplementation;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@RestController
public class ReactiveController {


    final FluxProcessor processor;
    final FluxSink sink;
    final AtomicLong counter;

    List<Chatroom> chatroomList;
    final FluxSink chatSink;
    final FluxProcessor<List<Chatroom>, List<Chatroom>> chatProcessor;
    Flux<Chatroom> events;
    //final Flux<Chatroom> chats;

    @Autowired
    private ChatroomRepository chatroomRepository;

    public ReactiveController() {
        this.processor = DirectProcessor.create().serialize();
        this.sink = processor.sink();
        this.counter = new AtomicLong();
        this.chatroomList = new ArrayList<>();

        this.chatProcessor = DirectProcessor.create();
        this.chatSink = chatProcessor.sink();
        this.events = Flux.fromStream(chatroomList.stream());
    }

    @GetMapping("/send")
    public void test(String message) {
        sink.next("Message #" + counter.getAndIncrement() + ": " + message);
    }

    @GetMapping(path = "/chatevents/{number}")
    public Flux<Integer> slowCountToNumber(@PathVariable("number") String id) {
        Flux<Integer> dynamicFlux = Flux.create(sink -> {
            SlowCounter.count(sink, id);
        });
        return dynamicFlux;
    }

    @GetMapping(path = "/chat", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<Chatroom> chatEvent(@RequestParam final String uuid){
        System.out.println("Fluxing " + uuid);

        return chatroomRepository.findById(uuid);
    }

    @GetMapping(path = "/message")
    public void addMesssage(
            @RequestParam final String id,
            @RequestParam final String sender,
            @RequestParam final String message
    ){
        System.out.println("Creating new message");
        Chatroom chatroom = chatroomRepository.findOrCreateById(id);
        //chatroom.addMessage(sender, message);

        //this.events = Flux.fromStream(chatroomList.stream());
    }

}
