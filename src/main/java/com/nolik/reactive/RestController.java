package com.nolik.reactive;

import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.util.function.Tuple2;

import java.time.Duration;

@org.springframework.web.bind.annotation.RestController
@RequiredArgsConstructor
public class RestController {
    private final UserRepository userRepository;
    private final UserActionService userActionService;
    private final WebClient client;

    @GetMapping(value = "/rest/users", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<User> getUsers(){
        Flux<Long> interval = Flux.interval(Duration.ofSeconds(1));

       return Flux.zip(interval, userRepository.findAll())
               .map(Tuple2::getT2);
    }

    @GetMapping(value = "/rest/user/{id}/actions", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<UserAction> getUserActions(@PathVariable String id) {
       return userActionService.streamUserAction(id);
    }

    @GetMapping(value = "/rest/users/twits", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<Twit> getAllTwits() {
        return client.get()
                .uri("/twits")
                .accept(MediaType.TEXT_EVENT_STREAM)
                .retrieve()
                .bodyToFlux(Twit.class);
    }

}
