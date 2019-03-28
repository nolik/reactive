package com.nolik.reactive;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.Date;
import java.util.Random;
import java.util.stream.Stream;

@Service
public class UserActionService {

    Flux<UserAction> streamUserAction(String userId) {
        return Flux.fromStream(Stream.generate(
                () -> new UserAction(userId, new Date(), randomAction())))
                //                .flatMap(this::actionMediator);
                .delayElements(Duration.ofSeconds(1));
    }

    private Flux<UserAction> actionMediator(UserAction userAction) {
        return null;
    }

    private String randomAction() {
        String[] actions = {"write twit", "follow", "unfollow"};
        return actions[new Random().nextInt(actions.length)];
    }
}

@Data
@AllArgsConstructor
class UserAction {
    private String username;
    private Date date;
    private String userAction;
}
