package com.nolik.reactive;


import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonPOJOBuilder
public class Twit {
    private String id;

    private String userName;

    private String text;
}
