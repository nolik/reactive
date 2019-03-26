package com.nolik.reactive;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserSumaryDTO {
    private String userId;
    private String userName;
    private List<Twit> twits;
}
