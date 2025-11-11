package ch.bzz.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class LoginProject200Response {
    @JsonProperty("access_token")
    private String accessToken;
}
