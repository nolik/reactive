package com.nolik.reactive;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.http.MediaType.TEXT_EVENT_STREAM;
import static org.springframework.web.reactive.function.server.ServerResponse.ok;

@Component
@RequiredArgsConstructor
public class UserHandler {
    private final UserRepository userRepository;
    private final UserActionService userActionService;

    Mono<ServerResponse> listOfUsers(ServerRequest request) {
        Flux<User> users = userRepository.findAll();
        return ok().contentType(APPLICATION_JSON).body(users, User.class);
    }

    Mono<ServerResponse> userActions(ServerRequest request) {
        Flux<UserAction> userActions = userActionService.streamUserAction(request.pathVariable("id"));
        return ok().contentType(TEXT_EVENT_STREAM).body(userActions, UserAction.class);
    }
}
