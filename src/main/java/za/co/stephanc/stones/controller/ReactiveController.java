package za.co.stephanc.stones.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.*;
import za.co.stephanc.stones.model.Session;
import za.co.stephanc.stones.repository.SessionRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
public class ReactiveController {

    private static final Logger logger = LoggerFactory.getLogger(ReactiveController.class);

    @Autowired
    private SessionRepository sessionRepository;

    @GetMapping(path = "/chat", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<Session> chatEvent(@RequestParam final String uuid){
        logger.info("Fluxing " + uuid);

        return sessionRepository.findById(uuid);
    }

    @GetMapping(path = "/message")
    public void addMesssage(
            @RequestParam final String id,
            @RequestParam final String sender,
            @RequestParam final String message
    ){
        logger.info("Creating new message");
        Session session = sessionRepository.findOrCreateById(id);
        session.addMessage(sender, message);
    }


    @GetMapping(path = "/sow")
    public void addMesssage(
            @RequestParam final String id,
            @RequestParam final int index
    ){
        logger.info("Creating new message");
        Session session = sessionRepository.findOrCreateById(id);
        session.getMancala().sow(index);
    }
}
