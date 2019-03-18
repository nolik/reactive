package com.nolik.reactive;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

@Component
@RequiredArgsConstructor
public class Initializer implements ApplicationRunner {

    private final UserRepository userRepository;

    public Initializer(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        Flux.just("Ihar", "Vasia", "Petua")
                .map(User::new)
                .flatMap(userRepository::save)
                .thenMany(userRepository.findAll())
                .subscribe(System.out::println);
    }
}
