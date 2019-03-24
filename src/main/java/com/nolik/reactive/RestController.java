package com.nolik.reactive;

import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import reactor.core.publisher.Flux;

@org.springframework.web.bind.annotation.RestController
@RequiredArgsConstructor
public class RestController {
    private final UserRepository userRepository;
    private final UserActionService userActionService;

    @GetMapping(value = "/rest/users", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<User> getUsers(){
       return userRepository.findAll();
    }

    @GetMapping(value = "/rest/user/{id}/user-actions", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<UserAction> getUserActions(@PathVariable String id) {
       return userActionService.streamUserAction(id);
    }
}
