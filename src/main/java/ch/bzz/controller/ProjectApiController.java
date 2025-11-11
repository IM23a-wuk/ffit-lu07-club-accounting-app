package ch.bzz.controller;
 
import ch.bzz.model.Project;
import ch.bzz.repository.ProjectRepository;
import ch.bzz.util.JwtUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.RestController;
import ch.bzz.api.ProjectApi;
import ch.bzz.model.LoginRequest;
import ch.bzz.model.LoginProject200Response;
import lombok.extern.slf4j.Slf4j;

import java.util.Optional;

 
@RestController
@Slf4j
public class ProjectApiController implements ProjectApi {
 
    private final ProjectRepository projectRepository;
    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
    private final JwtUtil jwtUtil;
 
    public ProjectApiController(ProjectRepository projectRepository, JwtUtil jwtUtil) {
        this.projectRepository = projectRepository;
        this.jwtUtil = jwtUtil;
    }
 
    @Override
    public ResponseEntity<Void> createProject(LoginRequest loginRequest) {
        log.debug("Attempting to create project '{}'", loginRequest.getProjectName());
        if (projectRepository.findByProjectName(loginRequest.getProjectName()).isPresent()) {
            log.warn("Project '{}' already exists. Aborting creation.", loginRequest.getProjectName());
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }

        Project project = new Project();
        project.setProjectName(loginRequest.getProjectName());
        project.setPasswordHash(encoder.encode(loginRequest.getPassword()));
        projectRepository.save(project);

        log.info("Successfully created project '{}'", loginRequest.getProjectName());
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
 
    @Override
    public ResponseEntity<LoginProject200Response> loginProject(LoginRequest loginRequest) {
        log.debug("Attempting to login with project '{}'", loginRequest.getProjectName());
        Optional<Project> projectOpt = projectRepository.findByProjectName(loginRequest.getProjectName());
        if (projectOpt.isEmpty() || !encoder.matches(loginRequest.getPassword(), projectOpt.get().getPasswordHash())) {
            log.warn("Login failed for project '{}'", loginRequest.getProjectName());
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

        String token = jwtUtil.generateToken(projectOpt.get().getProjectName());
        LoginProject200Response response = new LoginProject200Response();
        response.setAccessToken(token);

        log.info("Successfully logged in project '{}'", loginRequest.getProjectName());
        return ResponseEntity.ok(response);
    }
    }