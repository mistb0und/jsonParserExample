package org.example;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record User(
        String username,
        @JsonIgnore
        String password,
        @JsonProperty("user_name")
        String email) {
}
