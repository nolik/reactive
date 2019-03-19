package com.nolik.reactive;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import reactor.core.publisher.Flux;

@org.springframework.web.bind.annotation.RestController
@RequiredArgsConstructor
public class RestController {
    private final UserRepository userRepository;

    @GetMapping("/rest/users")
    public Flux<User> getUsers(){
       return userRepository.findAll();
    }
}
