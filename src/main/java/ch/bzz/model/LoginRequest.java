package ch.bzz.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class LoginRequest {
    @JsonProperty("project_name")
    private String projectName;
    private String password;
}
