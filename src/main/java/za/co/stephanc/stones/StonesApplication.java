package za.co.stephanc.stones;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
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

@SpringBootApplication
@RestController
public class StonesApplication {

    public static void main(String[] args) {
        SpringApplication.run(StonesApplication.class, args);
    }

    final FluxProcessor processor;
    final FluxSink sink;
    final AtomicLong counter;

    public StonesApplication() {
        this.processor = DirectProcessor.create().serialize();
        this.sink = processor.sink();
        this.counter = new AtomicLong();
    }

    @GetMapping("/send")
    public void test() {
        sink.next("Hello World #" + counter.getAndIncrement());
    }

    @RequestMapping(produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<ServerSentEvent> sse() {
        return processor.map(e -> ServerSentEvent.builder(e).build());
    }
}
