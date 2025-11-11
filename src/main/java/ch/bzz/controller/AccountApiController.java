package ch.bzz.controller;

import ch.bzz.generated.api.AccountApi;
import ch.bzz.generated.model.Account;
import ch.bzz.generated.model.UpdateAccountsRequest;
import ch.bzz.repository.AccountRepository;
import ch.bzz.repository.ProjectRepository;
import ch.bzz.util.JwtUtil;
import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@Slf4j
public class AccountApiController implements AccountApi {
    private final HttpServletRequest request;
    private final AccountRepository accountRepository;
    private final ProjectRepository projectRepository;
    private final JwtUtil jwtUtil;
    private final ModelMapper modelMapper;

    public AccountApiController(HttpServletRequest request, AccountRepository accountRepository, ProjectRepository projectRepository, JwtUtil jwtUtil, ModelMapper modelMapper) {
        this.request = request;
        this.accountRepository = accountRepository;
        this.projectRepository = projectRepository;
        this.jwtUtil = jwtUtil;
        this.modelMapper = modelMapper;
    }

    @Override
    public ResponseEntity<List<Account>> getAccounts() {
        try {
            String header = request.getHeader("Authorization");
            String token = header.substring(7);

            String projectName = jwtUtil.getClaim(token, Claims::getSubject);
            Optional<ch.bzz.model.Project> projectOpt = projectRepository.findByProjectName(projectName);
            if (projectOpt.isPresent()) {
                List<ch.bzz.model.Account> accounts = accountRepository.findByProject(projectOpt.get());
                List<Account> accountDtos = accounts.stream()
                        .map(account -> modelMapper.map(account, Account.class))
                        .toList();
                return ResponseEntity.ok(accountDtos);
            } else {
                log.warn("Project not found for token subject: {}", projectName);
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            log.error("Error retrieving accounts", e);
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }

    @Override
    public ResponseEntity<Void> updateAccounts(UpdateAccountsRequest updateAccountsRequest) {
        log.warn("Method 'updateAccounts' not implemented yet.");
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }
}
