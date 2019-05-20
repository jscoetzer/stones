package za.co.stephanc.stones.controller;


import org.springframework.http.MediaType;
import org.springframework.http.codec.ServerSentEvent;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.DirectProcessor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.FluxProcessor;
import reactor.core.publisher.FluxSink;

import java.util.concurrent.atomic.AtomicLong;

@RestController
public class ReactiveController {

    final FluxProcessor processor;
    final FluxSink sink;
    final AtomicLong counter;

    public ReactiveController() {
        this.processor = DirectProcessor.create().serialize();
        this.sink = processor.sink();
        this.counter = new AtomicLong();
    }

    @GetMapping("/send")
    public void test(String message) {
        sink.next("Message #" + counter.getAndIncrement() + ": " + message);
    }

    @RequestMapping(path="/stream/messages", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<ServerSentEvent> sse(String clientId) {
        System.out.println(clientId);
        return processor.map(e -> ServerSentEvent.builder(e).build());
    }

}
