package com.nolik.reactive;

import lombok.RequiredArgsConstructor;
import org.reactivestreams.Subscriber;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.server.ServerRequest;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Collections;

@Service
@RequiredArgsConstructor
public class TwitterService {
    private final WebClient client;
    private final UserRepository userRepository;

    Flux<Twit> getAllTwits() {
        return client.get()
                .uri("/twits")
                .accept(MediaType.TEXT_EVENT_STREAM)
                .retrieve()
                .bodyToFlux(Twit.class);
    }

    Mono<Flux<Twit>> getUserTwitsByUserId(String userId) {
        return userRepository.findById(userId)
                .map(User::getName)
                .map(this::getUserTwits);
    }

    Flux<Twit> getUserTwits(String username) {
        return client.get()
                .uri("/twits/{username}", username)
                .accept(MediaType.TEXT_EVENT_STREAM)
                .retrieve()
                .bodyToFlux(Twit.class);
    }

    private Flux<Twit> getUserTwits(UserSumaryDTO userSumaryDTO) {
        return getUserTwits(userSumaryDTO.getUserName());
    }

    Flux<UserSumaryDTO> getUsersWithTwits(ServerRequest request) {
        return userRepository.findAll()
                .map(this::mapUserSummary)
                .mergeWith(this::getUserTwits);
    }

    private UserSumaryDTO mapUserSummary(User user) {
        return new UserSumaryDTO(user.getId(), user.getName(), Collections.emptyList());
    }
}
