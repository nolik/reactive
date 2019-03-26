package com.nolik.reactive;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.web.reactive.function.server.ServerResponse.ok;

@Component
@RequiredArgsConstructor
public class TwitHandler {
    private final TwitterService twitterService;

    Mono<ServerResponse> twitsByUser(ServerRequest request) {
        Flux<Twit> twits = twitterService.getUserTwits(request.pathVariable("username"));
        return ok().contentType(APPLICATION_JSON).body(twits, Twit.class);
    }

//    Mono<ServerResponse> twitsByUserId(ServerRequest request) {
//        Flux<Twit> twits = twitterService.getUserTwitsByUserId(request.pathVariable("userId"));
//        return ok().contentType(APPLICATION_JSON).body(twits, UserSumaryDTO.class);
//    }

    Mono<ServerResponse> allUsersTwits(ServerRequest request) {
        Flux<Twit> twits = twitterService.getUsersWithTwits(request);
        return ok().contentType(APPLICATION_JSON).body(twits, UserSumaryDTO.class);
    }
}
