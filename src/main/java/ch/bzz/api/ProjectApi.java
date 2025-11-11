package ch.bzz.api;

import ch.bzz.model.LoginRequest;
import ch.bzz.model.LoginProject200Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

public interface ProjectApi {
    @PostMapping("/projects")
    ResponseEntity<Void> createProject(@RequestBody LoginRequest loginRequest);

    @PostMapping("/login")
    ResponseEntity<LoginProject200Response> loginProject(@RequestBody LoginRequest loginRequest);
}
