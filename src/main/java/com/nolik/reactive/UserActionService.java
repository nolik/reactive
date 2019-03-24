package com.nolik.reactive;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.util.function.Tuple2;

import java.time.Duration;
import java.util.Date;
import java.util.Random;
import java.util.stream.Stream;

@Service
public class UserActionService {

    Flux<UserAction> streamUserAction(String userId) {
        Flux<Long> interval = Flux.interval(Duration.ofSeconds(1));

        Flux<UserAction> userActions = Flux.fromStream(Stream.generate(
                () -> new UserAction(userId, new Date(), randomAction())
        ));

        return Flux.zip(interval, userActions)
                .map(Tuple2::getT2);
    }

    private String randomAction() {
        String[] actions = {"write twit", "follow" , "unfollow"};
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
